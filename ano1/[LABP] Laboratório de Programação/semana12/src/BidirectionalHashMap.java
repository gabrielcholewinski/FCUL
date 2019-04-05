import java.util.HashMap;
import java.util.Set;

/**
 * 
 * @author 
 *
 */

/**
 * Tabela bidireccional.
 */

public class BidirectionalHashMap<K, V> {
	private HashMap<K, V> orgs;
	private HashMap<V, K> urls;

	/**
	 * construtor
	 */
	public BidirectionalHashMap() {
		orgs = new HashMap<K, V>();
		urls = new HashMap<V, K>();
	}


	/**
	 * Dado um valor, obter a chave correspondente
	 * @param value - o valor
	 * @return K - a chave
	 */
	public K getKey(V value) {
		return urls.get(value);
	}

	/**
	 * Dada uma chave, obter o valor correspondente
	 * @param key - a chave
	 * @return V - o valor
	 */
	public V getValue(K key) {
		return orgs.get(key);
	}

	/**
	 * Adicionar um par chave-valor
	 * @param key - a chave
	 * @param value - o valor
	 */
	public void put(K key, V value) {
		if(containsValue(value)) 
			removeByKey(removeByValue(value));
		
		orgs.put(key, value);
		urls.put(value, key);
	}

	/**
	 * Verificar se a tabela contem uma chave
	 * @param key -a chave
	 * @return true se contem a chave, false se nao contem.
	 */
	public boolean containsKey(K key) {
		return orgs.containsKey(key);
	}

	/**
	 * verificar se a tabela contem um certo valor
	 * @param value - o valor
	 * @return true se contem o valor, false caso contrario.
	 */
	public boolean containsValue(V value) {
		return orgs.containsValue(value);
	}

	/**
	 * Remover um par chave-valor, dada a chave.
	 * @param key - a chave.
	 * @return V - o valor previamente associado a chave.
	 */
	public V removeByKey(K key) {
		if(containsKey(key)) 
			return orgs.remove(key);
		
		return null;
	}

	/**
	 * Remover um par chave-valor, dado o valor.
	 * @param value - o valor
	 * @return K - a chave associada ao valor antes da remocao do par.
	 */
	public K removeByValue(V value) {
		if(containsValue(value)) 
			return urls.remove(value);
		
		return null;
	}

	/**
	 * Retornar o tamanho da tabela (numero de pares chave-valor contidos).
	 * @return o tamanho da tabela.
	 */
	public int size() {
		return orgs.size();
	} 


	/**
	 * Retornar as chaves 
	 * @return the set of keys
	 */
	public Set<K> getKeys(){ 
		return orgs.keySet();
	}

}
