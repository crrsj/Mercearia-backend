package com.mercearia.dto;

import com.mercearia.entity.Produto;



public record AtualizarDto(
        long id,      
        int estoque,
        double preco) {
    public AtualizarDto(Produto atualiza) {
        this(
                atualiza.getId(),                             
                atualiza.getEstoque(),
                atualiza.getPreco());
    }
}
