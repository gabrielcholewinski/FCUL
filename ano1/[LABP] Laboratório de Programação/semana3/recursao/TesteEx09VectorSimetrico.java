package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx09VectorSimetrico {
	

    private boolean esperado;
    private boolean obtido;

    private int[] v;

    @Test
    public void umSoElemento () {
        v = new int[1];

        v[0] = 1;
        
        esperado = true;
        obtido = Recursive.ehSimetrico(v);

        assertTrue(esperado == obtido);

    }


    @Test
    public void maisQueUmEEhSimetrico () {
        v = new int[10];
        

        for (int i = 0; i < v.length / 2 ; i++) {
            v[i] = i*5 % 4;
            v[v.length-1 -i] = i*5 % 4;
        }

        esperado = true;
        obtido = Recursive.ehSimetrico(v);

        assertTrue(esperado == obtido);

    }
    
    
    @Test
    public void maisQueUmENaoEhSimetrico () {
        v = new int[10];
        

        for (int i = 0; i < v.length; i++) {
            v[i] = 1;
        }
        
        v[4] = 2;

        esperado = false;
        obtido = Recursive.ehSimetrico(v);

        assertTrue(esperado == obtido);

    }

	   
}
