package homework5;

import java.util.Date;

/**
 * A {@code GameResult} represents the result of a game.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class GameResult implements Comparable<GameResult> {

	/**
	 * The ID of the player.
	 */
	protected String playerID;

	/**
	 * The amount of time spent for the game.
	 */
	protected long time;

	/**
	 * The number of moves.
	 */
	protected int moves;

	/**
	 * The time when this {@code GameResult} is constructed.
	 */
	protected Date date = new Date();

	/**
	 * Constructs a {@code GameResult}.
	 * 
	 * @param playerID
	 *            the ID of the player.
	 * @param time
	 *            the amount of time spent for the game.
	 * @param moves
	 *            the number of moves.
	 */
	public GameResult(String playerID, long time, int moves) {
		this.playerID = playerID;
		this.time = time;
		this.moves = moves;
	}

	@Override
	public String toString() {
		return "player ID: " + playerID + ", time: " + time + ", moves: " + moves;
	}

	@Override
	public int compareTo(GameResult o) {
		int c = (int) Math.max(Math.min(1, time * moves - o.time * o.moves), -1);
		if (c != 0)
			return c;
		return o.playerID.compareTo(o.playerID);
	}

}
