package homework6;
/**
 * A {@code CommunicationException} indicates a communication failure.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class CommunicationException extends Exception {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 2222272510071849622L;

	/**
	 * Constructs a new {@code CommunicationException}.
	 * 
	 * @param cause
	 *            the cause of the {@code CommunicationException}.
	 */
	public CommunicationException(Throwable cause) {
		super(cause);
	}
}
