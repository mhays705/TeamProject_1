import java.util.NoSuchElementException;

/** A doubly-linked list with iterators */
public class Linked_List<T> implements Iterable<T> {
    /** A doubly-linked list node */
    private class DNode {
        // Data fields
        T data;  // Stores some data.
        DNode next;  // Stores a reference to the next node.
        DNode prev;  // Stores a reference to the previous node.

        // Constructor
        DNode(T value) { data = value; }  // Time complexity: O(1)
    }

    // Data fields
    private DNode head;  // Stores a reference to the first node of the list.
    private DNode tail;  // Stores a reference to the last node of the list.
    private int numOfItems;  // Stores the size of the list.

    // Constructors

    public Linked_List() {}  // Default constructor

    public Linked_List(Linked_List<T> other) {  // Copy constructor
        numOfItems = other.numOfItems;
        if (numOfItems != 0) {
            head = tail = new DNode(other.head.data);
            DNode q = other.head.next;
            while (q != null) {
                tail.next = new DNode(q.data);
                tail.next.prev = tail;
                tail = tail.next;
                q = q.next;
            }
        }
    }  // Time complexity: O(n)

    // Methods

    /** Returns the size of the linked list.
        @return: number of elements stored in the linked list
    */
    public final int size() { return numOfItems; }  // Time complexity: O(1)

    /** Tests whether the linked list is empty.
        @return: {true} if the list is empty; {false} otherwise
    */
    public final boolean isEmpty() { return size() == 0; }  // Time complexity: O(1)

    /** Returns the value stored in the head of the linked list.
        @return: value stored in the head of the linked list
        @throws NoSuchElementException : list is empty.
    */
    public final T getFirst() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty list"); }
        return head.data;
    }  // Time complexity: O(1)

    /** Returns the value stored in the tail of the linked list.
        @return: value stored in the tail of the linked list
        @throws NoSuchElementException: list is empty.
    */
    public final T getLast() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty list"); }
        return tail.data;
    }  // Time complexity: O(1)

    /** Adds a new element to the front end (before head) of the linked list.
        @param item: element to be added to the list
    */
    public void addFirst(T item) {
        if (numOfItems++ == 0) { head = tail = new DNode(item); }
        else {
            head.prev = new DNode(item);
            head.prev.next = head;
            head = head.prev;
        }
    }  // Time complexity: O(1)

    /** Adds a new element to the rear end (after tail) of the linked list.
        @param item: element to be added to the list
    */
    public void addLast(T item) {
        if (numOfItems++ == 0) { head = tail = new DNode(item); }
        else {
            tail.next = new DNode(item);
            tail.next.prev = tail;
            tail = tail.next;
        }
    }  // Time complexity: O(1)

    /** Removes the first element (stored in head) from the linked list.
        @return: element removed
        @throws NoSuchElementException: list is empty.
    */
    public final T removeFirst() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty list"); }
        T deleted = head.data;
        if (numOfItems-- == 1) { head = tail = null; }
        else {
            head = head.next;
            head.prev = null;
        }
        return deleted;
    }  // Time complexity: O(1)

    /** Removes the last element (stored in tail) from the linked list.
        @return: element removed
        @throws NoSuchElementException: list is empty.
    */
    public final T removeLast() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty list"); }
        T deleted = tail.data;
        if (numOfItems-- == 1) { head = tail = null; }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        return deleted;
    }  // Time complexity: O(1)

    /** Tests whether a target value appears in the linked list.
        @param target: value to search in the list
        @return: {true} if target found in the list; {false} otherwise
    */
    public boolean contains(T target) {
        DNode p = head;
        while (p != null) {
            if (p.data.equals(target)) { return true; }
            p = p.next;
        }
        return false;
    }  // Time complexity: O(n)

    /** Removes all elements from the linked list. */
    public final void clear() {
        head = tail = null;
        numOfItems = 0;
    }  // Time complexity: O(1)

    /** Customizes the output format for the linked list.
        @return: a string representing the output format of the linked list
    */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder().append('[');
        DNode current = head;
        while (current != null) {
            builder.append(current.data.toString());
            if (current != tail) { builder.append(" -> "); }
            current = current.next;
        }
        return builder.append(']').toString();
    }  // Time complexity: O(n)

    /** Generates a list iterator positioned at the beginning of the list.
        @return: a list iterator positioned at the beginning of the list.
    */
    @Override
    public List_Iterator<T> iterator() {
        return new List_Iterator<T>() {
            // Data fields
            private DNode nextNode = head;  // Stores a reference to the next node at current iterator position.
            private DNode prevNode = null;  // Stores a reference to the previous node at current iterator position.

            // Methods

            /** Tests whether there exists a next element at current iterator position.
                @return: {true} if there exists a next element at current iterator position; {false} otherwise
            */
            @Override
            public boolean hasNext() { return nextNode != null; }  // Time complexity: O(1)

            /** Tests whether there exists a previous element at current iterator position.
                @return: {true} if there exists a previous element at current iterator position; {false} otherwise
            */
            @Override
            public boolean hasPrevious() { return prevNode != null; }  // Time complexity: O(1)

            /** Moves the iterator forward one position and returns the element passed by.
                @return: element passed by in the iterator movement
                @throws NoSuchElementException: there is not a next element at current iterator position.
            */
            @Override
            public T next() {
                if (!hasNext()) { throw new NoSuchElementException("Accessing null pointer"); }
                prevNode = nextNode;
                nextNode = nextNode.next;
                return prevNode.data;
            }  // Time complexity: O(1)

            /** Moves the iterator backward one position and returns the element passed by.
                @return: element passed by in the iterator movement
                @throws NoSuchElementException: there is not a previous element at current iterator position.
            */
            @Override
            public T previous() {
                if (!hasPrevious()) { throw new NoSuchElementException("Accessing null pointer"); }
                nextNode = prevNode;
                prevNode = prevNode.prev;
                return nextNode.data;
            }  // Time complexity: O(1)

            /** Replaces the next element at current iterator position with the specified element.
                @param value: element with which to replace the next element at current iterator position
                @throws NoSuchElementException: there is not a next element at current iterator position.
            */
            @Override
            public void setNext(T value) {
                if (!hasNext()) { throw new NoSuchElementException("Accessing null pointer"); }
                nextNode.data = value;
                next();  // Moves the iterator forward.
            }  // Time complexity: O(1)

            /** Replaces the previous element at current iterator position with the specified element.
                @param value: element with which to replace the previous element at current iterator position
                @throws NoSuchElementException: there is not a previous element at current iterator position.
            */
            @Override
            public void setPrevious(T value) {
                if (!hasPrevious()) { throw new NoSuchElementException("Accessing null pointer"); }
                prevNode.data = value;
                previous();
            }  // Time complexity: O(1)

            /** Removes (and returns) the next element at current iterator position.
                @return: element removed
                @throws NoSuchElementException: there is not a next element at current iterator position.
            */
            @Override
            public T removeNext() {
                if (!hasNext()) { throw new NoSuchElementException("Accessing null pointer"); }
                T value = nextNode.data;
                if (numOfItems-- == 1) {
                    head = tail = prevNode = nextNode = null;
                } else if (prevNode == null) {  // The node to be removed is the head.
                    nextNode = nextNode.next;
                    nextNode.prev = null;
                    head = nextNode;
                } else if (nextNode.next == null) {  // The node to be removed is the tail.
                    nextNode = null;
                    prevNode.next = null;
                    tail = prevNode;
                } else {
                    prevNode.next = nextNode.next;
                    nextNode = nextNode.next;
                    nextNode.prev = prevNode;
                }
                return value;
            }  // Time complexity: O(1)

            /** Removes (and returns) the previous element at current iterator position.
                @return: element removed
                @throws NoSuchElementException: there is not a previous element at current iterator position.
            */
            @Override
            public T removePrevious() {
                if (!hasPrevious()) { throw new NoSuchElementException("Accessing null pointer"); }
                previous();
                return removeNext();
            }  // Time complexity: O(1)

            /** Inserts an element at the current iterator position.
                @param value: element to insert to the list
            */
            @Override
            public void add(T value) {
                if (!hasPrevious()) {  // The new item will be the new head of the list.
                    addFirst(value);
                    prevNode = head;
                    nextNode = head.next;
                } else if (!hasNext()) {  // The new item will be the new tail of the list.
                    addLast(value);
                    prevNode = tail;
                    nextNode = null;
                } else {
                    DNode newNode = new DNode(value);
                    newNode.prev = prevNode;
                    newNode.prev.next = newNode;
                    newNode.next = nextNode;
                    newNode.next.prev = newNode;
                    prevNode = newNode;
                    numOfItems++;
                }
            }  // Time complexity: O(1)

            /** Moves the iterator to the beginning of the list. */
            @Override
            public void reset() {
                prevNode = null;
                nextNode = head;
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
