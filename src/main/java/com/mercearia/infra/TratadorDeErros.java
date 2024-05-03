package com.mercearia.infra;

import jakarta.persistence.EntityNotFoundException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MensagemDeErros>tratamentoDeErros(){
        var  erros = new MensagemDeErros(HttpStatus.NOT_FOUND,"Produto não encontrado !");
        return new ResponseEntity<>(erros,HttpStatus.NOT_FOUND);

    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MensagemDeErros>estoqueInsuficiente(){
        var  erros = new MensagemDeErros(HttpStatus.BAD_REQUEST,"Estoque insuficiente !");
        return new ResponseEntity<>(erros,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
          Map<String, String> errors = new HashMap<>();
          ex.getBindingResult().getAllErrors().forEach((error) -> {
           String fieldName = ((FieldError) error).getField();
           String errorMessage = error.getDefaultMessage();
           errors.put(fieldName, errorMessage);
       });
       return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);


   }
}
