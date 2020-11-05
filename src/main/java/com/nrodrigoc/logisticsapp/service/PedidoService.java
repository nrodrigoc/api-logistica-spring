package com.nrodrigoc.logisticsapp.service;

import com.nrodrigoc.logisticsapp.model.Pedido;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesPedidoDTO;
import com.nrodrigoc.logisticsapp.rest.dto.PedidoDTO;

public interface PedidoService {

    public InformacoesPedidoDTO salvar(PedidoDTO pedido);

    public InformacoesPedidoDTO getById(Integer id);

}
