package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteIAElaborada {

	Tabuleiro tabuleiro;
	IAElaborada ia;
	// Pessoa pessoa;

	Peca reiBranco;
	Peca torreBranca;
	Peca reiPreto;
	Peca torrePreta;
	Peca cavaloBranco;
	Peca cavaloPreto;
	Peca bispoPreto;
	Peca rainhaPreta;

	@Before
	public void before() {
		ia = new IAElaborada("", CorJogador.PRETO, 7, 45);
		tabuleiro = new Tabuleiro();

		reiBranco = new Rei(new Jogador("TesteB", CorJogador.BRANCO));
		torreBranca = new Torre(new Jogador("TesteB", CorJogador.BRANCO));
		reiPreto = new Rei(new Jogador("TesteP", CorJogador.PRETO));
		torrePreta = new Torre(new Jogador("TesteP", CorJogador.PRETO));
		cavaloBranco = new Cavalo(new Jogador("TesteB", CorJogador.BRANCO));
		cavaloPreto = new Cavalo(new Jogador("TesteP", CorJogador.PRETO));
		bispoPreto = new Bispo(new Jogador("TesteP", CorJogador.PRETO));
		rainhaPreta = new Rainha(new Jogador("TesteP", CorJogador.PRETO));
	}

	@Test
	public void escolherJogada() throws CasaOcupadaException,
			CloneNotSupportedException, JogadaInvalidaException {

		// Coloca a peça no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(1, 8), torrePreta);
		tabuleiro.colocarPeca(new Posicao(1, 4), torrePreta);
		tabuleiro.colocarPeca(new Posicao(5, 8), reiPreto);
		tabuleiro.colocarPeca(new Posicao(5, 2), cavaloPreto);
		tabuleiro.colocarPeca(new Posicao(6, 4), cavaloPreto);
		tabuleiro.colocarPeca(new Posicao(8, 8), reiBranco);

		Assert.assertNotNull(ia.escolherJogada(tabuleiro));
	}

}
