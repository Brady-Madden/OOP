package homework5;

import java.awt.Component;
import java.io.Serializable;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 * The {@code SlidingPuzzleTest} class contains code for testing the {@code SlidingPuzzle} game.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class SlidingPuzzleTest {

	private static final String String = null;
	private static final Object Object = null;
	private static final Component Component = null;
	private static final Icon Icon = null;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            string arguments.
	 */
	public static void main(String[] args) {
		startTest("csi405", new GameServerImpl(4, 4), null);
	}

	/**
	 * Start the test.
	 * 
	 * @param defaultPlayerID
	 *            the default player ID.
	 * @param server
	 *            the server to use.
	 * @param solver
	 *            a {@code SlidingPuzzleSolver}.
	 */
	public static void startTest(final String defaultPlayerID, final GameServer server, final SlidingPuzzleSolver solver) {
		while (true) {
			final String playerID = getPlayerID(defaultPlayerID);
			if (playerID == null) // if the "Cancel" button is pressed.
				System.exit(1);
			if (playerID.length() > 0) {
				Runnable r = new Runnable() { // start a SlidingPuzzleFrame
					public void run() {
						SlidingPuzzleFrame frame = new SlidingPuzzleFrame(playerID, server, solver);
						frame.setVisible(true);
					}
				};
				EventQueue.invokeLater(r);
				return;
			}
		}
	}

	/**
	 * Obtains the player ID using a blocking input dialog.
	 * 
	 * @param defaultPlayerID
	 *            the default player ID.
	 * @return the player ID obtained from the user.
	 */
	protected static String getPlayerID(String defaultPlayerID) {	
		//initialize dialog box variables
		int messageType = JOptionPane.PLAIN_MESSAGE;
		Icon icon = null;
		Component parentComponent = null;
		Object initialSelectionValue = null;
		java.lang.Object[] selectionValues = null;
		java.lang.Object message = null;
		java.lang.String title = "Enter Player ID:";
		//shows
		defaultPlayerID = (java.lang.String) JOptionPane.showInputDialog(parentComponent, message, title,  messageType, icon, selectionValues, initialSelectionValue);
		System.out.println(defaultPlayerID);
		return defaultPlayerID;
	}
}
