import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This test class is designed to verify the MorseCodeConverter class.
 * 
 * @author Mariia Honcharenko
 */

public class MorseCodeConverterTest {

	/**
     * Test conversion of a simple Morse code string to its English equivalent.
     */
	@Test
	public void testConvertMorseStringToEnglishString1() {	
		
		String code1 = MorseCodeConverter.convertToEnglish(".. / .-.. --- ...- . / -.-. --- -- .--. ..- - . .-. / ... -.-. .. . -. -.-. .");
		assertEquals("i love computer science", code1);
	}
	
	/**
     * Test conversion of a more complex Morse code string to its English equivalent.
     */
	@Test
	public void testConvertMorseStringToEnglishString2() {	
		
		String code2 = MorseCodeConverter.convertToEnglish(".. - / .. ... / -.. ..- .-. .. -. --. / --- ..- .-. / -.. .- .-. -.- . ... - / -- --- -- . -. - ... / - .... .- - / .-- . / -- ..- ... - / ..-. --- -.-. ..- ... / - --- / ... . . / - .... . / .-.. .. --. .... -");
		assertEquals("it is during our darkest moments that we must focus to see the light", code2);
	}
	
	/**
     * Test conversion of a Morse code file to its English equivalent.
     * The file "code1.txt" is supposed t contain a Morse code message that is equal to the expected English string.
     */
	@Test
	public void testConvertMorseFileToEnglishString1() {	
		
		File file = new File("src/code1.txt"); 
		try {
			assertEquals("life is a succession of lessons which must be lived to be understood", MorseCodeConverter.convertToEnglish(file));
		} catch (FileNotFoundException e) {
			assertTrue("An exception was caught", false);
		}
	}
	
	/**
     * Test conversion of a more complex and lengthy Morse code file to its English equivalent.
     * The file "code2.txt" is supposed contain an extensive Morse code message that is equal to the expected long English string.
     */
	@Test
	public void testConvertMorseFileToEnglishString2() {	
		
		File file = new File("src/code2.txt"); 
		try {
			assertEquals("mitosis is a part of the cell cycle in which replicated chromosomes are separated into two new nuclei cell division by mitosis is an equational division which gives rise to genetically identical cells in which the total number of chromosomes is maintained mitosis is preceded by the s phase of interphase during which dna replication occurs and is followed by telophase and cytokinesis which divides the cytoplasm organelles and cell membrane of one cell into two new cells containing roughly equal shares of these cellular components", MorseCodeConverter.convertToEnglish(file));
		} catch (FileNotFoundException e) {
			assertTrue("An exception was caught", false);
		}
	}
	
}
