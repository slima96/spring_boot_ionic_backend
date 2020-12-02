package br.com.viatekbrasil.industrial.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import br.com.viatekbrasil.industrial.domain.Empresa;
import br.com.viatekbrasil.industrial.domain.Linha;
import br.com.viatekbrasil.industrial.domain.Produto;
import br.com.viatekbrasil.industrial.services.validation.ProdutoUpdate;

@ProdutoUpdate
public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String codigo;
	
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String descricao;
	
	private Integer ciclo;

	private Integer cavidade;

	private Double preco;
	
	private Empresa empresa;
	
	private Linha linha;
	
	public ProdutoDTO() {
	}
	
	public ProdutoDTO(Produto obj) {
		id = obj.getId();
		codigo = obj.getCodigo();
		descricao = obj.getDescricao();
		ciclo = obj.getCiclo();
		cavidade = obj.getCavidade();
		preco = obj.getPreco();
		empresa = obj.getEmpresa();
		linha = obj.getLinha();
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Linha getLinha() {
		return linha;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}	
}
