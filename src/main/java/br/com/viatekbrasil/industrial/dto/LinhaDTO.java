package br.com.viatekbrasil.industrial.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.viatekbrasil.industrial.domain.Linha;
import br.com.viatekbrasil.industrial.services.validation.LinhaUpdate;

@LinhaUpdate
public class LinhaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=20, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	public LinhaDTO() {
	}
	
	public LinhaDTO(Linha obj) {
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
