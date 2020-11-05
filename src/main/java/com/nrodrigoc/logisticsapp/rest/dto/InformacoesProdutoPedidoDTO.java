package com.nrodrigoc.logisticsapp.rest.dto;

import lombok.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesProdutoPedidoDTO {

    private Integer codigo;
    private String nome_produto;
    private Integer quantidade;

}
