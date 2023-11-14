import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for MorseCodeTree.
 * 
 * @author Mariia Honcharenko
 */
class MorseCodeTreeTest {

	private MorseCodeTree morseCodeTree;
	
	/**
     * Creates MorseCodeTree for testing.
     */
	@BeforeEach
    void setUp() {
        morseCodeTree = new MorseCodeTree();
    }

	/**
     * Test the getRoot method to verify if the root is null or not.
     */
	@Test
    void testGetRoot() {
        assertNotNull(morseCodeTree.getRoot());
    }
	
	/**
     * Test fetch, buildTree and insert methods.
     */
	@Test
	void testFetch() {
        assertEquals("h", morseCodeTree.fetch("...."));
        assertEquals("e", morseCodeTree.fetch("."));
        assertEquals("i", morseCodeTree.fetch(".."));
        assertEquals("a", morseCodeTree.fetch(".-"));
    }
	
	/**
     * Test the toArrayList method to verify it returns a correct list of tree elements.
     */
    @Test
    void testToArrayList() {
        ArrayList<String> list = morseCodeTree.toArrayList();
        assertEquals(27, list.size());
        assertEquals("h", list.get(0));
        assertEquals("o", list.get(list.size() - 1));
    }
	
	/**
     * Test the delete method to verify it throws an UnsupportedOperationException.
     */
    @Test
    void testDeleteUnsupported() {
        assertThrows(UnsupportedOperationException.class, () -> morseCodeTree.delete("m"));
    }

    /**
     * Test the update method to verify it throws an UnsupportedOperationException.
     */
    @Test
    void testUpdateUnsupported() {
        assertThrows(UnsupportedOperationException.class, () -> morseCodeTree.update());
    }
}
