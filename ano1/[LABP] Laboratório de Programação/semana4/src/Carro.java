package src;

/**
 * 
 * @author
 *
 */
public class Carro {
	private int count;
	private int numRegisto;
	private int comprimento;
	private int km;
	private boolean revisaoFeita;

	/**
	 * 
	 * @param numRegisto 
	 * @param comprimento
	 * @param km
	 * @requires 
	 */
	public Carro (int numRegisto, int comprimento, int km) {
		this.numRegisto = numRegisto;
		this.comprimento = comprimento;
		this.km = km;
		this.count = 20000;
		this.revisaoFeita = false;

	}

	/**
	 * 
	 * @return 
	 */
	public int obtemKm () {
		return this.km;
	}

	/**
	 * 
	 * @return
	 */
	public int obtemComprimento () {
		return this.comprimento;
	}

	/**
	 * 
	 * @param km
	 * @requires
	 */
	public void actualizarKm (int km) {
		this.km += km;
		while(this.km > this.count) {
			this.count+=20000;
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean precisaRevisao() {
		boolean result = false;
		if(this.km > 20000 && !this.revisaoFeita) {
			return true;
		}
		if(this.km > this.count && !this.revisaoFeita) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * 
	 * @return
	 */
	public int proximaRevisao() {
		if(this.km > 20000 && !this.revisaoFeita) return this.km;
		if(this.km > 20000 && this.revisaoFeita) return this.km + 20000;
		if(this.km < 20000 && !this.revisaoFeita){
			return this.km + 20000;
		}else {
			return this.count - this.km;
		}

	}

	/**
	 * 
	 */
	public void fazRevisao () {
		this.count += 20000;
		this.revisaoFeita = true;
	}

	/**
	 * 
	 * @return
	 */
	public int obtemNumeroRegisto() {
		return this.numRegisto;
	}

	/**
	 * 
	 */
	public String toString () {
		String result;
		String teste;
		if(this.precisaRevisao()) {
			result = "em falta";
		} else {
			result = "ok";
		}
		teste = "Carro " + this.numRegisto + ": " + "(" + this.km + " km, " + this.comprimento + " cm," + " Revisao: " +result+")";
		return teste;
	}

}