package edu.ben.groupproject.test;

import org.junit.Test;

import edu.ben.groupproject.Board;
import edu.ben.groupproject.Purchase;
import edu.ben.groupproject.TileType;
import junit.framework.Assert;

/**
 * Test class for the board class
 * 
 * @author Saran
 *
 */
public class BoardTest {
	Board b = new Board();
	Purchase p1 = new Purchase("Player1");
	Purchase p2 = new Purchase("Player2");

	/**
	 * Test to see if clear board method works
	 */
	@Test
	public void clearBoardTest() {
		b.clearBoard();
		TileType expected;
		TileType actual;
		// Test arbitrary tiles
		actual = b.getTile(3, 2).getType();
		expected = TileType.PLAIN;
		Assert.assertEquals(expected, actual);
		actual = b.getTile(8, 5).getType();
		expected = TileType.WATER;
		Assert.assertEquals(expected, actual);
		actual = b.getTile(6, 13).getType();
		expected = TileType.SUPPLYSTATION;
		Assert.assertEquals(expected, actual);
		actual = b.getTile(13, 27).getType();
		expected = TileType.MOUNTAIN;
		Assert.assertEquals(expected, actual);
		actual = b.getTile(9, 0).getType();
		expected = TileType.P1START;
		Assert.assertEquals(expected, actual);
		actual = b.getTile(11, 0).getType();
		expected = TileType.P2START;
		Assert.assertEquals(expected, actual);
		actual = b.getTile(10, 38).getType();
		expected = TileType.GOAL;
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Test to see if changeTile method works
	 */
	@Test
	public void changeTileTest() {
		b.clearBoard();
		TileType expected;
		TileType actual;

		// Test arbitrary tiles
		b.changeTile(3, 2, TileType.MOUNTAIN);
		actual = b.getTile(3, 2).getType();
		expected = TileType.MOUNTAIN;
		Assert.assertEquals(expected, actual);
		b.changeTile(8, 5, TileType.P1START);
		actual = b.getTile(8, 5).getType();
		expected = TileType.P1START;
		Assert.assertEquals(expected, actual);
		b.changeTile(10, 38, TileType.WATER);
		actual = b.getTile(10, 38).getType();
		expected = TileType.WATER;
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Test to see if addTrack method works
	 */
	@Test
	public void addTrackTest() {
		b.clearBoard();
		boolean expected;
		boolean actual;

		actual = b.addTrack(9, 6, p1);
		expected = true;
		Assert.assertEquals(expected, actual);
		actual = b.addTrack(9, 6, p1);
		expected = false;
		Assert.assertEquals(expected, actual);
		actual = b.addTrack(10, 0, p1);
		expected = false;
		Assert.assertEquals(expected, actual);

		actual = b.addTrack(9, 6, p2);
		expected = false;
		Assert.assertEquals(expected, actual);
		actual = b.addTrack(9, 10, p2);
		expected = true;
		Assert.assertEquals(expected, actual);
		actual = b.addTrack(10, 20, p2);
		expected = true;
		Assert.assertEquals(expected, actual);
	}

}
