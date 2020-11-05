package com.nrodrigoc.logisticsapp.exception;

public class CaminhoneiroNaoEncontradoException extends RuntimeException{

    public CaminhoneiroNaoEncontradoException(Integer id) {
        super("O caminhoneiro de id " + id + " não foi encontrado.");
    }
}
