package br.com.viatekbrasil.industrial.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.viatekbrasil.industrial.domain.Equipamento;
import br.com.viatekbrasil.industrial.dto.EquipamentoNewDTO;
import br.com.viatekbrasil.industrial.repositories.EquipamentoRepository;
import br.com.viatekbrasil.industrial.resources.exception.FieldMessage;

public class EquipamentoInsertValidator implements ConstraintValidator<EquipamentoInsert, EquipamentoNewDTO> {

	@Autowired
	private EquipamentoRepository repo;
	
	@Override
	public void initialize(EquipamentoInsert ann) {
	}

	@Override
	public boolean isValid(EquipamentoNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		
		Equipamento aux = repo.findByCodigo(objDto.getCodigo());
		
		if (aux != null) {
			list.add(new FieldMessage("codigo", "Este código já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
