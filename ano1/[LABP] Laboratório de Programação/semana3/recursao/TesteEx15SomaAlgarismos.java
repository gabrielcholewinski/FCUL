package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx15SomaAlgarismos {

    private int esperado;
    private int obtido;

    private int l;

    @Test
    public void umDigito () {
        l = 8;

        esperado = 	8;		
        obtido = Recursive.somaAlgarismos(l);

        assertEquals(esperado,obtido);

    }


    @Test
    public void doisAlgarismos () {
        l = 99;

        esperado =  18;      
        obtido = Recursive.somaAlgarismos(l);

        assertEquals(esperado,obtido);

    }

    @Test
    public void maisQueDois () {
        l = 1111111;

        esperado =  7;      
        obtido = Recursive.somaAlgarismos(l);

        assertEquals(esperado,obtido);

    }
}
