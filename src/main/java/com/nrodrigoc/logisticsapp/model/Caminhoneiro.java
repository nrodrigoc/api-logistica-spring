package com.nrodrigoc.logisticsapp.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "TB_CAMINHONEIRO")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Caminhoneiro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "caminhoneiro_seq")
    private Integer id;

    @Column(length = 100)
    private String nome;

    @OneToOne
    @JoinColumn(name = "frete_id")
    private Frete frete;

}
