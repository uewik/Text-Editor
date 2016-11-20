/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	// Test removing an element from the list.
	@Test
	public void testRemove()
	{
		try
		{
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e)
		{
			
		}
		try
		{
			list1.remove(3);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e)
		{
			
		}
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		// TODO: Add more tests here

	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		try {
			list1.add(null);
			fail("Check null element.");
		}
		catch (NullPointerException e) {
		
		}
		boolean b = list1.add(24);
		assertEquals("Add: check b is correct ", (Boolean) true, b);
		assertEquals("Add: check last element is correct ", (Integer) 24, list1.get(3));
		assertEquals("Add: check size is correct ", 4, list1.size());
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Size: check size is correct ", 0, emptyList.size());
		assertEquals("Size: check size is correct ", LONG_LIST_LENGTH, longerList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try {
			list1.add(-1, 24);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			list1.add(4, 24);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			list1.add(1, null);
			fail("Check null element.");
		}
		catch (NullPointerException e) {
		
		}
		list1.add(0, 24);
		list1.add(2, 25);
		list1.add(5, 26);
		assertEquals("Add: check added element is correct ", (Integer) 24, list1.get(0));
		assertEquals("Add: check added element is correct ", (Integer) 25, list1.get(2));
		assertEquals("Add: check added element is correct ", (Integer) 26, list1.get(5));
		assertEquals("Add: check size is correct ", 6, list1.size());
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try {
			list1.set(-1, 24);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			list1.set(4, 24);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			list1.set(1, null);
			fail("Check null element.");
		}
		catch (NullPointerException e) {
		
		}
		int val = list1.set(1, 30);
		assertEquals("Add: check replaced element is correct ", 21, val);
		assertEquals("Add: check set element is correct ", (Integer) 30, list1.get(1));
	}
	
	
	// TODO: Optionally add more test methods.
	
}
