package src;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 
 *
 */
public class Pista{
	private int identificacao;
	private int comprimentoGaragem;
	private Deque<Carro> pilhaCarros = new ArrayDeque<Carro>();
	private int count;

	/**
	 * 
	 * @param identificacao
	 * @param comprimentoGaragem
	 * @requires 
	 */
	public Pista (int identificacao, int comprimentoGaragem) {
		this.identificacao = identificacao;
		this.comprimentoGaragem = comprimentoGaragem;
		this.count = 0;

	}

	/**
	 * 
	 * @return 
	 */
	public int obtemComprimento () {
		return this.comprimentoGaragem;
	}

	/**
	 * 
	 * @return 
	 */
	public int obtemComprimentoOcupado () {
		if(pilhaCarros.isEmpty()) {
			return 0;
		} else {
			return count;
		}
	}


	/**
	 * 
	 * @return
	 */
	public int obtemIdentificacao() {
		return this.identificacao;
	}

	/**
	 * 
	 * @return 
	 */
	public boolean estaVazia() {
		if(this.pilhaCarros.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @return 
	 * @requires 
	 */
	public Carro carroNoTopo() {
		return this.pilhaCarros.peek();
	}


	/**
	 * 
	 * @return 
	 * @requires 
	 */
	public Carro tiraCarro() {
		this.count -= pilhaCarros.peek().obtemComprimento();
		return this.pilhaCarros.pop();
	}


	/**
	 * 
	 * @param carro
	 * @return 
	 * @requires 
	 */
	public boolean cabeNaPista(Carro carro) {
		if(carro.obtemComprimento() > this.comprimentoGaragem) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @param carro 
	 * @requires 
	 */
	public boolean estacionaCarro(Carro carro) {
		if(cabeNaPista(carro) == true){
			this.pilhaCarros.push(carro);
			count += carro.obtemComprimento();
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Representacao da pista
	 */
	public String toString () {
		StringBuilder resultado = new StringBuilder();
		resultado.append("Pista "+this.identificacao+": \n");
		resultado.append(+obtemComprimento() - obtemComprimentoOcupado()+" cm de espaco livre. \n");
		resultado.append(pilhaCarros.toString());
		return resultado.toString();
	}

}