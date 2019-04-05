package testes;
import src.*;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Test; 

public class DevolveGrupoReservaTest { 

	private String esperado;
	private String obtido;  


	@Test(timeout = 1000)
	public final void test1() throws FileNotFoundException {
	    Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo1")));
        Rede rede =  new Rede(sc);
        sc.close(); 
		rede.diminuirRaioPara(2); 
		this.esperado = "E F "; 
		this.obtido = rede.devolveGrupoReserva();   
		assertEquals(esperado, obtido);
	}  

	@Test(timeout = 1000)
		public final void test2() throws FileNotFoundException {  
			Rede rede= 
					new Rede(new Scanner("A\n B\n  C\n   D\n    E\n   F\n  G\n#"));
			rede.diminuirRaioPara(3); 
			esperado  = "E ";
			obtido = rede.devolveGrupoReserva();
			assertEquals(esperado, obtido);
		}   
	
		@Test(timeout = 1000)
		public final void test3() {  
			Rede rede = new Rede(new Scanner("A\n#"));
			rede.diminuirRaioPara(0);
			this.esperado = "";
			this.obtido = rede.devolveGrupoReserva(); 
			assertEquals(esperado, obtido);
		} 

		@Test(timeout = 1000)
		public final void test4() {  
			Rede rede = new Rede(new Scanner("A\n B\n C\n#")); 
			rede.diminuirRaioPara(0);
			this.esperado = "B C ";
			obtido = rede.devolveGrupoReserva();
			assertEquals(esperado, obtido);
		} 

		@Test(timeout = 1000)
		public final void test5() throws FileNotFoundException {  
		    Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo2")));
            Rede rede =  new Rede(sc);
            sc.close();
			rede.diminuirRaioPara(2); 
			this.esperado = "J E I F ";
			obtido = rede.devolveGrupoReserva();
			assertEquals(esperado, obtido);
		} 

		@Test(timeout = 1000)
		public final void test6() throws FileNotFoundException {  
		    Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo3")));
            Rede rede =  new Rede(sc);
            sc.close();
			rede.diminuirRaioPara(3);   
			this.esperado = "J I ";
			obtido = rede.devolveGrupoReserva();
			assertEquals(esperado, obtido);
		}

		@Test(timeout = 1000)
		public final void test7() throws FileNotFoundException {  
		    Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo4")));
            Rede rede =  new Rede(sc);
            sc.close();
			rede.diminuirRaioPara(3);  
			this.esperado = "J L I K ";
			obtido = rede.devolveGrupoReserva();
			assertEquals(esperado, obtido);
		}  

}
