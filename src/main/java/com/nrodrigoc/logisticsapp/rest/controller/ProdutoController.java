package com.nrodrigoc.logisticsapp.rest.controller;

import com.nrodrigoc.logisticsapp.rest.dto.InformacoesProdutoPedidoDTO;
import com.nrodrigoc.logisticsapp.model.Produto;
import com.nrodrigoc.logisticsapp.service.impl.ProdutoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoServiceImpl produtoService;

    @PostMapping("/add")
    public Produto salvar(@RequestBody Produto produto){
        return produtoService.salvar(produto);
    }

    @GetMapping
    public Produto getById(@RequestParam Integer id){
        return produtoService.getById(id);
    }

}
