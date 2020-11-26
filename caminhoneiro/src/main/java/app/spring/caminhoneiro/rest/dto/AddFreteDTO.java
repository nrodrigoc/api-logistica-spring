package app.spring.caminhoneiro.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter@Setter
@Builder
public class AddFreteDTO {

    private Integer caminhoneiro_id;

    private String destino;

    private Set<Integer> pedidos;

    private Integer frete_id;

}
