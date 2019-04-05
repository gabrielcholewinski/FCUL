import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class KMeanClusteringTest {

    private KMeanClustering kmean;
    private ArrayList<Element> dados;
    @Before
    public void setUp() throws Exception {
        String data = ""
                + "d1;0;0;0;0;0\n"
                + "d2;1;0;0;0;0\n"
                + "d3;0;1;0;0;0\n"
                + "d4;0;0;1;0;0\n"
                + "d5;0;0;0;1;0\n"
                + "e1;5;5;5;5;1\n"
                + "e2;6;5;5;5;1\n"
                + "e3;5;6;5;5;1\n"
                + "e4;5;5;6;5;1\n"
                + "e5;5;5;5;6;0\n";


        Scanner sc = new Scanner(data);
        dados = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] s = line.split(";");
            String nome = s[0];
            double[] features = new double[4];
            for(int i = 1; i <= features.length; i++) {
                features[i-1] =Double.parseDouble(s[i]);
            }
            int classificacao = Integer.parseInt(s[s.length - 1]);
            Element element = new Element(nome, features, "not given", classificacao);
            dados.add(element);
        }
        sc.close();
        ArrayList<Element> centroides = new ArrayList<>();
        centroides.add(dados.get(0));
        centroides.add(dados.get(5));
        kmean = new KMeanClustering(dados, centroides);
    }


    private void createElements(String data, ArrayList<Element> dados2) {
        Scanner sc = new Scanner(data);
        //dados2 = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] s = line.split(";");
            String nome = s[0];
            double[] features = new double[4];
            for(int i = 1; i <= features.length; i++) {
                features[i-1] = Double.parseDouble(s[i]);
            }
            int classificacao = Integer.parseInt(s[s.length - 1]);
            Element element = new Element(nome, features, "not given", classificacao);
            dados2.add(element);
        }
        sc.close();
    }


    @Test
    public final void testRescale() {
        ArrayList<Element> actual = KMeanClustering.rescale(dados);

        String data = ""
                + "d1;-1.066435882470042; -1.066435882470042; -1.066435882470042; -1.066435882470042;0\n"
                + "d2;-0.6714596297033598; -1.066435882470042; -1.066435882470042; -1.066435882470042;0\n"
                + "d3;-1.066435882470042; -0.6714596297033598; -1.066435882470042; -1.066435882470042;0\n"
                + "d4;-1.066435882470042; -1.066435882470042; -0.6714596297033598; -1.066435882470042;0\n"
                + "d5;-1.066435882470042; -1.066435882470042; -1.066435882470042; -0.6714596297033598;0\n"
                + "e1;0.9084453813633689; 0.9084453813633689; 0.9084453813633689; 0.9084453813633689;1\n"
                + "e2;1.303421634130051; 0.9084453813633689; 0.9084453813633689; 0.9084453813633689;1\n"
                + "e3;0.9084453813633689; 1.303421634130051; 0.9084453813633689; 0.9084453813633689;1\n"
                + "e4;0.9084453813633689; 0.9084453813633689; 1.303421634130051; 0.9084453813633689;1\n"
                + "e5;0.9084453813633689; 0.9084453813633689; 0.9084453813633689; 1.303421634130051;0\n";
        
        Scanner sc = new Scanner(data);
        ArrayList<Element> expected = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] s = line.split(";");
            String nome = s[0];
            double[] features = new double[4];
            for(int i = 1; i <= features.length; i++) {
                features[i-1] =Double.parseDouble(s[i]);
            }
            int classificacao = Integer.parseInt(s[s.length - 1]);
            Element element = new Element(nome, features, "not given", classificacao);
            expected.add(element);
        }
        sc.close();
        
        
        createElements(data, new ArrayList<Element>());

        assertEquals(expected, actual);
    }

    @Test
    public final void testToString() {
        String expected = "Clusters Produced:  \n"+
                "Cluster 0: \n"+
                "Centroid 0 features=[0.2, 0.2, 0.2, 0.2] label = 0\n"+
                "    d1 0\n"+
                "    d2 0\n"+
                "    d3 0\n"+
                "    d4 0\n"+
                "    d5 0\n"+
                "Cluster 1: \n"+
                "Centroid 1 features=[5.2, 5.2, 5.2, 5.2] label = 1\n"+
                "    e1 1\n"+
                "    e2 1\n"+
                "    e3 1\n"+
                "    e4 1\n"+
                "    e5 0\n";
        String actual = kmean.toString();
        assertEquals(expected, actual);
    }

    @Test
    public final void testDissimilarity() {
        double expected = 3.577708;
        double actual = kmean.dissimilarity();
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public final void testNumWrongClassifications() {
        int expected = 1;
        int actual = kmean.numWrongClassifications();
        assertEquals(expected, actual);
    }

}
