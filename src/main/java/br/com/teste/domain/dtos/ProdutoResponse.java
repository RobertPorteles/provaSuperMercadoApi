package br.com.teste.domain.dtos;

import java.util.UUID;

import br.com.teste.domain.entities.CategoriaProduto;
import lombok.Data;

@Data
public class ProdutoResponse {

	private UUID id;
	private CategoriaProduto categoriaProduto;
	private String nome;
	private Double preco;
	private int quantidadeEmEstoque;	
	
}
