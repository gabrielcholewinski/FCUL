package testes;

import src.Garagem;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class GaragemToStringTest {

    private Garagem garagem;

    private String esperado;
    private String obtido;

    @Before
    public void setUp () throws Exception {
        this.garagem = new Garagem(3, 12000);

        garagem.registaNovoCarro(110, 6000);
        garagem.registaNovoCarro(210, 5000);
        garagem.registaNovoCarro(310, 4000);
        garagem.registaNovoCarro(410, 3000);
        garagem.registaNovoCarro(510, 2000);
        garagem.registaNovoCarro(610, 1000);

    }

    @Test
    public void tudoParado () throws IOException {

        

        esperado = "A garagem tem 3 pistas com a seguinte configuracao:\n" + 
                "Pista 0: \n" + 
                "11480 cm de espaco livre. \n" + 
                "[Carro 4: (3000 km, 410 cm, Revisao: ok), Carro 1: (6000 km, 110 cm, Revisao: ok)]\n" + 
                "Pista 1: \n" + 
                "11280 cm de espaco livre. \n" + 
                "[Carro 5: (2000 km, 510 cm, Revisao: ok), Carro 2: (5000 km, 210 cm, Revisao: ok)]\n" + 
                "Pista 2: \n" + 
                "11080 cm de espaco livre. \n" + 
                "[Carro 6: (1000 km, 610 cm, Revisao: ok), Carro 3: (4000 km, 310 cm, Revisao: ok)]\n" + 
                "estao em servico: 0 carros";

        obtido = garagem.toString();

        assertEquals(esperado, obtido);
    }


    @Test
    public void umEmMovimento () throws IOException {
        
        garagem.escolhaPorNumKm();
        
        esperado = "A garagem tem 3 pistas com a seguinte configuracao:\n" + 
                "Pista 0: \n" + 
                "11480 cm de espaco livre. \n" + 
                "[Carro 4: (3000 km, 410 cm, Revisao: ok), Carro 1: (6000 km, 110 cm, Revisao: ok)]\n" + 
                "Pista 1: \n" + 
                "11280 cm de espaco livre. \n" + 
                "[Carro 5: (2000 km, 510 cm, Revisao: ok), Carro 2: (5000 km, 210 cm, Revisao: ok)]\n" + 
                "Pista 2: \n" + 
                "11690 cm de espaco livre. \n" + 
                "[Carro 3: (4000 km, 310 cm, Revisao: ok)]\n" + 
                "estao em servico: 1 carros";
        
        obtido = garagem.toString();
        
        assertEquals(esperado, obtido);
    }
        
    
        @Test(timeout = 1000)
        public void doisEmMovimento () throws IOException {
    
            garagem.escolhaPorNumKm();
            garagem.escolhaPorNumKm();
            
            esperado = "A garagem tem 3 pistas com a seguinte configuracao:\n" + 
                    "Pista 0: \n" + 
                    "11480 cm de espaco livre. \n" + 
                    "[Carro 4: (3000 km, 410 cm, Revisao: ok), Carro 1: (6000 km, 110 cm, Revisao: ok)]\n" + 
                    "Pista 1: \n" + 
                    "11790 cm de espaco livre. \n" + 
                    "[Carro 2: (5000 km, 210 cm, Revisao: ok)]\n" + 
                    "Pista 2: \n" + 
                    "11690 cm de espaco livre. \n" + 
                    "[Carro 3: (4000 km, 310 cm, Revisao: ok)]\n" + 
                    "estao em servico: 2 carros";
            
            obtido = garagem.toString();
            
            assertEquals(esperado, obtido);
        }
        
        
        @Test(timeout = 1000)
        public void doisEmMovimentoEUmRegressa () throws IOException {
            
            garagem.escolhaPorNumKm();
            garagem.escolhaPorNumKm();
            
            garagem.estacionaCarro(5,1000);
            
            esperado = "A garagem tem 3 pistas com a seguinte configuracao:\n" + 
                    "Pista 0: \n" + 
                    "11480 cm de espaco livre. \n" + 
                    "[Carro 4: (3000 km, 410 cm, Revisao: ok), Carro 1: (6000 km, 110 cm, Revisao: ok)]\n" + 
                    "Pista 1: \n" + 
                    "11280 cm de espaco livre. \n" + 
                    "[Carro 5: (3000 km, 510 cm, Revisao: ok), Carro 2: (5000 km, 210 cm, Revisao: ok)]\n" + 
                    "Pista 2: \n" + 
                    "11690 cm de espaco livre. \n" + 
                    "[Carro 3: (4000 km, 310 cm, Revisao: ok)]\n" + 
                    "estao em servico: 1 carros";
            
            obtido = garagem.toString();
            
            assertEquals(esperado, obtido);
        }

}
