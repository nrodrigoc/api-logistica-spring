package com.nrodrigoc.logisticsapp.repository;

import com.nrodrigoc.logisticsapp.model.Frete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreteRepository extends JpaRepository<Frete, Integer> {
}
