package homework6;
import java.util.Set;

import java.util.TreeSet;

/**
 * This CommunicationTest shows an example of using the communication package.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class RemoteMethodInvocationTest {

	/**
	 * The main program of CommunicationTest.
	 * 
	 * @throws Exception
	 *             if an error occurs.
	 */
	public static void main(String args[]) throws Exception {
		Registry registry = new Registry(10000); // construct a Registry
		registry.register("123", new TreeSet<Integer>()); // register a TreeSet
		RegistryClient client = new RegistryClient("127.0.0.1", 10000);
		Set<Integer> s = client.lookup("123", Set.class);
		s.add(2);
		s.add(1);
		System.out.println(s);
		registry.shutdown();
	}

}
