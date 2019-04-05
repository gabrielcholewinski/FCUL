package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx03MaximoVector {
	
	public static final double DELTA = 0.0001;

	private int esperado;
	private int obtido;
	
	private int[] v;
	
	@Test
	public void umSoElemento () {
		v = new int[1];
		v[0] = -1;		
		
		esperado = -1;
		obtido = Recursive.maior(v);
		
		assertTrue(esperado == obtido);

	}
	

	@Test
	public void doisElementos () {
		v = new int[2];
		v[0] = 5;
		v[1] = -3;
		
		esperado = 5;
		obtido = Recursive.maior(v);
		
		assertEquals(esperado, obtido);

	}
	
	@Test
	public void maisQueDoisElementos () {
		v = new int[10];
		for (int i = 0; i < v.length; i++) {
			v[i] = i*5 % 4;
		}
		
		esperado = 3;
		obtido = Recursive.maior(v);
		
		assertTrue(esperado == obtido);

	}
}
