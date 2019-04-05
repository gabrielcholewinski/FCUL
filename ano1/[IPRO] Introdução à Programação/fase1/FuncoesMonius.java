package fase1;

/**
 * Esta classe permite calcular se o Monius pode ou não acertar o Outro
 *
 * @author ip040 - Ruhan Victor(51779) e Tiago Carvalho(51034)
 * @version 1.0
 */

//inicio classe FuncoesMonius
public class FuncoesMonius { 
/**
 * Esse método calcula a linha
 * @param posObjeto
 * @param comprim
 * 
 * @require posObjeto%comprim != 0 se a divisão por resto for diferente de zero
 * 								   a divisão de PosObjeto por comprim, somado 
 * 								   um, irá dar a posição das linhas do Objeto;
 * 
 * 			posObjeto%comprim == 0 se a divisão por resto for igual à zero, a
 * 								   divisão de posObjeto por comprim irá dar a
 * 								   posição das linhas do Objeto;
 * 
 * @return retorna o valor de linhaObjeto
 */
	//inicio metodo linha;
	static int linha(int posObjeto, int comprim){ 
		
		//declara uma variável do tipo int com o nome linhaObjeto;
		int linhaObjeto; 

		//se divisãoCom resto de posObjeto por comprim for diferente de zero;
		if(posObjeto % comprim != 0){ 
			
			/* linhaObjeto recebe o valor da divisão 
			   de posObjeto com comprim, somado +1; */
			linhaObjeto = (posObjeto/comprim)+1; 
			
		/* Se a divisão com resto de posObjeto 
		   por comprim não for diferente de zero; */
		}else{ 
			
			/* linhaObjeto recebe o valor da divisão 
			   inteira de posObjeto por comprim; */
			linhaObjeto = posObjeto/comprim; 
		}
		//retorna o valor de linhaObjeto;
		return linhaObjeto; 
		
	//fim metodo linha
	} 
	/**
	 * Esse método calcula a coluna
	 * @param posObjeto Posição do Objeto
	 * @param comprim comprimento ou número de colunas da tabela
	 * 
	 * @require posObjeto % comprim != 0	Se a divisão com resto de posObjeto 
	 * 										por comprim for diferente de zero
	 * 										(não for divisor):
	 * 										
	 * 			linha % 2 != 0				e a linha for ímpar, o valor da 
	 * 										coluna será a divisão por resto de
	 * 										posObjeto por comprim;
	 * 				
	 * 			linha % 2 == 0				se a linha for par, o valor da 
	 * 										coluna será a divisão com resto de
	 * 										posObjeto por comprim, somado um, e 
	 * 										subtraído comprim;
	 * 
	 * 			posObjeto % comprim == 0	Se a divisão com resto de posObjeto
	 * 										por comprim for igual à zero(é 
	 * 										divisor): 
	 * 
	 * 			linha % 2 == 0				se a divisão com resto de linha por
	 * 										dois for igual à zero, a coluna do 
	 * 										objeto será igual à um;
	 * 
	 * 			linha % 2 != 0				se a divisão com resto de linha por
	 * 										dois for diferente de zero, a 
	 * 										coluna será igual ao comprim;
	 * 
	 * @return retorna o valor de colunaObjeto
	 */

	//inicio metodo coluna;
	static int coluna(int posObjeto, int comprim){ 
		
		//declara uma variável do tipo int com o nome colunaObjeto;
		int colunaObjeto;
		
		/*declara uma variável do tipo int com o nome linha, 
		  e chama o método linha dentro dela; */
		int linha = linha(posObjeto, comprim);
		
		//se o valor de posObjeto não é divisor de comprim;
		if(posObjeto % comprim != 0) {
			
			//se a linha não for par;
			if(linha % 2 != 0){ 
				
				/* colunaObjeto recebe a divisão de resto
				   entre PosObjeto e comprim; */
				colunaObjeto = posObjeto % comprim;
				
			//se a linha for par;
			}else{ 
				
				/*colunaObjeto recebe o valor da subtração entre comprim 
				  com a divisão com resto de PosObjeto e comprim somado +1; */
				colunaObjeto = comprim - (posObjeto % comprim) + 1; 
			}
		}
		//se o valor de posObjeto for divisor de comprim;
		else { 
			
			//se a linha for par;
			if (linha % 2 == 0) { 
				
				//colunaObjeto recebe o valor 1;
				colunaObjeto = 1; 
			}
			
			//se a linha não for par;
			else { 
				
				//colunaObjeto recebe o valor de comprim;
				colunaObjeto = comprim; 
			}
		}

		//retorna o valor de colunaObjeto;
		return colunaObjeto; 
		
	//fim metodo coluna;
	} 
/**
 * Esse método calcula a distancia entre Monius e Outro
 * @param linhaMonius
 * @param colunaMonius
 * @param linhaOutro
 * @param colunaOutro
 * 
 * @require A distância entre um Monius e um Outro será a raíz quadrada da 
 * 			adição do módulo da subtração entre linha do Monius com linha do
 * 			Outro e a adição do módulo da subtração entre a coluna do Monius
 * 			e a coluna do Outro;
 * 
 * @return retorna a distancia entre Monius e Outro
 */
	//inicio metodo distancia
	static double Distancia(int linhaMonius, int colunaMonius, int linhaOutro,
															 int colunaOutro){
		
		/* cria uma variável do tipo double com o nome formula, e 
		   calcula a distância entre Monius e Outro; */
		double formula = Math.sqrt(Math.pow(linhaMonius - linhaOutro, 2) +
						 		   Math.pow(colunaMonius - colunaOutro, 2)); 
		
		//retorna o valor da variável formula;
		return formula; 

	//fim metodo distancia;
	} 
/**
 * Esse método permite saber se o Monius pode atingir o Outro
 * @param posMonius
 * @param posOutro
 * @param alcanceLuz
 * @param angulo
 * @param comprim
 * @param altura
 * @require distancia <= alcanceLuz - Se a distância entre o Monius for menor
 * 									  ou igual ao alcance do sabre de Luz, isso 
 * 									  significa que o Sabre pode atingir o 
 * 									  Monius.
 * 
 * 			angulo == 90.0	- Se o angulo for igual à 90 graus:
 * 
 * 			linhaMonius % 2 == 0 && linhaOutro <= linhaMonius && 
 * 			colunaOutro <= colunaMonius - Se a linha dos Monius for positiva, e 
 * 										  a linha do Outro for menor ou igual à
 * 										  linha do Monius, e a coluna do Outro
 * 										  for menor ou igual a coluna dos 
 * 										  Monius, o Monius atinge o Outro;
 * 
 * 			linhaMonius % 2 != 0 && linhaOutro >= linhaMonius &&
 * 			colunaOutro >= colunaMonius - Se a linha dos Monius for negativa, e 
 * 										  a linha do Outro for maior ou igual à
 * 										  linha do Monius, e a coluna do Outro
 * 										  for maior ou igual a coluna dos 
 * 										  Monius, o Monius atinge o Outro;
 * 
 * 			angulo == -90.0 - Se o angulo for igual à -90 graus:
 * 
 * 			linhaMonius % 2 == 0 && linhaOutro >= linhaMonius &&
 * 			colunaOutro <= colunaMonius - Se a linha dos Monius for positiva, e 
 * 										  a linha do Outro for maior ou igual à
 * 										  linha do Monius, e a coluna do Outro
 * 										  for menor ou igual a coluna dos 
 * 										  Monius o Monius atinge o Outro;
 * 
 *			linhaMonius % 2 != 0 && linhaOutro <= linhaMonius && 
 *			colunaOutro >= colunaMonius - Se a linha dos Monius for negativa, e 
 * 										  a linha do Outro for menor ou igual à
 * 										  linha do Monius, e a coluna do Outro
 * 										  for maior ou igual a coluna dos 
 * 										  Monius o Monius atinge o Outro;
 * 			
 * 			angulo == 180.0 || angulo == -180.0 - Se o angulo for de 180 graus
 * 												  ou -180 graus
 * 
 * 			linhaMonius % 2 == 0 && colunaOutro <= colunaMonius - Se a linha do
 * 										  Monius for positiva, e a coluna do 
 * 										  Outro for menor ou igual a coluna do
 * 										  Outro, o Monius atinge o Outro;
 * 
 * 			linhaMonius % 2 != 0 && colunaOutro >= colunaMonius - Se a linha do
 * 										  Monius for negativa, e a coluna do 
 * 										  Outro for maior ou igual a coluna do
 * 										  Outro, o Monius atinge o Outro;
 * 
 * 			angulo == 360.0 || angulo == -360.0 - Se o angulo for de 360 graus
 * 										  ou de -360 graus, o Monius atinge o
 * 										  Outro; 
 * 
 * @return retorna o valor booleano se o Monius pode atingir o Outro ou não
 */
	//inicio metodo foiAtingido
	static boolean foiAtingido(int posMonius, int posOutro, int alcanceLuz, 
								double angulo, int comprim, int altura){
		
		//chamada linha dos Monius;
		int linhaMonius = linha(posMonius,comprim);
		
		//chamada coluna dos Monius;
		int colunaMonius = coluna(posMonius,comprim); 
		
		//chamada linha do Outro;
		int linhaOutro = linha(posOutro, comprim); 
		
		//chamada coluna do Outro;
		int colunaOutro = coluna(posOutro, comprim); 
		
		//chamada do metodo Distancia;
		double distancia = Distancia(linhaMonius, colunaMonius, linhaOutro,
									 colunaOutro); 
		
		/* Declara uma variável booleana com o nome result e a inicializa
		com o valor false; */
		boolean result = false; 
		
		//para saber se a distância é menor que o alcance do sabre;
		 if(distancia <= alcanceLuz) { 
			 
			//para angulos de 90 graus;
	          if (angulo == 90.0) { 
	        	  
	        	//90 graus à esquerda;
	              if(linhaMonius % 2 == 0 && linhaOutro <= linhaMonius &&
	            	 colunaOutro <= colunaMonius) {
	            	  
	            	//altera o valor da variável booleana result, para true;
	                  result = true; 
	                  
	                //90 graus à direita;
	              }else if(linhaMonius % 2 != 0 && linhaOutro >= linhaMonius &&
	            		  	colunaOutro >= colunaMonius) { 
	            	  
	            	//altera o valor da variável booleana result, para true;
	                  result = true; 
	              }
	          }

	        //para angulos de -90 graus
	          if(angulo == -90.0) { 
	        	  
	        	//-90 graus à esquerda;
	              if(linhaMonius % 2 == 0 && linhaOutro >= linhaMonius &&
	            		  				   colunaOutro <= colunaMonius) {
	            	  
	            	//altera o valor da variável booleana result, para true;
	            	  result = true; 
	            	  
	            	//-90 graus à direita
	              }else if(linhaMonius % 2 != 0 && linhaOutro <= linhaMonius &&
	            		  						 colunaOutro >= colunaMonius) { 

	            	//altera o valor da variável booleana result, para true;
	                  result = true; 
	            	  }
	          }

	        //180graus ou -180 graus à esquerda;
	          if(angulo == 180.0 || angulo == -180.0){
	        	  
	        	  //Se a divisão com resto de linhaMonius por 2 for igual a 0
	              if(linhaMonius % 2 == 0 && colunaOutro <= colunaMonius){
	            	  
	            	//altera o valor da variável booleana result, para true;
	            	  result = true;
	            	  
	            	//180 graus ou -180 graus à direita;
	              }else if(linhaMonius %2 != 0 && colunaOutro >= colunaMonius){ 
	            	  
	            	//altera o valor da variável booleana result, para true;
	            	  result = true;
	              }
	          }

	          //360 graus ou -360 graus à esquerda ou à direita;
	          if (angulo == 360.0 || angulo == -360.0) {
	        	  
	        	//altera o valor da variável booleana result, para true;
	        	  result = true; 
	          }
	    }else{
	    	result = false;
	    }
		 
		//retorna o valor da variável booleana result;	 
	  return result; 
	
	 //fim do metodo foiAtingido
	} 
	
//fim da classe;	
} 