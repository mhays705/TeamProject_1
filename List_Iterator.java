public interface List_Iterator<T> extends Iterator<T> {
	/**
	 * Tests where there exists a previous element.
	 * @return: {true} if there exists a previous element; {false} otherwise
	 */
	boolean hasPrevious();

	/**
	 * Moves the iterator backward and returns the element passed by.
	 * @return: value passed by
	 */
	T previous();
	/**
	 * Deletes the previous element
	 * @return: value deleted
	 */
	T removePrevious();
	/**
	 * Inserts a new value at current iterator position.
	 * @param value: value to be inserted
	 */
	void add(T value);

	/**
	 * Updates the next element.
	 * @param value: update value of the next element
	 */
	void setNext(T value);

	/**
	 * Updates the previous element.
	 * @param value:
	 */
	void setPrevious(T value);
	
	/**
	 * Returns value of element without advancing the iterator
	 * @return: Value from next element
	 */
	
	T peek(); 
	
	
	
	/** 
	 * Moves iterator to beginning of list
	 */
	
	void reset();
	
}
