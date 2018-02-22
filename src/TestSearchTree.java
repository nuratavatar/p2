import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

///////////////////////////////////////////////////////////////////////////////
//Title:            P2 (Implemented Using AVL Tree)
//Files:            BalancedSearchTree.java, TestSearchTree.java, SearchTreeADT.java
//Semester:         CS 400 Spring 2018
//
//Authors:          Matt Zimmers, Tarun Mandalapu
//Email:            tmandalapu@wisc.edu
//Lecturer's Name:  Debra Deppeler
//Source Credits:   https://pages.cs.wisc.edu/~deppeler/cs400/readings/AVL-Trees/index.html
//Known Bugs:       TODO
///////////////////////////////////////////////////////////////////////////////


public class TestSearchTree {

	SearchTreeADT<String> tree = null;
	String expected = null;
	String actual = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tree = new BalancedSearchTree<String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01_isEmpty_on_empty_tree() {
		expected = "true";
		actual = "" + tree.isEmpty();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	public void test02_ascending_order_on_empty_tree() {
		expected = "";
		actual = tree.inAscendingOrder();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	/** tests that the height of an empty tree is 0 */
	public void test03_height_on_empty_tree() {
		expected = "0";
		actual = "" + tree.height();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	public void test04_isEmpty_after_one_insert() {
		tree.insert("1");
		expected = "false";
		actual = "" + tree.isEmpty();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	/** tests that the ascending order after inserting A item is "A," */
	public void test05_ascending_order_after_one_insert() {
		tree.insert("A");
		expected = "A,";
		actual = tree.inAscendingOrder();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	/** tests that the height after inserting A is 1 */
	public void test06_height_after_one_insert() {
		tree.insert("A");
		expected = "1";
		actual = "" + tree.height();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	/** tests that the height after inserting A and deleting it is 0 */
	public void test07_height_after_one_insert_and_delete() {
		tree.insert("A");
		tree.delete("A");
		expected = "0";
		actual = "" + tree.height();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}
	
	@Test
	/** tests the IllegalArgumentException that should be thrown from 
	 * the insert method when attempting to insert a null value*/
	public void test08_insert_IllegalArgumentException() {
	    try {
	        tree.insert(null);
	        fail("expected: IllegalArgumentException actual: no Exception");
	    } catch (IllegalArgumentException e) {
	        
	    }
	}
	@Test
	/** tests the isEmpty() method for before anything is inserted and after
	 * a value are inserted*/
	public void test09_isEmpty() {
	    if (tree.isEmpty() == false) fail("expected: true actual: false");
	    tree.insert("B");
	    if (tree.isEmpty() == true) fail("expected: false actual: true");
	}
	
	@Test
	/** tests lookup(T item) by inserting varying amounts of nodes and then
	 *  looking it up*/
	public void test10_lookup_functionality() {
	    if (tree.lookup("Banana")) fail("expected: false actual: true");
	    tree.insert("Apple");
	    tree.insert("Peach");
	    tree.insert("Plum");
	    tree.insert("Orange");
	    if (tree.lookup("Banana")) fail("expected: false actual: true");
	    if (!tree.lookup("Peach")) fail("expected: true actual: false");
	    if (!tree.lookup("Orange")) fail("expected: false actual: true");
	}
	
	@Test
	/** tests the IllegalArgumentException that should be thrown from
	 * the delete method when attempting to insert a null value*/
	public void test11_delete_IllegalArgumentException() {
	    try {
	        tree.delete(null);
	        fail("expected: IllegalArgumentException actual: no Exception");
	    } catch (IllegalArgumentException e) {
	        
	    }
	}

	@Test
	/** tests delete(T item) by inserting varying nodes and deleting one 
	 * and then trying to look it up*/
	public void test12_delete_existing_item() {
	    tree.insert("Apple");
        tree.insert("Peach");
        tree.insert("Plum");
        tree.insert("Orange");
        tree.delete("Plum");
        if (tree.lookup("Plum")) fail("expected: false actual: true");
	}
	
	@Test
	/** tests inAscendingOrder() by creating a shadow array of the
	 *  values added and sorting that array independently. What is 
	 *  returned by inAscendingOrder() (after parsed by commas) should match 
	 *  the sorted array's contents */
	public void test13_inAscendingOrder() {
	    Random rand = new Random();
	    ArrayList<String> arr = new ArrayList<String>();
	    for (int i = 0; i < 2000; i++) arr.add(i + "");
	    
	    for (int i = 0; i < 2000; i++) {
	        tree.insert(arr.remove(rand.nextInt(arr.size())));
	    }
	    
	    String orderedString = tree.inAscendingOrder();
	    orderedString = orderedString.substring(0, orderedString.length()-1); 
	    // gets rid of ending comma
	    
	    String[] ordered = orderedString.split(",");
	    boolean worked = true;
	    for (int i = 1; i < ordered.length; i++) {
	        if (ordered[i-1].compareTo(ordered[i]) > 0) worked = false;
	    }
	    if (!worked) fail("expected: sorted order of elements actual: "
	            + "unsorted order of elements");
	}
	
	@Test
	/** tests height() by inserting varying amounts of predetermined 
	 * nodes with a known structure outcome and testing the expected 
	 * and returned value of height*/
	public void test14_height_functionality() {
	    int height = tree.height();
	    if (height != 0) fail("expected: 0 actual " + height);
	    tree.insert("Peach");
	    height = tree.height();
	    if (height != 1) fail("expected: 1 actual " + height);
	    tree.insert("Pear");
	    tree.insert("Apple");
	    tree.insert("Carrot");
	    tree.insert("Orange");
	    tree.insert("Zucchini");
	    tree.insert("Salmon");
	    tree.insert("Bacon");
	    // written down for correct rotations
	    
	    height = tree.height();
	    if (height != 4) fail("expected: 4 actual " + height);
	}
}

