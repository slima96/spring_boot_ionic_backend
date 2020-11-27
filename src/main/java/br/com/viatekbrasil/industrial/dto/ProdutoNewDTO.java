package br.com.viatekbrasil.industrial.dto;

import java.io.Serializable;

import br.com.viatekbrasil.industrial.services.validation.ProdutoInsert;

@ProdutoInsert
public class ProdutoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String descricao;
	private Integer ciclo;
	private Integer cavidade;
	private Double preco;
	
	private Integer empresaid;
	
	private Integer linhaid;
	
	public ProdutoNewDTO() {
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

	public Integer getEmpresaid() {
		return empresaid;
	}

	public void setEmpresaid(Integer empresaid) {
		this.empresaid = empresaid;
	}

	public Integer getLinhaid() {
		return linhaid;
	}

	public void setLinhaid(Integer linhaid) {
		this.linhaid = linhaid;
	}
	
}
