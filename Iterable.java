@FunctionalInterface
public interface Iterable<T> {
	/**
	 * Generates an iterator positioned at the beginning of the data structure.
	 * @return: an iterator positioned at the beginning of the data structure
	 */

	Iterator<T> iterator();
}
