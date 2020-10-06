package br.com.viatekbrasil.industrial.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.viatekbrasil.industrial.domain.Produto;
import br.com.viatekbrasil.industrial.dto.ProdutoDTO;
import br.com.viatekbrasil.industrial.repositories.ProdutoRepository;
import br.com.viatekbrasil.industrial.resources.exception.FieldMessage;

public class ProdutoUpdateValidator implements ConstraintValidator<ProdutoUpdate, ProdutoDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ProdutoRepository repo;
	
	@Override
	public void initialize(ProdutoUpdate ann) {
	}

	@Override
	public boolean isValid(ProdutoDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		
		
		Produto aux1 = repo.findByCodigo(objDto.getCodigo());
		Produto aux2 = repo.findByDescricao(objDto.getDescricao());
		
		if (aux1 != null && !aux1.getId().equals(uriId)) {
			list.add(new FieldMessage("codigo", "Código do produto já existe"));
		}
		
		if (aux2 != null && !aux1.getId().equals(uriId)) {
			list.add(new FieldMessage("descricao", "Existe um produto com a mesma descrição"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldMessage())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
