package com.nrodrigoc.logisticsapp.model;

import lombok.*;

import javax.persistence.*;

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
    private String nome;

}
