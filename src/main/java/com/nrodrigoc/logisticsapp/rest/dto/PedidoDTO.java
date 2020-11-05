package com.nrodrigoc.logisticsapp.rest.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO {

    /* JSON Format
        {
            "cliente": id,
            "produtos": [
                    {
                        "idProduto": id,
                        "quantidade": qntdd
                    }
            ]
        }
     */

    private Integer cliente;
    private Set<ProdutoPedidoDTO> produtos;
}
