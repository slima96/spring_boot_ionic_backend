package br.com.viatekbrasil.industrial.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.viatekbrasil.industrial.domain.Linha;
import br.com.viatekbrasil.industrial.dto.LinhaDTO;
import br.com.viatekbrasil.industrial.repositories.LinhaRepository;
import br.com.viatekbrasil.industrial.resources.exception.FieldMessage;

public class LinhaUpdateValidator implements ConstraintValidator<LinhaUpdate, LinhaDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private LinhaRepository repo;
	
	@Override
	public void initialize(LinhaUpdate ann) {
	}

	@Override
	public boolean isValid(LinhaDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		
		Linha aux = repo.findByNome(objDto.getNome());
		
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("nome", "Linha de produto já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
