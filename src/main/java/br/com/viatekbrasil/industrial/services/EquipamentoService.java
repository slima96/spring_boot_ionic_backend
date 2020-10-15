package br.com.viatekbrasil.industrial.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viatekbrasil.industrial.domain.Equipamento;
import br.com.viatekbrasil.industrial.repositories.EquipamentoRepository;
import br.com.viatekbrasil.industrial.services.exceptions.ObjectNotFoundException;

@Service
public class EquipamentoService {
	
	@Autowired
	private EquipamentoRepository repo;

	public Equipamento find(Integer id) {
		Optional<Equipamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Equipamento.class.getName()));
	}
	

}