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
    
    public BalancedSearchTree() 
    {
        numNodes = 0;
    }
    
    /**
     * @author tmand
     * Class for nodes in the binary search tree
     * @param <K> generic comparable object for key
     */
	protected class Treenode<K extends Comparable<K>> 
	{
	    K key; //treenode key
        Treenode<K> left; //left child
        Treenode<K> right; //right child
        Treenode<K> parent; //parent node
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
     * Method used to rebalance tree
     * Rotates nodes one to the right with "a" as a focal point
     * @param b, the node we are rotating from
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
        return a;
    }

    /**
	 * Method used to rebalance tree
	 * Rotates nodes one to the right with "b" as a focal point
	 * @param b, the node we are rotating from
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
	    return b;
	}
	
	/**
	 * Returns a string containing the keys of each node in the tree in ascending order seperated by commas
	 */
	public String inAscendingOrder()
	{
	    if (numNodes == 0)
	    {
	        return "";
	    }
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
	 * Returns true if the tree is empty and false otherwise
	 */
	public boolean isEmpty() 
	{
        if (numNodes == 0) 
        {
        return true;
        }
        return false;
    }

	/**
	 * Returns the height of the tree
	 */
	public int height() 
	{
        if (isEmpty())
        {
        return 0;
        }
        else if (root.right == null && root.left == null) 
        {
            return 1;
        }
        else
        {
            return Math.max(subTreeHeight(root.right), subTreeHeight(root.left));
        }
    }
    
	/**
	 * Returns true if the tree contains a node with item as its key
	 */
	public boolean lookup(T item) {
	    if (item == null) 
        {
	        throw new IllegalArgumentException();
        }
        if (isEmpty())
        {
            return false;
        }
        else 
        {
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
	 * Returns the balance factor of the root
	 * @return balance factor of root
	 */
	private int balanceFactor() 
	{
        if (isEmpty())
        {
        return 0;
        }
        return subTreeHeight(root.left) - subTreeHeight(root.right);
    }
    
	/**
	 * Returns the balance factor of a given node
	 * @param node: the node in question
	 * @return the balance factor of node
	 */
    private int balanceFactor(Treenode<T> node) 
	{
        if (node == null) 
        {
        return 0;
        }
        return subTreeHeight(node.left) - subTreeHeight(node.right);
    }
    
    /**
     * Returns the height of a subtree starting with "node"
     * @param node: the root of the subtree
     * @return the height of the subtree with "node" as a root
     */
    private int subTreeHeight(Treenode<T> node) 
    {
        if (node == null) 
        {
            return 0;
        }
        return 1 + Math.max(subTreeHeight(node.left), subTreeHeight(node.right));
    }

	/**
	 * Inserts a new node with item as its key in the correct position
	 */
    public void insert(T item) throws IllegalArgumentException, DuplicateKeyException
	{
		//if item is null throw IllegalArgumentException, 
		// otherwise insert into balanced search tree
	    if(item == null)
	    {
	        throw new IllegalArgumentException();
	    }
	    else if(this.lookup(item))
	    {
	        throw new DuplicateKeyException();
	    }
	    if(this.isEmpty())
        {
            root = new Treenode<T>(item);
        }
	    else
        {
        insertHelper(item, root);
        }
	    numNodes++;
	    
	}
	
	/**
	 * Helper method for BST insertion algorithm
	 * @param item: item to be inserted
	 * @param currNode: the current node the algorith is on
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
    public Treenode<T> insertHelper(T item, Treenode<T> currNode)
	{
	    
	    if(item.compareTo(currNode.key) < 0)
	    {
	        if(currNode.left == null)
	        {
	            Treenode<T> newNode = new Treenode(item);
	            currNode.left = newNode;
	            newNode.parent = currNode;
	            return newNode;
	        }
	        else
	        {
	            insertHelper(item, currNode.left);
	        }
	    }
	    else
	    {
	        if(currNode.right == null)
	        {
	            Treenode<T> newNode = new Treenode(item);
	            currNode.right = newNode;
	            newNode.parent = currNode;
	            return newNode;
	        }
	        else
	        {
	            insertHelper(item, currNode.right);
	        }
	    }
	    
	    int balance = this.balanceFactor(currNode);
	    //insertion at right right
        if(balance < -1 && item.compareTo(currNode.right.key) > 0)
        {
            System.out.println("right right");
            return rotateLeft(currNode);
        }
	    //insertion at left left
	    if(balance > 1 && item.compareTo(currNode.left.key) < 0)
	    {
	        System.out.println("left left");
	        return rotateRight(currNode);
	    }
	    //insertion at right left
	    if(balance < -1 && item.compareTo(currNode.right.key) < 0)
	    {
	        System.out.println("right left");
	        currNode.right = rotateRight(currNode.right);
	        return rotateLeft(currNode);
	    }
	    //insertion at left right
	    if(balance > 1 && item.compareTo(currNode.left.key) > 0)
	    {
	        System.out.println("left right");
	        currNode.left = rotateLeft(currNode.left);
	        return rotateRight(currNode);
	    }
	    
	    return currNode;
	}
	
	/**
	 * travels up the tree from a recently added node
	 * to find the first unbalanced node if one exists
	 * @param currNode
	 */
	public Treenode<T> balanceHelper(Treenode<T> newNode)
	{
	    Treenode<T> current = newNode.parent;
	    while(true)
	    {
	        if(Math.abs(this.balanceFactor(current)) > 1)
	        {
	            
	        }
	    }
	}
	/**
	 * @param unbalancedNode
	 */
	public void balanceTree(Treenode<T> unbalancedNode)
	{
	    
	}

	/**
	 * If the tree contains a node with item as its key, remove it from the tree and rebalance
	 */
	public void delete(T item) 
	{
		//TODO if item is null or not found in tree, return without error
		// else remove this item key from the tree and rebalance

		// NOTE: if you are unable to get delete implemented
		// it will be at most 5% of the score for this program assignment
	}
	
	public static void main(String args[])
	{
	    BalancedSearchTree<String> avl = new BalancedSearchTree<String>();
	    avl.insert("orange");
	    avl.insert("poo");
	    avl.insert("zoo");
	    System.out.println(avl.inAscendingOrder());
//	    System.out.println(avl.root.left.key);
//	    System.out.println(avl.root.key);
//	    System.out.println(avl.root.right.key);
	}

}


