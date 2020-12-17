package com.nrodrigoc.logisticsapp.service;

import com.nrodrigoc.logisticsapp.model.CaminhoneiroFrete;
import com.nrodrigoc.logisticsapp.rest.dto.FreteDTO;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesFreteDTO;

public interface CaminhoneiroFreteService {

    CaminhoneiroFrete salvar(Integer caminhoneiro_id);

    void entregaFrete(Integer idCaminhoneiro);

    InformacoesFreteDTO addFrete(FreteDTO frete );
}
