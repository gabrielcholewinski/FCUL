import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pacote<E> implements Iterable<E> {

	private int capacidade; // capacidade maxima do pacote
	private ArrayList<E> listElem; //lista de elementos do pacote
	private int elems; //elementos jah no pacote
	
	/**
	 * Cria um pacote com uma dada capacidade
	 * @param capacidade número máximo de elementos que o pacote pode conter
	 */
	public Pacote(int capacidade) {
		listElem = new ArrayList<E>(capacidade);
		this.capacidade = capacidade;
		elems = 0;

	}
	/**
	 * Retorna a capacidade máxima do pacote em termos de número de elementos
	 */
	public int getCapacidade(){
		return listElem.size();
	}
	
	/**
	 * Retorna o número de elementos actualmente contidos no pacote
	 * @return numElems
	 */
	public int getNumItems(){
		return elems;
	}
	/**
	 * Indica se o pacote ja esta cheio
	 * @return true se o pacote estiver cheio e nao for
	 * possivel adicionar mais items
	 */
	public boolean estaCheio(){
		return elems == capacidade;

	}
	/**
	 * Empacota um item se o pacote não estiver cheio.
	 * @param item o item a ser empacotado
	 * @return false se o pacote estiver cheio
	 */
	public boolean empacota(E item){
		
		if(elems == capacidade)
			return false;
		else{
			listElem.add(item);
			elems++;
			return true;
		}
	}

	/**
	 * Retorna os items contidos neste pacote
	 * @return lista de items empacotados
	 */
	public List<E> items(){
		return listElem;
	}

	/**
	 * Retorna um iterador capaz de atravessar todos
	 * os itens presentes neste pacote
	 * @return iterador
	 */
	public Iterator<E> iterator() { 
		return new PacoteIterator();
	}
	
	private class PacoteIterator implements Iterator<E>{
		
		private int index;
		
		private PacoteIterator(){
			this.index = -1;
		}
		
		
		public boolean hasNext() {
			return this.index < elems-1;
		}

		
		public E next() {
			E res = null;
			if(hasNext()){
				this.index++;
				return listElem.get(this.index);
			}
			return res;
		}		
	}



}
