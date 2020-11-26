package com.nrodrigoc.logisticsapp.rest.dto;

import com.nrodrigoc.logisticsapp.validation.NotEmptySet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter@Setter
@AllArgsConstructor
public class FreteDTO {

    @NotNull(message = "{caminhoneiro.id.obrigatorio}")
    private Integer idCaminhoneiro;

    @NotEmpty(message = "{frete.destino.obrigatorio}")
    private String destino;

    @NotEmptySet(message = "{frete.pedidos.obrigatorio}")
    private Set<Integer> pedidos;

}
