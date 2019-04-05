package testes;

import src.Fracao;

import static org.junit.Assert.*;

import org.junit.Test;

public class FraccaoTestesMakeFromString {
    
    private Fracao esperada;
    private Fracao obtida;


    @Test(timeout = 1000)
    public void fracaoComNumeradorDenominador () {
        esperada = new Fracao(2,1);
        obtida = Fracao.makeFromString("10/5");
        
        assertTrue(esperada.equivalente(obtida));
    }
    
    @Test(timeout = 1000)
    public void fracaoComNumerador () {
        esperada = new Fracao(2,1);
        obtida = Fracao.makeFromString("2");
        
        assertTrue(esperada.equivalente(obtida));
    }
    
}
