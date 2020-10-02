package br.com.viatekbrasil.industrial.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viatekbrasil.industrial.domain.Empresa;
import br.com.viatekbrasil.industrial.repositories.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository repo;

	public Empresa find(Integer id) {
		Optional<Empresa> obj = repo.findById(id);
		return obj.orElse(null);
	}
}