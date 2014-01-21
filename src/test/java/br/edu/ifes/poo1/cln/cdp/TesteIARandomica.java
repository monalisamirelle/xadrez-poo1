package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteIARandomica {

	Tabuleiro tabuleiro;
	IARandomica ia;

	Peca reiBranco;
	Peca reiPreto;
	Peca torreBranca;
	Peca torrePreta;
	Peca cavaloBranco;
	Peca cavaloPreto;
	Peca bispoPreto;
	Peca rainhaPreta;
	Peca rainhaBranca;
	Peca peaoBranco;
	Peca peaoPreto;

	@Before
	public void before() {
		ia = new IARandomica("", CorJogador.PRETO);
		tabuleiro = new Tabuleiro();

		reiBranco = new Rei(CorJogador.BRANCO);
		torreBranca = new Torre(CorJogador.BRANCO);
		reiPreto = new Rei(CorJogador.PRETO);
		bispoPreto = new Bispo(CorJogador.PRETO);
		rainhaPreta = new Rainha(CorJogador.PRETO);
		rainhaBranca = new Rainha(CorJogador.BRANCO);
		peaoBranco = new Peao(CorJogador.BRANCO);
		peaoPreto = new Peao(CorJogador.PRETO);

	}

	@Test
	public void escolherJogada() throws CasaOcupadaException,
			CloneNotSupportedException, JogadaInvalidaException, InterruptedException {

		// Coloca a peça no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(1, 1), reiBranco);
		tabuleiro.colocarPeca(new Posicao(8, 8), reiPreto);
		tabuleiro.colocarPeca(new Posicao(2, 6), torreBranca);
		tabuleiro.colocarPeca(new Posicao(3, 7), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(3, 5), peaoBranco);
		tabuleiro.colocarPeca(new Posicao(4,7), rainhaPreta);
		tabuleiro.colocarPeca(new Posicao(2,3), rainhaBranca);
		tabuleiro.colocarPeca(new Posicao(4,3), bispoPreto);

		Assert.assertNotNull(ia.escolherJogada(tabuleiro));
	}

}
