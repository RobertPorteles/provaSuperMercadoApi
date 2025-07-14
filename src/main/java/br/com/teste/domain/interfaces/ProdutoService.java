package br.com.teste.domain.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.teste.domain.dtos.ProdutoRequest;
import br.com.teste.domain.dtos.ProdutoResponse;

public interface ProdutoService {

	List<ProdutoResponse> listarProdutos();
	      
	ProdutoResponse criarProduto(ProdutoRequest produto);
	
	ProdutoResponse deletarProduto(UUID id);

	ProdutoResponse atualizarProduto(UUID id, ProdutoRequest produto);
	
	
}
