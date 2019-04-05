package testes;
import src.*;
import static org.junit.Assert.assertEquals;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Test;


public class RaioTest {

	private int esperado;
	private int obtido; 
	
	
	
	@Test(timeout = 1000)
	public final void test1() throws FileNotFoundException {
	    Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo1")));
		Rede rede =  new Rede(sc);
		sc.close();
		this.esperado = 3;
		this.obtido = rede.raio();  
		assertEquals(esperado, obtido);
	} 
	
	@Test(timeout = 1000)
	public final void test2() throws FileNotFoundException {  
		Rede rede = 
				new Rede(new Scanner("A\n B\n  C\n   D\n    E\n   F\n  G\n#")); 
		this.esperado = 4;
		this.obtido = rede.raio();  
		assertEquals(esperado, obtido);
	}  
	
	@Test(timeout = 1000)
	public final void test3() {  
		Rede rede = new Rede(new Scanner("A\n#"));
		this.esperado = 0;
		this.obtido = rede.raio(); 
		assertEquals(esperado, obtido);
	} 
	
	@Test(timeout = 1000)
	public final void test4() {  
		Rede rede = new Rede(new Scanner("A\n B\n C\n#"));
		this.esperado = 1;
		this.obtido = rede.raio(); 
		assertEquals(esperado, obtido);
	}
	
	@Test(timeout = 1000)
	public final void test5() throws FileNotFoundException {  
	    Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo2")));
        Rede rede =  new Rede(sc);
        sc.close();
		this.esperado = 4;
		this.obtido = rede.raio(); 
		assertEquals(esperado, obtido);
	}
	
	@Test(timeout = 1000)
	public final void test6() throws FileNotFoundException {  
	    Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo3")));
        Rede rede =  new Rede(sc);
		this.esperado = 4;
		this.obtido = rede.raio(); 
		sc.close();
		assertEquals(esperado, obtido);
	}
	
	@Test(timeout = 1000)
	public final void test7() throws FileNotFoundException {  
	    Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo4")));
        Rede rede =  new Rede(sc);
        sc.close();
		this.esperado = 5;
		this.obtido = rede.raio(); 
		assertEquals(esperado, obtido);
	}
	
}
