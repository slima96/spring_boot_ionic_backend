package br.com.viatekbrasil.industrial.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.viatekbrasil.industrial.domain.Movimento;
import br.com.viatekbrasil.industrial.domain.MovimentoDetalhe;
import br.com.viatekbrasil.industrial.domain.enums.StatusMovimento;
import br.com.viatekbrasil.industrial.repositories.MovimentoDetalheRepository;
import br.com.viatekbrasil.industrial.repositories.MovimentoRepository;
import br.com.viatekbrasil.industrial.security.UserSS;
import br.com.viatekbrasil.industrial.services.exceptions.AuthorizationException;
import br.com.viatekbrasil.industrial.services.exceptions.ObjectNotFoundException;

@Service
public class MovimentoService {
	
	@Autowired
	private MovimentoRepository repo;
	
	@Autowired
	private EquipamentoService equipamentoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private MovimentoDetalheRepository movimentoDetalheRepository;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private TurnoService turnoService;

	public Movimento find(Integer id) {
		Optional<Movimento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Movimento.class.getName()));
	}
	
	@Transactional
	public Movimento insert(Movimento obj) {
		obj.setId(null);
		obj.setData(new Date());
		obj.setEmpresa(empresaService.find(obj.getEmpresa().getId()));
		obj.setTurno(turnoService.find(obj.getTurno().getId()));
		obj.setStatus(StatusMovimento.EMDIGITACAO);
		
		obj = repo.save(obj);
		
		for (MovimentoDetalhe mv : obj.getItens()) {
			mv.setProduto(produtoService.find(mv.getProduto().getId()));
			mv.setCiclo(mv.getProduto().getCiclo());
			mv.setCavidade(mv.getProduto().getCavidade());
			mv.setPreco(mv.getProduto().getPreco());
			mv.setEquipamento(equipamentoService.find(mv.getEquipamento().getId()));
			mv.setMovimento(obj);
		}
		
		movimentoDetalheRepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}
	
	public Page<Movimento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}	
}