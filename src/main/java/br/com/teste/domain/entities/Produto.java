package br.com.teste.domain.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "produto")
@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Enumerated(EnumType.STRING)
	@Column(name = "categoria_produto", length = 20, nullable = false)
	private CategoriaProduto categoriaProduto;
	
	@Column(name = "nome", nullable = false,unique = true)
	private String nome;
	
	@Column(name = "preco", nullable = false)
	private Double preco;
	
	@Column(name = "quantidade_em_estoque", nullable = false)
	private int quantidadeEmEstoque;	
	
	       
	        
}
