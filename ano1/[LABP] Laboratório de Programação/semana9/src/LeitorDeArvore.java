package src;

import java.util.Scanner; 

/**
 * @author marco
 */
public class LeitorDeArvore {

	private static final String BLANK = "";
	private static final char END = '#'; 
	private static int space = 0; 
	private static String lastLine = "";
	private static int lastSpace = 0;

    /**
     * Le de um scanner dado para construcao de uma arvore
     * @param sc - o scanner usado na leitura
     * @return a arvore binaria de acordo com a estrutura do ficheiro
     * @requires sc != null
     */
	public static LinkedBinaryTree<Character> leArvore(Scanner file) { 
		String actualLine = null;
		LinkedBinaryTree<Character> result = null;
		
		if (file.hasNext()){ 
			actualLine = file.nextLine();
		} else {
			return  new LinkedBinaryTree<>();
		}
		if (actualLine.trim().charAt(0) == END){  
			throw  new RuntimeException("Format not valid");
		}	

		char root = actualLine.trim().charAt(0);
		LinkedBinaryTree<Character>  left = buildTree(file);
		LinkedBinaryTree<Character> right = buildTreeR(file);  

		result = new LinkedBinaryTree<>(root, left, right);
		
		file.close();
		space = 0;
		lastLine = BLANK;
		lastSpace = 0;
		
		return result;
	}

    /**
     * Constroi a arvore dado um scanner
     * @param sc o ascanner de onde se le
     * @return uma arvore binaria construida a partir do leitor
     * @requires sc != null
     */
	private static  LinkedBinaryTree<Character> buildTree(Scanner file) { 
		String actualLine = null;
		char root;

		if (file.hasNext()){
			actualLine = file.nextLine();
		} else {
			return new LinkedBinaryTree<>();
		} 

		if (!actualLine.isEmpty() && actualLine.trim().charAt(0) != END){
			root = actualLine.trim().charAt(0);
		} else {
			return new LinkedBinaryTree<>();
		} 
		 
		lastLine = actualLine;

		if (countSpaces(actualLine) == space+1){ 
			space++;
			lastLine = BLANK;
			LinkedBinaryTree<Character>  left = buildTree(file); 
			//lastLine = BLANK;
			lastSpace = space; 
			LinkedBinaryTree<Character> right = buildTreeR(file);
			return new LinkedBinaryTree<>(root, left, right);
		} else {  
			return new LinkedBinaryTree<>();
		}

	} 	 

	private static  LinkedBinaryTree<Character> buildTreeR(Scanner file) { 
		if (lastLine.length()>0){
			space--;
			char root = lastLine.trim().charAt(0);  
			if (space == countSpaces(lastLine)-2){  
				lastLine = BLANK;  
				space = lastSpace; 
				return new LinkedBinaryTree<>(root, buildTree(file), buildTreeR(file));
			} else { 
				return new LinkedBinaryTree<>();
			}
		} else {
			return new LinkedBinaryTree<>();
		}
	}


    /**
     * conta os espacaos necesarios
     * @param s a string de onde se conta
     * @return o numero de espacos
     * @requires s!= null
     */
	private static int countSpaces(String s){
		return s.length() - s.replaceAll(" ", "").length();
	} 
}
