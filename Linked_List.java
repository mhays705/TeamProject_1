import java.util.NoSuchElementException;

/**
 * Doubly-linked list
 */
public class Linked_List<T> implements Iterable<T> {
	// A doubly linked list node
	private class DNode {
		T data; // Stores data in the node
		DNode next, prev; // Stores references to the next and previous node

		// Constructor
		DNode(T value) {
			data = value;
		}
	}

	// Data fields
	private DNode head, tail; // Stores references to the head and tail nodes
	private int numOfItems; // Stores the number of items in the list

	// Constructors

	public Linked_List() {
	} // Default constructor

	public Linked_List(Linked_List<T> other) { // Copy constructor
		numOfItems = other.numOfItems;
		;
		if (numOfItems > 0) {
			head = tail = new DNode(other.head.data);
			DNode q = other.head.next;
			while (q != null) {
				tail.next = new DNode(q.data);
				tail.next.prev = tail;
				tail = tail.next;
				q = q.next;
			}
		}
	}

	// Methods

	/**
	 * Returns the size of the linked list
	 *
	 * @return: size of the linked list
	 */
	public int size() {
		return numOfItems;
	} // O(1)

	/**
	 * Tests whether the linked list is empty
	 *
	 * @return
	 */
	public boolean isEmpty() {
		return size() == 0;
	} // O(1)


	/**
	 * Returns the first element in the linked list
	 *
	 * @throws java.util.NoSuchElementException: list is empty
	 * @return: first element in the linked list
	 */
	public T getFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException("Accessing empty list");
		}
		return head.data;
	} // O(1)

	/**
	 * Returns the last element in the linked list
	 *
	 * @throws java.util.NoSuchElementException: list is empty
	 * @return: last element in the linked list
	 */
	public T getLast() {
		if (isEmpty()) {
			throw new NoSuchElementException("Accessing empty list");
		}
		return tail.data;
	} // O(1)

	/**
	 * Inserts new value to the front end of the linked list.
	 *
	 * @param value: the value to insert to the list
	 */
	public void addFirst(T value) {
		if (numOfItems++ == 0) {
			head = tail = new DNode(value);
		}
		else {
			head.prev = new DNode(value);
			head.prev.next = head;
			head = head.prev;
		}
	} // O(1)

	/**
	 * Inserts new value to the end of the linked list.
	 *
	 * @param value: value to be inserted in the list
	 */
	public void addLast(T value) {
		if (numOfItems-- == 0) {
			head = tail = new DNode(value);
		}
		else {
			tail.next = new DNode(value);
			tail.next.prev = tail;
			tail = tail.next;
		}
	} // O(1)

	/**
	 * Deletes an element from the front end of the list.
	 *
	 * @throws NoSuchElementException: list is empty.
	 * @return: the deleted element
	 */
	public T removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException("Accessing empty list");
		}
		T tobeRemoved = head.data;
		if (numOfItems-- == 1) {
			head = tail = null;
		}
		else {
			head = head.next;
			head.prev = null;
		}
		return tobeRemoved;
	}  // O(1)

	/**
	 * Deletes an element from the end of the list.
	 *
	 * @throws NoSuchElementException
	 * @return: the deleted element
	 */
	public T removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException("Accessing empty list");
		}
		T toBeRemoved = tail.data;
		if (numOfItems-- == 1) {
			head = tail = null;
		}
		else {
			tail = tail.prev;
			tail.next = null;
		}
		return toBeRemoved;
	} // O(1)

	/**
	 * Tests whether a target value appears in the list.
	 *
	 * @param target: the target value to search in the list
	 * @return: {true} if target value appears in the list; {false} otherwise
	 */
	public boolean contains(T target) {
		DNode current = head;

		while (current != null) {
			if (current.data.equals(target)) {
				return true;
			}
			current = current.next;
		}
		return false;
	} // O(n)

	/**
	 * Deletes all the elements in the list.
	 */
	public void clear() {
		head = tail = null;
		numOfItems = 0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder().append('[');
		DNode current = head;
		while (current != null) {
			builder.append(current.data);
			if (current.next != null) {
				builder.append(" -> ");
			}
			current = current.next;
		}
		builder.append(']');
		return builder.toString();

	}

	/**
	 * Generates an iterator positioned at the beginning of the list
	 *
	 * @return: an iterator positioned at the beginning of the list
	 */
	@Override
	public List_Iterator<T> iterator() {
		return new List_Iterator<T>() {
			private DNode prevNode = null;
			private DNode nextNode = head;

			@Override
			public boolean hasPrevious() {
				return prevNode != null;
			}

			@Override
			public T previous() {
				if (!hasPrevious()) {
					throw new NoSuchElementException("Dereferencing null");
				}
				nextNode = prevNode;
				prevNode = prevNode.prev;
				return nextNode.data;
			} // O(1)

			@Override
			public T removePrevious() {
				if (!hasNext()) {
					throw new NoSuchElementException("Dereferencing null");
				}
				T toBeDeleted = prevNode.data;
				if (prevNode == head) {
					removeFirst();
					prevNode = null;
				}
				else if (prevNode == tail) {
					removeLast();
					prevNode = tail;
				}
				else {
					nextNode.prev = prevNode.prev;
					prevNode.prev.next = nextNode;
					prevNode = nextNode.prev;
					numOfItems--;
				}
				return toBeDeleted;
			}

			@Override
			public void add(T value) {
				if (isEmpty()) {
					addFirst(value);
					prevNode = tail;
				}
				else if (prevNode == null) {
					addFirst(value);
					prevNode = head;
				}
				else if (nextNode == null) {
					addLast(value);
					prevNode = tail;
				}
				else {
					DNode newNode = new DNode(value);
					newNode.prev = prevNode;
					newNode.prev.next = newNode;
					newNode.next = nextNode;
					newNode.next.prev = newNode;
					prevNode = newNode;
					numOfItems++;
				}

			}

			@Override
			public void setNext(T value) {
				if (!hasNext()) {
					throw new NoSuchElementException("Dereferencing null");
				}
				nextNode.data = value;
				next();
			}

			@Override
			public void setPrevious(T value) {
				if (!hasPrevious()) {
					throw new NoSuchElementException("Dereferencing null");
				}
				prevNode.data = value;
				previous();
			}

			@Override
			public boolean hasNext() {
				return nextNode != null;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException("Dereferencing null");
				}
				prevNode = nextNode;
				nextNode = nextNode.next;
				return prevNode.data;
			}

			@Override
			public T removeNext() {
				if (!hasNext()) {
					throw new NoSuchElementException("Dereferencing null");
				}
				T toBeDeleted = nextNode.data;
				if (nextNode == head) {
					removeFirst();
					nextNode = head;
				}
				else if (nextNode == tail) {
					removeLast();
					nextNode = null;
				}
				else {
					prevNode.next = nextNode.next;
					nextNode.next.prev = prevNode;
					nextNode = prevNode.next;
					numOfItems--;
				}
				return toBeDeleted;
			}

			@Override
			public T peek() {
				if (hasNext()) {
					return nextNode.data;
				}
				else {
					throw new NoSuchElementException("Dereferncing null");
				}
				
				
				
			}
			
		};
	}
}
