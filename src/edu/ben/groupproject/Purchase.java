package edu.ben.groupproject;

import java.util.ArrayList;

/**
 * Purchase class that allows a player to buy resources
 * 
 * @author Saran
 *
 */
public class Purchase extends Player implements PlayerPurchaseInterface {
	// Global constants
	public static final int SUPPLIES_COST = 1;
	public static final int CREW_COST = 4;
	public static final int SECURITYGUARDS_COST = 2;

	// Class variables
	ArrayList<String> purchaseHistory;

	/**
	 * Default constructor
	 * 
	 * @param a
	 *            takes in a player type
	 */
	public Purchase(String name) {
		super(name, 75, 0, 0);
		if (name.equals("Player1")) {
			super.setRow(9);
		} else {
			super.setRow(11);
		}
		purchaseHistory = new ArrayList<String>();
	}
	
	@Override
	/**
	 * Allows player to purchase supplies assuming they have money to do so
	 * 
	 * @param quantity
	 *            of supplies one wishes to purchase
	 * @return returns true if player has enough money to purchase and false if
	 *         one does not
	 */
	public boolean purchaseSupplies(int quantity) {
		if ((super.getMoney() < (quantity * SUPPLIES_COST)) || quantity < 1) {
			return false;
		} else {
			super.setMoney(super.getMoney() - (quantity * SUPPLIES_COST));
			super.setSupplies(super.getSupplies() + quantity);
			purchaseHistory.add(
					super.getName() + " has purchased " + quantity + " supplies for $" + (quantity * SUPPLIES_COST));
			return true;
		}
	}
	
	@Override
	/**
	 * Allows player to purchase crew members assuming they have money to do so
	 * 
	 * @param quantity
	 *            of crew members one wishes to purchase
	 * @return returns true if player has enough money to purchase and false if
	 *         one does not
	 */
	public boolean purchaseCrew(int quantity) {
		if ((super.getMoney() < (quantity * CREW_COST)) || quantity < 1) {
			return false;
		} else {
			super.setMoney(super.getMoney() - (quantity * CREW_COST));
			super.setCrew(super.getCrew() + quantity);
			purchaseHistory.add(
					super.getName() + " has purchased " + quantity + " crew members for $" + (quantity * CREW_COST));
			return true;
		}
	}

	@Override
	/**
	 * Allows player to purchase body guards assuming they have money to do so
	 * 
	 * @param quantity
	 *            of body guards one wishes to purchase
	 * @return returns true if player has enough money to purchase and false if
	 *         one does not
	 */
	public boolean purchaseGuards(int quantity) {
		if ((super.getMoney() < (quantity * SECURITYGUARDS_COST)) || quantity < 1) {
			return false;
		} else {
			super.setMoney(super.getMoney() - (quantity * SECURITYGUARDS_COST));
			super.setGuards(super.getGuards() + quantity);
			purchaseHistory.add(super.getName() + " has purchased " + quantity + " security guards for $"
					+ (quantity * SECURITYGUARDS_COST));
			return true;
		}
	}

	/**
	 * Gets the purchase history of the player
	 * 
	 * @return array list of type string with the purchase history
	 */
	public ArrayList<String> getPurchaseHistory() {
		return purchaseHistory;
	}

	/**
	 * Gets the purchase history of the player at a certain index
	 * 
	 * @param number
	 *            of the purchase
	 * @return returns the purchase as a string
	 */
	public String getPurchaseHistory(int number) {
		if (purchaseHistory.size() >= 1) {
			return purchaseHistory.get(number);
		} else {
			return "";
		}
	}
}
