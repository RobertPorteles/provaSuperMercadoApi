package br.com.teste.domain.dtos;

import br.com.teste.domain.entities.CategoriaProduto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoRequest {

	@NotNull(message = "A categoria do produto é obrigatória.")
	private CategoriaProduto categoriaProduto;
	
	@NotBlank(message = "O nome do produto é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
	private String nome;
	
	@DecimalMin(value = "0.99", inclusive = false, message = "O preço deve ser maior que 0.99.")
    @NotNull(message = "O preço é obrigatório.")
	private Double preco;
	
	@Min(value = 0, message = "A quantidade em estoque não pode ser negativa.")
	private int quantidadeEmEstoque;

		
	

}
