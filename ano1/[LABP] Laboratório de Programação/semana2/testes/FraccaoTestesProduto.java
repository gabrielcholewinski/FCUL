package testes;

import src.Fracao;

import static org.junit.Assert.*;

import org.junit.Test;

public class FraccaoTestesProduto {
    
    private Fracao f1;
    private Fracao f2;
    private Fracao esperada;
    private Fracao obtida;


    @Test(timeout = 1000)
    public void inversas () {
        f1 = new Fracao(10,5);
        f2 = new Fracao(1,2);
        esperada = new Fracao(1,1);
        obtida = f1.produto(f2);
        assertTrue(esperada.equivalente(obtida));
        //caso contrario precisa da implementacao do equals para Object!
        //ha outra forma????
    }
    
    

    @Test(timeout = 1000)
    public void umaAUm () {
        f1 = new Fracao(1,1);
        f2 = new Fracao(1,2);
        esperada = f2;
        obtida = f1.produto(f2);
        assertTrue(esperada.equivalente(obtida));
    }
    
    

    @Test(timeout = 1000)
    public void naoInteirasPositivas () {
        f1 = new Fracao(5,10);
        f2 = new Fracao(1,2);
        esperada = new Fracao(1,4);
        obtida = f1.produto(f2);
        assertTrue(esperada.equivalente(obtida));
    }
    

    @Test(timeout = 1000)
    public void naoInteirasNegativas () {
        f1 = new Fracao(-5,10);
        f2 = new Fracao(1,-2);
        esperada = new Fracao(1,4);
        obtida = f1.produto(f2);
        assertTrue(esperada.equivalente(obtida));
    }

    @Test(timeout = 1000)
    public void naoInteirasUmaPOsitivaOutraNegativa () {
        f1 = new Fracao(-5,10);
        f2 = new Fracao(1,2);
        esperada = new Fracao(-1,4);
        obtida = f1.produto(f2);
        assertTrue(esperada.equivalente(obtida));
    }
    
}
