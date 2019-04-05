package testes;

import src.Fracao;
import src.Polinomio;

import static org.junit.Assert.*;

import org.junit.Test;

public class PolinomioTestesEscalar {

    private Polinomio p1;
    private Polinomio esperado;
    private Polinomio obtido;

    @Test(timeout = 1000)
    public void grau0EEscalar0 () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(3,2);
        p1 = new Polinomio (coefs);
        coefs[0] = new Fracao(0,1);
        esperado = new Polinomio (coefs);
        obtido = p1.escalar(new Fracao(0,1));
        assertTrue(esperado.ehIgual(obtido));
    }

    @Test(timeout = 1000)
    public void grau0EEscalar1 () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(3,2);
        p1 = new Polinomio (coefs);
        esperado = new Polinomio (coefs);
        obtido = p1.escalar(new Fracao(1,1));
        assertTrue(esperado.ehIgual(obtido));
    }    

    
    @Test(timeout = 1000)
    public void grau0EEscalarMaior1 () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(3,2);
        p1 = new Polinomio (coefs);
        coefs[0] = new Fracao(3,1);
        esperado = new Polinomio (coefs);
        obtido = p1.escalar(new Fracao(2,1));
        assertTrue(esperado.ehIgual(obtido));
    }   
    

    @Test(timeout = 1000)
    public void grau0EEscalarMenor1 () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(3,2);
        p1 = new Polinomio (coefs);
        coefs[0] = new Fracao(1,2);
        esperado = new Polinomio (coefs);
        obtido = p1.escalar(new Fracao(1,3));
        assertTrue(esperado.ehIgual(obtido));
    }   
    
    
    public void grau1EEscalar0 () {
        Fracao[] coefs= new Fracao[2];
        coefs[0] = new Fracao(3,2);
        coefs[1] = new Fracao(1,2);
        p1 = new Polinomio (coefs);
        coefs= new Fracao[1];
        coefs[0] = new Fracao(0,1);
        esperado = new Polinomio (coefs);
        obtido = p1.escalar(new Fracao(0,1));
        assertTrue(esperado.ehIgual(obtido));
    }

    @Test(timeout = 1000)
    public void grau1EEscalar1 () {
        Fracao[] coefs= new Fracao[2];
        coefs[0] = new Fracao(3,2);
        coefs[1] = new Fracao(1,2);
        p1 = new Polinomio (coefs);
        
        esperado = new Polinomio (coefs);
        obtido = p1.escalar(new Fracao(1,1));
        assertTrue(esperado.ehIgual(obtido));
    }    

    
    @Test(timeout = 1000)
    public void grau1EEscalarMaior1 () {
        Fracao[] coefs= new Fracao[2];
        coefs[0] = new Fracao(3,2);
        coefs[1] = new Fracao(1,2);
        p1 = new Polinomio (coefs);
        
        coefs[0] = new Fracao(3,1);
        coefs[1] = new Fracao(2,2);
        esperado = new Polinomio (coefs);
        obtido = p1.escalar(new Fracao(2,1));
        assertTrue(esperado.ehIgual(obtido));
    }   
    
    @Test(timeout = 1000)
    public void grau1EEscalarMenor1 () {
        Fracao[] coefs= new Fracao[2];
        coefs[0] = new Fracao(3,2);
        coefs[1] = new Fracao(1,2);
        p1 = new Polinomio (coefs);
        
        coefs[0] = new Fracao(3,4);
        coefs[1] = new Fracao(1,4);
        esperado = new Polinomio (coefs);
        obtido = p1.escalar(new Fracao(1,2));
        assertTrue(esperado.ehIgual(obtido));
    }    
    
    
    public void grauMaior1EEscalar0 () {
        Fracao[] coefs= new Fracao[3];
        coefs[0] = new Fracao(3,2);
        coefs[1] = new Fracao(1,2);
        coefs[2] = new Fracao(1,2);
        p1 = new Polinomio (coefs);
        coefs= new Fracao[1];
        coefs[0] = new Fracao(0,1);
        esperado = new Polinomio (coefs);
        obtido = p1.escalar(new Fracao(0,1));
        assertTrue(esperado.ehIgual(obtido));
    }
    
    @Test(timeout = 1000)
    public void grauMaior1EEscalar1 () {
        Fracao[] coefs= new Fracao[3];
        coefs[0] = new Fracao(3,2);
        coefs[1] = new Fracao(1,2);
        coefs[2] = new Fracao(1,2);
        p1 = new Polinomio (coefs);
        
        esperado = new Polinomio (coefs);
        obtido = p1.escalar(new Fracao(1,1));
        assertTrue(esperado.ehIgual(obtido));
    }    

    
    @Test(timeout = 1000)
    public void grauMaior1EEscalarMaior1 () {
        Fracao[] coefs= new Fracao[3];
        coefs[0] = new Fracao(3,2);
        coefs[1] = new Fracao(1,2);
        coefs[2] = new Fracao(1,2);
        p1 = new Polinomio (coefs);
        
        coefs[0] = new Fracao(3,1);
        coefs[1] = new Fracao(2,2);
        coefs[2] = new Fracao(2,2);
        esperado = new Polinomio (coefs);
        obtido = p1.escalar(new Fracao(2,1));
        assertTrue(esperado.ehIgual(obtido));
    }   
    
    @Test(timeout = 1000)
    public void grauMaior1EEscalarMenor1 () {
        Fracao[] coefs= new Fracao[3];
        coefs[0] = new Fracao(3,2);
        coefs[1] = new Fracao(1,2);
        coefs[2] = new Fracao(1,2);
        p1 = new Polinomio (coefs);
        
        coefs[0] = new Fracao(3,4);
        coefs[1] = new Fracao(1,4);
        coefs[2] = new Fracao(1,4);
        esperado = new Polinomio (coefs);
        obtido = p1.escalar(new Fracao(1,2));
        assertTrue(esperado.ehIgual(obtido));
    } 
    
}
