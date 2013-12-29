package br.edu.ifes.poo1.cln.cdp;

import java.util.ArrayList;
import java.util.Random;

public class Zeus extends Maquina{

	/**
	 * Lista contendo as posições que são possíveis de ter uma peça controlada
	 * pela máquina
	 */
	private ArrayList<Integer> posicoesPossiveis = new ArrayList<Integer>(64);

	public Zeus(String nome, CorJogador cor) {
		super(nome, cor);
	}

	/**
	 * Inicializa uma lista que deve ser capaz de guardar todas as posições
	 * ainda não foram escolhidas pelo modo aleatório de escolhas
	 */
	private ArrayList<Integer> inicializaLista(ArrayList<Integer> lista) {
		int contadorIndice;
		for (contadorIndice = 1; contadorIndice <= 64; contadorIndice++)
			lista.add(contadorIndice);
		return lista;
	}

	/**
	 * Método que gera um indice aleatório com base em uma lista
	 * 
	 * @param lista
	 * @return
	 */
	private int geraIndiceAleatorio(ArrayList<Integer> lista) {
		Random aleatorio = new Random();
		return aleatorio.nextInt(lista.size());
	}

	/**
	 * Irá escolher uma peça possível de ser controlada pela máquina
	 * 
	 * @return
	 */
	private PosicaoEscolhidaMaquina escolhePeca() {
		int indiceAleatorio;
		int casaSelecionada;
		Posicao posicao;
		do {
			// Cria um valor correspondente a uma posição aleatória
			indiceAleatorio = geraIndiceAleatorio(posicoesPossiveis);
			casaSelecionada = posicoesPossiveis.get(indiceAleatorio);
			posicao = new Posicao(((casaSelecionada - 1) % 8) + 1,
					((casaSelecionada - 1) / 8) + 1);
			// Se a posição selecionada pelo tabuleiro for diferente de uma peça
			// da máquina
			if (tabuleiro.estaInimigo(this, posicao) == true
					|| tabuleiro.estaVazio(posicao) == true) {
				// Removemos a posição do escopo de posições possiveis
				posicoesPossiveis.remove(indiceAleatorio);
			}
			// Faça isso enquanto...
		} while (posicoesPossiveis.size() != 0
				&& (tabuleiro.estaInimigo(this, posicao) == true || tabuleiro
						.estaVazio(posicao)));
		// Se todas as posições (peças) já foram analisadas. Retorne nulo
		if (posicoesPossiveis.size() == 0) {
			return null;
		}
		// Retorne a posição possível
		return new PosicaoEscolhidaMaquina(new Posicao(
				((casaSelecionada - 1) % 8) + 1,
				((casaSelecionada - 1) / 8) + 1), indiceAleatorio);
	}

	/**
	 * Método que irá gerar um ataque
	 * 
	 * @param origem
	 * @return
	 */
	private Posicao ataqueEscolhido(Posicao origem) {
		Peca peca = tabuleiro.espiarPeca(origem);
		ArrayList<Integer> ataquesPossiveis = new ArrayList<Integer>(64);
		ataquesPossiveis = inicializaLista(ataquesPossiveis);
		int indiceAleatorio;
		int casaSelecionada;
		Posicao destino;
		do {
			indiceAleatorio = geraIndiceAleatorio(ataquesPossiveis);
			casaSelecionada = ataquesPossiveis.get(indiceAleatorio);
			destino = new Posicao(((casaSelecionada - 1) % 8) + 1,
					((casaSelecionada - 1) / 8) + 1);
			if (peca.podeAtacar(origem, destino, tabuleiro) == false)
				ataquesPossiveis.remove(indiceAleatorio);
		} while (peca.podeAtacar(origem, destino, tabuleiro) == false
				&& ataquesPossiveis.size() != 0);
		if (ataquesPossiveis.size() == 0)
			return null;
		// Retorne o ataque
		return destino;
	}

	/**
	 * Método que irá gerar um movimento
	 * 
	 * @param origem
	 * @return
	 */
	private Posicao movimentoEscolhido(Posicao origem) {
		Peca peca = tabuleiro.espiarPeca(origem);
		ArrayList<Integer> movimentosPossiveis = new ArrayList<Integer>(64);
		movimentosPossiveis = inicializaLista(movimentosPossiveis);
		int indiceAleatorio;
		int casaSelecionada;
		Posicao destino;
		do {
			indiceAleatorio = geraIndiceAleatorio(movimentosPossiveis);
			casaSelecionada = movimentosPossiveis.get(indiceAleatorio);
			destino = new Posicao(((casaSelecionada - 1) % 8) + 1,
					((casaSelecionada - 1) / 8) + 1);
			if (peca.podeAndar(origem, destino, tabuleiro) == false)
				movimentosPossiveis.remove(indiceAleatorio);
		} while (peca.podeAndar(origem, destino, tabuleiro) == false
				&& movimentosPossiveis.size() != 0);
		if (movimentosPossiveis.size() == 0)
			return null;
		// Retorne o movimento
		return destino;
	}

	/**
	 * Método que irá definir uma jogada para ser realizada pela máquina
	 * 
	 * @return
	 */
	public Jogada escolherJogada() {
		posicoesPossiveis = inicializaLista(posicoesPossiveis);
		PosicaoEscolhidaMaquina posicaoEscolhida;
		Posicao realizaAtaque = null;

		// Tenta realizar um ataque
		do {
			posicaoEscolhida = escolhePeca();
			if (posicaoEscolhida != null) {
				realizaAtaque = ataqueEscolhido(posicaoEscolhida.getPosicao());
				posicoesPossiveis.remove(posicaoEscolhida.getIndice());
			}
		} while (realizaAtaque == null && posicoesPossiveis.size() != 0);
		// Se um ataque pode ser realizado
		if (realizaAtaque != null) {
			return new Jogada(posicaoEscolhida.getPosicao(), realizaAtaque,
					TipoJogada.ATACAR);
			// Tenta realizar movimento (caso um ataque não possa ser realizado)
		} else {
			posicoesPossiveis = inicializaLista(posicoesPossiveis);
			Posicao realizaMovimento = null;
			// Tenta realizar um movimento
			do {
				posicaoEscolhida = escolhePeca();
				realizaMovimento = movimentoEscolhido(posicaoEscolhida
						.getPosicao());
			} while (realizaMovimento == null && posicaoEscolhida != null);

			if (realizaMovimento != null) {
				return new Jogada(posicaoEscolhida.getPosicao(),
						realizaMovimento, TipoJogada.ANDAR);
			}
		}
		return null;
	}

}
