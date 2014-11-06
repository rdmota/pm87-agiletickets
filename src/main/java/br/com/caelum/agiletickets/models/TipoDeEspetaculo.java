package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;

public enum TipoDeEspetaculo implements CalculoPrecoStrategy {

	CINEMA {
		@Override
		public BigDecimal calculaPreco(Sessao sessao) {
			if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.05) {
				return sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
			return sessao.getPreco();
		}
	},

	SHOW {
		@Override
		public BigDecimal calculaPreco(Sessao sessao) {
			return CINEMA.calculaPreco(sessao);
		}
	},

	TEATRO {
		@Override
		public BigDecimal calculaPreco(Sessao sessao) {
			return sessao.getPreco();
		}
	},

	BALLET {
		@Override
		public BigDecimal calculaPreco(Sessao sessao) {

			BigDecimal preco;

			if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.50) {
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			} else {
				preco = sessao.getPreco();
			}

			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}

			return preco;
		}
	},

	ORQUESTRA {
		@Override
		public BigDecimal calculaPreco(Sessao sessao) {
			return BALLET.calculaPreco(sessao);
		}
	};

}