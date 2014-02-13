package br.edu.ifes.poo1.app;

import br.edu.ifes.poo1.ciu.cci.Controlador;
import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;

/**
 * Aplicação principal, para a execução do jogo na linha de comando.
 */
public class App {

	public static void main(String[] args) throws CasaOcupadaException,
			JogadaInvalidaException, InterruptedException {

		// /** Dados */
		// Tabuleiro tabuleiro = new Tabuleiro();
		// Peca reiPreto = new Rei(TipoCorJogador.PRETO);
		// Peca torreBranca = new Torre(TipoCorJogador.BRANCO);
		// Peao peaoBranco = new Peao(TipoCorJogador.BRANCO);
		//
		// /** Peças */
		// tabuleiro.colocarPeca(new Posicao(1, 1), reiPreto);
		// tabuleiro.colocarPeca(new Posicao(1, 8), torreBranca);
		// peaoBranco.setPodeEnPassant(true);
		// tabuleiro.colocarPeca(new Posicao(4, 7), peaoBranco);
		//
		// /** Jogo 1 */
		// Jogador jogadorBranco = new Pessoa("Paulos@", TipoCorJogador.BRANCO);
		// Jogador jogadorPreto = new Pessoa("Otavio Silva",
		// TipoCorJogador.PRETO);
		// AplJogo jogo1 = new AplJogo(jogadorBranco, jogadorPreto, tabuleiro,
		// TipoSituacaoPartida.VITORIA, jogadorPreto.getNome());
		//
		// jogadorBranco = new Pessoa("Paulos Oliveila", TipoCorJogador.BRANCO);
		// jogadorPreto = new Pessoa("OUHW3", TipoCorJogador.PRETO);
		// AplJogo jogo2 = new AplJogo(jogadorBranco, jogadorPreto, tabuleiro,
		// TipoSituacaoPartida.VITORIA, jogadorPreto.getNome());
		//
		// Arquivo arquivo = new Arquivo();
		// List<DadosPartida> listaDados = new ArrayList<DadosPartida>();
		// DadosPartida dp1 = new DadosPartida(jogo1);
		// DadosPartida dp2 = new DadosPartida(jogo2);
		// listaDados.add(dp1);
		// listaDados.add(dp2);
		// arquivo.escrevaPartidas(listaDados);

		// Inicia o jogo.
		Controlador ctrl = new Controlador();
		ctrl.iniciar();
	}
}
