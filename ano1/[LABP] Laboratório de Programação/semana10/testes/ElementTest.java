import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ElementTest {

    private Element element;
    @Before
    public void setUp () throws Exception {
        double[] features = {2.1, 2.2, 2.3, 2.4};
        this.element = new Element("1", features, "1", 1);
    }

    @Test
    public void testGetName () {
        String expected;
        String actual;

        expected = "1";
        actual = this.element.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetFeatures () {
        double[] expected = {2.1, 2.2, 2.3, 2.4};
        double[] actual;

        actual = this.element.getFeatures();
        Assert.assertArrayEquals(expected, actual, 0.0001);
    }

    @Test
    public void testGetFeaturesSize () {
        int expected;
        int actual;

        expected = 4;
        actual = this.element.getFeaturesSize();
        assertEquals(expected, actual);
    }

    @Test
    public void testDistance0 () {
        double[] features = {2.1, 2.2, 2.3, 2.4};
        Element element2 = new Element("1", features, "1", 1);
        double expected = 0.0;
        double actual = this.element.distance(element2);
        assertEquals(expected, actual, 0.0001);
    }
    
    @Test
    public void testDistance1 () {
        double[] features = {1.1, 2.2, 2.3, 2.4};
        Element element2 = new Element("1", features, "1", 1);
        double expected = 1.0;
        double actual = this.element.distance(element2);
        assertEquals(expected, actual, 0.0001);
    }
    
    @Test
    public void testDistance3 () {
        double[] features = {3.1, 3.2, 3.3, 2.4};
        Element element2 = new Element("1", features, "1", 1);
        double expected = 1.73205;
        double actual = this.element.distance(element2);
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testToString () {
        String expected;
        String actual;

        expected = "1 features=[2.1, 2.2, 2.3, 2.4] label = 1";
        actual = this.element.toString();
        assertEquals(expected, actual);                
    }

    @Test
    public void testEqualsObjectDiferentesRefs () {
        double[] features = {2.1, 2.2, 2.3, 2.4};
        Element element2 = new Element("1", features, "1", 1);
        boolean iguais = this.element.equals(element2);
        assertTrue(iguais);
    }
    
    @Test
    public void testEqualsObjectDiferentesNomes () {
        double[] features = {2.1, 2.2, 2.3, 2.4};
        Element element2 = new Element("2", features, "1", 1);
        boolean iguais = this.element.equals(element2);
        assertTrue(!iguais);
    }

    @Test
    public void testEqualsObjectDiferentesFeatures () {
        double[] features = {2.1, 1.2, 2.3, 2.4};
        Element element2 = new Element("1", features, "1", 1);
        boolean iguais = this.element.equals(element2);
        assertTrue(!iguais);
    }
    
    @Test
    public void testEqualsObjectDiferentesLabels () {
        double[] features = {2.1, 2.2, 2.3, 2.4};
        Element element2 = new Element("1", features, "2", 1);
        boolean iguais = this.element.equals(element2);
        assertTrue(!iguais);
    }
    
    @Test
    public void testEqualsObjectDiferentesTypes () {
        double[] features = {2.1, 2.2, 2.3, 2.4};
        Element element2 = new Element("1", features, "1", 2);
        boolean iguais = this.element.equals(element2);
        assertTrue(!iguais);
    }
    
    @Test
    public void testGetType () {
        int expected = 1;
        int actual = this.element.getType();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetLabel () {
        String expected = "1";
        String actual = this.element.getLabel();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSetLabel () {
        String expected = "2";
        this.element.setLabel("2");
        String actual = this.element.getLabel();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testClone () {
        Element element2 = this.element.clone();
        this.element.setLabel("0");
        assertTrue(!element.equals(element2));
    }
    
    @Test
    public void testClone2 () {
        Element element2 = this.element.clone();
        assertTrue(element.equals(element2));
    }
}
