package com.nrodrigoc.logisticsapp.service;

import com.nrodrigoc.logisticsapp.rest.dto.FreteDTO;
import com.nrodrigoc.logisticsapp.model.Caminhoneiro;
import com.nrodrigoc.logisticsapp.model.Frete;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesFreteDTO;

import javax.validation.Valid;

public interface CaminhoneiroService {

    public Caminhoneiro salvar (Caminhoneiro caminhoneiro);

    public InformacoesFreteDTO addFrete(FreteDTO frete);

    public Caminhoneiro getById(Integer id);

    public InformacoesFreteDTO getFrete(Integer idFrete);
//    Optional

}
