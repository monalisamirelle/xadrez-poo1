package br.edu.ifes.poo1.cln.cdp.ia;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifes.poo1.cln.cdp.CasaOcupadaException;
import br.edu.ifes.poo1.cln.cdp.Jogada;
import br.edu.ifes.poo1.cln.cdp.Posicao;
import br.edu.ifes.poo1.cln.cdp.TabuleiroXadrez;
import br.edu.ifes.poo1.cln.cdp.TipoCorJogador;
import br.edu.ifes.poo1.cln.cdp.TipoPeca;
import br.edu.ifes.poo1.cln.cdp.pecas.Peao;
import br.edu.ifes.poo1.cln.cdp.pecas.Peca;
import br.edu.ifes.poo1.cln.cdp.pecas.Rainha;

public class GeraEstado {

	/**
	 * Método que armazena, dado um tabuleiro, os próximos estados possíveis
	 * para o tabuleiro
	 * 
	 * @param corJogador
	 * @return
	 * @throws CasaOcupadaException
	 */
	public List<Estado> proximosEstadosPossiveis(
			TabuleiroXadrez tabuleiroAtual, TipoCorJogador corJogador)
			throws CasaOcupadaException {

		// Primeiramente, cria uma cópia do tabuleiro para não atrapalhar o
		// tabuleiro atual
		TabuleiroXadrez copiaTabuleiro = tabuleiroAtual.tabuleiroClonado();

		// Em seguida, reseta o estado de en passant do jogador
		copiaTabuleiro.resetaPodeEnPassant(corJogador);

		// Gera a lista de possíveis jogadas da cópia do tabuleiro
		List<Jogada> possiveisJogadas = copiaTabuleiro
				.geraJogadasPossiveis(corJogador);

		// Cria-se uma lista que irá receber os próximos estados
		List<Estado> proximosEstados = new ArrayList<Estado>();

		// Para cada jogada realizada pela peça, gere um novo
		// tabuleiro e or armazene na lista de tabuleiros
		for (Jogada jogada : possiveisJogadas) {
			TabuleiroXadrez tabuleiroNovo = copiaTabuleiro.tabuleiroClonado();
			// Verifica o tipo de jogada e gera o tabuleiro correto
			switch (jogada.getTipoJogada()) {
			case ANDAR:
				tabuleiroNovo = estadoAndar(jogada, corJogador, copiaTabuleiro,
						tabuleiroNovo);
				break;
			case ATACAR:
				tabuleiroNovo = estadoAtacar(jogada, corJogador,
						copiaTabuleiro, tabuleiroNovo);
				break;
			case ROQUE_MENOR:
				tabuleiroNovo = estadoRoqueMenor(jogada, corJogador,
						copiaTabuleiro, tabuleiroNovo);
				break;
			case ROQUE_MAIOR:
				tabuleiroNovo = estadoRoqueMaior(jogada, corJogador,
						copiaTabuleiro, tabuleiroNovo);
				break;
			case EN_PASSANT_ESQUERDA:
				tabuleiroNovo = estadoEnPassantEsquerda(jogada, corJogador,
						copiaTabuleiro, tabuleiroNovo);
				break;
			case EN_PASSANT_DIREITA:
				tabuleiroNovo = estadoEnPassantDireita(jogada, corJogador,
						copiaTabuleiro, tabuleiroNovo);
				break;
			}
			// Se a jogada a ser realizada não leva um jogador a um estado de
			// xeque
			if (!tabuleiroNovo.verificarXeque(corJogador))
				proximosEstados.add(new Estado(jogada, tabuleiroNovo));
		}
		return proximosEstados;
	}

	/**
	 * 
	 * @param jogada
	 * @param corJogador
	 * @param copiaTabuleiro
	 * @param tabuleiroNovo
	 * @return
	 * @throws CasaOcupadaException
	 */
	private TabuleiroXadrez estadoAndar(Jogada jogada,
			TipoCorJogador corJogador, TabuleiroXadrez copiaTabuleiro,
			TabuleiroXadrez tabuleiroNovo) throws CasaOcupadaException {
		Peca peca = copiaTabuleiro.espiarPeca(jogada.getOrigem());
		tabuleiroNovo.retirarPeca(jogada.getOrigem());
		// Se for Promoção
		if (jogada.ehPromocao()) {
			Peca rainha = new Rainha(corJogador);
			rainha.setJaMoveu();
			tabuleiroNovo.colocarPeca(jogada.getDestino(), rainha);
		}
		// Se a peça pode ser vítima de en passant
		else if (peca.getTipoPeca() == TipoPeca.PEAO
				&& peca.medeDeslocamentoPeca(jogada.getOrigem().getLinha(),
						jogada.getDestino().getLinha()) == 2) {
			Peao peaoVitima = (Peao) peca.clone();
			peaoVitima.setPodeEnPassant(true);
			peaoVitima.setJaMoveu();
			tabuleiroNovo.colocarPeca(jogada.getDestino(), peaoVitima);
		} else {
			// Controle Roque
			if (peca.getJaMoveu() == true)
				tabuleiroNovo.colocarPeca(jogada.getDestino(), peca);
			else {
				Peca novaPeca = peca.clone();
				novaPeca.setJaMoveu();
				tabuleiroNovo.colocarPeca(jogada.getDestino(), novaPeca);
			}
		}
		return tabuleiroNovo;
	}

	/**
	 * 
	 * @param jogada
	 * @param corJogador
	 * @param copiaTabuleiro
	 * @param tabuleiroNovo
	 * @return
	 * @throws CasaOcupadaException
	 */
	private TabuleiroXadrez estadoAtacar(Jogada jogada,
			TipoCorJogador corJogador, TabuleiroXadrez copiaTabuleiro,
			TabuleiroXadrez tabuleiroNovo) throws CasaOcupadaException {
		Peca peca = copiaTabuleiro.espiarPeca(jogada.getOrigem());
		tabuleiroNovo.retirarPeca(jogada.getOrigem());
		tabuleiroNovo.retirarPeca(jogada.getDestino());
		// Se for Promoção
		if (jogada.ehPromocao()) {
			Rainha rainha = new Rainha(corJogador);
			rainha.setJaMoveu();
			tabuleiroNovo.colocarPeca(jogada.getDestino(), rainha);
		} else {
			// Controle roque
			if (peca.getJaMoveu() == true)
				tabuleiroNovo.colocarPeca(jogada.getDestino(), peca);
			else {
				Peca novaPeca = peca.clone();
				novaPeca.setJaMoveu();
				tabuleiroNovo.colocarPeca(jogada.getDestino(), novaPeca);
			}
		}
		return tabuleiroNovo;
	}

	/**
	 * 
	 * @param jogada
	 * @param corJogador
	 * @param copiaTabuleiro
	 * @param tabuleiroNovo
	 * @return
	 * @throws CasaOcupadaException
	 */
	private TabuleiroXadrez estadoRoqueMenor(Jogada jogada,
			TipoCorJogador corJogador, TabuleiroXadrez copiaTabuleiro,
			TabuleiroXadrez tabuleiroNovo) throws CasaOcupadaException {
		if (corJogador == TipoCorJogador.BRANCO) {
			Peca novoRei = copiaTabuleiro.espiarPeca(new Posicao(5, 1)).clone();
			novoRei.setJaMoveu();
			Peca novaTorre = copiaTabuleiro.espiarPeca(new Posicao(8, 1))
					.clone();
			novaTorre.setJaMoveu();
			tabuleiroNovo.retirarPeca(new Posicao(5, 1));
			tabuleiroNovo.retirarPeca(new Posicao(8, 1));
			tabuleiroNovo.colocarPeca(new Posicao(7, 1), novoRei);
			tabuleiroNovo.colocarPeca(new Posicao(6, 1), novaTorre);
		} else {
			Peca novoRei = copiaTabuleiro.espiarPeca(new Posicao(5, 8)).clone();
			novoRei.setJaMoveu();
			Peca novaTorre = copiaTabuleiro.espiarPeca(new Posicao(8, 8))
					.clone();
			novaTorre.setJaMoveu();
			tabuleiroNovo.retirarPeca(new Posicao(5, 8));
			tabuleiroNovo.retirarPeca(new Posicao(8, 8));
			tabuleiroNovo.colocarPeca(new Posicao(7, 8), novoRei);
			tabuleiroNovo.colocarPeca(new Posicao(6, 8), novaTorre);
		}
		return tabuleiroNovo;
	}

	/**
	 * 
	 * @param jogada
	 * @param corJogador
	 * @param copiaTabuleiro
	 * @param tabuleiroNovo
	 * @return
	 * @throws CasaOcupadaException
	 */
	private TabuleiroXadrez estadoRoqueMaior(Jogada jogada,
			TipoCorJogador corJogador, TabuleiroXadrez copiaTabuleiro,
			TabuleiroXadrez tabuleiroNovo) throws CasaOcupadaException {
		if (corJogador == TipoCorJogador.BRANCO) {
			Peca novoRei = copiaTabuleiro.espiarPeca(new Posicao(5, 1)).clone();
			novoRei.setJaMoveu();
			Peca novaTorre = copiaTabuleiro.espiarPeca(new Posicao(1, 1))
					.clone();
			novaTorre.setJaMoveu();
			tabuleiroNovo.retirarPeca(new Posicao(5, 1));
			tabuleiroNovo.retirarPeca(new Posicao(1, 1));
			tabuleiroNovo.colocarPeca(new Posicao(3, 1), novoRei);
			tabuleiroNovo.colocarPeca(new Posicao(4, 1), novaTorre);
		} else {
			Peca novoRei = copiaTabuleiro.espiarPeca(new Posicao(5, 8)).clone();
			novoRei.setJaMoveu();
			Peca novaTorre = copiaTabuleiro.espiarPeca(new Posicao(1, 8))
					.clone();
			novaTorre.setJaMoveu();
			tabuleiroNovo.retirarPeca(new Posicao(5, 8));
			tabuleiroNovo.retirarPeca(new Posicao(1, 8));
			tabuleiroNovo.colocarPeca(new Posicao(3, 8), novoRei);
			tabuleiroNovo.colocarPeca(new Posicao(4, 8), novaTorre);
		}
		return tabuleiroNovo;
	}

	/**
	 * 
	 * @param jogada
	 * @param corJogador
	 * @param copiaTabuleiro
	 * @param tabuleiroNovo
	 * @return
	 * @throws CasaOcupadaException
	 */
	private TabuleiroXadrez estadoEnPassantEsquerda(Jogada jogada,
			TipoCorJogador corJogador, TabuleiroXadrez copiaTabuleiro,
			TabuleiroXadrez tabuleiroNovo) throws CasaOcupadaException {
		Peca peca = copiaTabuleiro.espiarPeca(jogada.getOrigem());
		// Retirar a peça de sua posição
		tabuleiroNovo.retirarPeca(jogada.getOrigem());
		// Retirar peça inimiga à esquerda
		tabuleiroNovo.retirarPeca(new Posicao(
				jogada.getOrigem().getColuna() - 1, jogada.getOrigem()
						.getLinha()));
		// Se o en passant for favorável as peças brancas
		if (corJogador == TipoCorJogador.BRANCO)
			tabuleiroNovo.colocarPeca(new Posicao(jogada.getOrigem()
					.getColuna() - 1, jogada.getOrigem().getLinha() + 1), peca);
		// Se en passant for favorável as peças pretas
		else
			tabuleiroNovo.colocarPeca(new Posicao(jogada.getOrigem()
					.getColuna() - 1, jogada.getOrigem().getLinha() - 1), peca);
		return tabuleiroNovo;
	}

	/**
	 * 
	 * @param jogada
	 * @param corJogador
	 * @param copiaTabuleiro
	 * @param tabuleiroNovo
	 * @return
	 * @throws CasaOcupadaException
	 */
	private TabuleiroXadrez estadoEnPassantDireita(Jogada jogada,
			TipoCorJogador corJogador, TabuleiroXadrez copiaTabuleiro,
			TabuleiroXadrez tabuleiroNovo) throws CasaOcupadaException {
		Peca peca = copiaTabuleiro.espiarPeca(jogada.getOrigem());
		// Retirar a peça de sua posição
		tabuleiroNovo.retirarPeca(jogada.getOrigem());
		// Retirar peça inimiga à direita
		tabuleiroNovo.retirarPeca(new Posicao(
				jogada.getOrigem().getColuna() + 1, jogada.getOrigem()
						.getLinha()));
		// Se o en passant for favorável as peças brancas
		if (corJogador == TipoCorJogador.BRANCO)
			tabuleiroNovo.colocarPeca(new Posicao(jogada.getOrigem()
					.getColuna() + 1, jogada.getOrigem().getLinha() + 1), peca);
		// Se en passant for favorável as peças pretas
		else
			tabuleiroNovo.colocarPeca(new Posicao(jogada.getOrigem()
					.getColuna() + 1, jogada.getOrigem().getLinha() - 1), peca);
		return tabuleiroNovo;
	}

}
