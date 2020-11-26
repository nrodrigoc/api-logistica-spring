package app.spring.caminhoneiro.domain.reposity;

import app.spring.caminhoneiro.domain.model.FretePedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FretePedidoRepository extends JpaRepository<FretePedido, Integer> {

    Set<FretePedido> findByPedido(Integer id);

}
