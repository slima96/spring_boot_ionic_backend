package br.com.viatekbrasil.industrial.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.viatekbrasil.industrial.domain.Empresa;
import br.com.viatekbrasil.industrial.services.EmpresaService;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresaResource {

	@Autowired
	private EmpresaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Empresa obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
