package src;

/**
 * 
 * @author 
 *
 */
public class Garagem{
	public static final int MAX_CARROS = 50;
	private int numPistas;
	private int comprimento;
	private Carro[]allCars;
	private Pista[] pistas;
	private int registado;
	private int carroEmServico;
	private boolean[]emServico;

	/**
	 * 
	 * @param numPistas 
	 * @param comprimento
	 * @requires 
	 */
	public Garagem (int numPistas, int comprimento) {
		this.numPistas = numPistas;
		this.comprimento = comprimento;
		this.allCars = new Carro[MAX_CARROS];
		this.registado = 0;
		this.emServico = new boolean[MAX_CARROS];
		this.pistas = new Pista[this.numPistas];
		for(int i = 0; i < this.pistas.length; i++) {
			pistas[i] = new Pista(i,this.comprimento);
		}
	}


	/**
	 * 
	 * @param comprimento
	 * @param numKm 
	 * @requires 
	 */
	public void registaNovoCarro (int comprimento, int numKm) {

		allCars[registado] = new Carro(registado + 1, comprimento,numKm);
		pistas[pistaPlusEmpty()].estacionaCarro(allCars[registado]);
		this.registado++;

	}
	/**
	 * 
	 * @return pista mais vazia 
	 */
	private int pistaPlusEmpty() {
		int resultado = 0;
		int x = pistas[0].obtemComprimentoOcupado();
		for(int i = 1; i < this.pistas.length; i++) {
			int teste = pistas[i].obtemComprimentoOcupado();
			if(teste < x) {
				x = teste; 
				resultado = i;
			}
		}
		return resultado;
	}

	/**
	 * 
	 * @return 
	 */
	public int obtemNumeroCarrosRegistados ( ) {
		return this.registado;
	}

	/**
	 * 
	 * @return 
	 * @reuqires 
	 */
	public int escolhaPorNumRegisto ( ) {
		int result = 0;
		for(int i = 1; i < pistas.length; i++) {
			if(pistas[i].carroNoTopo().obtemNumeroRegisto() < pistas[result].carroNoTopo().obtemNumeroRegisto()) result = i;
		}	
		int carroNum = pistas[result].carroNoTopo().obtemNumeroRegisto();
		pistas[result].tiraCarro();
		this.emServico[carroNum] = true;
		carroEmServico++;
		return carroNum;


	}

	/**
	 * 
	 * @return
	 * @requires 
	 */
	public int escolhaPorNumKm ( ) {
		int result = 0;
		for(int i = 1; i < pistas.length; i++) {
			if(pistas[i].carroNoTopo().obtemKm() < pistas[result].carroNoTopo().obtemKm()) result = i;
		}
		int carroXota = pistas[result].carroNoTopo().obtemNumeroRegisto();
		pistas[result].tiraCarro();
		this.emServico[carroXota-1] = true;
		carroEmServico++;
		return carroXota;
	}


	/**
	 * 
	 * @return
	 * @requires
	 */
	public int[] carrosEmServico ( ) {
		int[]teste = new int[carroEmServico];
		int count = 0;
		for(int i = 0; i < emServico.length; i++) {
			if(emServico[i]) {
				teste[count] = i+1;
				count++;
			}
		}
		return teste;
	}

	/**
	 * 
	 * @param xpto
	 * @param quantosKmsFez
	 * @return 
	 * @requires
	 */
	public void estacionaCarro (int numRegisto, int quantosKmsFez) {
		Carro bilao = allCars[numRegisto-1];
		bilao.actualizarKm(quantosKmsFez);
		if(bilao.precisaRevisao()) bilao.fazRevisao();
		this.pistas[ pistaPlusEmpty()].estacionaCarro(bilao);
		this.emServico[numRegisto-1] = false;
		carroEmServico--;
	}


	/**
	 * 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("A garagem tem " + this.numPistas + " pistas com a seguinte configuracao:\n");
		for(int i = 0; i < this.numPistas; i++) {
			sb.append(pistas[i].toString() + "\n");
		}
		return sb.append("estao em servico: " + carroEmServico + " carros").toString();
	}

	
}