package testes;

import src.Fracao;

import static org.junit.Assert.*;

import org.junit.Test;

public class FraccaoTestesEhZero {
    
    private Fracao f;


    @Test(timeout = 1000)
    public void umaQueEhZero () {
        f = new Fracao(0,1); 
        assertTrue (f.ehZero());
    }
    

    @Test(timeout = 1000)
    public void outraQueEhZero () {
        f = new Fracao(0,5); 
        assertTrue (f.ehZero());
    }
    
    @Test(timeout = 1000)
    public void umaQueNaoEhZero () {
        f = new Fracao(1,5); 
        assertTrue (!f.ehZero());
    }
    
    @Test(timeout = 1000)
    public void outraQueNaoEhZero () {
        f = new Fracao(2,1); 
        assertTrue (!f.ehZero());
    }
    
    @Test(timeout = 1000)
    public void aindaOutraQueNaoEhZero () {
        f = new Fracao(5,3); 
        assertTrue (!f.ehZero());
    }
}
