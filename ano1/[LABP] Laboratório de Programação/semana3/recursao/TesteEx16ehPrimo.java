package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx16ehPrimo {

    private boolean esperado;
    private boolean obtido;

    private int n;

    @Test
    public void um () {
        n = 1;

        esperado = 	false;		
        obtido = Recursive.ehPrimo(n);

        assertEquals(esperado,obtido);

    }


    @Test
    public void dois () {
        n = 2;

        esperado =  true;      
        obtido = Recursive.ehPrimo(n);

        assertEquals(esperado,obtido);

    }

    @Test
    public void primo () {
        n = 31;

        esperado =  true;      
        obtido = Recursive.ehPrimo(n);

        assertEquals(esperado,obtido);

    }
    
    @Test
    public void naoPrimo () {
        n = 35;

        esperado =  false;      
        obtido = Recursive.ehPrimo(n);

        assertEquals(esperado,obtido);

    }
}
