import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ClusterTest {

    private Cluster cluster;


    @Before
    public void setUp () throws Exception {
        double[] features = {0.0, 0.0, 0.0};
        Element c =  new Element ("C", features, "1", 1);

        this.cluster = new Cluster(c, "1");
    }

    @Test
    public void testAddeGetElements () {
        double[] features = {0.0, 0.0, 0.0};

        Element e1 =  new Element ("1", features, "1", 1 );
        features[1] = 3.0;
        Element e2 =  new Element ("2", features, "1", 1 );
        features[2] = 3.0;
        Element e3 =  new Element ("3", features, "1", 1 );

        this.cluster.add(e1);
        this.cluster.add(e2);
        this.cluster.add(e3);

        ArrayList<Element> actual = this.cluster.getElements();
        ArrayList<Element> expected = new ArrayList<>();
        expected.add(e1);
        expected.add(e2);
        expected.add(e3);

        assertEquals(expected, actual);
    }

    @Test
    public void testContains () {
        double[] features = {0.0, 0.0, 0.0};

        Element e1 =  new Element ("1", features, "1", 1 );
        features[1] = 3.0;
        Element e2 =  new Element ("2", features, "1", 1 );
        features[2] = 3.0;
        Element e3 =  new Element ("3", features, "1", 1 );

        this.cluster.add(e1);
        this.cluster.add(e2);
        this.cluster.add(e3);

        boolean tem = this.cluster.contains(e1);
        assertTrue(tem);
    }
    @Test
    public void testContains2 () {
        double[] features = {0.0, 0.0, 0.0};

        Element e1 =  new Element ("1", features, "1", 1 );
        features[1] = 3.0;
        Element e2 =  new Element ("2", features, "1", 1 );
        features[2] = 3.0;
        Element e3 =  new Element ("3", features, "1", 1 );

        this.cluster.add(e1);
        this.cluster.add(e3);

        boolean tem = this.cluster.contains(e2);
        assertTrue(!tem);
    }

    @Test
    public void testIsEmpty () {
        boolean vazio = this.cluster.isEmpty();
        assertTrue(vazio);
    }

    @Test
    public void testNaoIsEmpty () {
        double[] features = {0.0, 0.0, 0.0};

        Element e1 =  new Element ("1", features, "1", 1 );
        features[1] = 3.0;

        this.cluster.add(e1);
        boolean vazio = this.cluster.isEmpty();

        assertTrue(!vazio);
    }


    @Test
    public void testSize0 () {
        int expected = 0;
        int actual = this.cluster.size();
        assertEquals(expected, actual);
    }

    @Test
    public void testSize2 () {
        double[] features = {0.0, 0.0, 0.0};

        Element e1 =  new Element ("1", features, "1", 1 );
        features[1] = 3.0;
        Element e2 =  new Element ("2", features, "1", 1 );
        features[2] = 3.0;
        Element e3 =  new Element ("3", features, "1", 1 );

        this.cluster.add(e1);
        this.cluster.add(e2);
        this.cluster.add(e3);

        int expected = 3;
        int actual = this.cluster.size();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetLabel () {

        String expected = "1";
        String actual = this.cluster.getLabel();
        assertEquals(expected, actual);

    }

    @Test
    public void testSetLabel () {

        String expected = "2";
        this.cluster.setLabel("2");
        String actual = this.cluster.getLabel();
        assertEquals(expected, actual);
    }


    @Test
    public void testGetCentroid () {
        double[] features = {0.0, 0.0, 0.0};
        Element expected =  new Element ("C", features, "1", 1 );
        Element actual = this.cluster.getCentroid();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetCentroid2 () {
        double[] features = {0.0, 0.0, 0.0};
        Element expected =  new Element ("C", features, "1", 1 );
        Element actual = this.cluster.getCentroid();
        actual.setLabel("2");
        assertTrue(!expected.equals(actual));
    }

    @Test
    public void testUpdateCentroid () {
        double[] features = {0.0, 0.0, 0.0};

        features[0] = 3.0;
        Element e1 =  new Element ("1", features, "1", 1 );
        features[1] = 6.0;
        Element e2 =  new Element ("2", features, "1", 1 );
        features[2] = 15.0;
        Element e3 =  new Element ("3", features, "1", 1 );

        this.cluster.add(e1);
        this.cluster.add(e2);
        this.cluster.add(e3);

        double[] features2 = {3.0, 4.0, 5.0};
        
        Element expected = new Element("Centroid 1", features2, "1", 1);
        
        
        
        this.cluster.updateCentroid();
        Element actual = this.cluster.getCentroid();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetCentroid () {
        double[] features = {1.0, 2.0, 3.0};
        Element e1 =  new Element ("1", features, "1", 2);
        
        this.cluster.setCentroid(e1);
        Element actual = this.cluster.getCentroid();
        assertEquals(e1, actual);
    }
    
    @Test
    public void testSetCentroid2 () {
        double[] features = {1.0, 2.0, 3.0};
        Element e1 =  new Element ("1", features, "1", 2);
        
        this.cluster.setCentroid(e1);
        e1.setLabel("0");
        Element actual = this.cluster.getCentroid();
        assertTrue(!e1.equals(actual));
    }

    @Test
    public void testToString () {
        double[] features = {0.0, 0.0, 0.0};

        Element e1 =  new Element ("1", features, "1", 1 );
        features[1] = 3.0;
        Element e2 =  new Element ("2", features, "1", 0 );
        features[2] = 3.0;
        Element e3 =  new Element ("3", features, "1", 1 );

        this.cluster.add(e1);
        this.cluster.add(e2);
        this.cluster.add(e3);
        
        String expected = "Cluster 1:\n" + 
                "    1 features=[0.0, 0.0, 0.0] label = 1\n" + 
                "    2 features=[0.0, 3.0, 0.0] label = 1\n" + 
                "    3 features=[0.0, 3.0, 3.0] label = 1\n" + 
                "centroid =C features=[0.0, 0.0, 0.0] label = 1\n";
        String actual = this.cluster.toString();
        assertEquals(expected, actual);
        
    }

    @Test
    public void testVariance () {
        double[] features = {0.0, 0.0, 0.0};

        features[0] = 3.0;
        Element e1 =  new Element ("1", features, "1", 1 );
        features[0] = 0.0;
        features[1] = 3.0;
        Element e2 =  new Element ("2", features, "1", 0 );
        features[1] = 0.0;
        features[2] = 3.0;
        Element e3 =  new Element ("3", features, "1", 1 );

        this.cluster.add(e1);
        this.cluster.add(e2);
        this.cluster.add(e3);
        
        double expected = 5.1961524;
        double actual = this.cluster.variance();
        assertEquals(expected, actual, 0.0001);
    }
}
