package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx07Binomial {
	
	private int esperado;
	private int obtido;
	
	private int l;
	private int k;
	
	@Test
	public void lAZero () {
		l = 0;
		k = 10;
		
		esperado = 	0;		
		obtido = Recursive.binomial(l, k);
		
		assertEquals(esperado,obtido);

	}
	
	@Test
	public void lAUm () {
		l = 1;
		k = 1;
		
		esperado = 	1;		
		obtido = Recursive.binomial(l, k);
		
		assertEquals(esperado,obtido);

	}
	
	
	@Test
	public void kMaiorQueL () {
		l = 15;
		k = 20;
		
		esperado = 	0;		
		obtido = Recursive.binomial(l, k);
		
		assertEquals(esperado,obtido);

	}
	
	@Test
	public void lIgualAKMaiorQue1 () {
		l = 12;
		k = 12;
		
		esperado = 	1;		
		obtido = Recursive.binomial(l, k);
		
		assertEquals(esperado,obtido);

	}
	
	@Test
	public void kA1 () {
	    l = 12;
	    k = 1;

	    esperado =  12;      
	    obtido = Recursive.binomial(l, k);

	    assertEquals(esperado,obtido);

	}

	@Test
	public void kA0LGrande () {
	    l = 12;
	    k = 0;

	    esperado =  1;      
	    obtido = Recursive.binomial(l, k);

	    assertEquals(esperado,obtido);

	}

	@Test
	public void lMaiorQueK () {
	    l = 12;
	    k = 6;

	    esperado =  924;      
	    obtido = Recursive.binomial(l, k);
	    
	    assertTrue(esperado == obtido);

	}
	   
}
