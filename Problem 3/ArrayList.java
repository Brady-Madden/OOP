
import java.util.Iterator;



/**
 * An {@code ArrayList} represents a list using an array.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 * @param <E>
 *            the type of elements held in the list.
 */
public class ArrayList<E> extends List<E> {

	/**
	 * An array for containing elements.
	 */
	protected E[] array;

	/**
	 * The beginning of the list in the array.
	 */
	protected int start = 0;

	/**
	 * Constructs an empty {@code ArrayList} with the specified initial capacity.
	 * 
	 * @param initialCapacity
	 *            the initial capacity of this {@code ArrayList}.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(int initialCapacity) {
		array = (E[]) new Object[initialCapacity];
	}

	@Override
	public void addFirst(E e) {
		// TODO Problem 4
		start--;
		if (start < 0) 
			start += array.length;
			size = size++;
		array[start] = e;
		 size++;
		 //or size = array.length;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Problem 5
			return  new java.util.Iterator<E>() {

				@Override
				public boolean hasNext() {
	
					return start < array.length;   
				}

				@Override
				public E next() {
					//E element = current.element;
					//current = current.next;
					//return element;
					if (start < array.length) {
						E element = array[start];
						start++;
						return element;
					}
					return null;
				};
		
			};
	}
}

