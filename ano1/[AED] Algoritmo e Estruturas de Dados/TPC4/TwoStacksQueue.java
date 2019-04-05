package src;

import java.util.Queue;
import java.util.Stack;

public class TwoStacksQueue <E> implements Queue <E> { 
	private Stack <E> stack1; 
	private Stack <E> stack2;

	/**
	 * construtor da Classe
	 */
	public TwoStacksQueue() { 
		stack1 = new Stack<>();
		stack2 = new Stack<>();
	} 

	/**
	 * Empurra um elemento na pilha
	 * @param e element
	 */
	public void enqueue(E e) { 
		stack1.push(e);
	} 

	/**
	 * Puxa um elemento da pilha
	 */
	public void dequeue () { 
		if(!stack1.isEmpty()) {
			stack1.pop();
		}
	} 

	/**
	 * Entrega o elemento no topo da pilha
	 * @return
	 */
	public E front () {
		if(stack1.isEmpty()) {
			return null;
		}else {
			if(!stack2.isEmpty()) {
				return stack2.peek();
			}else {
				commitStack2();
				return front();
			}
		}
	}

	/**
	 * A pilha estah vazia?
	 */
	public boolean isEmpty() { 
		boolean estaVazia = false;
		if(stack1.isEmpty()) {
			estaVazia = true;
			commitStack2();
		}
		return estaVazia;
	}

	/**
	 * Joga os elementos da stack1 na stack2, pela regra do L.I.F.O.
	 */
	private void commitStack2() {
		Stack<E> stackTemp = (Stack<E>) stack1.clone();
		for(int i=0 ; i<stackTemp.size() ; i++) {
			stack2.push(stackTemp.get(i));
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return stack1.size();
	}


	/*****************************************************************
	 * A complexidade amortizada para os metodos dequeue e front eh  *
	 * O(1), visto que ambos dependem apenas do primeiro elemento    *
	 *****************************************************************/

}