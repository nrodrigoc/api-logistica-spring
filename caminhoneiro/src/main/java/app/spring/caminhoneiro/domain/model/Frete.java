package app.spring.caminhoneiro.domain.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity(name = "tb_frete")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Frete {

    @Id
    private Integer id;

    @OneToOne
    private Caminhoneiro caminhoneiro;

    @OneToMany(mappedBy = "frete_id")
    private Set<FretePedido> pedidos;

    private String destino;

}
