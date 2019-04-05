import java.util.Iterator;
import java.util.Scanner;

import javax.xml.soap.Node;


public class Rede {
	private LinkedBinaryTree <Character> equipeExploradores;
	private ArrayQueue <Character> equipeReserva;
	private int alturaArvore;


	/**
	 * Constroi uma rede de exploracao dado a configuracao num ficheiro
	 * @param ficheiro - o scanner com o conteudo do ficheiro
	 */
	public Rede(Scanner ficheiro) {
		this.equipeExploradores = LeitorDeArvore.leArvore(ficheiro);
		this.equipeReserva = new ArrayQueue<>();
		this.alturaArvore = this.equipeExploradores.height(); //devido a raiz e necessario subtrair por 1
	}

	@Override
	public boolean equals(Object obj) {
		Rede result = (Rede) obj;
		if (alturaArvore != result.alturaArvore)
			return false;
		if (equipeReserva == null) {
			if (result.equipeReserva != null)
				return false;
		} else if (!equipeReserva.equals(result.equipeReserva))
			return false;
		if (equipeExploradores == null) {
			if (result.equipeExploradores != null)
				return false;
		} else if (!equipeExploradores.equals(result.equipeExploradores))
			return false;
		return true;
	}

	/**
	 * Devolve o raio de exploracao da rede
	 * @return - o raio de exploracao da rede
	 */
	public int raio() {
		return alturaArvore-1;

	}

	/**
	 * Diminui o raio de exploracao da rede para um dado valor
	 * @param n - o novo raio de exploracao
	 * @requires n < this.raio
	 */
	public void diminuirRaioPara(int n) {
		equipeExploradores = diminuirRaioPara(equipeExploradores,n ,0);
		alturaArvore = n+1;
	}

	private LinkedBinaryTree<Character> diminuirRaioPara(LinkedBinaryTree<Character> tree, int raio, int pro) {
		if(tree.isEmpty())
			return new LinkedBinaryTree<>();

		if(pro > raio) {
			acrescentaReservas(tree);
			return new LinkedBinaryTree<>();
		}
		return new LinkedBinaryTree<>(tree.data(),
				diminuirRaioPara(tree.leftSubtree(), raio, pro+1),
					diminuirRaioPara(tree.rightSubtree(), raio, pro+1));
	}

	private void acrescentaReservas(LinkedBinaryTree<Character> tree) {
		Iterator<Character> it = tree.infixIterator();
		while(it.hasNext()) {
			equipeReserva.offer(it.next());
		}
	}

	/**
	 * Completa a rede de exploracao de modo a ficar com todas as folhas possiveis,
	 * na arvore binaria com a altura da rede de exploracao actual
	 * atraves da reativacao (quando possivel) de um explorador de reversa.
	 * O preenchimento eh feito partindo da esquerda e para a periferia.
	 * Caso nao haja exploradores suficientes deve colocar num '*' nos espacos que
	 * ficam por preencher.
	 */
	public void preencherRede() {
		equipeExploradores = preencheArvore(equipeExploradores, 0);
	}


	private LinkedBinaryTree<Character> preencheArvore(LinkedBinaryTree<Character> tree, int pro) {
		if(tree.isPerfect() && tree.height()-1 == raio()-pro)
			return tree;

		LinkedBinaryTree<Character> root = new LinkedBinaryTree<>(tree.data());
		LinkedBinaryTree<Character> left;
		LinkedBinaryTree<Character> right;

		if(!tree.leftSubtree().isEmpty()){
			left = preencheArvore(tree.leftSubtree(), pro+1);
		}else {
			left = new LinkedBinaryTree<>(equipeReserva.isEmpty() ? '*' : equipeReserva.element());
			if(!equipeReserva.isEmpty())
				equipeReserva.remove();
			if(pro < raio()) {
				left =  preencheArvore(left, pro+1); //possivel erro
			}
		}

		if(!tree.rightSubtree().isEmpty()){
			right = preencheArvore(tree.rightSubtree(), pro+1);
		}else {
			right = new LinkedBinaryTree<>(equipeReserva.isEmpty() ? '*' : equipeReserva.element());
			if(!equipeReserva.isEmpty())
				equipeReserva.remove();
			if(pro < raio()) {
				right =  preencheArvore(right, pro+1); //possivel erro
			}
		}
		return new LinkedBinaryTree<>(root.data(),
				left == null ? new LinkedBinaryTree<>() : left,
				right == null ? new LinkedBinaryTree<>() : right);
	}

	/**
	 * Existem exploradores inativos?
	 * @return true se ha exploradores desativos
	 */
	public boolean temExploradoresDeReserva() {
		return !equipeReserva.isEmpty();
	}

	/**
	 * preenche a rede, da esquerda para a direita, partindo do no c para a periferia da rede,
	 * acrescentando todos os nos os existentes no grupo de exploradores de reserva.
	 * @param c o nome do no de onde se parte
	 * @return false sse c nao existe ou eh inapropriado (i.e, se ligacoes por atribuir) ou esta inativo
	 * @requires
	 */
	public boolean distribuiRobosPartindoDe(char c) {
		if(equipeReserva.isEmpty())
			return false;
		equipeExploradores = distribuiRobosPartindoDe(equipeExploradores, c);
		return false;
	}

	private LinkedBinaryTree<Character> distribuiRobosPartindoDe(LinkedBinaryTree<Character> tree, char c) {
		if(tree.isEmpty())
			return tree;
		if(tree.data().equals(c)) {
			return distribuindoRobos(tree);
		}
		return new LinkedBinaryTree<>(tree.data(), distribuiRobosPartindoDe(tree.leftSubtree(), c), distribuiRobosPartindoDe(tree.rightSubtree(), c));
	}

	private LinkedBinaryTree<Character> distribuindoRobos(LinkedBinaryTree<Character> tree) {
		if(!equipeReserva.isEmpty()) {
			char aux = equipeReserva.element();
			equipeReserva.remove();
			if(!equipeExploradores.isEmpty()) {
				char aux2 = equipeReserva.element();
				equipeReserva.remove();
				return new LinkedBinaryTree<>(tree.data(), distribuindoRobos(new LinkedBinaryTree<>(aux)), distribuindoRobos(new LinkedBinaryTree<>(aux2)));
			}
			return new LinkedBinaryTree<>(tree.data(), distribuindoRobos(tree.leftSubtree()), new LinkedBinaryTree<>());
		}
		return tree;
	}

	/**
	 * devolve uma cadeia de caracteres que eh uma representação textual da rede de exploracao,
	 * tal que um no aparece entre parentesis, seguido das sub-arvores esquerda e direita,
	 * tambem entre parentesis
	 * @return (raiz(subarvoreEsquerda()subarvoreDireita())
	 * Por exemplo se a arvore for
	 * A
	 *  B
	 *   C
	 *   E
	 *  F
	 *   G
	 *    H
	 *   I
	 *
	 * devolve a represetacao (A(B(C()E())F(G(H())I())))
	 */
	public String imprimeRedeParentesis() {
		return imprimeRedeParentesis(equipeExploradores)+")";
	}

	private String imprimeRedeParentesis(LinkedBinaryTree<Character> tree) {
		StringBuilder sb = new StringBuilder("(");
		if(tree.isEmpty())
			return sb.toString();
		sb.append("("+imprimeRedeParentesis(new LinkedBinaryTree<>(tree.data(), tree.leftSubtree(), tree.rightSubtree()))+")");
		return sb.toString();
	}

	/**
	 * Devolve uma cadeia de caracteres com uma representacao
	 * textual da rede, com um no por linha, identica ah representacao no ficheiro de entrada e
	 * ah fornecida pelo metodo toString da LinkedBinaryTree
	 * @return representacao textual da rede em forma de arvore
	 */
	public String imprimeRede() {
		return equipeExploradores.toString();
	}

	/**
	 * Devolve uma cadeia de caracteres com uma representacao textual da rede dos exploradores
	 * inactivos (em reserva), com todos os nos na mesma linha e separados por espacos.
	 * @return
	 * @requires
	 */
	public String devolveGrupoReserva() {
		ArrayQueue<Character> aux = equipeReserva.clone();
		StringBuilder sb = new StringBuilder();
		while (!aux.isEmpty()) {
			sb.append(aux.element() + " ");
			aux.remove();
		}
		return sb.toString();
	}



}
