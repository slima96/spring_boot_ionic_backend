package br.com.viatekbrasil.industrial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.viatekbrasil.industrial.domain.Linha;
import br.com.viatekbrasil.industrial.dto.LinhaDTO;
import br.com.viatekbrasil.industrial.dto.LinhaNewDTO;
import br.com.viatekbrasil.industrial.repositories.LinhaRepository;
import br.com.viatekbrasil.industrial.services.exceptions.DataIntegrityException;
import br.com.viatekbrasil.industrial.services.exceptions.ObjectNotFoundException;

@Service
public class LinhaService {
	
	@Autowired
	private LinhaRepository repo;

	public Linha find(Integer id) {
		Optional<Linha> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Linha.class.getName()));
	}
	
	public Linha insert (Linha obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Linha update(Linha obj) {
		Linha newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível remover por que há entidades relacionadas");
		}
				
	}
	
	public List<Linha> findAll() {
		return repo.findAll();
	}
	
	public Linha fromDTO(LinhaDTO objDTO) {
		return new Linha(objDTO.getId(), objDTO.getNome());
	}
	
	public Linha fromDTO(LinhaNewDTO objDTO) {
		Linha lin = new Linha(null, objDTO.getNome());
		return lin;
	}
	
	private void updateData(Linha newObj, Linha obj) {
		newObj.setNome(obj.getNome());
		
	}
}