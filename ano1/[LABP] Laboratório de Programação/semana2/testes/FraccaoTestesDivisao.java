package testes;

import src.Fracao;

import static org.junit.Assert.*;

import org.junit.Test;

public class FraccaoTestesDivisao {
    
    private Fracao f1;
    private Fracao f2;
    private Fracao esperada;
    private Fracao obtida;


    @Test(timeout = 1000)
    public void teste1 () {
        f1 = new Fracao(10,5);
        f2 = new Fracao(1,2);
        esperada = new Fracao(4,1);
        obtida = f1.divisao(f2);
        assertTrue(esperada.equivalente(obtida));
    }
    
    

    @Test(timeout = 1000)
    public void teste2 () {
        f1 = new Fracao(1,1);
        f2 = new Fracao(1,2);
        esperada = new Fracao(2,1);
        obtida = f1.divisao(f2);
        assertTrue(esperada.equivalente(obtida));
    }
    
    

    @Test(timeout = 1000)
    public void teste3 () {
        f1 = new Fracao(5,10);
        f2 = new Fracao(1,2);
        esperada = new Fracao(1,1);
        obtida = f1.divisao(f2);
        assertTrue(esperada.equivalente(obtida));
    }
    

    @Test(timeout = 1000)
    public void teste4 () {
        f1 = new Fracao(-5,10);
        f2 = new Fracao(1,-2);
        esperada = new Fracao(1,1);
        obtida = f1.divisao(f2);
        assertTrue(esperada.equivalente(obtida));
    }

    @Test(timeout = 1000)
    public void teste5 () {
        f1 = new Fracao(-5,4);
        f2 = new Fracao(1,2);
        esperada = new Fracao(-5,2);
        obtida = f1.divisao(f2);
        assertTrue(esperada.equivalente(obtida));
    }
    
}
