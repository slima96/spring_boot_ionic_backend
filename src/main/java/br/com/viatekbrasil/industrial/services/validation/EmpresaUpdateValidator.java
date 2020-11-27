package br.com.viatekbrasil.industrial.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.viatekbrasil.industrial.domain.Empresa;
import br.com.viatekbrasil.industrial.dto.EmpresaDTO;
import br.com.viatekbrasil.industrial.repositories.EmpresaRepository;
import br.com.viatekbrasil.industrial.resources.exception.FieldMessage;

public class EmpresaUpdateValidator implements ConstraintValidator<EmpresaUpdate, EmpresaDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private EmpresaRepository repo;
	
	@Override
	public void initialize(EmpresaUpdate ann) {
	}

	@Override
	public boolean isValid(EmpresaDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		
		Empresa aux = repo.findByNome(objDto.getNome());
		
		if (aux != null && !aux.getId().equals(uriId)) {
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
