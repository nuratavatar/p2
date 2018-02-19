///////////////////////////////////////////////////////////////////////////////
// Title:            P2 (Implemented Using AVL Tree)
// Files:            BalancedSearchTree.java, TestSearchTree.java, SearchTreeADT.java
// Semester:         CS 400 Spring 2018
//
// Authors:          Matt Zimmers, Tarun Mandalapu
// Email:            tmandalapu@wisc.edu
// Lecturer's Name:  Debra Deppeler
// Source Credits:   https://pages.cs.wisc.edu/~deppeler/cs400/readings/AVL-Trees/index.html
// Known Bugs:       TODO
///////////////////////////////////////////////////////////////////////////////

// Matt was here

/**
 * 
 * @author tmand
 * Binary search tree implementation using AVL tree algorithm. 
 * Implements methods from SearchTreeADT given for assignment.
 * @param <T> generic comparable object for use as keys in search tree.
 */
public class BalancedSearchTree<T extends Comparable<T>> implements SearchTreeADT<T> {
    protected int numNodes; //Keeps track of the number of nodes in the tree
    protected Treenode<T> root; //Keeps track of the tree's root node
    
    public BalancedSearchTree() {
        numNodes = 0;
    }
    
    /**
     * @author tmand
     * Class for nodes in the binary search tree
     * @param <K> generic comparable object for key
     */
	protected class Treenode<K extends Comparable<K>> {
	    K key;
        Treenode<K> left;
        Treenode<K> right;
        
		public Treenode(K item) {
			this(item,null,null);
		}
		public Treenode(K item, Treenode<K> left, Treenode<K> right) {
			key = item;
			this.left = left;
			this.right = right;
		}
	}
	
	/**
	 * Method used to rebalance tree
	 * Rotates nodes one to the right with "b" as a focal point
	 * @param b, the node we are rotating from
	 */
	@SuppressWarnings("unused")
    private void rotateRight(Treenode<T> b)
	{
	    Treenode<T> a = b.right;
	    b.left = a.right;
	    a.right = b;
	    if(b.equals(root))
	    {
	        root = a;
	    }
	}
	
	/**
     * Method used to rebalance tree
     * Rotates nodes one to the left with "a" as a focal point
     * @param a, the node we are rotating from
     */
	@SuppressWarnings("unused")
	private void rotateLeft(Treenode<T> a)
	{
	    Treenode<T> b = a.left;
	    a.right = b.left;
	    b.left = a;
	    if(a.equals(root))
	    {
	        root = b;
	    }
	}
	
	/**
	 * Returns a string containing the keys of each node in the tree in ascending order seperated by commas
	 */
	public String inAscendingOrder() {
	    if (numNodes == 0) return "";
        return ascendingHelper(root);
    }
    
    private String ascendingHelper(Treenode<T> node) {
        String val = "";
        if (node.left != null) val = ascendingHelper(node.left);
        val += node.key.toString() + ",";
        if (node.right != null) val += ascendingHelper(node.right);
        return val;
    }

	/**
	 * Returns true if the tree is empty and false otherwise
	 */
	public boolean isEmpty() {
        if (numNodes == 0) return true;
        return false;
    }

	/**
	 * Returns the height of the tree
	 */
	public int height() {
        if (isEmpty()) return 0;
        else if (root.right == null && root.left == null) return 1;
        else return Math.max(heightHelper(root.right), heightHelper(root.left));
    }
    
    private int heightHelper(Treenode<T> node) {
        if (node == null) return 0;
        else return 1 + Math.max(heightHelper(node.right), heightHelper(node.left));
    }

	/**
	 * Returns true if the tree contains a node with item as its key
	 */
	public boolean lookup(T item) {
	    if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) return false;
        else {
            Treenode<T> currNode = root;
            boolean found = false;
            while (!found && currNode != null) {
                if (currNode.key.compareTo(item) == 0) found = true;
                else if (currNode.key.compareTo(item) > 0) currNode = currNode.left;
                else currNode = currNode.right;
            }
            return found;
        }
    }
	
	private int balanceFactor() {
        if (isEmpty()) return 0;
        return subTreeHeight(root.left) - subTreeHeight(root.right);
    }
    
    private int balanceFactor(Treenode<T> node) {
        if (isEmpty()) return 0;
        return subTreeHeight(node.left) - subTreeHeight(node.right);
    }
    
    private int subTreeHeight(Treenode<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(subTreeHeight(node.left), subTreeHeight(node.right));
    }

	/**
	 * Inserts a new node with item as its key in the correct position
	 */
	public void insert(T item) {
		//TODO if item is null throw IllegalArgumentException, 
		// otherwise insert into balanced search tree
	}

	/**
	 * If the tree contains a node with item as its key, remove it from the tree and rebalance
	 */
	public void delete(T item) {
		//TODO if item is null or not found in tree, return without error
		// else remove this item key from the tree and rebalance

		// NOTE: if you are unable to get delete implemented
		// it will be at most 5% of the score for this program assignment
	}

}

