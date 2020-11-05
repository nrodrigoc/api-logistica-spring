package com.nrodrigoc.logisticsapp.repository;

import com.nrodrigoc.logisticsapp.model.ProdutoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Integer> {
}
