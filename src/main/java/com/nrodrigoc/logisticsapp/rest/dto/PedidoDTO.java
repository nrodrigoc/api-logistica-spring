package com.nrodrigoc.logisticsapp.rest.dto;

import com.nrodrigoc.logisticsapp.validation.NotEmptySet;
import lombok.*;

import javax.validation.constraints.NotNull;
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

    @NotNull(message = "{cliente.id.obrigatorio}")
    private Integer cliente;

    @NotEmptySet(message = "{produtos.lista.obrigatorio}")
    private Set<ProdutoPedidoDTO> produtos;
}
