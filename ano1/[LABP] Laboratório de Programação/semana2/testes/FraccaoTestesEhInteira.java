package testes;

import src.Fracao;

import static org.junit.Assert.*;

import org.junit.Test;

public class FraccaoTestesEhInteira {
    
    private Fracao f;


    @Test(timeout = 1000)
    public void umaQueEhZero () {
        f = new Fracao(0,1); 
        assertTrue (f.ehInteira());
    }
    

    
    @Test(timeout = 1000)
    public void umaQueNaoEhInteira () {
        f = new Fracao(1,-5); 
        assertTrue (!f.ehInteira());
    }
    
    

    @Test(timeout = 1000)
    public void outraQueNaoEhInteira () {
        f = new Fracao(6,5); 
        assertTrue (!f.ehInteira());
    }
    
    
    
    @Test(timeout = 1000)
    public void umaQueEhInteira () {
        f = new Fracao(-10,5); 
        assertTrue (f.ehInteira());
    }
    
    

    @Test(timeout = 1000)
    public void outraQueEhInteira () {
        f = new Fracao(6,-3); 
        assertTrue (f.ehInteira());
    }
    

    @Test(timeout = 1000)
    public void aindaOutraQueEhInteira () {
        f = new Fracao(6,3); 
        assertTrue (f.ehInteira());
    }
}
