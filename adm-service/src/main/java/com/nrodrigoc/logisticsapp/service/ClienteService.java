package com.nrodrigoc.logisticsapp.service;

import com.nrodrigoc.logisticsapp.model.Cliente;
import com.nrodrigoc.logisticsapp.model.Pedido;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesClienteDTO;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesPedidoDTO;

public interface ClienteService {

    public Cliente salvar(Cliente cliente);

    public InformacoesClienteDTO getById(Integer id);

    public InformacoesPedidoDTO addPedido(Integer idCliente, Integer idPedido);
}
