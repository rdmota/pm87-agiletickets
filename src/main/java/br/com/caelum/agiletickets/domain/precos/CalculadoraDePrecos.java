package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		TipoDeEspetaculo tipoEspetaculo = sessao.getEspetaculo().getTipo();
		Integer duracaoEmMinutos = sessao.getDuracaoEmMinutos();
		Integer totalIngressos = sessao.getTotalIngressos();
		BigDecimal precoInicial = sessao.getPreco();
		Integer ingressosReservados = sessao.getIngressosReservados();
		
		preco = calcula(tipoEspetaculo, duracaoEmMinutos, totalIngressos, precoInicial, ingressosReservados); 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal calcula(TipoDeEspetaculo tipoEspetaculo, Integer duracaoEmMinutos, Integer totalIngressos,
			BigDecimal precoInicial, Integer ingressosReservados) {
		
		if(tipoEspetaculo.equals(TipoDeEspetaculo.CINEMA) || tipoEspetaculo.equals(TipoDeEspetaculo.SHOW)) {

			return calculaCinemaOuShow(totalIngressos, precoInicial, ingressosReservados);
			
		}
		
		if(tipoEspetaculo.equals(TipoDeEspetaculo.BALLET)) {
			return calculaBallet(duracaoEmMinutos, totalIngressos, precoInicial, ingressosReservados);
		}

		if(tipoEspetaculo.equals(TipoDeEspetaculo.ORQUESTRA)) {
			return calculaOrquestra(duracaoEmMinutos, totalIngressos, precoInicial, ingressosReservados);
		}
			
		return precoInicial;
	}

	private static BigDecimal calculaOrquestra(Integer duracaoEmMinutos,
			Integer totalIngressos, BigDecimal precoInicial,
			Integer ingressosReservados) {
		BigDecimal preco;
		if((totalIngressos - ingressosReservados) / totalIngressos.doubleValue() <= 0.50) { 
			preco = precoInicial.add(precoInicial.multiply(BigDecimal.valueOf(0.20)));
		} else {
			preco = precoInicial;
		}

		if(duracaoEmMinutos > 60){
			preco = preco.add(precoInicial.multiply(BigDecimal.valueOf(0.10)));
		}
		return preco;
	}

	private static BigDecimal calculaBallet(Integer duracaoEmMinutos,
			Integer totalIngressos, BigDecimal precoInicial,
			Integer ingressosReservados) {
		BigDecimal preco;
		preco = calculaOrquestra(duracaoEmMinutos, totalIngressos, precoInicial,
				ingressosReservados);
		return preco;
	}

	private static BigDecimal calculaCinemaOuShow(Integer totalIngressos, BigDecimal precoInicial, Integer ingressosReservados) {
		
		BigDecimal preco;
		//quando estiver acabando os ingressos... 
		if((totalIngressos - ingressosReservados) / totalIngressos.doubleValue() <= 0.05) { 
			preco = precoInicial.add(precoInicial.multiply(BigDecimal.valueOf(0.10)));
		} else {
			preco = precoInicial;
		}
		return preco;
	}
	
}