package com.nrodrigoc.logisticsapp.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@Builder
public class CaminhoneiroDTO {

    private String nome;
    private Integer frete;
}
