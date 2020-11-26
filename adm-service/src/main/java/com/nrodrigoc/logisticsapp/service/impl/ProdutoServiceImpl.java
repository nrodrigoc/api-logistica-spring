package com.nrodrigoc.logisticsapp.service.impl;

import com.nrodrigoc.logisticsapp.rest.dto.InformacoesProdutoPedidoDTO;
import com.nrodrigoc.logisticsapp.exception.ProdutoNaoEncontradoException;
import com.nrodrigoc.logisticsapp.model.Produto;
import com.nrodrigoc.logisticsapp.repository.ProdutoRepository;
import com.nrodrigoc.logisticsapp.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getById(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

}
