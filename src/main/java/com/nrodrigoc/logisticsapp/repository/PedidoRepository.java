package com.nrodrigoc.logisticsapp.repository;

import com.nrodrigoc.logisticsapp.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
