package br.com.viatekbrasil.industrial.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.viatekbrasil.industrial.domain.Turno;
import br.com.viatekbrasil.industrial.services.TurnoService;

@RestController
@RequestMapping(value = "/turnos")
public class TurnoResource {

	@Autowired
	private TurnoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Turno> find(@PathVariable Integer id) {
		Turno obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
