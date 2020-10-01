package br.com.viatekbrasil.industrial.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.viatekbrasil.industrial.domain.Empresa;

@RestController
@RequestMapping(value="/empresas")
public class EmpresaResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Empresa> listar() {
		
		Empresa emp1 = new Empresa(1,"Viatek");
		Empresa emp2 = new Empresa(2, "Viamed");
		
		List<Empresa> lista = new ArrayList<>();
		
		lista.addAll(Arrays.asList(emp1, emp2));
		
		return lista;
	}	
}
