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
public class BalancedSearchTree<T extends Comparable<T>> implements SearchTreeADT<T> 
{
    protected int numNodes; //Keeps track of the number of nodes in the tree
    protected Treenode<T> root; //Keeps track of the tree's root node
    
    /**
     * Basic constructor, initializes numNodes
     */
    public BalancedSearchTree() 
    {
        numNodes = 0;
    }
    
    /**
     * Class for nodes in the binary search tree
     * @param <K> generic comparable object for key
     */
	protected class Treenode<K extends Comparable<K>> 
	{
	    K key; //treenode key
        Treenode<K> left; //left child
        Treenode<K> right; //right child
        /**
         * Basic treenode constructor
         * @param item: key
         */
		public Treenode(K item)
		{
			this(item,null,null);
		}
		/**
		 * Overloaded treenode constructor
		 * @param item: key
		 * @param left: left child
		 * @param right: right child
		 */
		public Treenode(K item, Treenode<K> left, Treenode<K> right) 
		{
			key = item;
			this.left = left;
			this.right = right;
		}
	}
	
	/**
	 * Provides the minimum descendant node of a given node
	 * @param node: the given node
	 * @return the given nodes minimum descendant
	 */
	private Treenode<T> minDescendant(Treenode<T> node)
	{
	    //traverse left until you reach an node with no left child
	    while(node.left != null)
	    {
	      //the first node with no left child is the descendant with the lowest key
            node = node.left; 
	    }
	    return node;
	        
	}
	
	/**
     * Method used to rebalance tree
     * Rotates nodes one to the right with "a" as a focal point
     * @param b, the node we are rotating from
     * @return the node that replaces b after rotation
     */
    private Treenode<T> rotateRight(Treenode<T> b)
    {
        //a is the node to the left of the root that will become the new root
        Treenode<T> a = b.left;
        //Y is the subtree that switches parents in rotations (the middle one)
        Treenode<T> Y = a.right; 
        //assigning b as the right child of the new root
        a.right = b;
        //swapping Y's parent
        b.left = Y;
        //reassigning root
        if(b.equals(root))
        {
            root = a;
        }
        return a; //return the new root node of the subtree (or full tree if b is the root)
    }

    /**
	 * Method used to rebalance tree
	 * Rotates nodes one to the right with "b" as a focal point
	 * @param b, the node we are rotating from
	 * @return the new root of the subtree
	 */
    private Treenode<T> rotateLeft(Treenode<T> a)
	{
        //b is the node to the right of the root that will become the new root
	    Treenode<T> b = a.right;
	    //Y is the subtree that must swap parents
	    Treenode<T> Y = b.left;
	    //assigning a as b's left child
	    b.left = a;
	    //swapping Y's parent
	    a.right = Y;
	    //reassigning root
	    if(a.equals(root))
	    {
	        root = b;
	    }
	    return b; //return the new root of the subtree
	}
	
	/**
	 * @return a string containing the keys of each node in the tree in ascending order seperated by commas
	 */
	public String inAscendingOrder()
	{
	    //if the tree is empty return an empty string
	    if (numNodes == 0)
	    {
	        return "";
	    }
	    //otherwise call the helper method
        return ascendingHelper(root);
    }
    
	/**
	 * Helper method for inAscendingOrder() method
	 * @param node: the node being visited
	 * @return A string of the keys of the nodes up to "node" in order
	 */
    private String ascendingHelper(Treenode<T> node)
    {
        String result = "";
        //follow in-order traversal algorithm
        if (node.left != null) 
        {
            result = ascendingHelper(node.left);
        }
        result += node.key.toString() + ",";
        if (node.right != null)
        {
            result += ascendingHelper(node.right);
        }
        return result;
    }

	/**
	 * @return true if the tree is empty and false otherwise
	 */
	public boolean isEmpty() 
	{
	    //if numNodes is 0 it's empty
        if (numNodes == 0) 
        {
            return true;
        }
        return false;
    }

	/**
	 * @return height of the tree
	 */
	public int height() 
	{
	    //empty tree case
        if (isEmpty())
        {
        return 0;
        }
        //only root case
        else if (root.right == null && root.left == null) 
        {
            return 1;
        }
        //neither
        else
        {
            //add the size of the maximum of the right and left subtrees to 1 for the root
            return 1 + Math.max(subTreeHeight(root.right), subTreeHeight(root.left));
        }
    }
    
	/**
	 * @return true if the tree contains a node with item as its key
	 */
	public boolean lookup(T item) {
	    //nodes can't have null keys
	    if (item == null) 
        {
	        throw new IllegalArgumentException();
        }
	    //there are no nodes
        if (isEmpty())
        {
            return false;
        }
        else 
        {
            //perform a binary search tree traversal until you find a node with item as its key 
            //and return true or you reach a leaf in which case return false
            Treenode<T> currNode = root;
            boolean found = false;
            while (!found && currNode != null) 
            {
                if (currNode.key.compareTo(item) == 0) 
                {
                    found = true;
                }
                else if (currNode.key.compareTo(item) > 0)
                {
                    currNode = currNode.left;
                }
                else 
                {
                    currNode = currNode.right;
                }
            }
            return found;
        }
    }
    
	/**
	 * Returns the balance factor of a given node
	 * @param node: the node in question
	 * @return the balance factor of node
	 */
    private int balanceFactor(Treenode<T> node) 
	{
        //if the tree is empty the balance factor is 0
        if (node == null) 
        {
            return 0;
        }
        //the balance factor is equal to the difference in heights of the left and right subtrees
        return subTreeHeight(node.left) - subTreeHeight(node.right);
    }
    
    /**
     * Returns the height of a subtree starting with "node"
     * @param node: the root of the subtree
     * @return the height of the subtree with "node" as a root
     */
    private int subTreeHeight(Treenode<T> node) 
    {
        //base case
        if (node == null) 
        {
            return 0;
        }
        //recursive case
        return 1 + Math.max(subTreeHeight(node.left), subTreeHeight(node.right));
    }

	/**
	 * Inserts a new node with item as its key in the correct position
	 */
    public void insert(T item) throws IllegalArgumentException, DuplicateKeyException
	{
		//Nodes can't have null keys
	    if(item == null)
	    {
	        throw new IllegalArgumentException();
	    }
	    //Nodes can't have duplicate keys
	    else if(this.lookup(item))
	    {
	        throw new DuplicateKeyException();
	    }
	    //If the tree is empty create a new node at the root with key "item"
	    if(this.isEmpty())
        {
            root = new Treenode<T>(item);
        }
	    //Otherwise call insertHelper
	    else
        {
	        insertHelper(item, root);
        }
	    numNodes++; //increment numNodes
	    
	}
	
	/**
	 * Helper method for BST insertion algorithm
	 * @param item: item to be inserted
	 * @param currNode: the current node the algorith is on
	 * @return currNode or the node that replaces currNode in the case of a rotation to fix balance
	 */
    public Treenode<T> insertHelper(T item, Treenode<T> currNode)
	{
	    //the node has been inserted
	    if(currNode.key == item)
	    {
	        return null;
	    }
	    //traverse the tree to find the correct position to insert the node
	    //going left
	    if(item.compareTo(currNode.key) < 0)
	    {
	        //(base case) if we've reached a leaf, set the correct child to a newNode with "item" as its key
	        if(currNode.left == null)
	        {
	            Treenode<T> newNode = new Treenode<T>(item);
	            currNode.left = newNode;
	        }
	        //(recursive case) otherwise, continue traversal
	        else
	        {
	            currNode.left = insertHelper(item, currNode.left);
	        }
	    }
	    //going right
	    else
	    {
	        //(base case) if we've reached a leaf, set the correct child to a newNode with "item" as its key
	        if(currNode.right == null)
	        {
	            Treenode<T> newNode = new Treenode<T>(item);
	            currNode.right = newNode;
	        }
	        //(recursive case) otherwise, continue traversal
	        else
	        {
	            currNode.right = insertHelper(item, currNode.right);
	        }
	    }
	    
	    //Checking to see if the tree needs to be rebalanced
	    int bal = this.balanceFactor(currNode);
	    
	    //insertion at right right
        if(bal < -1 && item.compareTo(currNode.right.key) > 0)
        {
            return rotateLeft(currNode);
        }
	    //insertion at left left
	    if(bal > 1 && item.compareTo(currNode.left.key) < 0)
	    {
	        return rotateRight(currNode);
	    }
	    //insertion at right left
	    if(bal < -1 && item.compareTo(currNode.right.key) < 0)
	    {
	        currNode.right = rotateRight(currNode.right);
	        return rotateLeft(currNode);
	    }
	    //insertion at left right
	    if(bal > 1 && item.compareTo(currNode.left.key) > 0)
	    {
	        currNode.left = rotateLeft(currNode.left);
	        return rotateRight(currNode);
	    }
	    
	    return currNode;
	}
	 
	/**
	 * If the tree contains a node with item as its key, remove it from the tree and rebalance
	 */
	public void delete(T item) 
	{
	    //No nodes should have null keys
	    if(item == null)
	    {
	        throw new IllegalArgumentException();
	    }
	    //Only delete if the given key is in the tree
		if(lookup(item))
		{
		    deleteHelper(item, root);
		    numNodes--; //decrement the variable
		}
		
		
	}
	
	/**
	 * Helper method for delete
	 * Performs normal BST insert then travels up the tree to rebalance
	 * @param item: the key of the node we are deleting
	 * @param currNode: the node that the helper method is currently checking
	 * @return the current node or the node that replaces the current node if a rotation occurs
	 */
	public Treenode<T> deleteHelper(T item, Treenode<T> currNode)
	{
	    //Traversing tree to find the node with key "item"
	    if(item.compareTo(currNode.key) < 0)
	    {
	        currNode.left = deleteHelper(item, currNode.left);
	    }
	    else if(item.compareTo(currNode.key) > 0)
	    {
	        currNode.right = deleteHelper(item, currNode.right);
	    }
	    //Reached the node to be deleted
	    else
	    {
	        //Deleting a leaf node
	        if(currNode.left == null && currNode.right == null)
	        {
	            System.out.println();
	            currNode = null;
	        }
	        //Deleting a node with one child
	        else if(currNode.left == null || currNode.right == null)
	        {
	            if(currNode.left == null)
	            {
	                currNode = currNode.right;
	                currNode.right = null;
	            }
	            else
	            {
	                currNode = currNode.left;
	                currNode.left = null;
	            }

	        }
	        //Deleting an internal node
	        else
	        {
	            Treenode<T> descendant =  this.minDescendant(currNode.right);
	            currNode.key = descendant.key;
	            System.out.println();
                printTree();
                System.out.println();
	            currNode.right = deleteHelper(descendant.key, currNode.right);
	            
	        }
	    }
	    System.out.println("checking balance");
        int bal = this.balanceFactor(currNode);
        //right right
        if(bal < -1 && balanceFactor(currNode.right) <= 0)
        {
            return rotateLeft(currNode);
        }
        //left left
        if(bal > 1 && balanceFactor(currNode.left) >= 0)
        {
            return rotateRight(currNode);
        }
        //right left
        if(bal < -1 && balanceFactor(currNode.right) > 0)
        {
            currNode.right = rotateRight(currNode.right);
            return rotateLeft(currNode);
        }
        //left right
        if(bal > 1 && balanceFactor(currNode.left) < 0)
        {
            currNode.left = rotateLeft(currNode.left);
            return rotateRight(currNode);
        }
        
        return currNode;
	}
	
	/**
	 * Utility method for printTree method
	 * Prints a level "level" of tree
	 * @param currentNode: Starting node
	 * @param level: Level of tree to print
	 */
	private void printLevel(Treenode<T> currentNode, int level)
	{
	    if(!(currentNode == null))
	    {
	        //base case
	        if(level == 1)
	        {
	            System.out.print(currentNode.key + " ");
	        }
	        //recursive case
	        else
	        {
	            printLevel(currentNode.left, level-1);
	            printLevel(currentNode.right, level-1);
	        }
	    }
	    //to make it more readable
	    else
	    {
	        System.out.print("null ");
	    }
	}
	
	/**
	 * print tree method for testing
	 * prints each level of the tree on a separate line
	 */
	private void printTree()
	{
	    int currentLevel = 1;
	    int treeHeight = this.height();
	    //print each level of the tree until you reach the last level
	    while(currentLevel <= treeHeight)
	    {
	        this.printLevel(root, currentLevel);
	        currentLevel++;
	        System.out.println(); //seperate lines
	    }
	   
	}
}


