package br.com.viatekbrasil.industrial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.viatekbrasil.industrial.domain.Empresa;
import br.com.viatekbrasil.industrial.domain.Equipamento;
import br.com.viatekbrasil.industrial.dto.EquipamentoDTO;
import br.com.viatekbrasil.industrial.dto.EquipamentoNewDTO;
import br.com.viatekbrasil.industrial.repositories.EquipamentoRepository;
import br.com.viatekbrasil.industrial.services.exceptions.DataIntegrityException;
import br.com.viatekbrasil.industrial.services.exceptions.ObjectNotFoundException;

@Service
public class EquipamentoService {

	@Autowired
	private EquipamentoRepository repo;
	
	@Autowired
	private EmpresaService empresaService;

	public Equipamento find(Integer id) {
		Optional<Equipamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Equipamento.class.getName()));
	}
	
	@Transactional
	public Equipamento insert (Equipamento obj) {
		obj.setId(null);
	
		obj = repo.save(obj);
		return obj;
	}
	
	public Equipamento update(Equipamento obj) {
		Equipamento newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(obj);
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
	
	public List<Equipamento> findAll() {
		return repo.findAll();
	}

	public Equipamento fromDTO(EquipamentoDTO objDTO, Integer id) {
		Equipamento obj = find(id);
		
		Equipamento newObj = new Equipamento();
		
		newObj.setId(id);
		
		newObj.setCodigo(objDTO.getCodigo() != null  ? objDTO.getCodigo() : obj.getCodigo());
		newObj.setDescricao(objDTO.getDescricao() != null ? objDTO.getDescricao() : obj.getDescricao());
		newObj.setTonelagem(objDTO.getTonelagem() != null ? objDTO.getTonelagem() : obj.getTonelagem());
		newObj.setEmpresa(objDTO.getEmpresa() != null ? objDTO.getEmpresa() : obj.getEmpresa());	
		
		return newObj;
	}

	public Equipamento fromDTO(EquipamentoNewDTO objDTO) {
		Empresa empresa = empresaService.find(objDTO.getEmpresa().getId());
		
		Equipamento obj = new Equipamento(
				null, 
				objDTO.getCodigo(), 
				objDTO.getDescricao(), 
				objDTO.getTonelagem(), 
				empresa);
		
		return obj;
	}
	
	private void updateData(Equipamento newObj, Equipamento obj) {
		newObj.setCodigo(obj.getCodigo());
		newObj.setDescricao(obj.getDescricao());
		newObj.setTonelagem(obj.getTonelagem());
		newObj.setEmpresa(obj.getEmpresa());
	}

}