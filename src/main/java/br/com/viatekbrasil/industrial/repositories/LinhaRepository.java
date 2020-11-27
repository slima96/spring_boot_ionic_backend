package br.com.viatekbrasil.industrial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.viatekbrasil.industrial.domain.Linha;

@Repository
public interface LinhaRepository extends JpaRepository<Linha, Integer> {

	@Transactional(readOnly = true)
	Linha findByNome(String nome);
}
