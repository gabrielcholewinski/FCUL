package testes;

import src.Garagem;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class GaragemRecolheTest {
    
    private Garagem garagem;
    
    private int esperado;
    private int obtido;
    
    @Before
    public void setUp () throws Exception {
        this.garagem = new Garagem(3, 5000);

        garagem.registaNovoCarro(200, 1000);
        garagem.registaNovoCarro(300, 2000);
        garagem.registaNovoCarro(150, 3000);
        garagem.registaNovoCarro(100, 100);
        
        garagem.escolhaPorNumKm();
        
    }

    @Test
    public void recolhe () throws IOException {
        
        
        garagem.estacionaCarro(4, 200);
        
        obtido = garagem.escolhaPorNumKm();
        
        this.esperado = 4;
        
        
        assertEquals(esperado, obtido);
    }

    
   
}
