package homework5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;
import java.io.Serializable;

import homework5.SlidingPuzzle.Position;

/**
 * A {@code GameServerImpl} provides {@code SlidingPuzzle} games to players.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class GameServerImpl implements GameServer {

	/**
	 * The {@code SlidingPuzzle} to provide to game players.
	 */
	protected SlidingPuzzle puzzle;

	/**
	 * A map that associates player IDs with {@code SlidingPuzzle}s.
	 */
	protected HashMap<String, SlidingPuzzle> player2puzzle = new HashMap<String, SlidingPuzzle>();

	/**
	 * A map that associates player IDs with {@code GameResult}s.
	 */
	protected HashMap<String, GameResult> player2result;

	/**
	 * The {@code GameResult}s (ranked).
	 */
	protected TreeSet<GameResult> results;

	/**
	 * The name of the file to store data.
	 */
	protected String fileName;

	/**
	 * Constructs a {@code GameServer}.
	 * 
	 * @param rows
	 *            the number of rows in the {@code SlidingPuzzle} to provide to game players.
	 * @param columns
	 *            the number of columns in the {@code SlidingPuzzle} to provide to game players.
	 */
	public GameServerImpl(int rows, int columns) {
		fileName = "server." + rows + "." + columns;
		try {
			load(fileName + ".dat");
		} catch (Exception e) {
			try {
				load(fileName + ".bak");
			} catch (Exception ee) {
				puzzle = new SlidingPuzzle(rows, columns);
				player2result = new HashMap<String, GameResult>();
				results = new TreeSet<GameResult>();
				save();
			}
		}
		System.out.println(puzzle);
		printReport();
	}

	@Override
	public synchronized SlidingPuzzle getPuzzle(String playerID) {
		SlidingPuzzle puzzle = new SlidingPuzzle(this.puzzle);
		player2puzzle.put(playerID, puzzle);
		return puzzle;
	}

	@Override
	public synchronized Position slide(String playerID, int row, int column) {
		SlidingPuzzle puzzle = player2puzzle.get(playerID);
		Position p = puzzle.slide(row, column);
		if (puzzle.isSolved()) {
			player2puzzle.remove(playerID); // garbage collection
			GameResult result = new GameResult(playerID, puzzle.elapsedTimeMillis(), puzzle.moves());
			GameResult prevResult = player2result.get(playerID);
			if (prevResult == null || result.compareTo(prevResult) < 0) { // if first or better result
				player2result.put(playerID, result);
				if (prevResult != null)
					results.remove(prevResult);
				results.add(result);
				save();
				printReport();
			}
		}
		return p;
	}

	@Override
	public synchronized GameResult[] results() {
		return results.toArray(new GameResult[0]);
	}

	/**
	 * Saves data in system files.
	 */
	protected void save() {
		try {
			save(fileName + ".dat");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			save(fileName + ".bak");
		} catch (Exception e) {
		}
	}

	/**
	 * Prints a report about this {@code GameServerImpl}.
	 */
	protected void printReport() {
		System.out.println(new Date());
		for (GameResult r : results)
			System.out.println(" " + r);
		System.out.println();
	}

	/**
	 * Saves data in the specified file.
	 * 
	 * @param fileName
	 *            the name of the file.
	 * @throws FileNotFoundException
	 *             if the file exists but is a directory rather than a regular file, does not exist but cannot be
	 *             created, or cannot be opened for any other reason.
	 * @throws IOException
	 *             if the specified file cannot be updated.
	 */
	protected void save(String fileName) throws FileNotFoundException, IOException {
		// Problem 5 (20 points)
		FileOutputStream file = new FileOutputStream(fileName);
		ObjectOutputStream objectoutput = new ObjectOutputStream(file);
		try {
		objectoutput.writeObject(this.puzzle);
		objectoutput.writeObject(this.player2result);
		objectoutput.writeObject(this.results);
		objectoutput.close();
		file.close();
		} 
		catch (IOException e) {
			throw new UnsupportedOperationException();
			//not serializable exception handler
		}
	}

	/**
	 * Loads data from the specified file.
	 * 
	 * @param fileName
	 *            the name of the file.
	 * @throws FileNotFoundException
	 *             if the specified file cannot be found.
	 * @throws IOException
	 *             if the specified file cannot be read.
	 * @throws ClassNotFoundException
	 *             if the class of a serialized object cannot be found.
	 */
	@SuppressWarnings("unchecked")
	protected void load(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Problem 6 (20 points)
		FileInputStream fin = new FileInputStream(fileName);
		ObjectInputStream input = new ObjectInputStream(fin);
		this.puzzle = (SlidingPuzzle)input.readObject();
		this.player2result = (HashMap<String, GameResult>)input.readObject();
		this.results = (TreeSet<GameResult>)input.readObject();
		input.close();
		fin.close();
		throw new UnsupportedOperationException();
	}

}
