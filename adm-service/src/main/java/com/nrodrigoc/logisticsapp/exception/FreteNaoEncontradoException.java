package com.nrodrigoc.logisticsapp.exception;

public class FreteNaoEncontradoException extends RuntimeException{

    public FreteNaoEncontradoException(Integer id) {

        super("Frete de id " + id + " não encontrado.");
    }
}
