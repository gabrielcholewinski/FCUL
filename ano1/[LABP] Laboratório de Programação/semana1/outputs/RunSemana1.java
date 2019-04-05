package outputs;

import tut.TUTEDECC;

import java.io.IOException;

/**
 * Classe de teste do projecto 1 referente ah TUTEDECC.
 * @author Team LabP
 */
public class RunSemana1 {

    public static void main (String[] args) throws IOException {

        // testar capitalizar -1
        TUTEDECC.capitalizar(args[0], -1, "capitalizar-1.txt");


        // testar capitalizar 0
        TUTEDECC.capitalizar(args[0], 0, "capitalizar0.txt");

        // testar capitalizar 1
        TUTEDECC.capitalizar(args[0], 1, "capitalizar1.txt");


        // testar retirar caracteres
        TUTEDECC.retiraCaracteres(args[0], args[1], "retirarCaracteres.txt");

        // testar troca de numeros
        TUTEDECC.numerosPorLetras(args[0],"numerosPorLetras.txt");

        // testar rotacao
        TUTEDECC.rotacao(args[0], "rotacao.txt", 6);


        //testar ocorencias tipo 0
        TUTEDECC.frequenciasLetras(args[0], "frequenciaDeLetras0.txt", 0);

        //testar ocorencias tipo 1
        TUTEDECC.frequenciasLetras(args[0], "frequenciaDeLetras1.txt", 1);

        //testar frequencias padrao
        TUTEDECC.detectaPadrao(args[0], args[2],"frequenciaDePadroes.txt" );


    }
}