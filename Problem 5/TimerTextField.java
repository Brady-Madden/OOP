package homework5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * A {@code TimerTextField} continually shows the elapsed time since its creation.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class TimerTextField extends JTextField {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 5441824147620104162L;

	/**
	 * The start time of this {@code TimerTextField}.
	 */
	protected long startTime = System.currentTimeMillis();

	/**
	 * The timer of this {@code TimerTextField}.
	 */
	protected Timer timer;

	/**
	 * Construct a {@code TimerTextField}.
	 * 
	 * @param interval
	 *            time in milliseconds between consecutive updates of the elapsed time.
	 */
	public TimerTextField(int interval) {
		setText("0 seconds");
		// Problem 4 (10 points)
		ActionListener action = new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				setText((System.currentTimeMillis() - startTime)/1000.0 + " seconds");
			}	};
		Timer time = new Timer(interval, action);
		timer = time;
		timer.start();	
		}
	/**
	 * Stops the timer of this {@code TimerTextField}.
	 */
	public void stop() {
		// Problem 4 (10 points)
		timer.stop();
	}

}

