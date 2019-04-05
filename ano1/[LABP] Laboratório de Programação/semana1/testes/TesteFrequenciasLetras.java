package testes;

import tut.TUTEDECC;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class TesteFrequenciasLetras {


    @Test
    public void testeFrequenciaLetrasPorAlfabetica () throws IOException {

        String esperado = 	"a 0.037037037037037035\n" +
                "b 0.037037037037037035\n" +
                "c 0.037037037037037035\n" +
                "d 0.037037037037037035\n" +
                "e 0.037037037037037035\n" +
                "f 0.037037037037037035\n" +
                "g 0.037037037037037035\n" +
                "h 0.037037037037037035\n" +
                "i 0.037037037037037035\n" +
                "j 0.037037037037037035\n" +
                "k 0.037037037037037035\n" +
                "l 0.037037037037037035\n" +
                "m 0.037037037037037035\n" +
                "n 0.037037037037037035\n" +
                "o 0.037037037037037035\n" +
                "p 0.037037037037037035\n" +
                "q 0.037037037037037035\n" +
                "r 0.037037037037037035\n" +
                "s 0.07407407407407407\n" +
                "t 0.037037037037037035\n" +
                "u 0.037037037037037035\n" +
                "v 0.037037037037037035\n" +
                "w 0.037037037037037035\n" +
                "x 0.037037037037037035\n" +
                "y 0.037037037037037035\n" +
                "z 0.037037037037037035\n";


        String texto = "abcdefghijklmnopqrsstuvwxyz";

        PrintWriter in = new PrintWriter("input.txt");
        in.write(texto);
        in.close();
       
        TUTEDECC.frequenciasLetras("input.txt", "output.txt", 0);

        String obtida = new String(Files.readAllBytes(Paths.get("output.txt")), StandardCharsets.UTF_8);

        assertEquals(esperado, obtida);


    }


    @Test
    public void testeFrequenciaLetrasPorOcorrencias () throws IOException {

        String esperado =   "s 0.07407407407407407\n" +
                "a 0.037037037037037035\n" +
                "b 0.037037037037037035\n" +
                "c 0.037037037037037035\n" +
                "d 0.037037037037037035\n" +
                "e 0.037037037037037035\n" +
                "f 0.037037037037037035\n" +
                "g 0.037037037037037035\n" +
                "h 0.037037037037037035\n" +
                "i 0.037037037037037035\n" +
                "j 0.037037037037037035\n" +
                "k 0.037037037037037035\n" +
                "l 0.037037037037037035\n" +
                "m 0.037037037037037035\n" +
                "n 0.037037037037037035\n" +
                "o 0.037037037037037035\n" +
                "p 0.037037037037037035\n" +
                "q 0.037037037037037035\n" +
                "r 0.037037037037037035\n" +
                "t 0.037037037037037035\n" +
                "u 0.037037037037037035\n" +
                "v 0.037037037037037035\n" +
                "w 0.037037037037037035\n" +
                "x 0.037037037037037035\n" +
                "y 0.037037037037037035\n" +
                "z 0.037037037037037035\n";


        String texto = "abcdefghijklmnopqrsstuvwxyz";
        PrintWriter in = new PrintWriter("input.txt");
        in.write(texto);
        in.close();

        TUTEDECC.frequenciasLetras("input.txt", "output.txt", 1);

        String obtida = new String(Files.readAllBytes(Paths.get("output.txt")), StandardCharsets.UTF_8);


        assertEquals(esperado, obtida);

    }
}
