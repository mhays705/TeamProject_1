public interface Iterator<T> {


	/**
	 * Tests whether there exists a next element at current iterator position
	 * @return: {true} if there exists a next element; {false} otherwise
	 */
	boolean hasNext();

	/**
	 * Advances the iterator and returns the element passed by.
	 * @return: the element passed by
	 */
	T next();

	/**
	 * Deletes the next element at current iterator position.
	 * @return: value delete
	 */
	T removeNext();


}
