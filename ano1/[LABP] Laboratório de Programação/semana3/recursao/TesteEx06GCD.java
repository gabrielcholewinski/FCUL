package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx06GCD {
	
	private int esperado;
	private int obtido;
	
	private int p;
	private int q;
	
	@Test
	public void pIgualAQ () {
		p = 10;
		q = 10;
		
		esperado = 	10;		
		obtido = Recursive.gcd(p, q);
		
		assertTrue(esperado == obtido);

	}
	
	@Test
	public void pMenorQueQEGCD1 () {
		p = 10000;
		q = 1000000001;
		
		esperado = 	1;		
		obtido = Recursive.gcd(p, q);
		
		assertTrue(esperado == obtido);

	}
	
	
	@Test
	public void pMenorQueQEGCDMaiorQue1 () {
		p = 123456700;
		q = 100000;
		
		esperado = 	100;		
		obtido = Recursive.gcd(p, q);
		
		assertTrue(esperado == obtido);

	}
	
	@Test
	public void pMaiorQueQECGDMaiorQue1 () {
		p = 123467895;
		q = 16385;
		
		esperado = 	5;		
		obtido = Recursive.gcd(p, q);
		
		assertTrue(esperado == obtido);

	}
}
