package app.spring.caminhoneiro.rest.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformacoesFreteDTO {

    private Integer numero_frete;
    private String nome_caminhoneiro;
    private Set<Integer> ids_pedidos;
    private String destino;
}
