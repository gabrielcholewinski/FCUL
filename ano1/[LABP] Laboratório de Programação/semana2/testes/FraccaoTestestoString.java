package testes;

import src.Fracao;

import static org.junit.Assert.*;

import org.junit.Test;

public class FraccaoTestestoString {
    
    private Fracao f1;
    private String esperada;
    private String obtida;


    @Test(timeout = 1000)
    public void inteiraPositiva () {
        f1 = new Fracao(10,5);
        esperada = "2";
        obtida = f1.toString();
        assertEquals(esperada, obtida);
        //caso contrario precisa da implementacao do equals para Object!
        //ha outra forma????
    }

    @Test(timeout = 1000)
    public void aZero () {
        f1 = new Fracao(0,1);
        esperada = "0";
        obtida = f1.toString();
        assertEquals(esperada, obtida);
        //caso contrario precisa da implementacao do equals para Object!
        //ha outra forma????
    }
    
    @Test(timeout = 1000)
    public void inteiraNegativa () {
        f1 = new Fracao(10,-2);
        esperada = "-5";
        obtida = f1.toString();
        assertEquals(esperada, obtida);
        //caso contrario precisa da implementacao do equals para Object!
        //ha outra forma????
    }
    
    @Test(timeout = 1000)
    public void naoInteiraPositiva () {
        f1 = new Fracao(5,10);
        esperada = "1/2";
        obtida = f1.toString();
        assertEquals(esperada, obtida);
        //caso contrario precisa da implementacao do equals para Object!
        //ha outra forma????
    }


    @Test(timeout = 1000)
    public void naoInteiraNegativa () {
        f1 = new Fracao(1,-2);
        esperada = "-1/2";
        obtida = f1.toString();
        assertEquals(esperada, obtida);
        //caso contrario precisa da implementacao do equals para Object!
        //ha outra forma????
    }
}
