package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx11Binario {

    private String esperado;
    private String obtido;

    private int n;

    @Test
    public void Zero () {		
        n = 0;

        esperado = 	"0";
        obtido = Recursive.intToBin(n);

        assertEquals(esperado , obtido);

    }
    
    @Test
    public void um () {       
        n = 1;

        esperado =  "1";
        obtido = Recursive.intToBin(n);

        assertEquals(esperado , obtido);

    }

    @Test
    public void impar () {       
        n = 7;

        esperado =  "111";
        obtido = Recursive.intToBin(n);

        assertEquals(esperado , obtido);

    }
    
    @Test
    public void par () {       
        n = 10;

        esperado =  "1010";
        obtido = Recursive.intToBin(n);

        assertEquals(esperado , obtido);

    }
    
    @Test
    public void potenciaDeDois () {       
        n = 16;

        esperado =  "10000";
        obtido = Recursive.intToBin(n);

        assertEquals(esperado , obtido);

    }

}

