package com.nrodrigoc.logisticsapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrodrigoc.logisticsapp.email.service.MailService;
import com.nrodrigoc.logisticsapp.exception.FreteNaoEncontradoException;
import com.nrodrigoc.logisticsapp.exception.PedidoNaoEncontradoException;
import com.nrodrigoc.logisticsapp.exception.RegraNegocioException;
import com.nrodrigoc.logisticsapp.model.CaminhoneiroFrete;
import com.nrodrigoc.logisticsapp.model.Cliente;
import com.nrodrigoc.logisticsapp.model.Frete;
import com.nrodrigoc.logisticsapp.model.Pedido;
import com.nrodrigoc.logisticsapp.model.enums.StatusPedido;
import com.nrodrigoc.logisticsapp.repository.CaminhoneiroFreteRepository;
import com.nrodrigoc.logisticsapp.repository.FreteRepository;
import com.nrodrigoc.logisticsapp.repository.PedidoRepository;
import com.nrodrigoc.logisticsapp.rest.dto.AddFreteDTO;
import com.nrodrigoc.logisticsapp.rest.dto.EmailDTO;
import com.nrodrigoc.logisticsapp.rest.dto.FreteDTO;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesFreteDTO;
import com.nrodrigoc.logisticsapp.service.CaminhoneiroFreteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CaminhoneiroFreteServiceImpl implements CaminhoneiroFreteService {

    @Autowired
    private CaminhoneiroFreteRepository caminhoneiroFreteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private FreteRepository freteRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final Logger logger = LoggerFactory.getLogger(CaminhoneiroFreteService.class);

    @Override
    @Transactional
    public CaminhoneiroFrete salvar(Integer caminhoneiro_id) {
        return caminhoneiroFreteRepository.save(CaminhoneiroFrete.builder()
                .caminhoneiroId(caminhoneiro_id)
                .freteId(-1)
                .build());
    }

    @Override
    @Transactional
    public void entregaFrete(Integer idFrete) {
        Frete frete = freteRepository.findById(idFrete)
                .orElseThrow(() -> new FreteNaoEncontradoException(idFrete));
//
//        Caminhoneiro caminhoneiro = frete.getCaminhoneiro();
//
//
        CaminhoneiroFrete caminhoneiroFrete = caminhoneiroFreteRepository
                .findOneByFreteId(idFrete);

        // Muda o status dos pedidos do frete para "entregue"
        Set<Pedido> pedidos = pedidoRepository.findByFrete(frete).stream()
                .map(pedido -> {
                    Cliente cliente = pedido.getCliente();
                    pedido.setStatus(StatusPedido.ENTREGUE);

                    //Envia email pro cliente avisando que o pedido foi entregue
                    mailService.publishMessage(
                            EmailDTO.builder()
                            .to(cliente.getEmail())
                            .subject("Pedido de código " + pedido.getId())
                            .content("Sr(a). " + cliente.getNome() + ", o seu pedido de código "
                                    + pedido.getId() + " foi entregue com sucesso!")
                            .build()
                    );
                    return pedidoRepository.save(pedido);
                }).collect(Collectors.toSet());
        pedidoRepository.saveAll(pedidos);

        // Libera o caminhoneiro para receber outro frete
        caminhoneiroFrete.setFreteId(-1);
        caminhoneiroFreteRepository.save(caminhoneiroFrete);

//        //Retira o frete do caminhoneiro para que ele possa receber outro
//        caminhoneiro.setFrete(null);
//        caminhoneiroRepository.save(caminhoneiro);
//
    }

    @Override
    @Transactional
    public InformacoesFreteDTO addFrete(FreteDTO dto) {
        Frete frete = new Frete();

        if (dto.getPedidos().isEmpty()){
            throw new RegraNegocioException("A lista de pedidos não pode estar vazia");
        }

        //Encontra os pedidos
        Set<Pedido> pedidos = dto.getPedidos()
                .stream().map(idPedido -> pedidoRepository.findById(idPedido)
                        .orElseThrow(() -> new PedidoNaoEncontradoException(idPedido)))
                .collect(Collectors.toSet());

        // Busca a entidade na tabela caminhoneiro_frete através do id do caminhoneiro
        CaminhoneiroFrete caminhoneiroFrete = caminhoneiroFreteRepository
                .findOneByCaminhoneiroId(dto.getIdCaminhoneiro());

        if(caminhoneiroFrete.getFreteId() != -1) {
            throw new RegraNegocioException("Este caminhoneiro já possui um frete");
        }

        //Troca o status dos pedidos para "CAMINHO" e coloca o frete deles
        for (Pedido p : pedidos) {
            if(!p.getStatus().equals(StatusPedido.POSTADO)) {
                throw new RegraNegocioException("O pedido de código " + p.getId() + " já está em um frete");
            }
            p.setFrete(frete);
            p.setStatus(StatusPedido.CAMINHO);
        }

        //Cria e salva o frete
        freteRepository.save(frete);

        caminhoneiroFrete.setFreteId(frete.getId());

        caminhoneiroFreteRepository.save(caminhoneiroFrete);


        //Atualiza os pedidos
        pedidoRepository.saveAll(pedidos);

        //Adiciona o frete ao caminhoneiro
//        caminhoneiro.setFrete(frete);

        //Atualiza o caminhoneiro
//        caminhoneiroRepository.save(caminhoneiro);

        publishAddFreteMessage(AddFreteDTO.builder()
                .caminhoneiro_id(dto.getIdCaminhoneiro())
                .destino(dto.getDestino())
                .frete_id(frete.getId())
                .pedidos(dto.getPedidos())
                .build());

        return InformacoesFreteDTO.builder()
                .numero_frete(frete.getId())
                .id_caminhoneiro(dto.getIdCaminhoneiro())
                .destino(dto.getDestino())
                .ids_pedidos(dto.getPedidos())
                .build();
    }


    public void publishAddFreteMessage(AddFreteDTO dto) {
        String serializedObj = "";

        try {
            serializedObj = objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        logger.info("Sending message to RabbitMQ");
        rabbitTemplate.convertAndSend("", "frete-queue", serializedObj);
        logger.info("Message published!");
    }
}
