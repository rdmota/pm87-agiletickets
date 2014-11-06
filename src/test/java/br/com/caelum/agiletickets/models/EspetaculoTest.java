package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
//		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	@Test
	public void criarSessoesParaDataInformada(){
		LocalDate inicio = LocalDate.now(); 
		LocalDate fim = LocalDate.now();
		LocalTime horario = LocalTime.MIDNIGHT.plusHours(17);
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(inicio, sessoes.get(0).getInicio().toLocalDate());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void dataInicioNaoPodeSerNula() {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.criaSessoes(null, new LocalDate(), new LocalTime(), Periodicidade.DIARIA);
	}
	
	@Test
	public void criarTresSessoesEntreOsDias1e3ComPeriodicidadeDiaria() {
		LocalDate inicio = new LocalDate(2014,1,1);
		LocalDate fim = new LocalDate(2014,1,3);
		LocalTime horario = LocalTime.now();
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		assertEquals(3, sessoes.size());
		
		LocalDate dataPrimeiraSessao = new LocalDate(sessoes.get(0).getInicio());
		assertEquals(new LocalDate(2014,1,1), dataPrimeiraSessao);
		
		LocalDate dataSegundaSessao = new LocalDate(sessoes.get(1).getInicio());
		assertEquals(new LocalDate(2014,1,2), dataSegundaSessao);
		
		LocalDate dataTerceiraSessao = new LocalDate(sessoes.get(2).getInicio());
		assertEquals(new LocalDate(2014,1,3), dataTerceiraSessao);
		
	}
	
	@Test
	public void criarCincoSessoesEntreOsDias1e31DeJaneiroComPeriodicidadeSemanal() {
		LocalDate inicio = new LocalDate(2014,1,1);
		LocalDate fim = new LocalDate(2014,1,31);
		LocalTime horario = LocalTime.now();
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		assertEquals(5, sessoes.size());
		
		LocalDate dataPrimeiraSessao = new LocalDate(sessoes.get(0).getInicio());
		assertEquals(new LocalDate(2014,1,1), dataPrimeiraSessao);
		
		LocalDate dataSegundaSessao = new LocalDate(sessoes.get(1).getInicio());
		assertEquals(new LocalDate(2014,1,8), dataSegundaSessao);
		
		LocalDate dataTerceiraSessao = new LocalDate(sessoes.get(2).getInicio());
		assertEquals(new LocalDate(2014,1,15), dataTerceiraSessao);
		
		LocalDate dataQuartaSessao = new LocalDate(sessoes.get(3).getInicio());
		assertEquals(new LocalDate(2014,1,22), dataQuartaSessao);
		
		LocalDate dataQuintaSessao = new LocalDate(sessoes.get(4).getInicio());
		assertEquals(new LocalDate(2014,1,29), dataQuintaSessao);
	}
	
	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
