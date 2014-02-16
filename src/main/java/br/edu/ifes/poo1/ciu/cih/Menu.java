package br.edu.ifes.poo1.ciu.cih;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsável pela exibição e controle de um menu do jogo.
 */
public abstract class Menu {
	/** Título do menu. */
	private String titulo;

	/** Lista de itens a ser preenchida pelas classes filhas. */
	private List<ItemMenu> itens;

	/**
	 * Contrói um menu com as devidas informações necessárias. Que são o seu
	 * título e os itens do menu.
	 * 
	 * @param titulo
	 *            Título do menu.
	 * @param itens
	 *            Cada itens do menu.
	 */
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
	public ItemMenu selecionarItem(EntradaSaida io)
			throws EntradaInvalidaException {
		// Imprime todo o menu principal
		io.imprimirLinha(this.toString());

		// Tenta ler a escolha do usuário.
		int escolha;
		try {
			// Lê a escolha do usuário.
			escolha = Integer
					.parseInt(io.pedir("Selecione uma opção do menu:"));
		} catch (NumberFormatException e) {
			// Se o jogador entrou com alguma sequência de caracteres que não
			// possa ser identificada como um inteiro, então dizemos que a
			// entrada escolhida foi inválida. E lançamos a seguinte excessão.
			throw new EntradaInvalidaException(
					"A entrada recebida não pode ser interpretada como uma das opções listadas.");
		}
		// Retorna a entrada do menu correspondente a escolha do usuário.
		for (int i = 0; i < itens.size(); i++) {
			if (i == escolha)
				return itens.get(i);
		}

		// Se até este momento nenhum item foi retornado, é poque o jogador
		// escolheu um item que não está disponível no menu. Então lançamos
		// a excessão.
		throw new EntradaInvalidaException(
				"Você não selecionou um item válido da lista.");
	}

	/**
	 * Insiste como o usuário até conseguir dele um item do menu válido.
	 * Enquanto o usuário não der uma entrada válida, o método perguntará a ele
	 * continuamente por uma entrada. Exibindo também os devidos avisos.
	 * 
	 * @param cli
	 *            Interface de linha de comando a ser usada para se comunicar
	 *            com o jogador.
	 * @return Item escolhido pelo usuário.
	 */
	public ItemMenu insistirPorEntradaValida(EntradaSaida io) {
		do {
			try {
				// Tenta pegar uma entrada do jogador e retornar um item
				// escolhido pelo jogador.
				return this.selecionarItem(io);
			} catch (EntradaInvalidaException e) {
				// Se o jogador escolher alguma entrada inválida, avise-o...
				io.imprimirLinha("[!] " + e.getMessage() + "\n");
				continue; // ... e repita o laço.
			}
		} while (true);
	}

	/**
	 * Retorna o título do menu.
	 * 
	 * @return Título do menu.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Fornece uma lista dos itens que há neste menu. É importante notar que a
	 * lista forncecida é apenas uma cópia, e qualquer alteração feita nela não
	 * reflete no Menu.
	 * 
	 * @return Uma lista com os itens deste menu.
	 */
	public List<ItemMenu> getItens() {
		return new ArrayList<ItemMenu>(itens);
	}

	/**
	 * Retorna o número de itens de menu que a neste menu.
	 * 
	 * @return Número de itens no menu.
	 */
	public int size() {
		return this.itens.size();
	}
}
