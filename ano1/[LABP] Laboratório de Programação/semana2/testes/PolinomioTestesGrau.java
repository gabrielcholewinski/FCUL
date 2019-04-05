package testes;

import src.Fracao;
import src.Polinomio;

import static org.junit.Assert.*;

import org.junit.Test;

public class PolinomioTestesGrau {

    private Polinomio p;
    private int esperado;
    private int obtido;

    @Test(timeout = 1000)
    public void grau0 () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(3,2);
        p = new Polinomio (coefs);
        esperado = 0;
        obtido = p.grau();
        assertEquals(esperado, obtido);
    }
    
    @Test(timeout = 1000)
    public void grau0NaoInteiro () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(1,2);
        p = new Polinomio (coefs);
        esperado = 0;
        obtido = p.grau();
        assertEquals(esperado, obtido);
    }
    
    @Test(timeout = 1000)
    public void grau1Inteiro () {
        Fracao[] coefs= new Fracao[2];
        coefs[0] = new Fracao(2,1);
        coefs[1] = new Fracao(2,2);
        p = new Polinomio (coefs);
        esperado = 1;
        obtido = p.grau();
        assertEquals(esperado, obtido);
    }
    
    @Test(timeout = 1000)
    public void grau1NaoInteiro () {
        Fracao[] coefs= new Fracao[2];
        coefs[0] = new Fracao(2,3);
        coefs[1] = new Fracao(2,5);
        p = new Polinomio (coefs);
        esperado = 1;
        obtido = p.grau();
        assertEquals(esperado, obtido);
    }
    
    
    @Test(timeout = 1000)
    public void grauMaiorQue1Inteiro () {
        Fracao[] coefs= new Fracao[4];
        coefs[0] = new Fracao(1,1);
        coefs[1] = coefs[0];
        coefs[2] = coefs[0];
        coefs[3] = coefs[0];
        p = new Polinomio (coefs);
        esperado = 3;
        obtido = p.grau();
        assertEquals(esperado, obtido);
    }
    
    
    @Test(timeout = 1000)
    public void grauMaiorQue1NaoInteiro () {
        Fracao[] coefs= new Fracao[4];
        coefs[0] = new Fracao(2,3);
        coefs[1] = coefs[0];
        coefs[2] = coefs[0];
        coefs[3] = coefs[0];
        p = new Polinomio (coefs);
        esperado = 3;
        obtido = p.grau();
        assertEquals(esperado, obtido);
    }
}
