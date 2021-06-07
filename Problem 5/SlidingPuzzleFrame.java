package homework5;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JScrollPane;

/**
 * A {@code SlidingPuzzleFrame} allows a user to play a {@code SlidingPuzzle}.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class SlidingPuzzleFrame extends JFrame {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 4894690727803208497L;

	/**
	 * The date format to use in the result table.
	 */
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

	/**
	 * The {@code GameServer}.
	 */
	protected GameServer server;

	/**
	 * The {@code SlidingPuzzleSolver}.
	 */
	protected SlidingPuzzleSolver solver;

	/**
	 * The content pane of this {SlidingPuzzleFrame}.
	 */
	protected JPanel contentPane;

	/**
	 * A {@code JTextField} to show the player ID.
	 */
	protected JTextField playerIDTextField;

	/**
	 * A {@code TimerTextField}.
	 */
	protected TimerTextField timerTextField;

	/**
	 * A {@code JTextField} to show the number of moves.
	 */
	protected JTextField movesTextField;

	/**
	 * A {@code JTable} to show the game results.
	 */
	protected JTable resultTable;

	/**
	 * Creates a {@code SlidingPuzzleFrame}.
	 * 
	 * @param playerID
	 *            the ID of the player.
	 * @param server
	 *            a {@code GameServerInterf}.
	 * @param solver
	 *            a {@code SlidingPuzzleSolver}.
	 */
	public SlidingPuzzleFrame(String playerID, GameServer server, SlidingPuzzleSolver solver) {
		this.server = server;
		this.solver = solver;
		setTitle("Sliding Puzzle Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.55);
		contentPane.add(splitPane, BorderLayout.CENTER);

		JPanel rightPanel = new JPanel();
		splitPane.setRightComponent(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));

		JPanel listPanel = new JPanel();
		listPanel.setBorder(new TitledBorder(null, "Players", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		rightPanel.add(listPanel, BorderLayout.CENTER);
		listPanel.setLayout(new BorderLayout(0, 0));

		resultTable = new JTable();
		resultTable.setPreferredScrollableViewportSize(new Dimension(150, 60));
		listPanel.add(new JScrollPane(resultTable));

		updateResultTable(server.results());
		new Timer(10000, new ActionListener() { // schedule a task to update the result table every 10 seconds
					@Override
					public void actionPerformed(ActionEvent e) {
						updateResultTable(SlidingPuzzleFrame.this.server.results());
					}
				}).start();

		JPanel topRightPanel = new JPanel();
		rightPanel.add(topRightPanel, BorderLayout.NORTH);
		topRightPanel.setLayout(new GridLayout(3, 1, 0, 0));

		JPanel playerIDPanel = new JPanel();
		playerIDPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Player ID",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		topRightPanel.add(playerIDPanel);
		playerIDPanel.setLayout(new BorderLayout(0, 0));

		playerIDTextField = new JTextField(playerID);
		playerIDTextField.setEditable(false);
		playerIDTextField.setColumns(10);
		playerIDPanel.add(playerIDTextField, BorderLayout.NORTH);

		JPanel timePanel = new JPanel();
		timePanel.setBorder(new TitledBorder(null, "Time", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		topRightPanel.add(timePanel);
		timePanel.setLayout(new BorderLayout(0, 0));

		timerTextField = new TimerTextField(1000);
		timerTextField.setEditable(false);
		timerTextField.setColumns(10);
		timePanel.add(timerTextField);

		JPanel movesPanel = new JPanel();
		movesPanel.setBorder(new TitledBorder(null, "Moves", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		topRightPanel.add(movesPanel);
		movesPanel.setLayout(new BorderLayout(0, 0));

		movesTextField = new JTextField();
		movesTextField.setEditable(false);
		movesTextField.setColumns(10);
		movesPanel.add(movesTextField);

		JPanel puzzlePanel = new SlidingPuzzlePanel(playerID, this);

		puzzlePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Board",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(puzzlePanel);
	}

	/**
	 * Updates the result table using the specified {@code GameResult}s.
	 * 
	 * @param results
	 *            {@code GameResult}s.
	 */
	public int rank = 0;
	protected void updateResultTable(final GameResult[] results) {
		// Problem 3 (10 points)
	AbstractTableModel datamodel = new AbstractTableModel() {
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return results.length;
		}
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 5; //rank, ID, Time, ID, Date	
		}
		@Override				//rows	columns
		public Object getValueAt(int x, int y) {
			// TODO Auto-generated method stub
			while (y == 0) {
				rank++;
				return rank;
			}
			while(y==1) {
				return results[x].playerID;
			} 
			while(y == 2) {
				return results[x].time/1000;
			}
			while (y == 3) {
				return results[x].moves; 
			}
			while (y == 4) {
				return format.format(new java.util.Date());
			}
			return null;
		}
		@Override
		public String getColumnName(int y) {
			// TODO Auto-generated method stub
			if (y==0) {
				return "Rank";
			}
			if (y==1) {
				return "Player ID";
			}
			if (y==2) {
				return "Time";
			}
			if (y==3) {
				return "Moves";
			}
			if (y==4) {
				return "Date";
			}
			else {
			return null;
			}
		}
	};
	resultTable.setModel(datamodel);
	}

}

