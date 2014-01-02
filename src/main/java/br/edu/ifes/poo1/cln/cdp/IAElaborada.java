package br.edu.ifes.poo1.cln.cdp;

import java.util.ArrayList;

public class IAElaborada extends Maquina {

	// FIXME EsTa para exemplo
	Pessoa pes = new Pessoa("", CorJogador.BRANCO);

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
	private ArrayList<NoArvore> listaNos = new ArrayList<NoArvore>();

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
	public ArrayList<NoArvore> geraFilhos(NoArvore noPai,
			ArrayList<NoArvore> novaListaNos) throws CasaOcupadaException,
			CloneNotSupportedException, JogadaInvalidaException {

		// Cria uma lista de tabuleiros que contém todos os estados possíveis a
		// serem gerados dado o nó pai
		ArrayList<Tabuleiro> listaTabuleiros = new ArrayList<Tabuleiro>();

		// Cria um jogador
		CorJogador corJogador = null;

		// Se estamos em um nível MIN, devemos pegar todas as jogadas da máquina
		if (noPai.getNivel() == TipoNivel.MAX)
			corJogador = this.getCor();
		// Se estamos em um nível MAX, devemos pegar todas as jogadas da
		// pessoa
		else
			corJogador = pes.getCor();
		listaTabuleiros = noPai.getTabuleiroNo().proximosEstadosPossiveis(
				corJogador);

		// Para cada tabuleiro da lista de tabuleiros
		for (Tabuleiro tabuleiroEstado : listaTabuleiros) {
			// Crie um nó que reconheça seu pai
			NoArvore no = new NoArvore(noPai, tabuleiroEstado);
			// Verifica se determinada jogada deixa o tabuleiro em xeque ou xeque-mate
			
			if (tabuleiroEstado.verificarXeque(corJogador)) {
			//	System.out.println("\n");
			//	tabuleiroEstado.digaTabuleiro();
				no.setXeque();
				if (tabuleiroEstado.verificarXequeMate(corJogador))
					no.setXequeMate();
			}

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
	 * @throws CloneNotSupportedException
	 * @throws JogadaInvalidaException
	 */
	public boolean criaCamada() throws CasaOcupadaException,
			CloneNotSupportedException, JogadaInvalidaException {
		ArrayList<NoArvore> novaListaNos = new ArrayList<NoArvore>();
		// Para cada nó da atual lista de nós
		for (NoArvore noPai : listaNos) {
			long fim = System.currentTimeMillis();
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
	 * @throws JogadaInvalidaException
	 * @throws CasaOcupadaException
	 */
	public ArrayList<Jogada> jogadasPossiveis(NoArvore no)
			throws JogadaInvalidaException {
		ArrayList<Jogada> listaJogadas = no.getTabuleiroNo()
				.geraJogadasPossiveis(this.getCor());
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
			System.out.println("tamanho da lista de nós: " + listaNos.size());
			atingiuTempoMaximo = criaCamada();
		}

		// Insiro os valores nos nós folhas
		inserirValorFolhas(listaNos);

		// Realizo a busca em profundidade (aplicando minimax e poda alfa beta)
		busca.buscaEmProfundidade(raiz);

		// Crio a lista de jogadas
		ArrayList<Jogada> listaJogadas = jogadasPossiveis(raiz);
		// Para cada nó na lista de adjacência do pai (começa de 1 pois o nó pai
		// não tem primeiro valor na lista de adjacência pois não tem pai
		for (int indice = 1; indice < raiz.getListaAdjacencia().size(); indice++)
			// Se o nó possuir o mesmo valor do pai, então o tabuleiro escolhido
			// foi o desse nó
			if (raiz.getValor() == raiz.getListaAdjacencia().get(indice)
					.getValor()) {
				System.out.println("Melhor jogada alcançada");
				return listaJogadas.get(indice - 1);
			}
		// Retorno null caso não tenha jogada a ser realizada
		return null;

	}
}
