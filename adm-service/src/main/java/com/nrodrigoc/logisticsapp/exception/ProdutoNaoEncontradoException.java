package com.nrodrigoc.logisticsapp.exception;

public class ProdutoNaoEncontradoException extends RuntimeException{

    public ProdutoNaoEncontradoException(Integer id) {
        super("O produto de id " + id + " não foi encontrado!");
    }
}
