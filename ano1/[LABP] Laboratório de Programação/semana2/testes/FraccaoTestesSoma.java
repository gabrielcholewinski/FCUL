package testes;

import src.Fracao;

import static org.junit.Assert.*;

import org.junit.Test;

public class FraccaoTestesSoma {
    
    private Fracao f;
    private Fracao g;
    private Fracao esperada;
    private Fracao obtida;


    @Test(timeout = 1000)
    public void duasPositivas () {
        f = new Fracao(1,5);
        g = new Fracao(2,5);
        esperada = new Fracao(3,5);
        obtida = f.soma(g);
        assertTrue(esperada.equivalente(obtida));
    }
    
    

    @Test(timeout = 1000)
    public void duasPositivasMaioresQueUm () {
        f = new Fracao(6,4);
        g = new Fracao(1,2);
        esperada = new Fracao(2, 1);
        obtida = f.soma(g);
        assertTrue(esperada.equivalente(obtida));
    }
    
    @Test(timeout = 1000)
    public void soma0 () {
        f = new Fracao(1,2);
        g = new Fracao(1,-2);
        esperada = new Fracao(0, 1);
        obtida = f.soma(g);
        assertTrue(esperada.equivalente(obtida));
    }
    
    @Test(timeout = 1000)
    public void soma1 () {
        f = new Fracao(1,2);
        g = new Fracao(1,2);
        esperada = new Fracao(1, 1);
        obtida = f.soma(g);
        assertTrue(esperada.equivalente(obtida));
    }
    
    @Test(timeout = 1000)
    public void somaMenorQue0 () {
        f = new Fracao(1,-2);
        g = new Fracao(1,-2);
        esperada = new Fracao(-1, 1);
        obtida = f.soma(g);
        assertTrue(esperada.equivalente(obtida));
    }
    
}
