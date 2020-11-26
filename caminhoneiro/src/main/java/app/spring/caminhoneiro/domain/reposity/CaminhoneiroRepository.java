package app.spring.caminhoneiro.domain.reposity;

import app.spring.caminhoneiro.domain.model.Caminhoneiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaminhoneiroRepository extends JpaRepository<Caminhoneiro, Integer> {

}
