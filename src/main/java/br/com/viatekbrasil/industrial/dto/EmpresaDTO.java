package br.com.viatekbrasil.industrial.dto;

import java.io.Serializable;

import br.com.viatekbrasil.industrial.domain.Empresa;

public class EmpresaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	public EmpresaDTO() {
	}
	
	public EmpresaDTO(Empresa obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
