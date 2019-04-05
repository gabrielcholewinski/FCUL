package fase3;

import java.util.Scanner;
import java.util.Random;

public class JogarLightmare3 {
	/*Corre o jogo LightmareMoniusland3
	*@param iniMonius - vetor com as posicoes do Monius
	*@param angulo - angulo de ataque do Monius
	*@param alcanceLuz - alcance do ataque do Monius
	*@param linhas - altura do caminho
	*@param colunas - comprimento do caminho
	*@param maxVezes - maximo de jogadas
	*@param gerador - gerador Random
	*@param leitor - scanner
	*@requires iniMonius.length > 0 && angulo in {-180, -90, 90, 180, 360} &&
						alcanceLuz > 0 && linhas > 0 && colunas > 0 && maxVezes > 0
	*/
	public static void experimentaJogar3 (int[] iniMonius, int angulo, int alcanceLuz,
			int linhas, int colunas, int maxVezes,
			Random gerador, Scanner leitor) {
		System.out.println("Como te chamas?");
		String nome = leitor.nextLine(); //recebe o nome do jogador
		System.out.println("############################################################");
		System.out.println("O angulo de ataque dos Monius serah sempre " + angulo +
											" graus durante o jogo");
		System.out.println("O alcance de luz dos sabres dos Monius serah sempre "
												+ alcanceLuz + " durante o jogo");
		int posOutro = 1;

		boolean outroAtingido = false;
		int numeroMonius = iniMonius.length;
		System.out.println(tabuleiro(linhas, colunas, posOutro, iniMonius, outroAtingido));
		outroAtingido = jogadaInicial(iniMonius, posOutro, nome);
		// ve se o jogo acaba na primeira jogada
		for(int i = 1; i <= maxVezes && !outroAtingido && numeroMonius != 0
																 && posOutro < linhas * colunas; i++) {

			int linhaOutro = FuncoesMonius3.linha(posOutro, colunas);
			//int colunaOutro = FuncoesMonius3.coluna(posOutro, colunas, linhaOutro);
			int minimo = saltoMinimo(colunas, posOutro);
			int maximo = saltoMaximo(colunas, linhas, linhaOutro, posOutro);
			String errMess = "Tem que ser um inteiro entre " + minimo + " e " + maximo;
			System.out.print(nome + ", quantas posicoes vais saltar? (Tem que ser entre ");
			System.out.println(minimo + " e " + maximo + ")");
			int saltoOutro = FuncoesMonius3.lerValorNoIntervalo(minimo, maximo, errMess,
											 leitor); //recebe o valor do salto do Outro
			int antigaPosOutro = posOutro;
			posOutro += saltoOutro;
			System.out.println(nome + " salta para " + posOutro);

			for(int j = 0; j < iniMonius.length; j++) { //ataques do Monius
				if(((iniMonius[j] % 2 == 0 && i % 2 != 0) || (iniMonius[j] % 2 != 0
						&& i % 2 == 0)) && iniMonius[j] != -62) { //ve se o Monius ataca
					System.out.println("Monius em: " + iniMonius[j] + " ataca!");
					if (!outroAtingido) { //caso o Outro ainda nao tenha sido acertado
						outroAtingido = FuncoesMonius3.foiAtingido(iniMonius[j], posOutro,
																						alcanceLuz, angulo, colunas, linhas);
					}
				}
			}
			for (int k = 0; k < iniMonius.length; k++) { //movimentos do Monius
				if ((iniMonius[k] % 2 == 0 && i % 2 == 0) || (iniMonius[k] % 2 != 0 &&
					 i % 2!= 0)){ //ve se o Monius salta
					int saltoMonius = gerador.nextInt(colunas) + 1;
					int novaPosMonius = movMonius(iniMonius[k], antigaPosOutro, saltoMonius);
					if ((novaPosMonius < 1 || novaPosMonius > linhas * colunas) && iniMonius[k] != -62) {
						System.out.println("Monius em: " + iniMonius[k] +
																" salta para fora de jogo!");
						numeroMonius--; //decresce um no numero de Monius
						iniMonius[k] = -62; //tira o Monius fora do tabuleiro
					}
					else if (iniMonius[k] != -62) { //salta caso o Monius nao esteja morto
						System.out.println("Monius em: " + iniMonius[k] + " salta para "
															+ novaPosMonius);
						iniMonius[k] = novaPosMonius;
						if (iniMonius[k] == posOutro) { //ve se o Monius nao cai no Outro
							outroAtingido = true;
						}
					}
				}
			}
			System.out.println(tabuleiro(linhas, colunas, posOutro, iniMonius,
												outroAtingido)); //imprime o tabuleiro
			mensagensFimJogo(linhas, colunas, posOutro, outroAtingido, numeroMonius, i,
											nome, maxVezes); //imprime as mensagens de fim de jogo
		}
	}
	/*Calcula o salto minimo do Outro
	*@param colunas - comprimento do caminho
	*@param posOutro - posicao do Outro
	*@return numero minimo possivel para o salto do Outro
	*@requires colunas > 0 && posOutro > 0
	*/
	public static int saltoMinimo (int colunas, int posOutro) {
		if(posOutro <= colunas) { //caso o Outro esteja na primeira linha
			return 1 - posOutro;
		}
		else { //nas restantes
			return -colunas;
		}
	}
	/*Caclula o salto maximo do Outro
	*@param colunas - comprimento do caminho
	*@param linhas - altura do caminho
	*@param linhaOutro - linha do Outro
	*@param posOutro - posicao do Outro
	*@return numero maximo possivel para o salto do Outro
	*@requires colunas > 0 && linhas > 0 && linhaOutro > 0 && posOutro > 0
	*/
	public static int saltoMaximo (int colunas, int linhas, int linhaOutro,
																int posOutro) {
		if(linhaOutro == linhas) { //caso o Outro esteja na ultima linha
			return linhas * colunas - posOutro;
		}
		else { //nas restantes
			return colunas;
		}
	}
	/*Calcula o movimento do Monius
	*@param posMonius - posicao do Monius
	*@param posOutro - posicao do Outro
	*@param saltoMonius - salto do Monius
	*@return posicao final do Monius
	*@requires posMonius > 0 && posOutro > 0 && saltoMonius > 0
	*/
	public static int movMonius (int posMonius, int posOutro, int saltoMonius) {
		if (posMonius > posOutro) {
			return posMonius - saltoMonius;
		}
		else {
			return posMonius + saltoMonius;
		}
	}
	/*Imprime as mensagens de fim de jogo
	*@param linhas - altura do caminho
	*@param colunas - comprimento do caminho
	*@param posOutro - posicao do outroAtingido
	*@param outroAtingido - estado do Outro
	*@param numeroMonius - numero de Monius vivos
	*@param i - numero da jogada
	*@param nome - nome do jogador
	*@param maxVezes - maximo de jogadas
	*@requires linhas > 0 && colunas > 0 && posOutro > 0 && i > 0 && nome != null
	           && maxVezes > 0
	*/
	public static void mensagensFimJogo(int linhas, int colunas, int posOutro,
			boolean outroAtingido, int numeroMonius, int i, String nome, int maxVezes) {
		if (outroAtingido) { //se o Outro for atingido
			if (i == 1) { //na 1a jogada
				System.out.println("Oooops, " + nome + "! Foste atingido ao fim de "
												+ i + " jogada!");
			}
			else { //noutras jogadas
				System.out.println("Oooops, " + nome + "! Foste atingido ao fim de "
												+ i + " jogadas!");
			}
		}
		else if (posOutro == linhas * colunas) { //se o Outro chegar ao fim do caminho
			if(i == 1) { //na 1a jogada
				System.out.println("Parabens, " + nome + "! Atravessaste o caminho ao fim de "
												+ i + " jogada!");
			}
			else { //noutras jogadas
				System.out.println("Parabens, " + nome + "! Atravessaste o caminho ao fim de "
												+ i + " jogadas!");
			}
		}
		else if (numeroMonius == 0) { //se os Monius morrerem todos
			System.out.println("Parabens, " + nome + "! Tens o caminho livre!");
		}
		else if (i == maxVezes) { //se chegar ao maximo de jogadas
			System.out.println("Parabens, " + nome + "! Conseguiste escapar durante "
												+ i + " jogadas!");
		}
	}
	/*Verifica se o jogo nao acaba na primeira jogada
	*@param iniMonius - vetor que contem as posicoes dos Monius
	*@param posOutro - posicao do Outro
	*@param nome - nome do jogador
	*@return estado do jogo apos a primeira jogada
	*@requires iniMonius.length > 0 && posOutro > 0 && nome != null
	*/
	public static boolean jogadaInicial (int[] iniMonius, int posOutro, String nome) {
		for (int i = 0; i < iniMonius.length; i++) {
			if (iniMonius[i] == posOutro) {
				System.out.println("Oooops, " + nome + "! Foste atingido ao fim de "
												+ "0 jogadas!");
				return true;
			}
		}
		return false;
	}
	/*Imprime o tabuleiro
	*@param linhas - altura do caminho
	*@param colunas - comprimento do caminho
	*@param posOutro - posicao do outroAtingido
	*@param iniMonius - vetor com posicoes dos Monius
	*@param outroAtingido - estado do Outro
	*@return caminho em forma de string
	*@requires  linhas > 0 && colunas > 0 && posOutro > 0 && iniMonius.length > 0
	*/
	public static String tabuleiro(int linhas, int colunas, int posOutro,
	int[] iniMonius, boolean outroAtingido){
		int linhaOutro = FuncoesMonius3.linha(posOutro, colunas);
		int colunaOutro = FuncoesMonius3.coluna(posOutro, colunas, linhaOutro);

		StringBuilder sb = new StringBuilder(""); //cria o StringBuilder

		 for (int i = 1; i <= linhas; i++) {
			 for (int j = 1; j <= colunas; j++) {
				 int posicao = posObjeto(i, j, colunas);
				 boolean moniusContido = FuncoesMonius3.contidoEmParte(posicao,
				 iniMonius, iniMonius.length); //ve se o Monius esta na posicao corrente
				 if (moniusContido) {
					 if (linhaOutro == i && colunaOutro == j) {
						 sb.append("@"); //caso o Monius esteja na mesma posicao do Outro
					 }
					 else {
					 	sb.append("M"); //Monius sozinho
					}
				 }
				 else if (linhaOutro == i && colunaOutro == j) {
					 if (outroAtingido) {
						 sb.append("*"); //Outro atingido
					 }
					 else {
						 sb.append("&"); //Outro sozinho
					 }
				 }
				 else {
					 sb.append("_"); //espacos do caminho
				 }
			 }
			 sb.append('\n');
		 }
		 return sb.toString();
	 }
	 /*
	 * Transforma a coluna e linha em posicao
	 * @param linhaObjeto - linha da personagem
	 * @param colunaObjeto - coluna da personagem
	 * @param colunas - comprimento do caminho
	 * @return posicao do objeto
	 * @requires linhaObjeto > 0 && colunaObjeto > 0 &&
	 * 			colunas > 0
	 */
		 public static int posObjeto(int linhaObjeto, int colunaObjeto, int colunas){
			if(linhaObjeto % 2 != 0){
				return linhaObjeto * colunas - (colunas - colunaObjeto);
			}else{
				return linhaObjeto * colunas - (colunaObjeto - 1);
			}
		}
}
