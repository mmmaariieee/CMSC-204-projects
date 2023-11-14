import java.util.ArrayList;

/**
 * A MorseCodeTree class represents a tree that is used for
 * the conversion of Morse code to English.
 * It uses a generic TreeNode class.
 * 
 * @author Mariia Honcharenko
 */

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {

	private TreeNode<String> root;
	
	/**
     * Constructor calls the buildTree method to create the MorseCodeTree.
     */
    public MorseCodeTree() {
        buildTree();
    }
    
    /**
     * Returns the root of the Morse code tree.
     * 
     * @return The root of the tree.
     */
    @Override
    public TreeNode<String> getRoot() {
        return root;
    }

    /**
     * Sets the root of the Morse code tree.
     * 
     * @param node The new root.
     */
    @Override
    public void setRoot(TreeNode<String> node) {
        this.root = node;
    }
    
    /**
     * Inserts a new letter into the tree.
     * 
     * @param code The Morse code path to the letter.
     * @param letter The letter that represents the code.
     */
    @Override
    public void insert(String code, String letter) {
        addNode(root, code, letter);
    }
    
    /**
     * Adds a letter to the tree at the location represented by the code.
     * 
     * @param root The current root.
     * @param code The Morse code path that shows where to insert the letter.
     * @param letter The letter that is inserted.
     */
    @Override
    public void addNode(TreeNode<String> root, String code, String letter) {
        if (code.length() == 1) {
            if (code.equals(".")) {
                root.setLeft(new TreeNode<>(letter));
            } else if (code.equals("-")) {
                root.setRight(new TreeNode<>(letter));
            }
        } else {
            if (code.charAt(0) == '.') {
                if (root.getLeft() == null) {
                    root.setLeft(new TreeNode<>(""));
                }
                addNode(root.getLeft(), code.substring(1), letter);
            } else if (code.charAt(0) == '-') {
                if (root.getRight() == null) {
                    root.setRight(new TreeNode<>(""));
                }
                addNode(root.getRight(), code.substring(1), letter);
            }
        }
    }
    
    /**
     * Returns a letter from the tree based on the code.
     * 
     * @param code The Morse code that represents the path to the letter.
     * @return The letter that represents given Morse code.
     */
    @Override
    public String fetch(String code) {
        return fetchNode(root, code);
    }
    
    /**
     * Searches the tree to find the letter that represents the Morse code.
     * 
     * @param root The current root during the search.
     * @param code The remaining Morse code for the search.
     * @return The letter that represents given Morse code.
     */
    @Override
    public String fetchNode(TreeNode<String> root, String code) {
        if (code.length() == 1) {
            if (code.equals(".")) {
                return root.getLeft().getValue();
            } else if (code.equals("-")) {
                return root.getRight().getValue();
            }
        } else {
            if (code.charAt(0) == '.') {
                return fetchNode(root.getLeft(), code.substring(1));
            } else if (code.charAt(0) == '-') {
                return fetchNode(root.getRight(), code.substring(1));
            }
        }
        return null;
    }
    
    /**
     * @throws UnsupportedOperationException because deletion is not allowed in this tree.
     */
    @Override
    public MorseCodeTree delete(String data) {
        throw new UnsupportedOperationException("Deleting the letter is not allowed");
    }

    /**
     * @throws UnsupportedOperationException because updating is not allowed in this tree.
     */
    @Override
    public MorseCodeTree update() {
        throw new UnsupportedOperationException("Updating the letter is not allowed");
    }
    
    /**
     * Builds the MorseCodeTree.
     */
    @Override
    public void buildTree() {
        root = new TreeNode<>("");

        insert(".", "e");
        insert("-", "t");
        insert("..", "i");
        insert(".-", "a");
        insert("-.", "n");
        insert("--", "m");
        insert("...", "s");
        insert("..-", "u");
        insert(".-.", "r");
        insert(".--", "w");
        insert("-..", "d");
        insert("-.-", "k");
        insert("--.", "g");
        insert("---", "o");
        insert("....", "h");
        insert("...-", "v");
        insert("..-.", "f");
        insert(".-..", "l");
        insert(".--.", "p");
        insert(".---", "j");
        insert("-...", "b");
        insert("-..-", "x");
        insert("-.-.", "c");
        insert("-.--", "y");
        insert("--..", "z");
        insert("--.-", "q");
    }
    
    /**
     * Creates an ArrayList in LNR traversal order.
     * 
     * @return An ArrayList with the elements from the tree in LNR order.
     */
    public ArrayList<String> toArrayList() {
        ArrayList<String> list = new ArrayList<>();
        LNRoutputTraversal(root, list);
        return list;
    }
    
    /**
     * Performs an LNR traversal of the tree.
     * 
     * @param root The current root during the traversal.
     * @param list The ArrayList to which the elements are added.
     */
    public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
        if (root != null) {
            LNRoutputTraversal(root.getLeft(), list);
            list.add(root.getValue());
            LNRoutputTraversal(root.getRight(), list);
        }
    }

}
