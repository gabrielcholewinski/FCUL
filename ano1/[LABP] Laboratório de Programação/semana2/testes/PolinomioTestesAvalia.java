package testes;

import src.Fracao;
import src.Polinomio;

import static org.junit.Assert.*;

import org.junit.Test;

public class PolinomioTestesAvalia {

    private Polinomio p1;
    private Fracao x;
    private Fracao esperado;

    @Test(timeout = 1000)
    public void grau0 () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(3,2);
        p1 = new Polinomio (coefs);
        x = new Fracao(1,1); 
        esperado = coefs[0];
        assertTrue(p1.avalia(x).equivalente(esperado));
    }

    @Test(timeout = 1000)
    public void grau1 () {
        Fracao[] coefs= new Fracao[2];
        coefs[0] = new Fracao(1,1);
        coefs[1] = new Fracao(1,1);
        p1 = new Polinomio (coefs);
        x = new Fracao(1,1); 
        esperado = new Fracao(2,1);
        assertTrue(p1.avalia(x).equivalente(esperado));
    }


    @Test(timeout = 1000)
    public void grauMaior1 () {
        Fracao[] coefs= new Fracao[3];
        coefs[0] = new Fracao(1,1);
        coefs[1] = new Fracao(1,1);
        coefs[2] = new Fracao(1,1);
        p1 = new Polinomio (coefs);
        x = new Fracao(1,2); 
        esperado = new Fracao(7,4);
        assertTrue(p1.avalia(x).equivalente(esperado));
    }

}
