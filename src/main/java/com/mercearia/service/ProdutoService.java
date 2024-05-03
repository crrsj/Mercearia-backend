package com.mercearia.service;

import com.mercearia.dto.AtualizarDto;
import com.mercearia.dto.ProdutoDto;
import com.mercearia.dto.VendaDto;
import com.mercearia.entity.Produto;
import com.mercearia.repository.ProdutoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    public Produto cadastrarProduto(ProdutoDto produto){
       var cadastrar = new Produto(produto) ;
       return produtoRepository.save(cadastrar);
    }
    public List<Produto> buscarTodos(){
        return produtoRepository.findAll();
    }
    public Produto buscarId(Long id){
        return produtoRepository.getReferenceById(id);
    }
    @Transactional
    public Produto atualizarProduto(AtualizarDto atualizar){
        var atualize = produtoRepository.getReferenceById(atualizar.id());
        atualize.atualiza(atualizar);
        atualize.setEstoque(atualize.getEstoque() + atualize.getEstoque());
        return atualize;
    }
    public void excluir(Long id){
        produtoRepository.deleteById(id);
    }
    
    @Transactional
    public Produto processarVenda(VendaDto venda,Long id) { 
    	var produto = new Produto(venda);
    	produto.setId(id);
    	 produtoRepository.findById(id);      	   	                             
  
        if (produto.getEstoque() < produto.getQuantidade()) {
            throw new IllegalArgumentException();
        } 
        
        produto.setTotal(produto.getPreco() * produto.getQuantidade());
        produto.setEstoque(produto.getEstoque() - produto.getQuantidade());
       
        return produtoRepository.save(produto);
    }

 }