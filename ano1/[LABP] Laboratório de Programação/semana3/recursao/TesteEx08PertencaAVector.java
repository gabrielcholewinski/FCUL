package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx08PertencaAVector {


    private boolean esperado;
    private boolean obtido;

    private int[] v;
    private int n;

    @Test
    public void umSoElementoENaoPertence () {
        v = new int[1];
        v[0] = -1;
        
        n = 0; 

        esperado = false;
        obtido = Recursive.pertence(v, n);

        assertTrue(esperado == obtido);

    }


    @Test
    public void umSoElementoEPertence () {
        v = new int[1];
        v[0] = 1;
        
        n = 1; 

        esperado = true;
        obtido = Recursive.pertence(v, n);

        assertTrue(esperado == obtido);


    }
    

    @Test
    public void maisQueUmElementoNaoOcorre () {
        v = new int[10];
        
        n = 6; 

        for (int i = 0; i < v.length; i++) {
            v[i] = i*5 % 4;
        }

        esperado = false;
        obtido = Recursive.pertence(v, n);

        assertTrue(esperado == obtido);

    }
    
    
    @Test
    public void maisQueUmElementoEOcorreDuasVezes () {
        v = new int[10];
        
        n = 1; 

        for (int i = 0; i < v.length - 1; i++) {
            v[i] = i*5 % 4;
        }

        
        esperado = true;
        obtido = Recursive.pertence(v, n);

        assertTrue(esperado == obtido);

    }
}
