package com.nrodrigoc.logisticsapp.rest.controller;

import com.nrodrigoc.logisticsapp.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleProdutoNaoEncontrado(ProdutoNaoEncontradoException e){
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(FreteNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleFreteNaoEncontrado(FreteNaoEncontradoException e) {
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(CaminhoneiroNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleCaminhoneiroNaoEncontrado(CaminhoneiroNaoEncontradoException e) {
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNaoEncontrado(PedidoNaoEncontradoException e) {
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleClienteNaoEncontrado(ClienteNaoEncontradoException e){
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException e){
        return new ApiErrors(e.getMessage());
    }
}
