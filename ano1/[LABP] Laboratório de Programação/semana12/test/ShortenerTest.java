import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ShortenerTest {

	private Shortener sh;

	@Before
	public void setUp () throws Exception {
		ArrayList<String> doms = new ArrayList<>();
		doms.add("pt");
		doms.add("com");
		this.sh = new Shortener(doms, 4);
	}

	@Test
	public void testRegistarOrganizacao () {
		String nome = "teste";
		String address = "unused.pt";
		boolean actual = this.sh.registarOrganizacao(nome, address);
		boolean expected = true;
		assertEquals(expected, actual);
	}

	@Test
	public void testRegistarOrganizacao2 () {
		String nome = "teste";
		String address = "nonexistent.pt";
		boolean unused = this.sh.registarOrganizacao(nome, address);
		boolean actual = this.sh.registarOrganizacao(nome, address);
		boolean expected = false;
		assertEquals(expected, actual);
	}

	@Test
	public void testRegistarOrganizacao3 () {
		String nome = "teste";
		String address = "nonexistent.xyz";
		boolean actual = this.sh.registarOrganizacao(nome, address);
		boolean expected = false;
		assertEquals(expected, actual);
	}

	@Test
	public void testRegistarUrl () { 
		String nome = "teste";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url expected = new Url("d7413833.pt", 1);
		Url actual = this.sh.registarUrl(nome);
		assertEquals(expected, actual);
	}

	@Test
	public void testRegistarUrl2 () {
		String nome = "testeNovo";  
		Url expected = null;
		Url actual = this.sh.registarUrl(nome);
		assertEquals(expected, actual);
	}

	@Test
	public void testRegistarUrl3 () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.pt";
		Url unused = this.sh.registarUrl(nome);  
		Url expected = null;
		Url actual = this.sh.registarUrl(nome);
		assertEquals(expected, actual);
	}

	@Test
	public void testRegistarUrl4 () {
		String nome = "teste1";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused1 = this.sh.registarUrl(nome); 

		String nome2 = "teste2";
		String address2 = "xxx.yyy.www.pt";
		this.sh.registarOrganizacao(nome2, address2); 
		Url unused2 = this.sh.registarUrl(nome2); 

		String nome3 = "teste3";
		String address3 = "xxx.yyy.kkk.pt";
		this.sh.registarOrganizacao(nome3, address3);
		Url expected = null;
		Url actual = this.sh.registarUrl(nome3);
		assertEquals(expected, actual);
	} 

	@Test
	public void testRegistarUrl5 () { 
		String nome = "teste";
		String address = "xxx.yyy.zzz.com";
		this.sh.registarOrganizacao(nome, address);   
		Url unused = this.sh.registarUrl(nome);

		String nome2 = "teste2";
		String address2 = "xxx.yyy.www.com";
		this.sh.registarOrganizacao(nome2, address2);
		Url expected = new Url("34a59c60.com", 2); 
		Url actual= this.sh.registarUrl(nome2); 
		assertEquals(expected, actual);
	}

	@Test
	public void testRemoverUrl () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url expected = this.sh.registarUrl(nome);
		Url actual = 
				this.sh.removerUrl(this.sh.organizacaoPorNome(nome));
		assertEquals(expected, actual);
	}

	@Test
	public void testRemoverUrl2 () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.pt";
		Organizacao notRegistered = new Organizacao(nome, address);
		Url expected = null;
		Url actual = this.sh.removerUrl(notRegistered);
		assertEquals(expected, actual);
	}

	@Test
	public void testRemoverUrl3 () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused = this.sh.registarUrl(nome);
		Url unused2 = 
				this.sh.removerUrl(this.sh.organizacaoPorNome(nome));
		Url expected = null;
		Url actual = this.sh.obterUrl(this.sh.organizacaoPorNome(nome)); 
		assertEquals(expected, actual);
	}

	@Test
	public void testObterDono () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address); 
		Url unused = this.sh.registarUrl(nome);
		String nome2 = "teste2";
		String address2 = "xxx.yyy.www.pt";
		this.sh.registarOrganizacao(nome2, address2); 
		Url unused2 = this.sh.registarUrl(nome2);
		Organizacao expected = this.sh.organizacaoPorNome(nome2);
		Organizacao actual = this.sh.obterDono("pt", 2);
		assertEquals(expected, actual);
	}

	@Test
	public void testObterDono2 () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address); 
		Url unused = this.sh.registarUrl(nome);
		String nome2 = "teste2";
		String address2 = "xxx.yyy.www.pt";
		this.sh.registarOrganizacao(nome2, address2); 
		Url unused2 = this.sh.registarUrl(nome2);
		Organizacao expected = this.sh.organizacaoPorNome(nome2);
		Organizacao actual = this.sh.obterDono("pt", 2);
		assertEquals(expected, actual);
	}

	@Test
	public void testObterDono3 () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address); 
		Url unused = this.sh.registarUrl(nome); 
		Organizacao expected = null;
		Organizacao actual = this.sh.obterDono("pt", 2);
		assertEquals(expected, actual);
	}

	@Test
	public void testObterDono4 () { 
		Organizacao expected = null;
		Organizacao actual = this.sh.obterDono("pt", 3);
		assertEquals(expected, actual);
	}

	@Test
	public void testObterDono5 () { 
		Organizacao expected = null;
		Organizacao actual = this.sh.obterDono("uk", 1);
		assertEquals(expected, actual);
	}

	@Test
	public void testOrganizacaoDoNome () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address); 
		Organizacao expected = new Organizacao(nome, address);
		Organizacao actual = this.sh.organizacaoPorNome(nome);
		assertEquals(expected, actual);
	}

	@Test
	public void testOrganizacaoDoNome2 () {
		String nome = "teste"; 
		Organizacao expected = null;
		Organizacao actual = this.sh.organizacaoPorNome(nome);
		assertEquals(expected, actual);
	}

	@Test
	public void testObterUrl () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address); 
		Url expected = this.sh.registarUrl(nome);
		Url actual = this.sh.obterUrl(this.sh.organizacaoPorNome(nome));
		assertEquals(expected, actual);
	}

	@Test
	public void testObterUrl2 () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address); 
		Url expected = null;
		Url actual = this.sh.obterUrl(this.sh.organizacaoPorNome(nome));
		assertEquals(expected, actual);
	}

	@Test
	public void testObterEspacoDoUrl () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address); 
		Url url = this.sh.registarUrl(nome);
		String expected = "pt";
		String actual = this.sh.obterEspacoDoUrl(url);
		assertEquals(expected, actual);
	}

	@Test
	public void testObterEspacoDoUrl2 () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.com";
		this.sh.registarOrganizacao(nome, address); 
		Url url = this.sh.registarUrl(nome);
		String expected = "com";
		String actual = this.sh.obterEspacoDoUrl(url);
		assertEquals(expected, actual);
	}

	@Test
	public void testObterEspacoDoUrl3 () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.uk";
		this.sh.registarOrganizacao(nome, address); 
		Url url = this.sh.registarUrl(nome);
		String expected = null;
		String actual = this.sh.obterEspacoDoUrl(url);
		assertEquals(expected, actual);
	}

	@Test
	public void testObterEspacoDaOrg () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address); 
		Url url = this.sh.registarUrl(nome);
		String expected = "pt";
		String actual = 
				this.sh.obterEspacoDaOrg(this.sh.organizacaoPorNome(nome));
		assertEquals(expected, actual);
	}

	@Test
	public void testObterEspacoDaOrg2 () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.com";
		this.sh.registarOrganizacao(nome, address); 
		Url url = this.sh.registarUrl(nome);
		String expected = "com";
		String actual =  
				this.sh.obterEspacoDaOrg(this.sh.organizacaoPorNome(nome));
		assertEquals(expected, actual);
	}

	@Test
	public void testObterEspacoDaOrg3 () {
		String nome = "teste";
		String address = "xxx.yyy.zzz.uk";
		this.sh.registarOrganizacao(nome, address); 
		Url url = this.sh.registarUrl(nome);
		String expected = null;
		String actual =  
				this.sh.obterEspacoDaOrg(this.sh.organizacaoPorNome(nome));
		assertEquals(expected, actual);
	}


	@Test
	public void testUrlGerado () {
		String nome = "teste1";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused1 = this.sh.registarUrl(nome); 

		String nome2 = "teste2";
		String address2 = "xxx.yyy.www.pt";
		this.sh.registarOrganizacao(nome2, address2); 
		Url unused2 = this.sh.registarUrl(nome2); 

		String nome3 = "teste3";
		String address3 = "xxx.yyy.kkk.pt";
		this.sh.registarOrganizacao(nome3, address3);
		int expected = 2;
		int actual = this.sh.urlGerado();
		assertEquals(expected, actual);
	}

	@Test
	public void testUrlGerado2 () {
		String nome = "teste1";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused1 = this.sh.registarUrl(nome); 

		String nome2 = "teste2";
		String address2 = "xxx.yyy.www.pt";
		this.sh.registarOrganizacao(nome2, address2); 
		Url unused2 = this.sh.registarUrl(nome2); 

		String nome3 = "teste3";
		String address3 = "xxx.yyy.kkk.com";
		this.sh.registarOrganizacao(nome3, address3);
		Url unused3 = this.sh.registarUrl(nome3);
		
		int expected = 3;
		int actual = this.sh.urlGerado();
		assertEquals(expected, actual);
	}

	public void testUrlGerado3 () {
		String nome = "teste1";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address); 

		int expected = 0;
		int actual = this.sh.urlGerado();
		assertEquals(expected, actual);
	}

	public void testUrlGerado4 () {
		String nome = "teste"; 
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused = this.sh.registarUrl(nome);
		Url unused2 = 
				this.sh.removerUrl(this.sh.organizacaoPorNome(nome));
		
		int expected = 0;
		int actual = this.sh.urlGerado();
		assertEquals(expected, actual);
	}
	
	public void testUrlGerado5 () {
		String nome = "teste"; 
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused = this.sh.registarUrl(nome); 
		int expected = 1;
		int actual = this.sh.urlGerado();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testUrlGeradoS () {
		String nome = "teste1";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused1 = this.sh.registarUrl(nome); 

		String nome2 = "teste2";
		String address2 = "xxx.yyy.www.pt";
		this.sh.registarOrganizacao(nome2, address2); 
		Url unused2 = this.sh.registarUrl(nome2); 

		String nome3 = "teste3";
		String address3 = "xxx.yyy.kkk.pt";
		this.sh.registarOrganizacao(nome3, address3);
		int expected = 2;
		int actual = this.sh.urlGerado("pt");
		assertEquals(expected, actual);
	}

	@Test
	public void testUrlGeradoS2 () {
		String nome = "teste1";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused1 = this.sh.registarUrl(nome); 

		String nome2 = "teste2";
		String address2 = "xxx.yyy.www.pt";
		this.sh.registarOrganizacao(nome2, address2); 
		Url unused2 = this.sh.registarUrl(nome2); 

		String nome3 = "teste3";
		String address3 = "xxx.yyy.kkk.com";
		this.sh.registarOrganizacao(nome3, address3);
		int expected = 2;
		int actual = this.sh.urlGerado("pt");
		assertEquals(expected, actual); //2 = 2 not 2 = 3
	}
	
	@Test
	public void testUrlGeradoS3 () {
		String nome = "teste1";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused1 = this.sh.registarUrl(nome); 

		String nome2 = "teste2";
		String address2 = "xxx.yyy.www.pt";
		this.sh.registarOrganizacao(nome2, address2); 
		Url unused2 = this.sh.registarUrl(nome2); 

		String nome3 = "teste3";
		String address3 = "xxx.yyy.kkk.com";
		this.sh.registarOrganizacao(nome3, address3);
		Url unused3 = this.sh.registarUrl(nome3);
		
		int expected = 1;
		int actual = this.sh.urlGerado("com");
		assertEquals(expected, actual);
	}

	public void testUrlGeradoS4 () {
		String nome = "teste1";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address); 

		int expected = 0;
		int actual = this.sh.urlGerado("pt");
		assertEquals(expected, actual);
	}

	public void testUrlGeradoS5 () {
		String nome = "teste"; 
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused = this.sh.registarUrl(nome);
		Url unused2 = 
				this.sh.removerUrl(this.sh.organizacaoPorNome(nome));
		
		int expected = 0;
		int actual = this.sh.urlGerado("pt");
		assertEquals(expected, actual);
	}
	
	public void testUrlGeradoS6 () {
		String nome = "teste"; 
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused = this.sh.registarUrl(nome); 
		int expected = 0;
		int actual = this.sh.urlGerado("uk");
		assertEquals(expected, actual);
	}


	@Test
	public void testNumeroOrgsRegistadas () {
		String nome = "teste1";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused1 = this.sh.registarUrl(nome); 

		String nome2 = "teste2";
		String address2 = "xxx.yyy.www.pt";
		this.sh.registarOrganizacao(nome2, address2);  

		String nome3 = "teste3";
		String address3 = "xxx.yyy.kkk.com";
		this.sh.registarOrganizacao(nome3, address3);
		
		int expected = 3;
		int actual = this.sh.nOrgsRegistadas();
		assertEquals(expected, actual); 
	}
	
	@Test
	public void testNumeroOrgsRegistadas2 () {
		String nome = "teste1";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused1 = this.sh.registarUrl(nome); 

		String nome2 = "teste2";
		String address2 = "xxx.yyy.www.pt";
		this.sh.registarOrganizacao(nome2, address2);  

		String nome3 = "teste3";
		String address3 = "xxx.yyy.kkk.uk";
		this.sh.registarOrganizacao(nome3, address3);
		
		int expected = 2;
		int actual = this.sh.nOrgsRegistadas();
		assertEquals(expected, actual);
		
		
	}
	
	@Test
	public void testNumeroOrgsRegistadasPorDominio2 () {
		String nome = "teste1";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused1 = this.sh.registarUrl(nome); 

		String nome2 = "teste2";
		String address2 = "xxx.yyy.www.pt";
		this.sh.registarOrganizacao(nome2, address2);  

		String nome3 = "teste3";
		String address3 = "xxx.yyy.kkk.com";
		this.sh.registarOrganizacao(nome3, address3);
		
		int expected = 2;
		int actual = this.sh.nOrgsRegistadasPorDominio("pt");
		assertEquals(expected, actual);
		
		
	}
	
	@Test
	public void testNumeroOrgsRegistadasPorDominio3 () {
		String nome = "teste1";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused1 = this.sh.registarUrl(nome); 

		String nome2 = "teste2";
		String address2 = "xxx.yyy.www.pt";
		this.sh.registarOrganizacao(nome2, address2);  

		String nome3 = "teste3";
		String address3 = "xxx.yyy.kkk.com";
		this.sh.registarOrganizacao(nome3, address3);
		
		int expected = 1;
		int actual = this.sh.nOrgsRegistadasPorDominio("com");
		assertEquals(expected, actual);
		
		
	}
	
	@Test
	public void testNumeroOrgsRegistadas3 () {
		String nome = "teste1";
		String address = "xxx.yyy.zzz.pt";
		this.sh.registarOrganizacao(nome, address);
		Url unused1 = this.sh.registarUrl(nome); 

		String nome2 = "teste2";
		String address2 = "xxx.yyy.www.pt";
		this.sh.registarOrganizacao(nome2, address2);  

		String nome3 = "teste3";
		String address3 = "xxx.yyy.kkk.uk";
		this.sh.registarOrganizacao(nome3, address3);
		
		int expected = 0;
		int actual = this.sh.nOrgsRegistadasPorDominio("uk");
		assertEquals(expected, actual);
		
		
	}
}

