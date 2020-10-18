package br.com.viatekbrasil.industrial.services;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.viatekbrasil.industrial.domain.Empresa;
import br.com.viatekbrasil.industrial.domain.Equipamento;
import br.com.viatekbrasil.industrial.domain.Linha;
import br.com.viatekbrasil.industrial.domain.Movimento;
import br.com.viatekbrasil.industrial.domain.MovimentoDetalhe;
import br.com.viatekbrasil.industrial.domain.Pessoa;
import br.com.viatekbrasil.industrial.domain.Produto;
import br.com.viatekbrasil.industrial.domain.Turno;
import br.com.viatekbrasil.industrial.domain.enums.Perfil;
import br.com.viatekbrasil.industrial.domain.enums.StatusMovimento;
import br.com.viatekbrasil.industrial.repositories.EmpresaRepository;
import br.com.viatekbrasil.industrial.repositories.EquipamentoRepository;
import br.com.viatekbrasil.industrial.repositories.LinhaRepository;
import br.com.viatekbrasil.industrial.repositories.MovimentoDetalheRepository;
import br.com.viatekbrasil.industrial.repositories.MovimentoRepository;
import br.com.viatekbrasil.industrial.repositories.PessoaRepository;
import br.com.viatekbrasil.industrial.repositories.ProdutoRepository;
import br.com.viatekbrasil.industrial.repositories.TurnoRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
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
	
	@Autowired
	private PessoaRepository pessoaRepository;

	
	public void instatiateTestDatabase() {
		Empresa viatek = new Empresa(null, "Viatek");
		Empresa viamed = new Empresa(null, "Viamed");
		Empresa gplast = new Empresa(null, "G-plast");
		
		Pessoa pessoa = new Pessoa(null, "Alexsandro Leão Lima", "sandro", pe.encode("Sl879900"));
		pessoa.addPerfil(Perfil.ADMIN);
		
		Pessoa usuario = new Pessoa(null, "Alexsandro Lima", "usuario", pe.encode("Sl879900"));
		
		Linha linha1 = new Linha(null, "Viatek");
		Linha linha2 = new Linha(null, "Mondial");
		Linha linha3 = new Linha(null, "JCS");
		
		Turno turno1 = new Turno(null, "T-1");
		Turno turno2 = new Turno(null, "T-2");
		Turno turno3 = new Turno(null, "T-3");
		
		Equipamento equipamento = new Equipamento(null, "MQ-01", "Maquina 1 da Viatek", "1800-DT", viatek);
		
		viatek.getEquipamentos().addAll(Arrays.asList(equipamento));
		
		Produto balde =  new Produto(null, "10001", "Balde para concreto", 35, 1, 3.0, linha1, viatek);
		Produto pivoMondial = new Produto(null, "1020-01", "Pivo mondial", 20, 4, 1.5, linha2, viamed);
		Produto acoplamento =  new Produto(null, "102030", "Acoplamento JCS", 20, 6, 1.5, linha3, gplast);
		
		viatek.getProdutos().addAll(Arrays.asList(balde));
		viamed.getProdutos().addAll(Arrays.asList(pivoMondial));
		gplast.getProdutos().addAll(Arrays.asList(acoplamento));
		
		linha1.getProdutos().addAll(Arrays.asList(balde));
		linha2.getProdutos().addAll(Arrays.asList(pivoMondial));
		linha3.getProdutos().addAll(Arrays.asList(acoplamento));
		
		Movimento movimento = new Movimento(null, new Date(), StatusMovimento.EMDIGITACAO, viatek, pessoa, turno1);
		
		viatek.getMovimentos().addAll(Arrays.asList(movimento));
		
		turno1.getMovimentos().addAll(Arrays.asList(movimento));
		
		MovimentoDetalhe mov1 = new MovimentoDetalhe(balde, movimento, equipamento, 12.0, balde.getCiclo(), balde.getCavidade(), balde.getPreco(), 2000, 500);
		MovimentoDetalhe mov2 = new MovimentoDetalhe(pivoMondial, movimento, equipamento, 12.0, pivoMondial.getCiclo(), pivoMondial.getCavidade(), pivoMondial.getPreco(), 4000, 2000);
		MovimentoDetalhe mov3 = new MovimentoDetalhe(acoplamento, movimento, equipamento, 12.0, acoplamento.getCiclo(), acoplamento.getCavidade(), acoplamento.getPreco(), 3000, 500);

		movimento.getItens().addAll(Arrays.asList(mov1, mov2, mov3));
		equipamento.getItens().addAll(Arrays.asList(mov1, mov2, mov3));
		balde.getItens().addAll(Arrays.asList(mov1));
		pivoMondial.getItens().addAll(Arrays.asList(mov2));
		acoplamento.getItens().addAll(Arrays.asList(mov3));
		
		empresaRepository.saveAll(Arrays.asList(viatek, viamed, gplast));
		pessoaRepository.saveAll(Arrays.asList(pessoa, usuario));
		linhaRepository.saveAll(Arrays.asList(linha1, linha2, linha3));
		equipamentoRepository.saveAll(Arrays.asList(equipamento));
		turnoRepository.saveAll(Arrays.asList(turno1, turno2, turno3));
		produtoRepository.saveAll(Arrays.asList(balde, pivoMondial, acoplamento));
		movimentoRepository.saveAll(Arrays.asList(movimento));
		movimentoDetalheRepository.saveAll(Arrays.asList(mov1, mov2, mov3));
	}
}
