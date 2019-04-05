package testes;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import tut.TUTEDECC;

public class TesteFrequenciasPadroes {


    @Test
    public void test () throws IOException {

        String esperado = 	"ab ocorre na linha 1, 2 vez(es)\n" +
                "stuv ocorre na linha 1, 1 vez(es)\n" +
                "ab ocorre na linha 2, 1 vez(es)\n" +
                "\n" + 
                "Numero de ocorrencias de cada padrao:\n"+
                "ab 3\n" +
                "stuv 1\n" +
                "ac 0\n";


        String texto = "abcdefghijklmnopqrsstuvwxyzab + \n ab";

        PrintWriter in = new PrintWriter("input.txt");
        in.write(texto);
        in.close();
        
        PrintWriter padroes = new PrintWriter("padroesTeste.txt");
        padroes.write("ab stuv ac");
        padroes.close();

        TUTEDECC.detectaPadrao("input.txt", "padroesTeste.txt", "output.txt");

        String obtida = new String(Files.readAllBytes(Paths.get("output.txt")), 
                StandardCharsets.UTF_8);


        assertEquals(esperado, obtida);

    }
}
