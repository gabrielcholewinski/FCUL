
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class BidirectionalHashMapTest {

    private BidirectionalHashMap<String, Integer> bdham;

    @Before
    public void setUp () throws Exception {
        this.bdham = new BidirectionalHashMap<>();
    }

    @Test
    public void testGetKey1 () {
        String expected = null;
        String actual = bdham.getKey(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetKey2 () {
        String expected = null;
        bdham.put("1", 1);
        String actual = bdham.getKey(2);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetKey3 () {
        String expected = "1";
        bdham.put("1", 1);
        String actual = bdham.getKey(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetKey4 () {
        String expected = "11";
        bdham.put("1", 1);
        bdham.put("11", 1);
        String actual = bdham.getKey(1);
        assertEquals(expected, actual);
    }


    @Test
    public void testGetKey5 () {
        String expected = "12";
        bdham.put("1", 1);
        bdham.put("12", 1);
        String actual = bdham.getKey(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetKey6 () {
        String expected = "3";
        bdham.put("1", 1);
        bdham.put("2", 2);
        bdham.put("3", 3);
        bdham.put("4", 4);
        String actual = bdham.getKey(3);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetValue1 () {
        Integer expected = null;
        Integer actual = bdham.getValue("1");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetValue2 () {
        Integer expected = null;
        bdham.put("1", 1);
        Integer actual = bdham.getValue("2");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetValue3 () {
        Integer expected = 1;
        bdham.put("1", 1);
        Integer actual = bdham.getValue("1");
        assertEquals(expected, actual);
    }


    @Test
    public void testGetValue4 () {
        Integer expected = 4;
        bdham.put("1", 1);
        bdham.put("2", 2);
        bdham.put("3", 3);
        bdham.put("4", 4);
        Integer actual = bdham.getValue("4");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetValue5 () {
        Integer expected = 1;
        bdham.put("1", 1);
        bdham.put("12", 1);
        Integer actual = bdham.getValue("12");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetValue6 () {
        Integer expected = null;
        bdham.put("1", 1);
        bdham.put("12", 1);
        Integer actual = bdham.getValue("1");
        assertEquals(expected, actual);
    }    

    @Test
    public void testContainsKey1 () {
        bdham.put("1", 1);
        bdham.put("12", 2);
        boolean expected = true;
        boolean actual = bdham.containsKey("1");
        assertEquals(expected, actual);
    }


    @Test
    public void testContainsKey2 () {
        bdham.put("1", 1);
        boolean expected = false;
        boolean actual = bdham.containsKey("2");
        assertEquals(expected, actual);
    }

    @Test
    public void testContainsKey3 () {
        bdham.put("1", 1);
        bdham.put("12", 2);
        boolean expected = false;
        boolean actual = bdham.containsKey("2");
        assertEquals(expected, actual);
    }

    @Test
    public void testContainsKey4 () {
        bdham.put("1", 1);
        bdham.put("12", 2);
        boolean expected = true;
        boolean actual = bdham.containsKey("12");
        assertEquals(expected, actual);
    }

    @Test
    public void testContainsValue1 () {
        bdham.put("1", 1);
        bdham.put("12", 2);
        boolean expected = true;
        boolean actual = bdham.containsValue(1);
        assertEquals(expected, actual);
    }


    @Test
    public void testContainsValue2 () {
        bdham.put("1", 1);
        boolean expected = false;
        boolean actual = bdham.containsValue(2);
        assertEquals(expected, actual);
    }

    @Test
    public void testContainsValue3 () {
        bdham.put("1", 1);
        bdham.put("1", 2);
        boolean expected = false;
        boolean actual = bdham.containsValue(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testContainsValue4 () {
        bdham.put("1", 1);
        bdham.put("12", 2);
        boolean expected = true;
        boolean actual = bdham.containsValue(2);
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveByKey1 () {
        bdham.put("1", 1);
        Integer expected = null;
        Integer actual = bdham.removeByKey("2");
        assertEquals(expected, actual);
    }


    @Test
    public void testRemoveByKey2 () {
        bdham.put("1", 1);
        Integer expected = 1;
        Integer actual = bdham.removeByKey("1");
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveByKey3 () {
        bdham.put("1", 1);
        bdham.put("12", 1);
        Integer expected = null;
        Integer actual = bdham.removeByKey("1");
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveByKey4 () {
        bdham.put("1", 1);
        bdham.put("12", 1);
        Integer expected = 1;
        Integer actual = bdham.removeByKey("12");
        assertEquals(expected, actual);
    }



    @Test
    public void testRemoveByValue () {
        bdham.put("1", 1);
        String expected = null;
        String actual = bdham.removeByValue(2);
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveByValue2 () {
        bdham.put("1", 1);
        String expected = "1";
        String actual = bdham.removeByValue(1);
        assertEquals(expected, actual);
    }


    @Test
    public void testRemoveByValue3 () {
        bdham.put("1", 1);
        bdham.put("1", 12);
        String expected = null;
        String actual = bdham.removeByValue(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveByValue4 () {
        bdham.put("1", 1);
        bdham.put("1", 12);
        String expected = "1";
        String actual = bdham.removeByValue(12);
        assertEquals(expected, actual);
    }


    @Test
    public void testSize1 () {
        int expected = 0;
        int actual = bdham.size();
        assertEquals(expected, actual);    
    }

    @Test
    public void testSize2 () {
        bdham.put("1", 1);
        int expected = 1;
        int actual = bdham.size();
        assertEquals(expected, actual);    
    }

    @Test
    public void testSize3 () {
        bdham.put("1", 1);
        bdham.put("1", 12);
        int expected = 1;
        int actual = bdham.size();
        assertEquals(expected, actual);    
    }

    @Test
    public void testSize4 () {
        bdham.put("1", 1);
        bdham.put("12", 1);
        int expected = 1;
        int actual = bdham.size();
        assertEquals(expected, actual);    
    }

    @Test
    public void testSize5 () {
        bdham.put("1", 1);
        bdham.put("12", 12);
        int expected = 2;
        int actual = bdham.size();
        assertEquals(expected, actual);    
    }

    @Test
    public void testSize6 () {
        bdham.put("1", 1);
        bdham.put("12", 12);
        bdham.removeByKey("1");
        int expected = 1;
        int actual = bdham.size();
        assertEquals(expected, actual);    
    }


    @Test
    public void testGetKeys1 () {
        Set<String> expected = new HashSet<>();
        Set<String> actual = bdham.getKeys();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetKeys2 () {
        Set<String> expected = new HashSet<>();
        expected.add("1");
        bdham.put("1", 1);
        Set<String> actual = bdham.getKeys();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetKeys3 () {
        Set<String> expected = new HashSet<>();
        expected.add("12");
        bdham.put("1", 1);
        bdham.put("12", 1);
        Set<String> actual = bdham.getKeys();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetKeys4 () {
        Set<String> expected = new HashSet<>();
        expected.add("1");
        expected.add("12");
        bdham.put("1", 1);
        bdham.put("12", 12);
        Set<String> actual = bdham.getKeys();
        assertEquals(expected, actual);
    }
}
