package br.com.viatekbrasil.industrial.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.viatekbrasil.industrial.domain.Movimento;
import br.com.viatekbrasil.industrial.services.MovimentoService;

@RestController
@RequestMapping(value = "/movimentos")
public class MovimentoResource {

	@Autowired
	private MovimentoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Movimento obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
