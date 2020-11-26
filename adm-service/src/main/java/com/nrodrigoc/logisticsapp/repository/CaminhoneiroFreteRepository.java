package com.nrodrigoc.logisticsapp.repository;

import com.nrodrigoc.logisticsapp.model.CaminhoneiroFrete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaminhoneiroFreteRepository extends JpaRepository<CaminhoneiroFrete, Integer> {

    CaminhoneiroFrete findOneByCaminhoneiroId(Integer id_caminhoneiro);

}
