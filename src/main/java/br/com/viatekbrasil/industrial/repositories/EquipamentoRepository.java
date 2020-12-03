package br.com.viatekbrasil.industrial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.viatekbrasil.industrial.domain.Equipamento;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer> {

	Equipamento findByCodigo(String codigo);
}
