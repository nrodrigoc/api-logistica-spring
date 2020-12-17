package com.nrodrigoc.logisticsapp.rest.controller;

import com.nrodrigoc.logisticsapp.rest.dto.InformacoesProdutoPedidoDTO;
import com.nrodrigoc.logisticsapp.model.Produto;
import com.nrodrigoc.logisticsapp.service.impl.ProdutoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/v1/geral/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoServiceImpl produtoService;

    @PostMapping
    public Produto salvar(@RequestBody @Valid Produto produto){
        return produtoService.salvar(produto);
    }

    @GetMapping("/{id}")
//    @CrossOrigin("http://192.168.0.102:3000")
    public Produto getById(@PathVariable Integer id){
        return produtoService.getById(id);
    }

    @GetMapping
    public List<Produto> getAll(){
        return produtoService.getProdutos();
    }


}
