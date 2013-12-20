package br.edu.ifes.poo1.ciu.cih;

/**
 * Excessão lançada quando o usuário seleciona uma entrada do menu inválida.
 */
public class EntradaInvalidaException extends Exception {

	/**
	 * Constrói a excessão com uma mensagem associada.
	 * 
	 * @param mensagem
	 *            Motivo pelo qual a excessão foi lançada.
	 */
	public EntradaInvalidaException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Número de série, gerado automaticamente.
	 */
	private static final long serialVersionUID = -7422850167962510604L;

}
