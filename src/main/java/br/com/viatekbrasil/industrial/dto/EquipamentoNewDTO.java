package br.com.viatekbrasil.industrial.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import br.com.viatekbrasil.industrial.domain.Empresa;
import br.com.viatekbrasil.industrial.domain.Equipamento;
import br.com.viatekbrasil.industrial.services.validation.EquipamentoInsert;

@EquipamentoInsert
public class EquipamentoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String codigo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String descricao;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String tonelagem;
	
	private Empresa empresa;
	
	public EquipamentoNewDTO() {
	}
	
	public EquipamentoNewDTO(Equipamento obj) {
		id = obj.getId();
		codigo = obj.getCodigo();
		descricao = obj.getDescricao();
		tonelagem = obj.getTonelagem();
		empresa = obj.getEmpresa();
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}	
}
