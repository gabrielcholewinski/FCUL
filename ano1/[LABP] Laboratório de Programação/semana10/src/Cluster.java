import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Cluster {
	private Element centroid;
	private String designacao;
	private ArrayList<Element> cluster;
	
	/**
	 * Cria um grupo (cluster) dado um elemento que serve de centroide
	 * e a sua designacao
	 * @param centroid - o elemento que serve de centroide
	 * @param s - a designcacao do cluster
	 * @requires centroid, s != null
	 */
	public Cluster (Element centroid, String s) {
		this.centroid = centroid;
		designacao = s;
		cluster = new ArrayList<>();
	}

	/**
	 * Cria um grupo (cluster) dado um elemento que serve de centroide
	 * e a sua designacao
	 * @param centroid - o elemento que serve de centroide
	 * @param s - a designcacao do cluster
	 * @requires centroid, s != null
	 */
	public boolean add(Element e) {
		if(!contains(e)) {
			cluster.add(e);
			e.setLabel(designacao);
			return true;
		}
		return false;
	}

	/**
	 * O elemento ja pertence ao cluster
	 * @param e - o elemento a testar
	 * @return true sse e ja existe ni cluster
	 * @requires e != null
	 */
	public boolean contains(Element e) {
		return cluster.contains(e);
	}

	/**
	 * Este cluster tem elementos? 
	 * @return true se nao ha elementos no cluster
	 */
	public boolean isEmpty() {
		return cluster.isEmpty();
	}

	/**
	 * Informa o numero de elementos deste cluster
	 * @return o numero de elmentos no cluster
	 */
	public int size() {
		return cluster.size();
	}



	/**
	 * Da uma forma de iterar sobre os elementos do cluster
	 * @return um iterador sobre os elementos deste cluster
	 */
	public Iterator<Element> iterator(){
		return cluster.iterator();
	}


	/**
	 * Devolve a classificacao atribuida
	 * @return a classificacao dada
	 */
	public String getLabel () {
		return designacao;
	}


	/**
	 * Actualiza a classificacao deste cluster
	 * @param label a classificacao dada ao cluster
	 * @requires label != null
	 */
	public void setLabel (String label) {
		designacao = label;
	}


	/**
	 * Devolve copia dos elementos do cluster 
	 * @return uma copia dos elementos deste cluster
	 */
	public ArrayList<Element> getElements () {
		return (ArrayList<Element>) cluster.clone();
	}


	/**
	 * Uma copia do centroide deste cluster
	 * @return uma copia do centroide
	 */
	public Element getCentroid () {
		return centroid.clone();
	}

	/**
	 * Actualiza o centroide de acordo com a media dos elementos 
	 * deste cluster 
	 * @requires !isEmpty()
	 */
	public void updateCentroid() { //media de todos os elementos;
		double[] aux = new double[centroid.getFeaturesSize()];
		
		for(int i=0 ; i<cluster.size() ; i++)
			for(int j=0 ; j<cluster.get(i).getFeatures().length ; j++) 
				aux[j] += cluster.get(i).getFeatures()[j];
		
		for(int k=0 ; k<aux.length ; k++)
			aux[k] /= cluster.size();
		
		int type = commonType();
		
		setCentroid(new Element("Centroid "+type, aux, type + "", type));
			
	}
	
	private int commonType() {
		int result = 0;
		int maxCount = 0;
		
		for(Element e1: cluster) {
			int count = 0;
			for(Element e2: cluster) {
				if(e1.getType() == e2.getType())
					count++;
			}
			if(count > maxCount) {
				maxCount = count;
				result = e1.getType();
			}
		}
		return result;
		
	}

	/**
	 * Actualiza o centroide deste cluster para um dado elemento 
	 * @param e o novo centroide
	 * @requires e != null
	 */
	public void setCentroid (Element e) {
		centroid = e.clone();
		designacao = centroid.getLabel();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder("Cluster "+designacao+":\n");
		for(int i=0 ; i<cluster.size() ; i++) {
			sb.append("    "+cluster.get(i)+"\n");
		}
		sb.append("centroid ="+centroid.toString()+"\n");
		return sb.toString();
	}


	/**
	 * Calcula a variancia deste cluster
	 * @return Raiz quadrada da soma do quadrado das distancias de todos os elementos 
	 *          ao centroide deste cluster 
	 * @requires !isEmpty()
	 */
	public double variance () {
		double aux = 0;
		
		for(int i=0 ; i<cluster.size() ; i++) {
			aux += Math.pow(cluster.get(i).distance(centroid), 2);
		}
		return Math.sqrt(aux);
	}

}
