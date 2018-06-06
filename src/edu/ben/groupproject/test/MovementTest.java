package edu.ben.groupproject.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.ben.groupproject.Board;
import edu.ben.groupproject.Movement;
import edu.ben.groupproject.Purchase;
import edu.ben.groupproject.Tile;
import junit.framework.Assert;

/**
 * Movement class test cases
 * 
 * @author Saran
 * @author KING_Savaman
 */
public class MovementTest {
	/**
	 * Public variables created by @Saraan
	 */
	Board b = new Board();
	Movement m = new Movement(b);
	Purchase p1 = new Purchase("Player1");
	Purchase p2 = new Purchase("Player2");
	Tile actual;
	Tile expected;

	/**
	 * Test create path and print path method in the movement class
	 * 
	 * @author KING_Savaman
	 */
	@Test
	public void createPrintPathTest() {
		Board b = new Board();
		Movement testPath = new Movement(b);
		Purchase p1 = new Purchase("Player1");
		Purchase p2 = new Purchase("Player2");

		// Add tiles from the board to the path
		testPath.addToPath(b.getTile(9, 1));
		testPath.addToPath(b.getTile(9, 2));
		testPath.addToPath(b.getTile(9, 3));
		testPath.addToPath(b.getTile(9, 4));

		// Since createPath affects the players supplies count, a fixed supply
		// count will be made.
		p1.setSupplies(50);

		// Create path for player 1
		testPath.createPath(testPath.getMovementPath(), p1);

		// Test that supply count is now 46 after 4 new tracks are created
		// It costs 1 supply count per track made.
		assertEquals(46, p1.getSupplies());

		// Test if player 1's path contains the same tiles
		// The first tile in the path is P1START so we are checking every tile
		// after
		assertEquals(b.getTile(9, 1), p1.getPath().get(1));
		assertEquals(b.getTile(9, 2), p1.getPath().get(2));
		assertEquals(b.getTile(9, 3), p1.getPath().get(3));
		assertEquals(b.getTile(9, 4), p1.getPath().get(4));

		// Test printing of the movement
		assertEquals("0 Crew member(s)\n0 Supply Unit(s)\n4 Moves", testPath.printMovementCost(p1));

	}

	/**
	 * Tests the shortest path method for player 1
	 */
	@Test
	public void findShortestPathP1() {
		p1.setCrew(10);
		p1.setSupplies(10);
		ArrayList<Tile> test;
		// Test for one tile movement
		test = m.findShortestPath(9, 1, p1);
		expected = b.getTile(9, 1);
		actual = test.get(0);
		Assert.assertEquals(expected, actual);

		// Test for zero movement
		test = m.findShortestPath(9, 0, p1);
		Assert.assertEquals(0, test.size());

		// Test for more than one movement
		test = m.findShortestPath(7, 5, p1);
		expected = b.getTile(9, 1);
		actual = test.get(0);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(9, 2);
		actual = test.get(1);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(9, 3);
		actual = test.get(2);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(9, 4);
		actual = test.get(3);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(9, 5);
		actual = test.get(4);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(8, 5);
		actual = test.get(5);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(7, 5);
		actual = test.get(6);
		Assert.assertEquals(expected, actual);

		// Test for moving straight down
		test = m.findShortestPath(13, 0, p1);
		expected = b.getTile(10, 0);
		actual = test.get(0);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(11, 0);
		actual = test.get(1);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(12, 0);
		actual = test.get(2);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(13, 0);
		actual = test.get(3);
		Assert.assertEquals(expected, actual);

		// Test for moving straight up
		test = m.findShortestPath(5, 0, p1);
		expected = b.getTile(8, 0);
		actual = test.get(0);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(7, 0);
		actual = test.get(1);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(6, 0);
		actual = test.get(2);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(5, 0);
		actual = test.get(3);
		Assert.assertEquals(expected, actual);

		// Test for moving straight horizontal
		test = m.findShortestPath(9, 4, p1);
		expected = b.getTile(9, 1);
		actual = test.get(0);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(9, 2);
		actual = test.get(1);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(9, 3);
		actual = test.get(2);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(9, 4);
		actual = test.get(3);
		Assert.assertEquals(expected, actual);

	}

	/**
	 * Tests the shortest path method for player 2
	 */
	@Test
	public void findShortestPathP2() {
		p2.setCrew(10);
		p2.setSupplies(10);
		ArrayList<Tile> test;
		// Test for one tile movement
		test = m.findShortestPath(11, 1, p2);
		expected = b.getTile(11, 1);
		actual = test.get(0);
		Assert.assertEquals(expected, actual);

		// Test for zero movement
		test = m.findShortestPath(11, 0, p2);
		Assert.assertEquals(0, test.size());

		// Test for more than one movement
		test = m.findShortestPath(9, 5, p2);
		expected = b.getTile(11, 1);
		actual = test.get(0);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(11, 2);
		actual = test.get(1);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(11, 3);
		actual = test.get(2);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(11, 4);
		actual = test.get(3);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(11, 5);
		actual = test.get(4);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(10, 5);
		actual = test.get(5);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(9, 5);
		actual = test.get(6);
		Assert.assertEquals(expected, actual);

		// Test for moving straight down
		test = m.findShortestPath(15, 0, p2);
		expected = b.getTile(12, 0);
		actual = test.get(0);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(13, 0);
		actual = test.get(1);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(14, 0);
		actual = test.get(2);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(15, 0);
		actual = test.get(3);
		Assert.assertEquals(expected, actual);

		// Test for moving straight up
		test = m.findShortestPath(7, 0, p2);
		expected = b.getTile(10, 0);
		actual = test.get(0);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(9, 0);
		actual = test.get(1);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(8, 0);
		actual = test.get(2);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(7, 0);
		actual = test.get(3);
		Assert.assertEquals(expected, actual);

		// Test for moving straight horizontal
		test = m.findShortestPath(11, 4, p2);
		expected = b.getTile(11, 1);
		actual = test.get(0);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(11, 2);
		actual = test.get(1);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(11, 3);
		actual = test.get(2);
		Assert.assertEquals(expected, actual);
		expected = b.getTile(11, 4);
		actual = test.get(3);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Tests the valid movement method for player 1
	 */
	@Test
	public void isValidPathP1() {
		boolean expected1;
		boolean actual1;
		p1.setCrew(10);
		p1.setSupplies(10);

		// Test a valid move
		expected1 = true;
		actual1 = m.isValidPath(3, 10, 2, p1);
		Assert.assertEquals(expected1, actual1);

		// Test invalid move
		expected1 = false;
		actual1 = m.isValidPath(4, 3, 28, p1);
		Assert.assertEquals(expected1, actual1);

		// Test valid move but not enough resources
		p1.setCrew(2);
		p1.setSupplies(2);
		expected1 = false;
		actual1 = m.isValidPath(3, 10, 2, p1);
		Assert.assertEquals(expected1, actual1);
	}

	/**
	 * Tests the valid movement method for player 2
	 */
	@Test
	public void isValidPathP2() {
		boolean expected1;
		boolean actual1;
		p2.setCrew(10);
		p2.setSupplies(10);

		// Test a valid move
		expected1 = true;
		actual1 = m.isValidPath(3, 10, 1, p2);
		Assert.assertEquals(expected1, actual1);

		// Test invalid move
		expected1 = false;
		actual1 = m.isValidPath(4, 3, 28, p2);
		Assert.assertEquals(expected1, actual1);

		// Test valid move but not enough resources
		p2.setCrew(2);
		p2.setSupplies(2);
		expected1 = false;
		actual1 = m.isValidPath(3, 10, 2, p2);
		Assert.assertEquals(expected1, actual1);
	}
}
