package testes;

import tut.TUTEDECC;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class TesteCapitalizar {

    @Test
    public void capitalizaTipoMenos1 () throws IOException {

        String esperado = "abcdefghijklmnop" +"\n" + "qrst. uvwxyz" + "\n";
        
        String texto = "ABCDEFGhijklmnop" + "\n" +"Qrst. uvwxyz";
        
        PrintWriter in = new PrintWriter("input.txt");
        in.write(texto);
        in.close();

        TUTEDECC.capitalizar("input.txt", -1, "output.txt");

        String obtida = new String(Files.readAllBytes(Paths.get("output.txt")), StandardCharsets.UTF_8);
       
        assertEquals(esperado, obtida);

        in.close();
        

    }
    
    @Test
    public void capitalizaTipo1 () throws IOException {

        String esperado = "ABCDEFGHIJKLMNOP" +"\n" + "QRS. TUVXWYZ" + "\n";
        
        String texto = "ABCDEFGhijklmnop" + "\n" +"Qrs. tuvXwyz";
        
        PrintWriter in = new PrintWriter("input.txt");
        in.write(texto);
        in.close();

        TUTEDECC.capitalizar("input.txt", 1, "output.txt");

        String obtida = new String(Files.readAllBytes(Paths.get("output.txt")), StandardCharsets.UTF_8);
        
        in.close();
        assertEquals(esperado,obtida);

    }
    
    @Test
    public void capitalizaTipo0 () throws IOException {

        String esperado = "Abcdefgh.ijklmnop" +"\n" + "Qrs. Tuvx.wyz" + "\n";
        
        String texto = "ABCDEFGh.ijklmnop" + "\n" +"Qrs. tuvX.Wyz";
        PrintWriter in = new PrintWriter("input.txt");
        in.write(texto);
        in.close();

       
        TUTEDECC.capitalizar("input.txt", 0, "output.txt");
       
        String obtida = new String(Files.readAllBytes(Paths.get("output.txt")), StandardCharsets.UTF_8);

        in.close();
        assertEquals(esperado,obtida);

    }
    
    @Test
    public void capitalizaTipoDiferente () throws IOException {

        
        String texto = "ABCDEFGhijklmnop" + "\n" +"QrstuvXwyz\n";
        String esperado = texto;
        
        
        PrintWriter in = new PrintWriter("input.txt");
        in.write(texto);
        in.close();

        TUTEDECC.capitalizar("input.txt", 2, "output.txt");
        
        String obtida = new String(Files.readAllBytes(Paths.get("output.txt")), StandardCharsets.UTF_8);

        in.close();
        assertEquals(esperado,obtida);

    }


}
