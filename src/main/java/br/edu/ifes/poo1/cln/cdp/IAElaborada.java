package br.edu.ifes.poo1.cln.cdp;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;

public class IAElaborada extends Maquina {

	/**
	 * Informa com quantas camadas estamos lidando no problema (O valor pode ser
	 * adequado na interface conforme dificuldade escolhida pelo jogador)
	 */
	private final int ALCANCEMAQUINA;

	/**
	 * Informa o tempo máximo que levará em buscar camadas
	 */
	private final int TEMPOMAXIMO;

	/**
	 * Informa o início desse tempo máximo
	 */
	private long inicio;

	/**
	 * Objeto que permite a maipulação do método na classe RealizaBusca
	 */
	private RealizaBusca busca = new RealizaBusca();

	/**
	 * Lista que irá conter todos os nós da camada mais inferior trabalhada
	 */
	private List<NoArvore> listaNos = new ArrayList<NoArvore>();

	/**
	 * Classe construtora de IAElaborada
	 * 
	 * @param nome
	 * @param cor
	 * @param alcance
	 */
	public IAElaborada(String nome, CorJogador cor, int alcance, int tempoMaximo) {
		super(nome, cor);
		this.ALCANCEMAQUINA = alcance;
		this.TEMPOMAXIMO = tempoMaximo;
	}

	/**
	 * Método que gera todos os nós de uma camada
	 * 
	 * @param listaNos
	 * @return
	 * @throws CasaOcupadaException
	 * @throws CloneNotSupportedException
	 * @throws JogadaInvalidaException
	 * @throws InterruptedException
	 */
	public boolean criaCamada() throws CasaOcupadaException,
			CloneNotSupportedException, JogadaInvalidaException,
			InterruptedException {

		// Construa as partes
		LeituraCamada parte1 = new LeituraCamada(0,
				(int) listaNos.size() * 1 / 3, listaNos);
		LeituraCamada parte2 = new LeituraCamada((int) listaNos.size() * 1 / 3,
				(int) listaNos.size() * 2 / 3, listaNos);
		LeituraCamada parte3 = new LeituraCamada((int) listaNos.size() * 2 / 3,
				listaNos.size(), listaNos);

		// Construa as threads
		Thread t1 = new Thread(parte1);
		Thread t2 = new Thread(parte2);
		Thread t3 = new Thread(parte3);

		// Inicia as threads
		t1.start();
		t2.start();
		t3.start();

		// Enquanto as threads estão rodando
		while (t1.getState() == State.RUNNABLE
				|| t2.getState() == State.RUNNABLE
				|| t3.getState() == State.RUNNABLE) {
			long fim = System.currentTimeMillis();
			// Se o tempo máximo for alcançado
			if ((fim - inicio) / 1000 > this.TEMPOMAXIMO) {
				// Interrompa as threads e retorne true
				t1.interrupt();
				t2.interrupt();
				t3.interrupt();
				return true;
				// TODO poderia haver um else que só permitisse esse loop a cada
				// um segundo
			}
		}

		// Crie a lista de nós
		listaNos = parte1.getNovaListaNos();
		listaNos.addAll(parte2.getNovaListaNos());
		listaNos.addAll(parte3.getNovaListaNos());
		return false;
	}

	/**
	 * Método que insere nos nós folhas os seus respectivos valores
	 * 
	 * @param listaNos
	 */
	public void inserirValorFolhas(List<NoArvore> listaNos) {
		for (NoArvore no : listaNos) {
			no.setValor(no.getEstado().getTabuleiro().valorTabuleiro());
			no.setTemValor();
		}
	}

	/**
	 * Método que escolhe a jogada da máquina
	 * 
	 * @throws CasaOcupadaException
	 * @throws CloneNotSupportedException
	 * @throws JogadaInvalidaException
	 * @throws InterruptedException
	 */
	public Jogada escolherJogada(Tabuleiro tabuleiroAtual)
			throws CasaOcupadaException, CloneNotSupportedException,
			JogadaInvalidaException, InterruptedException {

		// Crio nó raiz e informo a ele o tabuleiro atual
		NoArvore raiz = new NoArvore(new Estado(null, tabuleiroAtual));
		// Inserimos, inicialmente apenas o nó raiz
		listaNos.add(raiz);
		// Marca quando a análise foi iniciada
		inicio = System.currentTimeMillis();
		// Funciona verificando se foi atingido o tempo máximo estabelecido
		boolean atingiuTempoMaximo = false;
		// Crio a árvore de possibilidades
		for (int camada = 1; camada <= ALCANCEMAQUINA
				&& atingiuTempoMaximo == false; camada++) {
			atingiuTempoMaximo = criaCamada();
		}

		// Insiro os valores nos nós folhas
		inserirValorFolhas(listaNos);

		// Realizo a busca em profundidade (aplicando minimax e poda alfa beta)
		busca.buscaEmProfundidade(raiz);

		// Se o tabuleiro não já se encontra em xeque-mate
		if (raiz.isXequeMate() == false) {

			// Para cada nó na lista de adjacência do pai (começa de 1 pois o nó
			// pai não tem primeiro valor na lista de adjacência pois não tem
			// pai
			for (int indice = 1; indice < raiz.getListaAdjacencia().size(); indice++)
				// Se o nó possuir o mesmo valor do pai, então o tabuleiro
				// escolhido foi o desse nó
				if (raiz.getValor() == raiz.getListaAdjacencia().get(indice)
						.getValor()) {
					return raiz.getListaAdjacencia().get(indice).getEstado()
							.getJogada();
				}
		}
		// Retorno null caso não tenha jogada a ser realizada ou caso o
		// tabuleiro já se encontre em xeque-Mate
		return null;

	}
}
