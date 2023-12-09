import java.util.*;

/**
 * The Graph class implements the GraphInterface.
 * It represents a graph of vertices (towns) and  edges (roads).
 * It uses Dijkstra's Shortest Path algorithm to find the shortest path between two towns.
 *
 * @param <Town>  the vertex
 * @param <Road>  the edge
 * 
 * @author Mariia Honcharenko
 */
public class Graph implements GraphInterface<Town, Road> {

	private Set<Town> towns;
    private Set<Road> roads;
    private Map<Town, Integer> shortestRoads;
    private Map<Town, Town> prevTowns;
    
    /**
     * Constructor with no towns or roads.
     */
    public Graph() {
        towns = new HashSet<>();
        roads = new HashSet<>();
    }
    
    /**
     * Returns an edge connecting source town to destination town if such
     * vertices and such edge exist in this graph.
     *
     * @param source          source town of the road
     * @param destination     destination town of the road
     *
     * @return a road connecting source town to destination town
     */
    @Override
    public Road getEdge(Town source, Town destination) {
        for (Road road : roads) {
            if (road.contains(source) && road.contains(destination)) {
                return road;
            }
        }
        return null;
    }
    
    /**
     * Creates a new edge in this graph, and returns the created edge.
     *
     * @param source          source town of the road
     * @param destination     destination town of the road
     * @param distance        distance between towns
     * @param name            name of the road
     *
     * @return The new created road if added to the graph, if no - null
     *
     * @throws IllegalArgumentException if source or destination town is not found in the graph
     * @throws NullPointerException if any of the specified vertices is null
     */
    @Override
    public Road addEdge(Town source, Town destination, int distance, String name) {
        if (!containsVertex(source) || !containsVertex(destination) || distance < 0) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        if (source == null || destination == null) {
            throw new NullPointerException("Source town and destination town cannot be null");
        }

        Road road = new Road(source, destination, distance, name);
        roads.add(road);
        return road;
    }
    
    /**
     * Adds the town to this graph if not already present.
     *
     * @param town          town to be added to this graph
     *
     * @return true if this graph did not already contain the specified town
     *
     * @throws NullPointerException if the specified town is null
     */
    @Override
    public boolean addVertex(Town town) {
        if (town == null) {
            throw new NullPointerException("Town cannot be null");
        }

        return towns.add(town);
    }
    
    /**
     * Returns true if graph contains a road going from the source town
     * to the destination town.
     *
     * @param source         source town of the road
     * @param destination    destination town of the road
     *
     * @return true if this graph contains the specified road
     */
    @Override
    public boolean containsEdge(Town source, Town destination) {
        return getEdge(source, destination) != null;
    }
    
    /**
     * Returns true if graph contains the specified town.
     *
     * @param town          town whose presence in this graph is to be tested
     *
     * @return true if this graph contains the specified town
     */
    @Override
    public boolean containsVertex(Town town) {
        return towns.contains(town);
    }
    
    /**
     * Returns a set of the roads contained in this graph.
     *
     * @return a set of the roads
     */
    @Override
    public Set<Road> edgeSet() {
        return roads;
    }
    
    /**
     * Returns a set of all roads touching the specified town.
     *
     * @param town       the town for which a set of touching roads is returned
     *
     * @return a set of all roads touching the specified town
     *
     * @throws IllegalArgumentException if town is not found in the graph
     * @throws NullPointerException if town is null
     */
    @Override
    public Set<Road> edgesOf(Town town) {
        Set<Road> adjRoads = new HashSet<>();
        for (Road road : roads) {
            if (road.contains(town)) {
                adjRoads.add(road);
            }
        }
        return adjRoads;
    }
    
    /**
     * Removes a road if such towns and such road exist in this graph.
     *
     * @param source         source town of the road
     * @param destination    destination town of the road
     * @param distance       distance between towns
     * @param name           name of the road
     *
     * @return The removed road, or null if no road removed
     */
    @Override
    public Road removeEdge(Town source, Town destination, int distance, String name) {
        Road road = getEdge(source, destination);
        if (road != null && road.getWeight() == distance && road.getName().equals(name)) {
            roads.remove(road);
            return road;
        }
        return null;
    }
    
    /**
     * Removes the specified town from graph including all its touching
     * roads if present.
     *
     * @param town           town to be removed from graph
     *
     * @return true if the graph contained the specified vertex, false if not
     */
    @Override
    public boolean removeVertex(Town town) {
        if (!containsVertex(town)) {
            return false;
        }
        towns.remove(town);
        Iterator<Road> rIterator = roads.iterator();
        while (rIterator.hasNext()) {
            Road road = rIterator.next();
            if (road.contains(town)) {
                rIterator.remove();
            }
        }
        return true;
    }
    
    /**
     * Returns a set of the towns contained in this graph.
     *
     * @return a set of the towns in graph
     */
    @Override
    public Set<Town> vertexSet() {
        return towns;
    }
    
    /**
     * Dijkstra's Shortest Path Method.
     * 
     * @param source        the town to find shortest path from
     */
    @Override
    public void dijkstraShortestPath(Town source) {
    	
    	// Initialize data structures to store shortest distances and previous towns
    	shortestRoads = new HashMap<>();
    	prevTowns = new HashMap<>();
    	Set<Town> unvisitedTowns = new HashSet<>(towns);
    	
    	// Initialize shortest distances for all towns to infinity
        for (Town town : towns) {
            shortestRoads.put(town, Integer.MAX_VALUE);
        }
        
        // Initialize the distance from sourse town to itself to 0
        shortestRoads.put(source, 0);
        
        while (!unvisitedTowns.isEmpty()) {
        	Town currentTown = null;
            int shortestRoad = Integer.MAX_VALUE;

            // Find the unvisited town with the shortest distance
            for (Town town : unvisitedTowns) {
            	
                int distance = shortestRoads.get(town);
                // Compare distances and update if a shorter distance is found
                if (distance < shortestRoad) {
                    shortestRoad = distance;
                    currentTown = town;
                }
            }
            
            // If there are no reachable towns left, break out of the loop
            if (currentTown == null) {
                break;
            }
            
            // Remove towm from the list of unvisited towns
            unvisitedTowns.remove(currentTown);
            
            // Iterate through roads connected to the current town
            for (Road road : edgesOf(currentTown)) {
            	
            	// Find the adj town
                Town adjTown = road.getDestination().equals(currentTown) ? road.getSource() : road.getDestination();
                
                // Calculate the possible distance to the adj town through the current town
                int possibleDistance = shortestRoads.get(currentTown) + road.getWeight();
                
                // Check if the possible distance is shorter than the current shortest distance
                if (possibleDistance < shortestRoads.get(adjTown)) {
                	
                	// Update shortest distance and set current town as previous town
                    shortestRoads.put(adjTown, possibleDistance);
                    prevTowns.put(adjTown, currentTown);
                }
            }
        }
        
    }
    
    /**
     * Find the shortest path from the source town to the destination town
     * using the dijkstraShortestPath.
     * 
     * @param source          starting town
     * @param destination     ending town
     * @return An arraylist of Strings that describe the path from source to destination
     */   
    @Override
    public ArrayList<String> shortestPath(Town source, Town destination) {
        dijkstraShortestPath(source);

        ArrayList<String> route = new ArrayList<>();
        Town currentTown = destination;
        
        // If the destination is unreachable, return an empty route
//        if (shortestRoads.get(destination) == Integer.MAX_VALUE) {
//        	System.out.println(destination);
//            return new ArrayList<>(); // Destination is not reachable, return an empty route
//        }

        // Iterate through the prevTowns to build the route
        while (currentTown != null) {
            Town prevTown = prevTowns.get(currentTown);
            Road road = getEdge(prevTown, currentTown);
            if (road != null) {
            	// Add road details to the route
                route.add(prevTown + " via " + road.getName() + " to " + currentTown + " " + road.getWeight() + " mi");
            }
            currentTown = prevTown;
        }

        // Reverse the route to return it in the correct order
        Collections.reverse(route);
        
        return route;
    }
      
}
