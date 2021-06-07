package homework6;
import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;

/**
 * A {@code Server} can communicate with multiple {@code Client}s.
 * 
 * @author Jeong-Hyon Hwang (jhhwang@cs.albany.edu)
 */
public abstract class Server implements Runnable {

	/**
	 * The {@code ServerSocket} for this {@code Server}.
	 */
	java.net.ServerSocket serverSocket;

	/**
	 * The {@code Communicator}s for communicator with {@code Client}s.
	 */
	HashSet<Communicator> communicators = new HashSet<Communicator>();

	/**
	 * Constructs a {@code Server}.
	 * 
	 * @param port
	 *            the port number.
	 * @throws IOException
	 *             if the specified port is not available.
	 */
	public Server(int port) throws IOException {
		serverSocket = new java.net.ServerSocket(port);
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket s = serverSocket.accept(); // a new socket s created for each new connection
				// Problem 4 (20 points)
				Communicator comm = new Communicator(s);
				communicators.add(comm);
				new Thread().start();
				handleClient(comm);
				communicators.remove(comm);
				s.toString();
			} catch (Exception e) {
				return;
			}
		}
	}
	/**
	 * Communicates with a {@code Client} using the specified {@code Communicator}.
	 * 
	 * @param communicator
	 *            a {@code Communicator} connected to a {@code Client}.
	 * @throws Exception
	 *             if an error occurs.
	 */
	abstract protected void handleClient(Communicator communicator) throws Exception;

	/**
	 * Shuts down this {@code Server}.
	 */
	public void shutdown() {
		try {
			serverSocket.close();
		} catch (Exception e) {
		}
		synchronized (communicators) {
			for (Communicator communicator : communicators)
				communicator.shutdown();
		}
	}

}
