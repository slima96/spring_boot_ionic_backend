package br.com.viatekbrasil.industrial;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.viatekbrasil.industrial.domain.Empresa;
import br.com.viatekbrasil.industrial.domain.Equipamento;
import br.com.viatekbrasil.industrial.domain.Linha;
import br.com.viatekbrasil.industrial.domain.Movimento;
import br.com.viatekbrasil.industrial.domain.MovimentoDetalhe;
import br.com.viatekbrasil.industrial.domain.Produto;
import br.com.viatekbrasil.industrial.domain.Turno;
import br.com.viatekbrasil.industrial.domain.enums.StatusMovimento;
import br.com.viatekbrasil.industrial.repositories.EmpresaRepository;
import br.com.viatekbrasil.industrial.repositories.EquipamentoRepository;
import br.com.viatekbrasil.industrial.repositories.LinhaRepository;
import br.com.viatekbrasil.industrial.repositories.MovimentoDetalheRepository;
import br.com.viatekbrasil.industrial.repositories.MovimentoRepository;
import br.com.viatekbrasil.industrial.repositories.ProdutoRepository;
import br.com.viatekbrasil.industrial.repositories.TurnoRepository;

@SpringBootApplication
public class IndustrialApplication implements CommandLineRunner {
	
	@Autowired
	private EmpresaRepository empresaRepository; 
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@Autowired
	private LinhaRepository linhaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private TurnoRepository turnoRepository;
	
	@Autowired
	private MovimentoRepository movimentoRepository;
	
	@Autowired
	private MovimentoDetalheRepository movimentoDetalheRepository;

	public static void main(String[] args) {
		SpringApplication.run(IndustrialApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Empresa emp1 = new Empresa(null, "Viatek");
		Empresa emp2 = new Empresa(null, "Viamed");
		Empresa emp3 = new Empresa(null, "Gplast");
		Empresa emp4 = new Empresa(null, "Polimero Matriz");
		Empresa emp5 = new Empresa(null, "Polimero Filial");
		Empresa emp6 = new Empresa(null, "JCS");
		Empresa emp7 = new Empresa(null, "Mondial");		
		
		Linha l1 = new Linha(null, "Viatek");
		Linha l2 = new Linha(null, "Mondial");
		Linha l3 = new Linha(null, "JCS");
		
		Turno t1 = new Turno(null, "T-1");
		Turno t2 = new Turno(null, "T-2");
		Turno t3 = new Turno(null, "T-3");
		
		Equipamento eq1 = new Equipamento(null, "mq-01", "maquina 1", "1800DT", emp1);
		Equipamento eq2 = new Equipamento(null, "mq-02", "maquina 2", "1300DT", emp2);
		Equipamento eq3 = new Equipamento(null, "mq-03", "maquina 3", "1500DT", emp3);
		
		emp1.getEquipamentos().addAll(Arrays.asList(eq1));
		emp2.getEquipamentos().addAll(Arrays.asList(eq2));
		emp3.getEquipamentos().addAll(Arrays.asList(eq3));
		
		Produto p1 = new Produto(null,"10001", "Balde para concreto", 30, 1, 3.00, l1, emp1);
		Produto p2 = new Produto(null,"10002", "Pivo mondial", 30, 1, 3.00, l2, emp2);
		Produto p3 = new Produto(null,"10003", "Acoplamento JCS", 30, 1, 3.00, l3, emp3);
		
		emp1.getProdutos().addAll(Arrays.asList(p1));
		emp2.getProdutos().addAll(Arrays.asList(p2));
		emp3.getProdutos().addAll(Arrays.asList(p3));
		
		l1.getProdutos().addAll(Arrays.asList(p1));
		l2.getProdutos().addAll(Arrays.asList(p2));
		l3.getProdutos().addAll(Arrays.asList(p3));
		
		Movimento m1 = new Movimento(null, new Date(), StatusMovimento.CONCLUIDO, emp1);
		Movimento m2 = new Movimento(null, new Date(), StatusMovimento.EMDIGITACAO, emp2);
		Movimento m3 = new Movimento(null, new Date(), StatusMovimento.CONCLUIDO, emp3);
		
		emp1.getMovimentos().addAll(Arrays.asList(m1));
		emp2.getMovimentos().addAll(Arrays.asList(m2));
		emp3.getMovimentos().addAll(Arrays.asList(m3));
		
		MovimentoDetalhe md1 = new MovimentoDetalhe(p1, m1, t1, eq1, 10, 2, 3.0);
		MovimentoDetalhe md2 = new MovimentoDetalhe(p2, m2, t2, eq2, 10, 2, 3.0);
		MovimentoDetalhe md3 = new MovimentoDetalhe(p3, m3, t3, eq3, 10, 2, 3.0);
		
		m1.getItens().addAll(Arrays.asList(md1));
		m2.getItens().addAll(Arrays.asList(md2));
		m3.getItens().addAll(Arrays.asList(md3));
		
		p1.getItens().addAll(Arrays.asList(md1));
		p2.getItens().addAll(Arrays.asList(md2));
		p3.getItens().addAll(Arrays.asList(md3));
		
		t1.getItens().addAll(Arrays.asList(md1));
		t2.getItens().addAll(Arrays.asList(md2));
		t3.getItens().addAll(Arrays.asList(md3));
		
		eq1.getItens().addAll(Arrays.asList(md1));
		eq2.getItens().addAll(Arrays.asList(md2));
		eq3.getItens().addAll(Arrays.asList(md3));
		
		
		empresaRepository.saveAll(Arrays.asList(emp1, emp2, emp3, emp4, emp5, emp6, emp7));
		equipamentoRepository.saveAll(Arrays.asList(eq1, eq2, eq3));
		turnoRepository.saveAll(Arrays.asList(t1, t2, t3));
		linhaRepository.saveAll(Arrays.asList(l1, l2, l3));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		movimentoRepository.saveAll(Arrays.asList(m1, m2, m3));
		movimentoDetalheRepository.saveAll(Arrays.asList(md1,md2,md3));
		
	}

}
