package com.nrodrigoc.logisticsapp.service.impl;

import com.nrodrigoc.logisticsapp.model.enums.StatusPedido;
import com.nrodrigoc.logisticsapp.exception.FreteNaoEncontradoException;
import com.nrodrigoc.logisticsapp.exception.RegraNegocioException;
import com.nrodrigoc.logisticsapp.rest.dto.FreteDTO;
import com.nrodrigoc.logisticsapp.exception.CaminhoneiroNaoEncontradoException;
import com.nrodrigoc.logisticsapp.exception.PedidoNaoEncontradoException;
import com.nrodrigoc.logisticsapp.model.Caminhoneiro;
import com.nrodrigoc.logisticsapp.model.Frete;
import com.nrodrigoc.logisticsapp.model.Pedido;
import com.nrodrigoc.logisticsapp.repository.CaminhoneiroRepository;
import com.nrodrigoc.logisticsapp.repository.FreteRepository;
import com.nrodrigoc.logisticsapp.repository.PedidoRepository;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesFreteDTO;
import com.nrodrigoc.logisticsapp.service.CaminhoneiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CaminhoneiroServiceImpl implements CaminhoneiroService {

    @Autowired
    private CaminhoneiroRepository caminhoneiroRep;

    @Autowired
    private FreteRepository freteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public Caminhoneiro salvar(Caminhoneiro caminhoneiro) {
//        Frete frete = freteRepository.findById(dto.getFrete())
//                .orElseThrow(() -> new FreteNaoEncontradoException(dto.getFrete()));

        return caminhoneiroRep.save(caminhoneiro);
    }

    @Override
    @Transactional
    public InformacoesFreteDTO addFrete(FreteDTO dto) {
        Frete frete = new Frete();

        //Encontra o caminhoneiro
        Caminhoneiro caminhoneiro = caminhoneiroRep.findById(dto.getIdCaminhoneiro())
                .orElseThrow(() -> new CaminhoneiroNaoEncontradoException(dto.getIdCaminhoneiro()));

        if (caminhoneiro.getFrete() != null) {
            throw new RegraNegocioException("Este caminhoneiro já possui um frete");
        }

        //Encontra os pedidos
        Set<Pedido> pedidos = dto.getPedidos()
                .stream().map(idPedido -> pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new PedidoNaoEncontradoException(idPedido)))
                .collect(Collectors.toSet());


        //Troca o status dos pedidos para "CAMINHO" e coloca o frete deles
        for (Pedido p : pedidos) {
            if(p.getFrete() != null) {
                throw new RegraNegocioException("O pedido de código " + p.getId() + " já está em um frete");
            }
            p.setFrete(frete);
            p.setStatus(StatusPedido.CAMINHO);
        }

        //Atualiza os pedidos
        pedidoRepository.saveAll(pedidos);

        frete.setCaminhoneiro(caminhoneiro);
        frete.setPedidos(pedidos);
        frete.setDestino(dto.getDestino());

        //Cria e salva o frete
        freteRepository.save(frete);

        //Adiciona o frete ao caminhoneiro
        caminhoneiro.setFrete(frete);

        //Atualiza o caminhoneiro
        caminhoneiroRep.save(caminhoneiro);

        return InformacoesFreteDTO.builder()
                .numero_frete(frete.getId())
                .nome_caminhoneiro(caminhoneiro.getNome())
                .destino(frete.getDestino())
                .ids_pedidos(dto.getPedidos())
                .build();
    }

    @Override
    public Caminhoneiro getById(Integer id) {
        return caminhoneiroRep.findById(id)
                .orElseThrow(() -> new CaminhoneiroNaoEncontradoException(id));
    }

    @Override
    public InformacoesFreteDTO getFrete(Integer idFrete) {
        Frete frete = freteRepository.findById(idFrete)
                .orElseThrow(() -> new FreteNaoEncontradoException(idFrete));

        return InformacoesFreteDTO
                .builder()
                .numero_frete(frete.getId())
                .ids_pedidos(frete.getPedidos().stream()
                .map(Pedido::getId).collect(Collectors.toSet()))
                .destino(frete.getDestino())
                .nome_caminhoneiro(frete.getCaminhoneiro().getNome())
                .build();
    }
}
