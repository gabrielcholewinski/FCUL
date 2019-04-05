package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RunSemana8 {


    public static void main (String[] args) throws NumberFormatException, IOException {
        Statistics stats = new Statistics ();

        List<Sample> a = inicio("data.txt");
        
//        double [] temperaturas = new double[a.size()];
//        
//        for (int i=0; i<a.size(); i++) {
//            temperaturas[i] = a.get(i).getTempertaure();
//        }
//
//        System.out.println("Temperatura:");
//        System.out.println("min " + stats.min(temperaturas));
//        System.out.println("mean " + stats.mean(temperaturas));
//        System.out.println("max " + stats.max(temperaturas));
//        System.out.println("stddev " + stats.stddev(temperaturas));
//        System.out.println("var " + stats.var(temperaturas));
//
//        System.out.println("");
//        System.out.println("");
//        

        int [] humidades = new int[a.size()];
        
        for (int i=0; i<a.size(); i++) {
            humidades[i] = a.get(i).getHumidity();
        }

        System.out.println("Humidade:");
        System.out.println("min " + stats.min(humidades));
        System.out.println("mean " + stats.mean(humidades));
        System.out.println("max " + stats.max(humidades));
        System.out.println("stddev " + stats.stddev(humidades));
        System.out.println("var " + stats.var(humidades));

    }

    /**
     * le e constroi samples;
     * @param in - o Buffered de onde se le os dados
     * @throws NumberFormatException 
     * @throws IOException
     */
    public static List <Sample> inicio(String fileName) throws NumberFormatException, IOException {

        BufferedReader in = new BufferedReader( new FileReader(fileName));

        String line;

        ArrayList<Sample> amostras = new ArrayList<>();

        while ((line = in.readLine()) != null) {
            String[] s = line.split(";");

            int year = Integer.parseInt(s[0]);
            int month = Integer.parseInt(s[1]);
            int day = Integer.parseInt(s[2]);
            int hour = Integer.parseInt(s[3]);
            int minute = Integer.parseInt(s[4]);
            double temperature = Double.parseDouble(s[5]);
            int humidity = Integer.parseInt(s[6]);

            Sample am = new Sample (year, month, day, hour, minute, temperature, humidity);
            amostras.add(am);
        }
        in.close();
        return amostras;
    }




}
