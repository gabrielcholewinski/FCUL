package testes;

import tut.TUTEDECC;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class TesteRetiraCarateres {

    @Test
    public void retiraMaisQueUma () throws IOException {

        String esperado = "abcdefghklmnopqsrstuvwxyz" + "\n";
        
        String texto = "abcdefghijklmnopqsrstuvwxyz";
        
        PrintWriter in = new PrintWriter("input.txt");
        in.write(texto);
        in.close();
        
        PrintWriter letras = new PrintWriter("letras.txt");
        letras.write("ij");
        letras.close();
        

        TUTEDECC.retiraCaracteres("input.txt", "letras.txt", "output.txt");

        String obtida = new String(Files.readAllBytes(Paths.get("output.txt")), 
                StandardCharsets.UTF_8);

        assertEquals(esperado, obtida);

    }
    
    @Test
    public void retiraCaseSensitive () throws IOException {

        String esperado = "bcdefghijklmnopqsrstuvwxyzA" + "\n";
        
        String texto = "abcdefghijklmnopqsrstuvwxyzA";
        PrintWriter in = new PrintWriter("input.txt");
        in.write(texto);
        in.close();
        
        PrintWriter letras = new PrintWriter("letras.txt");
        letras.write("a");
        letras.close();
        

        TUTEDECC.retiraCaracteres("input.txt", "letras.txt", "output.txt");

        String obtida = new String(Files.readAllBytes(Paths.get("output.txt")), 
                StandardCharsets.UTF_8);
        
        assertEquals(esperado, obtida);
    }
    
    @Test
    public void retiraEspacos () throws IOException {

        String esperado = "bcdefghijklmnopqsrstuvwxyz" + "\n";
        
        String texto = "abcd efgh ijk lmn opq srs tuv wx yz";
        PrintWriter in = new PrintWriter("input.txt");
        in.write(texto);
        in.close();
        
        PrintWriter letras = new PrintWriter("letras.txt");
        letras.write("a ");
        letras.close();
        

        TUTEDECC.retiraCaracteres("input.txt", "letras.txt", "output.txt");

        String obtida = new String(Files.readAllBytes(Paths.get("output.txt")), 
                StandardCharsets.UTF_8);
        
        assertEquals(esperado, obtida);

    }

}
