package br.com.teste.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.domain.dtos.ProdutoRequest;
import br.com.teste.domain.dtos.ProdutoResponse;
import br.com.teste.services.ProdutoServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/mercado/v1")
public class ProdutoController {
	@Autowired  ProdutoServiceImpl produtoServiceImpl;

	@GetMapping("/listar")
	public List<ProdutoResponse> listarProdutos() {
		return produtoServiceImpl.listarProdutos();
	}

	@PostMapping("/criar")
	public ProdutoResponse post(@RequestBody @Valid ProdutoRequest request) {
		return produtoServiceImpl.criarProduto(request);
		
	}

	@PutMapping("/{id}")
	public ProdutoResponse put(@PathVariable UUID id, @RequestBody @Valid ProdutoRequest request) {
	    return produtoServiceImpl.atualizarProduto(id, request);
	}


	@DeleteMapping("/deletar/{id}")
	public ProdutoResponse delete(@PathVariable UUID id) {
		return produtoServiceImpl.deletarProduto(id);
	}
}
