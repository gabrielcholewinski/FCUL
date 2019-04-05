public class DLL<E> {
	
	// Private nested class
	private static class Node <E> { 
		
		private E element; //elemento do nó
		private Node <E> prev; //nó anterior
		private Node <E> next; //nó sucessor
		
		private Node (E Element , Node<E> prev , Node<E> next) {
			this.element = element; 
			this.prev = prev;
			this.next = next;
		} 	
	}
	
	// The first node of the list. Null if empty.
	private Node<E> first;
	// The last node of the list. Null if empty.
	private Node <E> last;
	/****************************
	 *	My Program starts here  *  
	 ****************************/
	//Tive de criar o construtor da classe pois faltava trabalhar com os atributos last e first
	//dos quais seriam necessarios para construir as classes requeridas no TPC3
	public DLL(E element) {
		this.last = new Node<E>(this.first.element, this.first, null); //null na ultima pos pois nao ha nada atras do last
		this.first = new Node<E>(element, null, this.last); //null na primeira pos pois nao ha nada na frente do first
		//this.ultLast = this.last; //para poder usar o removeLast
	}
	
	/**
	 * Devolve o ultimo elemento do Node
	 * @return E
	 * @requires this.last != null
	 */
	public E getLast() { //nao pode remover o node aqui pois isso soh eh feito no removeLast()
		return this.last.element;	
	}
	
	public void addLast(E element) { /*cria o novo Node com referencia ao node anterior, e null na ultima pos,
									   e muda o node anterior para fazer referencia ao novo node, com os mesmos
									   atributos que ele tinha*/
		Node<E> newNode = new Node<E>(element, this.last, null);
		this.last = new Node<E>(this.last.element, this.last.prev, newNode);
	}
	
	public void removeLast() {
		this.last.prev = new Node<E>(this.last.prev.element, this.last.prev, null);
	}
	
	/**
	 * Imprime os Nodes de ordem inversa, ou seja
	 * cria um node com o valor do ultimo node, 
	 * portanto, enquanto o node anterior nao for 
	 * a null(enquanto nao chegar na primeira posicao)
	 * deve imprimir o elemento desse node e mudar o 
	 * node variavel para a referencia que estiver atras
	 * 
	 * Sua complexidade eh O(n)
	 */
	public void reversePrint() {
		Node<E> exp = this.last; //experimental Node ; complexidade = O(1)
		while(exp.prev != null) { //complexidade = O(n)
			System.out.println(exp.element); //complexidade = O(1)
			exp = exp.prev; //complexidade = O(1)
		}
	}//complexidade total = O(n)
	
}