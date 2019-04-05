package testes;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import tut.TUTEDECC;

public class TesteRotacao {


    @Test
    public void test () throws IOException {

        String esperado = "lmnopqrstuvwxyzabcdefghijk" + "\n";
        String texto = "abcdefghijklmnopqrstuvwxyz" + "\n";

        PrintWriter in = new PrintWriter("input.txt");
        in.write(texto);
        in.close();


        TUTEDECC.rotacao("input.txt", "output.txt", 11);

        String obtida = new String(Files.readAllBytes(Paths.get("output.txt")), 
                StandardCharsets.UTF_8);

        assertEquals(esperado, obtida);

    }
}
