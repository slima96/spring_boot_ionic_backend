package br.com.viatekbrasil.industrial.dto;

import java.io.Serializable;

import br.com.viatekbrasil.industrial.services.validation.EmpresaInsert;

@EmpresaInsert
public class EmpresaNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	public EmpresaNewDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
