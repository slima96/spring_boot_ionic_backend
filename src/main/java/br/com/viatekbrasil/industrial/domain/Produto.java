package br.com.viatekbrasil.industrial.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String codigo;
	private String descricao;
	private Integer ciclo;
	private Integer cavidade;
	private Double preco;
	
	@ManyToOne
	@JoinColumn(name = "linha_id")
	private Linha linha;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.produto")
	private Set<MovimentoDetalhe> itens = new HashSet<>();
	
	public Produto() {
	}

	public Produto(Integer id,String codigo, String descricao, Integer ciclo, Integer cavidade, Double preco, Linha linha, Empresa empresa) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.ciclo = ciclo;
		this.cavidade = cavidade;
		this.preco = preco;
		this.linha = linha;
		this.empresa = empresa;
	}
	
	@JsonIgnore
	public List<Movimento> getMovimentos(){
		List<Movimento> lista = new ArrayList<>();
		for (MovimentoDetalhe x : itens) {
			lista.add(x.getMovimento());
		}
		return lista;
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

	public Integer getCiclo() {
		return ciclo;
	}

	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	public Integer getCavidade() {
		return cavidade;
	}

	public void setCavidade(Integer cavidade) {
		this.cavidade = cavidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Linha getLinha() {
		return linha;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Set<MovimentoDetalhe> getItens() {
		return itens;
	}

	public void setItens(Set<MovimentoDetalhe> itens) {
		this.itens = itens;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
