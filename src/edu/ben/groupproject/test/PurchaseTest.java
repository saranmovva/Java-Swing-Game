package edu.ben.groupproject.test;

import org.junit.Test;

import edu.ben.groupproject.Purchase;
import junit.framework.Assert;

/**
 * Test class for Purchase class
 * 
 * @author Saran
 *
 */
public class PurchaseTest {
	/**
	 * Test for purchase supplies method
	 */
	@Test
	public void purchaseSuppliesTest() {
		Purchase test = new Purchase("test");
		boolean expected = false;
		boolean actual = test.purchaseSupplies(100);
		// Test when you dont have enough money to purchase supplies
		Assert.assertEquals(expected, actual);

		expected = true;
		actual = test.purchaseSupplies(10);
		// Test when you have enough money to purchase supplies
		Assert.assertEquals(expected, actual);

		expected = false;
		actual = test.purchaseSupplies(-5);
		// Test when you put a negative number
		Assert.assertEquals(expected, actual);

		expected = false;
		actual = test.purchaseSupplies(0);
		// Test when you put a zero quantity
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Test for purchase Crew method
	 */
	@Test
	public void purchaseCrewTest() {
		Purchase test = new Purchase("test");
		boolean expected = false;
		boolean actual = test.purchaseCrew(100);
		// Test when you dont have enough money to purchase supplies
		Assert.assertEquals(expected, actual);

		expected = true;
		actual = test.purchaseCrew(10);
		// Test when you have enough money to purchase supplies
		Assert.assertEquals(expected, actual);

		expected = false;
		actual = test.purchaseCrew(-5);
		// Test when you put a negative number
		Assert.assertEquals(expected, actual);

		expected = false;
		actual = test.purchaseCrew(0);
		// Test when you put a zero quantity
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Test for purchase guards method
	 */
	@Test
	public void purchaseGuardsTest() {
		Purchase test = new Purchase("test");
		boolean expected = false;
		boolean actual = test.purchaseGuards(100);
		// Test when you dont have enough money to purchase supplies
		Assert.assertEquals(expected, actual);

		expected = true;
		actual = test.purchaseGuards(10);
		// Test when you have enough money to purchase supplies
		Assert.assertEquals(expected, actual);

		expected = false;
		actual = test.purchaseGuards(-5);
		// Test when you put a negative number
		Assert.assertEquals(expected, actual);

		expected = false;
		actual = test.purchaseGuards(0);
		// Test when you put a zero quantity
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Test for purchase history method
	 */
	@Test
	public void getPurchaseHistoryTest() {
		Purchase test = new Purchase("test");
		String expected = "";
		String actual = test.getPurchaseHistory(0);
		// Test when there is no purchases made
		Assert.assertEquals(expected, actual);

		test.purchaseSupplies(10);
		expected = "test has purchased 10 supplies for $10";
		actual = test.getPurchaseHistory(0);
		// Test when there is one purchase made
		Assert.assertEquals(expected, actual);

		test.purchaseSupplies(25);
		test.purchaseCrew(4);
		test.purchaseGuards(2);
		test.purchaseSupplies(7);
		expected = "test has purchased 2 security guards for $4";
		actual = test.getPurchaseHistory(3);
		// Test when there is a couple purchases made
		Assert.assertEquals(expected, actual);

	}

}
