package br.com.viatekbrasil.industrial.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.viatekbrasil.industrial.domain.enums.StatusMovimento;

@Entity
public class Movimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data;
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name = "turno_id")
	private Turno turno;
	
	@OneToMany(mappedBy = "id.movimento")
	private Set<MovimentoDetalhe> itens = new HashSet<>();
		
	public Movimento() {
	}

	public Movimento(Integer id, Date data, StatusMovimento status, Empresa empresa, Pessoa pessoa, Turno turno) {
		super();
		this.id = id;
		this.data = data;
		this.status = status.getCod();
		this.empresa = empresa;
		this.pessoa = pessoa;
		this.turno = turno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public StatusMovimento getStatus() {
		return StatusMovimento.toEnum(status);
	}

	public void setStatus(StatusMovimento status) {
		this.status = status.getCod();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Set<MovimentoDetalhe> getItens() {
		return itens;
	}

	public void setItens(Set<MovimentoDetalhe> itens) {
		this.itens = itens;
	}
	
	public double getMediaEficiencia() {
		double soma = 0.0;
		int qtdeItens = 0;
		for(MovimentoDetalhe ip : itens) {
			soma = soma + ip.getEficiencia();
			qtdeItens += 1;
		}
		double media = soma / qtdeItens;
		return media;
	}
	
	public double getMediaRefugo() {
		double soma = 0.0;
		int qtdeItens = 0;
		for(MovimentoDetalhe ip : itens) {
			soma = soma + ip.getRefugo();
			qtdeItens += 1;
		}
		double media = soma / qtdeItens;
		return media;
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
		Movimento other = (Movimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Produção de número: ");
		builder.append(getId());
		builder.append("\n");
		builder.append("Data: ");
		builder.append(sdf.format(getData()));
		builder.append(" - ");
		builder.append("Empresa: ");
		builder.append(getEmpresa().getNome());
		builder.append(" - ");
		builder.append("Turno: ");
		builder.append(getTurno().getNome());
		builder.append(" - ");
		builder.append("Status de produção: ");
		builder.append(getStatus().getDescricao());
		builder.append("\n");
		builder.append("\n");
		builder.append("Detalhes do lançamento: ");
		builder.append("\n");
		builder.append("\n");
		for(MovimentoDetalhe mv : getItens()) {
			builder.append(mv.toString());
		}
		
		builder.append("\n");
		builder.append("Média de eficiência: ");
		builder.append(NumberFormat.getPercentInstance().format(getMediaEficiencia()));
		builder.append("\n");
		builder.append("Média de Refugo: ");
		builder.append(NumberFormat.getPercentInstance().format(getMediaRefugo()));
		
		return builder.toString();
	}	
	
	
}
