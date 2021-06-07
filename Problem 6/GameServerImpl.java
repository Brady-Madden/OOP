package homework6;

import java.io.IOException;

import homework5.GameServer;

public class GameServerImpl extends homework5.GameServerImpl {

	/**
	 * The {@code Registry} for this {@code GameServerImpl}.
	 */
	protected Registry registry;

	/**
	 * Constructs a {@code GameServerImpl}.
	 * 
	 * @param rows
	 *            the number of rows in the {@code SlidingPuzzle} to provide to game players.
	 * @param columns
	 *            the number of columns in the {@code SlidingPuzzle} to provide to game players.
	 * @param port
	 *            the communication port to use.
	 * @throws IOException
	 *             if the specified port is not available.
	 */
	public GameServerImpl(int rows, int columns, int port) throws IOException {
		super(rows, columns);
		registry = new Registry(port);
		registry.register("", this); // register this GameServer
	}

	/**
	 * Returns a proxy to the specified {@code GameServer}.
	 * 
	 * @param address
	 *            the address of the {@code GameServer}.
	 * @param port
	 *            the port of the {@code GameServer}.
	 * @return a proxy to the specified {@code GameServer}.
	 * @throws CommunicationException
	 *             if a communication error occurs.
	 */
	public static GameServer createProxy(String address, int port) throws CommunicationException {
		try {
			RegistryClient client = new RegistryClient(address, port);
			return client.lookup("", GameServer.class); // get a stub/proxy to access the GameServer
		} catch (Throwable t) {
			throw new CommunicationException(t);
		}
	}

	/**
	 * Shuts down this {@code GameServer}.
	 */
	public void shutdown() {
		registry.shutdown();
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments.
	 * @throws NumberFormatException
	 *             if the arguments are not integers.
	 * @throws IOException
	 *             if the specified port is not available.
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		new GameServerImpl(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
	}

}
