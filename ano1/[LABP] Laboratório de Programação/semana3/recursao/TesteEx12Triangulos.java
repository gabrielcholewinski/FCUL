package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx12Triangulos {
	
	private int esperado;
	private int obtido;
	
	private int l;
	
	@Test
	public void um () {
		l = 1;
		
		esperado = 	1;		
		obtido = Recursive.triangulos(l);
		
		assertEquals(esperado,obtido);

	}
	
	
	@Test
    public void dois () {
        l = 2;
        
        esperado =  3;      
        obtido = Recursive.triangulos(l);
        
        assertEquals(esperado,obtido);

    }
	
	@Test
    public void muitos () {
        l = 10;
        
        esperado =  55;      
        obtido = Recursive.triangulos(l);
        
        assertEquals(esperado,obtido);

    }
}
