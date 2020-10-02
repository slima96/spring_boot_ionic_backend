package br.com.viatekbrasil.industrial;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.viatekbrasil.industrial.domain.Empresa;
import br.com.viatekbrasil.industrial.repositories.EmpresaRepository;

@SpringBootApplication
public class IndustrialApplication implements CommandLineRunner {
	
	@Autowired
	private EmpresaRepository empresaRepository; 

	public static void main(String[] args) {
		SpringApplication.run(IndustrialApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Empresa emp1 = new Empresa(null, "Viatek");
		Empresa emp2 = new Empresa(null, "Viamed");
		
		empresaRepository.saveAll(Arrays.asList(emp1, emp2));		
	}

}
