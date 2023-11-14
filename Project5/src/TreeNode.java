/**
 * A generic TreeNode class is used in the MorseCodeTree class.
 * The class consists of a reference to the data and a reference
 * to the left and right child.  
 * 
 * @param <T> The data type of TreeNode.
 * 
 * @author Mariia Honcharenko
 */

public class TreeNode<T> {
	
	// Value that node stores
    private T value;
    // Reference to the left child
    private TreeNode<T> left;
    // Reference to the right child
    private TreeNode<T> right;

    /**
     * Creates a new TreeNode, sets value to the dataNode, and sets left and right child to null .
     * 
     * @param dataNode The data that TreeNode stores.
     */
    public TreeNode(T dataNode) {
    	this.value = dataNode;
    	left = null;
    	right = null;
    }
    
    /**
     * Makes deep copies of nodes.
     * 
     * @param node The node that is copied.
     */
    public TreeNode(TreeNode<T> node) {
        this.value = node.value;
        
        // Copy a reference to the left child if it exists
        if (node.left != null) {
            this.left = new TreeNode<>(node.left);
        } else {
            this.left = null;
        }

        // Copy a reference to the right child if it exists
        if (node.right != null) {
            this.right = new TreeNode<>(node.right);
        } else {
            this.right = null;
        }
    }
    
    // Getter methods
    public T getValue() {
        return value;
    }
    
    public TreeNode<T> getLeft() {
        return left;
    }
    
    public TreeNode<T> getRight() {
        return right;
    }
    
    // Setter methods
    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }
    
    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}
