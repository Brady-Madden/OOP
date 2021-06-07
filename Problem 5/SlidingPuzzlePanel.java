package homework5;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import homework5.SlidingPuzzle.Position;

/**
 * A {@code SlidingPuzzlePanel} shows a {@code SlidingPuzzle}.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class SlidingPuzzlePanel extends JPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -7135757912459575183L;

	/**
	 * A {@code JLabel} representing the empty slot.
	 */
	protected JLabel emptySlot;

	/**
	 * The {@code SlidingPuzzle} to show.
	 */
	protected SlidingPuzzle puzzle;

	/**
	 * The {@code SlidingPuzzleFrame} which contains this {@code SlidingPuzzlePanel}.
	 */
	protected SlidingPuzzleFrame frame;

	/**
	 * The player ID.
	 */
	protected String playerID;

	/**
	 * Constructs a {@code SlidingPuzzlePanel}.
	 * 
	 * @param playerID
	 *            the player ID.
	 * @param frame
	 *            the {@code SlidingPuzzleFrame} to contain the constructed {@code SlidingPuzzlePanel}.
	 */
	public SlidingPuzzlePanel(String playerID, SlidingPuzzleFrame frame) {
		this.playerID = playerID;
		this.frame = frame;
		this.puzzle = frame.server.getPuzzle(playerID);
		setLayout(new GridLayout(puzzle.rows, puzzle.columns, 0, 0));
		for (int i = 0; i < puzzle.rows; i++) {
			for (int j = 0; j < puzzle.columns; j++) {
				int tileID = puzzle.tile(i, j); // get the tile ID
				if (tileID == 0) { // if the empty slot
					emptySlot = new JLabel("");
					add(emptySlot);
				} else { // if a tile
					JButton tile = new JButton("" + tileID);
					tile.setFocusable(false);
					add(tile); // register the JButton representing the current tile as a child of this panel
					if (frame.solver == null) // if there is no automatic puzzle solver
						addActionListener(tile); // add action listener to allow a user to play the game
				}
			}
		}
		frame.movesTextField.setText("" + puzzle.moves()); // show the number (0) of moves on the screen
		if (frame.solver != null) { // if there is an automatic puzzle solver.
			new Thread(new Runnable() { // start a new thread solving the puzzle and updating this panel accordingly
						@Override
						public void run() {
							Iterable<Position> positions = SlidingPuzzlePanel.this.frame.solver.solve(puzzle);
							for (final Position p : positions) { // positions of tiles to slide into the empty slot
								EventQueue.invokeLater(new Runnable() {
									@Override
									public void run() {
										slide(p.row, p.column); // update this panel to show the movement of tiles
									}
								});
							}
						}
					}).start();
		}
	}

	/**
	 * Creates an {@code ActionListener} and adds it to the specified {@code JButton} representing a sliding tile. When
	 * the {@code JButton} is pressed, the {@code ActionListener} needs to call the {@link #slide(JButton)} method.
	 * 
	 * @param tile
	 *            a {@code JButton} representing a tile.
	 */
	protected void addActionListener(JButton tile) {
tile.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e) {
		e.getSource();
		slide(tile);
	}
});
	
	}
	
	

	/**
	 * Slides, if possible, the specified tile into the empty slot.
	 * 
	 * @param tile
	 *            the tile to slide.
	 */
	protected void slide(JButton tile) {
		Dimension size = tile.getSize();
		int row = tile.getY() / size.height;
		int column = tile.getX() / size.width;
		slide(row, column);
	}

	/**
	 * Slides, if possible, the specified tile into the empty slot.
	 * 
	 * @param row
	 *            the row in which the tile is located.
	 * @param column
	 *            the column in which the tile is located.
	 */
	protected synchronized void slide(int row, int column) {
		Position p = puzzle.slide(row, column); // slide the tile at the specified location
		frame.server.slide(playerID, row, column); // inform the server of the tile to move
		if (p != null) { // if it was possible to move the tile
			int prevIndex = row * puzzle.columns + column; // previous index of the tile
			int newIndex = p.row() * puzzle.columns + p.column(); // new index of the tile
			Component tile = getComponent(prevIndex); // find the JButton representing the tile
			remove(Math.min(prevIndex, newIndex)); // remove a component
			add(emptySlot, prevIndex); // add the empty slot at the previous index
			add(tile, newIndex); // add the tile at the new index
			validate(); // schedule to re-display the children of this panel
			frame.movesTextField.setText("" + puzzle.moves()); // update the # of moves on screen
			if (puzzle.isSolved()) {
				finalizeGame(); // inform the user of the completion of the game
			}
		}
	}

	/**
	 * Informs the user of the completion of the game.
	 */
	protected void finalizeGame() {
		frame.timerTextField.stop(); // stops the timer
		JOptionPane.showConfirmDialog(null, "Congratulations! You solved the puzzle!", "Confirmation",
				JOptionPane.PLAIN_MESSAGE);
		for (Component c : getComponents()) { // disable all JButtons representing tiles
			c.setEnabled(false);
		}
		EventQueue.invokeLater(new Runnable() { // update the result table
					@Override
					public void run() {
						frame.updateResultTable(frame.server.results());
					}
				});
	}

}

