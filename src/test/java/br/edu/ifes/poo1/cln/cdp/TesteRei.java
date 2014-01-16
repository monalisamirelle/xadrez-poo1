package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteRei {
	private Tabuleiro tabuleiro;
	private Rei reiBranco;
	private Jogador brancas;
	private Jogador pretas;

	@Before
	public void before() throws CasaOcupadaException {
		// Inicia um tabuleiro vazio.
		tabuleiro = new Tabuleiro();

		// Inicia os jogadores.
		brancas = new Jogador("TesteBranco", CorJogador.BRANCO);
		pretas = new Jogador("TestePreto", CorJogador.PRETO);

		// Inicia o rei.
		reiBranco = new Rei(CorJogador.BRANCO);

		// Colocar rei no tabuleiro
		tabuleiro.colocarPeca(new Posicao(4, 4), new Rei(brancas.getCor()));

		// Coloca peças inimigas no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(6, 6), new Peao(pretas.getCor()));
		tabuleiro.colocarPeca(new Posicao(4, 3), new Peao(pretas.getCor()));
		tabuleiro.colocarPeca(new Posicao(4, 5), new Peao(pretas.getCor()));

		// Coloca peça aliada no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(3, 3), new Peao(brancas.getCor()));
	}

	@Test
	public void podeAndar() throws CasaOcupadaException {

		// Verifica se o rei pode realizar determinado movimento, para as
		// localidas ao redor dele.
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(5,
				3), tabuleiro));
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(3,
				5), tabuleiro));

		// Verifica se o rei se moverá para uma posição que está sendo ameaçada.
		Assert.assertFalse(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(
				5, 5), tabuleiro));
		Assert.assertFalse(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(
				3, 4), tabuleiro));
		Assert.assertFalse(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(
				5, 4), tabuleiro));

	}

	@Test
	public void podeAtacar() throws CasaOcupadaException {

		/** Verifica se o rei pode realizar determinado ataque */
		Assert.assertTrue(reiBranco.podeAtacar(new Posicao(4, 4), new Posicao(
				4, 3), tabuleiro));
		Assert.assertTrue(reiBranco.podeAtacar(new Posicao(4, 4), new Posicao(
				4, 5), tabuleiro));

		/**
		 * Verifica se o rei pode atacar uma posição vazia.
		 */
		Assert.assertFalse(reiBranco.podeAtacar(new Posicao(4, 4), new Posicao(
				5, 4), tabuleiro));
		Assert.assertFalse(reiBranco.podeAtacar(new Posicao(4, 4), new Posicao(
				5, 3), tabuleiro));
		Assert.assertFalse(reiBranco.podeAtacar(new Posicao(4, 4), new Posicao(
				3, 5), tabuleiro));

		/**
		 * Verifica se o rei pode atacar uma posição com peça aliada.
		 */
		Assert.assertFalse(reiBranco.podeAtacar(new Posicao(4, 4), new Posicao(
				3, 3), tabuleiro));
	}
}