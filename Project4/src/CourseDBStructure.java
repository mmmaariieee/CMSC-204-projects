import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * CourseDBStructure is a class that implements the CourseDBStructureInterface and
 * represents the data structure for managing course elements. It has methods to add,
 * retrieve, and display course information.
 * 
 * @author Mariia Honcharenko
 */
public class CourseDBStructure implements CourseDBStructureInterface {

	private LinkedList<CourseDBElement>[] hashTable;
	private int hashTableSize;
	
	/**
     * Constructs a CourseDBStructure with the estimated size.
     *
     * @param estimSize the estimated size for the data structure
     */
	public CourseDBStructure (int estimSize) {
		int hashTableSize = findNextPrime(estimSize);
		implementHashTable(hashTableSize);
	}
	
	/**
     * Constructs a CourseDBStructure for testing with a specific hash table size.
     *
     * @param testing       a testing parameter (not used for production)
     * @param hashTableSize the size of the hash table
     */
	public CourseDBStructure (String testing, int hashTableSize) {
		implementHashTable(hashTableSize);
	}
	
	/**
     * Initializes the hash table with a specified size.
     *
     * @param size the size of the hash table
     */
	@SuppressWarnings("unchecked")
	private void implementHashTable (int size) {
		hashTableSize = size;
		hashTable = new LinkedList[hashTableSize];
		for (int i = 0; i < hashTableSize; i++) {
			hashTable[i] = new LinkedList<>();
		}
	}
	
	/**
     * Adds a course element to the data structure. If a course with the same CRN
     * exists, its information is updated.
     *
     * @param element the course element to add or update
     */
	@Override
	public void add(CourseDBElement element) {
	    int i = getIndex(element.getCRN());
	    LinkedList<CourseDBElement> bucket = hashTable[i];

	    for (CourseDBElement course : bucket) {
	        if (course.getCRN() == element.getCRN()) {
	            course.setID(element.getID());
	            course.setCredits(element.getCredits());
	            course.setInstructor(element.getInstructor());
	            course.setRoomNum(element.getRoomNum());
	            return;
	        }
	    }
	    bucket.add(element);
	}

	/**
     * Finds a course element by its CRN (Course Registration Number).
     *
     * @param crn the CRN of the course to retrieve
     * @return the course element with the specified CRN
     * @throws IOException if the course with the specified CRN is not found
     */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		int i = getIndex(crn);
		LinkedList<CourseDBElement> bucket = hashTable[i];
		for (CourseDBElement element : bucket) {
			if (element.getCRN() == crn) {
				return element;
			}
		}
		throw new IOException("Course with CRN " + crn + " is not found");
	}

	/**
     * Returns an ArrayList of string representations of all courses in the data structure.
     *
     * @return an ArrayList of string representations of courses
     */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> courses = new ArrayList<>();
		for (LinkedList<CourseDBElement> bucket : hashTable) {
			for (CourseDBElement element : bucket) {
				courses.add(element.toString());
			}
		}
		return courses;
	}

	/**
     * Returns the size of the hash table.
     *
     * @return the size of the hash table
     */
	@Override
	public int getTableSize() {
		return hashTableSize;
	}
	
	/**
     * Finds the next prime number greater than or equal to the given number.
     *
     * @param num the number to find the next prime for
     * @return the next prime number
     */
	private int findNextPrime (int num) {
		int possibleNum = (int) (num / 1.5);
	    while (true) {
	        if (isPrime(possibleNum)) {
	            return possibleNum;
	        }
	        possibleNum++;
	    }
	}
	
	/**
     * Checks if a number is prime.
     *
     * @param num the number to check if it is prime
     * @return true if the number is prime, false otherwise
     */
	private boolean isPrime (int num) {
		
		if (num <= 1 || num % 4 != 3) {
	        return false;
	    }
	    
	    for (int i = 2; i <= Math.sqrt(num); i++) {
	        if (num % i == 0) {
	            return false;
	        }
	    }
	    
	    return true;
	}
	
	/**
     * Calculates the hash index for a given CRN.
     *
     * @param crn the Course Registration Number (CRN)
     * @return the hash index for the CRN
     */
	private int getIndex (int crn) {
		return Math.abs(Integer.toString(crn).hashCode()) % hashTableSize;
	}
}
