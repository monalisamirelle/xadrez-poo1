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
		List<Tabuleiro> listaTabuleiros = new ArrayList<Tabuleiro>();
		// Se o nó pai não está em xeque-mate, devemos considerar seus filhos
		if (noPai.isXequeMate() == false) {
			// Crie uma lista de tabuleiros com todas as jogadas possíveis de
			// serem realizadas por aquele tabuleiro
			listaTabuleiros = noPai.getTabuleiroNo().proximosEstadosPossiveis(
					noPai.getCorNo());
			// Para cada tabuleiro da lista de tabuleiros
			for (Tabuleiro tabuleiroEstado : listaTabuleiros) {
				// Crie um nó que reconheça seu pai
				NoArvore no = new NoArvore(noPai, tabuleiroEstado);
				// Se o nó não está em xeque, o movimento é válido
				if (no.isXeque() == false)

					// TODO está falando jogadas de xeque-mate que não são
					// xeque-mate
					// if (no.isXequeMate() == true){
					// no.getTabuleiroNo().digaTabuleiro();
					// System.out.println("");
					// }

					// Faça esse nó ser adicionado a nova lista de nós
					novaListaNos.add(no);
			}
		} else {
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
	 * @throws JogadaInvalidaException
	 * @throws CasaOcupadaException
	 */
	public List<Jogada> jogadasPossiveis(NoArvore no)
			throws JogadaInvalidaException {
		List<Jogada> listaJogadas = no.getTabuleiroNo().geraJogadasPossiveis(
				this.getCor());
		return listaJogadas;
	}

	/**
	 * Método que escolhe a jogada da máquina
	 * 
	 * @throws CasaOcupadaException
	 * @throws CloneNotSupportedException
	 * @throws JogadaInvalidaException
	 */
	public Jogada escolherJogada(Tabuleiro tab) throws CasaOcupadaException,
			CloneNotSupportedException, JogadaInvalidaException {

		// Crio nó raiz e informo a ele o tabuleiro atual
		NoArvore raiz = new NoArvore(tab);

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
			// Crio a lista de jogadas
			List<Jogada> listaJogadas = jogadasPossiveis(raiz);
			// Para cada nó na lista de adjacência do pai (começa de 1 pois o nó
			// pai
			// não tem primeiro valor na lista de adjacência pois não tem pai
			for (int indice = 1; indice < raiz.getListaAdjacencia().size(); indice++)
				// Se o nó possuir o mesmo valor do pai, então o tabuleiro
				// escolhido
				// foi o desse nó
				if (raiz.getValor() == raiz.getListaAdjacencia().get(indice)
						.getValor()) {
					System.out.println("Melhor jogada alcançada");
					return listaJogadas.get(indice - 1);
				}
		}
		// Retorno null caso não tenha jogada a ser realizada ou caso o
		// tabuleiro já se encontre em xeque-Mate
		return null;

	}
}
