package br.com.viatekbrasil.industrial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.viatekbrasil.industrial.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Transactional(readOnly = true)
	Produto findByCodigo(String codigo);
	
	@Transactional(readOnly = true)
	Produto findByDescricao(String descricao);
}
