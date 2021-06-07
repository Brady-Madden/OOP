import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * A {@code MultiValuedTreeMap} associates each key with a set of values.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 * @param <K>
 *            the type of keys.
 * @param <V>
 *            the type of values.
 */
public class MultiValuedTreeMap<K, V> extends TreeMap<K, LinkedList<V>> implements Iterable<Pair<K, V>> {

	/**
	 * An automatically generated serial version ID.
	 */
	private static final long serialVersionUID = -6229569372944782075L;

	/**
	 * Adds the specified key and value.
	 * 
	 * @param k
	 *            the key to add.
	 * @param v
	 *            the value to add.
	 */
	public void add(K k, V v) { // Problem 1
		//if no list associated with key create new list 
		if (!containsKey(k)) {
			LinkedList<V> temp = new LinkedList<V>();
			//put new key into list at value v
			this.put(k, temp);
		}
		//if list has the key insert value at that key
		if (containsKey(k)) {
			this.get(k).add(v);
		}
	}

	/**
	 * Removes the first value associated with the specified key.
	 * 
	 * @param k
	 *            a key.
	 * @return the first value associated with the specified key; {@code null} if no such value.
	 */
	public V removeFirst(K k) { // Problem 2
		//create value instance to return that value
		V value = null;
		if (containsKey(k)) {
			//remove value at specified key
			value = get(k).removeFirst();
		}
		//if no key return null
		if (!containsKey(k)) {
		return null;
		}

		return value;
	}

	/**
	 * Removes the first value associated with the first key.
	 * 
	 * @return a {@code Pair} containing the first key and its first value; {@code null} if this
	 *         {@code MultiValuedTreeMap} is empty.
	 */
	public Pair<K, V> removeFirst() { // Problem 3
		//if the list is empty return null
		if(this.isEmpty()) {
			return null;
		}
		//create k,v pair for first node  
		Pair<K,V> pair = new Pair<K,V>(firstKey(), get(this.firstKey()).getFirst());
		   //assigns k to firstkey and removes it from list
			K k = firstKey();
		    removeFirst(k);
		    //if first key has no values remove the key from the map
		    if(get(this.firstKey()).isEmpty()) {
		        remove(this.firstKey());
		    }
		    return pair;
		}
	/**
	 * Returns an iterator over the key-value pairs contained in this {@code MultiValuedTreeMap}.
	 * 
	 * @return an iterator over the key-value pairs contained in this {@code MultiValuedTreeMap}.
	 */
	@SuppressWarnings("unchecked")
	public Iterator<Pair<K, V>> iterator() { // Problem 6
		//create a list for the k,v pairs
		LinkedList<Pair <K,V>> list = new LinkedList<Pair<K,V>>();
		//iterate over all the keys in the list
		for (Pair<K, V> k: list) {
			//iterate over all the values given the key
			for (V v: get(k)) {
				//add these pairs to the list
				list.add(new Pair<K,V>((K) k,v));
			}
		}
		//return every element of the list	
		return list.iterator();
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the program arguments.
	 */
	public static void main(String[] args) {
		MultiValuedTreeMap<Integer, String> m = new MultiValuedTreeMap<Integer, String>();
		m.add(1, "b");
		m.add(1, "a");
		m.add(0, "c");
		System.out.println(m);
		
		System.out.println(m.removeFirst(1));
		System.out.println(m);
		
		System.out.println(m.removeFirst());
		System.out.println(m.removeFirst());
		System.out.println(m.removeFirst());

	    m.add(1, "b");
		m.add(1, "a");
		m.add(0, "c");
		System.out.println(m); 
		for (Pair<Integer, String> p : m) {
			System.out.println(p);
		} 
	}

}
