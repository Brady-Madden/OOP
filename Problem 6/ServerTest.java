package homework6;

import java.io.IOException;

public class ServerTest {

	/**
	 * The main program.
	 * 
	 * @param args
	 *            {@code String} arguments.
	 * @throws Exception
	 *             if an error occurs.
	 */
	public static void main(String... args) throws Exception {
		Server server = new Server(10000) {

			@Override
			protected void handleClient(Communicator communicator) throws ClassNotFoundException, IOException {
				while (true) {
					Object o = communicator.receiveObject();
					communicator.send(o);
				}
			}

		}; // construct server
		Communicator c = new Communicator(Communicator.createSocket("127.0.0.1", 10000));
		String message = "Hi, there!";
		c.send(message); // send message
		System.out.println("received: " + c.receiveObject()); // receive an object from server
		server.shutdown();// shutdown server
	}
}
