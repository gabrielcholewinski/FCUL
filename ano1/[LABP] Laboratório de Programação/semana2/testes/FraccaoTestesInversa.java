package testes;

import src.Fracao;

import static org.junit.Assert.*;

import org.junit.Test;

public class FraccaoTestesInversa {
    
    private Fracao f;
    private Fracao esperada;
    private Fracao obtida;


    @Test(timeout = 1000)
    public void inteiraPositiva () {
        f = new Fracao(10,5);
        esperada = new Fracao(5,10);
        obtida = f.inversa();
        assertTrue(esperada.equivalente(obtida));
        //caso contrario precisa da implementacao do equals para Object!
        //ha outra forma????
    }
    
    

    @Test(timeout = 1000)
    public void aUm () {
        f = new Fracao(1,1);
        esperada = new Fracao(1,1);
        obtida = f.inversa();
        assertTrue(esperada.equivalente(obtida));
    }
    
    
    
    @Test(timeout = 1000)
    public void inteiraNegativa () {
        f = new Fracao(-10,5);
        esperada = new Fracao(-1,2);
        obtida = f.inversa();
        assertTrue(esperada.equivalente(obtida));
    }
    
    

    @Test(timeout = 1000)
    public void naoInteiraPositiva () {
        f = new Fracao(5,10);
        esperada = new Fracao(2,1);
        obtida = f.inversa();
        assertTrue(esperada.equivalente(obtida));
    }
    
    @Test(timeout = 1000)
    public void naoInteiraNegativa () {
        f = new Fracao(-5,10);
        esperada = new Fracao(2,-1);
        obtida = f.inversa();
        assertTrue(esperada.equivalente(obtida));
    }
}
