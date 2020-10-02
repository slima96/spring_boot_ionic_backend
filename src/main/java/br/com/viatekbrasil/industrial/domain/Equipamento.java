package br.com.viatekbrasil.industrial.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Equipamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String codigo;
	private String descricao;
	private String tonelagem;
	
	@JsonBackReference
	@ManyToMany
	@JoinTable(name = "EQUIPAMENTO_EMPRESA",
		joinColumns = @JoinColumn(name = "equipamento_id"),
		inverseJoinColumns = @JoinColumn(name = "empresa_id")
	)	
	private List<Empresa> empresas = new ArrayList<>();
	
	public Equipamento() {
	}

	public Equipamento(Integer id, String codigo, String descricao, String tonelagem) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.tonelagem = tonelagem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTonelagem() {
		return tonelagem;
	}

	public void setTonelagem(String tonelagem) {
		this.tonelagem = tonelagem;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipamento other = (Equipamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
