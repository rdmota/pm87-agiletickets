package br.com.caelum.agiletickets.models;

public enum Periodicidade {
	
	DIARIA(1), SEMANAL(7);
	
	private Integer dias;
	private Periodicidade(Integer dias){
		this.dias = dias;
	}
	public Integer getDias() {
		return dias;
	}
	
}
