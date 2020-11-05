package com.nrodrigoc.logisticsapp.repository;

import com.nrodrigoc.logisticsapp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
