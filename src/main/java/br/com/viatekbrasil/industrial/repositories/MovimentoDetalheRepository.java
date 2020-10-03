package br.com.viatekbrasil.industrial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.viatekbrasil.industrial.domain.MovimentoDetalhe;

@Repository
public interface MovimentoDetalheRepository extends JpaRepository<MovimentoDetalhe, Integer> {

}
