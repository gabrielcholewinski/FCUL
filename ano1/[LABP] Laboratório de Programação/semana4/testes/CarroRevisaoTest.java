package testes;

import src.Carro;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarroRevisaoTest {

    private Carro carroDeTeste;
    private int esperado;
    private int obtido;


    @Test
    public void testOkm () {
        this.carroDeTeste = new Carro(1,1, 0);
        esperado = 20000;
        obtido = carroDeTeste.proximaRevisao();
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testMaisQue0km () {
        this.carroDeTeste = new Carro(1,1, 200);
        esperado = 20200;
        obtido = carroDeTeste.proximaRevisao();
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testComUpdateDekm () {
        this.carroDeTeste = new Carro(1,1, 10000);
        this.carroDeTeste.actualizarKm(20000);
        esperado = 30000;
        obtido = carroDeTeste.proximaRevisao();
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testComUpdateDekmEKm () {
        this.carroDeTeste = new Carro(1,1, 10000);
        this.carroDeTeste.actualizarKm(20000);
        this.carroDeTeste.fazRevisao();
        esperado = 50000;
        obtido = carroDeTeste.proximaRevisao();
        assertEquals(esperado, obtido);
    }
    
}
