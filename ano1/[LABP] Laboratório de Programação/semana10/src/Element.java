import java.util.Arrays;

public class Element {

    //o nome do elemento
    private final String name;

    //o conjunto de caracteristicas
    private final double[] features;

    //a classificacao original do registo
    private final int type;

    //a sua classificacao obtida
    private String label;

    /**
     * Cria um Elemento com o nome, o conjunto de carateristicas e o seu tipo
     * @param name - a identificacao do dado
     * @param features - o conjuntode caraterisiticas 
     * @param type - classificacao pre definida
     * @param label - a etiqueta atribuida por Clustering
     * @requires features != null
     */
    public Element (String name, double[] features, String label, int type) {
        super();
        this.name = name;
        this.features =  new double[features.length];
        for (int i = 0; i < features.length; i++) {
            this.features[i] = features[i];
        }
        this.label = label;
        this.type = type;
    }

    /**
     * A classificacao original do elemento
     * @return a classificacao original
     */
    public int getType () {
        return type;
    }

    /**
     * A classificacao dada pelo clustering
     * @return a classificacao dado por Clustering
     */
    public String getLabel () {
        return label;
    }

    /**
     * Establece uma classificacao para este elemento
     * @param label - a classificacao dada
     * @requires label != null
     */
    public void setLabel (String label) {
        this.label = label;
    }

    /**
     * Devolve o nome do elemento
     * @return o nome deste elemento 
     */
    public String getName () {
        return name;
    }

    /**
     * Devolve o vector de carateristicas deste elemento
     * @return the features
     */
    public double[] getFeatures () {  
        return features;
    }

    /**
     * Devolve o numero de caraterisitcas
     * @return o numero de caraterisitcas
     */
    public int getFeaturesSize () {
        return features.length;
    }

    /**
     * Calcula a distancia deste elemento a outro
     * @param e o outro elemento
     * @return distancia euclidiana entre este elemento e e
     * @requires e != null e do mesmo tamanho deste
     */
    //eh calculada por dis( x , y, p) = ( sum_i (abs(x_i - y_i))^p)^(1/p) 
    //no caso 2, para simplificar
    public double distance (Element e) {
        double sum = 0;
        for (int i = 0; i < features.length; i++) {
            sum += Math.pow(Math.abs(features[i] - e.features[i]), 2);
        }
        return Math.sqrt(sum);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        return name  + " features=" + Arrays.toString(features) +" label = " + label;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }else if (obj instanceof Element) {
            Element other = (Element) obj;
            return (name.equals(other.name) && Arrays.equals(features, other.features) 
                    && this.type == other.type && label.equals(other.label));   
            }
        return false;
    }

    /**
     * Retorna uma copia deste elemento
     */
    public Element clone() {
        return new Element(this.name, this.features, this.label, this.type);
    }

}
