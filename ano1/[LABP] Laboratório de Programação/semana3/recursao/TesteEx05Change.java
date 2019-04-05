package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx05Change {
	
	private String stringEsperado;
	private String stringObtido;
	
	private String s;
	private char old;
	private char newer;
	
	
	@Test
	public void comStringvazia () {
		s = "";
		old = 'x';
		newer = 'X';

		stringEsperado = 	"";		
		stringObtido = Recursive.change(s, old, newer);
		
		assertEquals(stringEsperado, stringObtido);

	}
	
	@Test
	public void semOcorrencias () {
		s = "aha";
		old = 'x';
		newer = 'X';

		stringEsperado = 	"aha";		
		stringObtido = Recursive.change(s, old, newer);
		
		assertEquals(stringEsperado, stringObtido);

	}
	
	@Test
	public void umaOcorrencia () {
		s = "aha";
		old = 'h';
		newer = 'X';

		stringEsperado = 	"aXa";		
		stringObtido = Recursive.change(s, old, newer);
		
		assertEquals(stringEsperado, stringObtido);

	}
	
	@Test
	public void maisQueUmaOcorrencia () {
		s = "ha 3 ases? sim ha";
		old = 'a';
		newer = 'X';

		stringEsperado = 	"hX 3 Xses? sim hX";		
		stringObtido = Recursive.change(s, old, newer);
		
		assertEquals(stringEsperado, stringObtido);

	}
	
	@Test
	public void ehCaseSensitiveMaiusculas () {
		s = "A caixA De PAndorA";
		old = 'A';
		newer = 'Y';

		stringEsperado = 	"Y caixY De PYndorY";		
		stringObtido = Recursive.change(s, old, newer);
		
		assertEquals(stringEsperado, stringObtido);

	}	
	
	@Test
	public void ehCaseSensitiveMinusculas () {
		s = "caixADePandorA";
		old = 'i';
		newer = 'y';

		stringEsperado = 	"cayxADePandorA";		
		stringObtido = Recursive.change(s, old, newer);
		
		assertEquals(stringEsperado, stringObtido);

	}	
	
}
