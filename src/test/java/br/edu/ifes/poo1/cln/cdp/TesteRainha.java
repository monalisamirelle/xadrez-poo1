package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteRainha {
	Tabuleiro tabuleiro;
	Rainha rainhaBranca;
	Peao peaoPreto;
	Peao peaoBranco;

	@Before
	public void before() throws CasaOcupadaException {
		tabuleiro = new Tabuleiro();
		rainhaBranca = new Rainha(new Jogador(CorJogador.BRANCO, tabuleiro));
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
		/** Verifica se a rainha pode realizar determinado movimento */
		Assert.assertTrue(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(5, 5), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(4, 8), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(7, 7), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAndar(new Posicao(4, 4),
				new Posicao(6, 6), tabuleiro));
	}

	@Test
	public void podeAtacar() {

		/** Verifica se a rainha pode realizar determinado ataque */
		Assert.assertTrue(rainhaBranca.podeAtacar(new Posicao(6, 3),
				new Posicao(6, 4), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAtacar(new Posicao(6, 6),
				new Posicao(8, 8), tabuleiro));
		Assert.assertTrue(rainhaBranca.podeAtacar(new Posicao(6, 3),
				new Posicao(5, 3), tabuleiro));

		/** Verifica se a rainha pode atacar uma posição vazia */
		Assert.assertFalse(rainhaBranca.podeAtacar(new Posicao(6, 3),
				new Posicao(6, 5), tabuleiro));
		Assert.assertFalse(rainhaBranca.podeAtacar(new Posicao(6, 3),
				new Posicao(6, 2), tabuleiro));
		Assert.assertFalse(rainhaBranca.podeAtacar(new Posicao(6, 3),
				new Posicao(3, 6), tabuleiro));

		/** Verifica se a rainha pode atacar uma posição com peça aliada */
		Assert.assertFalse(rainhaBranca.podeAtacar(new Posicao(4, 4),
				new Posicao(2, 2), tabuleiro));
	}
}
