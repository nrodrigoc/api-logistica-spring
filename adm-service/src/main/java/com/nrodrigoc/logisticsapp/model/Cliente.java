package com.nrodrigoc.logisticsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nrodrigoc.logisticsapp.validation.NotEmptySet;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity(name = "TB_CLIENTE")
@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "cliente_seq")
    private Integer id;

    @Column(length = 100)
    @NotEmpty(message = "{cliente.nome.obrigatorio}")
    private String nome;

    @Column(length = 100)
    @NotEmpty(message = "{cliente.email.obrigatorio}")
    @Email(message = "email incorreto")
    private String email;

    @Column(length = 100)
    @NotEmpty(message = "{cliente.login.obrigatorio}")
    private String login;

    @Column(length = 100)
    @NotEmpty(message = "{cliente.senha.obrigatorio}")
    private String senha;

    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos;


}
