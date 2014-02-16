package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifes.poo1.cln.cdp.pecas.Peao;
import br.edu.ifes.poo1.cln.cdp.pecas.Torre;

public class TesteTorre {
	Tabuleiro tabuleiro;
	Torre torreBranca;
	Peao peaoPreto;
	Peao peaoBranco;

	@Before
	public void before() throws CasaOcupadaException {
		tabuleiro = new Tabuleiro();
		torreBranca = new Torre(TipoCorJogador.BRANCO);
		peaoPreto = new Peao(TipoCorJogador.PRETO);
		peaoBranco = new Peao(TipoCorJogador.BRANCO);

		/** Coloca peças inimigas no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(6, 6), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(8, 8), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(5, 1), peaoPreto);

		/** Coloca peça aliada no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(2, 2), peaoBranco);
	}

	@Test
	public void podeAndar() throws CasaOcupadaException {

		/** Verifica se a torre pode realizar determinado movimento */
		Assert.assertTrue(torreBranca.podeAndar(new Posicao(4, 4), new Posicao(
				4, 8), tabuleiro));
		Assert.assertTrue(torreBranca.podeAndar(new Posicao(4, 4), new Posicao(
				4, 1), tabuleiro));
		Assert.assertTrue(torreBranca.podeAndar(new Posicao(4, 4), new Posicao(
				8, 4), tabuleiro));
		Assert.assertTrue(torreBranca.podeAndar(new Posicao(4, 4), new Posicao(
				1, 4), tabuleiro));
		Assert.assertTrue(torreBranca.podeAndar(new Posicao(5, 5), new Posicao(
				5, 7), tabuleiro));
		Assert.assertTrue(torreBranca.podeAndar(new Posicao(5, 5), new Posicao(
				5, 2), tabuleiro));
		Assert.assertTrue(torreBranca.podeAndar(new Posicao(5, 5), new Posicao(
				7, 5), tabuleiro));
		Assert.assertTrue(torreBranca.podeAndar(new Posicao(5, 5), new Posicao(
				2, 5), tabuleiro));
	}

	@Test
	public void podeAtacar() throws CasaOcupadaException {

		/** Verifica se a torre pode realizar determinado ataque */
		Assert.assertTrue(torreBranca.podeAtacar(new Posicao(8, 6),
				new Posicao(6, 6), tabuleiro));
		Assert.assertTrue(torreBranca.podeAtacar(new Posicao(8, 6),
				new Posicao(8, 8), tabuleiro));
		Assert.assertTrue(torreBranca.podeAtacar(new Posicao(1, 1),
				new Posicao(5, 1), tabuleiro));
		 
		
		/** Verifica se a torre pode atacar uma posição vazia. */
		Assert.assertFalse(torreBranca.podeAtacar(new Posicao(6, 7),
				new Posicao(6, 8), tabuleiro));
		Assert.assertFalse(torreBranca.podeAtacar(new Posicao(8, 2),
				new Posicao(6, 2), tabuleiro));
		Assert.assertFalse(torreBranca.podeAtacar(new Posicao(3, 8),
				new Posicao(3, 6), tabuleiro));

		/**
		 * Verifica se a torre pode atacar uma posição com peça aliada.
		 */
		Assert.assertFalse(torreBranca.podeAtacar(new Posicao(2, 5),
				new Posicao(2, 2), tabuleiro));
	}
}
