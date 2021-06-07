package homework6;

/**
 * The {@code SlidingPuzzleTest} class contains code for testing the {@code SlidingPuzzle} game.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class SlidingPuzzleTest extends homework5.SlidingPuzzleTest {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            string arguments.
	 */
	public static void main(String args[]) {
		try {
			new GameServerImpl(4, 4, 12000);
			startTest("csi405", GameServerImpl.createProxy("127.0.0.1", 12000), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
