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
	Peca peaoBranco;
	Peca peaoPreto;
	
	@Before
	public void before() {
		ia = new IAElaborada("", CorJogador.PRETO, 3, 45);
		tabuleiro = new Tabuleiro();

		reiBranco = new Rei(CorJogador.BRANCO);
		torreBranca = new Torre(CorJogador.BRANCO);
		reiPreto = new Rei(CorJogador.PRETO);
		torrePreta = new Torre(CorJogador.PRETO);
		cavaloBranco = new Cavalo(CorJogador.BRANCO);
		cavaloPreto = new Cavalo(CorJogador.PRETO);
		bispoPreto = new Bispo(CorJogador.PRETO);
		rainhaPreta = new Rainha(CorJogador.PRETO);
		peaoBranco = new Peao(CorJogador.BRANCO);
		peaoPreto = new Peao(CorJogador.PRETO);
		
	}

	@Test
	public void escolherJogada() throws CasaOcupadaException,
			CloneNotSupportedException, JogadaInvalidaException {

		// Coloca a peça no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(2, 1), reiPreto);
		//tabuleiro.colocarPeca(new Posicao(7, 5), torrePreta);
		//tabuleiro.colocarPeca(new Posicao(6, 5), cavaloPreto);
		tabuleiro.colocarPeca(new Posicao(8, 8), reiBranco);
		tabuleiro.colocarPeca(new Posicao(3, 7), torreBranca);
		tabuleiro.colocarPeca(new Posicao(2, 8), torreBranca);
		//tabuleiro.colocarPeca(new Posicao(3,5), peaoBranco);
		//tabuleiro.colocarPeca(new Posicao(2,7), peaoPreto);
		
		// FIXME onde está o rei Branco?
		Assert.assertNotNull(ia.escolherJogada(tabuleiro));
	}

}
