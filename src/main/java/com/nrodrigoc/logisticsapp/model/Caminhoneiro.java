package com.nrodrigoc.logisticsapp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = "O campo nome é obrigatório.")
    private String nome;

    @OneToOne
    @JoinColumn(name = "frete_id")
    private Frete frete;

}
