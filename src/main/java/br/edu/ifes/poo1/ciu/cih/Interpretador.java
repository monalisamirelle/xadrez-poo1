package br.edu.ifes.poo1.ciu.cih;

import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.JogadaInvalidaException;
import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.TipoJogada;
import br.edu.ifes.poo1.cln.cdp.TipoPeca;

/**
 * Responsável pela interpretação das entradas dos usuários.
 */
public class Interpretador {

	/**
	 * Decodifica a entrada do jogador para o formato adequado.
	 * 
	 * @param jogada
	 *            Entrada do jogador.
	 * @return Uma jogada já interpretada para ser executada por um Jogador.
	 * @throws JogadaInvalidaException
	 *             Lançada se a entrada do usuário não puder ser convertida para
	 *             uma jogada válida.
	 */
	public static Jogada interpretarJogada(String jogada)
			throws JogadaInvalidaException {

		// Se for uma jogada do tipo Roque, já podemos retornar a jogada
		// construída.
		if (jogada.contentEquals("O-O"))
			return new Jogada(TipoJogada.ROQUE_MENOR);
		if (jogada.contentEquals("O-O-O"))
			return new Jogada(TipoJogada.ROQUE_MAIOR);

		// Inicia as variáveis necessárias para criação das jogadas restantes.
		String strOrigem;
		String strDestino;
		TipoJogada tipo;
		boolean ehPromocao;
		char promocaoChar = 0; // Será sobrescrito depois, se necessário.

		// Dicerne o tipo da jogada. Felizmente, o tamanho da String ajuda
		// muito. :)
		switch (jogada.length()) {

		// Se for uma jogada simples, sem ataque, como: 1253
		case 4:
			// Preenche os dados necessários.
			strOrigem = jogada.substring(0, 2);
			strDestino = jogada.substring(2, 4);
			tipo = TipoJogada.ANDAR;
			ehPromocao = false;
			break;

		// Se for uma jogada simples do tipo ataque, como: 24x53
		case 5:
			// Verifica o 'x' no meio.
			if (jogada.charAt(2) != 'X')
				throw new JogadaInvalidaException(
						"O comando dado não pode ser interpretado.");

			// Preenche os dados necessários.
			strOrigem = jogada.substring(0, 2);
			strDestino = jogada.substring(3, 5);
			tipo = TipoJogada.ATACAR;
			ehPromocao = false;
			break;

		// Jogada sem ataque, mas com promoção de peão, como: 4568=D
		case 6:
			// Verifica o '=' da promoção.
			if (jogada.charAt(4) != '=')
				throw new JogadaInvalidaException(
						"O comando dado não pode ser interpretado.");

			// Preenche os dados necessários.
			strOrigem = jogada.substring(0, 2);
			strDestino = jogada.substring(2, 4);
			tipo = TipoJogada.ANDAR;
			ehPromocao = true;
			promocaoChar = jogada.charAt(5);
			break;

		// Jogada de ataque e promoção de peão, como: 57x48=T
		case 7:
			// Verifica o 'x' do ataque e o '=' da promoção.
			if (jogada.charAt(2) != 'X' || jogada.charAt(4) != '=')
				throw new JogadaInvalidaException(
						"O comando dado não pode ser interpretado.");

			// Preenche os dados necessários.
			strOrigem = jogada.substring(0, 2);
			strDestino = jogada.substring(3, 5);
			tipo = TipoJogada.ATACAR;
			ehPromocao = true;
			promocaoChar = jogada.charAt(6);
			break;

		// Qualquer outro tipo de jogada será inválida.
		default:
			throw new JogadaInvalidaException(
					"O comando dado não pode ser interpretado.");
		}

		// Pega as colunas e as linhas.
		String origemColuna = strOrigem.substring(0, 1);
		String origemLinha = strOrigem.substring(1, 2);
		String destinoColuna = strDestino.substring(0, 1);
		String destinoLinha = strDestino.substring(1, 2);

		// Convertendo as colunas e linhas para inteiro.
		int intOrigemColuna, intOrigemLinha, intDestinoColuna, intDestinoLinha;
		try {
			intOrigemColuna = Integer.parseInt(origemColuna);
			intOrigemLinha = Integer.parseInt(origemLinha);
			intDestinoColuna = Integer.parseInt(destinoColuna);
			intDestinoLinha = Integer.parseInt(destinoLinha);
		} catch (NumberFormatException e) {
			throw new JogadaInvalidaException(
					"O comando dado não pode ser interpretado. Use algo como: 1213");
		}

		// Verifica se estão dentro dos limites do tabuleiro.
		if (intOrigemColuna < 1 || intOrigemColuna > 8 || intOrigemLinha < 1
				|| intOrigemLinha > 8 || intDestinoColuna < 1
				|| intDestinoColuna > 8 || intDestinoLinha < 1
				|| intDestinoLinha > 8)
			throw new JogadaInvalidaException(
					"Você está indicando uma casa fora dos limites do tabuleiro.");

		// Prepara a origem e o destino para serem usadas.
		Posicao origem = new Posicao(intOrigemColuna, intOrigemLinha);
		Posicao destino = new Posicao(intDestinoColuna, intDestinoLinha);

		// Retorna a jogada interpretada.
		if (ehPromocao) {
			// Filtra o tipo de peça para o qual deseja-se promover o peão.
			TipoPeca promocao;
			switch (promocaoChar) {
			case 'D':
				promocao = TipoPeca.RAINHA;
				break;
			case 'T':
				promocao = TipoPeca.TORRE;
				break;
			case 'B':
				promocao = TipoPeca.BISPO;
				break;
			case 'C':
				promocao = TipoPeca.CAVALO;
				break;

			default:
				// Qualquer outra peça solicitada é inválida.
				throw new JogadaInvalidaException(
						"As promoções apenas podem acontecer para: rainha (D), torre(T), bispo (B) e cavalo (C).");
			}

			// Retorna uma jogada com promoção.
			return new Jogada(origem, destino, tipo, promocao);
		} else {
			// Retorna uma jogada simples, sem promoção.
			return new Jogada(origem, destino, tipo);
		}
	}
}
