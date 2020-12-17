package com.nrodrigoc.logisticsapp.rest.controller;

import com.nrodrigoc.logisticsapp.rest.dto.AddFreteDTO;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesPedidoDTO;
import com.nrodrigoc.logisticsapp.rest.dto.PedidoDTO;
import com.nrodrigoc.logisticsapp.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/geral/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public InformacoesPedidoDTO salvar(@RequestBody @Valid PedidoDTO dto){
        return pedidoService.salvar(dto);
    }

    @GetMapping
    private InformacoesPedidoDTO getById(@RequestParam Integer id) {
        return pedidoService.getById(id);
    }

    @PostMapping("/atualizar/")
    private void AtualizarStatus(@RequestBody AddFreteDTO dto) {
        pedidoService.atualizarStatus(dto);
    }
}
