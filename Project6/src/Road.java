/**
 * The Road class is an edge in a graph of towns.
 * It has references to the two towns, the distance between these towns, and a name for the road. This class implements the `Comparable`
 * It implements the Comparable interface.
 *
 * @author Mariia Honcharenko
 */
public class Road implements Comparable<Road> {
	
	private Town source;
	private Town destination;
	private int distance;
	private String name;
	
	/**
     * Constructor with specific distance.
     *
     * @param source      Source town of the road
     * @param destination Destination town of the road
     * @param distance    Distance from one town to the other
     * @param name        Name of the road
     */
	public Road (Town source, Town destination, int distance, String name) {
		this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.name = name;
	}
	
	/**
     * Constructor with default distance 1.
     *
     * @param source      Source town of the road
     * @param destination Destination town of the road
     * @param name        Name of the road
     */
    public Road(Town source, Town destination, String name) {
        this(source, destination, 1, name);
    }
	
    /**
     * Returns true if the edge contains the given town.
     *
     * @param town       a town of the graph
     * @return true only if the edge is connected to the given vertex
     */
    public boolean contains(Town town) {
        return source.equals(town) || destination.equals(town);
    }
    
    /**
     * Gives a string representation of the road.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return String.format("%s via %s to %s %dmi", source.getName(), name, destination.getName(), distance);
    }

    /**
     * Getter method for the road name.
     *
     * @return The name of the road
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the destination town.
     *
     * @return A town on the road
     */
    public Town getDestination() {
        return destination;
    }

    /**
     * Getter method for the source town.
     *
     * @return A town on the road
     */
    public Town getSource() {
        return source;
    }
    
    /**
     * Compares this road to another road.
     *
     * @param road     the road to compare 
     * @return 0 if the road names are the same, a positive or negative number if the road names are not the same
     */
    @Override
    public int compareTo(Road road) {
        return this.name.compareTo(road.getName());
    }
    
    /**
     * Getter method for the distance of the road.
     *
     * @return the distance of the road
     */
    public int getWeight() {
        return distance;
    }
    
    /**
     * Checks if two roads are equal.
     *
     * @param road     an object of road to compare
     * @return true if the road connects the same towns as the compared road
     */
    @Override
    public boolean equals(Object road) {
        if (this == road) return true;
        if (road == null || getClass() != road.getClass()) return false;
        Road otherRoad = (Road) road;
        return (source.equals(otherRoad.getSource()) && destination.equals(otherRoad.getDestination())) ||
               (source.equals(otherRoad.getDestination()) && destination.equals(otherRoad.getSource()));
    }
}
