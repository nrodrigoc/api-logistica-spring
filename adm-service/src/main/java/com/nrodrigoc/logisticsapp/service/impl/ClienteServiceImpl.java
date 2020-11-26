package com.nrodrigoc.logisticsapp.service.impl;

import com.nrodrigoc.logisticsapp.exception.ClienteNaoEncontradoException;
import com.nrodrigoc.logisticsapp.exception.PedidoNaoEncontradoException;
import com.nrodrigoc.logisticsapp.model.Cliente;
import com.nrodrigoc.logisticsapp.model.Pedido;
import com.nrodrigoc.logisticsapp.model.ProdutoPedido;
import com.nrodrigoc.logisticsapp.repository.ClienteRepository;
import com.nrodrigoc.logisticsapp.repository.PedidoRepository;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesClienteDTO;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesPedidoDTO;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesProdutoPedidoDTO;
import com.nrodrigoc.logisticsapp.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public InformacoesClienteDTO getById(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));

        return converterClienteParaDTO(cliente);
    }

    public InformacoesClienteDTO converterClienteParaDTO(Cliente cliente){
        return InformacoesClienteDTO
                .builder()
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .pedidos(converterPedidosParaDTO(cliente.getPedidos()))
                .build();
    }

    public Set<InformacoesPedidoDTO> converterPedidosParaDTO(Set<Pedido> pedidos){
        return pedidos.stream()
                .map(pedido -> InformacoesPedidoDTO
                .builder()
                .nome_cliente(pedido.getCliente().getNome())
                .produtos(converterProdutosParaDTO(pedido.getProdutos()))
                .statusPedido(pedido.getStatus().toString())
                .build()).collect(Collectors.toSet());

    }

    public Set<InformacoesProdutoPedidoDTO> converterProdutosParaDTO(Set<ProdutoPedido> produtoPedidos){
        return produtoPedidos
                .stream().map(produtoPedido -> InformacoesProdutoPedidoDTO
                .builder()
                .codigo(produtoPedido.getId())
                .nome_produto(produtoPedido.getProduto().getNome())
                .quantidade(produtoPedido.getQuantidade())
                .build()).collect(Collectors.toSet());
    }


    @Override
    public InformacoesPedidoDTO addPedido(Integer idCliente, Integer idPedido) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ClienteNaoEncontradoException(idCliente));

        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new PedidoNaoEncontradoException(idPedido));

        //Pega os pedidos que o cliente possui
        Set<Pedido> pedidosCliente = cliente.getPedidos();
        pedidosCliente.add(pedido);

        //Adiciona os novos pedidos
        cliente.setPedidos(pedidosCliente);

        //Atualiza o cliente
        clienteRepository.save(cliente);

        return converterPedidoParaDTO(pedido);
    }

    private InformacoesPedidoDTO converterPedidoParaDTO(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .nome_cliente(pedido.getCliente().getNome())
                .produtos(converterProdutosParaDTO(pedido.getProdutos()))
                .statusPedido(pedido.getStatus().toString())
                .build();
    }

}
