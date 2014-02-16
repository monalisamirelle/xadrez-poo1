package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifes.poo1.cln.cdp.pecas.Peao;
import br.edu.ifes.poo1.cln.cdp.pecas.Rei;

public class TesteRei {
	private TabuleiroXadrez tabuleiro;
	private Rei reiBranco;

	@Before
	public void before() throws CasaOcupadaException {
		// Inicia um tabuleiro vazio.
		tabuleiro = new TabuleiroXadrez();
		
		// Inicia o rei.
		reiBranco = new Rei(TipoCorJogador.BRANCO);

		// Colocar rei no tabuleiro
		tabuleiro.colocarPeca(new Posicao(4, 4), new Rei(TipoCorJogador.BRANCO));

		// Coloca peças inimigas no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(6, 6), new Peao(TipoCorJogador.PRETO));
		tabuleiro.colocarPeca(new Posicao(4, 3), new Peao(TipoCorJogador.PRETO));
		tabuleiro.colocarPeca(new Posicao(4, 5), new Peao(TipoCorJogador.PRETO));

		// Coloca peça aliada no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(3, 3), new Peao(TipoCorJogador.BRANCO));
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

		/**
		 * Muda rei de posição
		 */
		tabuleiro.retirarPeca(new Posicao(4, 4));
		tabuleiro.colocarPeca(new Posicao(2, 2), reiBranco);

		/**
		 * Verifica se rei pode atacar uma peça que está o ameaçando
		 */
		tabuleiro.colocarPeca(new Posicao(1, 3), new Peao(TipoCorJogador.PRETO));
		Assert.assertTrue(reiBranco.podeAtacar(new Posicao(2, 2), new Posicao(
				1, 3), tabuleiro));

		/**
		 * Verifica se rei pode atacar uma peça que está o ameaçando e está
		 * protegida
		 */
		tabuleiro.colocarPeca(new Posicao(2, 4), new Peao(TipoCorJogador.PRETO));
		Assert.assertFalse(reiBranco.podeAtacar(new Posicao(2, 2), new Posicao(
				1, 3), tabuleiro));

	}
}