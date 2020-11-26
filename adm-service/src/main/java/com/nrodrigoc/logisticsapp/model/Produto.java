package com.nrodrigoc.logisticsapp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "TB_PRODUTO")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "produto_seq")
    private Integer id;

    @Column
    @NotEmpty(message = "{produto.nome.obrigatorio}")
    private String nome;

}
