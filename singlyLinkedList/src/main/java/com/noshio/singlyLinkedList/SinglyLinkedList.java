package com.noshio.singlyLinkedList;

import java.lang.reflect.Array;
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
	 * @return void
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
	
	/**
	 * Adds a new element to the list, if it does not exist yet.
	 * 
	 * @param element
	 * @return boolean
	 */
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
	 * 
	 * @return void
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
	 * 
	 * @param after
	 * @return void
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
	 * returns an array of objects contained in the linkedList
	 * 
	 * @param class
	 * @return array
	 */
	public  T[] toArray(Class<T> clazz){
		if (head == null) {
			throw new IndexOutOfBoundsException("the referenced SingleListedLink has no elements");
		}
		
		@SuppressWarnings("unchecked")
		T[] values=(T[])Array.newInstance(clazz, size());
		int index=0;
		Node<T> tmp = head;
		while (true) {
			if (tmp == null) {
				break;
			} else {
				values[index]=tmp.getData();
				index++;		
			}
			tmp = tmp.next();
		}
		return values;	
	}
	

	
	/**
	 * reverses the order of the elements on this list
	 * THIS METHOD MUTATES THE ORIGINAL LIST
	 * 
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
	 * this method allows to determine the number of elements presents on the list.
	 * 
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
	 * this method is used to determine if a certain element is contained on the list.
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
	
	//bellow are implementations of methods that override or implement
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

	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 * Our implementation of iterator
	 */
	@Override
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
	 * A private inner class representing one node of the list.
	 * 
	 * @author martins
	 *
	 * @param <T>
	 */
	private static class Node<T> implements Comparable<T> {
		private T data; //holds the stored data
		private Node<T> linksTo;//holds the pointer to the next element.

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
				return 0;//is equal
			} else {
				return 1;//is different
			}
		}
	}

}
