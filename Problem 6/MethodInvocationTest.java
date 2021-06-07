package homework6;
import java.util.SortedSet;
import java.util.TreeSet;

public class MethodInvocationTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SortedSet<Integer> set = new TreeSet<Integer>(); // construct a sorted set.
		set.add(2); // add 2
		set.add(1); // add 1
		System.out.println(Registry.invoke(set, "toString", null, null)); // same as System.out.println(set.toString())
	}

}
