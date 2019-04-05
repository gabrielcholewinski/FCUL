package testes;
import src.*;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Test;

public class DevolveRedeTest { 

	private String esperado;
	private String obtido;  


	@Test(timeout = 1000)
	public final void test1() throws FileNotFoundException {
	    Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo1")));
        Rede rede =  new Rede(sc);
        sc.close(); 
		this.esperado = 
			"A\n B\n  C\n   E\n   F\n  G\n H\n";
		this.obtido = rede.imprimeRede();    
		assertEquals(esperado, obtido);
	}   
	
	@Test(timeout = 1000)
	public final void test2() throws FileNotFoundException {  
		Rede rede= 
				new Rede(new Scanner("A\n B\n  C\n   D\n    E\n   F\n  G\n#")); 
		esperado  = "A\n B\n  C\n   D\n    E\n   F\n  G\n"; 
		this.obtido = rede.imprimeRede();    
		assertEquals(esperado, obtido);
	}  

	@Test(timeout = 1000)
	public final void test3() {  
		Rede rede = new Rede(new Scanner("A\n#")); 
		this.esperado = "A\n";
		this.obtido = rede.imprimeRede(); 
		assertEquals(esperado, obtido);
	} 

	@Test(timeout = 1000)
	public final void test4() {  
		Rede rede = new Rede(new Scanner("A\n B\n C\n#"));  
		this.esperado = "A\n B\n C\n";
		obtido = rede.imprimeRede();
		assertEquals(esperado, obtido);
	} 

	@Test(timeout = 1000)
	public final void test5() throws FileNotFoundException {  
	    Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo2")));
        Rede rede =  new Rede(sc);
        sc.close(); 
		this.esperado =  
				"A\n B\n  C\n   E\n    J\n    I\n   F\n  G\n H\n"; 
		obtido = rede.imprimeRede();
		assertEquals(esperado, obtido);
	}  
} 