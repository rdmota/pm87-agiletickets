package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.CalculoPrecoStrategy;
import br.com.caelum.agiletickets.models.Sessao;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {

		CalculoPrecoStrategy tipoEspetaculo = sessao.getEspetaculo().getTipo();

		return tipoEspetaculo.calculaPreco(sessao).multiply(BigDecimal.valueOf(quantidade));

	}

}