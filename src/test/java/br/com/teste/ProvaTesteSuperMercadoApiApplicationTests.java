package br.com.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.teste.domain.dtos.ProdutoRequest;
import br.com.teste.domain.dtos.ProdutoResponse;
import br.com.teste.domain.entities.CategoriaProduto;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProvaTesteSuperMercadoApiApplicationTests {
    
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
	
	@Test
	@Order(1)
	@DisplayName("Deve permitir criar produto em estoque")
	void DeveCriarProdutoComSucesso() throws Exception {
	    // Arrange
	    ProdutoRequest request = new ProdutoRequest();
	    request.setNome("Guaraná");
	    request.setPreco(7.50);
	    request.setQuantidadeEmEstoque(100);
	    request.setCategoriaProduto(CategoriaProduto.BEBIDAS);

	    // Act
	    String jsonResponse = mockMvc.perform(post("/api/mercado/v1/criar")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(request)))
	            .andExpect(status().isOk())
	            .andReturn().getResponse().getContentAsString();

	    // Assert
	    ProdutoResponse response = objectMapper.readValue(jsonResponse, ProdutoResponse.class);
	    assertEquals("Guaraná", response.getNome());
	    assertEquals(7.50, response.getPreco());
	    assertEquals(100, response.getQuantidadeEmEstoque());
	    assertEquals(CategoriaProduto.BEBIDAS, response.getCategoriaProduto());
	}


	
	@Test
	@Order(2)
	@DisplayName("Não deve permitir criação de produto com nome duplicado")
	void naoDeveCriarProdutoComMesmoNome() throws Exception {
	    ProdutoRequest request = new ProdutoRequest();
	    request.setNome("Guaraná"); // mesmo nome do teste anterior
	    request.setPreco(7.50);
	    request.setQuantidadeEmEstoque(100);
	    request.setCategoriaProduto(CategoriaProduto.BEBIDAS);

	    mockMvc.perform(post("/api/mercado/v1/criar")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(request)))
	            .andExpect(status().isBadRequest()); // <-- aqui é o correto!
	}

	@Test
	@Order(3)
	@DisplayName("Se ainda tiver produto em estoque, não deve permitir exclusão")
	void naoPodeExcluirProdutoEmEstoque() throws Exception {
	    // Arrange: Criamos um ProdutoRequest com estoque maior que zero
	    ProdutoRequest request = new ProdutoRequest();
	    request.setNome("Batata Frita");
	    request.setPreco(10.0);
	    request.setQuantidadeEmEstoque(5); 
	    request.setCategoriaProduto(CategoriaProduto.ALIMENTOS);

	    // Act: Enviamos uma requisição POST para criar o produto
	    String jsonResponse = mockMvc.perform(post("/api/mercado/v1/criar")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(request)))
	            .andExpect(status().isOk()) // Esperamos que a criação seja bem-sucedida (200)
	            .andReturn().getResponse().getContentAsString();

	    // Convertemos a resposta em um objeto ProdutoResponse para obter o ID do produto criado
	    ProdutoResponse response = objectMapper.readValue(jsonResponse, ProdutoResponse.class);
	    UUID id = response.getId();

	    // Act: Tentamos excluir o produto recém-criado que ainda tem estoque
	    mockMvc.perform(delete("/api/mercado/v1/deletar/" + id))
	            .andExpect(status().isBadRequest()) // Esperamos um erro 400 (Bad Request) porque não é permitido excluir produtos com estoque
	            .andReturn();
	}
	@Test
	@Order(4)
	@DisplayName("Deve permitir exclusão de produto pois está sem estoque")
	void PodeExcluirProdutoSemEstoque() throws Exception {
	    ProdutoRequest request = new ProdutoRequest();
	    request.setNome("Tomate");
	    request.setPreco(10.0);
	    request.setQuantidadeEmEstoque(0); 
	    request.setCategoriaProduto(CategoriaProduto.ALIMENTOS);

	    String jsonResponse = mockMvc.perform(post("/api/mercado/v1/criar")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(request)))
	            .andExpect(status().isOk())
	            .andReturn().getResponse().getContentAsString();

	    ProdutoResponse response = objectMapper.readValue(jsonResponse, ProdutoResponse.class);
	    UUID id = response.getId();

	    mockMvc.perform(delete("/api/mercado/v1/deletar/" + id))
	            .andExpect(status().isOk()) // Verifica apenas o status 204
	            .andReturn();
	}

	
}
