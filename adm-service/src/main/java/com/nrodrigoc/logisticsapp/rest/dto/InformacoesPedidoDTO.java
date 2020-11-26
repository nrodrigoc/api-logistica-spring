package com.nrodrigoc.logisticsapp.rest.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformacoesPedidoDTO {

    private String nome_cliente;
    private Set<InformacoesProdutoPedidoDTO> produtos;
    private String statusPedido;

}
