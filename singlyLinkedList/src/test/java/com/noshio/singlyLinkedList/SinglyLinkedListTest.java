package com.noshio.singlyLinkedList;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class SinglyLinkedListTest extends TestCase {
	private SinglyLinkedList<Integer> sl1 = new SinglyLinkedList<Integer>();
	private SinglyLinkedList<Integer> sl2 = new SinglyLinkedList<Integer>();

	protected void setUp() throws Exception {
		super.setUp();

	}

	@Test
	public void testAdd() {
		sl1.add(10);
		sl1.add(20);
		sl1.add(30);
		sl2.add(10);
		sl2.add(20);
		sl2.add(30);
		assertNotNull(sl1);
		assertNotNull(sl2);
		assertTrue(sl1.equals(sl2));

	}

	@Test
	public void testAddIfAbsent() {
		sl1.add(10);
		sl1.add(20);
		sl1.add(30);
		assertNotNull(sl1);
		assertTrue(sl1.addIfAbsent(50));
	}

	@Test
	public void testAddAfter() {
		sl1.add(10);
		sl1.add(20);
		sl1.add(30);
		sl2.add(10);
		sl2.add(30);
		assertNotNull(sl1);
		assertNotNull(sl2);
		sl2.addAfter(20, 10);// adding element 20 after element 10;
		assertTrue(sl1.equals(sl2));
	}

	@Test
	public void testDeleteFront() {
		sl1.add(10);
		sl1.add(20);
		sl1.add(30);

		sl2.add(20);
		sl2.add(30);
		assertNotNull(sl1);
		assertNotNull(sl2);
		sl1.deleteFront();
		assertTrue(sl1.equals(sl2));
	}

	@Test
	public void testDeleteAfter() {

		sl1.add(10);
		sl1.add(20);
		sl1.add(30);

		sl2.add(10);

		sl2.add(30);
		assertNotNull(sl1);
		assertNotNull(sl2);
		sl1.deleteAfter(10);
		assertTrue(sl1.equals(sl2));
	}

	@Test
	public void testSize() {
		sl1.add(10);
		sl1.add(20);
		sl1.add(30);
		assertNotNull(sl1);

		assertEquals(3, sl1.size());
	}

	@Test
	public void testContains() {
		sl1.add(10);
		sl1.add(20);
		sl1.add(30);
		assertTrue(sl1.contains(10));
		assertTrue(sl1.contains(20));
		assertTrue(sl1.contains(30));
	}

	@Test
	public void testIterator() {
		sl1.add(10);
		sl1.add(20);
		sl1.add(30);
		assertNotNull(sl1);
		assertNotNull(sl2);
		List<Integer> testvalues = new ArrayList<Integer>();
		testvalues.add(10);
		testvalues.add(20);
		testvalues.add(30);
		for (Integer elem : sl1) {
			assertTrue(testvalues.contains(elem));
		}
	}
	
	@Test
	public void testReverse() {
		SinglyLinkedList<Integer> sl=new SinglyLinkedList<Integer>();
		sl.add(10);
		sl.add(20);
		sl.add(30);
		sl.reverse();
		assertNotNull(sl1);
	}
	
	@Test
	public void testToArray() {
		SinglyLinkedList<Integer> sl=new SinglyLinkedList<Integer>();
		sl.add(10);
		sl.add(20);
		sl.add(30);
		assertNotNull(sl1);
		Integer resultArray[]=new Integer[sl.size()];
		resultArray=sl.toArray(Integer.class);
		assertTrue(resultArray[0]==10);
		assertTrue(resultArray[1]==20);
		assertTrue(resultArray[2]==30);
		assertTrue(resultArray.length==3);
		
	}
	
	


}
