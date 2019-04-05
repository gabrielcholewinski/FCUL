import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UrlTest {
    
    private Url url;
    
    @Before
    public void setUp () throws Exception {
        this.url = new Url("82cb643b.pt", 2);
    }


    @Test
    public void testGetIpAddress () {
        String expected = "100.1.1.2";
        String actual = this.url.obterEnderecoIP();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAddress () {
        String expected = "82cb643b.pt";
        String actual = this.url.obterEndereco();
        assertEquals(expected, actual);
    }

    @Test
    public void testEqualsObject () {
        Url url2 = new Url("82cb643b.pt", 2);
        assertEquals(this.url, url2);
    }
    
    @Test
    public void testEqualsObject2 () {
        Url url2 = new Url("82cb643b.pt", 1);
        assertTrue(!this.url.equals(url2));
    }
    
    @Test
    public void testEqualsObject3 () {
        Url url2 = new Url("82cb643c.pt", 2);
        assertTrue(!this.url.equals(url2));
    }

    @Test
    public void testToString () {
        String expected = "ENDERECO : 82cb643b.pt, IP : 100.1.1.2";
        String actual = this.url.toString();
        assertEquals(expected, actual);
    }
}
