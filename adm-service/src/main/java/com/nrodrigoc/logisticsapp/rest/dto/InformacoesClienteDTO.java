package com.nrodrigoc.logisticsapp.rest.dto;

import lombok.*;

import java.util.Set;

@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformacoesClienteDTO {

    private String nome;
    private String email;
    private Set<InformacoesPedidoDTO> pedidos;
}
