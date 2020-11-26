package app.spring.caminhoneiro.application.service;

import app.spring.caminhoneiro.domain.model.Caminhoneiro;
import app.spring.caminhoneiro.rest.dto.AddFreteDTO;
import app.spring.caminhoneiro.rest.dto.CaminhoneiroDTO;
import app.spring.caminhoneiro.rest.dto.InformacoesFreteDTO;

public interface CaminhoneiroService {

    public Caminhoneiro salvar(Caminhoneiro caminhoneiro );

    public InformacoesFreteDTO addFrete(AddFreteDTO frete);

    public CaminhoneiroDTO getById(Integer id);

    public InformacoesFreteDTO getFrete(Integer idFrete);
    //    Optional
    public void entregaFrete(Integer idCaminhoneiro);

}
