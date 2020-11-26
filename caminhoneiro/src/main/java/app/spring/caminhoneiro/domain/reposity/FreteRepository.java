package app.spring.caminhoneiro.domain.reposity;

import app.spring.caminhoneiro.domain.model.Frete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreteRepository extends JpaRepository<Frete, Integer> {
}
