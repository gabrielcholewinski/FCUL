package testes;

import src.Pista;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PistaConstructorTest {
    
    private Pista estacionamento;
    
    
    @Before
    public void setUp () throws Exception {
        this.estacionamento = new Pista(1, 2500);
    }

    @Test
    public void testGetNome () {
        int obtido = estacionamento.obtemIdentificacao(); 
        assertEquals(1, obtido);
    }

    @Test
    public void testGetComprimento () {
        int obtido = estacionamento.obtemComprimento(); 
        assertEquals(2500, obtido);
    }
   
}
