package br.edu.ifes.poo1.ciu.cih;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.edu.ifes.poo1.cln.cdp.Pessoa;
import br.edu.ifes.poo1.cln.cdp.Peca;
import br.edu.ifes.poo1.cln.cdp.Tabuleiro;

/**
 * Interface de Linha de Comando (CLI da sigla em inglês). É responsável pela
 * comunicação com os jogadores.
 */
public abstract class Cli {
	protected Scanner s = new Scanner(System.in);

	/**
	 * Renova os elementos da tela, tais como: o tabuleiro, o indicador de vez e
	 * qualquer outra coisa que seja de pura apresentação ao usuário dos
	 * elementos do jogo.
	 * 
	 * @param tabuleiro
	 *            Tabuleiro que está sendo usado na partida.
	 * @param brancas
	 *            Jogador que controla as peças brancas.
	 * @param pretas
	 *            Jogador que controla as peças pretas.
	 */
	public void atualizar(Tabuleiro tabuleiro, Pessoa brancas, Pessoa pretas) {
		// Imprime as peças capturadas pelos jogadores e suas pontuações.
		imprimirPontuacoes(brancas, pretas);

		// Imprime o tabuleiro.
		imprimirTabuleiro(tabuleiro);
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
	private void imprimirPontuacoes(Pessoa brancas, Pessoa pretas) {
		System.out.println(":: Pontuação dos jogadores");
		System.out.println(":: -----------------------");
		System.out.println(":: " + getDescricaoPecasCapturadas(brancas));
		System.out.println(":: " + getDescricaoPecasCapturadas(pretas));
		System.out.println();
	}

	/**
	 * Lê uma jogada da tela. Este método retorna uma String com a entrada pura
	 * do jogador, sem nenhuma verificação.
	 * 
	 * @param jogador
	 * 
	 * @return Retorna uma String com a jogada do usuário.
	 */
	public String lerJogada(Pessoa jogador) {
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
	 * Exibe o menu principal e deixa o jogador escolher uma de suas entradas.
	 * Se ele indicar algum item que não está listado, ou fornecer uma entrada
	 * não compreensível, a excessão EntradaMenuInvalida é lançada.
	 * 
	 * @return A entrada de menu selecionada pelo usuário.
	 * @throws EntradaInvalidaException
	 *             Lançada caso não seja possível interpretar a escolha do
	 *             usuário como uma entrada de menu válida.
	 */
	public ItemMenuPrincipal selecionarItemMenuPrincipal()
			throws EntradaInvalidaException {

		// Imprime todo o menu principal
		System.out.println("Menu Principal:");
		for (ItemMenuPrincipal item : ItemMenuPrincipal.values()) {
			System.out.println("\t" + item.getOrdem() + ". "
					+ item.getDescricao());
		}

		// Tenta lê a escolha do usuário.
		int escolha;
		try {
			// Lê a escolha do usuário.
			escolha = Integer.parseInt(pedir("Selecione uma opção:"));
		} catch (InputMismatchException e) {
			// Se o jogador entrou com alguma sequência de caracteres que não
			// possa ser identificada como um inteiro, então dizemos que a
			// entrada escolhida foi inválida. E lançamos a seguinte excessão.
			throw new EntradaInvalidaException(
					"A entrada recebida não pode ser interpretada como uma das opções listadas.");
		}

		// Retorna a entrada do menu correspondente a escolha do usuário.
		for (ItemMenuPrincipal item : ItemMenuPrincipal.values()) {
			if (item.getOrdem() == escolha)
				return item;
		}

		// Se até este momento nenhum item foi retornado, é poque o jogador
		// escolheu um item que não está disponível no menu. Então lançamos
		// a excessão.
		throw new EntradaInvalidaException(
				"Você não selecionou um item válido da lista.");
	}

	/**
	 * Parabeniza o vencedor de uma partida.
	 * 
	 * @param vencedor
	 *            Vencedor da partida.
	 */
	public void parabenizarVencedor(Pessoa vencedor) {
		System.out.println("Parabéns a " + vencedor.getNome()
				+ ", ganhador da partida");

	}

	/**
	 * Exibe uma mensagem de alerta ao usuário.
	 * 
	 * @param mensagem
	 *            Mensagem a ser exibida para o usuário.
	 */
	public void exibirAlerta(String mensagem) {
		System.out.println("[!] " + mensagem);
	}

	/**
	 * Retorna uma string com uma descrição de que peças foram captura
	 * 
	 * @param jogador
	 * @return
	 */
	protected String getDescricaoPecasCapturadas(Pessoa jogador) {
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
	protected int pontuacaoTotal(Pessoa jogador) {
		int pontuacao = 0;
		for (Peca peca : jogador.getPecasCapturadas()) {
			pontuacao += peca.getValor();
		}
		return pontuacao;
	}

	/**
	 * Retorna uma String que represente a peça indicada.
	 * 
	 * @param peca
	 *            Peca que deve ser convertida para String.
	 * @return String que representa a peça.
	 */
	public abstract String PecaToString(Peca peca);

	public Ambiente selecionarAmbiente() throws EntradaInvalidaException {
		// Imprime todos os ambientes
		System.out.println("Ambientes disponíveis:");
		for (Ambiente ambiente : Ambiente.values()) {
			System.out.println("\t" + ambiente.getOrdem() + ". " + ambiente);
		}

		// Tenta ler a escolha do usuário.
		int escolha;
		try {
			// Lê a escolha do usuário.
			escolha = Integer.parseInt(pedir("Selecione uma opção:"));
		} catch (InputMismatchException e) {
			// Se o jogador entrou com alguma sequência de caracteres que não
			// possa ser identificada como um inteiro, então dizemos que a
			// entrada escolhida foi inválida. E lançamos a seguinte excessão.
			throw new EntradaInvalidaException(
					"A entrada recebida não pode ser interpretada como uma das opções listadas.");
		}

		// Retorna a entrada do menu correspondente a escolha do usuário.
		for (Ambiente ambiente : Ambiente.values()) {
			if (ambiente.getOrdem() == escolha)
				return ambiente;
		}

		// Se até este momento nenhum item foi retornado, é poque o jogador
		// escolheu um item que não está disponível no menu. Então lançamos
		// a excessão.
		throw new EntradaInvalidaException(
				"Você não selecionou um item válido da lista.");
	}

	/**
	 * Exibe a mensagem indicada por parâmetro ao usuário e solicita uma
	 * resposta.
	 * 
	 * @param solicitacao
	 *            Mensagem expressando um pedido ao usuário.
	 * @return Reposta do usuário.
	 */
	public String pedir(String solicitacao) {
		System.out.println(solicitacao);
		System.out.print(" >> ");
		return s.next();
	}

	public void exibir(String mensagem) {
		System.out.println(mensagem);
	}
}
