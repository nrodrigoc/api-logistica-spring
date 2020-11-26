package com.nrodrigoc.logisticsapp.rest.dto;

import com.nrodrigoc.logisticsapp.validation.NotEmptySet;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddFreteDTO {

    private Integer caminhoneiro_id;

    private String destino;

    private Set<Integer> pedidos;

    private Integer frete_id;

}
