package com.mercearia.dto;

import com.mercearia.entity.Produto;

public record VendaDto(
		
		Long id,
		String nome,
		String imagem,
		double preco,
	    int estoque,
	    int quantidade,
		double total
		              ) {

	public VendaDto(Produto venda) {
		this(
				venda.getId(),
				venda.getNome(),
				venda.getImagem(),
				venda.getPreco(),
				venda.getEstoque(),
				venda.getQuantidade(),
				venda.getTotal());
	}

}
