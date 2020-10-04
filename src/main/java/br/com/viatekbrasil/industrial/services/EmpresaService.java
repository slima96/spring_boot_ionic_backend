package br.com.viatekbrasil.industrial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.viatekbrasil.industrial.domain.Empresa;
import br.com.viatekbrasil.industrial.dto.EmpresaDTO;
import br.com.viatekbrasil.industrial.repositories.EmpresaRepository;
import br.com.viatekbrasil.industrial.services.exceptions.DataIntegrityException;
import br.com.viatekbrasil.industrial.services.exceptions.ObjectNotFoundException;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository repo;

	public Empresa find(Integer id) {
		Optional<Empresa> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Empresa.class.getName()));
	}
	
	public Empresa insert (Empresa obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Empresa update(Empresa obj) {
		Empresa newObj = find(obj.getId());
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

	public List<Empresa> findAll() {
		return repo.findAll();
	}
	
	public Page<Empresa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Empresa fromDTO(EmpresaDTO objDTO) {
		return new Empresa(objDTO.getId(), objDTO.getNome());
	}
	
	private void updateData(Empresa newObj, Empresa obj) {
		newObj.setNome(obj.getNome());
		
	}
}