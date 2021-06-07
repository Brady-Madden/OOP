package homework5;

import homework5.SlidingPuzzle.Position;

/**
 * A {@code SlidingPuzzleSolver} solves a {@code SlidingPuzzle} problem.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public interface SlidingPuzzleSolver {

	/**
	 * Solves the specified {@code SlidingPuzzle}.
	 * 
	 * @param puzzle
	 *            the {@code SlidingPuzzle} to solve.
	 * @return the {@code Position}s that are slid into the empty position.
	 */
	public Iterable<Position> solve(SlidingPuzzle puzzle);

}

