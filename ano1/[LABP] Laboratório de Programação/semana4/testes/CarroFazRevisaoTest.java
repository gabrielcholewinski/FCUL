package testes;

import src.Carro;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarroFazRevisaoTest {

    private Carro carroDeTeste;
    private boolean esperado;
    private boolean obtido;


    @Test
    public void testOkm () {
        this.carroDeTeste = new Carro(1,1, 0);
        esperado = false;
        obtido = carroDeTeste.precisaRevisao();
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testMaisQue0km () {
        this.carroDeTeste = new Carro(1,1, 200);
        esperado = false;
        obtido = carroDeTeste.precisaRevisao();
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testComUpdateDekm () {
        this.carroDeTeste = new Carro(1,1, 10000);
        this.carroDeTeste.actualizarKm(20000);
        this.carroDeTeste.fazRevisao();
        esperado = false;
        obtido = carroDeTeste.precisaRevisao();
        assertEquals(esperado, obtido);
    }
    
}
