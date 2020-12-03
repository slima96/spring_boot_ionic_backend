package br.com.viatekbrasil.industrial.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import br.com.viatekbrasil.industrial.domain.Empresa;
import br.com.viatekbrasil.industrial.domain.Equipamento;
import br.com.viatekbrasil.industrial.services.validation.EquipamentoUpdate;

@EquipamentoUpdate
public class EquipamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String codigo;
	
	@Length(min=5, max=40, message="O tamanho deve ser entre 5 e 40 caracteres")
	private String descricao;
	
	@Length(min=5, max=10, message="O tamanho deve ser entre 5 e 10 caracteres")
	private String tonelagem;
	
	private Empresa empresa;
	
	public EquipamentoDTO() {
	}
	
	public EquipamentoDTO(Equipamento obj) {
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
