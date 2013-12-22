package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteRei {
	private Tabuleiro tabuleiro;
	private Rei reiBranco;
	private Jogador brancas;
	private Jogador pretas;

	@Before
	public void before() throws CasaOcupadaException {
		// Inicia um tabuleiro vazio.
		tabuleiro = new Tabuleiro();

		// Inicia os jogadores.
		brancas = new Jogador("TesteBranco", CorJogador.BRANCO);
		pretas = new Jogador("TestePreto", CorJogador.PRETO);

		// Inicia o rei.
		reiBranco = new Rei(new Jogador("Teste", CorJogador.BRANCO));

		// Coloca peças inimigas no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(6, 6), new Peao(pretas));
		tabuleiro.colocarPeca(new Posicao(8, 8), new Peao(pretas));
		tabuleiro.colocarPeca(new Posicao(5, 1), new Peao(pretas));

		// Coloca peça aliada no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(2, 2), new Peao(brancas));
	}

	@Test
	public void podeAndar() {

		// Verifica se o rei pode realizar determinado movimento, para as
		// localidas ao redor dele. Exceto o canto superior esquerdo, que está
		// ameaçado.
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(4,
				3), tabuleiro));
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(4,
				5), tabuleiro));
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(3,
				4), tabuleiro));
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(5,
				4), tabuleiro));
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(5,
				3), tabuleiro));
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(3,
				5), tabuleiro));
		Assert.assertTrue(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(3,
				3), tabuleiro));

		// Verifica se o rei se moverá para o canto superior esquerdo (posição
		// que está sendo ameaçada).
		Assert.assertFalse(reiBranco.podeAndar(new Posicao(4, 4), new Posicao(
				5, 5), tabuleiro));
	}

	@Test
	public void podeAtacar() {

		/** Verifica se o rei pode realizar determinado ataque */
		Assert.assertTrue(reiBranco.podeAtacar(new Posicao(7, 5), new Posicao(
				6, 6), tabuleiro));
		Assert.assertTrue(reiBranco.podeAtacar(new Posicao(8, 7), new Posicao(
				8, 8), tabuleiro));
		Assert.assertTrue(reiBranco.podeAtacar(new Posicao(4, 1), new Posicao(
				5, 1), tabuleiro));

		/**
		 * Verifica se o rei pode atacar uma posição vazia.
		 * 
		 * Pode porque o método podeAtacar(..) não deve fazer verificação do que
		 * está na casa e destino.
		 */
		Assert.assertTrue(reiBranco.podeAtacar(new Posicao(6, 4), new Posicao(
				6, 5), tabuleiro));
		Assert.assertTrue(reiBranco.podeAtacar(new Posicao(7, 3), new Posicao(
				6, 2), tabuleiro));
		Assert.assertTrue(reiBranco.podeAtacar(new Posicao(2, 6), new Posicao(
				3, 6), tabuleiro));

		/**
		 * Verifica se o rei pode atacar uma posição com peça aliada.
		 * 
		 * Pode porque o método podeAtacar(..) não deve fazer verificação do que
		 * está na casa e destino.
		 */
		Assert.assertTrue(reiBranco.podeAtacar(new Posicao(3, 3), new Posicao(
				2, 2), tabuleiro));
	}
}