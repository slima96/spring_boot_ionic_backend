package br.com.viatekbrasil.industrial.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viatekbrasil.industrial.domain.Turno;
import br.com.viatekbrasil.industrial.repositories.TurnoRepository;
import br.com.viatekbrasil.industrial.services.exceptions.ObjectNotFoundException;

@Service
public class TurnoService {
	
	@Autowired
	private TurnoRepository repo;

	public Turno find(Integer id) {
		Optional<Turno> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Turno.class.getName()));
	}
}