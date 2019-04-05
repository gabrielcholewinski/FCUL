package testes;

import src.Carro;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarroPrecisaRevisaoTest {

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
    public void testMaiskm () {
        this.carroDeTeste = new Carro(1,1, 2500);
        esperado = false;
        obtido = carroDeTeste.precisaRevisao();
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testComUpdateAbaixoLimiteDekm () {
        this.carroDeTeste = new Carro(1,1, 0);
        this.carroDeTeste.actualizarKm(1000);
        esperado = false;
        obtido = carroDeTeste.precisaRevisao();
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testComUpdateParaLimiteDekm () {
        this.carroDeTeste = new Carro(1,1, 0);
        this.carroDeTeste.actualizarKm(2000);
        esperado = false;
        obtido = carroDeTeste.precisaRevisao();
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testComUpdateParaCimaLimiteDekm () {
        this.carroDeTeste = new Carro(1,1, 0);
        this.carroDeTeste.actualizarKm(25000);
        esperado = true;
        obtido = carroDeTeste.precisaRevisao();
        assertEquals(esperado, obtido);
    }
    
    
    @Test
    public void testComUpdateParaCimaLimiteDekm2 () {
        this.carroDeTeste = new Carro(1,1, 25000);
        this.carroDeTeste.actualizarKm(40000);
        esperado = true;
        obtido = carroDeTeste.precisaRevisao();
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testComAplicacaoDeUmaRevisao () {
        this.carroDeTeste = new Carro(1,1, 25000);
        this.carroDeTeste.actualizarKm(40000);
        this.carroDeTeste.fazRevisao();
        esperado = false;
        obtido = carroDeTeste.precisaRevisao();
        assertEquals(esperado, obtido);
    }
    
}
