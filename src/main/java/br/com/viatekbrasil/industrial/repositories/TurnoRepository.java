package br.com.viatekbrasil.industrial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.viatekbrasil.industrial.domain.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {

}
