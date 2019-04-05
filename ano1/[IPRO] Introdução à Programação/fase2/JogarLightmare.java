package fase2;

import java.util.Random;
/**
 * Esta classe contem o metodo experimentaJogar e outras
 * funcoes necessarias para que o jogo corra
 * @author 040
 * @author Tiago Carvalho 51034
 * @author Ruhan Azevedo 51779
 * @date Novembro 2017
 *
 */
public class JogarLightmare {
	public static boolean continua = true;
	public static int numeroAtaques = 0;
	/*
	 * Quem ganha o jogo?
	 * @param posMonius - posicao do Monius
	 * @param posOutro - posicao do Outro
	 * @param angulo - angulo de ataque
	 * @param linhas - altura do caminho
	 * @param colunas - largura do caminho
	 * @param maxVezes - numero maximo de jogadas
	 * @requires posMonius >= 1 && posMonius <= comprim * altura && 
     *           posOutro >= 1 && posOutro < = comprim * altura && 
     *           angulo in {-90, 90, -180, 180, 360} &&
     *           colunas > 0 && linhas > 0
	 */
	public static void experimentaJogar(int posMonius, int posOutro, double angulo, int linhas, int colunas, int maxVezes){
		continua = true;
		numeroAtaques = 0;
		int linhaMonius = FuncoesMonius.linha(posMonius,colunas);
		int colunaMonius = FuncoesMonius.coluna(posMonius,colunas,FuncoesMonius.linha(posMonius,colunas));
		int linhaOutro = FuncoesMonius.linha(posOutro,colunas);
		int colunaOutro = FuncoesMonius.coluna(posOutro,colunas,FuncoesMonius.linha(posOutro,colunas));
		System.out.println("############################################################");
		System.out.println("O angulo de ataque do Monius serah sempre "+angulo+" graus durante o jogo");
		boolean outroAtingido = false;
		caminho(linhas, colunas,linhaMonius, colunaMonius, linhaOutro, colunaOutro,outroAtingido);
		System.out.println();
		Random gerador = new Random(1);
		int outroChegaFim = linhas*colunas;

		for(int k = 1; k <= maxVezes && !outroAtingido && colunaOutro * linhaOutro != outroChegaFim && continua;k++){
			int alcanceLuz = gerador.nextInt(colunas)+1; //Gerar valor alcance
			System.out.println("Alcance de luz nesta jogada: " + alcanceLuz);
			
			int saltoOutro = gerador.nextInt(colunas*2+1)-colunas; //Gerar salto do Outro
			
		
			boolean moniusAtaca = gerador.nextBoolean(); //Gerar se o Monius ataca
			
			int saltoMonius = alcanceLuz*2; //Gerar salto
			
			if(moniusAtaca){ //Monius ataca
				posOutro = movOutro(posOutro, linhaOutro, colunaOutro, saltoOutro, colunas, linhas);
				linhaOutro = FuncoesMonius.linha(posOutro, colunas);
				colunaOutro = FuncoesMonius.coluna(posOutro, colunas, FuncoesMonius.linha(posOutro, colunas));
				//movimento do Outro
				System.out.println("Monius vai atacar!");
				outroAtingido = FuncoesMonius.foiAtingido(posMonius, posOutro, alcanceLuz, angulo, colunas, linhas);
				numeroAtaques++; //ataque do Monius
			}
			else{ //Monius salta
				System.out.println("Monius salta: " + saltoMonius +"     "+"Monius salta para " + posMonius);
				if(posMonius > posOutro) { //salto negativo
					 saltoMonius = -saltoMonius;
					 if(posMonius + saltoMonius > 0) {
						 posMonius += saltoMonius;
					 }
					 else {
						 posMonius = colunas * linhas + (posMonius + saltoMonius);
					 }
					}
				else{ //salto positivo
					 if(posMonius + saltoMonius <= colunas * linhas) {
						 posMonius += saltoMonius;
					 }
					 else if (posMonius + saltoMonius > colunas * linhas){
						 posMonius = (posMonius + saltoMonius) - colunas * linhas;
					 }
				}
				posOutro = movOutro(posOutro, linhaOutro, colunaOutro, saltoOutro, colunas, linhas);
				linhaOutro = FuncoesMonius.linha(posOutro, colunas);
				colunaOutro = FuncoesMonius.coluna(posOutro, colunas, FuncoesMonius.linha(posOutro, colunas));
				//movimento do Outro
			}
			
			System.out.println("Outro move-se: " + saltoOutro+"     "+"Nova posiÁ„o do Outro: " + posOutro);
			
	

			linhaMonius = FuncoesMonius.linha(posMonius,colunas);
			colunaMonius = FuncoesMonius.coluna(posMonius,colunas,FuncoesMonius.linha(posMonius,colunas));

			caminho(linhas, colunas, linhaMonius, colunaMonius, linhaOutro, colunaOutro, outroAtingido);
			System.out.println();
			
			if (k == maxVezes) { //Outro escapa
				System.out.println("Parabens ao Outro! Conseguiu escapar durante " + k +" jogadas!");
			}
			else if (outroAtingido) { //Outro atingido
				System.out.println("Oooops! O Outro foi atingido ao fim de " + k + " jogadas!");
			}
			else if (colunaOutro * linhaOutro == outroChegaFim) { //Outro chega ao fim do caminho
				System.out.println("Parabens! O Outro atravessou o caminho ao fim de " + k + " jogadas!");
			}
			else if (!continua) { //Outro È atingido
				System.out.println("Oooops! O Outro foi atingido ao fim de " + k + " jogadas!");
			}
		}
	System.out.println("O Monius atacou " + numeroAtaques + " vezes");
	}//fim metodo experimentaJogar
/*
 * Imprime o caminho resultante de cada jogada
 * @param linhas - altura do caminho
 * @param colunas - comprimento do caminho
 * @param linhaMonius - linha do Monius
 * @param colunaMonius - coluna do Monius
 * @param linhaOutro - linha do Outro
 * @param colunaOutro - coluna do Outro
 * @param outroAtingido - se o Outro foi atingido
 * @requires linhaMonius >= 1 && colunaMonius >= 1 &&
 * 			linhaOutro >= 1 && colunaOutro >= 1 &&
 * 			linhas > 0 && colunas > 0
 * 			
 */
	public static void caminho(int linhas, int colunas, int linhaMonius, int colunaMonius, int linhaOutro, int colunaOutro, boolean outroAtingido){
			for( int i = 1 ; i <= linhas ; i++ ){
				for(int j = 1 ; j <= colunas ; j++){
					if(i == linhaMonius && j == colunaMonius  ){
						//Monius e Outro na mesma posicao
						if(colunaOutro == colunaMonius && linhaMonius == linhaOutro) {
							System.out.print("@");
							continua = false;
						}
						//posicao do Monius
						else {
						 System.out.print("M");
						}
					 }
					else if (i == linhaOutro && j == colunaOutro ) {
						//Outro atingido
						if(outroAtingido) {
							System.out.print("*");
						}
						//posicao do Outro
						else {
							System.out.print("O");
						}
					}
					else {
						System.out.print("_");
					}
				}

				System.out.println();
			}
	}
	/*
	 * Como se movimenta o Outro?
	 * @param posOutro - posicao do Outro
	 * @param linhaOutro - linha do Outro
	 * @param colunaOutro - coluna do Outro
	 * @param saltoOutro - salto do Outro
	 * @param colunas - comprimento do caminho
	 * @param linhas - altura do caminho
	 * @return nova posicao do Outro
	 * @requires posOutro >= 1 &&
	 * 			linhaMonius >= 1 && colunaMonius >= 1 &&
	 * 			linhaOutro >= 1 && colunaOutro >= 1 &&
	 * 			linhas > 0 && colunas > 0
	 */
	public static int movOutro(int posOutro, int linhaOutro, int colunaOutro, int saltoOutro, int colunas, int linhas) {
		if (saltoOutro == 0) {
			return posOutro;
		}
		else if (posOutro + saltoOutro < 0 || posOutro + saltoOutro > linhas * colunas) { 
			if (linhaOutro % 2 == 0) { //Ricochete esquerda linha par
				colunaOutro = 2 - colunaOutro + saltoOutro;
				return posObjeto(linhaOutro, colunaOutro, colunas);
			}
			else { //linha impar
				if (posOutro + saltoOutro > linhas * colunas) { //Ricochete direita
					colunaOutro = Math.abs(2 - colunaOutro - saltoOutro);
					return posObjeto(linhaOutro, colunaOutro, colunas);
				}
				else {
					if (saltoOutro == - colunas && posOutro == 1) { //2 ricochetes primeira linha
						return 1 * colunas + 1;
					}
					else {
						colunaOutro =  2 - colunaOutro - saltoOutro;  //Ricochete esquerda
						return posObjeto(linhaOutro, colunaOutro, colunas);
					}
				}
			}
			
		}
		else {
			return posOutro + saltoOutro;
		}
	}
	/*
	 * Transforma a coluna e linha em posicao
	 * @param linhaObjeto - linha da personagem
	 * @param colunaObjeto - coluna da personagem
	 * @param colunas - comprimento do caminho
	 * @return posicao do objeto
	 * @requires linhaObjeto >= 1 && colunaObjeto >= 1 &&
	 * 			colunas >= 1
	 */
	public static int posObjeto(int linhaObjeto, int colunaObjeto, int colunas){ //metodo calcular posicao 
		if(linhaObjeto % 2 != 0){
			return linhaObjeto * colunas - (colunas - colunaObjeto);
		}else{
			return linhaObjeto * colunas - (colunaObjeto - 1);
		}
	}
	//fim metodo posObjeto
}//fim classe

