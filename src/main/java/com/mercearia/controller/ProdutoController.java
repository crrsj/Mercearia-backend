package com.mercearia.controller;

import com.mercearia.dto.AtualizarDto;
import com.mercearia.dto.ProdutoDto;
import com.mercearia.dto.VendaDto;
import com.mercearia.service.ProdutoService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.List;

@RestController
@RequestMapping("produto")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    @Operation(summary = "Rota responsável pelo cadastro de produtos") 
    @ApiResponse(responseCode = "201",description = "produto cadastrado com sucesso",content = {
   		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
    public ResponseEntity<ProdutoDto>cadastrarProduto(@RequestBody ProdutoDto produto){
        var cadastre = produtoService.cadastrarProduto(produto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("produto/{id}")
                .buildAndExpand(cadastre.getId()).toUri();       
        return ResponseEntity.created(uri).body(new ProdutoDto(cadastre));
    }
    @GetMapping
    @Operation(summary = "Rota responsável pela busca de todos os produtos")
	 @ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	   })           
    public ResponseEntity<List<ProdutoDto>> BuscarTodos(){
        var lista = produtoService.buscarTodos().stream().map(ProdutoDto::new).toList();
        return ResponseEntity.ok(lista);
    }
    @GetMapping("{id}")
    @Operation(summary = "Rota responsável por buscar um cliente pelo id")
	 @ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	  })         		
    public ResponseEntity<ProdutoDto>buscaId(@PathVariable Long id){
        var buscaId = produtoService.buscarId(id);
        return ResponseEntity.ok(new ProdutoDto(buscaId));
    }
    @DeleteMapping("{id}")
    @Operation(summary = "Rota responsável por deletar produto pelo id")
	 @ApiResponse(responseCode = "204",description = "produto deletado com sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	    })           
    public ResponseEntity<Void>excluir(@PathVariable Long id){        
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
     @PutMapping
     @Operation(summary = "Rota responsável por atualizar o produto")
	 @ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	   })           
     public ResponseEntity<AtualizarDto>editar(@RequestBody @Valid AtualizarDto atualizar){
        var atualiza = produtoService.atualizarProduto(atualizar);
        return ResponseEntity.ok().body(new AtualizarDto(atualiza));
    }
     @PutMapping("{id}")
     @Operation(summary = "Rota responsável por processar a venda,ou seja calcula o valor pela quantidade e atualiza o estoque")
	 @ApiResponse(responseCode = "200",description = "Venda processada com sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	   })           
     public ResponseEntity<VendaDto>processarVenda(@RequestBody VendaDto vendas,@PathVariable Long id){
    	 var venda = produtoService.processarVenda(vendas,id);
    	 return ResponseEntity.ok(new VendaDto(venda));
     }
}
