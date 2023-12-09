import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *  TownGraphManager manages towns and roads in a graph.
 *  It implements the TownGraphManagerInterface.
 *  
 *  @author Mariia Honcharenko
 */

public class TownGraphManager implements TownGraphManagerInterface {
	
	private Graph graph;
	
	public TownGraphManager() {
        this.graph = new Graph();
    }
    
	/**
	 * Adds a road between 2 towns, includes road name.
	 * 
	 * @param name1      name of town 1
	 * @param name2      name of town 2
	 * @param distance   distance between town 1 and town 2
	 * @param roadName   name of road
	 * 
	 * @return true if the road was added 
	 */
    @Override
    public boolean addRoad(String name1, String name2, int distance, String road) {
        Town town1 = getTown(name1);
        Town town2 = getTown(name2);
        
        if (town1 != null && town2 != null) {
            graph.addEdge(town1, town2, distance, road);
            return true;
        }
        
        return false;
    }
    
    /**
	 * Returns the name of the road between 2 towns.
	 * 
	 * @param name1      name of town 1
	 * @param name2      name of town 2
	 * 
	 * @return name of road between town 1 and town2, if not - null
	 */
    @Override
    public String getRoad(String name1, String name2) {
        Town town1 = getTown(name1);
        Town town2 = getTown(name2);
        
        if (town1 != null && town2 != null) {
            Road road = graph.getEdge(town1, town2);
            if (road != null) {
                return road.getName();
            }
        }
        
        return null;
    }
    
    /**
	 * Adds a town to the graph.
	 * 
	 * @param name       the town's name
	 * 
	 * @return true if the town was added, false if not
	 */
    @Override
    public boolean addTown(String name) {
        Town town = new Town(name);
        
        if (!graph.containsVertex(town)) {
            graph.addVertex(town);
            return true;
        }
        
        return false;
    }
	
	/**
	 * Gets a town with a specific name.
	 * 
	 * @param name      the town's name 
	 * 
	 * @return the Town by the name, or null if town does not exist
	 */
    @Override
    public Town getTown(String name) {
        for (Town town : graph.vertexSet()) {
            if (town.getName().equals(name)) {
                return town;
            }
        }
        
        return null;
    }
	
	/**
	 * Checks if a town is already in the graph.
	 * 
	 * @param name        the town's name 
	 * 
	 * @return true if the town is in the graph, false if not
	 */
    @Override
    public boolean containsTown(String name) {
        for (Town town : graph.vertexSet()) {
            if (town.getName().equals(name)) {
                return true;
            }
        }
        
        return false;
    }
	
	/**
	 * Checks if a road is in the graph.
	 * 
	 * @param town1      name of town 1
	 * @param town2      name of town 2
	 * 
	 * @return true if the road is in the graph, false if not
	 */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        Town source = getTown(town1);
        Town destination = getTown(town2);
        
        if (source != null && destination != null) {
            return graph.containsEdge(source, destination);
        }
        
        return false;
    }
	
	/**
	 * Creates an arraylist of all road names sorted.
	 * 
	 * @return an arraylist of all roads
	 */
    @Override
    public ArrayList<String> allRoads() {
        ArrayList<String> roads = new ArrayList<>();

        for (Road road : graph.edgeSet()) {
            roads.add(road.getName());
        }

        Collections.sort(roads);

        return roads;
    }
	
	/**
	 * Deletes a road from the graph.
	 * 
	 * @param town1          name of town 1
	 * @param town2          name of town 2
	 * @param name           road name
	 * 
	 * @return true if the road was deleted, false if not
	 */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String name) {
        Town source = getTown(town1);
        Town destination = getTown(town2);
        
        if (source != null && destination != null) {
        	
            Road road = graph.getEdge(source, destination);
            
            if (road != null && road.getName().equals(name)) {
                graph.removeEdge(source, destination, road.getWeight(), road.getName());
                return true;
            }
        }
        
        return false;
    }
	
	/**
	 * Deletes a town from the graph.
	 * 
	 * @param name      name of town
	 * 
	 * @return true if the town was deleted, false if not
	 */
    @Override
    public boolean deleteTown(String name) {
        Town town = getTown(name);
        
        if (town != null) {
            return graph.removeVertex(town);
        }
        
        return false;
    }

	/**
	 * Creates an arraylist of all towns in alphabetical order.
	 * 
	 * @return an arraylist of all towns
	 */
    @Override
    public ArrayList<String> allTowns() {
        ArrayList<String> towns = new ArrayList<>();
        
        for (Town town : graph.vertexSet()) {
            towns.add(town.getName());
        }
        
        Collections.sort(towns);
        
        return towns;
    }
	
	/**
	 * Returns the shortest path from town 1 to town 2.
	 * 
	 * @param name1        name of town 1
	 * @param name2        name of town 2
	 * 
	 * @return an Arraylist of roads connecting two towns together
	 */
    @Override
    public ArrayList<String> getPath(String name1, String name2) {
        Town town1 = getTown(name1);
        Town town2 = getTown(name2);

        if (town1 == null || town2 == null) {
            return null;
        }

        return graph.shortestPath(town1, town2);
    }
    
    /**
     * Populates the town graph with data from the given file.
     *
     * @param file         The file to read the town and road info from
     * 
     * @throws FileNotFoundException     If the specified file is not found
     * @throws IOException               If an error occurs while reading the file
     */
    public void populateTownGraph(File file) throws FileNotFoundException, IOException {
    	
    	try (Scanner scanner = new Scanner(file)) {
    		
			while (scanner.hasNext()) {
				
				String[] elements = scanner.nextLine().split(";");
				
				String[] roadElements = elements[0].split(",");
				
				String roadName = roadElements[0];
				Integer distance = Integer.parseInt(roadElements[1]);
				
				String town1 = elements[1];
				String town2 = elements[2];
				
				graph.addVertex(new Town(town1));
				graph.addVertex(new Town(town2));
				
				Town source = getTown(town1);
		        Town destination = getTown(town2);
		        
		        if (source != null && destination != null) {
		            graph.addEdge(source, destination, distance, roadName);
		        }
			}
		}
        
    }
}
