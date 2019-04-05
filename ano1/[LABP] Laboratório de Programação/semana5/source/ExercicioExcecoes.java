package source;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExercicioExcecoes {

	/**
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException{
		try(Scanner sc = new Scanner(new File("inteiros.txt"))){
			while(sc.hasNextLine()) {
				int tamanho = lerTamanhoVector(sc);
				int[] inteiros = lerInteirosVector(sc, tamanho);
				double potencia = lerValorPotencia(sc);
				if(potencia < 0) {
					throw new IllegalArgumentException("[ERROU] vai se foder vagabunda");
				}
				double[] raizes = calculaPotencias(inteiros, potencia);
				imprimeVector(raizes);
			}
		}catch(FileNotFoundException e) {
			System.out.println("Ficheiro nao existe");
		}catch(IllegalArgumentException e) {
			if(e.getMessage() == null) {
				System.out.println("[ACERTOU] vai se foder coraRun");
			}else{
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param sc
	 * @return
	 */
	private static int lerTamanhoVector(Scanner sc) {
		try {
			int result = Integer.parseInt(sc.nextLine());
			if(result < 0.01) {
				throw new InputMismatchException("[ERRO] fodeu xota1");
			}
			return result;
		}catch(InputMismatchException e) {
			if(e.getMessage() == null) {
				System.out.println("[ACERTOU] fodeu xota1");
			}else {
				System.out.println(e.getMessage());
			}
			return 0;
		}
	}
	
	/**
	 * 
	 * @param sc
	 * @param tamanho
	 * @return
	 */
	private static int[] lerInteirosVector(Scanner sc, int tamanho) {
		try {
			String text = sc.nextLine();
			String vText[] = text.split(" ");
			int[] result = new int[vText.length];
			for(int i=0 ; i<result.length ; i++) {
				result[i] = Integer.parseInt(vText[i]);
			}
			if(tamanho != result.length) {
				throw new InputMismatchException("[ERRO] fodeu xota 2");
			}else{
				return result;
			}
		}catch(InputMismatchException e) {
			if(e.getMessage() == null) {
				System.out.println("[ACERTOU] fodeu xota2");
			}else {
				System.out.println(e.getMessage());
			}
			return null;
		}
	}
	
	/**
	 * 
	 * @param sc
	 * @return
	 */
	private static double lerValorPotencia(Scanner sc) {
		return Double.parseDouble(sc.nextLine());
	}
	/**
	 * 
	 * @param inteiros
	 * @param potencia
	 * @return
	 */
	private static double[] calculaPotencias(int[] inteiros, double potencia) {
		double[] result = new double[inteiros.length];
		for(int i=0 ; i<result.length ; i++) {
			result[i] = Math.pow(inteiros[i], potencia);
		}
		return result;
	}
	/**
	 * 
	 * @param raizes
	 */
	private static void imprimeVector(double[] raizes) {
		for(double d: raizes) {
			System.out.println(d + " ");
		}
		System.out.println();
	}
	
}
