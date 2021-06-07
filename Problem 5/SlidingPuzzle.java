package homework5;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A {@code SlidingPuzzle} represents a sliding puzzle instance.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class SlidingPuzzle {

	/**
	 * The number of rows.
	 */
	protected int rows;

	/**
	 * The number of columns.
	 */
	protected int columns;

	/**
	 * The tiles.
	 */
	protected int[][] tiles;

	/**
	 * The empty position of this {@code SlidingPuzzle}.
	 */
	protected Position emptyPosition;

	/**
	 * The number of tiles placed correctly.
	 */
	protected int completedTiles;

	/**
	 * The number of moves so far.
	 */
	protected int moves = 0;

	/**
	 * The start time of this {@code SlidingPuzzle}.
	 */
	protected long startTime = System.currentTimeMillis();

	/**
	 * A {@code Position} represents a position on a {@code SlidingPuzzle}.
	 * 
	 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
	 */
	public static class Position implements java.io.Serializable {

		/**
		 * The serial version UID.
		 */
		private static final long serialVersionUID = -181214490658118736L;

		/**
		 * The row index of this {@code Position}.
		 */
		protected int row;

		/**
		 * The column index of this {@code Position}.
		 */
		protected int column;

		/**
		 * Constructs a {@code Position}.
		 * 
		 * @param row
		 *            the row index of the {@code Position}.
		 * @param column
		 *            the column index of the {@code Position}.
		 */
		public Position(int row, int column) {
			this.row = row;
			this.column = column;
		}

		/**
		 * Constructs a {@code Position} as a clone of the specified {@code Position}.
		 * 
		 * @param other
		 *            a {@code Position}.
		 */
		public Position(Position other) {
			this.row = other.row;
			this.column = other.column;
		}

		/**
		 * Returns the row index of this {@code Position}.
		 * 
		 * @return the row index of this {@code Position}.
		 */
		public int row() {
			return row;
		}

		/**
		 * Returns the column index of this {@code Position}.
		 * 
		 * @return the column index of this {@code Position}.
		 */
		public int column() {
			return column;
		}

		@Override
		public String toString() {
			return "(" + row + ", " + column + ")";
		}
	}

	/**
	 * Constructs a {@code SlidingPuzzle}.
	 * 
	 * @param rows
	 *            the number of rows.
	 * @param columns
	 *            the number of columns.
	 */
	public SlidingPuzzle(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		tiles = new int[rows][columns];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				tiles[i][j] = i * columns + j + 1;
		emptyPosition = new Position(rows - 1, columns - 1);
		tiles[rows - 1][columns - 1] = 0;
		completedTiles = rows * columns - 1;
		shuffle();
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++)
				s += String.format("%3d", tiles[i][j]);
			s += System.lineSeparator();
		}
		return s;
	}

	/**
	 * Constructs a {@code SlidingPuzzle} as a clone of the specified {@code SlidingPuzzle}.
	 * 
	 * @param other
	 *            a {@code SlidingPuzzle}.
	 */
	public SlidingPuzzle(SlidingPuzzle other) {
		this.rows = other.rows;
		this.columns = other.columns;
		this.tiles = new int[rows][columns];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				tiles[i][j] = other.tiles[i][j];
		this.emptyPosition = new Position(other.emptyPosition);
		this.completedTiles = other.completedTiles;
		this.moves = other.moves;
	}

	/**
	 * Returns the number of rows in this {@code SlidingPuzzle}.
	 * 
	 * @return the number of rows in this {@code SlidingPuzzle}.
	 */
	public int rows() {
		return rows;
	}

	/**
	 * Returns the number of columns in this {@code SlidingPuzzle}.
	 * 
	 * @return the number of columns in this {@code SlidingPuzzle}.
	 */
	public int columns() {
		return columns;
	}

	/**
	 * Slide, if possible, the specified tile into the empty position.
	 * 
	 * @param row
	 *            the row index of the tile to slide.
	 * @param column
	 *            the column index of the tile to slide.
	 * @return the new {@code Position} of the tile; {@code null} if it is not possible to move the tile.
	 */
	public Position slide(int row, int column) {
		if (Math.abs(row - emptyPosition.row) + Math.abs(column - emptyPosition.column) == 1) {
			boolean isCorrectlyPositioned = isCompleted(row, column);
			tiles[emptyPosition.row][emptyPosition.column] = tiles[row][column];
			tiles[row][column] = 0;
			Position p = emptyPosition;
			emptyPosition = new Position(row, column);
			completedTiles += (isCompleted(p.row, p.column) ? 1 : 0) - (isCorrectlyPositioned ? 1 : 0);
			moves++;
			return p;
		} else
			return null;
	}

	/**
	 * Determines whether or not the specified tile is correctly positioned.
	 * 
	 * @param row
	 *            a row.
	 * @param column
	 *            a column.
	 * @return {@code true} if the specified tile is correctly positioned; {@code false} otherwise.
	 */
	protected boolean isCompleted(int row, int column) {
		return tiles[row][column] == row * columns + column + 1;
	}

	/**
	 * Returns the tile at the specified position.
	 * 
	 * @param row
	 *            a row.
	 * @param column
	 *            a column.
	 * @return the tile at the specified position.
	 */
	public int tile(int row, int column) {
		return tiles[row][column];
	}

	/**
	 * Returns the number of moves so far.
	 * 
	 * @return the number of moves so far.
	 */
	public int moves() {
		return moves;
	}

	/**
	 * Determines whether or not this {@code SlidingPuzzle} is solved.
	 * 
	 * @return {@code true} if this {@code SlidingPuzzle} is solved; {@code false} otherwise.
	 */
	public boolean isSolved() {
		return completedTiles == rows * columns - 1;
	}

	/**
	 * Returns the elapsed time in milliseconds since the creation of this {@code SlidingPuzzle}.
	 * 
	 * @return the elapsed time in milliseconds since the creation of this {@code SlidingPuzzle}.
	 */
	public long elapsedTimeMillis() {
		return System.currentTimeMillis() - startTime;
	}

	/**
	 * Shuffles this {@code SlidingPuzzle}.
	 */
	protected void shuffle() {
		while (completedTiles > 0) {
			ArrayList<Position> candidatesCorrectlyPositioned = new ArrayList<Position>();
			ArrayList<Position> candidates = new ArrayList<Position>();
			update(candidatesCorrectlyPositioned, candidates, emptyPosition.row - 1, emptyPosition.column);
			update(candidatesCorrectlyPositioned, candidates, emptyPosition.row + 1, emptyPosition.column);
			update(candidatesCorrectlyPositioned, candidates, emptyPosition.row, emptyPosition.column - 1);
			update(candidatesCorrectlyPositioned, candidates, emptyPosition.row, emptyPosition.column + 1);
			Position next = candidatesCorrectlyPositioned.size() > 0 ? candidatesCorrectlyPositioned.get((int) (Math
					.random() * candidatesCorrectlyPositioned.size())) : candidates
					.get((int) (Math.random() * candidates.size()));
			slide(next.row, next.column);
		}
		moves = 0;
	}

	/**
	 * Inserts the specified tile into one of the specified collections.
	 * 
	 * @param candidatesCorrectlyPositioned
	 *            a collection to store tiles that are correctly positioned at the moment.
	 * @param candidates
	 *            a collection to store tiles that are not correctly positioned at the moment.
	 * @param row
	 *            the row index of a tile under consideration.
	 * @param column
	 *            the column index of a tile under consideration.
	 */
	protected void update(Collection<Position> candidatesCorrectlyPositioned, Collection<Position> candidates, int row,
			int column) {
		if (0 <= row && row < rows && 0 <= column && column < columns) {
			if (isCompleted(row, column))
				candidatesCorrectlyPositioned.add(new Position(row, column));
			else
				candidates.add(new Position(row, column));
		}
	}

}
