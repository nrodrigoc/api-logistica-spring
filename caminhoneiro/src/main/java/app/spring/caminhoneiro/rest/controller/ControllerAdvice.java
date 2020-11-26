package app.spring.caminhoneiro.rest.controller;

import feign.FeignException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(FeignException.class)
    public String handleFeignExcp(FeignException e, HttpServletResponse response){
        response.setStatus(e.status());
        return e.getMessage();
    }
}
