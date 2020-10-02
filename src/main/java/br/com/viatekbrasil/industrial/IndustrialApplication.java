package br.com.viatekbrasil.industrial;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.viatekbrasil.industrial.domain.Empresa;
import br.com.viatekbrasil.industrial.domain.Equipamento;
import br.com.viatekbrasil.industrial.repositories.EmpresaRepository;
import br.com.viatekbrasil.industrial.repositories.EquipamentoRepository;

@SpringBootApplication
public class IndustrialApplication implements CommandLineRunner {
	
	@Autowired
	private EmpresaRepository empresaRepository; 
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(IndustrialApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Empresa emp1 = new Empresa(null, "Viatek");
		Empresa emp2 = new Empresa(null, "Viamed");
		
		Equipamento eq1 = new Equipamento(null, "mq-01", "maquina 1", "1800DT", emp1);
		Equipamento eq2 = new Equipamento(null, "mq-02", "maquina 2", "1300DT", emp1);
		Equipamento eq3 = new Equipamento(null, "mq-03", "maquina 3", "1500DT", emp2);
		
		emp1.getEquipamentos().addAll(Arrays.asList(eq1, eq2));
		emp2.getEquipamentos().addAll(Arrays.asList(eq3));
		
		empresaRepository.saveAll(Arrays.asList(emp1, emp2));
		equipamentoRepository.saveAll(Arrays.asList(eq1, eq2, eq3));
		
		
	}

}
