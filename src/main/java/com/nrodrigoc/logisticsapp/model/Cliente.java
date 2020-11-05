package com.nrodrigoc.logisticsapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "TB_CLIENTE")
@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "cliente_seq")
    private Integer id;

    @Column(length = 100)
    private String nome;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String login;

    @Column(length = 100)
    private String senha;

    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos;


}
