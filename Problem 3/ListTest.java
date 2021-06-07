

/**
 * The {@code ListTest} class tests the {@code LinkedList} and {@code ArrayList} classes.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class ListTest {

	/**
	 * The main method of {@code ListTest}.
	 * 
	 * @param args
	 *            the program arguments.
	 */
	public static void main(String[] args) {
		// Problem 1
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 4; i >= 0; i--)
			list.addFirst(i);
		System.out.println(list.size());

		// Problem 2
		System.out.println(list);

		// Problem 3
		((LinkedList<Integer>) list).reverse();
		System.out.println(list);

		// Problem 4
		list = new ArrayList<Integer>(5);
		for (int i = 4; i >= 0; i--)
			list.addFirst(i);
		System.out.println(list.size());

		// Problem 5
		System.out.println(list);
	}

}
