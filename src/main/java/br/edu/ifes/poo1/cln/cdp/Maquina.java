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
	private Posicao escolhePeca() {
		int indiceAleatorio;
		int casaSelecionada;
		Posicao posicao;
		do {
			// Cria um valor correspondente a uma posição aleatória
			indiceAleatorio = geraIndiceAleatorio(posicoesPossiveis);
			casaSelecionada = posicoesPossiveis.get(indiceAleatorio);
			posicao = new Posicao(((casaSelecionada - 1) % 8) + 1,
					((casaSelecionada - 1) / 8) + 1);
			// System.out.println("esta vazio?" + tabuleiro.estaVazio(posicao));
			// System.out.println("esta inimigo?"
			// + tabuleiro.estaInimigo(this, posicao));
			// Se a posição selecionada pelo tabuleiro for diferente de uma peça
			// da máquina
			if (tabuleiro.estaInimigo(this, posicao) == true
					|| tabuleiro.estaVazio(posicao) == true) {
				//System.out.println("Peça removida");
				// Removemos a posição do escopo de posições possiveis
				posicoesPossiveis.remove(indiceAleatorio);
			}
			// Faça isso enquanto...
		} while (posicoesPossiveis.size() != 0
				&& (tabuleiro.estaInimigo(this, posicao) == true || tabuleiro
						.estaVazio(posicao)));
		System.out.println(casaSelecionada);
		// Se todas as posições (peças) já foram analisadas. Retorne nulo
		if (posicoesPossiveis.size() == 0)
			return null;
		// Retorne a posição possível
		return (new Posicao(((casaSelecionada - 1) % 8) + 1,
				((casaSelecionada - 1) / 8) + 1));
	}

	/**
	 * Método que irá gerar um ataque
	 * 
	 * @param origem
	 * @return
	 */
	private Posicao ataqueEscolhido(Posicao origem) {
		Peca peca = tabuleiro.espiarPeca(origem);
		//System.out.println("verifica: "+peca.podeAndar(new Posicao(6,4), new Posicao(6,8), tabuleiro));
		ArrayList<Integer> ataquesPossiveis = new ArrayList<Integer>(64);
		ataquesPossiveis = inicializaLista(ataquesPossiveis);
		int indiceAleatorio;
		int casaSelecionada;
		Posicao destino;
		do {
			indiceAleatorio = geraIndiceAleatorio(ataquesPossiveis);
			//System.out.println("Indice Aleatorio: " + indiceAleatorio);
			casaSelecionada = ataquesPossiveis.get(indiceAleatorio);
			//System.out.println("casa selecionada: " + casaSelecionada);
			destino = new Posicao(((casaSelecionada - 1) % 8) + 1,
					((casaSelecionada - 1) / 8) + 1);
			//System.out.println(peca.podeAtacar(origem, destino, tabuleiro));
			if (peca.podeAtacar(origem, destino, tabuleiro) == false)
				ataquesPossiveis.remove(indiceAleatorio);
		//	System.out.println("Ataques: "+ataquesPossiveis);
		} while (peca.podeAtacar(origem, destino, tabuleiro) == false
				&& ataquesPossiveis.size() != 0);
		if (ataquesPossiveis.size() == 0) {
			System.out.println("falhei");
			return null;
		}// Retorne o ataque
		//System.out.println("atacar!");
	//	System.out.println(destino.getLinha() + " " + destino.getColuna());
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
	public boolean escolherJogada() {

		posicoesPossiveis = inicializaLista(posicoesPossiveis);
		Posicao posicaoEscolhida;
		Posicao realizaAtaque;
		// Tenta realizar um ataque
		do {
			posicaoEscolhida = escolhePeca();
			System.out.println("posicao escolhida: "+posicaoEscolhida);
			realizaAtaque = ataqueEscolhido(posicaoEscolhida);
			//System.out.println("realizaAtaque: "+realizaAtaque);
			posicoesPossiveis.remove(posicaoEscolhida);
		} while (realizaAtaque == null && posicoesPossiveis != null);
		// Se um ataque pode ser realizado
		if (realizaAtaque != null) {
			return true;
			// return new Jogada(posicaoEscolhida, realizaAtaque,
			// TipoJogada.ATACAR);
			// Tenta realizar movimento (caso um ataque não possa ser realizado)
		} else {
			posicoesPossiveis = inicializaLista(posicoesPossiveis);
			Posicao realizaMovimento;
			// Tenta realizar um movimento
			do {
				posicaoEscolhida = escolhePeca();
				System.out.println("Peça foi escolhida para movimentar\n"
						+ "linha " + posicaoEscolhida.getLinha() + " coluna: "
						+ posicaoEscolhida.getColuna());
				realizaMovimento = movimentoEscolhido(posicaoEscolhida);
			} while (realizaMovimento == null && posicaoEscolhida != null);
			if (realizaMovimento != null) {
				System.out.println("movimentou");
				return true;
				// return new Jogada(posicaoEscolhida, realizaMovimento,
				// TipoJogada.ANDAR);
			}
		}
		return false;
	}

}
