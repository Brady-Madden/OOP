import java.lang.reflect.Array;

/**
 * This {@code IntArrays} class provides methods for manipulating {@code int} arrays.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class IntArrays {

	/**
	 * Returns a string representation of the contents of the specified array.
	 * 
	 * @param a
	 *            an {@code int} array.
	 * @return a string representation of the contents of the specified array
	 */
	public static String toString(int[] a) {
	String s = "()";
	int l = 0;
	try {
	if(a.length==0) {
		return s;
	}
	l = a.length;
	}
	catch (NullPointerException e) { 
		System.out.println("()");
	}
	char c [] = new char [l];
	 for (int x = 0; x < l; x++) {
		c[x] = (char) ('0' + a[x]);
	 }
	s = String.copyValueOf(c);
	return s;
	}

	/**
	 * Determines whether or not the specified arrays have the same contents.
	 * 
	 * @param a1
	 *            an {@code int} array.
	 * @param a2
	 *            an {@code int} array.
	 * @return {@code true} if the specified arrays are of the same length and have the same elements; {@code false}
	 *         otherwise.
	 */
	public static boolean compare(int[] a1, int[] a2) {
		int l1 =0;
		int l2=0;
		try {
			l1 = a1.length;
			l2 = a2.length;
			if (l1 != l2) {
				return false;
			}
			if (l1 > 0) {
				if (a2 == null) {
				return false;
				}
			}
			for (int i = 0; i < l1; i++ ) {
				if (a1[i] != a2[i]) {
					return false;
				}
			}
		}
		catch (NullPointerException e) {
			if (a2 != a1)
			return false;
		}
	
		return true;
	}

	/**
	 * Returns the concatenation of the two specified arrays.
	 * 
	 * @param a1
	 *            an {@code int} array.
	 * @param a2
	 *            an {@code int} array.
	 * @return an array that corresponds to the concatenation of the two specified arrays.
	 */
	public static int[] concatenate(int[] a1, int[] a2) {
		int l1 = 0;
		int l2 = 0;
		int l = 0;
		int count = 0;
		try {
		l1 = a1.length;
		l2 = a2.length;
		}
		catch (NullPointerException e) { 
			if (l1 >= 0 ) {
				if (l2 == 0)
				return a1;
			}
			if (l2 >= 0) {
				if(l1 == 0) 
					return a2;
			}
		}
		l = l1 + l2;
		int [] a3 = new int [l];
		for (int x = 0; x < l1; x++) { 
			a3[x] = a1[x];
			count++;
		}
		for (int x = 0; x < l2; x++) { 
			a3[count++] = a2[x];
		}
		return a3;
	}

	/**
	 * Merges two sorted arrays into a sorted array.
	 * 
	 * @param a1
	 *            a sorted {@code int} array.
	 * @param a2
	 *            a sorted {@code int} array.
	 * @return a sorted array containing the elements from the specified sorted arrays.
	 */
	public static int[] merge(int[] a1, int[] a2) {
		int array[] = concatenate(a1, a2);
        int lastPos;
        int index;
        int temp;
        int count = 0;
        int count2 = 0;
        try {
        for (lastPos = array.length - 1; lastPos >= 0; lastPos--)
          {
            for (index = 0; index <= lastPos - 1; index++)
            {
                count2++;

                if (array[index] > array[index + 1])
                {
                    count++;
                    temp = array[index];
                    array[index] = array[index + 1];
                    array[index + 1] = temp;
                }
            }
          }
		return array;
        }
        catch (NullPointerException e) { 
        	
        }
        return null;
	}
	

	/**
	 * The main function.
	 * 
	 * @param args
	 *            the program arguments.
	 */
	public static void main(String[] args) {
		System.out.println("Problem 2:");
		testToString();
		System.out.println("Problem 3:");
		testCompare();
		System.out.println("Problem 4:");
		testConcatenate();
		System.out.println("Problem 5:");
		testMerge();
	}

	private static void testToString() {
		System.out.println(toString(new int[] { 1, 2, 3 }));
		System.out.println(toString(new int[] {}));
		System.out.println(toString(null));
	}

	private static void testCompare() {
		System.out.println(compare(new int[] { 1, 2, 3 }, new int[] { 1, 2, 3 }));
		System.out.println(compare(new int[] { 1, 2, 3 }, new int[] { 1, 2, 4 }));
		System.out.println(compare(new int[] { 1, 2, 3 }, new int[] { 1, 2 }));
		System.out.println(compare(new int[] { 1, 2, 3 }, null));
		System.out.println(compare(null, null));
	}

	private static void testConcatenate() {
		System.out.println(toString(concatenate(new int[] { 1, 2, 3 }, new int[] { 1, 3 })));
		System.out.println(toString(concatenate(new int[] { 1, 2, 3 }, null)));
		System.out.println(toString(concatenate(null, null)));
	}

	private static void testMerge() {
		System.out.println(toString(merge(new int[] { 1, 2, 3 }, new int[] { 1, 3 })));
		System.out.println(toString(merge(new int[] { 1, 2, 3 }, null)));
		System.out.println(toString(merge(null, null)));
	}

}
