package testes;

import src.Fracao;
import src.Polinomio;

import static org.junit.Assert.*;

import org.junit.Test;

public class PolinomioTestesEhConstante {

    private Polinomio p;

    @Test(timeout = 1000)
    public void grau0NaoEhZero () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(3,2);
        p = new Polinomio (coefs);
        assertTrue(p.ehConstante());
    }
    
    @Test(timeout = 1000)
    public void grau0EhZero () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(0,1);
        p = new Polinomio (coefs);
        assertTrue(p.ehConstante());
    }
    
    @Test(timeout = 1000)
    public void grauMaior0 () {
        Fracao[] coefs= new Fracao[4];
        coefs[0] = new Fracao(1,1);
        coefs[1] = coefs[0];
        coefs[2] = coefs[0];
        coefs[3] = coefs[0];
        p = new Polinomio (coefs);
        assertTrue(!p.ehConstante());
    }
    
}
