/**
 * Each {@code Car} instance maintains information about a {@code Car}.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class Car {

	/**
	 * The color red.
	 */
	public static int RED = rgb(255, 0, 0);

	/**
	 * The color green.
	 */
	public static int GREEN = rgb(0, 255, 0);

	/**
	 * The color blue.
	 */
	public static int BLUE = rgb(0, 0, 255);

	/**
	 * The plate number of this {@code Car}.
	 */
	protected String plateNumber;

	/**
	 * The exterior color of this {@code Car}.
	 */
	protected int exteriorColor;

	

	/**
	 * Constructs a {@code Car} instance.
	 * 
	 * @param plateNumber
	 *            the plate number of the {@code Car}.
	 * @param exteriorColor
	 *            the exterior color of the {@code Car}.
	 */
	public Car(String plateNumber, int exteriorColor) {
		this.plateNumber = plateNumber;
		this.exteriorColor = exteriorColor;
	}

	public String toString(){
		String s = ("plate number: " + plateNumber + " exterior color: " + exteriorColor);
		return s;
	} 
	/**
	 * Sets the plate number of this {@code Car}.
	 * 
	 * @param plateNumber
	 *            the new plate number of this {@code Car}.
	 */
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;  
	}

	/**
	 * Returns the plate number of this {@code Car}.
	 * 
	 * @return the plate number of this {@code Car}.
	 */
	public String getPlateNumber() {
		String n = plateNumber;
		return n;
	}
 public boolean equals(Car a) {
	 if (exteriorColor == a.exteriorColor ) {
		 if (plateNumber == a.plateNumber) {
			 return true;
		 }
	 }
	 return false;
 }
	/**
	 * Returns an {@code int} value that corresponds to the specified color.
	 * 
	 * @param r
	 *            the red component.
	 * @param g
	 *            the green component.
	 * @param b
	 *            the blue component.
	 * @return an {@code int} value that corresponds to the specified color.
	 */
	public static int rgb(int r, int g, int b) {
		return (0xFF << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF) << 0);
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the program arguments.
	 */
	public static void main(String[] args) {
		// Problems 1 and 2
		Car red = new Car("1", Car.RED);
		System.out.println(red);

		// Problem 3
		red.setPlateNumber("2");
		System.out.println(red);

		// Problem 4
		System.out.println(red.getPlateNumber());

		// Problem 5
		System.out.println(red.equals(new Car("1", Car.RED)));
		System.out.println(red.equals(new Car("2", Car.RED)));
		System.out.println(red.equals(new Car("1", Car.GREEN)));
		System.out.println(red.equals("1"));
	}
}
