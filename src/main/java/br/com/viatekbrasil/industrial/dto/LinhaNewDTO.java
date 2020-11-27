package br.com.viatekbrasil.industrial.dto;

import java.io.Serializable;

import br.com.viatekbrasil.industrial.services.validation.LinhaInsert;

@LinhaInsert
public class LinhaNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	public LinhaNewDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
