package com.mercearia.entity;

import com.mercearia.dto.AtualizarDto;
import com.mercearia.dto.ProdutoDto;
import com.mercearia.dto.VendaDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable {

   
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String imagem;
    private double preco;
    private int estoque;    
    private int quantidade;
    private double total;

    public Produto(ProdutoDto produto) {
        this.nome = produto.nome();
        this.imagem = produto.imagem();
        this.preco = produto.preco();
        this.estoque = produto.estoque();
        this.quantidade = produto.quantidade();
        this.total = produto.total();
    }

    public void atualiza(AtualizarDto atualizar) {        
        this.preco = atualizar.preco();
        this.estoque = atualizar.estoque();
              
    }
      

	public Produto(VendaDto venda) {
		this.id = venda.id();
		this.nome = venda.nome();
		this.imagem = venda.imagem();
	    this.quantidade = venda.quantidade();
	    this.preco = venda.preco();
	    this.total = venda.total();
	    this.estoque = venda.estoque();
	}
}
