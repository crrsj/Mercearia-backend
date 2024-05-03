package com.mercearia.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
public class MensagemDeErros {
    private HttpStatus status;
    private String mensagem;


}
