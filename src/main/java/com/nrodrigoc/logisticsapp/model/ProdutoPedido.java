package com.nrodrigoc.logisticsapp.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "tb_produto_pedido")
@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "produto_pedido_seq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column
    private Integer quantidade;

}
