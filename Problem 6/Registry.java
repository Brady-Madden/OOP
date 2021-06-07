package homework6;

import java.io.IOException;
import java.lang.reflect.*;
import java.util.HashMap;

/**
 * A {@code Registry} maintains a collection of objects that can be used by remote clients.
 * 
 * @author Jeong-Hyon Hwang (jhhwang@cs.albany.edu)
 */
public class Registry extends Server {

	/**
	 * The objects managed by this {@code Registry}.
	 */
	HashMap<String, Object> objects = new HashMap<String, Object>();

	/**
	 * Constructs a {@code Registry}.
	 * 
	 * @param port
	 *            the communication port.
	 * @throws IOException
	 *             if the specified port is not available.
	 */
	public Registry(int port) throws IOException {
		super(port);
	}

	/**
	 * Adds an object to this {@code Registry}.
	 * 
	 * @param objectID
	 *            the identifier of the object.
	 * @param object
	 *            the object to add.
	 */
	public void register(String objectID, Object object) {
		objects.put(objectID, object);
	}

	/**
	 * Removes the specified object.
	 * 
	 * @param objectID
	 *            the identifier of the object.
	 */
	public void remove(String objectID) {
		objects.remove(objectID);
	}

	@Override
	protected void handleClient(Communicator communicator) throws ClassNotFoundException, IOException,
			MethodInvocationException {
		for (;;) {
			Object request = communicator.receiveObject();
			if (request instanceof LookupRequest) { // lookup request
				communicator.send(handle((LookupRequest) request));
			} else if (request instanceof MethodInvocationRequest) { // method invocation
				communicator.send(handle((MethodInvocationRequest) request));
			} else
				communicator.send(request);
		}
	}

	/**
	 * Handles the specified {@code LookupRequest}.
	 * 
	 * @param lookupRequest
	 *            the {@code LookupRequest}.
	 * @return true if the lookup request succeeds (i.e., the object to access has the type specified by the client);
	 *         false otherwise.
	 */
	protected boolean handle(LookupRequest lookupRequest) {
		String objectID = lookupRequest.objectID();
		Class<?> objectType = lookupRequest.objectType();
		Object object = objects.get(objectID);
		return objectType.isInstance(object); // true if the object to access has the type specified by the client.
	}

	/**
	 * Handles the specified {@code MethodInvocationRequest}.
	 * 
	 * @param r
	 *            the {@code MethodInvocationRequest} to process.
	 * @return the result of invocation; a {@code MethodInvocationException} if the method invocation fails.
	 * @throws MethodInvocationException
	 *             if an error occurs during method invocation.
	 */
	protected Object handle(MethodInvocationRequest r) throws MethodInvocationException {
		return invoke(objects.get(r.objectID()), r.methodName(), r.parameterTypes(), r.args());
	}

	/**
	 * Invokes the specified method on the specified object.
	 * 
	 * @param obj
	 *            an object.
	 * @param methodName
	 *            the name of the method.
	 * @param parameterTypes
	 *            the parameter types of the method.
	 * @param args
	 *            the arguments for the method.
	 * @return the return value of the invoked method.
	 * @throws MethodInvocationException
	 *             if an error occurs during method invocation.
	 */
	public static Object invoke(Object obj, String methodName, Class<?>[] parameterTypes, Object[] args)
			throws MethodInvocationException {
		// Problem 5 (10 points)
		 Class<?> invokeclass = obj.getClass();
	        try {
	            Method md = invokeclass.getMethod(methodName, parameterTypes);
	            Object out = md.invoke(obj, args);
	            return out;
	        } catch (NoSuchMethodException e) {
	            System.out.println("Method" + methodName + " does not exist.\n");
	        } catch(IllegalAccessException i) {
	            i.printStackTrace();
	        } catch(InvocationTargetException i) {
	            i.printStackTrace();
	        }
		return null;
	}

}
