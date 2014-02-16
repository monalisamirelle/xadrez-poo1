package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifes.poo1.cln.cdp.ia.IARandomica;
import br.edu.ifes.poo1.cln.cdp.pecas.Bispo;
import br.edu.ifes.poo1.cln.cdp.pecas.Peao;
import br.edu.ifes.poo1.cln.cdp.pecas.Peca;
import br.edu.ifes.poo1.cln.cdp.pecas.Rainha;
import br.edu.ifes.poo1.cln.cdp.pecas.Rei;
import br.edu.ifes.poo1.cln.cdp.pecas.Torre;

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
		ia = new IARandomica("", TipoCorJogador.PRETO);
		tabuleiro = new Tabuleiro();

		reiBranco = new Rei(TipoCorJogador.BRANCO);
		torreBranca = new Torre(TipoCorJogador.BRANCO);
		reiPreto = new Rei(TipoCorJogador.PRETO);
		bispoPreto = new Bispo(TipoCorJogador.PRETO);
		rainhaPreta = new Rainha(TipoCorJogador.PRETO);
		rainhaBranca = new Rainha(TipoCorJogador.BRANCO);
		peaoBranco = new Peao(TipoCorJogador.BRANCO);
		peaoPreto = new Peao(TipoCorJogador.PRETO);

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
