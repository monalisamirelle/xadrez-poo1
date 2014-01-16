package br.edu.ifes.poo1.cln.cdp;

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
	 * Método que gera, dado um nó, os filhos desse nó
	 * 
	 * @param listaNos
	 * @param novaListaNos
	 * @return
	 * @throws CasaOcupadaException
	 * @throws CloneNotSupportedException
	 * @throws JogadaInvalidaException
	 */
	public List<NoArvore> geraFilhos(NoArvore noPai, List<NoArvore> novaListaNos)
			throws CasaOcupadaException, CloneNotSupportedException,
			JogadaInvalidaException {

		// Cria uma lista de tabuleiros que contém todos os estados possíveis a
		// serem gerados dado o nó pai
		List<Estado> listaEstados = new ArrayList<Estado>();
		// Se o nó pai não está em xeque-mate, devemos considerar seus filhos
		if (noPai.isXequeMate() == false) {
			// Crie uma lista de tabuleiros com todas as jogadas possíveis de
			// serem realizadas naquele tabuleiro
			listaEstados = noPai.getEstado().getTabuleiro()
					.proximosEstadosPossiveis(noPai.getCorNo());
			// Para cada estado da lista de estados
			for (Estado estado : listaEstados) {
				noPai.getCorNo();
				// Se o tabuleiro pertencente a esse estado não estiver em xeque
				// ou xeque mate. Exemplo: Se a preta acabou de mover, o jogo só
				// deve incluir os movimentos que não deixam o rei preto em
				// xeque ou xeque-mate
				if (!(estado.getTabuleiro().verificarXeque(noPai.getCorNo()) || estado
						.getTabuleiro().verificarXequeMate(noPai.getCorNo()))) {
					// Crie um nó que reconheça seu pai e armazene o estado
					NoArvore no = new NoArvore(noPai, estado);
					// $ Faça esse nó ser adicionado a nova lista de nós
					novaListaNos.add(no);
				} else
					System.out.println("/////// JOGADAS QUE LEVAM A XEQUES ///////");
			}
		} else {
			System.out.println("");
			System.out.println("O NÓ EM QUESTÃO JÁ SE ENCONTRA EM XEQUE-MATE");
			noPai.getEstado().getTabuleiro().digaTabuleiro();
			System.out.println("");
			novaListaNos.add(noPai);
		}
		return novaListaNos;
	}

	/**
	 * Método que gera todos os nós de uma camada
	 * 
	 * @param listaNos
	 * @return
	 * @throws CasaOcupadaException
	 * @throws CloneNotSupportedException
	 * @throws JogadaInvalidaException
	 */
	public boolean criaCamada() throws CasaOcupadaException,
			CloneNotSupportedException, JogadaInvalidaException {
		List<NoArvore> novaListaNos = new ArrayList<NoArvore>();
		// Para cada nó da atual lista de nós
		for (NoArvore noPai : listaNos) {
			long fim = System.currentTimeMillis();
			// Se o tempo não tiver acabad
			if ((fim - inicio) / 1000 < this.TEMPOMAXIMO)
				// Faça a nova lista de nós receber os filhos
				novaListaNos = geraFilhos(noPai, novaListaNos);
			else
				return true;
		}
		listaNos = novaListaNos;
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
	 */
	public Jogada escolherJogada(Tabuleiro tabuleiroAtual)
			throws CasaOcupadaException, CloneNotSupportedException,
			JogadaInvalidaException {

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
			System.out.println("\n\nCamada: " + camada);
			atingiuTempoMaximo = criaCamada();
			System.out.println("tamanho da lista de nós: " + listaNos.size());
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
					System.out.println("Melhor jogada alcançada");
					System.out.println("Saia da coluna "
							+ raiz.getListaAdjacencia().get(indice).getEstado()
									.getJogada().getOrigem().getColuna()
							+ " e linha "
							+ raiz.getListaAdjacencia().get(indice).getEstado()
									.getJogada().getOrigem().getLinha()
							+ " e vá para a coluna "
							+ raiz.getListaAdjacencia().get(indice).getEstado()
									.getJogada().getDestino().getColuna()
							+ " e linha "
							+ raiz.getListaAdjacencia().get(indice).getEstado()
									.getJogada().getDestino().getLinha());
					return raiz.getListaAdjacencia().get(indice).getEstado()
							.getJogada();
				}
		}
		System.out.println("Erro! Jogo já se encontra em xeque-mate");
		// Retorno null caso não tenha jogada a ser realizada ou caso o
		// tabuleiro já se encontre em xeque-Mate
		return null;

	}
}
