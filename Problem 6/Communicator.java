package homework6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * A {@code Communicator} can communicate with another {@code Communicator}. These {@code Communicator}s can
 * send/receive objects to/from each other.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class Communicator {

	/**
	 * The input stream.
	 */
	protected java.io.ObjectInputStream in;

	/**
	 * The output stream.
	 */
	protected java.io.ObjectOutputStream out;

	/**
	 * The {@code Socket} for this {@code Communicator}.
	 */
	protected Socket socket;

	/**
	 * Constructs a {@code Communicator}.
	 * 
	 * @param socket
	 *            the {@code Socket} to use.
	 * @throws IOException
	 *             if the specified {@code Socket} cannot be used.
	 */
	public Communicator(java.net.Socket socket) throws IOException {
		this.socket = socket;
		// Problem 1 (20 points)
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new  ObjectInputStream(socket.getInputStream());
	}

	/**
	 * Sends the specified object to the other {@code Communicator} connected to this {@code Communicator}.
	 * 
	 * @param object
	 *            an object to send.
	 * @throws IOException
	 *             if an error occurs while sending the specified object to the other {@code Communicator}.
	 */
	public void send(Object object) throws IOException {
		// Problem 2 (20 points)
		out.writeObject(object);
	}

	/**
	 * Receives an object from the other {@code Communicator} connected to this {@code Communicator}.
	 * 
	 * @return an object received from the other {@code Communicator} connected to this {@code Communicator}.
	 * @throws IOException
	 *             if an error occurs when the object is being received from the {@code Server}.
	 * @throws ClassNotFoundException
	 *             if the class of the received object cannot be found.
	 */
	public Object receiveObject() throws IOException, ClassNotFoundException {
		return in.readObject();
	}

	/**
	 * Shuts down this {@code Communicator}.
	 */
	public void shutdown() {
		try {
			socket.close();
		} catch (Exception e) {
		}
	}

	/**
	 * Constructs a {@code Socket} connected to the specified port on the specified host.
	 * 
	 * @param address
	 *            the IP address of the host.
	 * @param port
	 *            the port number.
	 * @return a {@code Socket} connected to the specified port on the specified host.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static Socket createSocket(String address, int port) throws UnknownHostException, IOException {
		for (;;) {
			// Problem 3 (20 points)
			Socket socket = new Socket(address, port);
			return socket;
		}
	}

}
