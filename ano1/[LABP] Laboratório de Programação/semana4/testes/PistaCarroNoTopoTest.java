package testes;

import src.Carro;
import src.Pista;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PistaCarroNoTopoTest {
    
    private Pista estacionamento;
    private Carro obtido;
    private Carro esperado;
    
    @Before
    public void setUp () throws Exception {
        this.estacionamento = new Pista(1, 2500);
    }

    @Test
    public void testeVaziaInicio () {
        obtido = null;
        obtido = estacionamento.carroNoTopo(); 
    }
    
    
    @Test
    public void testeCom1 () {
        Carro xpto = new Carro(1, 1, 10000);
        estacionamento.estacionaCarro(xpto);
        obtido = estacionamento.carroNoTopo(); 
        esperado = xpto;
        assertEquals(esperado, obtido);
    }

    @Test
    public void testeComMaisQue1 () {
        Carro xpto = new Carro(1, 230, 10000);
        Carro xpto2 = new Carro(2, 350, 10000);
        estacionamento.estacionaCarro(xpto);
        estacionamento.estacionaCarro(xpto2);
        obtido = estacionamento.carroNoTopo(); 
        esperado = xpto2;
        assertEquals(esperado, obtido);
    }
    
    

}
