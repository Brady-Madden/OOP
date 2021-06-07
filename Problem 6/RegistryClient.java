package homework6;

import java.io.IOException;

/**
 * A {@code RegistryClient} can connect to a {@code Registry} and obtain a stub for accessing an object managed by the
 * {@code Registry}.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class RegistryClient extends Communicator {

	/**
	 * Constructs a {@code RegistryClient}.
	 * 
	 * @param address
	 *            the address of the {@code Registry}.
	 * @param port
	 *            the port number of the {@code Registry}.
	 * @throws IOException
	 *             if cannot connect to the specified {@code Registry}.
	 */
	public RegistryClient(String address, int port) throws IOException {
		super(Communicator.createSocket(address, port));
	}

	/**
	 * Returns a stub for accessing an object managed by the {@code Registry}.
	 * 
	 * @param objectID
	 *            the ID of the object.
	 * @param objectType
	 *            the type of the object (must be an interface).
	 * @return a stub for accessing an object managed by the {@code Registry}.
	 * @throws CommunicationException
	 *             if a communication error occurs.
	 * @throws LookupException
	 *             if the Registry has no object with the specified ID and type.
	 */
	public <T> T lookup(String objectID, Class<?> objectType) throws LookupException, CommunicationException {
		if (!objectType.isInterface())
			throw new LookupException("" + objectType + " is not an interface.");
		try {
			send(new LookupRequest(objectID, objectType));
			Boolean result = (Boolean) receiveObject();
			if (result) {
				return createStub(objectID, objectType);
			} else {
				throw new LookupException("The remote object is not of the " + objectType + " type.");
			}
		} catch (IOException e) {
			throw new CommunicationException(e);
		} catch (ClassNotFoundException e) {
			throw new CommunicationException(e);
		}
	}

	/**
	 * Constructs a stub for accessing an object managed by the {@code Registry}.
	 * 
	 * @param objectID
	 *            the ID of the object.
	 * @param objectType
	 *            the type of the object (must be an interface).
	 * @return a stub for accessing an object managed by the {@code Registry}.
	 */
	protected <T> T createStub(final String objectID, Class<?> objectType) {
		// Problem 6 (10 points)
		return null;
	}

}
