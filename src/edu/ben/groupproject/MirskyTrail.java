package edu.ben.groupproject;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This is the Controller for the game Mirsky Trail
 * 
 * @author KING_Savaman
 * @version 1.5
 * @since 1.0
 */
public class MirskyTrail {
	public static final int HEIGHT_OF_BOARD = 21;
	public static final int WIDTH_OF_BOARD = 39;

	// Initialize Players
	private static Purchase player1;
	private static Purchase player2;
	private static Board board;
	private static PlayerRefillInterface refill;

	private Movement movement;
	private Die die;

	public MirskyTrail() {
		player1 = new Purchase("Player1");
		player2 = new Purchase("Player2");
		board = new Board();
		movement = new Movement(board);
		die = new Die();
	}

	/**
	 * Creates the sound effects for creating a track.
	 * 
	 * @author Jeff
	 */
	public void trainSoundEffects() {
		// Implementation of sound moved from GUI to controller by KING_Savaman
		try {
			AudioInputStream train = AudioSystem.getAudioInputStream(new File(
					"train.wav"));
			Clip run = AudioSystem.getClip();
			run.open(train);
			run.start();
			while (!run.isRunning())
				Thread.sleep(5);

			while (run.isRunning())
				Thread.sleep(5);

			run.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This determines how the path will be created and the functions necessary
	 * to give the end user the information required for the move.
	 * 
	 * @author KING_Savaman
	 * @param x
	 *            This parameter should be the x-axis of the tile that is
	 *            clicked.
	 * @param y
	 *            This parameter should be the y-axis of the tile that is
	 *            clicked.
	 * @param player
	 *            This parameter is the player that clicked the tile. Should be
	 *            of type Purchase.
	 */
	public void move(int x, int y, Purchase player) {
		boolean isPlayerTrack = player.isInPath(x, y);
		boolean isAheadOfCurrent = player.isAheadOfCurrent(x, y);
		ArrayList<Tile> path = movement.findShortestPath(x, y, player);

		if (((player.getName() == "Player1") && (board.getTile(x, y).getType() == TileType.P1START))
				|| ((player.getName() == "Player2") && (board.getTile(x, y)
						.getType() == TileType.P2START))) {
			player.setMoney(75);
			player.setBuySupplies(true);
			player.setRow(x);
			player.setCol(y);
		} else {
			if (isPlayerTrack) {
				if (isAheadOfCurrent)
					movement.createPath(path, player);
				player.setRow(x);
				player.setCol(y);
			} else {
				movement.createPath(path, player);
			}
		}

	}

	/**
	 * Purchase resources for a player
	 * 
	 * @author Saran
	 * @param player
	 *            The player that wants to purchase supplies
	 * @param purchaseType
	 *            The type of resource they want to purchases
	 * @param quantity
	 *            The quantity for resources they want to purchase
	 * @return boolean value to see if purchase was successful
	 */
	public boolean purchase(int player, String purchaseType, int quantity) {
		if (player == 1) {
			if (purchaseType.equals("Supplies")) {
				return player1.purchaseSupplies(quantity);
			} else if (purchaseType.equals("Crew")) {
				return player1.purchaseCrew(quantity);
			} else if (purchaseType.equals("Guards")) {
				return player1.purchaseGuards(quantity);
			} else {
				return false;
			}
		} else if (player == 2) {
			if (purchaseType.equals("Supplies")) {
				return player2.purchaseSupplies(quantity);
			} else if (purchaseType.equals("Crew")) {
				return player2.purchaseCrew(quantity);
			} else if (purchaseType.equals("Guards")) {
				return player2.purchaseGuards(quantity);
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * Returns the Board
	 * 
	 * @return the board the game is using.
	 */
	public Board gameBoard() {
		return board;
	}

	/**
	 * Returns Player 1
	 * 
	 * @return player one of the game.
	 */
	public Purchase getPlayer1() {
		return player1;
	}

	/**
	 * Returns Player 2
	 * 
	 * @return player two of the game.
	 */
	public Purchase getPlayer2() {
		return player2;
	}

	/**
	 * Returns tile from board class.
	 * 
	 * @author KING_Savaman
	 * @param x
	 *            This should be the x-axis of the tile that the caller is
	 *            getting.
	 * @param y
	 *            This should be the y-axis of the tile that the caller is
	 *            getting.
	 * @return the tile of the Board class with the corresponding indexes
	 */
	public Tile getTile(int x, int y) {
		return board.getTile(x, y);
	}

	/**
	 * Delegates rolling the die to Die class.
	 * 
	 * @author KING_Savaman
	 * @return the roll of the die.
	 */
	public int roll() {
		return die.roll();
	}

	/**
	 * Delegates the print cost of path to movement class.
	 * 
	 * @author KING_Savaman
	 * @return
	 */
	public String printPathCost(Purchase player) {
		return movement.printMovementCost(player);
	}

	/**
	 * Delegates the validate move to the movement class.
	 * 
	 * @author KING_Savaman
	 * @param x
	 *            This parameter should be the x-axis of the tile that was
	 *            clicked.
	 * @param y
	 *            This parameter should be the y-axis of the tile that was
	 *            clicked.
	 * @param player
	 *            This parameter is the player that clicked the tile. Should be
	 *            of type Purchase.
	 * @return True or False respectively for whether the move is a valid move
	 *         or not.
	 */
	public boolean validateMovement(int x, int y, Purchase player) {
		return movement.isValidPath(die.getFaceValue(), x, y, player);
	}

	/**
	 * For accessing the current face value of the die.
	 * 
	 * @return The number last rolled on a 6 faced die.
	 */
	public int getDieFaceVal() {
		return die.getFaceValue();
	}

	/**
	 * This method is used to determine whether or not a player is successful in
	 * stealing supplies from the other player
	 * 
	 * @author Corey McReady
	 * @param roll1
	 *            -player 1's initial roll
	 * @param roll2
	 *            -player 2's initial roll
	 * @param player
	 *            -number to determine which player is stealing
	 * @return -Returns true if the player stealing had the higher count and
	 *         returns false if the count was lower
	 */
	public boolean steal(int player1Multiplier, int player2Multiplier,
			int player) {
		int p1Multiplier = player1Multiplier;
		int p2Multiplier = player2Multiplier;
		int stealRoll = die.roll();
		if (player == 1) {
			if (p1Multiplier > p2Multiplier) {
				int suppliesStolen = (int) Math.round(stealRoll
						+ (stealRoll * 0.05));
				if (suppliesStolen > player2.getSupplies()) {
					suppliesStolen = player2.getSupplies();
				}
				player1.setSupplies(player1.getSupplies() + suppliesStolen);
				if (player2.getSupplies() < suppliesStolen) {
					player2.setSupplies(0);
				} else {
					player2.setSupplies(player2.getSupplies() - suppliesStolen);
				}
				return true;
			} else {
				if (player1.getGuards() == 0) {
					player1.setGuards(0);
				} else {
					player1.setGuards(player1.getGuards() - 1);
				}
				return false;
			}
		} else {
			if (p2Multiplier > p1Multiplier) {
				int suppliesStolen = (int) Math.round(stealRoll
						+ (stealRoll * 0.05));
				if (suppliesStolen > player1.getSupplies()) {
					suppliesStolen = player1.getSupplies();
				}
				player2.setSupplies(player2.getSupplies() + suppliesStolen);
				if (player1.getSupplies() < suppliesStolen) {
					player1.setSupplies(0);
				} else {
					player1.setSupplies(player1.getSupplies() - suppliesStolen);
				}
				return true;

			} else {
				if (player2.getGuards() == 0) {
					player2.setGuards(0);
				} else {
					player2.setGuards(player2.getGuards() - 1);
				}
				return false;
			}
		}

	}

	/**
	 * Used for enabling player 1's refill button in the gui after the
	 * initialization stage
	 */
	public void enableP1Refill() {
		player1.enableP1Refill();
	}

	/**
	 * Used for enabling player 2's refill button in the gui after the
	 * initialization stage
	 */
	public void enableP2Refill() {
		player2.enableP2Refill();
	}

	/**
	 * Compares the location of the player with the locations of the supply
	 * stations inside the refill class
	 * 
	 * @return true or false
	 */
	public boolean isRefillLocation(Purchase player) {
		return player.isRefillLocation(player);
	}

	/**
	 * Calculates the total amount of supplies achieved from dice roll for
	 * player 1
	 */
	public void refillPlayer(Purchase player) {
		int roll = die.roll();
		player.refill(roll, player);
	}

	/**
	 * This generates the random event of good fortune or disaster. It picks a
	 * number between 1 and 12 and sets whatever loss or gain accordingly. If
	 * the player has zero of the category they are getting, then they get
	 * whatever percentage was supposed to be added out of 10 added to them
	 * 
	 * @Author Corey McReady
	 * @param player
	 *            -This number determines whether the event is for player 1 or 2
	 * @return -Returns the event number so the corresponding message can be
	 *         displayed
	 */
	public int randomEvents(int player) {
		Random ran = new Random();
		int event = ran.nextInt(12) + 1;
		if (player == 1) {
			if (event == 1) {
				int gain = (int) Math.round(player1.getGuards()
						+ (player1.getGuards() * 0.1));
				player1.setGuards(gain);
				if (player1.getGuards() == 0) {
					player1.setGuards(1);
				}
				return 1;
			} else if (event == 2) {
				int loss = (int) Math.round(player1.getGuards()
						- (player1.getGuards() * 0.1));
				player1.setGuards(loss);
				return 2;
			} else if (event == 3) {
				int gain = (int) Math.round(player1.getCrew()
						+ (player1.getCrew() * 0.2));
				if (player1.getCrew() == 0) {
					player1.setCrew(2);
				}
				player1.setCrew(gain);
				return 3;
			} else if (event == 4) {
				int loss = (int) Math.round(player1.getCrew()
						- (player1.getCrew() * 0.2));
				player1.setCrew(loss);
				return 4;
			} else if (event == 5) {
				int gain = (int) Math.round(player1.getSupplies()
						+ (player1.getSupplies() * 0.3));
				player1.setSupplies(gain);
				if (player1.getSupplies() == 0) {
					player1.setSupplies(3);
				}
				return 5;
			} else if (event == 6) {
				int loss = (int) Math.round(player1.getSupplies()
						- (player1.getSupplies() * 0.3));
				player1.setSupplies(loss);
				return 6;
			} else if (event == 7) {
				int gain = (int) Math.round(player1.getSupplies()
						+ (player1.getSupplies() * 0.4));
				player1.setSupplies(gain);
				if (player1.getSupplies() == 0) {
					player1.setSupplies(4);
				}
				return 7;
			} else if (event == 8) {
				int loss = (int) Math.round(player1.getCrew()
						- (player1.getCrew() * 0.4));
				player1.setCrew(loss);
				return 8;
			} else if (event == 9) {
				int gain = (int) Math.round(player1.getSupplies()
						+ (player1.getSupplies() * 0.5));
				player1.setSupplies(gain);
				if (player1.getSupplies() == 0) {
					player1.setSupplies(5);
				}
				return 9;
			} else if (event == 10) {
				int loss = (int) Math.round(player1.getSupplies()
						- (player1.getSupplies() * 0.5));
				player1.setSupplies(loss);
				return 10;
			} else if (event == 11) {
				player1.setSupplies(player1.getSupplies() * 2);
				if (player1.getSupplies() == 0) {
					player1.setSupplies(10);
				}
				return 11;
			} else {
				int loss = (int) Math.round(player1.getSupplies()
						- (player1.getSupplies() * 0.75));
				player1.setSupplies(loss);
				return 12;
			}
		} else {
			if (event == 1) {
				int gain = (int) Math.round(player2.getGuards()
						+ (player2.getGuards() * 0.1));
				player2.setGuards(gain);
				if (player2.getSupplies() == 0) {
					player2.setSupplies(1);
				}
				return 1;
			} else if (event == 2) {
				int loss = (int) Math.round(player2.getGuards()
						- (player2.getGuards() * 0.1));
				player2.setGuards(loss);
				return 2;
			} else if (event == 3) {
				int gain = (int) Math.round(player2.getCrew()
						+ (player2.getCrew() * 0.2));
				player2.setCrew(gain);
				if (player2.getSupplies() == 0) {
					player2.setSupplies(2);
				}
				return 3;
			} else if (event == 4) {
				int loss = (int) Math.round(player2.getCrew()
						- (player2.getCrew() * 0.2));
				player2.setCrew(loss);
				return 4;
			} else if (event == 5) {
				int gain = (int) Math.round(player2.getSupplies()
						+ (player2.getSupplies() * 0.3));
				player2.setSupplies(gain);
				if (player2.getSupplies() == 0) {
					player2.setSupplies(3);
				}
				return 5;
			} else if (event == 6) {
				int loss = (int) Math.round(player2.getSupplies()
						- (player2.getSupplies() * 0.3));
				player2.setSupplies(loss);
				return 6;
			} else if (event == 7) {
				int gain = (int) Math.round(player2.getSupplies()
						+ (player2.getSupplies() * 0.4));
				player2.setSupplies(gain);
				if (player2.getSupplies() == 0) {
					player2.setSupplies(4);
				}
				return 7;
			} else if (event == 8) {
				int loss = (int) Math.round(player2.getCrew()
						- (player2.getCrew() * 0.4));
				player2.setCrew(loss);
				return 8;
			} else if (event == 9) {
				int gain = (int) Math.round(player2.getSupplies()
						+ (player2.getSupplies() * 0.5));
				player2.setSupplies(gain);
				if (player2.getSupplies() == 0) {
					player2.setSupplies(5);
				}
				return 9;
			} else if (event == 10) {
				int loss = (int) Math.round(player2.getSupplies()
						- (player2.getSupplies() * 0.5));
				player2.setSupplies(loss);
				return 10;
			} else if (event == 11) {
				player2.setSupplies(player2.getSupplies() * 2);
				if (player2.getSupplies() == 0) {
					player2.setSupplies(10);
				}
				return 11;
			} else {
				int loss = (int) Math.round(player2.getSupplies()
						- (player2.getSupplies() * 0.75));
				player2.setSupplies(loss);
				return 12;
			}
		}
	}

	/**
	 * Checks for which player's turn it is, then returns player 1 if isTurn is
	 * true (regardless if player 2 is also true). It is important to keep track
	 * of when you set a players turn to be true or false.
	 * 
	 * @author KING_Savaman
	 * @return the instance of the player who's turn it is.
	 */
	public Purchase getCurrentPlayer() {
		if (player1.isTurn()) {
			return player1;
		} else if (player2.isTurn()) {
			return player2;
		} else {
			return null;
		}
	}

	/**
	 * Checks the current players turn by calling the getCurrentPlayer method,
	 * then returns the opposite player.
	 * 
	 * @author KING_Savaman
	 * @return the instance of the player who's turn it isn't.
	 */
	public Purchase nextPlayer() {
		if (getCurrentPlayer().getName().equals("Player1")) {
			return player2;
		} else if (getCurrentPlayer().getName().equals("Player2")) {
			return player1;
		} else {
			return null;
		}
	}

	/**
	 * Method used in restarting the game re-initializes all the variables in
	 * the controller
	 * 
	 * @author Corey McReady
	 */
	public void restart() {
		player1 = new Purchase("Player1");
		player2 = new Purchase("Player2");
		board = new Board();
		movement = new Movement(board);
		refill = new Player("Refill", 0, 0, 0);

	}
}
