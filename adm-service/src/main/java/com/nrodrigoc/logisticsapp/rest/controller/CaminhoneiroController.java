package com.nrodrigoc.logisticsapp.rest.controller;

import com.nrodrigoc.logisticsapp.model.Caminhoneiro;
import com.nrodrigoc.logisticsapp.model.CaminhoneiroFrete;
import com.nrodrigoc.logisticsapp.rest.dto.CaminhoneiroDTO;
import com.nrodrigoc.logisticsapp.rest.dto.FreteDTO;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesFreteDTO;
import com.nrodrigoc.logisticsapp.rest.feign.CaminhoneiroClient;
import com.nrodrigoc.logisticsapp.service.CaminhoneiroService;
import com.nrodrigoc.logisticsapp.service.impl.CaminhoneiroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/caminhoneiro")
public class CaminhoneiroController {

    @Autowired
    private CaminhoneiroService caminhoneiroService;

    @Autowired
    private CaminhoneiroClient caminhoneiroClient;

    @PostMapping("/{id}/")
    public CaminhoneiroFrete salvar(@PathVariable("id") Integer caminhoneiro_id) {
        return caminhoneiroService.salvar(caminhoneiro_id);
    }

    @GetMapping("/{id}")
    public CaminhoneiroDTO getById(@PathVariable("id") Integer id){
        return caminhoneiroService.getById(id);
    }

    @PostMapping("/frete")
    public InformacoesFreteDTO addFrete(@RequestBody @Valid FreteDTO frete){
        return caminhoneiroService.addFrete(frete);
    }

    @GetMapping("/frete/{id}")
    public InformacoesFreteDTO getFrete(@PathVariable("id") Integer id) {
        return caminhoneiroService.getFrete(id);
    }

    @PostMapping("/frete/{id}")
    private void entregaFrete(@PathVariable("id") Integer id){
        caminhoneiroService.entregaFrete(id);
    }

}
