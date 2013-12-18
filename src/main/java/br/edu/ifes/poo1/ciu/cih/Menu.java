package br.edu.ifes.poo1.ciu.cih;

import java.util.InputMismatchException;
import java.util.List;

/**
 * Responsável pela exibição e controle de um menu do jogo.
 */
public abstract class Menu {
	/** Título do menu. */
	private String titulo;

	/** Lista de itens a ser preenchida pelas classes filhas. */
	private List<ItemMenu> itens;

	public Menu(String titulo, List<ItemMenu> itens) {
		this.titulo = titulo;
		this.itens = itens;
	}

	@Override
	public String toString() {
		// Começa a descrição com o título.
		String descricao = ":: " + titulo + "\n";

		// Anexa cada um dos itens.
		for (int i = 0; i < itens.size(); i++) {
			ItemMenu item = itens.get(i);
			descricao += "\t" + i + ". " + item.getDescricao() + "\n";
		}

		return descricao;

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
	// TODO: Acertar a implementação, se esse método realmente for ser usado.
	public ItemMenu selecionarItem(Cli cli) throws EntradaInvalidaException {
		// Imprime todo o menu principal
		cli.exibir(this.toString());

		// Tenta lê a escolha do usuário.
		int escolha;
		try {
			// Lê a escolha do usuário.
			escolha = Integer.parseInt(cli.pedir("Selecione uma opção:"));
		} catch (InputMismatchException e) {
			// Se o jogador entrou com alguma sequência de caracteres que não
			// possa ser identificada como um inteiro, então dizemos que a
			// entrada escolhida foi inválida. E lançamos a seguinte excessão.
			throw new EntradaInvalidaException(
					"A entrada recebida não pode ser interpretada como uma das opções listadas.");
		}

		// Retorna a entrada do menu correspondente a escolha do usuário.
		for (ItemMenu item : itens) {
			if (item.getOrdem() == escolha)
				return item;
		}

		// Se até este momento nenhum item foi retornado, é poque o jogador
		// escolheu um item que não está disponível no menu. Então lançamos
		// a excessão.
		throw new EntradaInvalidaException(
				"Você não selecionou um item válido da lista.");
	}

	public String getTitulo() {
		return titulo;
	}

	// TODO: Pensar na possíbilidade de retornar umas simples array (ItemMenu[])
	// e colocar o nome values() para este método. Assim como é uma enum.
	public List<ItemMenu> getItens() {
		return itens;
	}
}
