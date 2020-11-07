package com.nrodrigoc.logisticsapp.rest.controller;

import com.nrodrigoc.logisticsapp.email.MailServiceImpl;
import com.nrodrigoc.logisticsapp.model.Caminhoneiro;
import com.nrodrigoc.logisticsapp.rest.dto.FreteDTO;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesFreteDTO;
import com.nrodrigoc.logisticsapp.service.impl.CaminhoneiroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/caminhoneiro")
public class CaminhoneiroController {

    @Autowired
    private CaminhoneiroServiceImpl caminhoneiroService;

    @Autowired
    private MailServiceImpl mailService;

    @PostMapping
    public Caminhoneiro salvar(@RequestBody @Valid Caminhoneiro caminhoneiro) {
        mailService.sendSimpleMail(
                "nathanrodrigo@cc.ci.ufpb.br",
                "Caminhoneiro Registrado",
                "O caminhoneiro de nome " + caminhoneiro.getNome() + " foi registrado com sucesso!"
                );
        return caminhoneiroService.salvar(caminhoneiro);
    }

    @GetMapping
    public Caminhoneiro getById(@RequestParam Integer id){
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

}
