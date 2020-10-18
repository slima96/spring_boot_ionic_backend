package br.com.viatekbrasil.industrial.domain;

import java.io.Serializable;
import java.text.NumberFormat;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MovimentoDetalhe implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private MovimentoDetalhePK id = new MovimentoDetalhePK();
	
	private Double qtdeHoras;
	private Integer ciclo;
	private Integer cavidade;
	private Double preco;
	private Integer realizado;
	private Integer refugado;
	
	public MovimentoDetalhe() {
	}

	public MovimentoDetalhe(Produto produto, Movimento movimento, Equipamento equipamento, Double qtdeHoras, Integer ciclo, Integer cavidade, Double preco, Integer realizado, Integer refugado) {
		super();
		id.setProduto(produto);
		id.setMovimento(movimento);
		id.setEquipamento(equipamento);
		this.qtdeHoras = qtdeHoras;
		this.ciclo = ciclo;
		this.cavidade = cavidade;
		this.preco = preco;
		this.realizado = realizado;
		this.refugado = refugado;
		
	}

	public Produto getProduto() {
		return id.getProduto();
	}
	
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}
	
	@JsonIgnore
	public Movimento getMovimento() {
		return id.getMovimento();
	}
	
	public void setMovimento(Movimento movimento) {
		id.setMovimento(movimento);
	}
	
	public Equipamento getEquipamento() {
		return id.getEquipamento();
	}
	
	public void setEquipamento(Equipamento equipamento) {
		id.setEquipamento(equipamento);
	}
	
	public MovimentoDetalhePK getId() {
		return id;
	}

	public void setId(MovimentoDetalhePK id) {
		this.id = id;
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

	public Double getQtdeHoras() {
		return qtdeHoras;
	}

	public void setQtdeHoras(Double qtdeHoras) {
		this.qtdeHoras = qtdeHoras;
	}

	public Integer getRealizado() {
		return realizado;
	}

	public void setRealizado(Integer realizado) {
		this.realizado = realizado;
	}

	public Integer getRefugado() {
		return refugado;
	}

	public void setRefugado(Integer refugado) {
		this.refugado = refugado;
	}
	
	public double getProgramado() {
		return (((3600 / ciclo) * cavidade) * qtdeHoras);

	}
	
	public double getEficiencia() {
		return (realizado / getProgramado()) ;
	}
	
	public double getRefugo() {
		
		@SuppressWarnings("deprecation")
		double real = new Double(realizado);
		
		@SuppressWarnings("deprecation")
		double ref = new Double(refugado);
		
		return (ref / real) ;
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
		MovimentoDetalhe other = (MovimentoDetalhe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Código do equipamento: ");
		builder.append(getEquipamento().getCodigo());
		builder.append("\n");
		builder.append("Código do Produto: ");
		builder.append(getProduto().getCodigo());
		builder.append(" - ");
		builder.append("Nome do Produto: ");
		builder.append(getProduto().getDescricao());
		builder.append(" - ");
		builder.append("Programado: ");
		builder.append(NumberFormat.getIntegerInstance().format(getProgramado()));
		builder.append(" - ");
		builder.append("Realizado: ");
		builder.append(NumberFormat.getIntegerInstance().format(getRealizado()));
		builder.append(" - ");
		builder.append("Refugao: ");
		builder.append(NumberFormat.getIntegerInstance().format(getRefugado()));
		builder.append(" - ");
		builder.append("Eficiência: ");
		builder.append(NumberFormat.getPercentInstance().format(getEficiencia()));
		builder.append(" - ");
		builder.append("Refugo: ");
		builder.append(NumberFormat.getPercentInstance().format(getRefugo()));	
		builder.append("\n");
		builder.append("\n");
		
		return builder.toString();
	}
	
	
	
}
