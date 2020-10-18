package br.com.viatekbrasil.industrial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.viatekbrasil.industrial.domain.Pessoa;
import br.com.viatekbrasil.industrial.repositories.PessoaRepository;
import br.com.viatekbrasil.industrial.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PessoaRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Pessoa pessoa = repo.findByUsuario(usuario);
		if(pessoa == null) {
			throw new UsernameNotFoundException(usuario);
		}
		
		return new UserSS(pessoa.getId(), pessoa.getUsuario(), pessoa.getSenha(), pessoa.getPerfis());
	}
}
