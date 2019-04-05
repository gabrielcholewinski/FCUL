package testes;

import src.Fracao;

import static org.junit.Assert.*;

import org.junit.Test;

public class FraccaoTestesEhPositiva {
    
    private Fracao f;


    @Test(timeout = 1000)
    public void umaQueEhZero () {
        f = new Fracao(0,1); 
        assertTrue (!f.ehPositiva());
    }
    

    
    @Test(timeout = 1000)
    public void umaQueNaoEhPositiva () {
        f = new Fracao(1,-5); 
        assertTrue (!f.ehPositiva());
    }
    
    

    @Test(timeout = 1000)
    public void outraQueNaoEhPositiva () {
        f = new Fracao(-6,3); 
        assertTrue (!f.ehPositiva());
    }
    
    
    
    @Test(timeout = 1000)
    public void umaQueEhPositiva () {
        f = new Fracao(1,5); 
        assertTrue (f.ehPositiva());
    }
    
    

    @Test(timeout = 1000)
    public void outraQueEhPositiva () {
        f = new Fracao(6,3); 
        assertTrue (f.ehPositiva());
    }
    

}
