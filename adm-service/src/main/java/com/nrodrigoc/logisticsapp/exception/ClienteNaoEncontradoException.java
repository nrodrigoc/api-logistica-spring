package com.nrodrigoc.logisticsapp.exception;

public class ClienteNaoEncontradoException extends RuntimeException{
    public ClienteNaoEncontradoException(Integer id) {
        super("O cliente de id " + id + " não foi encontrado.");
    }
}
