package edu.ben.groupproject;

/**
 * 
 * @author Ryan Yanes
 *
 *This interface contains methods that were necessary based on project description in order to implement a game like this or any game like this that 
 *has purchasing functionality.
 */

public interface PlayerPurchaseInterface {
	
	/**
	 * Allows player to purchase supplies assuming they have money to do so
	 * 
	 * @param quantity
	 *            of supplies one wishes to purchase
	 * @return returns true if player has enough money to purchase and false if
	 *         one does not
	 */
	boolean purchaseSupplies(int quantity);
	
	/**
	 * Allows player to purchase crew members assuming they have money to do so
	 * 
	 * @param quantity
	 *            of crew members one wishes to purchase
	 * @return returns true if player has enough money to purchase and false if
	 *         one does not
	 */
	boolean purchaseCrew(int quantity);
	
	/**
	 * Allows player to purchase body guards assuming they have money to do so
	 * 
	 * @param quantity
	 *            of body guards one wishes to purchase
	 * @return returns true if player has enough money to purchase and false if
	 *         one does not
	 */
	boolean purchaseGuards(int quantity);

}
