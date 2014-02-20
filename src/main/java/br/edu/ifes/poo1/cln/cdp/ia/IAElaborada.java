package br.edu.ifes.poo1.cln.cdp.ia;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
import br.edu.ifes.poo1.cln.cdp.TabuleiroXadrez;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.tipos.TipoJogador;

public class IAElaborada extends Maquina {

	/**
	 * Se você deseja uma IA inteligente informe para o método construtor
	 * maquinaInteligente -> true; Se você deseja uma IA burra informe para o
	 * método construtor maquinaInteligente -> false; Se você deseja uma IA
	 * agressiva (buscando sempre realizar xeques e comer peças) informe para o
	 * método construtor alcance -> 1
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	 * Informa se a máquina será inteligente ou burra
	 */
	private final boolean MAQUINAINTELIGENTE;

	/**
	 * Informa o nível que o nó raiz deve responder (MAX ou MIN)
	 */
	TipoNivel nivel;

	/**
	 * Informa o início desse tempo máximo
	 */
	private long inicio;

	/**
	 * Classe construtora de IAElaborada
	 * 
	 * @param nome
	 * @param cor
	 * @param alcance
	 */
	public IAElaborada(String nome, TipoCorJogador cor, int alcance,
			int tempoMaximo, boolean maquinaInteligente) {
		super(nome, cor, TipoJogador.IAELABORADA);
		this.ALCANCEMAQUINA = alcance;
		this.TEMPOMAXIMO = tempoMaximo;
		this.MAQUINAINTELIGENTE = maquinaInteligente;
		if (maquinaInteligente)
			this.nivel = TipoNivel.MAX;
		else
			this.nivel = TipoNivel.MIN;
	}

	/**
	 * Método que gera todos os nós de uma camada
	 * 
	 * @param listaNos
	 * @return
	 * @throws InterruptedException
	 */
	public List<NoArvore> criaCamada(List<NoArvore> listaNos)
			throws InterruptedException {
		// GeraCamada parte1 = new GeraCamada(0, (int) listaNos.size(),
		// listaNos);
		// Construa as partes
		GeraCamada parte1 = new GeraCamada(0, (int) listaNos.size() * 1 / 4,
				listaNos);
		GeraCamada parte2 = new GeraCamada((int) listaNos.size() * 1 / 4,
				(int) listaNos.size() * 2 / 4, listaNos);
		GeraCamada parte3 = new GeraCamada((int) listaNos.size() * 2 / 4,
				(int) listaNos.size() * 3 / 4, listaNos);
		GeraCamada parte4 = new GeraCamada((int) listaNos.size() * 3 / 4,
				listaNos.size(), listaNos);

		// Construa as threads
		Thread t1 = new Thread(parte1);
		Thread t2 = new Thread(parte2);
		Thread t3 = new Thread(parte3);
		Thread t4 = new Thread(parte4);

		// Inicia as threads
		t1.start();
		t2.start();
		t3.start();
		t4.start();

		// Enquanto as threads estão rodando
		while (t1.getState() == State.RUNNABLE
				|| t2.getState() == State.RUNNABLE
				|| t3.getState() == State.RUNNABLE
				|| t4.getState() == State.RUNNABLE) {
			long fim = System.currentTimeMillis();
			// Se o tempo máximo for alcançado
			if ((fim - inicio) / 1000 > this.TEMPOMAXIMO) {

				// Interrompa as threads e retorne true
				parte1.setAcabou();
				parte2.setAcabou();
				parte3.setAcabou();
				parte4.setAcabou();

				// Espera as threads morrerem
				t1.join();
				t2.join();
				t3.join();
				t4.join();

				return listaNos;
			}
		}

		// Crie a lista de nós
		listaNos = parte1.getNovaListaNos();
		listaNos.addAll(parte2.getNovaListaNos());
		listaNos.addAll(parte3.getNovaListaNos());
		listaNos.addAll(parte4.getNovaListaNos());
		return listaNos;
	}

	// FIXME apagar no final, método desnecessário
	public boolean criaCamadaM(List<NoArvore> listaNos)
			throws InterruptedException {
		// Construa as partes
		GeraCamada parte1 = new GeraCamada(0, (int) listaNos.size(), listaNos);

		// Construa as threads
		Thread t1 = new Thread(parte1);

		// Inicia as threads
		t1.start();

		// Enquanto as threads estão rodando
		while (t1.getState() == State.RUNNABLE) {
			long fim = System.currentTimeMillis();
			// Se o tempo máximo for alcançado
			if ((fim - inicio) / 1000 > this.TEMPOMAXIMO) {

				// Interrompa as threads e retorne true
				parte1.setAcabou();

				// Espera as threads morrerem
				t1.join();

				return true;
			}
		}

		// Crie a lista de nós
		listaNos = parte1.getNovaListaNos();
		return false;
	}

	/**
	 * Método que insere nos nós folhas os seus respectivos valores
	 * 
	 * @param listaNos
	 */
	public void inserirValorFolhas(List<NoArvore> listaNos) {
		for (NoArvore no : listaNos) {
			int xequeMate = 0;
			// Verifica se o nó está em xeque. Influencia no valor do tabuleiro
			if (no.isXequeMate())
				if (no.getCorNo() == this.cor)
					xequeMate = -1;
				else
					xequeMate = 1;
			no.setValor(no.getEstado().getTabuleiro()
					.valorTabuleiro(this.cor, xequeMate));
			no.setTemValor();
		}
	}

	/**
	 * Método que escolhe a jogada da máquina
	 * 
	 * @throws CasaOcupadaException
	 * @throws JogadaInvalidaException
	 * 
	 */
	public Jogada escolherJogada(TabuleiroXadrez tabuleiroAtual)
			throws CasaOcupadaException, JogadaInvalidaException {
		// Inicia uma lista de nós
		List<NoArvore> listaNos = new ArrayList<NoArvore>();
		// Crio nó raiz e informo a ele o tabuleiro atual
		NoArvore raiz = new NoArvore(this.cor, this.nivel, new Estado(null,
				tabuleiroAtual));
		// Inserimos, inicialmente apenas o nó raiz
		listaNos.add(raiz);
		// Marca quando a análise foi iniciada
		inicio = System.currentTimeMillis();
		// Funciona verificando se foi atingido o tempo máximo estabelecido
		boolean atingiuTempoMaximo = false;
		// Crio a árvore de possibilidades
		for (int camada = 1; camada <= ALCANCEMAQUINA
				&& atingiuTempoMaximo == false; camada++) {
			try {
				listaNos = criaCamada(listaNos);
				// Faça uma verificação de tempo para ver se deve rodar uma
				// próxima camada
				long fim = System.currentTimeMillis();
				// Se o tempo máximo for alcançado
				if ((fim - inicio) / 1000 > this.TEMPOMAXIMO)
					atingiuTempoMaximo = true;
			} catch (InterruptedException e) {
				// Se apresentar algum erro de jogada, tente a IA randomica
				return suporte(tabuleiroAtual);
			}
		}

		// Insiro os valores nos nós folhas
		inserirValorFolhas(listaNos);

		// Realizo a busca em profundidade (aplicando minimax e poda alfa
		// beta)
		RealizaBusca busca = new RealizaBusca();
		busca.buscaEmProfundidade(raiz);

		// Cria lista de jogadas que possuem valor igual ao nó raiz
		List<Jogada> possivelJogada = new ArrayList<Jogada>();

		// Se o tabuleiro não já se encontra em xeque-mate
		if (raiz.isXequeMate() == false) {
			// Para cada nó na lista de adjacência do pai (começa de 1 pois
			// o nó pai não tem primeiro valor na lista de adjacência pois não
			// tem pai
			for (int indice = 1; indice < raiz.getListaAdjacencia().size(); indice++)
				// Se o nó possuir o mesmo valor do pai, então o tabuleiro
				// escolhido foi o desse nó
				if (raiz.getValor() == raiz.getListaAdjacencia().get(indice)
						.getValor()) {
					possivelJogada.add(raiz.getListaAdjacencia().get(indice)
							.getEstado().getJogada());
				}
		}
		// Se IA encontrar uma jogada
		if (!possivelJogada.isEmpty()) {
			Random random = new Random();
			return possivelJogada.get(random.nextInt(possivelJogada.size()));
		}
		// Controle caso IA não seja capaz de encontrar jogada alguma
		else {
			return suporte(tabuleiroAtual);
		}
	}

	/**
	 * Método criado para auxiliar caso a IA falhe em parar as threads ou em
	 * encontrar uma jogada
	 * 
	 * @param tabuleiroAtual
	 * @return
	 * @throws CasaOcupadaException
	 * @throws JogadaInvalidaException
	 */
	public Jogada suporte(TabuleiroXadrez tabuleiroAtual)
			throws CasaOcupadaException, JogadaInvalidaException {
		IARandomica suporteIa = new IARandomica(this.getCor());
		return suporteIa.escolherJogada(tabuleiroAtual);
	}

	public int getALCANCEMAQUINA() {
		return ALCANCEMAQUINA;
	}

	public int getTEMPOMAXIMO() {
		return TEMPOMAXIMO;
	}

	public boolean isMAQUINAINTELIGENTE() {
		return MAQUINAINTELIGENTE;
	}
}
