package com.nrodrigoc.logisticsapp.model;

import com.nrodrigoc.logisticsapp.model.enums.StatusPedido;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "TB_PEDIDO")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pedido_seq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private Set<ProdutoPedido> produtos;

    @ManyToOne
    @JoinColumn(name = "frete_id")
    private Frete frete;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusPedido status;


}
