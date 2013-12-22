package br.edu.ifes.poo1.cln.cgt;

/**
 * Lançada quando o jogo termina por qualquer razão.
 */
public class FimDeJogoException extends Exception {

	/** Inicia a excessão com uma mensagem */
	public FimDeJogoException(String mensagem) {
		super(mensagem);
	}

	/** Número gerado automaticamente. */
	private static final long serialVersionUID = -213995159085250439L;

}
