import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The TownGraphManager_STUDENT_Test is used to test the methods of the TownGraphManager class.
 * 
 * @author Mariia Honcharenko
 */
public class TownGraphManager_STUDENT_Test {
	private TownGraphManagerInterface graph;
	private String[] town;
	  
	/**
     * Set up the test environment before each test case.
     */
	@Before
	public void setUp() throws Exception {
		  graph = new TownGraphManager();
		  town = new String[16];
		  
		  for (int i = 1; i < 15; i++) {
			  town[i] = "Town_" + i;
			  graph.addTown(town[i]);
		  }
		  
		  graph.addRoad(town[1], town[3], 15, "Road_A");
		  graph.addRoad(town[1], town[5], 14, "Road_B");
		  graph.addRoad(town[1], town[7], 13, "Road_C");
		  graph.addRoad(town[1], town[9], 12, "Road_D");
		  graph.addRoad(town[2], town[11], 11, "Road_E");
		  graph.addRoad(town[2], town[13], 10, "Road_F");
		  graph.addRoad(town[2], town[14], 9, "Road_G");
		  graph.addRoad(town[2], town[1], 8, "Road_H");
		  graph.addRoad(town[3], town[4], 7, "Road_I");
		  graph.addRoad(town[4], town[14], 2, "Road_J");
		  graph.addRoad(town[6], town[14], 6, "Road_K");
		  graph.addRoad(town[7], town[8], 5, "Road_L");
		  graph.addRoad(town[10], town[14], 4, "Road_M");
		  graph.addRoad(town[12], town[6], 1, "Road_N");
		  graph.addRoad(town[12], town[13], 3, "Road_O");
		 
	}

	/**
     * Clean up the test environment after each test case.
     */
	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	/**
     * Tests the addRoad() method of TownGraphManager.
     */
	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_A", roads.get(0));
		assertEquals("Road_B", roads.get(1));
		assertEquals("Road_C", roads.get(2));
		assertEquals("Road_D", roads.get(3));
		graph.addRoad(town[4], town[11], 1,"Road_P");
		roads = graph.allRoads();
		assertEquals("Road_A", roads.get(0));
		assertEquals("Road_B", roads.get(1));
		assertEquals("Road_C", roads.get(2));
		assertEquals("Road_D", roads.get(3));
		assertEquals("Road_P", roads.get(15));
		
	}

	/**
     * Tests the getRoad() method of TownGraphManager.
     */
	@Test
	public void testGetRoad() {
		assertEquals("Road_J", graph.getRoad(town[4], town[14]));
		assertEquals("Road_M", graph.getRoad(town[10], town[14]));
		assertEquals("Road_A", graph.getRoad(town[1], town[3]));
		assertEquals("Road_C", graph.getRoad(town[1], town[7]));
		assertEquals("Road_K", graph.getRoad(town[6], town[14]));
	}

	/**
     * Tests the addTown() method of TownGraphManager.
     */
	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Town_15"));
		graph.addTown("Town_15");
		assertEquals(true, graph.containsTown("Town_15"));
	}
	
	/**
     * Tests the getPath() method of TownGraphManager for a disjoint graph.
     */
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("Town_15"));
		graph.addTown("Town_15");
		ArrayList<String> path = graph.getPath(town[1],"Town_15");
		assertFalse(path.size() > 0);
	}

	/**
     * Tests the containsTown() method of TownGraphManager.
     */
	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		assertEquals(false, graph.containsTown("Town_16"));
	}

	/**
     * Tests the containsRoadConnection() method of TownGraphManager.
     */
	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[7], town[8]));
		assertEquals(false, graph.containsRoadConnection(town[12], town[14]));
	}

	/**
     * Tests the allRoads() method of TownGraphManager.
     */
	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_A", roads.get(0));
		assertEquals("Road_B", roads.get(1));
		assertEquals("Road_C", roads.get(2));
		assertEquals("Road_D", roads.get(3));
		assertEquals("Road_E", roads.get(4));
		assertEquals("Road_F", roads.get(5));
		assertEquals("Road_G", roads.get(6));
		assertEquals("Road_H", roads.get(7));
		assertEquals("Road_I", roads.get(8));
		assertEquals("Road_J", roads.get(9));
		assertEquals("Road_K", roads.get(10));
		assertEquals("Road_L", roads.get(11));
		assertEquals("Road_M", roads.get(12));
		assertEquals("Road_N", roads.get(13));
		assertEquals("Road_O", roads.get(14));
	}

	/**
     * Tests the deleteRoadConnection() method of TownGraphManager.
     */
	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[12], town[13]));
		graph.deleteRoadConnection(town[12], town[13], "Road_O");
		assertEquals(false, graph.containsRoadConnection(town[12], town[13]));
	}

	/**
     * Tests the deleteTown() method of TownGraphManager.
     */
	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_5"));
		graph.deleteTown(town[5]);
		assertEquals(false, graph.containsTown("Town_5"));
	}
	
	/**
     * Tests the allTowns() method of TownGraphManager.
     */
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals("Town_1", roads.get(0));
		assertEquals("Town_10", roads.get(1));
		assertEquals("Town_11", roads.get(2));
		assertEquals("Town_12", roads.get(3));
		assertEquals("Town_13", roads.get(4));
		assertEquals("Town_14", roads.get(5));
		assertEquals("Town_2", roads.get(6));
		assertEquals("Town_3", roads.get(7));
		assertEquals("Town_4", roads.get(8));
		assertEquals("Town_5", roads.get(9));
		assertEquals("Town_6", roads.get(10));
		assertEquals("Town_7", roads.get(11));
		assertEquals("Town_8", roads.get(12));
		assertEquals("Town_9", roads.get(13));
	}

	/**
     * Tests the getPath() method of TownGraphManager.
     */
	@Test
	public void testGetPath1() {
		ArrayList<String> path = graph.getPath(town[11],town[13]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_11 via Road_E to Town_2 11 mi",path.get(0).trim());
		  assertEquals("Town_2 via Road_F to Town_13 10 mi",path.get(1).trim());

	}
	
	/**
     * Tests the getPath() method of TownGraphManager.
     */
	@Test
	public void testGetPath2() {
		ArrayList<String> path = graph.getPath(town[2],town[8]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_2 via Road_H to Town_1 8 mi",path.get(0).trim());
		  assertEquals("Town_1 via Road_C to Town_7 13 mi",path.get(1).trim());
		  assertEquals("Town_7 via Road_L to Town_8 5 mi",path.get(2).trim());
	}
	
	/**
     * Tests the getPath() method of TownGraphManager.
     */
	@Test
	public void testGetPath3() {
		ArrayList<String> path = graph.getPath(town[1],town[6]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via Road_H to Town_2 8 mi",path.get(0).trim());
		  assertEquals("Town_2 via Road_F to Town_13 10 mi",path.get(1).trim());
		  assertEquals("Town_13 via Road_O to Town_12 3 mi",path.get(2).trim());
		  assertEquals("Town_12 via Road_N to Town_6 1 mi",path.get(3).trim());
	}

}
