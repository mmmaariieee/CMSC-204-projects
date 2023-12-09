import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The Town_STUDENT_Test class is used to test the methods of the Town class.
 * 
 * @author Mariia Honcharenko
 */
public class Town_STUDENT_Test {
    private Town townA;
    private Town townB;

    /**
     * Set up the test environment before each test case.
     */
    @Before
    public void setUp() throws Exception {
        townA = new Town("Dnipro");
        townB = new Town("Paris");
    }

    /**
     * Clean up the test environment after each test case.
     */
    @After
    public void tearDown() throws Exception {
        townA = null;
        townB = null;
    }

    /**
     * Test the constructor of Town class.
     */
    @Test
    public void testTownConstructor() {
        assertEquals("Dnipro", townA.getName());
        assertTrue(townA.getAdjTowns().isEmpty());
    }

    /**
     * Test the copy constructor of Town class.
     */
    @Test
    public void testTownCopyConstructor() {
        Town copiedTown = new Town(townA);
        assertEquals(townA.getName(), copiedTown.getName());
        assertEquals(townA.getAdjTowns().size(), copiedTown.getAdjTowns().size());
        assertTrue(copiedTown.getAdjTowns().isEmpty());
    }

    /**
     * Test the getName() method of Town class.
     */
    @Test
    public void testGetName() {
        assertEquals("Dnipro", townA.getName());
        assertEquals("Paris", townB.getName());
    }

    /**
     * Test the getAdjTowns() method of Town class.
     */
    @Test
    public void testGetAdjTowns() {
        assertEquals(0, townA.getAdjTowns().size());
    }

    /**
     * Test the compareTo() method of Town class.
     */
    @Test
    public void testCompareTo() {
        assertTrue(townA.compareTo(townB) < 0);
        assertTrue(townB.compareTo(townA) > 0);
        assertTrue(townA.compareTo(new Town("Dnipro")) == 0);
    }

    /**
     * Test the toString() method of Town class.
     */
    @Test
    public void testToString() {
        assertEquals("Dnipro", townA.toString());
        assertEquals("Paris", townB.toString());
    }

    /**
     * Test the hashCode() method of Town class.
     */
    @Test
    public void testHashCode() {
        assertEquals("Dnipro".hashCode(), townA.hashCode());
        assertEquals("Paris".hashCode(), townB.hashCode());
    }

    /**
     * Test the equals() method of Town class.
     */
    @Test
    public void testEquals() {
        assertTrue(townA.equals(townA));
        assertTrue(townA.equals(new Town("Dnipro")));
        assertFalse(townA.equals(townB));
        assertFalse(townA.equals(null));
        assertFalse(townA.equals("Dnipro"));
    }
}
