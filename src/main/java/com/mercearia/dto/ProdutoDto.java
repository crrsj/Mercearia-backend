package com.mercearia.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercearia.entity.Produto;

import jakarta.validation.constraints.NotBlank;

public record ProdutoDto(
        Long id,
        @NotBlank(message = "Não pose ser vazio")
        String nome,
        @NotBlank(message = "Não pose ser vazio")
        String imagem,

        double preco,

        int estoque,
        @JsonIgnore
        int quantidade,

        double total) {
    public ProdutoDto(Produto cadastre) {
        this(
                cadastre.getId(),
                cadastre.getNome(),
                cadastre.getImagem(),
                cadastre.getPreco(),
                cadastre.getEstoque(),
                cadastre.getQuantidade(),
                cadastre.getTotal());
    }
}
