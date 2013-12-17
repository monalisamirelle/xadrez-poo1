package br.edu.ifes.poo1.cln.cdp;

import java.util.ArrayList;
import java.util.List;

/**
 * Representação de um jogador. Há no máximo 2 jogadores para o jogo de xadrez.
 */
public class Jogador {
	/** Nome do jogador. */
	private String nome;

	/** Cor do jogador. */
	private CorJogador cor;

	/** Peças as quais já capturou. */
	List<Peca> pecasCapturadas = new ArrayList<Peca>();

	/** Tabuleiro no qual está jogando. */
	private Tabuleiro tabuleiro;

	/**
	 * Constrói um jogador.
	 * 
	 * @param nome
	 *            Nome do jogador.
	 */
	public Jogador(String nome, CorJogador cor, Tabuleiro tabuleiro) {
		this.nome = nome;
		this.cor = cor;
		this.tabuleiro = tabuleiro;
	}

	/**
	 * Acrescenta uma peça, a lista de peças capturadas pelo jogador.
	 * 
	 * @param peca
	 *            Peça capturada.
	 */
	public void acrescentarPecaCapturada(Peca peca) {
		pecasCapturadas.add(peca);
	}

	/**
	 * Faz a movimentação de uma peça e também as verificações necessárias.
	 * 
	 * @param jogada Jogada que deve ser aplicada.
	 * @throws JogadaInvalidaException
	 */
	public void movimentarPeca(Jogada jogada) throws JogadaInvalidaException {
		// Espia o que há na origem e no destino.
		Peca pecaOrigem = tabuleiro.espiarPeca(jogada.getOrigem());
		Peca pecaDestino = tabuleiro.espiarPeca(jogada.getDestino());

		// Verifica se há uma peça na origem.
		if (pecaOrigem == null)
			throw new JogadaInvalidaException();
		
		// E verifica se a peça em origem é do jogador
		if (pecaOrigem.getJogador() != this)
			throw new JogadaInvalidaException();
		
		// Se a jogada for uma promoção de peão, na origem do movimento deve
		// estar um peão e o destino deve ser o a linha do lado oposto ao de
		// início das peças.
		// Faz as verificações relevantes a promoção dos peões.
		if (jogada.ehPromocao()) {
			// Sendo uma promoção, na origem deve estar um peão.
			if (pecaOrigem.getTipoPeca() != TipoPeca.PEAO) {
				throw new JogadaInvalidaException();
			}

			// E este peão deve estar se movimentando para a última linha do
			// tabuleiro.
			Posicao destino = jogada.getDestino();
			if (cor == CorJogador.BRANCO) {
				// Sendo branco, o jogador, o peão deve estar se movendo para a
				// linha 8.
				if (destino.getLinha() != 8)
					throw new JogadaInvalidaException();
			} else if (cor == CorJogador.PRETO) {
				// Sendo preto, o jogador, o peão deve estar se movendo para a
				// linha 8.
				if (destino.getLinha() != 1)
					throw new JogadaInvalidaException();
			}
		}

		// Faz as verificações para o ataque ou um simples andar da peça.
		if (jogada.ehAtaque()) {
			// Se não houver peça no destino e for um ataque, a jogada é
			// inválida.
			if (pecaDestino == null)
				throw new JogadaInvalidaException();
			
			// Verifica se a peça pode se mover para o destino.
			if (!pecaOrigem.podeAtacar(jogada.getOrigem(), jogada.getDestino(), tabuleiro))
				throw new JogadaInvalidaException();

			// Remove a peça do destino e acrescenta a lista de peças
			// capturadas, já que se trata de um ataque.
			this.pecasCapturadas
					.add(tabuleiro.retirarPeca(jogada.getDestino()));
		} else {
			// O destino deve estar livre, se a jogada não for um ataque.
			if (pecaDestino != null)
				throw new JogadaInvalidaException();
			
			// Verifica se a peça pode se mover para o destino.
			if (!pecaOrigem.podeAndar(jogada.getOrigem(), jogada.getDestino(), tabuleiro))
				throw new JogadaInvalidaException();
		}

		// Move a peça para o destino.
		Peca pecaRetirada = tabuleiro.retirarPeca(jogada.getOrigem());
		pecaRetirada.setJaMoveu(); // Marca a peça como já movida.
		try {
			tabuleiro.colocarPeca(jogada.getDestino(), pecaRetirada);
		} catch (CasaOcupadaException e) {
			// A excessão acima não deveria ter sido lançada em hipótese alguma,
			// pois as verificações com respeito ao destino já foram feitas.
			throw new JogadaInvalidaException();
		}
	}

	public String getNome() {
		return nome;
	}

	public List<Peca> getPecasCapturadas() {
		return pecasCapturadas;
	}

	public CorJogador getCor() {
		return cor;
	}
}
