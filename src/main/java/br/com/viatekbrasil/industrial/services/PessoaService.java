package br.com.viatekbrasil.industrial.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.viatekbrasil.industrial.domain.Pessoa;
import br.com.viatekbrasil.industrial.domain.enums.Perfil;
import br.com.viatekbrasil.industrial.dto.PessoaDTO;
import br.com.viatekbrasil.industrial.repositories.PessoaRepository;
import br.com.viatekbrasil.industrial.security.UserSS;
import br.com.viatekbrasil.industrial.services.exceptions.AuthorizationException;
import br.com.viatekbrasil.industrial.services.exceptions.DataIntegrityException;
import br.com.viatekbrasil.industrial.services.exceptions.ObjectNotFoundException;

@Service
public class PessoaService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private PessoaRepository repo;
	
	@Autowired
	private S3Service s3Service;

	@SuppressWarnings("null")
	public Pessoa find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Pessoa> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName()));
	}
	
	public Pessoa insert (Pessoa obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Pessoa update(Pessoa obj) {
		Pessoa newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
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

	public List<Pessoa> findAll() {
		return repo.findAll();
	}
	
	public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Pessoa fromDTO(PessoaDTO objDTO) {
		return new Pessoa(objDTO.getId(), objDTO.getNome(), objDTO.getUsuario(), pe.encode(objDTO.getSenha()));
	}
	
	private void updateData(Pessoa newObj, Pessoa obj) {
		newObj.setNome(obj.getNome());
		
	}
	
	public URI uploadProfilePicture(MultipartFile multiPartFile) {
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		
		URI uri = s3Service.uploadFile(multiPartFile);
		
		Pessoa pes = repo.findByUsuario(user.getUsername());
		pes.setImageUrl(uri.toString());
		repo.save(pes);		
		
		return uri;
	}
}