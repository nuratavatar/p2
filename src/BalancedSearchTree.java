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

public class BalancedSearchTree<T extends Comparable<T>> implements SearchTreeADT<T> {
    protected int numNodes;
    protected Treenode<T> root;
    
	// inner node class used to store key items and links to other nodes
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
	
	public String inAscendingOrder() {
		//TODO : must return comma separated list of keys in ascending order
		return "" ;
	}

	public boolean isEmpty() {
		if(numNodes == 0)
		{
		    return true;
		}
		return false;
	}

	public int height() {
		//TODO return the height of this tree
		return 0; 
	}

	public boolean lookup(T item) {
		//TODO must return true if item is in tree, otherwise false
		return false;
	}

	public void insert(T item) {
		//TODO if item is null throw IllegalArgumentException, 
		// otherwise insert into balanced search tree
	}

	public void delete(T item) {
		//TODO if item is null or not found in tree, return without error
		// else remove this item key from the tree and rebalance

		// NOTE: if you are unable to get delete implemented
		// it will be at most 5% of the score for this program assignment
	}

}

