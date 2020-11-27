package br.com.viatekbrasil.industrial.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.viatekbrasil.industrial.domain.Empresa;
import br.com.viatekbrasil.industrial.dto.EmpresaNewDTO;
import br.com.viatekbrasil.industrial.repositories.EmpresaRepository;
import br.com.viatekbrasil.industrial.resources.exception.FieldMessage;

public class EmpresaInsertValidator implements ConstraintValidator<EmpresaInsert, EmpresaNewDTO> {

	@Autowired
	private EmpresaRepository repo;
	
	@Override
	public void initialize(EmpresaInsert ann) {
	}

	@Override
	public boolean isValid(EmpresaNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		
		Empresa aux = repo.findByNome(objDto.getNome());
		
		if (aux != null) {
			list.add(new FieldMessage("nome", "Empresa já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
