package com.nrodrigoc.logisticsapp.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter@Setter
@AllArgsConstructor
public class FreteDTO {

    private Integer idCaminhoneiro;
    private String destino;
    private Set<Integer> pedidos;

}
