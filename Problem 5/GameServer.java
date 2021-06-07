package homework5;

import homework5.SlidingPuzzle.Position;

/**
 * The {@code GameServer} interface defines core methods for providing {@code SlidingPuzzle} games to players.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public interface GameServer {

	/**
	 * Returns a {@code SlidingPuzzle} for the specified player.
	 * 
	 * @param playerID
	 *            the ID of the player.
	 * @return a {@code SlidingPuzzle} for the specified player.
	 */
	public SlidingPuzzle getPuzzle(String playerID);

	/**
	 * Slides, if possible, the specified tile into the empty position.
	 * 
	 * @param playerID
	 *            the ID of the player.
	 * @param row
	 *            the row index of the tile to slide.
	 * @param column
	 *            the column index of the tile to slide.
	 * @return the new {@code Position} of the tile; {@code null} if it is not possible to move the tile.
	 */
	public Position slide(String playerID, int row, int column);

	/**
	 * Returns the current {@code GameResult}s.
	 * 
	 * @return the current {@code GameResult}s.
	 */
	public GameResult[] results();

}
