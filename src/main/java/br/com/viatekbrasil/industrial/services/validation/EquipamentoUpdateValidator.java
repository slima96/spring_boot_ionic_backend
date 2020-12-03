package br.com.viatekbrasil.industrial.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.viatekbrasil.industrial.domain.Equipamento;
import br.com.viatekbrasil.industrial.dto.EquipamentoDTO;
import br.com.viatekbrasil.industrial.repositories.EquipamentoRepository;
import br.com.viatekbrasil.industrial.resources.exception.FieldMessage;

public class EquipamentoUpdateValidator implements ConstraintValidator<EquipamentoUpdate, EquipamentoDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private EquipamentoRepository repo;
	
	@Override
	public void initialize(EquipamentoUpdate ann) {
	}

	@Override
	public boolean isValid(EquipamentoDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		
		Equipamento aux = repo.findByCodigo(objDto.getCodigo());
		
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("codigo", "Código já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
