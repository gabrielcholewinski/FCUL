
/**
 * Um par que armazena dois elementos de qualquer tipo,
 *  preservando a sua ordem.
 */
public class Par<A, B> {

	private A itemA; //primeiro elemento do par
	private B itemB; //segundo elemento do par

	/**
	 * Cria um par com dois items
	 * @param itemA - o primeiro item do par
	 * @param itemB - o segundo item do par
	 */
	public Par(A itemA, B itemB){
		this.itemA = itemA;
		this.itemB = itemB;

	}
	/**
	 * Devolve o primeiro elemento do par
	 */
	public A primeiro(){
		return itemA;

	}
	/**
	 * Devolve o segundo elemento do par
	 */
	public B segundo(){
		return itemB;
	}
}
