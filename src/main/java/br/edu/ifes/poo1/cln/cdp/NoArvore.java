package br.edu.ifes.poo1.cln.cdp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erro
 * 
 */
public class NoArvore {

	// Nó que é pai do nó em questão
	private NoArvore noPai;

	// Valor de um nó
	private int valor;

	// Indica se o n� em quest�o j� possui algum valor
	private boolean temValor;

	// Cada n� ter� uma lista de adjac�ncia indicando seus n�s de liga��o direta
	private List<NoArvore> listaAdjacencia = new ArrayList<NoArvore>();

	// Irá servir para indicar a posição na lista de adjacencia de um no
	private int posicaoListaAdjacencia;

	// Marca o nó como visitado
	private boolean marcado;
	
	// Cor do nó
	private CorJogador cor; 
	
	// Se o nó se encontra no nível max ou min
	private TipoNivel nivel;

	// Ir� refletir se o nó foi podado ou não
	private boolean podou;

	// Cada nó terá um um estado
	private Estado estado;

	// Verifica se o jogador daquele nó está provocando um xeque-mate
	private boolean xequeMate;

	/**
	 * Classe construtora de nó raiz
	 * 
	 * @param noPai
	 * @param valor
	 * @throws CasaOcupadaException
	 * @throws JogadaInvalidaException
	 * @throws CloneNotSupportedException
	 */
	public NoArvore(CorJogador cor, TipoNivel nivel, Estado estado) throws CasaOcupadaException,
			CloneNotSupportedException, JogadaInvalidaException {
		this.noPai = null;
		this.temValor = false;
		this.insereListaAdjacencia(noPai);
		this.posicaoListaAdjacencia = 0;
		this.marcado = false;
		this.cor = cor;
		// TODO podemos criar um if nesse nivel para gerar máquinas burras
		this.nivel = nivel;
		this.podou = false;
		this.estado = estado;
		this.xequeMate = estado.getTabuleiro().verificarXequeMate(
				CorJogador.BRANCO)
				|| estado.getTabuleiro().verificarXequeMate(CorJogador.PRETO);
	}

	/**
	 * Classe construtora de n�
	 * 
	 * @param noPai
	 * @param valor
	 * @throws CasaOcupadaException
	 * @throws JogadaInvalidaException
	 * @throws CloneNotSupportedException
	 */
	public NoArvore(NoArvore noPai, Estado estado) throws CasaOcupadaException,
			CloneNotSupportedException, JogadaInvalidaException {
		this.noPai = noPai;
		noPai.addFilho(this);
		this.temValor = false;
		this.insereListaAdjacencia(noPai);
		this.posicaoListaAdjacencia = this.getNoPai().getListaAdjacencia()
				.size();
		this.marcado = false;
		this.cor = CorJogador.getCorOposta(noPai.getCorNo());
		this.nivel = coloqueNivel();
		this.podou = false;
		this.estado = estado;
		this.xequeMate = estado.getTabuleiro().verificarXequeMate(
				CorJogador.getCorOposta(noPai.getCorNo()));
	}

	/**
	 * Pegar o valor de um n�
	 * 
	 * @return
	 */
	public int getValor() {
		return this.valor;
	}

	/**
	 * Modifica o valor de um n�
	 * 
	 * @param valor
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}

	/**
	 * Verifica se o n� possui valor ou n�o
	 * 
	 * @return
	 */
	public boolean getTemValor() {
		return this.temValor;
	}

	/**
	 * Muda o estado o n� quando ele receber um valor
	 */
	public void setTemValor() {
		this.temValor = true;
	}

	/**
	 * Retorna se um n� possui n� pai
	 * 
	 * @return
	 */
	public boolean temNoPai() {
		return (this.getNoPai() != null);
	}

	/**
	 * Retorna o n� pai de um n�
	 * 
	 * @return
	 */
	public NoArvore getNoPai() {
		// Se o n� possui pai
		if (this.noPai != null)
			return noPai;
		return null;
	}

	/**
	 * Retorna se um n� possui n�s filhos
	 * 
	 * @return
	 */
	public boolean temNoFilho() {
		// Se n�o for o n� raiz
		if (this.temNoPai()) {
			if (this.getListaAdjacencia().size() > 2)
				return true;
			// Se for o n� raiz
		} else {
			if (this.getListaAdjacencia().size() > 1)
				return true;
		}
		return false;
	}

	/**
	 * Pega o valor do primeiro n� filho
	 * 
	 * @return
	 */
	public NoArvore getPrimeiroFilho() {
		// Se o n� possui filhos..
		if (this.temNoFilho() == true)
			return this.listaAdjacencia.get(1);
		return null;
	}

	/**
	 * Gera um n� filho a um n�
	 * 
	 * @param no
	 */
	public void addFilho(NoArvore no) {
		// Adiciona na lista de adjac�ncias
		this.insereListaAdjacencia(no);
	}

	/**
	 * Verifica se o n� possui irm�os
	 * 
	 * @return
	 */
	public boolean aindaPossuiIrmao() {
		int posicao = this.posicaoListaAdjacencia - 1;
		int i;
		for (i = 0; i < this.getNoPai().getListaAdjacencia().size(); i++)
			if (this.getNoPai().getListaAdjacencia().get(i) != null)
				if (posicao == 1)
					return false;
		return true;
	}

	/**
	 * Recupera a lista de adjac�ncia de um n�
	 * 
	 * @return
	 */
	public List<NoArvore> getListaAdjacencia() {
		return this.listaAdjacencia;
	}

	/**
	 * Insere um n� na lista de adjac�ncia de outro n�
	 * 
	 * @param no
	 */
	public void insereListaAdjacencia(NoArvore no) {
		this.getListaAdjacencia().add(no);
	}

	/**
	 * Verifica se o n� j� foi marcado como visitado
	 * 
	 * @return
	 */
	public boolean isMarcado() {
		return marcado;
	}

	/**
	 * Marca n� como visitado
	 */
	public void marcaNo() {
		this.marcado = true;
	}

	/**
	 * Pega o n�vel que um n� se encontra "Max/min"
	 * 
	 * @return
	 */
	public TipoNivel getNivel() {
		return this.nivel;
	}

	/**
	 * Coloca o n�vel em um n�, com base no n� do pai
	 * 
	 * @param nivelNoPai
	 * @return
	 */
	public TipoNivel coloqueNivel() {
		if (this.getNoPai().getNivel() == TipoNivel.MAX)
			return TipoNivel.MIN;
		return TipoNivel.MAX;
	}

	/**
	 * Vê se um nó foi podado
	 * 
	 * @return
	 */
	public boolean getPodou() {
		return podou;
	}

	/**
	 * Poda os filhos de um n�
	 */
	public void podaFilhos() {
		int i;
		for (i = 1; i < this.getListaAdjacencia().size(); i++) {
			this.getListaAdjacencia().get(i).marcaNo();
			this.getListaAdjacencia().get(i).podou = true;
		}
	}

	public Estado getEstado() {
		return estado;
	}

	public boolean isXequeMate() {
		return xequeMate;
	}

	public CorJogador getCorNo() {
		return this.cor;
	}

}
