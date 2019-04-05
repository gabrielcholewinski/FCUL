package fase4;
/**
 * Representa os Outros e suas caracteristicas
 * @author ruhanvictor
 *
 */
public class Outro {
	public String nome;
	public int posicao;
	public char simbolo;
	public double stam;
	public EstadoOutro estadoOutro;
	//EstadoOutro;
	/**
	 * Construtor que determina as características do Outro
	 * @param nome
	 * @param simbolo
	 * @param stam
	 * @requires nome != null && simbolo != null && stam > 0
	 */
	public Outro(String nome, char simbolo, double stam){
		this.nome = nome;
		this.simbolo = simbolo;
		this.stam = stam;
		this.estadoOutro = EstadoOutro.EM_JOGO;
		this.posicao = 1;
	}
	/**
	 * @return nome do Outro
	 */
	public String nome() {
		return this.nome;
	}
	/**
	 * @return posicao do Outro
	 */
	public int posicao() {
		return this.posicao;
	}
	/**
	 * @return simbolo do Outro
	 */
	public char simbolo() {
		return this.simbolo;
	}
	/**
	 * @return o estado do Outro
	 */
	public EstadoOutro estado() {
		return this.estadoOutro;
	}
	/**
	 * muda a Stamina do Outro ou retorna SEM_STAMINA
	 * caso o Outro tenha stamina inferior ou igual a zero
	 * @param val valor para atribuir a Stamina
	 */
	public void mudaStamina(double val) {
		this.stam -= val;
		if(this.stam <= 0) {
			this.estadoOutro = EstadoOutro.SEM_STAMINA;
		}
	}
	/**
	 * Faz o Salto do Outro, caso a Stamina esteja
	 * a baixo de zero, invoca o metodo mudaStamina
	 * para atribuir que o Outro esta sem Stamina
	 * @param salto
	 */
	public void salta(int salto) {
		this.posicao += salto;
	}
	/*
	 * @return Outro esta em jogo?
	 * conj = conjultura(estado)
	 */
	public boolean emJogo() {
		boolean conj;
		if(this.estadoOutro == EstadoOutro.EM_JOGO) {
			conj = true;
		}else {
			conj = false;
		}
		return conj;
	}
	/**
	 * Atribui ao estado do Outro o CHOCOU_MONIUS
	 */
	public void chocouMonius() {
		this.estadoOutro = EstadoOutro.CHOCOU_MONIUS;
	}
	/**
	 * Atribui ao estado do Outro o CHEGOU
	 */
	public void chegou() {
		this.estadoOutro = EstadoOutro.CHEGOU;
	}
	/**
	 * Atribui ao estado do Outro o ATINGIDO_SABRE
	 */
	public void atingidoPorSabre() {
		this.estadoOutro = EstadoOutro.ATINGIDO_SABRE;
	}
	/**
	 * Atribui ao estado do Outro o CAIU_BURACO_NEGRO
	 */
	public void caiuBuracoNegro() {
		this.estadoOutro = EstadoOutro.CAIU_BURACO_NEGRO;
	}
	public String toString() {
		return "Outro [nome=" + nome + ", posicao=" + posicao + ", simbolo=" + simbolo + ", stam=" + stam
				+ ", estadoOutro=" + estadoOutro + "]";
	}
}
