package com.nrodrigoc.logisticsapp.rest.controller;

import com.nrodrigoc.logisticsapp.model.CaminhoneiroFrete;
import com.nrodrigoc.logisticsapp.rest.dto.FreteDTO;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesFreteDTO;
import com.nrodrigoc.logisticsapp.service.CaminhoneiroFreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/geral/api/caminhoneiro")
public class CaminhoneiroController {

    @Autowired
    private CaminhoneiroFreteService caminhoneiroFreteService;

    @PostMapping("/{id}/")
    public CaminhoneiroFrete salvar(@PathVariable("id") Integer caminhoneiro_id) {
        return caminhoneiroFreteService.salvar(caminhoneiro_id);
    }

    @PostMapping("/frete")
    public InformacoesFreteDTO addFrete(@RequestBody @Valid FreteDTO frete){
        return caminhoneiroFreteService.addFrete(frete);
    }

}
