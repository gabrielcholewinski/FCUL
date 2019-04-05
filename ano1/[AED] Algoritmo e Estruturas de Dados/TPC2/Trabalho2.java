 

/**
 * 
 * @author Ruhan Victor - 51779
 */
public class Trabalho2 {
	
    // directions:                 S, SE,  E, NE,  N, NW,  W, SW
    private static int[] xDirs = { 0,  1,  1,  1,  0, -1, -1, -1};
    private static int[] yDirs = {-1, -1,  0,  1,  1,  1,  0, -1};
    
    // useful for method sierpinski()
    private static final double SIN30 = Math.sin(Math.PI/6);
    private static final double COS30 = Math.cos(Math.PI/6);
    
    public static void sierpinskiSquare(double x0, double y0, double size, int iter) {
    	if (iter==0)
    		return;
    	
    	StdDraw.filledSquare(x0, y0, size);
    	
    	for(int i=0; i<8; i++)
    		// recursively draw smaller squares (a third of the size) in all 8 directions
    		sierpinskiSquare(x0+xDirs[i]*(2*size), y0+yDirs[i]*(2*size), size/3, iter-1);
    }
    /**
      * Cria uma figura com quadrados em recursao
      * @param x0 - Coordenada das abcissas
      * @param y0 - Coordenada das Ordenadas
      * @param size - tamanho do quadrado
      * @param iter - numero maximo de iteracoes
      * @requires size > 0
      **/
    public static void tSquare(double x0, double y0, double size, int iter) {
    //  condicao de paragem da recursividade													
        if(iter == 0) 
        		return;
    //	desenha o primeiro quadrado
    	StdDraw.filledSquare(x0, y0, size);
    //	corre um ciclo para fazer recursividade  	
    	for(int i=1; i<8; i+=2) {
    /** 	
      *		cria o proximo quadrado, dando x0 e y0 as direcoes  SE, NE, NW, SW 
      *		contidas no vetor xDirs e yDirs, multiplicados por size em recursao  
      **/		
    		tSquare(x0+xDirs[i]*(size), y0+yDirs[i]*(size), size/2.0, iter-1);
    	}
    	
    }
    
    /**
      * Cria uma figura com triangulo em recursao
      * @param x0
      * @param y0
      * @param size
      * @param iter
      * @requires size > 0
      **/
    public static void sierpinski(double x0, double y0, double size, int iter) {
    //		condicao de paragem da recursividade
    		if(iter == 0)
    			return;
    /**		
      * 	altura do triangulo = divide o triangulo equilatero na metade 
      * 	e calcula sua altura utilizando o teorema de pitahgoras
      */
    		double h = Math.sqrt(Math.pow(size,2) - Math.pow(size/2.0, 2));
    //		cria dois vetores com as coordenadas do x0 e y0 (3 pontos)
    		double[] x = new double[] {x0, (x0-size/2.0), (x0+size/2.0)};
    		double[] y = new double[] {y0, y0 + h, y0 +  h};
    		
    //		desenha o primeiro triangulo com as coordendas dos vetores x e y ah cima
    		StdDraw.filledPolygon(x, y);
    //		corre um ciclo para fazer recursividade		
    		for(int i=1; i<8; i+=3) { 
    /** 	o i só recebe os valores: i={1,4,7}
      *     o i serah usado para buscar as direcoes que sao contidas nos vetores 
      *     xDirs e yDirs, para o xDirs, se receber os valores de i, irao retornar
      *     xDirs[i]={1, 0 , -1}, enquanto ao yDirs[i]={-1, 1, -1}
      * 	==================================== || ====================================
      *     se o i nao for par a direcao serah SWx(1) ou SEx(-1), portanto para construir o 
      *     proximo triangulo sera necessario somar ao x0 metade do tamanho de size*
      *     a direcao, e o y deve se manter.
      */
                if (i % 2 != 0) {
                    sierpinski(x0 + xDirs[i] * size /2.0, y0 , size/2.0, iter-1); 
                } 
     /**
      * 	se o i for impar, o Nx(0) e Ny(1), portanto para construir o proximo triangulo
      * 	sera necessario somar a altura ao y, enquanto as outras operacoes, como +xDirs*size/2.0
      * 	so foram colocadas para manter o padrao, pois ira dar zero, nao necessitando assim, 
      * 	colocar a direcao, quanto ao y[Dirs[i] ira dar 1, significa que tambem nao eh necessario
      */
                else {
                    /*
                     * as coordenadas x mantem-se
                     * as coordenadas y serao as coordenadas y dos vertices superiores
                     */
                    sierpinski(x0+xDirs[i]*size/2.0, y0 +yDirs[i]*h, size/2.0, iter-1);
                }
            }
    }
    
    //////////////////////////////////////////////
 
    public static void main(String[] args) {
    	
        double windowSize = 120;
    	
        StdDraw.setXscale(0, windowSize); 
        StdDraw.setYscale(0, windowSize);
        StdDraw.clear(StdDraw.WHITE);  
        StdDraw.setPenColor(StdDraw.BLACK);
        
        // select one fractal to draw (last argument is iter)
        // sierpinskiSquare(windowSize/2, windowSize/2, windowSize/6, 4); 
         tSquare(windowSize/2, windowSize/2, windowSize/6, 4);
        //sierpinski(windowSize/2, windowSize/2, windowSize/6, 4);
       
    }
    
}