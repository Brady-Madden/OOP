package homework6;


/**
 * A {@code LookupException} occurs if a remote object lookup fails.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class LookupException extends Exception {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 2852209841903957004L;

	/**
	 * Constructs an {@code LookupException}.
	 * 
	 * @param message
	 *            the message that explains the {@code LookupException}.
	 */
	LookupException(String message) {
		super(message);
	}
}
