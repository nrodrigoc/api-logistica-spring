package com.nrodrigoc.logisticsapp.repository;

import com.nrodrigoc.logisticsapp.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
