package testes;

import src.Garagem;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class GaragemEmServicoTest {
    
    private Garagem garagem;
    
    private int[] esperado;
    private int[] obtido;
    
    @Before
    public void setUp () throws Exception {
        this.garagem = new Garagem(3, 12000);

        
    }

    @Test
    public void tudoParado () throws IOException {
        
        garagem.registaNovoCarro(110, 6000);
        garagem.registaNovoCarro(210, 5000);
        garagem.registaNovoCarro(310, 4000);
        garagem.registaNovoCarro(410, 3000);
        garagem.registaNovoCarro(510, 2000);
        garagem.registaNovoCarro(610, 1000);
        
        esperado = new int[0];
        
        obtido = garagem.carrosEmServico();
        
        assertArrayEquals(esperado, obtido);
    }
    

    @Test
    public void umEmMovimento () throws IOException {
        
        garagem.registaNovoCarro(110, 6000);
        garagem.registaNovoCarro(210, 5000);
        garagem.registaNovoCarro(310, 4000);
        garagem.registaNovoCarro(410, 3000);
        garagem.registaNovoCarro(510, 2000);
        garagem.registaNovoCarro(610, 1000);
        
        garagem.escolhaPorNumKm();
        
        esperado = new int[1];
        esperado[0] = 6;
        
        obtido = garagem.carrosEmServico();
        
        assertArrayEquals(esperado, obtido);
    }
    

    @Test(timeout = 1000)
    public void doisEmMovimento () throws IOException {
        
        garagem.registaNovoCarro(110, 6000);
        garagem.registaNovoCarro(210, 5000);
        garagem.registaNovoCarro(310, 4000);
        garagem.registaNovoCarro(410, 3000);
        garagem.registaNovoCarro(510, 2000);
        garagem.registaNovoCarro(610, 1000);
        
        
        garagem.escolhaPorNumKm();
        garagem.escolhaPorNumKm();
        
        esperado = new int[2];
        esperado[0] = 5;
        esperado[1] = 6;
        
        obtido = garagem.carrosEmServico();
       
        assertArrayEquals(esperado, obtido);
    }
    
    
    @Test(timeout = 1000)
    public void doisEmMovimentoEUmRegressa () throws IOException {
        
        garagem.registaNovoCarro(110, 6000);
        garagem.registaNovoCarro(210, 5000);
        garagem.registaNovoCarro(310, 4000);
        garagem.registaNovoCarro(410, 3000);
        garagem.registaNovoCarro(510, 2000);
        garagem.registaNovoCarro(610, 1000);
        
        
        garagem.escolhaPorNumKm();
        garagem.escolhaPorNumKm();
        
        garagem.estacionaCarro(6, 1000);
        esperado = new int[1];
        esperado[0] = 5;
        
        obtido = garagem.carrosEmServico();
       
        assertArrayEquals(esperado, obtido);
    }
    
}
