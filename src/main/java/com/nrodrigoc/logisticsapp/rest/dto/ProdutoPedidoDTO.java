package com.nrodrigoc.logisticsapp.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPedidoDTO {

    /* JSON format
       {
            "idProduto": id,
            "quantidade": qntdd
       }
     */

    private Integer idProduto;
    private Integer quantidade;

}
