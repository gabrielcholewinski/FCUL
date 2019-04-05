package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx18SomaQuadrados {

    private int esperado;
    private int obtido;

    private int n;

    @Test
    public void um () {
        n = 1;

        esperado = 	1;		
        obtido = Recursive.somaQuadrados(n);

        assertEquals(esperado,obtido);

    }
    
    
    @Test
    public void dois () {
        n = 2;

        esperado =  5;      
        obtido = Recursive.somaQuadrados(n);

        assertEquals(esperado,obtido);

    }
    
    @Test
    public void nGrande () {
        n = 10;

        esperado =  n * (n+1) * (2*n + 1) / 6;      
        obtido = Recursive.somaQuadrados(n);

        assertEquals(esperado,obtido);

    }

}
