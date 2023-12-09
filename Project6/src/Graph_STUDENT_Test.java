import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The Graph_STUDENT_Test class is used to test the methods of the Graph class.
 * 
 * @author Mariia Honcharenko
 */
public class Graph_STUDENT_Test {
	private GraphInterface<Town,Road> graph;
	private Town[] town;

	/**
     * Set up the test environment before each test case.
     */
	@Before
	public void setUp() throws Exception {
		 graph = new Graph();
		  town = new Town[16];
		  
		  for (int i = 1; i < 15; i++) {
			  town[i] = new Town("Town_" + i);
			  graph.addVertex(town[i]);
		  }
		  
		  graph.addEdge(town[1], town[3], 15, "Road_A");
		  graph.addEdge(town[1], town[5], 14, "Road_B");
		  graph.addEdge(town[1], town[7], 13, "Road_C");
		  graph.addEdge(town[1], town[9], 12, "Road_D");
		  graph.addEdge(town[1], town[11], 11, "Road_E");
		  graph.addEdge(town[2], town[13], 10, "Road_F");
		  graph.addEdge(town[2], town[14], 9, "Road_G");
		  graph.addEdge(town[2], town[1], 8, "Road_H");
		  graph.addEdge(town[3], town[4], 7, "Road_I");
		  graph.addEdge(town[14], town[6], 6, "Road_J");
		  graph.addEdge(town[7], town[8], 5, "Road_K");
		  graph.addEdge(town[14], town[10], 4, "Road_L");
		  graph.addEdge(town[13], town[12], 3, "Road_M");
		  graph.addEdge(town[4], town[14], 2, "Road_N");
		  graph.addEdge(town[12], town[6], 1, "Road_O");
	}

	/**
     * Clean up the test environment after each test case.
     */
	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	/**
     * Test the getEdge() method of Graph.
     */
	@Test
	public void testGetEdge() {
		assertEquals(new Road(town[1], town[5],14, "Road_B"), graph.getEdge(town[1], town[5]));
		assertEquals(new Road(town[14], town[6],6, "Road_J"), graph.getEdge(town[14], town[6]));
	}

	/**
     * Test the addEdge() method of Graph.
     */
	@Test
	public void testAddEdge() {
		assertEquals(false, graph.containsEdge(town[10], town[13]));
		graph.addEdge(town[10], town[13], 9, "Road_S");
		assertEquals(true, graph.containsEdge(town[10], town[13]));
	}

	/**
     * Test the addVertex() method of Graph.
     */
	@Test
	public void testAddVertex() {
		Town newTown = new Town("Town_16");
		assertEquals(false, graph.containsVertex(newTown));
		graph.addVertex(newTown);
		assertEquals(true, graph.containsVertex(newTown));
	}

	/**
     * Test the containsEdge() method of Graph.
     */
	@Test
	public void testContainsEdge() {
		assertEquals(true, graph.containsEdge(town[7], town[8]));
		assertEquals(false, graph.containsEdge(town[12], town[14]));
	}

	/**
     * Test the containsVertex() method of Graph.
     */
	@Test
	public void testContainsVertex() {
		assertEquals(true, graph.containsVertex(new Town("Town_14")));
		assertEquals(false, graph.containsVertex(new Town("Town_15")));
	}

	/**
     * Test the edgeSet() method of Graph.
     */
	@Test
	public void testEdgeSet() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Road_A", roadArrayList.get(0));
		assertEquals("Road_B", roadArrayList.get(1));
		assertEquals("Road_C", roadArrayList.get(2));
		assertEquals("Road_D", roadArrayList.get(3));
		assertEquals("Road_E", roadArrayList.get(4));
		assertEquals("Road_F", roadArrayList.get(5));
		assertEquals("Road_G", roadArrayList.get(6));
		assertEquals("Road_H", roadArrayList.get(7));
		assertEquals("Road_I", roadArrayList.get(8));
		assertEquals("Road_J", roadArrayList.get(9));
	}

	/**
     * Test the edgesOf() method of Graph.
     */
	@Test
	public void testEdgesOf() {
		Set<Road> roads = graph.edgesOf(town[1]);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Road_A", roadArrayList.get(0));
		assertEquals("Road_B", roadArrayList.get(1));
		assertEquals("Road_C", roadArrayList.get(2));
		assertEquals("Road_D", roadArrayList.get(3));
		assertEquals("Road_E", roadArrayList.get(4));
		assertEquals("Road_H", roadArrayList.get(5));
	}
	
	/**
     * Test the removeVertex() method of Graph.
     */
	@Test
	public void testRemoveVertex() {
		assertEquals(true, graph.containsVertex(town[14]));
		graph.removeVertex(town[14]);
		assertEquals(false, graph.containsVertex(town[14]));
	}

	/**
     * Test the vertexSet() method of Graph.
     */
	@Test
	public void testVertexSet() {
		Set<Town> roads = graph.vertexSet();
		assertEquals(true,roads.contains(town[4]));
		assertEquals(true, roads.contains(town[6]));
		assertEquals(true, roads.contains(town[8]));
		assertEquals(true, roads.contains(town[10]));
		assertEquals(true, roads.contains(town[12]));
	}

	/**
     * Test the shortest path calculation from Town_1 to Town_5.
     */
	@Test
	public void testTown_1ToTown_5() {
		String beginTown = "Town_1", endTown = "Town_5";
		Town beginIndex=null, endIndex=null;
		Set<Town> towns = graph.vertexSet();
		Iterator<Town> iterator = towns.iterator();
		while(iterator.hasNext()) {    	
			Town town = iterator.next();
			if(town.getName().equals(beginTown))
				beginIndex = town;
			if(town.getName().equals(endTown))
				endIndex = town;		
		}
		if (beginIndex != null && endIndex != null) {
			ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			assertNotNull(path);
			assertTrue(path.size() > 0);
			assertEquals("Town_1 via Road_B to Town_5 14 mi",path.get(0).trim());
		}
		else
			fail("Town names are not valid");

	}
	  
	/**
     * Test the shortest path calculation from Town_1 to Town_14.
     */  
	@Test
	public void testTown1ToTown_14() {
		String beginTown = "Town_1", endTown = "Town_14";
		Town beginIndex=null, endIndex=null;
		Set<Town> towns = graph.vertexSet();
		Iterator<Town> iterator = towns.iterator();
		while(iterator.hasNext()) {  	
			Town town = iterator.next();
			if(town.getName().equals(beginTown))
				beginIndex = town;
			if(town.getName().equals(endTown))
				endIndex = town;		
		}
		if (beginIndex != null && endIndex != null) {
			ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			assertNotNull(path);
			assertTrue(path.size() > 0);
			assertEquals("Town_1 via Road_H to Town_2 8 mi",path.get(0).trim());
			assertEquals("Town_2 via Road_G to Town_14 9 mi",path.get(1).trim());
		}
		else
			fail("Town names are not valid");

	}
	 
	/**
     * Test the shortest path calculation from Town_8 to Town_12.
     */
	@Test
	public void testTown_8ToTown_12() {
		String beginTown = "Town_8", endTown = "Town_12";
		Town beginIndex=null, endIndex=null;
		Set<Town> towns = graph.vertexSet();
		Iterator<Town> iterator = towns.iterator();
		while(iterator.hasNext()) {    	
			Town town = iterator.next();
			if(town.getName().equals(beginTown))
				beginIndex = town;
			if(town.getName().equals(endTown))
				endIndex = town;		
		}
		if (beginIndex != null && endIndex != null) {
			ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			assertNotNull(path);
			assertTrue(path.size() > 0);
			assertEquals("Town_8 via Road_K to Town_7 5 mi",path.get(0).trim());
			assertEquals("Town_7 via Road_C to Town_1 13 mi",path.get(1).trim());
			assertEquals("Town_1 via Road_H to Town_2 8 mi",path.get(2).trim());
			assertEquals("Town_2 via Road_F to Town_13 10 mi",path.get(3).trim());
			assertEquals("Town_13 via Road_M to Town_12 3 mi",path.get(4).trim());
		}
		else
			fail("Town names are not valid");

	}
}
