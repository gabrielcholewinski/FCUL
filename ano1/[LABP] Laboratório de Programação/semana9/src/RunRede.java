package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class RunRede {

    /**
     * @param args
     */ 
    public static void main(String[] args) {  

        try(Scanner tec = new Scanner(System.in)){
            System.out.print("Introduza o nome do ficheiro com a arvore: ");
            String nomeficheiro = tec.nextLine();
            System.out.println();
            Scanner fich = new Scanner(new File(nomeficheiro));

            Rede rede = new Rede(fich);
            System.out.println("Da leitura da rede obteve-se:");
            System.out.println(rede.imprimeRede());
            System.out.println();
            System.out.println("Cuja representacao em forma de parentesis eh:");
            System.out.println(rede.imprimeRedeParentesis()); 

            System.out.println();
            
            int raio;
            do {
                System.out.print("Diminuir raio de exploracao para: ");
                raio = tec.nextInt();
                if (raio < rede.raio())
                    rede.diminuirRaioPara(raio);
                else
                    System.out.println("Raio maior ou igual ao existente!");    
            } while (raio != rede.raio());

            System.out.println();
            System.out.println("A rede de exploracao ficou com a configuracao:");
            System.out.println(rede.imprimeRedeParentesis());

            System.out.println();
            if(rede.temExploradoresDeReserva()) {
                System.out.println("Exploradores de reserva: " + rede.devolveGrupoReserva());    
            } else {
                System.out.println("Sem exploradores de reserva.");
            }
            
            System.out.println();
            System.out.println("Completa-se a rede de exploracao com os robos de reserva e obtem-se:\n");
            if(rede.temExploradoresDeReserva()) {
                rede.preencherRede();
                //System.out.println(rede.devolveRede());
                System.out.println(rede.imprimeRedeParentesis());
            }

            System.out.println();
            if(rede.temExploradoresDeReserva()) {
                System.out.println("Exploradores de reserva: " + rede.devolveGrupoReserva());    
            } else {
                System.out.println("Sem exploradores de reserva.");
            }

            if (rede.temExploradoresDeReserva()) {
                do {
                    System.out.print("Preencher Partindo De: ");
                    String n = tec.next();
                    if (!rede.distribuiRobosPartindoDe( n.charAt(0) )) {
                        System.out.println("No incorrecto: preencher nao efectuado.");
                    } else {
                        System.out.println(rede.imprimeRede());
                        System.out.println(rede.imprimeRedeParentesis());
                    }

                } while (rede.temExploradoresDeReserva());
            }
        }catch (FileNotFoundException e) {
            System.out.println("ficheiro inexistente");
        }


    } 

}
