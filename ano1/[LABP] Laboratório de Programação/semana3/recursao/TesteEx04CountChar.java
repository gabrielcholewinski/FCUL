package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx04CountChar {
	
	private int esperado;
	private int obtido;
	
	private String s;
	private char c;
	
	@Test
	public void maisQueUmaOcorrencia () {
		s = "ha2as";
		c = 'a'; 

		esperado = 	2;		
		obtido = Recursive.countChar(s, c);
		
		assertTrue(esperado == obtido);

	}
	
	@Test
	public void apenasUmaOcorrencia () {
		s = "soHaum ene";
		c = 'n'; 

		esperado = 	1;
		obtido = Recursive.countChar(s, c);
		
		assertTrue(esperado == obtido);
	}

	@Test
	public void zeroOcorrencias () {
		s = "naotemagas";
		c = 'h'; 
		
		esperado = 	0;
		obtido = Recursive.countChar(s, c);
		
		assertTrue(esperado == obtido);
	}
}
