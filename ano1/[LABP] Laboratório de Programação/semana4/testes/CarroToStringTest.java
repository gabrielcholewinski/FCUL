package testes;

import src.Carro;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarroToStringTest {
    
    private Carro carrinhoDeTeste;
    private String esperado;
    private String obtido;
    
    @Test
    public void testToString0km () {
        this.carrinhoDeTeste = new Carro(0, 200, 0);
        esperado = "Carro 0: (0 km, 200 cm, Revisao: ok)";
        obtido = carrinhoDeTeste.toString(); 
        assertEquals(esperado, obtido);
    }

    @Test
    public void testToStringMaiskm () {
        this.carrinhoDeTeste = new Carro(0, 250, 3000);
        this.carrinhoDeTeste.actualizarKm(25000);
        esperado = "Carro 0: (28000 km, 250 cm, Revisao: em falta)";
        obtido = carrinhoDeTeste.toString(); 
        assertEquals(esperado, obtido);
    }
  
}
