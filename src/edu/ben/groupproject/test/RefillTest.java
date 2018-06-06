package edu.ben.groupproject.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ben.groupproject.Player;

/**
 * Refill Test Cases
 * 
 * @author Jeff
 *
 */
public class RefillTest {
	/**
	 * Tests if location is adjacent to supply station for player 1
	 */
	@Test
	public void testPlayer1Location() {
		Player player = new Player("one", 75, 0, 22);
		boolean x = player.isRefillLocation(player);
		assertEquals(x, true);
	}

	/**
	 * Tests if location is adjacent to supply station for player 2
	 */
	@Test
	public void testPlayer2Location() {
		Player player = new Player("two", 75, 0, 22);
		boolean x = player.isRefillLocation(player);
		assertEquals(x, true);
	}

	/**
	 * Test logic for refilling supplies to player 1
	 */
	@Test
	public void testPlayer1Supplies() {
		int roll = 6;
		Player player = new Player("one", 75, 0, 22);
		player.refill(roll, player);
		int x = player.getSupplies();
		assertEquals(x, 12);
	}

	/**
	 * Test logic for refilling supplies to player 2
	 */
	@Test
	public void testPlayer2Supplies() {
		int roll = 6;
		Player player = new Player("two", 75, 0, 22);
		player.refill(roll, player);
		int x = player.getSupplies();
		assertEquals(x, 12);
	}

}
