package br.com.viatekbrasil.industrial.dto;

import java.io.Serializable;

import br.com.viatekbrasil.industrial.services.validation.PessoaInsert;

@PessoaInsert
public class PessoaNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String usuario;
	private String senha;
	
	public PessoaNewDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
