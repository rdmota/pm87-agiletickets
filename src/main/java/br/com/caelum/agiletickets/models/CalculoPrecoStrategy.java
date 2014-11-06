package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;

public interface CalculoPrecoStrategy {	
	BigDecimal calculaPreco(Sessao sessao);
}
