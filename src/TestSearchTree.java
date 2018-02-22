import static org.junit.Assert.*;

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
	/** */
	public void test08_insert_IllegalArgumentException() {
	    try {
	        tree.insert(null);
	}

}

