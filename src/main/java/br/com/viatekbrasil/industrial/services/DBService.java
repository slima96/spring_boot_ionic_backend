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
		Produto p1 =  new Produto(null, "10002", "Produto 1", 35, 1, 3.0, linha1, viatek);
		Produto p2 =  new Produto(null, "10003", "Produto 2", 35, 1, 3.0, linha1, viatek);
		Produto p3 =  new Produto(null, "10004", "Produto 3", 35, 1, 3.0, linha1, viatek);
		Produto p4 =  new Produto(null, "10005", "Produto 4", 35, 1, 3.0, linha1, viatek);
		Produto p5 =  new Produto(null, "10006", "Produto 5", 35, 1, 3.0, linha1, viatek);
		Produto p6 =  new Produto(null, "10007", "Produto 6", 35, 1, 3.0, linha1, viatek);
		Produto p7 =  new Produto(null, "10008", "Produto 7", 35, 1, 3.0, linha1, viatek);
		Produto p8 =  new Produto(null, "10009", "Produto 8", 35, 1, 3.0, linha1, viatek);
/*		Produto p9 =  new Produto(null, "10010", "Produto 9", 35, 1, 3.0, linha1, viatek);
		Produto p10 =  new Produto(null, "10011", "Produto 10", 35, 1, 3.0, linha1, viatek);
		Produto p11 =  new Produto(null, "10012", "Produto 11", 35, 1, 3.0, linha1, viatek);
		Produto p12 =  new Produto(null, "10013", "Produto 12", 35, 1, 3.0, linha1, viatek);
		Produto p13 =  new Produto(null, "10014", "Produto 13", 35, 1, 3.0, linha1, viatek);
		Produto p14 =  new Produto(null, "10015", "Produto 14", 35, 1, 3.0, linha1, viatek);
		Produto p15 =  new Produto(null, "10016", "Produto 15", 35, 1, 3.0, linha1, viatek);
		Produto p16 =  new Produto(null, "10017", "Produto 16", 35, 1, 3.0, linha1, viatek);
		Produto p17 =  new Produto(null, "10018", "Produto 17", 35, 1, 3.0, linha1, viatek);
		Produto p18 =  new Produto(null, "10019", "Produto 18", 35, 1, 3.0, linha1, viatek);
		Produto p19 =  new Produto(null, "10020", "Produto 19", 35, 1, 3.0, linha1, viatek);
		Produto p20 =  new Produto(null, "10021", "Produto 20", 35, 1, 3.0, linha1, viatek);
		Produto p21 =  new Produto(null, "10022", "Produto 21", 35, 1, 3.0, linha1, viatek);
		Produto p22 =  new Produto(null, "10023", "Produto 22", 35, 1, 3.0, linha1, viatek);
		Produto p23 =  new Produto(null, "10024", "Produto 23", 35, 1, 3.0, linha1, viatek);
		Produto p24 =  new Produto(null, "10025", "Produto 24", 35, 1, 3.0, linha1, viatek);
		Produto p25 =  new Produto(null, "10026", "Produto 25", 35, 1, 3.0, linha1, viatek);
		Produto p26 =  new Produto(null, "10027", "Produto 26", 35, 1, 3.0, linha1, viatek);
		Produto p27 =  new Produto(null, "10028", "Produto 27", 35, 1, 3.0, linha1, viatek);
		Produto p28 =  new Produto(null, "10029", "Produto 28", 35, 1, 3.0, linha1, viatek);
		Produto p29 =  new Produto(null, "10030", "Produto 29", 35, 1, 3.0, linha1, viatek);
		Produto p30 =  new Produto(null, "10031", "Produto 30", 35, 1, 3.0, linha1, viatek);
		Produto p31 =  new Produto(null, "10032", "Produto 31", 35, 1, 3.0, linha1, viatek);
		Produto p32 =  new Produto(null, "10033", "Produto 32", 35, 1, 3.0, linha1, viatek);
		Produto p33 =  new Produto(null, "10034", "Produto 33", 35, 1, 3.0, linha1, viatek);
		Produto p34 =  new Produto(null, "10035", "Produto 34", 35, 1, 3.0, linha1, viatek);
		Produto p35 =  new Produto(null, "10036", "Produto 35", 35, 1, 3.0, linha1, viatek);
		Produto p36 =  new Produto(null, "10037", "Produto 36", 35, 1, 3.0, linha1, viatek);
		Produto p37 =  new Produto(null, "10038", "Produto 37", 35, 1, 3.0, linha1, viatek);
		Produto p38 =  new Produto(null, "10039", "Produto 38", 35, 1, 3.0, linha1, viatek);
		Produto p39 =  new Produto(null, "10040", "Produto 39", 35, 1, 3.0, linha1, viatek);
		Produto p40 =  new Produto(null, "10041", "Produto 40", 35, 1, 3.0, linha1, viatek);
*/		

		viatek.getProdutos().addAll(Arrays.asList(balde, p1, p2, p3, p4, p5, p6, p7, p8 /*, p9, p10, p11, p12, p13, p14,
													p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30,
													p31, p32, p33, p34, p35, p36, p37, p38, p39, p40*/));
		viamed.getProdutos().addAll(Arrays.asList(pivoMondial));
		gplast.getProdutos().addAll(Arrays.asList(acoplamento));
		
		linha1.getProdutos().addAll(Arrays.asList(balde, p1, p2, p3, p4, p5, p6, p7, p8 /*, p9, p10, p11, p12, p13, p14,
													p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30,
													p31, p32, p33, p34, p35, p36, p37, p38, p39, p40*/));
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
		produtoRepository.saveAll(Arrays.asList(balde, pivoMondial, acoplamento, p1, p2, p3, p4, p5, p6, p7, p8 /*, p9, p10, p11, p12, p13, p14,
													p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30,
													p31, p32, p33, p34, p35, p36, p37, p38, p39, p40*/));
		movimentoRepository.saveAll(Arrays.asList(movimento));
		movimentoDetalheRepository.saveAll(Arrays.asList(mov1, mov2, mov3));
	}
}
