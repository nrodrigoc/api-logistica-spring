package com.nrodrigoc.logisticsapp.service;

import com.nrodrigoc.logisticsapp.model.Caminhoneiro;
import com.nrodrigoc.logisticsapp.model.CaminhoneiroFrete;
import com.nrodrigoc.logisticsapp.rest.dto.AddFreteDTO;
import com.nrodrigoc.logisticsapp.rest.dto.CaminhoneiroDTO;
import com.nrodrigoc.logisticsapp.rest.dto.FreteDTO;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesFreteDTO;

public interface CaminhoneiroService {

    public CaminhoneiroFrete salvar(Integer caminhoneiro_id);

    public InformacoesFreteDTO addFrete( FreteDTO frete );

    public CaminhoneiroDTO getById(Integer id);

    public InformacoesFreteDTO getFrete(Integer idFrete);
//    Optional
    void entregaFrete(Integer idCaminhoneiro);

    void publishAddFreteMessage(AddFreteDTO dto);
}
