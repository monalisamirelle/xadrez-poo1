package br.edu.ifes.poo1.cln.cdp;

import java.util.ArrayList;

public class IAElaborada extends Maquina {

	// Informa com quantas camadas estamos lidando no problema (5 é o oficial,
	// mas valor pode ser adequado na interface conforme dificuldade escolhida
	// pelo jogador)
	private final int ALCANCEMAQUINA = 1;

	RealizaBusca busca = new RealizaBusca();

	public IAElaborada(String nome, CorJogador cor) {
		super(nome, cor);
	}

	/**
	 * Método que gera, dado um nó, os filhos desse nó
	 * 
	 * @param listaNos
	 * @param novaListaNos
	 * @return
	 * @throws CasaOcupadaException
	 */
	public ArrayList<NoArvore> geraFilhos(NoArvore noPai,
			ArrayList<NoArvore> novaListaNos) throws CasaOcupadaException {
		ArrayList<Tabuleiro> listaTabuleiros = new ArrayList<Tabuleiro>();
		// Cria uma lista de tabuleiros que contém todos os estados possíveis a
		// serem gerados dado o nó pai
		// Se estamos em um nível MAX, devemos pegar todas as jogadas da máquina
		if (noPai.getNivel() == TipoNivel.MIN) {
			listaTabuleiros = noPai.getTabuleiroNo().proximosEstadosPossiveis(
					this);
			// Se estamos em um nível MIN, devemos pegar todas as jogadas da
			// pessoa
		}
		// FIXME preciso dizer que essa operação deve ser realizada passando por
		// parâmetro o objeto da class pessoa, ou seja, o jogador que está
		// jogando contra a máquina, quando era pra passar a máquina eu usei ali
		// em cima ...proximosEstadosPossiveis(this), mas... agora, ao invés de
		// 'this' eu preciso passar a pessoa
		// else {
		// listaTabuleiros = noPai.getTabuleiroNo().proximosEstadosPossiveis(
		// );
		// }
		// Para cada tabuleiro da lista de tabuleiros
		for (Tabuleiro tabuleiroEstado : listaTabuleiros) {
			// Crie um nó que reconheça seu pai
			NoArvore no = new NoArvore(noPai, tabuleiroEstado);
			// Faça esse nó ser adicionado a nova lista de nós
			novaListaNos.add(no);
		}
		return novaListaNos;
	}

	/**
	 * Método que gera todos os nós de uma camada
	 * 
	 * @param listaNos
	 * @return
	 * @throws CasaOcupadaException
	 */
	public ArrayList<NoArvore> criaCamada(ArrayList<NoArvore> listaNos)
			throws CasaOcupadaException {
		ArrayList<NoArvore> novaListaNos = new ArrayList<NoArvore>();
		// Para cada nó da atual lista de nós
		for (NoArvore noPai : listaNos)
			// Faça a nova lista de nós receber os filhos
			novaListaNos = geraFilhos(noPai, novaListaNos);
		return novaListaNos;
	}

	/**
	 * Método que insere nos nós folhas os seus respectivos valores
	 * 
	 * @param listaNos
	 */
	public void inserirValorFolhas(ArrayList<NoArvore> listaNos) {
		for (NoArvore no : listaNos) {
			no.setValor(no.getTabuleiroNo().valorTabuleiro());
			no.setTemValor();
		}
	}

	/**
	 * Cria a lista de jogadas que podem ser realizadas por um nó (no caso,
	 * estamos interessados na lista de jogadas que podem ser realizadas pelo nó
	 * raiz)
	 * 
	 * @param no
	 * @return
	 * @throws CasaOcupadaException
	 */
	// OK
	public ArrayList<Jogada> jogadasPossiveis(NoArvore no) {
		ArrayList<Jogada> listaJogadas = no.getTabuleiroNo()
				.geraJogadasPossiveis(this);
		return listaJogadas;
	}

	/**
	 * Método que escolhe a jogada da máquina
	 * 
	 * @throws CasaOcupadaException
	 */
	public Jogada escolherJogada() throws CasaOcupadaException {

		// Crio nó raiz e informo a ele o tabuleiro atual
		NoArvore raiz = new NoArvore(tabuleiro);

		// Crio uma lista que irá conter todos os nós
		ArrayList<NoArvore> listaNos = new ArrayList<NoArvore>();

		// Inserimos, inicialmente apenas o nó raiz
		listaNos.add(raiz);

		// Crio a árvore de possibilidades
		for (int camada = 1; camada <= ALCANCEMAQUINA; camada++)
			listaNos = criaCamada(listaNos);

		// Insiro os valores nos nós folhas
		inserirValorFolhas(listaNos);

		// Realizo a busca em profundidade (aplicando minimax e poda alfa beta)
		busca.buscaEmProfundidade(raiz);

		// Crio a lista de jogadas
		ArrayList<Jogada> listaJogadas = jogadasPossiveis(raiz);

		// Para cada nó na lista de adjacência do pai
		for (int indice = 1; indice < raiz.getListaAdjacencia().size(); indice++)
			// Se o nó possuir o mesmo valor do pai, então o tabuleiro escolhido
			// foi o desse nó
			if (raiz.getValor() == raiz.getListaAdjacencia().get(indice)
					.getValor())
				return listaJogadas.get(indice);
		return null;
	}
}
