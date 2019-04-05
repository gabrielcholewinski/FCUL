package testes;

import src.Pista;
import src.Carro;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PistaAdicionaTest {
    
    private Pista estacionamento;
    private boolean obtido;
    private boolean esperado;
    
    @Before
    public void setUp () throws Exception {
        this.estacionamento = new Pista(1, 250);
    }

    
    @Test
    public void testeAdiciona () {
        Carro xpto = new Carro(1, 1, 10000);
        obtido = estacionamento.estacionaCarro(xpto); 
        esperado = true;
        assertEquals(esperado, obtido);
    }

    @Test
    public void testeNaoCabe () {
        Carro xpto = new Carro(1, 253, 10000);
        obtido = estacionamento.estacionaCarro(xpto);
        esperado = false;
        assertEquals(esperado, obtido);
    }
    
    

}
