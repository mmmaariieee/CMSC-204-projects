import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * CourseDBManager_STUDENT_Test is a set of JUnit tests for the CourseDBManager class.
 * These tests evaluate methods in the CourseDBManager implementation.
 * 
 * @author Mariia Honcharenko
 */
public class CourseDBManager_STUDENT_Test {
    private CourseDBManagerInterface dataMgr = new CourseDBManager();

    /**
     * Set up the data manager instance before each test.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        dataMgr = new CourseDBManager();
    }

    /**
     * Clean up and set data manager reference to null after each test.
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        dataMgr = null;
    }

    /**
     * Test the add method for adding a course to the database.
     */
    @Test
    public void testAddCourseToDB() {
        try {
            dataMgr.add("MATH101", 10101, 3, "MH101", "Prof. Johnson");
        } catch (Exception e) {
            fail("This should not have caused an Exception");
        }
    }

    /**
     * Test the showAll method for displaying all courses in the database.
     */
    @Test
    public void testShowAllCourses() {
        dataMgr.add("MATH101", 20101, 3, "MH101", "Prof. Johnson");
        dataMgr.add("PHYS202", 20202, 4, "PH220", "Dr. Anderson");
        dataMgr.add("CHEM303", 20303, 4, "CH330", "Dr. Davis");
        ArrayList<String> list = dataMgr.showAll();
        assertEquals(list.get(1), "\nCourse:MATH101 CRN:20101 Credits:3 Instructor:Prof. Johnson Room:MH101");
        assertEquals(list.get(0), "\nCourse:PHYS202 CRN:20202 Credits:4 Instructor:Dr. Anderson Room:PH220");
        assertEquals(list.get(2), "\nCourse:CHEM303 CRN:20303 Credits:4 Instructor:Dr. Davis Room:CH330");
    }

    /**
     * Test the readFile method for reading course information from a file.
     */
    @Test
    public void testReadCoursesFromFile() {
        try {
            File inputFile = new File("StudentTest_New.txt");
            PrintWriter inFile = new PrintWriter(inputFile);
            inFile.println("BIOL205 20505 4 LS210 Dr. Smith");
            inFile.println("ECON301 20111 3 SS100 Prof. Martinez");

            inFile.close();
            dataMgr.readFile(inputFile);
            assertEquals("BIOL205", dataMgr.get(20505).getID());
            assertEquals("ECON301", dataMgr.get(20111).getID());
            assertEquals("LS210", dataMgr.get(20505).getRoomNum());
            assertEquals("Prof. Martinez", dataMgr.get(20111).getInstructor());
        } catch (Exception e) {
            fail("Should not have thrown an exception");
        }
    }
}
