package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestePeao {
	Tabuleiro tabuleiro;
	Peao peaoBranco;
	Peao peaoBrancoMovimentado;
	Peao peaoPreto;
	Cavalo cavaloBranco;

	@Before
	public void before() throws CasaOcupadaException {
		tabuleiro = new Tabuleiro();
		peaoBranco = new Peao(new Jogador("Teste", CorJogador.BRANCO));
		peaoBrancoMovimentado = new Peao(
				new Jogador("Teste", CorJogador.BRANCO));
		peaoBrancoMovimentado.setJaMoveu();
		peaoPreto = new Peao(new Jogador("Teste", CorJogador.PRETO));
		cavaloBranco = new Cavalo(new Jogador("Teste", CorJogador.BRANCO));

		/** Coloca peças inimigas no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(6, 6), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(8, 7), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(5, 2), peaoPreto);

		/** Coloca peça aliada no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(3, 3), cavaloBranco);
	}

	@Test
	public void podeAndar() {

		/** Verifica se o peão pode realizar determinado movimento */
		Assert.assertTrue(peaoBranco.podeAndar(new Posicao(2, 2), new Posicao(
				2, 3), tabuleiro));
		Assert.assertTrue(peaoBranco.podeAndar(new Posicao(4, 4), new Posicao(
				4, 5), tabuleiro));

		/**
		 * Verifica se o peão pode avançar duas casas (caso nunca tenha se
		 * movido)
		 */
		Assert.assertTrue(peaoBranco.podeAndar(new Posicao(2, 2), new Posicao(
				2, 4), tabuleiro));

		/** Verifica se o peão pode avançar duas casas (caso tenha se movido) */
		Assert.assertFalse(peaoBrancoMovimentado.podeAndar(new Posicao(2, 2),
				new Posicao(2, 4), tabuleiro));

		/**
		 * Verifica se o peão pode avançar se houver uma peça inimiga na sua
		 * frente
		 */
		Assert.assertFalse(peaoBranco.podeAndar(new Posicao(8, 6), new Posicao(
				8, 7), tabuleiro));

		/**
		 * Verifica se o peão pode avançar se houver uma peça aliada na sua
		 * frente
		 */
		Assert.assertFalse(peaoBranco.podeAndar(new Posicao(3, 2), new Posicao(
				3, 3), tabuleiro));
	}

	@Test
	public void podeAtacar() {

		/** Verifica se o peão pode realizar determinado ataque de frente */
		Assert.assertFalse(peaoBranco.podeAtacar(new Posicao(6, 5),
				new Posicao(6, 6), tabuleiro));
		Assert.assertFalse(peaoBranco.podeAtacar(new Posicao(8, 6),
				new Posicao(8, 7), tabuleiro));
		Assert.assertFalse(peaoBranco.podeAtacar(new Posicao(5, 1),
				new Posicao(5, 2), tabuleiro));

		/** Verifica se o peão pode realizar determinado ataque em diagonal */
		Assert.assertTrue(peaoBranco.podeAtacar(new Posicao(5, 5), new Posicao(
				6, 6), tabuleiro));
		Assert.assertTrue(peaoBranco.podeAtacar(new Posicao(7, 6), new Posicao(
				8, 7), tabuleiro));
		Assert.assertTrue(peaoBranco.podeAtacar(new Posicao(6, 1), new Posicao(
				5, 2), tabuleiro));
		Assert.assertTrue(peaoBranco.podeAtacar(new Posicao(4, 1), new Posicao(
				5, 2), tabuleiro));

		/** Verifica se o peão pode atacar uma posição vazia de frente */
		Assert.assertFalse(peaoBranco.podeAtacar(new Posicao(6, 4),
				new Posicao(6, 5), tabuleiro));

		/** Verifica se o peão pode atacar uma posição vazia em diagonal */
		Assert.assertFalse(peaoBranco.podeAtacar(new Posicao(7, 3),
				new Posicao(8, 4), tabuleiro));

		/** Verifica se o peao pode atacar uma posição com peça aliada de frente */
		Assert.assertFalse(peaoBranco.podeAtacar(new Posicao(3, 2),
				new Posicao(3, 3), tabuleiro));
		/**
		 * Verifica se o peao pode atacar uma posição com peça aliada em
		 * diagonal
		 */
		Assert.assertFalse(peaoBranco.podeAtacar(new Posicao(2, 2),
				new Posicao(3, 3), tabuleiro));
	}
}