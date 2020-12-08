package com.nrodrigoc.logisticsapp.rest.controller;

import com.nrodrigoc.logisticsapp.model.Cliente;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesClienteDTO;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesPedidoDTO;
import com.nrodrigoc.logisticsapp.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/geral/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Cliente salvar(@RequestBody @Valid Cliente cliente){
        return clienteService.salvar(cliente);
    }

    @GetMapping
    private InformacoesClienteDTO getById(@RequestParam Integer id) {
        return clienteService.getById(id);
    }

    @PostMapping("/pedido")
    private InformacoesPedidoDTO addPedido(@RequestParam Integer idCliente, @RequestParam Integer idPedido){
        return clienteService.addPedido(idCliente, idPedido);
    }


}
