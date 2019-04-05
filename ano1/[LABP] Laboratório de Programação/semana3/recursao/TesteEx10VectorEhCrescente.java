package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx10VectorEhCrescente {
	

    private boolean esperado;
    private boolean obtido;

    private double[] v;

    @Test
    public void umSoElemento () {
        v = new double[1];

        v[0] = 1;
        
        esperado = true;
        obtido = Recursive.ehCrescente(v);

        assertTrue(esperado == obtido);

    }


    @Test
    public void maisQueUmEEhCrescente () {
        v = new double[10];
        

        for (int i = 0; i < v.length ; i++) {
            v[i] = i;
        }

        esperado = true;
        obtido = Recursive.ehCrescente(v);

        assertTrue(esperado == obtido);

    }
    
    @Test
    public void maisQueUmTodosIguais () {
        v = new double[10];
        

        for (int i = 0; i < v.length ; i++) {
            v[i] = 1;
        }

        esperado = true;
        obtido = Recursive.ehCrescente(v);

        assertTrue(esperado == obtido);

    }
    
    @Test
    public void maisQueUmENaoEhCrescente () {
        v = new double[10];
        

        for (int i = 0; i < v.length; i++) {
            v[i] = v.length-i;
        System.out.print(v[i]+" ");
        }
        
        
        esperado = false;
        obtido = Recursive.ehCrescente(v);

        assertTrue(esperado == obtido);

    }

	   
}
