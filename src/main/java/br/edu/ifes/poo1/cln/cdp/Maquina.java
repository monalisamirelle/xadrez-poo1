package br.edu.ifes.poo1.cln.cdp;

import java.util.ArrayList;
import java.util.Random;

public class Maquina extends Jogador {

	/**
	 * Lista contendo as posições que são possíveis de ter uma peça controlada
	 * pela máquina
	 */
	private ArrayList<Integer> posicoesPossiveis = new ArrayList<Integer>(64);

	public Maquina(String nome, CorJogador cor) {
		super(nome, cor);
	}

	/**
	 * Inicializa uma lista que deve ser capaz de guardar todas as posições
	 * ainda não foram escolhidas pelo modo aleatório de escolhas
	 */
	private void inicializaLista() {
		int contadorIndice;
		for (contadorIndice = 0; contadorIndice <= 64; contadorIndice++)
			posicoesPossiveis.add(contadorIndice);
		System.out.println(posicoesPossiveis);
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
	// Trabalhando de 0 a 7
	private Posicao escolhePeca() {
		int indiceAleatorio;
		int casaSelecionada;
		Posicao posicao;
		do {
			// Cria um valor correspondente a uma posição aleatória
			indiceAleatorio = geraIndiceAleatorio(posicoesPossiveis);
			casaSelecionada = posicoesPossiveis.get(indiceAleatorio);
			posicao = new Posicao((casaSelecionada % 8) - 1,
					casaSelecionada / 8);
			// Se a posição selecionada pelo tabuleiro for diferente de uma peça
			// da máquina
			if (tabuleiro.estaInimigo(this, posicao) == true
					|| tabuleiro.estaVazio(posicao))
				;
			// Removemos a posição do escopo de posições possiveis
			posicoesPossiveis.remove(indiceAleatorio);
			// Faça isso enquanto...
		} while (posicoesPossiveis.size() != 0
				&& (tabuleiro.estaInimigo(this, posicao) == true || tabuleiro
						.estaVazio(posicao)));
		// Se todas as posições (peças) já foram analisadas. Retorne nulo
		if (posicoesPossiveis.size() == 0)
			return null;
		// Retorne a posição possível
		return (new Posicao((casaSelecionada % 8) - 1, casaSelecionada / 8));
	}

	/**
	 * Método que irá gerar um ataque
	 * 
	 * @param origem
	 * @return
	 */
	// Trabalhando de 1 a 8
	private Posicao ataqueEscolhido(Posicao origem) {
		Peca peca = tabuleiro.espiarPeca(origem);
		ArrayList<Integer> ataquesPossiveis = new ArrayList<Integer>(64);
		int indiceAleatorio;
		int casaSelecionada;
		Posicao destino;
		do {
			indiceAleatorio = geraIndiceAleatorio(ataquesPossiveis);
			casaSelecionada = ataquesPossiveis.get(indiceAleatorio);
			destino = new Posicao(casaSelecionada % 8, casaSelecionada / 8 + 1);
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
	// Trabalhando de 1 a 8
	private Posicao movimentoEscolhido(Posicao origem) {
		Peca peca = tabuleiro.espiarPeca(origem);
		ArrayList<Integer> movimentosPossiveis = new ArrayList<Integer>(64);
		int indiceAleatorio;
		int casaSelecionada;
		Posicao destino;
		do {
			indiceAleatorio = geraIndiceAleatorio(movimentosPossiveis);
			casaSelecionada = movimentosPossiveis.get(indiceAleatorio);
			destino = new Posicao(casaSelecionada % 8, casaSelecionada / 8 + 1);
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
		inicializaLista();
		Posicao posicaoEscolhida;
		Posicao realizaAtaque;
		// Tenta realizar um ataque
		do {
			posicaoEscolhida = escolhePeca();
			realizaAtaque = ataqueEscolhido(posicaoEscolhida);
		} while (realizaAtaque == null && posicaoEscolhida != null);
		// Se um ataque pode ser realizado
		if (realizaAtaque != null) {
			return new Jogada(posicaoEscolhida, realizaAtaque, TipoJogada.ATACAR);
			// Tenta realizar movimento (caso um ataque não possa ser realizado)
		} else {
			inicializaLista();
			Posicao realizaMovimento;
			// Tenta realizar um movimento
			do {
				posicaoEscolhida = escolhePeca();
				realizaMovimento = movimentoEscolhido(posicaoEscolhida);
			} while (realizaMovimento == null && posicaoEscolhida != null);
			if (realizaMovimento != null)
				return new Jogada(posicaoEscolhida, realizaMovimento, TipoJogada.ANDAR);
		}
		return null;
	}

}
