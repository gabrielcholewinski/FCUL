import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OrganizacaoTest {
    
    private Organizacao org;
    
    @Before
    public void setUp () throws Exception {
        this.org = new Organizacao("nome", "xxx.yyy.zzz.gtld");
    }

    @Test
    public void testObterNome () {
        String expected = "nome";
        String actual = this.org.obterNome();
        assertEquals(expected, actual);
    }

    @Test
    public void testObterDominio1 () {
        String expected = "gtld";
        String actual = this.org.obterDominio();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testObterDominio2 () {
        this.org = new Organizacao("nome", "xxx.yyy.gtld");
        String expected = "gtld";
        String actual = this.org.obterDominio();
        assertEquals(expected, actual);
    }

    @Test
    public void testObterEnderecoInternet () {
        String expected = "xxx.yyy.zzz.gtld";
        String actual = this.org.obterEnderecoInternet();
        assertEquals(expected, actual);
    }

    @Test
    public void testToString () {
        String expected = "Organizacao [nome=nome, categoria=gtld, "
                + "endereco internet=xxx.yyy.zzz.gtld]";
        String actual = this.org.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testEqualsObject1 () {
        Organizacao org2 = new Organizacao("nome", "xxx.yyy.zzz.gtld");
        assertEquals(this.org, org2);
        
    }
    
    @Test
    public void testEqualsObject2 () {
        Organizacao org2 = new Organizacao("nome1", "xxx.yyy.zzz.gtld");
        assertTrue(!this.org.equals(org2));
    }
    
    @Test
    public void testEqualsObject3 () {
        Organizacao org2 = new Organizacao("nome", "xxxx.yyy.zzz.gtld");
        assertTrue(!this.org.equals(org2));
    }
}
