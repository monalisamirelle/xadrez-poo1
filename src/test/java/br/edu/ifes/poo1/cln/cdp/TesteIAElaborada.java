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
		ia = new IAElaborada("", TipoCorJogador.PRETO, 3, 45, true);
		tabuleiro = new Tabuleiro();

		reiBranco = new Rei(TipoCorJogador.BRANCO);
		torreBranca = new Torre(TipoCorJogador.BRANCO);
		reiPreto = new Rei(TipoCorJogador.PRETO);
		torrePreta = new Torre(TipoCorJogador.PRETO);
		cavaloBranco = new Cavalo(TipoCorJogador.BRANCO);
		cavaloPreto = new Cavalo(TipoCorJogador.PRETO);
		bispoPreto = new Bispo(TipoCorJogador.PRETO);
		rainhaPreta = new Rainha(TipoCorJogador.PRETO);
		rainhaBranca = new Rainha(TipoCorJogador.BRANCO);
		peaoBranco = new Peao(TipoCorJogador.BRANCO);
		peaoPreto = new Peao(TipoCorJogador.PRETO);

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
		tabuleiro.colocarPeca(new Posicao(5, 1), reiBranco);
		tabuleiro.colocarPeca(new Posicao(1, 1), torreBranca);
		tabuleiro.colocarPeca(new Posicao(8, 2), torrePreta);
		tabuleiro.colocarPeca(new Posicao(7, 5), reiPreto);
		//tabuleiro.colocarPeca(new Posicao(7, 2), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(2,7), peaoPreto);
		//tabuleiro.colocarPeca(new Posicao(1,4), peaoPreto);
		//tabuleiro.colocarPeca(new Posicao(3,3), peaoBranco);
		//tabuleiro.colocarPeca(new Posicao(6,7), peaoPreto);
		
		Assert.assertNotNull(ia.escolherJogada(tabuleiro));
	}

}
