package testes;

import src.Garagem;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class GaragemEscolhaTest {
    
    private Garagem garagem;
    
    private int esperado;
    private int obtido;
    
    @Before
    public void setUp () throws Exception {
        this.garagem = new Garagem(3, 12000);

        
    }

    @Test
    public void escolhaPorOrdem () throws IOException {
        
        garagem.registaNovoCarro(110, 6000);
        garagem.registaNovoCarro(210, 5000);
        garagem.registaNovoCarro(310, 4000);
        garagem.registaNovoCarro(410, 3000);
        garagem.registaNovoCarro(510, 2000);
        garagem.registaNovoCarro(610, 1000);
        
        
        this.esperado = 4;
        this.obtido = garagem.escolhaPorNumRegisto();
        
        
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void escolhaPorKm () throws IOException {

        garagem.registaNovoCarro(110, 6000);
        garagem.registaNovoCarro(210, 5000);
        garagem.registaNovoCarro(310, 4000);
        garagem.registaNovoCarro(410, 3000);
        garagem.registaNovoCarro(510, 2000);
        garagem.registaNovoCarro(610, 1000);
        
        
        this.esperado = 6;
        this.obtido = garagem.escolhaPorNumKm();
        
        
        assertEquals(esperado, obtido);
    }
   
}
