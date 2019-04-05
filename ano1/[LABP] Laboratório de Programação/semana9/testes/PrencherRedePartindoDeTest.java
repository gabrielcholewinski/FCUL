package testes;
import src.*;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

	import org.junit.Test; 
	
public class PrencherRedePartindoDeTest { 

		private Rede esperado;
		private Rede obtido;  
		 

		@Test(timeout = 1000)
		public final void test1() throws FileNotFoundException {
		    Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo1")));
            Rede rede =  new Rede(sc);
            sc.close(); 
			rede.diminuirRaioPara(2); 
			rede.distribuiRobosPartindoDe('G');
			this.esperado = 
					new Rede(new Scanner("A\n B\n  C\n  G\n  E\n  F\n  H\n#")); 
			this.obtido = rede;    
			assertEquals(esperado.imprimeRede(), obtido.imprimeRede());
		}  

		@Test(timeout = 1000)
		public final void test2() throws FileNotFoundException {  
			Rede rede= 
					new Rede(new Scanner("A\n B\n  C\n   D\n   E\n  F\n G\n#"));
			rede.diminuirRaioPara(3); 
			
			rede.distribuiRobosPartindoDe('C'); 
			esperado  = new Rede(new Scanner("A\n B\n  C\n   D\n   E\n  F\n G\n#")); 
			obtido = rede;
			assertEquals(esperado.imprimeRede(), obtido.imprimeRede());
			 
		}

		@Test(timeout = 1000)
		public final void test3() {  
			Rede rede = new Rede(new Scanner("A\n#"));
			rede.diminuirRaioPara(2);
			boolean isFalse = rede.distribuiRobosPartindoDe('B');
			assert(!isFalse);
		} 

		@Test(timeout = 1000)
		public final void test4() {  
			Rede rede = new Rede(new Scanner("A\n B\n C\n#")); 
			rede.diminuirRaioPara(0);   
			boolean isFalse = rede.distribuiRobosPartindoDe('B'); 
			assert(!isFalse);
		} 

		@Test(timeout = 1000)
		public final void test5() throws FileNotFoundException {  
		    Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo4")));
            Rede rede =  new Rede(sc);
            sc.close();
			rede.diminuirRaioPara(2); 
			rede.distribuiRobosPartindoDe('C'); 
			this.esperado = new Rede(
					new Scanner(
							"A\n B\n H\n  C\n   E\n    F\n     I\n     K\n    L\n   J\n  G\n#"));
			obtido = rede;
			assertEquals(esperado.imprimeRede(), obtido.imprimeRede());
		}  

}
