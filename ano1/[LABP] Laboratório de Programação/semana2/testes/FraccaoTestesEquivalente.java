package testes;

import src.Fracao;

import static org.junit.Assert.*;

import org.junit.Test;

public class FraccaoTestesEquivalente {
    
    private Fracao f;
    private boolean obtida;


    @Test(timeout = 1000)
    public void inteiraPositivaEquivalente () {
        f = new Fracao(10,5);
        Fracao f2 =  new Fracao(2,1);
        obtida = f.equivalente(f2);
        assertTrue(obtida);
    }
    

    @Test(timeout = 1000)
    public void inteiraPositivaIgual () {
        f = new Fracao(2,1);
        Fracao f2 =  new Fracao(2,1);
        obtida = f.equivalente(f2);
        assertTrue(obtida);
    }
    

    @Test(timeout = 1000)
    public void outraPositivaEquivalente () {
        f = new Fracao(1,2);
        Fracao f2 =  new Fracao(2,4);
        obtida = f.equivalente(f2);
        assertTrue(obtida);
    }
    
    
    @Test(timeout = 1000)
    public void positivaNaoEquivalente () {
        f = new Fracao(1,2);
        Fracao f2 =  new Fracao(3,4);
        obtida = f.equivalente(f2);
        assertTrue(!obtida);
    }
    
    @Test(timeout = 1000)
    public void negativaNaoEquivalente () {
        f = new Fracao(1,-2);
        Fracao f2 =  new Fracao(-3,4);
        obtida = f.equivalente(f2);
        assertTrue(!obtida);
    }
    
    
    @Test(timeout = 1000)
    public void negativaEquivalente () {
        f = new Fracao(-1,2);
        Fracao f2 =  new Fracao(2,-4);
        obtida = f.equivalente(f2);
        assertTrue(obtida);
    }
    
    @Test(timeout = 1000)
    public void outraNegativaNaoEquivalente () {
        f = new Fracao(1,-2);
        Fracao f2 =  new Fracao(-3,4);
        obtida = f.equivalente(f2);
        assertTrue(!obtida);
    }
}
