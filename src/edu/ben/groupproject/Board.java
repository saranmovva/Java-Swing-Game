package edu.ben.groupproject;

/**
 * Board class that manipulates the game board
 * 
 * @author Saraan
 */

public class Board {

	private Tile[][] board;

	/**
	 * Default Constructor
	 */
	public Board() {
		board = new Tile[MirskyTrail.HEIGHT_OF_BOARD][MirskyTrail.WIDTH_OF_BOARD];
		clearBoard();
	}

	/**
	 * Gets the Board
	 * 
	 * @return returns 2 dim array of Tiles
	 */
	public Tile[][] getBoard() {
		return board;
	}

	/**
	 * Method that clears the board to its original state
	 */
	public void clearBoard() {
		for (int x = 0; x < MirskyTrail.WIDTH_OF_BOARD; x++) {
			for (int y = 0; y < MirskyTrail.HEIGHT_OF_BOARD; y++) {
				// P1START tiles
				if (x == 0 && y == 9) {
					board[y][x] = new Tile(y, x, TileType.P1START);
				}
				// P2START tiles
				else if (x == 0 && y == 11) {
					board[y][x] = new Tile(y, x, TileType.P2START);
				}
				// GOAL tile
				else if (x == 38 && y == 10) {
					board[y][x] = new Tile(y, x, TileType.GOAL);
				}
				// WATER tiles
				else if (((x > 3 && x < 7) && (y > 5 && y < 15))
						|| ((x > 18 && x < 22) && ((y >= 0 && y < 3) || (y > 17 && y < 21)))
						|| (x == 33 && ((y >= 0 && y < 9) || (y > 11 && y < 21)))
						|| (x == 34 && ((y >= 0 && y < 5) || (y > 15 && y < 21))) || (x == 36 && (y > 7 && y < 13))) {
					board[y][x] = new Tile(y, x, TileType.WATER);

				}
				// SUPPLYSTATION tiles
				else if ((x == 0 && y == 10) || (x == 13 && (y == 6 || y == 14)) || (x == 15 && y == 10)
						|| (x == 22 && (y == 1 || y == 19) || (x == 28 && y == 10)) || (x == 30 && (y == 7 || y == 13))
						|| (x == 35 && (y == 1 || y == 19)) || (x == 19 && y == 22)) {
					board[y][x] = new Tile(y, x, TileType.SUPPLYSTATION);
				}
				// FOREST tiles
				else if (((x > 8 && x < 13) && (y == 4 || y == 16)) || ((x > 8 && x < 15) && (y == 5 || y == 15))
						|| (((x > 10 && x < 13) || x == 14) && (y == 6 || y == 14))
						|| ((x > 10 && x < 15) && (y == 7 || y == 13)) || ((x > 10 && x < 19) && (y == 8 || y == 12))
						|| ((x > 12 && x < 19) && (y == 9 || y == 11))
						|| (((x > 12 && x < 15) || (x > 15 && x < 19)) && (y == 10))) {
					board[y][x] = new Tile(y, x, TileType.FOREST);
				}
				// MOUNTAIN tiles
				else if ((x == 28 && (y == 1 || y == 19 || y == 2 || y == 18))
						|| ((x > 26 && x < 29) && ((y > 2 && y < 6) || (y > 14 && y < 18)))
						|| ((x > 25 && x < 30) && ((y > 5 && y < 9) || (y > 11 && y < 15)))
						|| ((x > 25 && x < 31) && (y == 9 || y == 11))
						|| (((x > 25 && x < 28) || (x > 28 && x < 31)) && y == 10)) {
					board[y][x] = new Tile(y, x, TileType.MOUNTAIN);
				} else {
					board[y][x] = new Tile(y, x, TileType.PLAIN);
				}
			}
		}
	}

	/**
	 * Method that return a tile
	 * 
	 * @param x
	 *            the x location of the tile
	 * @param y
	 *            the y location of the tile
	 * @return returns a Tile of the board
	 */
	public Tile getTile(int x, int y) {
		return board[x][y];
	}

	/**
	 * Method that changes a tile to a different type
	 * 
	 * @param x
	 *            x the x location of the tile you want to change
	 * @param y
	 *            y the y location of the tile you want to change
	 * @param t
	 *            the type of tile you want to change it to
	 * @return returns true if the change was successful or false if it was not
	 */
	public boolean changeTile(int x, int y, TileType t) {
		if (board[x][y].getType() == t) {
			return false;
		} else {
			board[x][y].setType(t);
			return true;
		}
	}

	/**
	 * Method that adds a track to the board
	 * 
	 * @deprecated a new implementation for adding a track is now in the Player
	 *             class
	 * @param x
	 *            the x location of the tile you want to add a track to
	 * @param y
	 *            the x location of the tile you want to add a track to
	 * @param p
	 *            the player that wants the track added
	 * @return returns true if adding track was successful and false if it was
	 *         unsuccessful
	 */
	public boolean addTrack(int x, int y, Purchase p) {
		if (p.getName().equals("Player1")) {
			if (board[x][y].getType() != TileType.SUPPLYSTATION && board[x][y].isP2Track() != true
					&& board[x][y].isP1Track() != true) {
				board[x][y].setP1Track(true);
				return true;
			} else {
				return false;
			}
		} else if (p.getName().equals("Player2")) {
			if (board[x][y].getType() != TileType.SUPPLYSTATION && board[x][y].isP1Track() != true
					&& board[x][y].isP2Track() != true) {
				board[x][y].setP2Track(true);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param tile
	 *            Pass in the tile type of your tile and this method will return
	 *            the crew cost.
	 * @author KING_Savaman
	 * @return the integer number of crew members it takes to build a track on
	 *         the tile.
	 */
	public int getCrewCostOfTile(TileType tile) {
		if (tile == TileType.FOREST)
			return 3;
		else if (tile == TileType.PLAIN)
			return 1;
		else if (tile == TileType.MOUNTAIN)
			return 5;
		else if (tile == TileType.WATER)
			return 4;
		else {
			return 0;
		}

	}

}
