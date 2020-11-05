package com.nrodrigoc.logisticsapp.service;

import com.nrodrigoc.logisticsapp.rest.dto.InformacoesProdutoPedidoDTO;
import com.nrodrigoc.logisticsapp.model.Produto;

public interface ProdutoService {

    public Produto salvar(Produto produto);

}
