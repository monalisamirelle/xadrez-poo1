package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteBispo {
	Tabuleiro tabuleiro;
	Bispo bispoBranco;
	Peao peaoPreto;
	Peao peaoBranco;

	@Before
	public void before() throws CasaOcupadaException {
		tabuleiro = new Tabuleiro();
		bispoBranco = new Bispo(new Jogador(CorJogador.BRANCO, tabuleiro));
		peaoPreto = new Peao(new Jogador(CorJogador.PRETO, tabuleiro));
		peaoBranco = new Peao(new Jogador(CorJogador.BRANCO, tabuleiro));

		/** Coloca peças inimigas no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(6, 4), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(8, 8), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(5, 3), peaoPreto);

		/** Coloca peça aliada no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(2, 2), peaoBranco);
	}

	@Test
	public void podeAndar() {

		/**
		 * Verifica se o bispo pode realizar determinado movimento (não se
		 * importando em diferenciar bispos de casas pretas e brancas)
		 */
		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(4, 4), new Posicao(
				5, 5), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(4, 4), new Posicao(
				7, 7), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(4, 4), new Posicao(
				3, 5), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(5, 8), new Posicao(
				3, 6), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(5, 8), new Posicao(
				6, 7), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(5, 8), new Posicao(
				7, 6), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(8, 1), new Posicao(
				1, 8), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAndar(new Posicao(7, 7), new Posicao(
				5, 5), tabuleiro));
	}

	@Test
	public void podeAtacar() {

		/** Verifica se o bispo pode realizar determinado ataque */
		Assert.assertTrue(bispoBranco.podeAtacar(new Posicao(7, 3),
				new Posicao(6, 4), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAtacar(new Posicao(6, 6),
				new Posicao(8, 8), tabuleiro));
		Assert.assertTrue(bispoBranco.podeAtacar(new Posicao(3, 5),
				new Posicao(5, 3), tabuleiro));
		
		/** Verifica se o bispo pode atacar uma posição vazia */
		Assert.assertFalse(bispoBranco.podeAtacar(new Posicao(3, 7),
				new Posicao(6, 5), tabuleiro));
		Assert.assertFalse(bispoBranco.podeAtacar(new Posicao(8, 4),
				new Posicao(6, 2), tabuleiro));
		Assert.assertFalse(bispoBranco.podeAtacar(new Posicao(1, 8),
				new Posicao(3, 6), tabuleiro));

		/** Verifica se o bispo pode atacar uma posição com peça aliada */
		Assert.assertFalse(bispoBranco.podeAtacar(new Posicao(5, 5),
				new Posicao(2, 2), tabuleiro));
	}
}
