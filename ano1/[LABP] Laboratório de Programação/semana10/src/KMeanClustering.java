import java.util.ArrayList;
import java.util.Random;

public class KMeanClustering {


	public static final int ITERATIONS = 100; //

	public static final double DELTA = 0.0001;

	private Cluster[] clusterPack;

	/**
	 * Cria uma instancia de KMeanClustering agrupando
	 * os elementos de uma dada lista em cluster
	 * @param lista - o conjunto de elementos
	 * @param centroidesIniciais a lista com os centroides iniciais
	 * @requires lista != null e todos os seus elementos nao null
	 *          centroidesIniciais != null 
	 */										//elementos do cluster			/centroid do cluster+nomeCluster
	public KMeanClustering(ArrayList<Element> lista, ArrayList<Element> centroidesIniciais) {
		clusterPack = new Cluster[centroidesIniciais.size()]; //final

		for(int i=0 ; i<centroidesIniciais.size() ; i++) { //cria cluster
			Element e = new Element(Integer.toString(i), 
					centroidesIniciais.get(i).getFeatures(), 
						centroidesIniciais.get(i).getLabel(), 
							centroidesIniciais.get(i).getType());
			clusterPack[i] = new Cluster(e, e.getName());
		}

		clusterPack = atribuiElement(clusterPack.clone(), lista); //colocar os elementos mais pertos do centroid do cluster x, no cluster x

		atualizaCentroid(clusterPack.clone(), lista);

	}

	/**
	 * atribui elementos ao cluster
	 */
	private Cluster[] atribuiElement(Cluster[] cluster, ArrayList<Element> lista) {
		Cluster[] result = cluster.clone();

		for(int i=0 ; i<lista.size() ; i++) {
			int index = 0;
			double aux = cluster[0].getCentroid().distance(lista.get(i));
			for(int j=0 ; j < cluster.length ; j++) {
				if(cluster[j].getCentroid().distance(lista.get(i)) <= aux) {
					aux = cluster[j].getCentroid().distance(lista.get(i));
					index = j;
				}

			}
			result[index].add(lista.get(i));

		}
		return result;

	}
	/**
	 * para atualizar os centroides, eh supostos correr
	 */
	private void atualizaCentroid(Cluster[] cluster, ArrayList<Element> lista) {
		int c = 0;
		while(c < ITERATIONS) {
			c++;
			int count = 0;
			Cluster[] aux = cluster.clone();
			
			for(int i=0 ; i<clusterPack.length ; i++) {
				Element e = clusterPack[i].getCentroid();
				clusterPack[i].updateCentroid();
				aux[i].setCentroid(clusterPack[i].getCentroid());
				if(e.distance(aux[i].getCentroid()) <= DELTA) {
					count++;
				}
			}
			if(count == cluster.length || c == ITERATIONS) {
				return;
			}
		}
	}

	/**
	 * Inicializa uma instancia de KMeanClustering agrupando
	 * os elementos de uma dada lista em cluster com centroides escolhidos aleatoriamente
	 * Nao se esqueca de alterar a label do cluster/centroide de acordo com a maioria dos seus elementos
	 * @param lista - o conjunto de elementos
	 * @param numClers o numero de centroides iniciais
	 * @requires lista != null e todos os seus elementos nao null
	 *          numCluster > 0 
	 */          
	public KMeanClustering(ArrayList<Element> lista, int numClusters) {
		this(lista, newCluster(lista, numClusters));
	}

	private static ArrayList<Element> newCluster(ArrayList<Element> lista, int numClusters) {
		ArrayList<Element> result = new ArrayList<>();
		Random gr = new Random();
		
		for(int i=0 ; i<numClusters ; i++) {
			int n = gr.nextInt(lista.size());
			result.add(lista.get(n));
		}
		return result;
	}

	/**
	 * Devolve uma arrayList com os elementos da lista escalados para uma normal de media proximo de 0 e 
	 * desvio padrao proximo de 1.
	 * @param lista - a lista de elementos a renormalizar
	 * @return uma lista com elementos renormalizados para uma media aproximada de 0 e 
	 * desvio padrão de aproximada 1. (x --> (x - media)/desvio
	 */
	public static ArrayList<Element> rescale (ArrayList<Element> lista) {
		ArrayList<Element> result = new ArrayList<>();

		double[] mediaLista = calculoMedia(lista);

		double[] desvioPadrao = calculoStdDev(lista, mediaLista);

		for(int i=0 ; i<lista.size() ; i++) {
			result.add( new Element(lista.get(i).getName(),
					normaliza(lista.get(i).getFeatures(), mediaLista, desvioPadrao),
					"not given",
						lista.get(i).getType()) );
		}

		return result;
	}


	private static double[] normaliza(double[] features, double[] mediaLista, double[] desvioPadrao) {
		double[] result = new double[features.length];

		for(int i=0 ; i<result.length ; i++)
			result[i] = (features[i] - mediaLista[i]) / desvioPadrao[i];
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder("Clusters Produced:  \n");
		for(int i=0 ; i<clusterPack.length ; i++) {
			sb.append("Cluster "+clusterPack[i].getLabel()+": \n");
			sb.append(clusterPack[i].getCentroid().getName()+" features=[");
			for(int j=0 ; j<clusterPack[i].getCentroid().getFeatures().length ; j++) {
				sb.append(clusterPack[i].getCentroid().getFeatures()[j]+", ");
			}
			sb.delete(sb.length()-2, sb.length());
			sb.append("] label = "+clusterPack[i].getLabel()+"\n");
			for(int k=0 ; k<clusterPack[i].getElements().size() ; k++) {
				sb.append("    "+clusterPack[i].getElements().get(k).getName() +" "+clusterPack[i].getElements().get(k).getType()+"\n");
			}
		}
		return sb.toString();
	}


	/**
	 * Calcula a dissimilaridade dos grupos
	 * @return a soma das variancias dos cluster deste KMeanClustering
	 */
	public double dissimilarity() {
		double result = 0.0;
		for(int i=0 ; i<clusterPack.length ; i++)
			result += clusterPack[i].variance();
		return result;
	}


	/**
	 * Determina com base na pre-classificacao o numero de elementos
	 * colocados nos clusters errados
	 * @return o numero de erros do cluster
	 */
	public int numWrongClassifications(){
		int result = 0;
		
		for(int i=0 ; i<clusterPack.length ; i++) {
			int aux = Integer.parseInt(clusterPack[i].getLabel());
			for(int j=0 ; j<clusterPack[i].getElements().size() ; j++) {
				if(aux != clusterPack[i].getElements().get(j).getType()) {
					result++;
				}
			}
		}
		
		return result;
	}



	/**
	 * Calcula o desvio padrao de um conjunto de dados
	 * @param lista - o conjunto de dados
	 * @param res - onde guarda os valores do desvio padrao de cada ma das componentes
	 * @return
	 */
	private static double[] calculoStdDev (ArrayList<Element> lista, double[] medias) {

		double[] featuresStdDev = new double [lista.get(0).getFeaturesSize()];

		for (Element e: lista) {
			double[] elemfeat = e.getFeatures();
			for (int i = 0; i < featuresStdDev.length; i++) {
				featuresStdDev[i] += Math.pow(elemfeat[i] - medias[i],2);
			}
		}

		for (int i = 0; i < featuresStdDev.length; i++) {
			featuresStdDev[i] = Math.sqrt(featuresStdDev[i] / lista.size());
		}

		return featuresStdDev;

	}

	/**
	 * Calcula a media de um conjunto de dados
	 * @param lista - o conjunto de dados
	 * @param res - onde guarda os valores da media de cada ma das componentes
	 * @return
	 */
	private static double[] calculoMedia (ArrayList<Element> lista) {
		double[] featuresMedia = new double [lista.get(0).getFeaturesSize()];

		//primeiro somar
		for (Element e: lista) {
			double[] elemfeat = e.getFeatures();
			for (int i = 0; i < featuresMedia.length; i++) {
				featuresMedia[i] += elemfeat[i];
			}
		}

		//depois dividir por quantos ha
		for (int i = 0; i < featuresMedia.length; i++) {
			featuresMedia[i] = featuresMedia[i]/(lista.size());
		}

		return featuresMedia;
	}


}
