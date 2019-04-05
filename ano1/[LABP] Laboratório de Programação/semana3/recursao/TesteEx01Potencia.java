package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx01Potencia {

    public static final double DELTA = 0.0001;

    private double esperado;
    private double obtido;

    private double base;
    private int expoente;

    @Test
    public void base1 () {		
        base = 1;
        expoente = 10;

        esperado = 	1.0;
        obtido = Recursive.potencia(base, expoente);

        assertTrue(Math.abs(esperado - obtido) < DELTA);

    }

    @Test
    public void baseMaiorQue1Expoente1 () {
        base = 10.0;
        expoente = 1; 

        esperado = 	10.0;
        obtido = Recursive.potencia(base, expoente);

        assertTrue(Math.abs(esperado - obtido) < DELTA);
    }

    @Test
    public void baseMaiorQue1ExpoenteMaiorQue1  () {		
        base = 2.0;
        expoente = 5; 


        esperado = 	32.0;
        obtido = Recursive.potencia(base, expoente);

        assertTrue(Math.abs(esperado - obtido) < DELTA);
    }
}
