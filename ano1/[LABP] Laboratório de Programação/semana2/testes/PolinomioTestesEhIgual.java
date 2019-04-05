package testes;

import src.Fracao;
import src.Polinomio;

import static org.junit.Assert.*;

import org.junit.Test;

public class PolinomioTestesEhIgual {

    private Polinomio p1;
    private Polinomio p2;

    @Test(timeout = 1000)
    public void grau0Igual () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(3,2);
        p1 = new Polinomio (coefs);
        p2 = new Polinomio (coefs);
        assertTrue(p1.ehIgual(p2));
    }
    
    @Test(timeout = 1000)
    public void grau0Equivalente () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(3,2);
        p1 = new Polinomio (coefs);
        coefs[0] = new Fracao(6,4);
        p2 = new Polinomio (coefs);
        assertTrue(p1.ehIgual(p2));
    }
  
    @Test(timeout = 1000)
    public void grau0NaoIgual () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(3,2);
        p1 = new Polinomio (coefs);
        coefs[0] = new Fracao(6,5);
        p2 = new Polinomio (coefs);
        assertTrue(!p1.ehIgual(p2));
    }
    
    
    @Test(timeout = 1000)
    public void grau1Igual () {
        Fracao[] coefs= new Fracao[2];
        coefs[0] = new Fracao(3,2);
        coefs[1] = new Fracao(1,1);
        p1 = new Polinomio (coefs);
        p2 = new Polinomio (coefs);
        assertTrue(p1.ehIgual(p2));
    }
    
    @Test(timeout = 1000)
    public void grauMaior1Iguais () {
        Fracao[] coefs= new Fracao[2];
        coefs[0] = new Fracao(3,2);
        coefs[1] = new Fracao(1,1);
        p1 = new Polinomio (coefs);
        coefs[0] = new Fracao(6,4);
        coefs[1] = new Fracao(2,2);
        p2 = new Polinomio (coefs);
        assertTrue(p1.ehIgual(p2));
    }
  
    @Test(timeout = 1000)
    public void grau1NaoIguais () {
        Fracao[] coefs= new Fracao[2];
        coefs[0] = new Fracao(3,2);
        coefs[1] = new Fracao(3,2);
        p1 = new Polinomio (coefs);
        coefs[0] = new Fracao(6,5);
        coefs[1] = new Fracao(3,2);
        p2 = new Polinomio (coefs);
        assertTrue(!p1.ehIgual(p2));
    }
}
