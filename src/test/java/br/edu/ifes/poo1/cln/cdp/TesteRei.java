package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteRei {
	Tabuleiro tabuleiro;
	Rei reiBranco;
	Peao peaoPreto;
	Peao peaoBranco;

	@Before
	public void before() throws CasaOcupadaException {
		tabuleiro = new Tabuleiro();
		reiBranco = new Rei(new Jogador(CorJogador.BRANCO, tabuleiro));
		peaoPreto = new Peao(new Jogador(CorJogador.PRETO, tabuleiro));
		peaoBranco = new Peao(new Jogador(CorJogador.BRANCO, tabuleiro));
		
		/** Coloca peças inimigas no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(6, 6), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(8, 8), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(5, 1), peaoPreto);

		/** Coloca peça aliada no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(2, 2), peaoBranco);
	}

	@Test
	public void podeAndar() {

		/** Verifica se o rei pode realizar determinado movimento */
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(
				4, 3), tabuleiro));
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(
				4, 5), tabuleiro));
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(
				3, 4), tabuleiro));
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(
				5, 4), tabuleiro));
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(
				5, 5), tabuleiro));
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(
				5, 3), tabuleiro));
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(
				3, 5), tabuleiro));
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(
				3, 3), tabuleiro));
	}

	@Test
	public void podeAtacar() {

		/** Verifica se o rei pode realizar determinado ataque */
		Assert.assertTrue(reiBranco.podeAtacar(new Posicao(7, 5),
				new Posicao(6, 6), tabuleiro));
		Assert.assertTrue(reiBranco.podeAtacar(new Posicao(8, 7),
				new Posicao(8, 8), tabuleiro));
		Assert.assertTrue(reiBranco.podeAtacar(new Posicao(4, 1),
				new Posicao(5, 1), tabuleiro));
		
		/** Verifica se o rei pode atacar uma posição vazia */
		Assert.assertFalse(reiBranco.podeAtacar(new Posicao(6, 4),
				new Posicao(6, 5), tabuleiro));
		Assert.assertFalse(reiBranco.podeAtacar(new Posicao(7, 3),
				new Posicao(6, 2), tabuleiro));
		Assert.assertFalse(reiBranco.podeAtacar(new Posicao(2, 6),
				new Posicao(3, 6), tabuleiro));

		/** Verifica se o rei pode atacar uma posição com peça aliada */
		Assert.assertFalse(reiBranco.podeAtacar(new Posicao(3, 3),
				new Posicao(2, 2), tabuleiro));
	}
}