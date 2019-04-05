package testes;

import src.Carro;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CarroConstructorTest {
    
    private Carro carrinhoDeTeste;
    
    
    @Before
    public void setUp () throws Exception {
        this.carrinhoDeTeste = new Carro(1, 233, 200);
    }

    @Test
    public void testOrdemDeRegisto () {
        int obtido = carrinhoDeTeste.obtemNumeroRegisto(); 
        assertEquals(1, obtido);
    }

    @Test
    public void testObtemComprimento () {
        double obtido = carrinhoDeTeste.obtemComprimento(); 
        assertEquals(obtido, 233, 0.001);
    }
    
    @Test
    public void testObtemKmIniciais () {
        int obtido = carrinhoDeTeste.obtemKm(); 
        assertEquals(200, obtido);
    }

   
}
