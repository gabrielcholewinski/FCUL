package recursao;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;


public class TesteEx02SomaAteN {
	
	private int esperado;
	private int obtido;
	
	private int n;
	
	@Test
	public void zero () {
		n = 0;
		
		esperado = 	0;
		obtido = Recursive.soma(n);
		
		assertEquals(esperado, obtido);
	}
	
	@Test
	public void um () {
		n = 1;
		
		esperado = 	1;
		obtido = Recursive.soma(n);
		
		assertEquals(esperado, obtido);
	}

	@Test
	public void maiorQue1 () {

		Random r = new Random ();
		
		n = r.nextInt(100);
				
		esperado = 	(n+1)*n/2;
		obtido = Recursive.soma(n);

		assertEquals(esperado, obtido);
	}
}
