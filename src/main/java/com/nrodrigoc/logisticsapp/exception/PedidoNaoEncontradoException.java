package com.nrodrigoc.logisticsapp.exception;

public class PedidoNaoEncontradoException extends RuntimeException{

    public PedidoNaoEncontradoException(Integer id) {
        super("O pedido de id " + id + " não foi encontrado.");
    }
}
