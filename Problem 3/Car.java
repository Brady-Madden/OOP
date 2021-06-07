

import java.util.LinkedHashSet;

/**
 * Each {@code Car} instance maintains information about a {@code Car}.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class Car {

	/**
	 * The plate number of this {@code Car}.
	 */
	protected String plateNumber;
	private int current = 0;

	/**
	 * Constructs a {@code Car} instance.
	 * 
	 * @param plateNumber
	 *            the plate number of the {@code Car}.
	 */
	public Car(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	/**
	 * Returns a string representation of this {@code Car}.
	 * 
	 * @return a string representation of this {@code Car}.
	 */
	public String toString() {
		return getClass().getSimpleName() + "(" + plateNumber + ")";
	}
	
	//Problem 6
	/*
	 * hash set by nature deletes duplicates
	 */
	@Override
	public boolean equals (Object object) {
	Car car = (Car) object;
	return car.plateNumber == this.plateNumber;
	}
	@Override
	public int hashCode() {
		return this.current ;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the program arguments.
	 */
	public static void main(String[] args) {
		// Problem 6
		LinkedHashSet<Car> s = new LinkedHashSet<Car>();
		s.add(new Car("1"));
		s.add(new Car("2"));
		s.add(new Car("1"));
		System.out.println(s);
	}
	
}

