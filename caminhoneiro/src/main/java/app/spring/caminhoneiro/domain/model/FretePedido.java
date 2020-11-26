package app.spring.caminhoneiro.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter@Setter
public class FretePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "frete_pedido_seq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "frete_id")
    private Frete frete_id;

    private Integer pedido;


}
