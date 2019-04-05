package testes;

import src.Fracao;
import src.Polinomio;

import static org.junit.Assert.*;

import org.junit.Test;

public class PolinomioTestesToString {

    private Polinomio p1;
    private String esperado;
    private String obtido;

    @Test(timeout = 10000)
    public void poliZero () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(0,1);
        p1 = new Polinomio (coefs);
        esperado = "0";
        obtido = p1.toString();
        assertEquals(esperado,obtido);
    }

    @Test(timeout = 10000)
    public void poliConstanteInteiraNegativo () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(-2,1);
        p1 = new Polinomio (coefs);
        esperado = "-2";
        obtido = p1.toString();
        assertEquals(esperado,obtido);
    }

    @Test(timeout = 10000)
    public void poliConstanteInteiraPositivo () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(2,1);
        p1 = new Polinomio (coefs);
        esperado = "2";
        obtido = p1.toString();
        assertEquals(esperado,obtido);
    }


    @Test(timeout = 10000)
    public void poliConstanteNaoInteiraNegativo () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(-2, 3);
        p1 = new Polinomio (coefs);
        esperado = "-2/3";
        obtido = p1.toString();
        assertEquals(esperado,obtido);
    }

    @Test(timeout = 10000)
    public void poliConstanteNaoInteiraPositivo () {
        Fracao[] coefs= new Fracao[1];
        coefs[0] = new Fracao(2,3);
        p1 = new Polinomio (coefs);
        esperado = "2/3";
        obtido = p1.toString();
        assertEquals(esperado,obtido);
    }


    @Test(timeout = 10000)
    public void poliGrau1PositivoNegativo () {
        Fracao[] coefs= new Fracao[2];
        coefs[0] = new Fracao(2,3);
        coefs[1] = new Fracao(-2,3);
        p1 = new Polinomio (coefs);
        esperado = "-2/3 x + 2/3";
        obtido = p1.toString();
        assertEquals(esperado,obtido);
    }

    @Test(timeout = 10000)
    public void poliGrau1NegativoPositivo () {
        Fracao[] coefs= new Fracao[2];
        coefs[0] = new Fracao(-2,3);
        coefs[1] = new Fracao(2,3);
        p1 = new Polinomio (coefs);
        esperado = "2/3 x -2/3";
        obtido = p1.toString();
        assertEquals(esperado,obtido);
    }

    @Test(timeout = 10000)
    public void poliSoComUns () {
        Fracao[] coefs= new Fracao[3];
        coefs[0] = new Fracao(1,1);
        coefs[1] = new Fracao(1,1);
        coefs[2] = new Fracao(1,1);
        p1 = new Polinomio (coefs);
        esperado = "1 x^2 + 1 x + 1";
        obtido = p1.toString();
        assertEquals(esperado,obtido);
    }

    @Test(timeout = 10000)
    public void poliComBucaros () {
        Fracao[] coefs= new Fracao[3];
        coefs[0] = new Fracao(1,1);
        coefs[1] = new Fracao(0,1);
        coefs[2] = new Fracao(1,1);
        p1 = new Polinomio (coefs);
        esperado = "1 x^2 + 1";
        obtido = p1.toString();
        assertEquals(esperado,obtido);
    }


    @Test(timeout = 10000)
    public void poliMix () {
        Fracao[] coefs= new Fracao[5];
        coefs[0] = new Fracao(1,1);
        coefs[1] = new Fracao(0,1);
        coefs[2] = new Fracao(-1,1);
        coefs[3] = new Fracao(-1,2);
        coefs[4] = new Fracao(3,1);
        p1 = new Polinomio (coefs);
        esperado = "3 x^4 -1/2 x^3 -1 x^2 + 1";
        obtido = p1.toString();
        assertEquals(esperado,obtido);
    }
}
