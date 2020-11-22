package br.com.viatekbrasil.industrial.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.viatekbrasil.industrial.domain.Pessoa;
import br.com.viatekbrasil.industrial.dto.PessoaNewDTO;
import br.com.viatekbrasil.industrial.repositories.PessoaRepository;
import br.com.viatekbrasil.industrial.resources.exception.FieldMessage;

public class PessoaInsertValidator implements ConstraintValidator<PessoaInsert, PessoaNewDTO> {

	@Autowired
	private PessoaRepository repo;
	
	@Override
	public void initialize(PessoaInsert ann) {
	}

	@Override
	public boolean isValid(PessoaNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		
		Pessoa aux1 = repo.findByUsuario(objDto.getUsuario());
		
		if (aux1 != null) {
			list.add(new FieldMessage("usuario", "Usuário já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldMessage())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
