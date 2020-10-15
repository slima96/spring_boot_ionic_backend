package br.com.viatekbrasil.industrial.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.viatekbrasil.industrial.domain.Movimento;
import br.com.viatekbrasil.industrial.domain.MovimentoDetalhe;
import br.com.viatekbrasil.industrial.domain.enums.StatusMovimento;
import br.com.viatekbrasil.industrial.repositories.MovimentoDetalheRepository;
import br.com.viatekbrasil.industrial.repositories.MovimentoRepository;
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

	public Movimento find(Integer id) {
		Optional<Movimento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Movimento.class.getName()));
	}
	
	@Transactional
	public Movimento insert(Movimento obj) {
		obj.setId(null);
		obj.setData(new Date());
		obj.setStatus(StatusMovimento.EMDIGITACAO);
		
		obj = repo.save(obj);
		
		for (MovimentoDetalhe mv : obj.getItens()) {
			mv.setCiclo(produtoService.find(mv.getProduto().getId()).getCiclo());
			mv.setCavidade(produtoService.find(mv.getProduto().getId()).getCavidade());
			mv.setPreco(produtoService.find(mv.getProduto().getId()).getPreco());
			mv.setEquipamento(equipamentoService.find(mv.getEquipamento().getId()));
			mv.setMovimento(obj);
		}
		
		movimentoDetalheRepository.saveAll(obj.getItens());
		
		return obj;
	}
}