package testes;


import src.*;

import static org.junit.Assert.assertEquals;


import org.junit.Test; 
public class PrencherRedeTest { 

    private Rede esperado;
    private Rede obtido;  


    @Test(timeout = 1000)
    public final void test1() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo1")));
        Rede rede =  new Rede(sc);
        sc.close();
        rede.diminuirRaioPara(2); 
        rede.preencherRede();
        this.esperado = 
                new Rede(new Scanner("A\n B\n  C\n  G\n H\n  E\n  F\n#")); 
        this.obtido = rede;    
        assertEquals(esperado.imprimeRede(), obtido.imprimeRede());
    }  

    @Test(timeout = 1000)
    public final void test3() {  
        Rede rede = new Rede(new Scanner("A\n#"));
        rede.diminuirRaioPara(0);
        rede.preencherRede();
        this.esperado = new Rede(new Scanner("A\n#"));
        this.obtido = rede; 
        assertEquals(esperado, obtido);
    } 

    @Test(timeout = 1000)
    public final void test4() {  
        Rede rede = new Rede(new Scanner("A\n B\n C\n#")); 
        rede.diminuirRaioPara(0); 
        rede.preencherRede();
        this.esperado = new Rede(new Scanner("A\n\n#"));
        obtido = rede;
        assertEquals(esperado.imprimeRede(), obtido.imprimeRede());
    } 

    @Test(timeout = 1000)
    public final void test5() throws FileNotFoundException {  
        Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo2")));
        Rede rede =  new Rede(sc);
        sc.close();
        rede.diminuirRaioPara(2); 
        rede.preencherRede();
        this.esperado = new Rede(new Scanner(
                "A\n B\n  C\n  G\n H\n  J\n  E\n#"));
        obtido = rede;
        assertEquals(esperado.imprimeRede(), obtido.imprimeRede());
    } 

    @Test(timeout = 1000)
    public final void test6() throws FileNotFoundException {  
        Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo3")));
        Rede rede =  new Rede(sc);
        sc.close();
        rede.diminuirRaioPara(3);  
        rede.preencherRede();
        this.esperado = new Rede(new Scanner(
                "A\n B\n  J\n   I\n   *\n  *\n  *\n  *\n  H\n   C\n    *\n    *\n   G\n    E\n    F\n#"));
        obtido = rede;
        assertEquals(esperado.imprimeRede(), obtido.imprimeRede());
    }

    @Test(timeout = 1000)
    public final void test7() throws FileNotFoundException {  
        Scanner sc = new Scanner(new BufferedReader(new FileReader("exemplo4")));
        Rede rede =  new Rede(sc);
        sc.close();
        rede.diminuirRaioPara(3); 
        rede.preencherRede();

        this.esperado = new Rede(new Scanner(
                "A\n B\n  J\n   L\n   I\n  K\n  *\n  *\n  H\n   C\n    *\n    *\n   G\n    E\n    F\n#"));
        obtido = rede; 
        assertEquals(esperado.imprimeRede(), obtido.imprimeRede());
    }  


}
