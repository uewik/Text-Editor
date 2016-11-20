package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		size = 0;
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if (element == null)
			throw new NullPointerException("null is illegal.");
		LLNode<E> node = new LLNode<E>(element);
		node.prev = tail.prev;
		tail.prev.next = node;
		node.next = tail;
		tail.prev = node;
		++size;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("index is out of bounds.");
		if (index <= size - 1 - index)
		{
			LLNode<E> node = head.next;
			for (int i = 0; i < index; ++i)
				node = node.next;
			return node.data;
		}
		else
		{
			LLNode<E> node = tail.prev;
			for (int i = size - 1; i > index; --i)
			{
				node = node.prev;
			}
			return node.data;
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("index is out of bounds.");
		if (element == null)
			throw new NullPointerException("null is illegal.");
		LLNode<E> node = new LLNode<E>(element);
		LLNode<E> temp = head;
		for (int i = 0; i < index; ++i)
			temp = temp.next;
		node.next = temp.next;
		temp.next.prev = node;
		node.prev = temp;
		temp.next = node;
		++size;
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException("index is outside the bounds");
		LLNode<E> temp = head;
		for (int i = 0; i < index; ++i)
			temp = temp.next;
		E value = temp.next.data;
		temp.next.next.prev = temp;
		temp.next = temp.next.next;
		--size;
		return value;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException("index is outside the bounds");
		if (element == null)
			throw new NullPointerException("null is illegal.");
		LLNode<E> temp = head.next;
		for (int i = 0; i < index; ++i)
			temp = temp.next;
		E res = temp.data;
		temp.data = element;
		return res;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
