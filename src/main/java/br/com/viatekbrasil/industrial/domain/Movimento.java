package br.com.viatekbrasil.industrial.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.viatekbrasil.industrial.domain.enums.StatusMovimento;

@Entity
public class Movimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date data;
	private Integer status;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "turno_id")
	private Turno turno;
	
	public Movimento() {
	}

	public Movimento(Integer id, Date data, StatusMovimento status, Empresa empresa, Turno turno) {
		super();
		this.id = id;
		this.data = data;
		this.status = status.getCod();
		this.empresa = empresa;
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

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
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
	
}
