import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The Road_STUDENT_Test class is used to test the methods of the Road class.
 * 
 * @author Mariia Honcharenko
 */
public class Road_STUDENT_Test {
    private Town town1;
    private Town town2;
    private Town town3;
    private Road road1;
    private Road road2;

    /**
     * Set up test objects before each test case.
     */
    @Before
    public void setUp() throws Exception {
        town1 = new Town("London");
        town2 = new Town("New York");
        town3 = new Town("Kyiv");
        road1 = new Road(town1, town2, 2000, "Road 1");
        road2 = new Road(town2, town3, 3000, "Road 2");
    }

    /**
     * Clean up test objects after each test case.
     */
    @After
    public void tearDown() throws Exception {
        town1 = null;
        town2 = null;
        town3 = null;
        road1 = null;
        road2 = null;
    }

    /**
     * Test the constructor of Road with a specific distance.
     */
    @Test
    public void testConstructorWithDistance() {
        assertEquals("London via Road 1 to New York 2000mi", road1.toString());
        assertEquals("New York via Road 2 to Kyiv 3000mi", road2.toString());
    }

    /**
     * Test the constructor of Road with the default distance.
     */
    @Test
    public void testConstructorWithDefaultDistance() {
        Road road = new Road(town1, town2, "Road B");
        assertEquals("London via Road B to New York 1mi", road.toString());
    }

    /**
     * Test the getName() method of Road.
     */
    @Test
    public void testGetName() {
        assertEquals("Road 1", road1.getName());
    }

    /**
     * Test the getSource() method of Road.
     */
    @Test
    public void testGetSource() {
        assertEquals(town1, road1.getSource());
    }

    /**
     * Test the getDestination() method of Road.
     */
    @Test
    public void testGetDestination() {
        assertEquals(town2, road1.getDestination());
    }

    /**
     * Test the getWeight() method of Road.
     */
    @Test
    public void testGetWeight() {
        assertEquals(2000, road1.getWeight());
        assertEquals(3000, road2.getWeight());
    }

    /**
     * Test the contains() method of Road.
     */
    @Test
    public void testContains() {
        assertTrue(road1.contains(town1));
        assertTrue(road1.contains(town2));
        assertFalse(road2.contains(town1));
        assertFalse(road1.contains(new Town("Town3")));
    }

    /**
     * Test the compareTo() method of Road.
     */
    @Test
    public void testCompareTo() {
        assertNotEquals(0, road1.compareTo(road2));
        assertEquals(0, road1.compareTo(new Road(town1, town2, 2000, "Road 1")));
        assertNotEquals(0, road1.compareTo(new Road(town1, town2, 10, "Road 3")));
    }

    /**
     * Test the equals() method of Road.
     */
    @Test
    public void testEquals() {
        assertFalse(road1.equals(road2));
        assertTrue(road2.equals(new Road(town2, town3, 3000, "Road 2")));
    }
}
