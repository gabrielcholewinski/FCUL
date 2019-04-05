package testes;

import src.Fracao;
import src.Polinomio;

import static org.junit.Assert.*;

import org.junit.Test;

public class PolinomioTestesSimetrico {

    private Polinomio p1;
    private Polinomio esperado;
    private Polinomio obtido;

    @Test(timeout = 1000)
    public void grau0 () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(3,2);
        p1 = new Polinomio (coefs);
        coefs[0] = new Fracao(-3,2);
        esperado = new Polinomio (coefs);
        obtido = p1.simetrico();
        assertTrue(esperado.ehIgual(obtido));
    }
    
    @Test(timeout = 1000)
    public void grau1 () {
        Fracao[] coefs= new Fracao[2];
        coefs[0] = new Fracao(3,2);
        coefs[1] = new Fracao(3,2);
        p1 = new Polinomio (coefs);
        coefs[0] = new Fracao(-3,2);
        coefs[1] = new Fracao(-3,2);
        esperado = new Polinomio (coefs);
        obtido = p1.simetrico();
        assertTrue(esperado.ehIgual(obtido));
    }
    
    @Test(timeout = 1000)
    public void grauMaior1 () {
        Fracao[] coefs= new Fracao[3];
        
        coefs[0] = new Fracao(3,2);
        coefs[1] = new Fracao(0,1);
        coefs[2] = new Fracao(1,1);
        p1 = new Polinomio (coefs);
        
        coefs[0] = new Fracao(-3,2);
        coefs[1] = new Fracao(0,2);
        coefs[2] = new Fracao(-1,1);
        
        esperado = new Polinomio (coefs);
        obtido = p1.simetrico();
        assertTrue(esperado.ehIgual(obtido));
    }
    
}
