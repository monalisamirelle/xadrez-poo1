package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteCavalo {
	Tabuleiro tabuleiro;
	Cavalo cavaloBranco;
	Peao peaoPreto;
	Peao peaoBranco;

	@Before
	public void before() throws CasaOcupadaException {
		tabuleiro = new Tabuleiro();
		cavaloBranco = new Cavalo(new Jogador(CorJogador.BRANCO, tabuleiro));
		peaoPreto = new Peao(new Jogador(CorJogador.PRETO, tabuleiro));

		/** Coloca peças inimigas no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(6, 4), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(8, 8), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(5, 3), peaoPreto);
		
		/** Coloca peça aliada no tabuleiro */
		tabuleiro.colocarPeca(new Posicao(2, 2), peaoBranco);
	}

	@Test
	public void podeAndar() {

		/** Verifica se o cavalo pode realizar determinado movimento */
		Assert.assertTrue(cavaloBranco.podeAndar(new Posicao(4, 4),
				new Posicao(5, 6), tabuleiro));
		Assert.assertTrue(cavaloBranco.podeAndar(new Posicao(4, 4),
				new Posicao(5, 2), tabuleiro));
		Assert.assertTrue(cavaloBranco.podeAndar(new Posicao(4, 4),
				new Posicao(6, 5), tabuleiro));
		Assert.assertTrue(cavaloBranco.podeAndar(new Posicao(4, 4),
				new Posicao(6, 3), tabuleiro));
		Assert.assertTrue(cavaloBranco.podeAndar(new Posicao(4, 4),
				new Posicao(3, 6), tabuleiro));
		Assert.assertTrue(cavaloBranco.podeAndar(new Posicao(4, 4),
				new Posicao(3, 2), tabuleiro));
		Assert.assertTrue(cavaloBranco.podeAndar(new Posicao(4, 4),
				new Posicao(2, 3), tabuleiro));
		Assert.assertTrue(cavaloBranco.podeAndar(new Posicao(4, 4),
				new Posicao(2, 5), tabuleiro));
	}

	@Test
	public void podeAtacar() {

		/** Verifica se o cavalo pode realizar determinado ataque */
		Assert.assertTrue(cavaloBranco.podeAtacar(new Posicao(4, 3),
				new Posicao(6, 4), tabuleiro));
		Assert.assertTrue(cavaloBranco.podeAtacar(new Posicao(7, 6),
				new Posicao(8, 8), tabuleiro));
		Assert.assertTrue(cavaloBranco.podeAtacar(new Posicao(6, 5),
				new Posicao(5, 3), tabuleiro));
		
		/** Verifica se o cavalo pode atacar uma posição vazia */
		Assert.assertFalse(cavaloBranco.podeAtacar(new Posicao(4, 4),
				new Posicao(6, 5), tabuleiro));
		Assert.assertFalse(cavaloBranco.podeAtacar(new Posicao(8, 3),
				new Posicao(6, 2), tabuleiro));
		Assert.assertFalse(cavaloBranco.podeAtacar(new Posicao(4, 4),
				new Posicao(3, 6), tabuleiro));
		
		/** Verifica se o cavalo pode atacar uma posição com peça aliada */
		Assert.assertFalse(cavaloBranco.podeAtacar(new Posicao(3, 4),
				new Posicao(2, 2), tabuleiro));	
	}
}
