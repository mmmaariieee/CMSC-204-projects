/**
 * CourseDBElement defines a course within the course database.
 * It encapsulates details about the course, such as its course ID, CRN,
 * credit hours, room number, and instructor.
 * 
 * @author Mariia Honcharenko
 */

public class CourseDBElement implements Comparable<CourseDBElement> {

	private String id;
	private int CRN;
	private int credits;
	private String roomNum;
	private String instructor;
	
    /**
     * Constructs a CourseDBElement with the specified information.
     * 
     * @param id         the course ID
     * @param CRN        the CRN (Course Registration Number)
     * @param credits    the number of credits
     * @param roomNum    the room number
     * @param instructor the name of the instructor
     */
	public CourseDBElement (String id, int CRN, int credits, String roomNum, String instructor) {
		this.id = id;
		this.CRN = CRN;
		this.credits = credits;
		this.roomNum = roomNum;
		this.instructor = instructor;
	}
	
    /**
     * Default constructor to initialize the CourseDBElement with empty values.
     */
	public CourseDBElement () {
		id = "";
		CRN = 00000;
		credits = 0;
		roomNum = "";
		instructor = "";
	}
	
    // Getter methods
	public String getID () {
		return id;
	}
	
	public int getCRN() {
        return CRN;
    }

    public int getCredits() {
        return credits;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public String getInstructor() {
        return instructor;
    }
    
    // Setter methods
    public void setID(String id) {
        this.id = id;
    }

    public void setCRN(int CRN) {
        this.CRN = CRN;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
	
    /**
     * Compares CourseDBElement objects based on their CRN.
     * 
     * @param other the CourseDBElement to compare with
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object
     */
    @Override
	public int compareTo (CourseDBElement other) {
		return this.CRN - other.CRN;
	}
    
    /**
     * Returns a string representation of the CourseDBElement.
     * 
     * @return a string representation of the CourseDBElement
     */
    @Override
    public String toString () {
    	return "\nCourse:" + id +
    			" CRN:" + CRN +
    			" Credits:" + credits +
    			" Instructor:" + instructor +
    			" Room:" + roomNum;
    }
    
    /**
     * Parses a string and creates a CourseDBElement if the string is in a valid
     * format.
     * 
     * @param str the string to parse
     * @return a CourseDBElement if the string is valid, or null if the format is
     *         invalid
     */
    public static CourseDBElement parse (String str) {
    	String[] fields = str.split(" ");
    	if (fields.length == 5) {
    		String id = fields[0];
            int CRN = Integer.parseInt(fields[1]);
            int credits = Integer.parseInt(fields[2]);
            String roomNum = fields[3];
            String instructor = fields[4];
            
            if (isValidCRN(CRN) && isValidCredits(credits)) {
            	return new CourseDBElement (id, CRN, credits, roomNum, instructor);
            } else {
            	System.out.println("Invalid course entry: " + str);
            }
    	}
    	return null;
    }
    
    //Helper methods
    private static boolean isValidCRN (int CRN) {
    	return CRN >= 10000 && CRN <= 99999;
    }
    
    private static boolean isValidCredits (int credits) {
    	return credits >= 1 && credits <= 4;
    }
	
}
