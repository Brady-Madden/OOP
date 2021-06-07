/**
 * A {@code Pair} contains two values.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 * @param <F>
 *            the type of the first value.
 * @param <S>
 *            the type of the second value.
 */
public class Pair<F, S> {

	/**
	 * The first value.
	 */
	protected F first;

	/**
	 * The second value.
	 */
	protected S second;

	/**
	 * Constructs a {@code Pair}.
	 * 
	 * @param first
	 *            the first value.
	 * @param second
	 *            the second value.
	 */
	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * Returns the first value.
	 * 
	 * @return the first value.
	 */
	public F first() {
		return first;
	}

	/**
	 * Returns the second value.
	 * 
	 * @return the second value.
	 */
	public S second() {
		return second;
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}

}

