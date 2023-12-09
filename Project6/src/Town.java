import java.util.ArrayList;

/**
 * The Town class is a vertix in a graph of towns.
 * It has a name of the town and a list of adjacent towns.
 * It implements the Comparable interface.
 * 
 * @author Mariia Honcharenko
 */
public class Town implements Comparable<Town> {
	
	private String name;
	private ArrayList<Town> adjTowns;
	
	/**
     * Constructor with the town's name.
     *
     * @param name       the name of the town
     */
	public Town (String name) {
		this.name = name;
		this.adjTowns = new ArrayList<>();
	}

	/**
     * Copy constructor.
     *
     * @param templateTown      an object of Town to copy 
     */
    public Town(Town templateTown) {
        this.name = templateTown.getName();
        this.adjTowns = new ArrayList<>(templateTown.getAdjTowns());
    }
    
    /**
     * Getter method for the town's name.
     *
     * @return the name of the town
     */
    public String getName() {
        return name;
    }
    
    /**
     * Getter method for the list of adjacent towns.
     *
     * @return the list of adjacent towns
     */
    public ArrayList<Town> getAdjTowns() {
        return new ArrayList<>(adjTowns);
    }
    
    /**
     * Compares one town to another one.
     *
     * @param town the town to compare
     * @return 0 if names are equal, a positive or negative number if the names are not equal
     */
    @Override
    public int compareTo(Town town) {
        return this.name.compareTo(town.getName());
    }
    
    /**
     * Gives a string with the town name.
     *
     * @return the town's name
     */
    @Override
    public String toString() {
        return name;
    }
    
    /**
     * Creates a hash code for the town's name.
     *
     * @return the hash code for the town's name
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    /**
     * Checks if 2 towns are equal.
     *
     * @param town       the town to compare
     * @return true if the town names are equal, false if not
     */
    @Override
    public boolean equals(Object town) {
        if (this == town) return true;
        if (town == null || getClass() != town.getClass()) return false;
        return getName().equals(((Town) town).getName());
    }
}
