package com.nrodrigoc.logisticsapp.repository;

import com.nrodrigoc.logisticsapp.model.Caminhoneiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaminhoneiroRepository extends JpaRepository<Caminhoneiro, Integer> {
}
