package com.nrodrigoc.logisticsapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "TB_FRETE")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Frete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "frete_seq")
    private Integer id;

}
