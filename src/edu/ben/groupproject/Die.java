package edu.ben.groupproject;

/**
 * Implementation of a Die All methods created by: @KING_Savaman
 * 
 * @author KING_Savaman
 * @version 1.0
 * @since 1.0
 */
public class Die {

	private final int SIDES = 6;
	private int faceValue;

	/**
	 * Creates and instance of Die with the initial face value of 1.
	 */
	public Die() {
		setFaceValue(1);
	}

	/**
	 * Returns the face value of the die based off the last roll.
	 * 
	 * @return The current face value of the Die.
	 */
	public int getFaceValue() {
		return faceValue;
	}

	/**
	 * Sets the face value of die.
	 * 
	 * @param faceValue
	 *            Set the value of the die, instead of a random integer.
	 */
	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}

	/**
	 * Sets the face value of die with a random number.
	 * 
	 * @return Calculated random integer from 1 to 6 inclusive.
	 */
	public int roll() {
		faceValue = (int) (Math.random() * SIDES) + 1;
		return faceValue;
	}
}
