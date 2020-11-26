package app.spring.caminhoneiro.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Builder
@AllArgsConstructor
public class EmailDTO {

    private String to;

    private String subject;

    private String content;

}
