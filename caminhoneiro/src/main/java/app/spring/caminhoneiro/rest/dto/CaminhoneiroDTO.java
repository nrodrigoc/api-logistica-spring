package app.spring.caminhoneiro.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
@AllArgsConstructor
@Builder
public class CaminhoneiroDTO {

    private String nome;
    private Integer frete;

}
