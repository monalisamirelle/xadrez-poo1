package br.edu.ifes.poo1.app;

import br.edu.ifes.poo1.ciu.cci.Controlador;
import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;

/**
 * Aplicação principal, para a execução do jogo na linha de comando.
 */
public class App {

	public static void main(String[] args) throws CasaOcupadaException,
			CloneNotSupportedException {

		// /** Dados */
		// Tabuleiro tabuleiro = new Tabuleiro();
		// Peca reiPreto = new Rei(CorJogador.PRETO);
		// Peca torreBranca = new Torre(CorJogador.BRANCO);
		// Peao peaoBranco = new Peao(CorJogador.BRANCO);
		//
		// /** Peças */
		// tabuleiro.colocarPeca(new Posicao(1, 1), reiPreto);
		// tabuleiro.colocarPeca(new Posicao(1, 8), torreBranca);
		// peaoBranco.setPodeEnPassant(true);
		// tabuleiro.colocarPeca(new Posicao(4, 7), peaoBranco);
		//
		// /** Jogo 1 */
		// Jogador jogadorBranco = new Pessoa("Paulos@", CorJogador.BRANCO);
		// Jogador jogadorPreto = new Pessoa("Otavio Silva", CorJogador.PRETO);
		// AplJogo jogo1 = new AplMultiplayer(jogadorBranco, jogadorPreto,
		// tabuleiro, TipoMotivoFimDaPartida.VITORIA,
		// jogadorPreto.getNome());
		//
		// jogadorBranco = new Pessoa("Paulos Oliveila", CorJogador.BRANCO);
		// jogadorPreto = new Pessoa("OUHW3", CorJogador.PRETO);
		// AplJogo jogo2 = new AplMultiplayer(jogadorBranco, jogadorPreto,
		// tabuleiro, TipoMotivoFimDaPartida.VITORIA,
		// jogadorPreto.getNome());
		//
		// Arquivo arquivo = new Arquivo();
		// List<DadosPartida> listaDados = new ArrayList<DadosPartida>();
		// DadosPartida dp1 = new DadosPartida(jogo1);
		// DadosPartida dp2 = new DadosPartida(jogo2);
		// listaDados.add(dp1);
		// listaDados.add(dp2);
		// arquivo.escrevaJogo(listaDados);
		// List<DadosPartida> lista = new ArrayList<DadosPartida>();
		// lista = arquivo.leiaJogos();
		// System.out.println(lista.get(1).getJogo().getBrancas().getNome());

		// Inicia o jogo.
		Controlador ctrl = new Controlador();
		ctrl.iniciar();
	}
}
