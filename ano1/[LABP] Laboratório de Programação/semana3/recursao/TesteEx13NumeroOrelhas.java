package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx13NumeroOrelhas {

    private int esperado;
    private int obtido;

    private int l;

    @Test
    public void um () {
        l = 1;

        esperado = 	3;		
        obtido = Recursive.numeroOrelhas(l);

        assertEquals(esperado,obtido);

    }


    @Test
    public void dois () {
        l = 2;

        esperado =  5;      
        obtido = Recursive.numeroOrelhas(l);

        assertEquals(esperado,obtido);

    }

    @Test
    public void muitos () {
        l = 5;

        esperado =  13;      
        obtido = Recursive.numeroOrelhas(l);

        assertEquals(esperado,obtido);

    }
}
