package edu.ben.groupproject.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ben.groupproject.MirskyTrail;

public class MirskyTrailTest {

	/**
	 * Test move() method for moving back to the start location
	 * 
	 * @author KING_Savaman
	 */
	@Test
	public void moveToStartTest() {
		MirskyTrail testGame = new MirskyTrail();

		/**
		 * Test player ones movement to p1Start
		 */
		// Test if player funds are currently 75
		assertEquals(75, testGame.getPlayer1().getMoney());
		// Change money
		testGame.getPlayer1().setMoney(5);
		// Make sure it's no longer 75
		assertNotEquals(75, testGame.getPlayer1().getMoney());

		// Move the player to its starting track
		testGame.move(9, 0, testGame.getPlayer1());

		// Funds should now be 75 again
		assertEquals(75, testGame.getPlayer1().getMoney());

		// Test setBuySupplies is true
		assertEquals(true, testGame.getPlayer1().isBuySupplies());

		/**
		 * Test player twos movement to p2start
		 */
		// Test if player funds are currently 75
		assertEquals(75, testGame.getPlayer2().getMoney());
		// Change money
		testGame.getPlayer2().setMoney(5);
		// Make sure it's no longer 75
		assertNotEquals(75, testGame.getPlayer2().getMoney());

		// Move the player to its starting track
		testGame.move(11, 0, testGame.getPlayer2());

		// Funds should now be 75 again
		assertEquals(75, testGame.getPlayer2().getMoney());

		// Test setBuySupplies is true
		assertEquals(true, testGame.getPlayer2().isBuySupplies());

	}

	/**
	 * Testing move() method for moving to a track they own.
	 * 
	 * @author KING_Savaman
	 */
	@Test
	public void moveToOwnTrackTest() {
		MirskyTrail testGame = new MirskyTrail();

		/**
		 * Test player 1
		 */
		// Add tiles to Player's list of owned tracks
		testGame.getPlayer1().addToPath(testGame.getTile(9, 1));
		testGame.getPlayer1().addToPath(testGame.getTile(9, 2));
		testGame.getPlayer1().addToPath(testGame.getTile(9, 3));

		// Move players to one of his own tracks
		testGame.move(9, 1, testGame.getPlayer1());
		// Test new player location
		assertEquals(9, testGame.getPlayer1().getRow());
		assertEquals(1, testGame.getPlayer1().getCol());

		// Move players to another one of his own tracks
		testGame.move(9, 3, testGame.getPlayer1());
		// Test new player location
		assertEquals(9, testGame.getPlayer1().getRow());
		assertEquals(3, testGame.getPlayer1().getCol());

		// Move players to another one of his own tracks
		testGame.move(9, 2, testGame.getPlayer1());
		// Test new player location
		assertEquals(9, testGame.getPlayer1().getRow());
		assertEquals(2, testGame.getPlayer1().getCol());

		/**
		 * Test player 2
		 */
		// Add tiles to Player's list of owned tracks
		testGame.getPlayer2().addToPath(testGame.getTile(11, 1));
		testGame.getPlayer2().addToPath(testGame.getTile(11, 2));
		testGame.getPlayer2().addToPath(testGame.getTile(11, 3));

		// Move players to one of his own tracks
		testGame.move(11, 1, testGame.getPlayer2());
		// Test new player location
		assertEquals(11, testGame.getPlayer2().getRow());
		assertEquals(1, testGame.getPlayer2().getCol());

		// Move players to another one of his own tracks
		testGame.move(11, 3, testGame.getPlayer2());
		// Test new player location
		assertEquals(11, testGame.getPlayer2().getRow());
		assertEquals(3, testGame.getPlayer2().getCol());

		// Move players to another one of his own tracks
		testGame.move(11, 2, testGame.getPlayer2());
		// Test new player location
		assertEquals(11, testGame.getPlayer2().getRow());
		assertEquals(2, testGame.getPlayer2().getCol());

	}

	/**
	 * Testing move() method for moving to a new track.
	 * 
	 * @author KING_Savaman
	 */
	@Test
	public void moveToNewTrackTest() {
		MirskyTrail testGame = new MirskyTrail();

		/**
		 * Test player 1
		 */

		// Move players to one of his own tracks
		testGame.move(9, 1, testGame.getPlayer1());
		// Test new player location
		assertEquals(9, testGame.getPlayer1().getRow());
		assertEquals(1, testGame.getPlayer1().getCol());

		// Move players to another one of his own tracks
		testGame.move(9, 3, testGame.getPlayer1());
		// Test new player location
		assertEquals(9, testGame.getPlayer1().getRow());
		assertEquals(3, testGame.getPlayer1().getCol());

		// Move players to another one of his own tracks
		testGame.move(9, 2, testGame.getPlayer1());
		// Test new player location
		assertEquals(9, testGame.getPlayer1().getRow());
		assertEquals(2, testGame.getPlayer1().getCol());

		/**
		 * Test player 2
		 */
		// Move players to one of his own tracks
		testGame.move(11, 1, testGame.getPlayer2());
		// Test new player location
		assertEquals(11, testGame.getPlayer2().getRow());
		assertEquals(1, testGame.getPlayer2().getCol());

		// Move players to another one of his own tracks
		testGame.move(11, 3, testGame.getPlayer2());
		// Test new player location
		assertEquals(11, testGame.getPlayer2().getRow());
		assertEquals(3, testGame.getPlayer2().getCol());

		// Move players to another one of his own tracks
		testGame.move(11, 2, testGame.getPlayer2());
		// Test new player location
		assertEquals(11, testGame.getPlayer2().getRow());
		assertEquals(2, testGame.getPlayer2().getCol());

	}

	/**
	 * Test the getCurrentPlayer() and getNextPlayer() methods
	 * 
	 * @author KING_Savaman
	 */
	@Test
	public void getCurrentNewPlayerTest() {
		MirskyTrail testGame = new MirskyTrail();

		// Should be null because the game has not decided for who should go
		// first
		assertEquals(null, testGame.getCurrentPlayer());

		// Set player one's turn
		testGame.getPlayer1().setTurn(true);

		// Expects that current player who is allowed to move is player 1
		assertEquals(testGame.getPlayer1(), testGame.getCurrentPlayer());
		// If player one is current player than it expects player 2 to be the
		// next player
		assertEquals(testGame.getPlayer2(), testGame.nextPlayer());

		// Set player two's turn to true and player one's turn to false
		testGame.getPlayer1().setTurn(false);
		testGame.getPlayer2().setTurn(true);

		// Tests vice versa scenario
		assertEquals(testGame.getPlayer2(), testGame.getCurrentPlayer());

		assertEquals(testGame.getPlayer1(), testGame.nextPlayer());

	}

}
