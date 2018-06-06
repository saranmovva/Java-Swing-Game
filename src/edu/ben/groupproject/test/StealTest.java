package edu.ben.groupproject.test;

/**
 *  @author Ryan Yanes
 *  This JUnit Test Class was used to test to see if the implementation of Stealing worked properly.
 *  
 */
import static org.junit.Assert.*;

import org.junit.Test;

import edu.ben.groupproject.MirskyTrail;
import edu.ben.groupproject.Purchase;

public class StealTest {

	/** This test checks to see if the stealing works for Player 1. */
	@Test
	public void testPlayer1SuccessfulSteal() {

		MirskyTrail game = new MirskyTrail();
		Purchase player1 = game.getPlayer1();
		Purchase player2 = game.getPlayer2();

		player1.purchaseSupplies(15);
		player1.purchaseGuards(20);
		player2.purchaseSupplies(15);
		player2.purchaseGuards(2);
		int p1multplier = game.roll() * player1.getGuards();
		int p2multiplier = game.roll() * player2.getGuards();
		assertTrue(game.steal(p1multplier, p2multiplier, 1));

	}

	/**
	 * This test checks to see if Player1 loses a guard after losing their steal
	 * attempt.
	 */
	@Test
	public void testPlayer1UnsuccessfulSteal() {

		MirskyTrail game = new MirskyTrail();
		Purchase player1 = game.getPlayer1();
		Purchase player2 = game.getPlayer2();

		player1.purchaseSupplies(15);
		player1.purchaseGuards(1);
		player2.purchaseSupplies(15);
		player2.purchaseGuards(10);
		int p1multplier = game.roll() * player1.getGuards();
		int p2multiplier = game.roll() * player2.getGuards();
		assertFalse(game.steal(p1multplier, p2multiplier, 1));
		assertEquals(0, player1.getGuards());

	}

	/**
	 * This test checks to see if Player2 successfully wins at stealing supplies
	 * from Player1.
	 */
	@Test
	public void testPlayer2SuccessfulSteal() {

		MirskyTrail game = new MirskyTrail();
		Purchase player1 = game.getPlayer1();
		Purchase player2 = game.getPlayer2();

		player1.purchaseSupplies(15);
		player1.purchaseGuards(2);
		player2.purchaseSupplies(15);
		player2.purchaseGuards(15);
		int p1multplier = game.roll() * player1.getGuards();
		int p2multiplier = game.roll() * player2.getGuards();
		assertTrue(game.steal(p1multplier, p2multiplier, 2));

	}

	/**
	 * This test checks to see if Player2 loses a guard if their attempt is
	 * unsuccessful.
	 */
	@Test
	public void testPlayer2UnsuccessfulSteal() {

		MirskyTrail game = new MirskyTrail();
		Purchase player1 = game.getPlayer1();
		Purchase player2 = game.getPlayer2();

		player1.purchaseSupplies(15);
		player1.purchaseGuards(30);
		player2.purchaseSupplies(15);
		player2.purchaseGuards(3);
		int p1multplier = game.roll() * player1.getGuards();
		int p2multiplier = game.roll() * player2.getGuards();
		assertFalse(game.steal(p1multplier, p2multiplier, 2));
		assertEquals(2, player2.getGuards());

	}

	/**
	 * This test checks if Player2's guards don't go negative if they try to
	 * steal without Guards and they are unsuccessful.
	 */
	@Test
	public void testPlayer2GuardsDontGoNegative() {

		MirskyTrail game = new MirskyTrail();
		Purchase player1 = game.getPlayer1();
		Purchase player2 = game.getPlayer2();

		player1.purchaseSupplies(15);
		player1.purchaseGuards(30);
		player2.purchaseSupplies(15);
		player2.purchaseGuards(0);
		int p1multplier = game.roll() * player1.getGuards();
		int p2multiplier = game.roll() * player2.getGuards();
		game.steal(p1multplier, p2multiplier, 2);
		assertEquals(0, player2.getGuards());

	}

	/**
	 * This test checks to see if Player1's Guards do not go negative if he/she
	 * tries to steal without Guards and they are unsuccessful.
	 */
	@Test
	public void testPlayer1GuardsDontGoNegative() {

		MirskyTrail game = new MirskyTrail();
		Purchase player1 = game.getPlayer1();
		Purchase player2 = game.getPlayer2();

		player1.purchaseSupplies(15);
		player1.purchaseGuards(0);
		player2.purchaseSupplies(15);
		player2.purchaseGuards(10);
		int p1multplier = game.roll() * player1.getGuards();
		int p2multiplier = game.roll() * player2.getGuards();
		game.steal(p1multplier, p2multiplier, 1);
		assertEquals(0, 0);

	}

}
