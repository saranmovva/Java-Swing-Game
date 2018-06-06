package edu.ben.groupproject;

/**
 * 
 * @author Ryan Yanes
 *
 * This interface was created to define how the implementation of a player their supplies refilling will work in our project.
 * The methods were contributed from Jeff's Refill class. I decided to get rid of the Refill class because refilling is an option
 * he or she can make with the involvement of another player.
 *
 */

public interface PlayerRefillInterface {
	
	static final int [][] location = { { 0, 22 }, { 2, 22 }, { 1, 21 }, { 1, 23 }, { 18, 22 }, { 20, 22 }, { 19, 21 }, { 19, 23 },
			{ 0, 35 }, { 2, 35 }, { 1, 34 }, { 1, 36 }, { 18, 35 }, { 20, 35 }, { 18, 35 }, { 20, 35 }, { 10, 14 },
			{ 10, 16 }, { 9, 15 }, { 11, 15 }, { 9, 28 }, { 11, 28 }, { 10, 27 }, { 10, 29 }, { 6, 30 }, { 8, 30 },
			{ 7, 29 }, { 7, 31 }, { 12, 30 }, { 14, 30 }, { 13, 29 }, { 13, 31 }, { 5, 13 }, { 7, 13 }, { 6, 12 },
			{ 6, 14 }, { 13, 13 }, { 15, 13 }, { 14, 12 }, { 14, 14 }, { 9, 0 }, { 11, 0 }, { 10, 1 } };

	/**
	 * Compares the location of the player with the locations of the supply
	 * stations
	 * 
	 * @param player
	 * @return true or false
	 */
	boolean isRefillLocation(Player player);

	/**
	 * Calculates the total amount of supplies achieved from dice roll
	 * 
	 * @param roll
	 * @param player
	 */
	void refill(int roll, Player player);

	/**
	 * Used for enabling player 1's refill button in the gui after the
	 * initialization stage
	 */
	void enableP1Refill();

	/**
	 * Used for enabling player 2's refill button in the gui after the
	 * initialization stage
	 */
	void enableP2Refill();

}
