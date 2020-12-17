package app.spring.caminhoneiro.application.service.impl;

import app.spring.caminhoneiro.application.service.CaminhoneiroService;
import app.spring.caminhoneiro.application.service.MailService;
import app.spring.caminhoneiro.domain.model.Caminhoneiro;
import app.spring.caminhoneiro.domain.model.Frete;
import app.spring.caminhoneiro.domain.model.FretePedido;
import app.spring.caminhoneiro.domain.reposity.CaminhoneiroRepository;
import app.spring.caminhoneiro.domain.reposity.FretePedidoRepository;
import app.spring.caminhoneiro.rest.dto.*;
import app.spring.caminhoneiro.rest.feign.CaminhoneiroClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CaminhoneiroServiceImpl implements CaminhoneiroService {

    @Autowired
    private CaminhoneiroRepository caminhoneiroRepository;

    @Autowired
    private FretePedidoRepository fretePedidoRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private CaminhoneiroClient caminhoneiroClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final Logger logger = LoggerFactory.getLogger(CaminhoneiroService.class);

    @Override
    @Transactional
    public Caminhoneiro salvar(Caminhoneiro caminhoneiro) {
        mailService.publishMessage(EmailDTO.builder()
                .to("nathanrodrigo70@gmail.com")
                .subject("Caminhoneiro Registrado!")
                .content("O caminhoneiro " + caminhoneiro.getNome() + " foi registrado com sucesso!")
                .build()
        );

        Caminhoneiro salvo = caminhoneiroRepository.save(caminhoneiro);

        //Atualiza a tabela no outro microservice
        caminhoneiroClient.salvar(salvo.getId());

        return salvo;
    }

    @Override
    @Transactional
    public InformacoesFreteDTO addFrete(AddFreteDTO dto) {
        //Encontra o caminhoneiro
        Caminhoneiro caminhoneiro = caminhoneiroRepository.findById(dto.getCaminhoneiro_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Frete frete = new Frete();
        frete.setId(dto.getFrete_id());

        Set<FretePedido> pedidos = converterParaSetFretePedido(frete, dto.getPedidos());
        fretePedidoRepository.saveAll(pedidos);

        frete.setCaminhoneiro(caminhoneiro);
        frete.setDestino(dto.getDestino());
        frete.setPedidos(pedidos);

        caminhoneiro.setFrete(frete);
        caminhoneiroRepository.save(caminhoneiro);

        //Atualiza os status dos pedidos e a tabela CaminhoneiroFrete
//        pedidoClient.atualizarStatus(AtualizaStatusDTO.builder()
//                .id_pedidos(dto.getPedidos())
//                .id_frete(frete.getId())
//                .novoStatus("POSTADO")
//                .build());

        return InformacoesFreteDTO.builder()
                .numero_frete(frete.getId())
                .nome_caminhoneiro(caminhoneiro.getNome())
                .destino(dto.getDestino())
                .ids_pedidos(dto.getPedidos())
                .build();
    }

    @Override
    @Transactional
    public CaminhoneiroDTO getById(Integer id) {
        Caminhoneiro caminhoneiro = caminhoneiroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return CaminhoneiroDTO.builder()
                .nome(caminhoneiro.getNome())
                .frete(caminhoneiro.getFrete() != null ? caminhoneiro.getFrete().getId() : -1)
                .build();
    }

    @Override
    public InformacoesFreteDTO getFrete(Integer idFrete) {
        return null;
    }

    @Override
    public void entregaFrete(Integer idCaminhoneiro) {
        Caminhoneiro caminhoneiro = caminhoneiroRepository.findById(idCaminhoneiro)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(caminhoneiro.getFrete() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Integer frete_id = caminhoneiro.getFrete().getId();

        logger.info("Sending message to RabbitMQ");
        rabbitTemplate.convertAndSend("", "pedido-queue", frete_id);
        logger.info("Message published!");

        caminhoneiro.setFrete(null);

    }

    private Set<FretePedido> converterParaSetFretePedido(Frete frete, Set<Integer> pedidos) {
        return pedidos.stream()
                .map(idPedido -> FretePedido.builder()
                        .frete_id(frete)
                        .pedido(idPedido)
                        .build()).collect(Collectors.toSet());

    }


}
