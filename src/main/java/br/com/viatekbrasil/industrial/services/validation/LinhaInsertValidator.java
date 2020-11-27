package br.com.viatekbrasil.industrial.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.viatekbrasil.industrial.domain.Linha;
import br.com.viatekbrasil.industrial.dto.LinhaNewDTO;
import br.com.viatekbrasil.industrial.repositories.LinhaRepository;
import br.com.viatekbrasil.industrial.resources.exception.FieldMessage;

public class LinhaInsertValidator implements ConstraintValidator<LinhaInsert, LinhaNewDTO> {

	@Autowired
	private LinhaRepository repo;
	
	@Override
	public void initialize(LinhaInsert ann) {
	}

	@Override
	public boolean isValid(LinhaNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		
		Linha aux = repo.findByNome(objDto.getNome());
		
		if (aux != null) {
			list.add(new FieldMessage("nome", "Linha de Produto já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
