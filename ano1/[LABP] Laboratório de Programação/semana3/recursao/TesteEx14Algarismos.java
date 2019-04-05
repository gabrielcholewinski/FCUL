package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx14Algarismos {

    private int esperado;
    private int obtido;

    private int l;

    @Test
    public void umDigito () {
        l = 8;

        esperado = 	1;		
        obtido = Recursive.algarismos(l);

        assertEquals(esperado,obtido);

    }


    @Test
    public void doisAlgarismos () {
        l = 99;

        esperado =  2;      
        obtido = Recursive.algarismos(l);

        assertEquals(esperado,obtido);

    }

    @Test
    public void maisQueDois () {
        l = 1234567;

        esperado =  7;      
        obtido = Recursive.algarismos(l);

        assertEquals(esperado,obtido);

    }
}
