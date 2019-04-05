package recursao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEx17Produto {

    private int esperado;
    private int obtido;

    private int n;
    private int m;

    @Test
    public void mNegativoNPositivo () {
        n = 1;
        m = -5;

        esperado = 	-5;		
        obtido = Recursive.produto(n,m);

        assertEquals(esperado,obtido);

    }
    
    @Test
    public void mNegativoNNegativo () {
        n = -1;
        m = -5;

        esperado =  5;     
        obtido = Recursive.produto(n,m);

        assertEquals(esperado,obtido);

    }
    
    @Test
    public void mZeroNNegativo () {
        n = -1;
        m = 0;

        esperado =  0;     
        obtido = Recursive.produto(n,m);

        assertEquals(esperado,obtido);

    }
    
    @Test
    public void mZeroNZero () {
        n = 0;
        m = 0;

        esperado =  0;     
        obtido = Recursive.produto(n,m);

        assertEquals(esperado,obtido);

    }
    
    @Test
    public void mZeroNPositivo () {
        n = 5;
        m = 0;

        esperado =  0;     
        obtido = Recursive.produto(n,m);

        assertEquals(esperado,obtido);

    }
    
    @Test
    public void mPositivoNNegativo () {
        n = -5;
        m = 4;

        esperado =  -20;     
        obtido = Recursive.produto(n,m);

        assertEquals(esperado,obtido);

    }
    
    @Test
    public void mPositivoNPositivo () {
        n = 5;
        m = 4;

        esperado =  20;     
        obtido = Recursive.produto(n,m);

        assertEquals(esperado,obtido);

    }
}
