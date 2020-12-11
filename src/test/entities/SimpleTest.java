package test.entities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.*;

public class SimpleTest {
    private Collection<Object> collection;

    @Before
    public void setUp() {
        collection = new ArrayList<Object>();
    }

    @Test
    public void testEmptyCollection() {
        assertTrue(collection.isEmpty());
    }

    @Test
    public void testOneItemCollection() {
        collection.add("itemA");
        assertEquals(4, collection.size());
    }

    @Test
    public void testCollectionAdd() {
        assertEquals(0, collection.size());
        collection.add("itemA");
        assertEquals(1, collection.size());
        collection.add("itemB");
        assertEquals(2, collection.size());
    }
}
