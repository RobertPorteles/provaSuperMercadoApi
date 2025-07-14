package br.com.teste.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.domain.dtos.ProdutoRequest;
import br.com.teste.domain.dtos.ProdutoResponse;
import br.com.teste.domain.entities.Produto;
import br.com.teste.domain.interfaces.ProdutoService;
import br.com.teste.repositories.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired ProdutoRepository produtoRepository;
	
	private ProdutoResponse toResponse(Produto produto) {
		ProdutoResponse response = new ProdutoResponse();
		response.setId(produto.getId());
		response.setNome(produto.getNome());
		response.setPreco(produto.getPreco());
		response.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque());
		response.setCategoriaProduto(produto.getCategoriaProduto());
		return response;
	}

	@Override
	public List<ProdutoResponse> listarProdutos() {
		//pega no banco de dados todos os produtos
		List<Produto> produtos = produtoRepository.findAll();
		//cria uma lista de resposta para retornar os produtos
		 List<ProdutoResponse> resposta = new ArrayList<>();
		 
		 for (Produto produto : produtos) {
			    resposta.add(toResponse(produto));
			}

		 return resposta;
	}

	@Override
	public ProdutoResponse criarProduto(ProdutoRequest request) {
		 
	    if (produtoRepository.existsByNomeIgnoreCase(request.getNome())) {
	        throw new RuntimeException("Já existe um produto cadastrado com este nome.");
	    }
		
		Produto produto = new Produto();
		
		produto.setNome(request.getNome());
		produto.setPreco(request.getPreco());
		produto.setQuantidadeEmEstoque(request.getQuantidadeEmEstoque());
		produto.setCategoriaProduto(request.getCategoriaProduto());
		
		produto = produtoRepository.save(produto);
		
	    
	    return toResponse(produto);
		
	}

	@Override
	public ProdutoResponse atualizarProduto(UUID id,ProdutoRequest request) {
		
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		
		produto.setNome(request.getNome());
		
		produto.setPreco(request.getPreco());
		
		produto.setQuantidadeEmEstoque(request.getQuantidadeEmEstoque());
		
		produto.setCategoriaProduto(request.getCategoriaProduto());
		
		Produto atualizado = produtoRepository.save(produto);
		return toResponse(atualizado);

	}

	@Override
	public ProdutoResponse deletarProduto(UUID id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		
		if(produto.getQuantidadeEmEstoque() > 0) {
			
			throw new RuntimeException("Não é possível deletar um produto que possui estoque.");
		}
		produtoRepository.delete(produto);
		
		
		return toResponse(produto);
	}



}
