package br.edu.ifes.poo1.ciu.cih;

import br.edu.ifes.poo1.cln.cdp.DadosPartida;
import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.Peca;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;

/**
 * Interface de Linha de Comando (CLI da sigla em inglês). É responsável pela
 * comunicação com os jogadores.
 */
public abstract class Cli {
	/** Usado para a entrada e saída de dados (io, Input Output). */
	protected EntradaSaida io = new EntradaSaida();

	/**
	 * Renova os elementos da tela, tais como: o tabuleiro, o indicador de vez e
	 * a pontuação de cada jogador.
	 * 
	 * @param tabuleiro
	 *            Tabuleiro que está sendo usado na partida.
	 * @param brancas
	 *            Jogador que controla as peças brancas.
	 * @param pretas
	 *            Jogador que controla as peças pretas.
	 */
	// TODO pontuação só deve aparecer quando requisitada
	public void atualizar(Tabuleiro tabuleiro, Jogador brancas, Jogador pretas) {
		// Imprime as peças capturadas pelos jogadores e suas pontuações.
		imprimirPontuacoes(brancas, pretas);
		imprimirLinha(""); // Dá uma folga para o próximo elemento.

		// Imprime o tabuleiro.
		imprimirTabuleiro(tabuleiro);
		imprimirLinha(""); // Dá uma folga para o próximo elemento.
	}

	/**
	 * Renova os elementos da tela, tais como: o tabuleiro, o indicador de vez e
	 * a pontuação de cada jogador. Exibe também uma aviso na tela.
	 * 
	 * @param tabuleiro
	 *            Tabuleiro que está sendo usado na partida.
	 * @param brancas
	 *            Jogador que controla as peças brancas.
	 * @param pretas
	 *            Jogador que controla as peças pretas.
	 * @param aviso
	 *            Aviso a ser exibido para o jogador.
	 */
	// TODO pontuação só deve aparecer quando requisitada
	public void atualizar(Tabuleiro tabuleiro, Jogador brancas, Jogador pretas,
			String aviso) {
		// Atualiza a tela normalmente.
		atualizar(tabuleiro, brancas, pretas);

		// Exibe a mensagem de aviso.
		exibirAlerta(aviso);
		imprimirLinha("");
	}

	/**
	 * Imprime na tela uma representação do estado atual do tabuleiro indicado
	 * por parâmetro.
	 * 
	 * @param tabuleiro
	 *            Tabuleiro a ser impresso.
	 */
	protected abstract void imprimirTabuleiro(Tabuleiro tabuleiro);

	/**
	 * Imprime as peças capturadas pelos jogadores e a pontuação de cada um.
	 * 
	 * @param brancas
	 *            Jogador que controla as brancas.
	 * @param pretas
	 *            Jogador que controla as pretas.
	 */
	private void imprimirPontuacoes(Jogador brancas, Jogador pretas) {
		imprimirLinha(":: Pontuação dos jogadores");
		imprimirLinha(":: -----------------------");
		imprimirLinha(":: " + getDescricaoPecasCapturadas(brancas));
		imprimirLinha(":: " + getDescricaoPecasCapturadas(pretas));
	}

	/**
	 * Lê uma jogada da tela. Este método retorna uma String com a entrada pura
	 * do jogador, sem nenhuma verificação.
	 * 
	 * @param jogador
	 *            Jogador que deve dar a jogada.
	 * 
	 * @return Retorna uma String com a jogada do usuário.
	 */
	public String lerJogada(Jogador jogador) {
		return pedir("Entre com a jogada (vez do jogador: " + jogador.getNome()
				+ "):");
	}

	/**
	 * Lê o nome do jogador que controla as peças brancas.
	 * 
	 * @return Nome do jogador.
	 */
	public String lerNomeJogadorBranco() {
		return pedir("Entre com o nome do jogador das peças brancas:");
	}

	/**
	 * Lê o nome do jogador que controla as peças pretas.
	 * 
	 * @return Nome do jogador.
	 */
	public String lerNomeJogadorPreto() {
		return pedir("Entre com o nome do jogador das peças pretas:");
	}

	/**
	 * Parabeniza o vencedor de uma partida.
	 * 
	 * @param vencedor
	 *            Vencedor da partida.
	 */
	public void fechamentoDaPartida(String mensagemFinalizacao) {
		imprimirLinha("");
		imprimirLinha(mensagemFinalizacao);
	}

	/**
	 * Retorna uma string com uma descrição de quais as peças foram capturadas
	 * pelo jogador e que pontuação elas garantem ao jogador.
	 * 
	 * @param jogador
	 *            Jogador do qual as peças seram contabilizadas.
	 * @return String que descreve as peças que o jogador capturou e a sua
	 *         pontuação.
	 */
	protected String getDescricaoPecasCapturadas(Jogador jogador) {
		// Inicia a descrição com o nome do jogador.
		String descricao = jogador.getNome() + ": ";

		// Junta cada peça que foi capturada na string.
		for (Peca peca : jogador.getPecasCapturadas()) {
			descricao += PecaToString(peca) + " ";
		}

		// Junta a string com a pontuação total do jogador.
		descricao += "= " + pontuacaoTotal(jogador);

		return descricao;
	}

	/**
	 * Calcula a pontuação total de um jogador.
	 * 
	 * @param jogador
	 *            Jogador do qual serão calculados os pontos.
	 * @return Pontuação do jogador.
	 */
	protected int pontuacaoTotal(Jogador jogador) {
		int pontuacao = 0;
		for (Peca peca : jogador.getPecasCapturadas()) {
			pontuacao += peca.getValor();
		}
		return pontuacao;
	}

	/**
	 * Converte uma peça para uma sequência de caracteres que a represente.
	 * 
	 * @param peca
	 *            Peça a converter.
	 * @return String correspondente a peça.
	 */
	public abstract String PecaToString(Peca peca);

	/**
	 * Exibe uma mensagem de alerta ao usuário. A mensagem recebida por
	 * parâmetro será devidamente formatada para ser exibida ao usuário como um
	 * alerta.
	 * 
	 * @param mensagem
	 *            Mensagem a ser exibida para o usuário.
	 */
	public void exibirAlerta(String mensagem) {
		io.imprimirLinha("[!] " + mensagem);
	}

	/**
	 * Exibe a mensagem indicada por parâmetro ao usuário e em seguida solicita
	 * uma resposta do usuário, que é retornada no formato de uma String.
	 * 
	 * @param solicitacao
	 *            Mensagem expressando um pedido ao usuário.
	 * @return Reposta do usuário.
	 */
	public String pedir(String solicitacao) {
		return io.pedir(solicitacao);
	}

	/**
	 * Exibe o texto recebido por parâmetro. Será escrito exatamente como
	 * recebido no parâmetro.
	 * 
	 * @param texto
	 *            Texto a ser exibido.
	 */
	public void imprimir(String texto) {
		io.imprimir(texto);
	}

	/**
	 * Exibe o texto recebido por parâmetro. Será escrito exatamente como
	 * recebido no parâmetro, porém com a adição de uma quebra de linha no
	 * final.
	 * 
	 * @param texto
	 *            Texto a ser exibido.
	 */
	public void imprimirLinha(String texto) {
		io.imprimirLinha(texto);
	}

	/** Retorna o objeto usado para escrita na linha de comando. */
	public EntradaSaida getIo() {
		return io;
	}

	/**
	 * Informa na tela todos os dados de uma determinada partida
	 * 
	 * @param dadosPartidas
	 */
	public void exibirDadosPartidas(int indice, DadosPartida dadosPartida) {
		io.imprimirLinha(indice+"..."+dadosPartida.getDataPartida() + "..."
				+ dadosPartida.getJogo().getBrancas().getNome() + "..."
				+ dadosPartida.getJogo().getPretas().getNome() + "..."
				+ dadosPartida.getJogo().getMotivoDeFinalizacao());
	}
	
	/** Pega um índice para apagar uma partida */
	public String pedeIndicePartidaApagar(){
		return pedir("Diga o índice da partida que deseja apagar (-1 Se desistir): ");
	}
	
	/** Pega um índice para carregar uma partida */
	public String pedeIndicePartidaCarregar(){
		return pedir("Diga o índice da partida que deseja carregar (-1 Se desistir): ");
	}
}
