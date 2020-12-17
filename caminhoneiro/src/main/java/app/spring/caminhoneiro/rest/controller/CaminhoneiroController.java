package app.spring.caminhoneiro.rest.controller;

import app.spring.caminhoneiro.application.service.CaminhoneiroService;
import app.spring.caminhoneiro.domain.model.Caminhoneiro;
import app.spring.caminhoneiro.rest.dto.AddFreteDTO;
import app.spring.caminhoneiro.rest.dto.CaminhoneiroDTO;
import app.spring.caminhoneiro.rest.dto.InformacoesFreteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/caminhoneiro")
public class CaminhoneiroController {

    @Autowired
    private CaminhoneiroService caminhoneiroService;

    @PostMapping
    public Caminhoneiro salvar(@RequestBody @Valid Caminhoneiro caminhoneiro) {
        return caminhoneiroService.salvar(caminhoneiro);
    }

    @PostMapping("/frete")
    public InformacoesFreteDTO addFrete(@RequestBody AddFreteDTO frete){
        return caminhoneiroService.addFrete(frete);
    }

    @PutMapping("/frete/{id}")
    public void entregarFrete(@PathVariable("id") Integer caminhoneiro_id) {
        caminhoneiroService.entregaFrete(caminhoneiro_id);
    }

    @GetMapping("/{id}")
    public CaminhoneiroDTO getById(@PathVariable("id") Integer id){
        return caminhoneiroService.getById(id);
    }
}
