package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteTabuleiro {
	
	Tabuleiro tabuleiro;

	@Before
	public void before() {
		tabuleiro = new Tabuleiro();
	}

	@Test
	public void espiarPeca() throws CasaOcupadaException {
		// Instancia uma peça qualquer.
		Peca peca = new Palhaco(null);

		// Coloca a peça no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(1, 1), peca);

		// A mesma peça deve ser lida.
		Assert.assertEquals(peca, tabuleiro.espiarPeca(new Posicao(1, 1)));

		// E ela deve permanecer no tabuleiro.
		Assert.assertEquals(peca, tabuleiro.espiarPeca(new Posicao(1, 1)));
	}

	@Test
	public void retirarPeca() throws CasaOcupadaException {
		// Instancia uma peça qualquer.
		Peca peca = new Palhaco(null);

		// Coloca a peça no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(1, 1), peca);

		// A mesma peça deve ser retirada.
		Assert.assertEquals(peca, tabuleiro.retirarPeca(new Posicao(1, 1)));

		// E ela não deve mais estar no tabuleiro.
		Assert.assertEquals(null, tabuleiro.retirarPeca(new Posicao(1, 1)));
	}

	@Test
	public void colocarPeca_emCasaVazia() throws CasaOcupadaException {
		// Coloca uma peça em uma casa vazia.
		tabuleiro.colocarPeca(new Posicao(1, 1), new Palhaco(null));
	}

	@Test(expected = CasaOcupadaException.class)
	public void colocarPeca_emCasaOcupada() throws CasaOcupadaException {
		// Coloca uma peça em uma casa vazia.
		tabuleiro.colocarPeca(new Posicao(1, 1), new Palhaco(null));

		// Coloca uma peça em uma casa OCUPADA.
		tabuleiro.colocarPeca(new Posicao(1, 1), new Palhaco(null));
	}

	@Test
	public void estaVazio() throws CasaOcupadaException {
		// Adiciona uma peça ao tabuleiro.
		tabuleiro.colocarPeca(new Posicao(1, 1), new Palhaco(null));

		// Testa a casa ocupada.
		Assert.assertFalse(tabuleiro.estaVazio(new Posicao(1, 1)));

		// Testa uma casa vazia.
		Assert.assertTrue(tabuleiro.estaVazio(new Posicao(2, 2)));
	}
}
