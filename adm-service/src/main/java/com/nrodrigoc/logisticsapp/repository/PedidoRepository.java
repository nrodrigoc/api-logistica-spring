package com.nrodrigoc.logisticsapp.repository;

import com.nrodrigoc.logisticsapp.model.Frete;
import com.nrodrigoc.logisticsapp.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    Set<Pedido> findByFrete(Frete frete);

}
