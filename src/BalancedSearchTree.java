// starter class for a BalancedSearchTree
// you may implement AVL, Red-Black, 2-3 Tree, or 2-3-4 Tree
// be sure to include in class header which tree you have implemented
public class BalancedSearchTree<T extends Comparable<T>> implements SearchTreeADT<T> {

	// inner node class used to store key items and links to other nodes
	protected class Treenode<K extends Comparable<K>> {
		public Treenode(K item) {
			this(item,null,null);
		}
		public Treenode(K item, Treenode<K> left, Treenode<K> right) {
			key = item;
			this.left = left;
			this.right = right;
		}
		K key;
		Treenode<K> left;
		Treenode<K> right;
	}

	protected Treenode<T> root;

	public String inAscendingOrder() {
		//TODO : must return comma separated list of keys in ascending order
		return "" ;
	}

	public boolean isEmpty() {
		//TODO return empty if there are no keys in structure
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


	// HINT: define this helper method that can find the smallest key 
	// in a sub-tree with "node" as its root
	// PRE-CONDITION: node is not null
	private T leftMost(Treenode<T> node) {
		// TODO return the key value of the left most node in this subtree
		// or return node's key if node does not have a left child
		return node.key;
	}

}

