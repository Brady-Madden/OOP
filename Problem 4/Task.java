/**
 * The {@code Task} interface serves as the super type for all of the classes defining tasks performed by a
 * {@code Simulator}. All classes implementing this {@code Task} interface must implement the {@code run()} method which
 * performs the intended task.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public interface Task {

	/**
	 * Performs the intended task. This method is automatically invoked by a {@code Simulator}.
	 * 
	 * @param simulationTime
	 *            the simulation time at which this method is invoked.
	 */
	public abstract void run(double simulationTime);

}
