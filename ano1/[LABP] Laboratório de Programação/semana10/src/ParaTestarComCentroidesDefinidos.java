import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ParaTestarComCentroidesDefinidos {

    public static void main (String[] args) throws FileNotFoundException {

        /*
         * i         arg[i]             numero de clusters    rescaled   centroides iniciais    erros
         * 0        iris.csv            3                         f          1, 70, 130         <= 16	V 1
         * 1        breastCancer.csv    2                         f          1, 459             <= 26	V 27 
         * 2        wine.csv            3                         t          23, 99, 133        <= 6	X 39
         * 3        AeEs.csv            2                         f          15, 999            <= 89   V 89                            
         */
        
        //mudar o valor do args
        String arg = args[2];

        ArrayList<Integer> centroidesIniciais =  new ArrayList<Integer>();
        
        //mudar a posicao inicial dos centroides
        centroidesIniciais.add(23);
        centroidesIniciais.add(99);
        centroidesIniciais.add(133);

        
        //se for necessario reescalar os dados
        boolean rescaled = true;
        
        Scanner sc = new Scanner(new File(arg));

        //numero de features - a primeira linha do ficheiro
        String line = sc.nextLine();
        int nFeatures = Integer.parseInt(line);

        //leitura dos dados
        ArrayList<Element> data = readData(sc, nFeatures);

        sc.close();
        
        KMeanClustering best = tryKMeans(data, centroidesIniciais, 1000, rescaled);
        
        System.out.println(best.numWrongClassifications());
        
        PrintWriter out = new PrintWriter("resultados"+ arg +".txt");
        out.write(best.toString());

        out.close();

       System.out.println(best);

    }


    /**
     * Le os dados para uma ArrayLisy
     * @param sc - o scanner de leitura
     * @param nFeatures o numero de features
     * @return uma lista de todos os dados
     * @requires sc != null nFeatures > 0
     */
    private static ArrayList<Element> readData (Scanner sc, int nFeatures) {
        String line;
        ArrayList<Element> dados = new ArrayList<Element>();

        
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] s = line.split(";");
            String nome = s[0];
            double[] features = new double[nFeatures];
            for(int i = 1; i <= features.length; i++) {
                features[i-1] =Double.parseDouble(s[i]);
            }
            int classificacao = Integer.parseInt(s[s.length - 1]);
            Element element = new Element(nome, features, "not given", classificacao);
            dados.add(element);
        }
        return dados;
    }


    //def trykmeans(examples, exampleType, numClusters, numTrials, verbose = False):
    //"""Calls kmeans numTrials times and returns the result with the lowest dissimilarity"""
    //best = kmeans(examples, exampleType, numClusters, verbose) minDissimilarity = dissimilarity(best)
    //for trial in range(1, numTrials):
    //clusters = kmeans(examples, exampleType, numClusters, verbose) currDissimilarity = dissimilarity(clusters)
    //if currDissimilarity < minDissimilarity:
    //best = clusters
    //minDissimilarity = currDissimilarity return best
    
    /**
     * Determina um conjunto de cluster com menor a similaridade de um conjunto de tentativas
     * @param lista - a lista com os dados
     * @param centroides - a lista de centroides iniciais
     * @param trials - o numero de tentativas a efectuar 
     * @param rescaled - diz se e preciso reescalar os dados ou nao
     * @return A melhor KMean obtida
     * @requires lista, centroides != null trials > 0
     */
    private static KMeanClustering tryKMeans (ArrayList<Element> lista, ArrayList<Integer> centroides, 
            int trials, boolean rescaled) {
        @SuppressWarnings("unchecked")
        
        ArrayList<Element> data = (ArrayList<Element>) lista.clone();
        if(rescaled) {
            data = KMeanClustering.rescale(lista);
        }
        
        ArrayList<Element> centroidesIniciais = new ArrayList<Element>();
        
        for (Integer i : centroides) {
            centroidesIniciais.add(data.get(i-1));
        }
        
        KMeanClustering best = new KMeanClustering(data, centroidesIniciais);
        
        double min = best.dissimilarity();
        
        for (int i = 0; i < trials; i++) {
            KMeanClustering newCluster = new KMeanClustering(lista, centroidesIniciais.size());
            if(newCluster.dissimilarity() < min) {
                min = newCluster.dissimilarity();
                best = newCluster;
            }
        }
       
        return best;
    }

}
