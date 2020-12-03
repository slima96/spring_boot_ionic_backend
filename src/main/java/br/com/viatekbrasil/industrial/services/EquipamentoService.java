package br.com.viatekbrasil.industrial.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.viatekbrasil.industrial.domain.Empresa;
import br.com.viatekbrasil.industrial.domain.Equipamento;
import br.com.viatekbrasil.industrial.dto.EquipamentoDTO;
import br.com.viatekbrasil.industrial.dto.EquipamentoNewDTO;
import br.com.viatekbrasil.industrial.repositories.EquipamentoRepository;
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

	public Equipamento fromDTO(EquipamentoDTO objDTO) {
		return new Equipamento(objDTO.getId(), objDTO.getCodigo(), objDTO.getDescricao(), objDTO.getTonelagem(), null);
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

}