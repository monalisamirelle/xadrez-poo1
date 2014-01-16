package br.edu.ifes.poo1.cln.cdp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteTabuleiro {

	Tabuleiro tabuleiro;

	Peca reiBranco;
	Peca torreBranca;
	Peca reiPreto;
	Peca torrePreta;
	Peca cavaloBranco;
	Peca cavaloPreto;
	Peao peaoBranco;
	Peao peaoPreto;

	@Before
	public void before() {
		tabuleiro = new Tabuleiro();

		reiBranco = new Rei(CorJogador.BRANCO);
		torreBranca = new Torre(CorJogador.BRANCO);
		reiPreto = new Rei(CorJogador.PRETO);
		torrePreta = new Torre(CorJogador.PRETO);
		cavaloBranco = new Cavalo(CorJogador.BRANCO);
		cavaloPreto = new Cavalo(CorJogador.PRETO);
		peaoBranco = new Peao(CorJogador.BRANCO);
		peaoPreto = new Peao(CorJogador.PRETO);
	}

	@Test
	public void espiarPeca() throws CasaOcupadaException {
		// Instancia uma peça qualquer.
		Peca peca = reiBranco;

		// Coloca a peça no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(1, 1), peca);

		// A mesma peça deve ser lida.
		Assert.assertEquals(peca, tabuleiro.espiarPeca(new Posicao(1, 1)));

		// E ela deve permanecer no tabuleiro.
		Assert.assertEquals(peca, tabuleiro.espiarPeca(new Posicao(1, 1)));
	}

	@Test
	public void retirarPeca() throws CasaOcupadaException {
		// Instancia uma peça qualquer.
		Peca peca = reiBranco;

		// Coloca a peça no tabuleiro.
		tabuleiro.colocarPeca(new Posicao(1, 1), peca);

		// A mesma peça deve ser retirada.
		Assert.assertEquals(peca, tabuleiro.retirarPeca(new Posicao(1, 1)));

		// E ela não deve mais estar no tabuleiro.
		Assert.assertEquals(null, tabuleiro.retirarPeca(new Posicao(1, 1)));
	}

	@Test
	public void colocarPeca_emCasaVazia() throws CasaOcupadaException {
		// Coloca uma peça em uma casa vazia.
		tabuleiro.colocarPeca(new Posicao(1, 1), reiBranco);
	}

	@Test(expected = CasaOcupadaException.class)
	public void colocarPeca_emCasaOcupada() throws CasaOcupadaException {
		// Coloca uma peça em uma casa vazia.
		tabuleiro.colocarPeca(new Posicao(1, 1), reiBranco);

		// Coloca uma peça em uma casa OCUPADA.
		tabuleiro.colocarPeca(new Posicao(1, 1), reiBranco);
	}

	@Test
	public void estaVazio() throws CasaOcupadaException {
		// Adiciona uma peça ao tabuleiro.
		tabuleiro.colocarPeca(new Posicao(1, 1), reiBranco);

		// Testa a casa ocupada.
		Assert.assertFalse(tabuleiro.estaVazio(new Posicao(1, 1)));

		// Testa uma casa vazia.
		Assert.assertTrue(tabuleiro.estaVazio(new Posicao(2, 2)));
	}

	@Test
	public void atravessouTabuleiro() {
		// Testa os limites do tabuleiro
		Assert.assertFalse(Tabuleiro.estaForaDoTabuleiro(new Posicao(1, 1)));
		Assert.assertFalse(Tabuleiro.estaForaDoTabuleiro(new Posicao(8, 8)));

		// Testa quando atravessa o tabuleiro
		Assert.assertTrue(Tabuleiro.estaForaDoTabuleiro(new Posicao(4, 9)));
		Assert.assertTrue(Tabuleiro.estaForaDoTabuleiro(new Posicao(4, 0)));
		Assert.assertTrue(Tabuleiro.estaForaDoTabuleiro(new Posicao(9, 5)));
		Assert.assertTrue(Tabuleiro.estaForaDoTabuleiro(new Posicao(0, 5)));
	}

	@Test
	public void podeRealizarMovimentacao() throws CasaOcupadaException {

		// Adiciona uma peça ao tabuleiro.
		tabuleiro.colocarPeca(new Posicao(2, 3), reiBranco);

		// Verifica se o caminho até a casa desejada é possível ou se irá se
		// encontrar algum obstáculo
		Assert.assertTrue(tabuleiro.podeRealizarMovimentacao(new Posicao(1, 1),
				new Posicao(8, 8)));
		Assert.assertTrue(tabuleiro.podeRealizarMovimentacao(new Posicao(4, 1),
				new Posicao(4, 8)));
		Assert.assertTrue(tabuleiro.podeRealizarMovimentacao(new Posicao(8, 1),
				new Posicao(1, 8)));
		Assert.assertTrue(tabuleiro.podeRealizarMovimentacao(new Posicao(1, 4),
				new Posicao(8, 4)));
		Assert.assertFalse(tabuleiro.podeRealizarMovimentacao(
				new Posicao(2, 1), new Posicao(2, 8)));
		Assert.assertFalse(tabuleiro.podeRealizarMovimentacao(
				new Posicao(1, 3), new Posicao(4, 3)));
	}

	@Test
	public void roqueMenor() throws CasaOcupadaException {
		// Testa Roque menor das peças brancas
		tabuleiro.colocarPeca(new Posicao(5, 1), reiBranco);
		tabuleiro.colocarPeca(new Posicao(8, 1), torreBranca);
		Assert.assertTrue(tabuleiro.ehRoqueMenor(CorJogador.BRANCO));

		// Testa Roque menor das peças brancas se houver uma peça no caminho
		tabuleiro.colocarPeca(new Posicao(6, 1), cavaloBranco);
		Assert.assertFalse(tabuleiro.ehRoqueMenor(CorJogador.BRANCO));
		tabuleiro.retirarPeca(new Posicao(6, 1));
		tabuleiro.colocarPeca(new Posicao(7, 1), cavaloBranco);
		Assert.assertFalse(tabuleiro.ehRoqueMenor(CorJogador.BRANCO));
		tabuleiro.retirarPeca(new Posicao(7, 1));

		// Testa Roque menor das peças brancas se rei já se moveu
		tabuleiro.espiarPeca(new Posicao(5, 1)).setJaMoveu();
		Assert.assertFalse(tabuleiro.ehRoqueMenor(CorJogador.BRANCO));
		tabuleiro.retirarPeca(new Posicao(5, 1));
		reiBranco = new Rei(CorJogador.BRANCO);
		tabuleiro.colocarPeca(new Posicao(5, 1), reiBranco);

		// Testa Roque menor das peças brancas se torre já se moveu
		tabuleiro.espiarPeca(new Posicao(8, 1)).setJaMoveu();
		Assert.assertFalse(tabuleiro.ehRoqueMenor(CorJogador.BRANCO));
		tabuleiro.retirarPeca(new Posicao(8, 1));
		torreBranca = new Torre(CorJogador.BRANCO);
		tabuleiro.colocarPeca(new Posicao(8, 1), torreBranca);

		// Testa Roque menor das peças pretas
		tabuleiro.colocarPeca(new Posicao(5, 8), reiPreto);
		tabuleiro.colocarPeca(new Posicao(8, 8), torrePreta);
		Assert.assertTrue(tabuleiro.ehRoqueMenor(CorJogador.PRETO));

		// Testa Roque menor das peças pretas se houver uma peça no caminho
		tabuleiro.colocarPeca(new Posicao(6, 8), cavaloPreto);
		Assert.assertFalse(tabuleiro.ehRoqueMenor(CorJogador.PRETO));
		tabuleiro.retirarPeca(new Posicao(6, 8));
		tabuleiro.colocarPeca(new Posicao(7, 8), cavaloPreto);
		Assert.assertFalse(tabuleiro.ehRoqueMenor(CorJogador.PRETO));
		tabuleiro.retirarPeca(new Posicao(7, 8));

		// Testa Roque menor das peças pretas se rei já se moveu
		tabuleiro.espiarPeca(new Posicao(5, 8)).setJaMoveu();
		Assert.assertFalse(tabuleiro.ehRoqueMenor(CorJogador.PRETO));
		tabuleiro.retirarPeca(new Posicao(5, 8));
		reiPreto = new Rei(CorJogador.BRANCO);
		tabuleiro.colocarPeca(new Posicao(5, 8), reiPreto);

		// Testa Roque menor das peças pretas se torre já se moveu
		tabuleiro.espiarPeca(new Posicao(8, 8)).setJaMoveu();
		Assert.assertFalse(tabuleiro.ehRoqueMenor(CorJogador.PRETO));
		tabuleiro.retirarPeca(new Posicao(8, 8));
		torrePreta = new Torre(CorJogador.PRETO);
		tabuleiro.colocarPeca(new Posicao(8, 8), torrePreta);

	}

	@Test
	public void roqueMaior() throws CasaOcupadaException {
		// Testa Roque menor das peças brancas
		tabuleiro.colocarPeca(new Posicao(5, 1), reiBranco);
		tabuleiro.colocarPeca(new Posicao(1, 1), torreBranca);
		Assert.assertTrue(tabuleiro.ehRoqueMaior(CorJogador.BRANCO));

		// Testa Roque menor das peças brancas se houver uma peça no caminho
		tabuleiro.colocarPeca(new Posicao(3, 1), cavaloBranco);
		Assert.assertFalse(tabuleiro.ehRoqueMaior(CorJogador.BRANCO));
		tabuleiro.retirarPeca(new Posicao(3, 1));
		tabuleiro.colocarPeca(new Posicao(4, 1), cavaloBranco);
		Assert.assertFalse(tabuleiro.ehRoqueMaior(CorJogador.BRANCO));
		tabuleiro.retirarPeca(new Posicao(4, 1));

		// Testa Roque menor das peças brancas se rei já se moveu
		tabuleiro.espiarPeca(new Posicao(5, 1)).setJaMoveu();
		Assert.assertFalse(tabuleiro.ehRoqueMaior(CorJogador.BRANCO));
		tabuleiro.retirarPeca(new Posicao(5, 1));
		reiBranco = new Rei(CorJogador.BRANCO);
		tabuleiro.colocarPeca(new Posicao(5, 1), reiBranco);

		// Testa Roque menor das peças brancas se torre já se moveu
		tabuleiro.espiarPeca(new Posicao(1, 1)).setJaMoveu();
		Assert.assertFalse(tabuleiro.ehRoqueMaior(CorJogador.BRANCO));
		tabuleiro.retirarPeca(new Posicao(1, 1));
		torreBranca = new Torre(CorJogador.BRANCO);
		tabuleiro.colocarPeca(new Posicao(1, 1), torreBranca);

		// Testa Roque menor das peças pretas
		tabuleiro.colocarPeca(new Posicao(5, 8), reiPreto);
		tabuleiro.colocarPeca(new Posicao(1, 8), torrePreta);
		Assert.assertTrue(tabuleiro.ehRoqueMaior(CorJogador.PRETO));

		// Testa Roque menor das peças pretas se houver uma peça no caminho
		tabuleiro.colocarPeca(new Posicao(3, 8), cavaloPreto);
		Assert.assertFalse(tabuleiro.ehRoqueMaior(CorJogador.PRETO));
		tabuleiro.retirarPeca(new Posicao(3, 8));
		tabuleiro.colocarPeca(new Posicao(4, 8), cavaloPreto);
		Assert.assertFalse(tabuleiro.ehRoqueMaior(CorJogador.PRETO));
		tabuleiro.retirarPeca(new Posicao(4, 8));

		// Testa Roque menor das peças pretas se rei já se moveu
		tabuleiro.espiarPeca(new Posicao(5, 8)).setJaMoveu();
		Assert.assertFalse(tabuleiro.ehRoqueMaior(CorJogador.PRETO));
		tabuleiro.retirarPeca(new Posicao(5, 8));
		reiPreto = new Rei(CorJogador.BRANCO);
		tabuleiro.colocarPeca(new Posicao(5, 8), reiPreto);

		// Testa Roque menor das peças pretas se torre já se moveu
		tabuleiro.espiarPeca(new Posicao(1, 8)).setJaMoveu();
		Assert.assertFalse(tabuleiro.ehRoqueMaior(CorJogador.PRETO));
		tabuleiro.retirarPeca(new Posicao(1, 8));
		torrePreta = new Torre(CorJogador.PRETO);
		tabuleiro.colocarPeca(new Posicao(1, 8), torrePreta);

	}

	@Test
	public void ehEnPassantEsquerda() throws CasaOcupadaException {
		tabuleiro.colocarPeca(new Posicao(4, 5), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(5, 5), peaoBranco);

		// O peão preto não pode sofrer En passant
		Assert.assertFalse(tabuleiro.ehEnPassantEsquerda(new Posicao(5, 5)));

		peaoPreto.setPodeEnPassant(true);

		// O peão preto pode sofrer En passant
		Assert.assertTrue(tabuleiro.ehEnPassantEsquerda(new Posicao(5, 5)));
	}

	@Test
	public void ehEnPassantDireita() throws CasaOcupadaException {
		tabuleiro.colocarPeca(new Posicao(4, 4), peaoPreto);
		tabuleiro.colocarPeca(new Posicao(5, 4), peaoBranco);

		// O peão preto não pode sofrer En passant
		Assert.assertFalse(tabuleiro.ehEnPassantDireita(new Posicao(4, 4)));

		peaoBranco.setPodeEnPassant(true);

		// O peão preto pode sofrer En passant
		Assert.assertTrue(tabuleiro.ehEnPassantDireita(new Posicao(4, 4)));
	}

	@Test
	public void resetaPodeEnPassant() throws CasaOcupadaException {
		// Colocamos uma peça que não sofreu en Passant
		tabuleiro.colocarPeca(new Posicao(2, 5), peaoPreto);
		Assert.assertFalse(peaoPreto.isPodeEnPassant());

		// Fazemos a peça poder ter En Passant
		peaoPreto.setPodeEnPassant(true);
		Assert.assertTrue(peaoPreto.isPodeEnPassant());

		// Criamos peça de outra cor e fazemos ter en passant
		tabuleiro.colocarPeca(new Posicao(2, 4), peaoBranco);
		peaoBranco.setPodeEnPassant(true);

		// Resetamos o estado de poder ocorrer um En Passant das casas pretas
		tabuleiro.resetaPodeEnPassant(CorJogador.PRETO);

		// Verificamos se as peças pretas ou brancas foram "atingidas"
		Assert.assertFalse(peaoPreto.isPodeEnPassant());
		Assert.assertTrue(peaoBranco.isPodeEnPassant());
	}

	@Test
	public void ehPromocao() throws CasaOcupadaException {

		// Testa se peça branca pode andar para uma promoção
		tabuleiro.colocarPeca(new Posicao(1, 7), peaoBranco);
		Assert.assertTrue(tabuleiro.ehPromocao(new Posicao(1, 7)));
		tabuleiro.colocarPeca(new Posicao(1, 2), peaoBranco);
		Assert.assertFalse(tabuleiro.ehPromocao(new Posicao(1, 2)));

		// Testa se peça preta pode andar para uma promoção
		tabuleiro.colocarPeca(new Posicao(8, 2), peaoPreto);
		Assert.assertTrue(tabuleiro.ehPromocao(new Posicao(8, 2)));
		tabuleiro.colocarPeca(new Posicao(8, 7), peaoPreto);
		Assert.assertFalse(tabuleiro.ehPromocao(new Posicao(8, 7)));

		// Testa se peça branca pode atacar para uma promoção
		tabuleiro.colocarPeca(new Posicao(1, 8), cavaloPreto);
		Assert.assertFalse(tabuleiro.ehPromocao(new Posicao(1, 7)));
		tabuleiro.colocarPeca(new Posicao(2, 8), cavaloPreto);
		Assert.assertTrue(tabuleiro.ehPromocao(new Posicao(1, 7)));

		// Testa se peça preta pode atacar para uma promoção
		tabuleiro.colocarPeca(new Posicao(8, 1), cavaloBranco);
		Assert.assertFalse(tabuleiro.ehPromocao(new Posicao(8, 2)));
		tabuleiro.colocarPeca(new Posicao(7, 1), cavaloBranco);
		Assert.assertTrue(tabuleiro.ehPromocao(new Posicao(8, 2)));
	}

	@Test
	public void verificarXequeMate() throws CasaOcupadaException,
			CloneNotSupportedException, JogadaInvalidaException {

		// Teste 1 : O rei pode fugir por uma casa
		tabuleiro.colocarPeca(new Posicao(1, 1), reiBranco);
		tabuleiro.colocarPeca(new Posicao(1, 8), torrePreta);
		Assert.assertFalse(tabuleiro.verificarXequeMate(CorJogador.BRANCO));

		// Teste 2: O rei está em xeque-mate
		tabuleiro.colocarPeca(new Posicao(2, 8), torrePreta);
		Assert.assertTrue(tabuleiro.verificarXequeMate(CorJogador.BRANCO));

		// Teste 3: O rei pode ser protegido por uma peça
		tabuleiro.colocarPeca(new Posicao(3, 3), cavaloBranco);
		Assert.assertFalse(tabuleiro.verificarXequeMate(CorJogador.BRANCO));

	}

	@Test
	public void valorTabuleiro() throws CasaOcupadaException {
		// Testa valor tabuleiro conforme insere peças

		// Uma peça
		tabuleiro.colocarPeca(new Posicao(1, 1), cavaloPreto);
		Assert.assertEquals(tabuleiro.valorTabuleiro(), 3);

		// Peça de mesmo valor
		tabuleiro.colocarPeca(new Posicao(2, 4), cavaloPreto);
		Assert.assertEquals(tabuleiro.valorTabuleiro(), 6);

		// Peça inimiga
		tabuleiro.colocarPeca(new Posicao(2, 8), cavaloBranco);
		Assert.assertEquals(tabuleiro.valorTabuleiro(), 3);

		// Peça de valor diferente (negativo)
		tabuleiro.colocarPeca(new Posicao(2, 1), torreBranca);
		Assert.assertEquals(tabuleiro.valorTabuleiro(), -2);
	}

	@Test
	public void recomendaJogada() throws CasaOcupadaException,
			JogadaInvalidaException, CloneNotSupportedException {
		// Não há jogadas a serem recomendadas
		tabuleiro.colocarPeca(new Posicao(1, 1), reiPreto);
		tabuleiro.colocarPeca(new Posicao(1, 8), torreBranca);
		tabuleiro.colocarPeca(new Posicao(2, 7), torreBranca);
		Assert.assertNull(tabuleiro.recomendaJogada(CorJogador.PRETO));
		
		// Há jogadas a serem recomendadas
		tabuleiro.colocarPeca(new Posicao(7, 4), torrePreta);
		Assert.assertNotNull(tabuleiro.recomendaJogada(CorJogador.PRETO));
	}

}
