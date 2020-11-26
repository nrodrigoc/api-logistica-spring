package com.nrodrigoc.logisticsapp.rest.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformacoesFreteDTO {

    private Integer numero_frete;
    private Integer id_caminhoneiro;
    private Set<Integer> ids_pedidos;
    private String destino;
}
