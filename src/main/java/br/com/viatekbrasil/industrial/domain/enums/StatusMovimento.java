package br.com.viatekbrasil.industrial.domain.enums;

public enum StatusMovimento {

	EMDIGITACAO(1, "Em digitação"),
	CONCLUIDO(2, "Concluído");
	
	private int cod;
	private String descricao;
	
	private StatusMovimento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static StatusMovimento toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (StatusMovimento x : StatusMovimento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
