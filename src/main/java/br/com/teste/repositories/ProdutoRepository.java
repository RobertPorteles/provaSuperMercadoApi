package br.com.teste.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.domain.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

	boolean existsByNomeIgnoreCase(String nome);

	boolean existsByQuantidadeEmEstoqueGreaterThan(int quantidade);
}
