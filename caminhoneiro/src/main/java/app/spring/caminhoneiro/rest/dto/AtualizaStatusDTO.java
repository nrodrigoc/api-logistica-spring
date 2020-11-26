package app.spring.caminhoneiro.rest.dto;

import lombok.*;

import java.util.Set;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtualizaStatusDTO {

    private Set<Integer> id_pedidos;

    private Integer id_frete;

    private String novoStatus;

}
