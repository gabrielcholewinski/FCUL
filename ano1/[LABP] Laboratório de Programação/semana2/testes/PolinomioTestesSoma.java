package testes;

import src.Fracao;
import src.Polinomio;

import static org.junit.Assert.*;

import org.junit.Test;

public class PolinomioTestesSoma {

    private Polinomio p1;
    private Polinomio p2;
    private Polinomio esperado;
    private Polinomio obtido;

    @Test(timeout = 1000)
    public void AmbosGrau0 () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(3,2);
        p1 = new Polinomio (coefs);
        p2 = new Polinomio (coefs);
        coefs[0] = new Fracao(3,1);
        esperado = new Polinomio (coefs);
        obtido = p1.soma(p2);
        assertTrue(esperado.ehIgual(obtido));
    }
    
    @Test(timeout = 1000)
    public void AmbosGrau1 () {
        Fracao[] coefs= new Fracao[2];
        coefs[0] = new Fracao(3,2);
        coefs[1] = new Fracao(3,2);
        p1 = new Polinomio (coefs);
        p2 = new Polinomio (coefs);
        coefs[0] = new Fracao(3,1);
        coefs[1] = new Fracao(3,1);
        esperado = new Polinomio (coefs);
        obtido = p1.soma(p2);
        assertTrue(esperado.ehIgual(obtido));
    }
    
    @Test(timeout = 1000)
    public void AmbosGrauMaior1 () {
        Fracao[] coefs= new Fracao[3];
        
        coefs[0] = new Fracao(3,2);
        coefs[1] = new Fracao(3,2);
        coefs[2] = new Fracao(1,1);
        p1 = new Polinomio (coefs);
        
        coefs[0] = new Fracao(1,2);
        coefs[1] = new Fracao(1,2);
        coefs[2] = new Fracao(1,1);
        
        p2 = new Polinomio (coefs);
        coefs[0] = new Fracao(2,1);
        coefs[1] = new Fracao(2,1);
        coefs[2] = new Fracao(2,1);
        esperado = new Polinomio (coefs);
        obtido = p1.soma(p2);
        assertTrue(esperado.ehIgual(obtido));
    }
    
    @Test(timeout = 1000)
    public void ambosGrauMaior1Diferentes () {
        Fracao[] coefs= new Fracao[3];
        
        coefs[0] = new Fracao(1,1);
        coefs[1] = new Fracao(1,1);
        coefs[2] = new Fracao(1,1);
        p1 = new Polinomio (coefs);
        
        coefs= new Fracao[4];
        coefs[0] = new Fracao(1,1);
        coefs[1] = new Fracao(1,1);
        coefs[2] = new Fracao(1,1);
        coefs[3] = new Fracao(1,1);
        
        p2 = new Polinomio (coefs);
        coefs[0] = new Fracao(2,1);
        coefs[1] = new Fracao(2,1);
        coefs[2] = new Fracao(2,1);
        coefs[3] = new Fracao(1,1);
        esperado = new Polinomio (coefs);
        obtido = p1.soma(p2);
        assertTrue(esperado.ehIgual(obtido));
    }
    
    
    public void outroAmbosGrauMaior1Diferentes () {
        Fracao[] coefs= new Fracao[3];
        
        coefs[0] = new Fracao(1,1);
        coefs[1] = new Fracao(1,1);
        coefs[2] = new Fracao(1,1);
        p1 = new Polinomio (coefs);
        
        coefs= new Fracao[4];
        coefs[0] = new Fracao(1,1);
        coefs[1] = new Fracao(1,1);
        coefs[2] = new Fracao(1,1);
        coefs[3] = new Fracao(1,1);
        
        p2 = new Polinomio (coefs);
        coefs[0] = new Fracao(2,1);
        coefs[1] = new Fracao(2,1);
        coefs[2] = new Fracao(2,1);
        coefs[3] = new Fracao(1,1);
        esperado = new Polinomio (coefs);
        obtido = p2.soma(p1);
        assertTrue(esperado.ehIgual(obtido));
    }
}
