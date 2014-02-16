package br.edu.ifes.poo1.ciu.cih;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import br.edu.ifes.poo1.cln.cdp.DadosPartida;
import br.edu.ifes.poo1.cln.cdp.DadosPessoa;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.Jogador;
import br.edu.ifes.poo1.cln.cdp.TabuleiroXadrez;
import br.edu.ifes.poo1.cln.cdp.pecas.Peca;

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
	public void atualizar(TabuleiroXadrez tabuleiro, Jogador brancas, Jogador pretas) {
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
	public void atualizar(TabuleiroXadrez tabuleiro, Jogador brancas, Jogador pretas,
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
	protected abstract void imprimirTabuleiro(TabuleiroXadrez tabuleiro);

	/**
	 * Imprime as peças capturadas pelos jogadores e a pontuação de cada um.
	 * 
	 * @param brancas
	 *            Jogador que controla as brancas.
	 * @param pretas
	 *            Jogador que controla as pretas.
	 */
	// TODO está em ordem de importância como pede o trabalho?
	public void imprimirPontuacoes(Jogador brancas, Jogador pretas) {
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
	public String lerAcaoJogador(Jogador jogador) {
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
		imprimirLinha(mensagemFinalizacao + "\n");
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
		io.imprimirLinha("[!] " + mensagem + "\n");
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
	 * Imprime uma recomendação a um jogador
	 * 
	 * @param jogada
	 */
	public void imprimirRecomendacao(int numeroRecomendacao,Peca peca ,Jogada jogada) {
		io.imprimirLinha(numeroRecomendacao + " - Recomendação:");
		io.imprimirLinha("Mova a peça "+peca.getTipoPeca()+" da coluna " + jogada.getOrigem().getColuna()
				+ " e linha " + jogada.getOrigem().getLinha()
				+ " para a coluna " + jogada.getDestino().getColuna()
				+ " e linha " + jogada.getDestino().getLinha() + "\n");
	}

	/**
	 * Informa na tela todos os dados de uma determinada partida que ainda não terminou
	 * 
	 * @param dadosPartidas
	 */
	public void exibirDadosPartidasAndamento(int indice,
			DadosPartida dadosPartida) {
		io.imprimirLinha(indice + "..."
				+ manipulaData(dadosPartida.getDataInicioPartida()) + "..."
				+ manipulaData(dadosPartida.getDataTerminoPartida()) + "..."
				+ dadosPartida.getJogo().getJogadorBrancas().getNome() + "..."
				+ dadosPartida.getJogo().getJogadorPretas().getNome());
	}

	/**
	 * Informa na tela todos os dados das partidas concluídas
	 * @param indice
	 * @param dadosPartida
	 */
	public void exibirDadosPartidasConcluidas(int indice,
			DadosPartida dadosPartida) {
		io.imprimirLinha(indice + "..."
				+ manipulaData(dadosPartida.getDataInicioPartida()) + "..."
				+ manipulaData(dadosPartida.getDataTerminoPartida()) + "..."
				+ dadosPartida.getJogo().getNomeVencedor());
	}

	/**
	 * Informa na tela todos os históricos de pessoas
	 * @param indice
	 * @param dadosPessoa
	 */
	public void exibirDadosJogadores(int indice, DadosPessoa dadosPessoa) {
		io.imprimirLinha(indice + "..." + dadosPessoa.getNome() + "..."
				+ dadosPessoa.getPartidasVencidas() + "..."
				+ dadosPessoa.getPartidasPerdidas());
	}

	/**
	 * Manipula uma data para transformá-la em uma String capaz de ser
	 * compreendida
	 * 
	 * @param data
	 * @return
	 */
	private String manipulaData(GregorianCalendar data) {
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy"
				+ " (" + "HH:mm:ss" + ")");
		return dataFormatada.format(data.getTimeInMillis());
	}

	/** Pega um índice para apagar uma partida */
	public String pedeIndicePartidaApagar() {
		return pedir("Diga o índice da partida que deseja apagar (-1 Se desistir): ");
	}

	/** Pega um índice para carregar uma partida */
	public String pedeIndicePartidaCarregar() {
		return pedir("Diga o índice da partida que deseja carregar (-1 Se desistir): ");
	}

	/**
	 * Método que informa os comandos que podem ser realizados por um jogador
	 */
	public void exibirComandos() {
		io.imprimirLinha("Lista de comandos:");
		io.imprimirLinha("");
		io.imprimirLinha("pontos -> Exibe a pontuação da partida");
		io.imprimirLinha("recomendar -> Recomenda uma jogada ao jogador (máximo de 3 jogadas)");
		io.imprimirLinha("salvar -> Salva o estado da partida");
		io.imprimirLinha("empate -> Sugere empate ao jogador adversário");
		io.imprimirLinha("desistir -> Desistir de uma partida (perde a partida)");
		io.imprimirLinha("sair -> Sair de uma partida (não termina ela)");
		io.imprimirLinha("ajuda -> Exibe os comandos possíveis");
		io.imprimirLinha("");
	}
}
