import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CourseDBManager is a class that implements the CourseDBManagerInterface
 * and manages a database of courses.
 * It has methods to add, retrieve, read from a file, and display course information.
 * 
 * @author Mariia Honcharenko
 */
public class CourseDBManager implements CourseDBManagerInterface {
	private CourseDBStructure courseStructure;
	
	/**
     * Constructor for CourseDBManager. Initializes the courseStructure with a
     * specified size.
     */
	public CourseDBManager() {
		courseStructure = new CourseDBStructure(500);
    }
	
	/**
     * Adds a new course to the database.
     *
     * @param id         the course ID
     * @param crn        the course registration number
     * @param credits    the number of credits
     * @param roomNum    the room number
     * @param instructor the instructor's name
     */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
        CourseDBElement course = new CourseDBElement(id, crn, credits, roomNum, instructor);
        courseStructure.add(course);
    }

	/**
     * Retrieves a course with the specified CRN from the database.
     *
     * @param crn the course registration number
     * @return the course with the specified CRN, or null if not found
     */
	@Override
	public CourseDBElement get(int crn) {
		try {
	        CourseDBElement course = courseStructure.get(crn);
	        return course;
	    } catch (IOException e) {
	        return null;
	    }
	}
	
	/**
     * Reads course information from a file and adds it to the database.
     *
     * @param input the input file containing course information
     * @throws FileNotFoundException if the input file is not found
     */
	@Override
    public void readFile(File input) throws FileNotFoundException {
        try {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                String[] fields = str.split(" ", 5);
                if (fields.length == 5) {
                    String id = fields[0];
                    int CRN = Integer.parseInt(fields[1]);
                    int credits = Integer.parseInt(fields[2]);
                    String roomNum = fields[3];
                    String instructor = fields[4];

                    add(id, CRN, credits, roomNum, instructor);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("The input file could not be found.");
        }
    }
	
	/**
     * Returns an ArrayList of string representations of all courses in the database.
     *
     * @return an ArrayList of string representations of courses
     */
	@Override
	public ArrayList<String> showAll() {
		return courseStructure.showAll();
	}

}
