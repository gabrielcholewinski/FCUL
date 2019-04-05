package tut;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class TUTEDECC {
	
	public static void main(String[] args) throws IOException {
		/*String puta = "O-ruhan/e.bacana";
		String[] resultados = puta.split("[./-]");
		for(int i = 0; i < resultados.length; i++) {
			System.out.print(resultados[i]);*/
		String fileIn = "lol.txt";
		String fileOut = "ola.txt";
		String fileLetras = "Letras.txt";
		int tipo = 0;
		int quantos = 6;
		//capitalizar(fileIn, tipo, fileOut);
	    //retiraCarateres(fileIn, fileLetras, fileOut);
		//System.out.println(fileIn);
		//numerosPorLetras(fileIn,fileOut);
		//int a = (int)'a'+ 6;
		//char c = (char)a;
		//System.out.println(c);
		//rotacao(fileIn,fileOut,quantos);
		frequenciasLetras(fileIn,fileOut, quantos);
		//detectaPadrao(fileIn,fileLetras,fileOut);
	}
	
	public static void capitalizar ( String fileIn, int tipo, String fileOut) throws FileNotFoundException {
		Scanner leitor = new Scanner(new File(fileIn));
		StringBuilder sb = new StringBuilder();
		PrintWriter escritor = new PrintWriter(fileOut);
		while(leitor.hasNextLine()) {
			if(tipo == -1) {
				sb.append(leitor.nextLine().toLowerCase()+" ");
			}
			if(tipo == 1) {
				sb.append(leitor.nextLine().toUpperCase()+" ");
			}
			if(tipo == 0) {
				sb.append(leitor.nextLine().toLowerCase()+" ");
				sb.replace(0, 1, sb.substring(0,1).toUpperCase());
				for(int i = 0; i < sb.length(); i++) {
						if(sb.charAt(i) == '.' && sb.charAt(i+1) == ' ') {
							sb.replace(i+2, i+3, sb.substring(i+2,i+3).toUpperCase());
						}
				}
			}
		}
		leitor.close();
		escritor.print(sb.toString());
		escritor.close();
	}
	
	public static void retiraCaracteres ( String fileIn, String fileLetras, String fileOut) throws IOException {
		Scanner leitor = new Scanner(new File(fileIn));
		Scanner afilhado = new Scanner(new File(fileLetras));
		PrintWriter escritor = new PrintWriter(fileOut);
		String x = afilhado.nextLine();
		while(leitor.hasNextLine()) {
			String s = leitor.nextLine();
			String[] a = s.split("["+x+" ]");
			for(int i = 0; i < a.length; i++) {
				if(a[i].length() > 0) {
					escritor.print(a[i]);
				}
			}
			escritor.println();
		}
		leitor.close();
		escritor.close();
	}
	
	public static void numerosPorLetras (String fileIn, String fileOut) throws FileNotFoundException {
		StringBuilder sb = new StringBuilder();
		Scanner leitor = new Scanner(new File(fileIn));
		PrintWriter escritor = new PrintWriter(fileOut);
		int count = 0;
	    while(leitor.hasNextLine()) {
	    	count++;
	    	if(count > 1) {
	    		sb.append("\n");
	    	}
	    	String s = leitor.nextLine();
	    	String[] a = s.split("[. ]");
	    	String[]result = {"1","2","3","4","5","6","7","8","9"};
	    	String[]lol = {"um","dois","tres","quatro","cinco","seis","sete","oito","nove"};
	    	for(int i = 0; i < a.length; i++) {
	    		sb.append(a[i]);
	    	}
	    	for(int j = 0; j < sb.length(); j++) {
	    		for(int c = 0; c < lol.length; c++)
	    		if(sb.charAt(j) == result[c].charAt(0)) {
	    			sb.replace(j, j+1, lol[c]);
	    		}
	    	}
	    }
	    leitor.close();
	    escritor.print(sb.toString());
	    escritor.close();
	}
	
	public static void rotacao (String fileIn, String fileOut, int quantos) throws FileNotFoundException {
		Scanner leitor = new Scanner(new File(fileIn));
		PrintWriter escritor = new PrintWriter(fileOut);
		StringBuilder sb = new StringBuilder();
		while(leitor.hasNextLine()) {
			String s = leitor.nextLine();
			String[]result = s.split("[. ]");
				for(int i = 0; i < result.length; i++) {
						sb.append(result[i]);
				}
					for(int j = 0; j < sb.length(); j++) {
						
						if(sb.charAt(j) >= 97 && sb.charAt(j) <= 122) {
							int a = (int)sb.charAt(j) + quantos;
							if(a-122 > 0) {
								a = 97 + (a-122) - 1;
							}
							char c = (char)a;
							String b =""+c;
							sb.replace(j, j+1, b);
						}
					}
		}
		leitor.close();
		escritor.print(sb.toString());
		escritor.close();
	}
	
	
	public static void frequenciasLetras (String fileIn, String fileOut, int tipo) throws FileNotFoundException {
		Scanner leitor = new Scanner(new File(fileIn));
		PrintWriter escritor = new PrintWriter(fileOut);
	    String c = "abcdefghijklmnopqrstuvwxyz";
		int[]count = new int[c.length()];
		int totalChars = 0;
		while(leitor.hasNextLine()) {
			char[] letras = leitor.nextLine().toCharArray();			
			for (int i = 0; i < letras.length; i++) {
				char b = letras[i];
				if(c.indexOf(b) >= 0) {
					count[c.indexOf(b)]++;
					totalChars++;
				}	
			}
		}
		double[] freq = new double[count.length];
		for(int i = 0; i < count.length; i++) {
			freq[i] = (double) count[i]/totalChars;
			escritor.print(c.charAt(i) + " " + freq[i]);
			escritor.println();
		}
		leitor.close();
		escritor.close();
	}
	
	public static void detectaPadrao (String fileIn,String filePadroes, String fileOut) throws IOException {
		Scanner leitor = new Scanner(new File(fileIn));
		Scanner afilhado = new Scanner(new File(filePadroes));
		PrintWriter escritor = new PrintWriter(fileOut);
		String v = afilhado.nextLine();
		while(leitor.hasNextLine()) {	
			String[]lol = leitor.nextLine().split("[ ]");
			String[]teste = v.split("[ ]");
			int[]zoro = new int[lol.length];
			for(int i = 0; i < teste.length; i++) {
				for(int j = 0; j < lol.length; j++) {
					if(lol[j].contains(teste[i])) {
						zoro[i]++;
					}
				}
			}
			for(int k = 0; k < zoro.length; k++) {
				escritor.println(teste[k] + ": " + zoro[k] + " vezes");
			}
			escritor.println();
		}
		leitor.close();
		afilhado.close();
		escritor.close();
	}
}
	
