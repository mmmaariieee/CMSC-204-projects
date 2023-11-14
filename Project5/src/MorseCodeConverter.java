import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * MorseCodeConverter class converts Morse code into English,
 * creates convertToEnglish and printTree methods.
 * 
 * @author Mariia Honcharenko
 */
public class MorseCodeConverter {

    private static MorseCodeTree morseCodeTree = new MorseCodeTree();

    /**
     * Constructor is not used because all methods are static
     */
    public MorseCodeConverter() {
    }

    /**
     * Converts Morse code into English. 
     * 
     * @param code The Morse code that needs to be converted.
     * @return The English translation.
     */
    public static String convertToEnglish(String code) {
        String[] words = code.split(" / ");
        StringBuilder translation = new StringBuilder();
        for (String word : words) {
            for (String codeForLetter : word.split(" ")) {
                String letter = morseCodeTree.fetch(codeForLetter);
                translation.append(letter != null ? letter : "");
            }
            translation.append(" ");
        }
        return translation.toString().trim().toLowerCase();
    }

    /**
     * Converts a file of Morse code into English.
     * 
     * @param file The File object that contains Morse Code.
     * @return The English translation of the file.
     * @throws FileNotFoundException If the file is not found.
     */
    public static String convertToEnglish(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        StringBuilder code = new StringBuilder();
        while (scanner.hasNextLine()) {
            code.append(scanner.nextLine()).append(" / ");
        }
        scanner.close();
        return convertToEnglish(code.toString().trim());
    }

    /**
     * Returns a string with the data in the tree in LNR order with a space in between .
     *
     * @return the data in the tree in LNR order separated by a space.
     */
    public static String printTree() {
        ArrayList<String> treeData = morseCodeTree.toArrayList();
        return String.join(" ", treeData);
    }
}