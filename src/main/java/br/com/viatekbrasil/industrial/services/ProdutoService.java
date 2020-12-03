package br.com.viatekbrasil.industrial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.viatekbrasil.industrial.domain.Empresa;
import br.com.viatekbrasil.industrial.domain.Linha;
import br.com.viatekbrasil.industrial.domain.Produto;
import br.com.viatekbrasil.industrial.dto.ProdutoDTO;
import br.com.viatekbrasil.industrial.dto.ProdutoNewDTO;
import br.com.viatekbrasil.industrial.repositories.ProdutoRepository;
import br.com.viatekbrasil.industrial.services.exceptions.DataIntegrityException;
import br.com.viatekbrasil.industrial.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private LinhaService linhaService;

	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Produto findByCodigo(String codigo) {
		Optional<Produto> obj = Optional.ofNullable(repo.findByCodigo(codigo));
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + codigo + ", Tipo: " + Produto.class.getName()));
	}
	
	@Transactional
	public Produto insert (Produto obj) {
		obj.setId(null);
	
		obj = repo.save(obj);
		return obj;
	}
	
	public Produto update(Produto obj) {
		Produto newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível remover por que há entidades relacionadas");
		}	
	}

	public List<Produto> findAll() {
		return repo.findAll();
	}
	
	public Page<Produto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
//	public Produto fromDTO(ProdutoDTO objDTO) {
//		return new Produto(
//				objDTO.getId(), 
//				objDTO.getCodigo(), 
//				objDTO.getDescricao(), 
//				objDTO.getCiclo(), 
//				objDTO.getCavidade(), 
//				objDTO.getPreco(), 
//				objDTO.getLinha(), 
//				objDTO.getEmpresa());
//	}
	
	public Produto fromDTO(ProdutoDTO objDTO, Integer id) {
		
		Produto aux = find(id);
		
		Produto newProduto = new Produto();
		
		newProduto.setId(id);
		
		newProduto.setCodigo(objDTO.getCodigo() != null  ? objDTO.getCodigo() : aux.getCodigo());
		newProduto.setDescricao(objDTO.getDescricao() != null ? objDTO.getDescricao() : aux.getDescricao());
		newProduto.setCiclo(objDTO.getCiclo() != null ? objDTO.getCiclo() : aux.getCiclo());
		newProduto.setCavidade(objDTO.getCavidade() != null ? objDTO.getCavidade() : aux.getCavidade());
		newProduto.setPreco(objDTO.getPreco() != null ? objDTO.getPreco() : aux.getPreco());
		newProduto.setEmpresa(objDTO.getEmpresa() != null ? objDTO.getEmpresa() : aux.getEmpresa());
		newProduto.setLinha(objDTO.getLinha() != null ? objDTO.getLinha() : aux.getLinha());
		
		return newProduto;
	}
	
	public Produto fromDTO(ProdutoNewDTO objDTO) {
		Linha linha = linhaService.find(objDTO.getLinha().getId());
		Empresa empresa = empresaService.find(objDTO.getEmpresa().getId());
		
		Produto prod = new Produto(
				null, 
				objDTO.getCodigo(), 
				objDTO.getDescricao(), 
				objDTO.getCiclo(), 
				objDTO.getCavidade(), 
				objDTO.getPreco(), 
				linha, 
				empresa);
		
		return prod;
	}
	
	private void updateData(Produto newObj, Produto obj) {
		newObj.setCodigo(obj.getCodigo());
		newObj.setDescricao(obj.getDescricao());
		newObj.setCiclo(obj.getCiclo());
		newObj.setCavidade(obj.getCavidade());
		newObj.setPreco(obj.getPreco());
		newObj.setEmpresa(obj.getEmpresa());
		newObj.setLinha(obj.getLinha());
	}
}