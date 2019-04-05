package src;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class RunSemana4 {

    public static final int NUM_PISTAS = 6;

    public static final int COMP_PISTAS = 2500;
    
    public static void main (String[] args) throws IOException {

        
        
        //inicio da garagem
        Garagem taxisAntero = new Garagem(NUM_PISTAS, COMP_PISTAS);
        leCarros(args[0], taxisAntero);
        
        System.out.println("Carros registados: " +  taxisAntero.obtemNumeroCarrosRegistados());
        System.out.println("------------------------");
        
        //estado da garagem
        System.out.println(taxisAntero.toString());
        System.out.println("------------------------");
        
        //requisicao de alguns servicos
        int taxi1 = taxisAntero.escolhaPorNumKm();

        //um dos carros requisitados
        System.out.println("Carro escolhido:" + taxi1);
        System.out.println("------------------------");
        
        int taxi2 = taxisAntero.escolhaPorNumRegisto();
        int taxi3 = taxisAntero.escolhaPorNumKm();
        int taxi4 = taxisAntero.escolhaPorNumKm();
        System.out.println("Carro escolhidos:" + taxi1 + " " + taxi2 + " " + taxi3 + " " + taxi4);
        System.out.println("------------------------");
        

        //carros em servico
        int[] taxisEmServico =  taxisAntero.carrosEmServico();
        for (int i : taxisEmServico) {
            System.out.print(i+ " ");
        }
        System.out.println();
        
        
        System.out.println("------------------------");
        
        //estado da garagem
        System.out.println(taxisAntero.toString());

        System.out.println("------------------------");
        
        taxisAntero.estacionaCarro(taxi1, 2000);
        taxisAntero.estacionaCarro(taxi2, 200);
        
        //mais servico
        taxi2 = taxisAntero.escolhaPorNumRegisto();
        taxi1 = taxisAntero.escolhaPorNumRegisto();
       
        taxisAntero.estacionaCarro(taxi2, 2000);
        taxisAntero.estacionaCarro(taxi4, 150);
        taxisAntero.estacionaCarro(taxi3, 2000);
        taxisAntero.estacionaCarro(taxi1, 100);

        //estado da garagem
        System.out.println(taxisAntero.toString());

        System.out.println("------------------------");
        
        //reorganizar a garagem
        //taxisAntero.reorganiza();
        
        //estado da garagem
        System.out.println(taxisAntero.toString());

    }


    /**
     * le e distribui os carros de acordo com o seu tamanho;
     * @param in - o Buffered de onde se le os dados
     * @throws IOException
     */
    public static void leCarros(String in, Garagem taxisAntero) throws IOException{

        try(Scanner sc = new Scanner(new File(in))){
            String s;
            while(sc.hasNextLine()){
                s = sc.nextLine();
                String[] linha = s.split(" ");
                taxisAntero.registaNovoCarro(Integer.parseInt(linha[0]), Integer.parseInt(linha[1]));
            }
        }
    }
}
