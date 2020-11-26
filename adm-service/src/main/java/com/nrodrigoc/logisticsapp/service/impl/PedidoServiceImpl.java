package com.nrodrigoc.logisticsapp.service.impl;

import com.nrodrigoc.logisticsapp.model.enums.StatusPedido;
import com.nrodrigoc.logisticsapp.exception.ClienteNaoEncontradoException;
import com.nrodrigoc.logisticsapp.exception.PedidoNaoEncontradoException;
import com.nrodrigoc.logisticsapp.exception.ProdutoNaoEncontradoException;
import com.nrodrigoc.logisticsapp.exception.RegraNegocioException;
import com.nrodrigoc.logisticsapp.model.Cliente;
import com.nrodrigoc.logisticsapp.model.Pedido;
import com.nrodrigoc.logisticsapp.model.Produto;
import com.nrodrigoc.logisticsapp.model.ProdutoPedido;
import com.nrodrigoc.logisticsapp.repository.*;
import com.nrodrigoc.logisticsapp.rest.dto.*;
import com.nrodrigoc.logisticsapp.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoPedidoRepository produtoPedidoRepository;

//    @Autowired
//    private CaminhoneiroFreteRepository caminhoneiroFreteRepository;

    @Override
    @Transactional
    public InformacoesPedidoDTO salvar(PedidoDTO dto) {
        Pedido pedido = new Pedido();

        Cliente cliente = clienteRepository.findById(dto.getCliente())
                .orElseThrow(() -> new ClienteNaoEncontradoException(dto.getCliente()));

//        Set<Pedido> pedidosCliente = cliente.getPedidos();
//        pedidosCliente.add(pedido);
//
//        cliente.setPedidos(pedidosCliente);

        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.POSTADO);

        // Adiciona os produtos ao pedido
        Set<ProdutoPedido> produtos = converterParaSetProdutoPedido(pedido, dto.getProdutos());
        produtoPedidoRepository.saveAll(produtos);

        pedido.setProdutos(produtos);

        pedidoRepository.save(pedido);

        //Atualiza o cliente
        clienteRepository.save(cliente);

        return InformacoesPedidoDTO.builder()
                .nome_cliente(pedido.getCliente().getNome())
                .statusPedido(pedido.getStatus().toString())
                .produtos(
                        produtos.stream()
                        .map(produtoPedido ->
                                InformacoesProdutoPedidoDTO.builder()
                                .codigo(produtoPedido.getProduto().getId())
                                .nome_produto(produtoPedido.getProduto().getNome())
                                .quantidade(produtoPedido.getQuantidade())
                                .build()
                        ).collect(Collectors.toSet())
                ).build();
    }

    @Override
    @Transactional
    public InformacoesPedidoDTO getById(Integer id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));

        return InformacoesPedidoDTO
                .builder()
                .produtos(pedido.getProdutos().stream()
                .map(produtoPedido ->
                        InformacoesProdutoPedidoDTO
                            .builder()
                            .codigo(produtoPedido.getProduto().getId())
                            .nome_produto(produtoPedido.getProduto().getNome())
                            .quantidade(produtoPedido.getQuantidade())
                            .build()).collect(Collectors.toSet()))
                .nome_cliente(pedido.getCliente().getNome())
                .statusPedido(pedido.getStatus().toString())
                .build();
    }

    @Transactional
    public Set<ProdutoPedido> converterParaSetProdutoPedido(Pedido pedido, Set<ProdutoPedidoDTO> produtos) {
        if(produtos.isEmpty()){
            throw new RegraNegocioException("Impossível postar um pedido sem produtos");
        }

        return produtos.stream()
                .map(produtoPedidoDTO -> {
                    Integer idProduto = produtoPedidoDTO.getIdProduto();
                    Produto produto = produtoRepository.findById(idProduto)
                            .orElseThrow(() -> new ProdutoNaoEncontradoException(idProduto));

                    return ProdutoPedido.builder()
                            .pedido(pedido)
                            .produto(produto)
                            .quantidade(produtoPedidoDTO.getQuantidade())
                            .build();
                    }
                ).collect(Collectors.toSet());

    }

    @Override
    @Transactional
    public void atualizarStatus(AddFreteDTO dto) {
//        Set<Pedido> pedidos = dto.getId_pedidos().stream()
//                .map(idPedido -> pedidoRepository.findById(idPedido)
//                        .orElseThrow(() -> new PedidoNaoEncontradoException(idPedido)))
//                .collect(Collectors.toSet());
//
//        pedidoRepository.saveAll(pedidos);
    }

}
