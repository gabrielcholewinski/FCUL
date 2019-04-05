package testes;

import src.Carro;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CarroObtemKmEActualizarKm {

    private Carro carroDeTeste;
    private int esperado;
    private int obtido;
    
    @Before
    public void setUp () throws Exception {
        this.carroDeTeste = new Carro(1,1, 0);
    }

    @Test
    public void testOkm () {
        esperado = 0;
        obtido = carroDeTeste.obtemKm();
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testMaisKmIniciais() {
        carroDeTeste = new Carro (1,1, 5000);
        esperado = 5000;
        obtido = carroDeTeste.obtemKm();
        assertEquals(esperado, obtido);
    }
    
    
    @Test
    public void testMaisKm () {
        carroDeTeste.actualizarKm(5);
        esperado = 5;
        obtido = carroDeTeste.obtemKm();
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testMaisKm2 () {
        carroDeTeste = new Carro (1,1, 200);
        carroDeTeste.actualizarKm(5);
        carroDeTeste.actualizarKm(5);
        esperado = 210;
        obtido = carroDeTeste.obtemKm();
        assertEquals(esperado, obtido);
    }
    
    
    @Test
    public void testMaisKmIniciaisUpdate() {
        carroDeTeste = new Carro (1,1, 5000);
        carroDeTeste.actualizarKm(5000);
        carroDeTeste.actualizarKm(5000);
        esperado = 15000;
        obtido = carroDeTeste.obtemKm();
        assertEquals(esperado, obtido);
    }
}
