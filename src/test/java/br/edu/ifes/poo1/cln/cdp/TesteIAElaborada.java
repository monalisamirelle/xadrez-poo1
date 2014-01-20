package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteIAElaborada {

	Tabuleiro tabuleiro;
	IAElaborada ia;

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
		ia = new IAElaborada("", CorJogador.PRETO, 10, 45, true);
		tabuleiro = new Tabuleiro();

		reiBranco = new Rei(CorJogador.BRANCO);
		torreBranca = new Torre(CorJogador.BRANCO);
		reiPreto = new Rei(CorJogador.PRETO);
		torrePreta = new Torre(CorJogador.PRETO);
		cavaloBranco = new Cavalo(CorJogador.BRANCO);
		cavaloPreto = new Cavalo(CorJogador.PRETO);
		bispoPreto = new Bispo(CorJogador.PRETO);
		rainhaPreta = new Rainha(CorJogador.PRETO);
		rainhaBranca = new Rainha(CorJogador.BRANCO);
		peaoBranco = new Peao(CorJogador.BRANCO);
		peaoPreto = new Peao(CorJogador.PRETO);

	}

	@Test
	public void escolherJogada() throws CasaOcupadaException,
			CloneNotSupportedException, JogadaInvalidaException, InterruptedException {

		/**
		 * O teste a seguir apenas não funcionará em duas situações: 
		 * 1) Houverem peças no tabuleiro, mas não existir algum dos reis 
		 * 2) A análise começar já estando, da última jogada do jogador humano, o seu rei em
		 * xeque, ou seja, o jogador humano ter realizado uma jogada que o coloca em xeque
		 * Como ambas as situações são atípicas e impossíveis de ocorrer
		 * no xadrez, ambas as situações não são tratadas. Contudo, ao forçar
		 * tais situações em testes você receberá um erro.
		 */

		// Coloca a peça no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(1, 1), reiBranco);
		//tabuleiro.colocarPeca(new Posicao(7, 1), torreBranca);
		tabuleiro.colocarPeca(new Posicao(2, 3), torrePreta);
		//tabuleiro.colocarPeca(new Posicao(6, 5), cavaloPreto);
		//tabuleiro.colocarPeca(new Posicao(8, 5), cavaloPreto);
		tabuleiro.colocarPeca(new Posicao(8, 8), reiPreto);
		//tabuleiro.colocarPeca(new Posicao(1, 8), torreBranca);
		//tabuleiro.colocarPeca(new Posicao(2, 6), torreBranca);
		//tabuleiro.colocarPeca(new Posicao(3, 5), peaoPreto);
		//tabuleiro.colocarPeca(new Posicao(2,7), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(4,7), rainhaPreta);
		//tabuleiro.colocarPeca(new Posicao(2,3), rainhaBranca);
		//tabuleiro.colocarPeca(new Posicao(4,3), bispoPreto);

		Assert.assertNotNull(ia.escolherJogada(tabuleiro));
	}

}
