package testes;

import src.Garagem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class GaragemCarrosRegistadosTest {
    
    private Garagem garagem;
    
    private int esperado;
    private int obtido;
    
    @Before
    public void setUp () throws Exception {
        this.garagem = new Garagem(3, 12000);

        
    }
    
    @Test(timeout =  1000)
    public void incial () {
        this.esperado = 0;
        this.obtido = garagem.obtemNumeroCarrosRegistados();
        
        
        assertEquals(esperado, obtido);
    }
    
    @Test(timeout =  1000)
    public void com1Registado () {
        garagem.registaNovoCarro(110, 600);
        this.esperado = 1;
        this.obtido = garagem.obtemNumeroCarrosRegistados();
        
        
        assertEquals(esperado, obtido);
    }
    
    @Test(timeout =  1000)
    public void comVariosRegistados () {
        garagem.registaNovoCarro(110, 600);
        garagem.registaNovoCarro(120, 600);
        garagem.registaNovoCarro(130, 600);
        garagem.registaNovoCarro(140, 600);
        garagem.registaNovoCarro(150, 600);
        this.esperado = 5;
        this.obtido = garagem.obtemNumeroCarrosRegistados();
        
        
        assertEquals(esperado, obtido);
    }
    
}
