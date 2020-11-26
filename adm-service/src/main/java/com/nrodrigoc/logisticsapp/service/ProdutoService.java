package com.nrodrigoc.logisticsapp.service;

import com.nrodrigoc.logisticsapp.rest.dto.InformacoesProdutoPedidoDTO;
import com.nrodrigoc.logisticsapp.model.Produto;

import java.util.List;

public interface ProdutoService {

    public Produto salvar(Produto produto);

    public List<Produto> getProdutos();
}
