package com.noshio.singlyLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of a single linked list that can store any type of objects.
 * this implementation allows iterating over the list and reverse it.
 * @author martins
 *
 * @param <T>
 */
public class SinglyLinkedList<T> implements Iterable<T> {

	private Node<T> head;
	private Node<T> tail;

	/**
	 * Adds a new element to the list
	 * 
	 * @param element
	 */

	public void add(T element) {

		Node<T> node = new Node<T>();
		node.setData(element);
		/**
		 * is it the first one ?
		 */
		if (head == null) {
			// there is only one element, so head and tail
			// will point to the same object
			head = node;
			tail = node;
		} else {
			// we have previous nodes, so
			// tail has to point to the next node
			tail.setNext(node);
			tail = node;
		}
	}

	public boolean addIfAbsent(T element) {
		if (head == null) { // the list is empty...so just add it and terminate
			add(element);
			return true;
		}
		// list is not empty...so we check if element exists
		if (contains(element)) {
			return false;
		} else {
			add(element);
			return true;
		}
	}

	/**
	 * Adds a new element after the specified element
	 * 
	 * @param element to insert
	 * @param after this element
	 * @return void
	 */
	public void addAfter(T element, T after) {
		if (head==null) {
			throw new IndexOutOfBoundsException("the referenced SingleListedLink has no elements");
		}
		Node<T> tmp = head;
		Node<T> refNode = null;

		/**
		 * we have to traverse all nodes untill the given element is found.
		 */
		while (true) {
			if (tmp == null) {
				break;
			}
			if (tmp.compareTo(after) == 0) {
				// we found the node with given data. adding after
				refNode = tmp;
				break;
			}
			tmp = tmp.next();
		}
		if (refNode != null) {
			// add the element after the target found above
			Node<T> node = new Node<T>();
			node.setData(element);
			node.setNext(tmp.next());
			if (tmp == tail) {
				tail = node;
			}
			tmp.setNext(node);

		} else {
			throw new NoSuchElementException("element could not be found in the referenced SingleListedLink");
		}
	}

	/**
	 * deletes the head element of the list
	 */
	public void deleteFront() {
		if (head == null) {
			throw new IndexOutOfBoundsException("the referenced SingleListedLink has no elements");
		}
		Node<T> tmp = head;// swap head with next node
		head = tmp.next();
		if (head == null) { // in case the list gets empty.
			tail = null;
		}
	}

	/**
	 * deletes the element immediately after the supplied element
	 * @param after
	 */
	public void deleteAfter(T after) {
		if (head==null) {
			throw new IndexOutOfBoundsException("the referenced SingleListedLink has no elements");
		}
		Node<T> tmp = head;
		Node<T> refNode = null;
		/**
		 * we have to traverse all nodes until the given element is found.
		 */
		while (true) {
			if (tmp == null) { // list is empty
				break;
			}
			if (tmp.compareTo(after) == 0) { // target Node found
				refNode = tmp;
				break;
			}
			tmp = tmp.next();
		}
		if (refNode != null) {
			tmp = refNode.next();
			refNode.setNext(tmp.next());
			if (refNode.next() == null) {
				tail = refNode;
			}
		} else {
			throw new NoSuchElementException("element could not be found in the referenced SingleListedLink");
		}
	}
	

	
	/**
	 * reverses the order of the elements on the list
	 * @return void
	 */
	public void  reverse() {
		Node<T> currentNode=head;
		Node<T> nextNode=null;
		Node<T> previousNode=null;
		
		while(currentNode!=null) {
			nextNode=currentNode.next();
			currentNode.setNext(previousNode);
			previousNode=currentNode;
			currentNode=nextNode;
		}
		head=previousNode;
			
	}

	/**
	 * @return int
	 */
	public int size() {
		int numberOfElements = 0;
		if (head == null) {
			throw new NullPointerException("Single Listed link not Initialized");
		}
		Node<T> tmp = head;
		// traverse the list and count
		while (true) {
			if (tmp == null) {
				break;
			}
			tmp = tmp.next();
			numberOfElements++;
		}
		return numberOfElements;
	}

	/**
	 * This method quickly allows to determine is the List is empty.
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		if (head == null) {
			return true;
		}
		return false;
	}

	/**
	 * this method is used to determine if a certain element is part of the list.
	 * 
	 * @param element
	 * @return boolean
	 */
	public boolean contains(T element) {

		if (head == null) {
			return false; // list is empty...so it does not contains anything.
		}
		boolean opResult = false;
		Node<T> tmp = head;
		while (true) {
			if (tmp == null) {
				break;
			}
			if (tmp.getData() == element) {
				opResult = true;
				break;
			}
			tmp = tmp.next();
		}
		return opResult;
	}
	
	//bellow are implementations of methods that override
	//standard ones.


	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (obj == null || this.head == null)
			return false;
		SinglyLinkedList<T> sl = null;
		
		try {
			sl = (SinglyLinkedList<T>) obj;
		} catch (ClassCastException e) {
			return false;
		}
		
		boolean equality = true; // Initially we assume they are equal
		Node<T> tmp1 = head;
		Node<T> tmp2 = sl.head;
		while (true) {
			if (tmp1 == null || tmp2 == null) {// when null..all elements have been compared.
				break;
			}
			// compare
			T data1 = tmp1.data;
			T data2 = tmp2.data;
			if (!(data1.equals(data2))) {
				equality = false;
				break;
			}
			tmp1 = tmp1.next();
			tmp2 = tmp2.next();
		}
		return equality;
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node<T> currentNode = head;

			public boolean hasNext() {
				boolean check;
				try {
					check = currentNode.hasNext();
				} catch (NullPointerException e) {
					check = false;
				}
				return check;
			}

			public T next() {
				T data = null;
				if (currentNode == head) {
					data = currentNode.data;
					currentNode = currentNode.next();
					return data;
				} else
					data = currentNode.data;
				currentNode = currentNode.next();
				return data;
			}
		};
	}

	/**
	 * A private inner class representing a node of the list.
	 * 
	 * @author martins
	 *
	 * @param <T>
	 */
	private static class Node<T> implements Comparable<T> {
		private T data;
		private Node<T> linksTo;

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node<T> next() {
			return linksTo;
		}

		public void setNext(Node<T> next) {
			this.linksTo = next;
		}

		public boolean hasNext() {
			return (this != null);
		}

		public int compareTo(T o) {
			if (o == this.data) {
				return 0;
			} else {
				return 1;
			}
		}
	}

}
