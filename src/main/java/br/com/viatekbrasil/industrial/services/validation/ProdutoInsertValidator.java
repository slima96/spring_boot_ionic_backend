package br.com.viatekbrasil.industrial.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.viatekbrasil.industrial.domain.Produto;
import br.com.viatekbrasil.industrial.dto.ProdutoNewDTO;
import br.com.viatekbrasil.industrial.repositories.ProdutoRepository;
import br.com.viatekbrasil.industrial.resources.exception.FieldMessage;

public class ProdutoInsertValidator implements ConstraintValidator<ProdutoInsert, ProdutoNewDTO> {

	@Autowired
	private ProdutoRepository repo;
	
	@Override
	public void initialize(ProdutoInsert ann) {
	}

	@Override
	public boolean isValid(ProdutoNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		
		Produto aux1 = repo.findByCodigo(objDto.getCodigo());
		Produto aux2 = repo.findByDescricao(objDto.getDescricao());
		
		if (aux1 != null) {
			list.add(new FieldMessage("codigo", "Código do produto já existe"));
		}
		
		if (aux2 != null) {
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
