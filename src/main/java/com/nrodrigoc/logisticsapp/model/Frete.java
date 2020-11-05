package com.nrodrigoc.logisticsapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter@Setter
@Table(name = "TB_FRETE")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Frete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "frete_seq")
    private Integer id;

    @OneToOne
    private Caminhoneiro caminhoneiro;

    @OneToMany(mappedBy = "frete")
    private Set<Pedido> pedidos;

    @Column(length = 100)
    private String destino;

}
