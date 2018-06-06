package edu.ben.groupproject;

import java.util.ArrayList;

/**
 * Player Class
 * 
 * @author Jeff
 * @author KING_Savaman
 */

public class Player implements PlayerRefillInterface {
	private String name;
	private int money;
	private int supplies;
	private int crew;
	private int guards;
	private ArrayList<Tile> path = new ArrayList<Tile>();
	private Tile headPosition;
	private int row;
	private int col;
	private boolean turn;
	private boolean buySupplies;
	private boolean isP1Refill = false;
	private boolean isP2Refill = false;
	

	/**
	 * Constructor for initializing player
	 * 
	 * @param name
	 * @param money
	 * @param r
	 * @param c
	 */
	public Player(String name, int money, int r, int c) {
		this.name = name;
		this.money = money;
		if (name == "Player1") {
			headPosition = new Tile(9, 0, TileType.P1START);
		} else {
			headPosition = new Tile(11, 0, TileType.P2START);
		}
		this.path.add(headPosition);
		this.row = r;
		this.col = c;
		buySupplies = true;

	}

	/**
	 * Set money to 75
	 */
	public Player() {
		money = 75;
	}

	/**
	 * Gets row
	 * 
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Gets col
	 * 
	 * @return col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Sets row
	 * 
	 * @param r
	 */
	public void setRow(int r) {
		row = r;
	}

	/**
	 * Sets col
	 * 
	 * @param c
	 */
	public void setCol(int c) {
		col = c;
	}

	/**
	 * Gets name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets money
	 * 
	 * @return money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Sets money
	 * 
	 * @param money
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * Gets supplies
	 * 
	 * @return supplies
	 */
	public int getSupplies() {
		return supplies;
	}

	/**
	 * Sets supplies
	 * 
	 * @param supplies
	 */
	public void setSupplies(int supplies) {
		this.supplies = supplies;
	}

	/**
	 * Gets crew
	 * 
	 * @return crew
	 */
	public int getCrew() {
		return crew;
	}

	/**
	 * Sets crew
	 * 
	 * @param crew
	 */
	public void setCrew(int crew) {
		this.crew = crew;
	}

	/**
	 * Gets guards
	 * 
	 * @return guards
	 */
	public int getGuards() {
		return guards;
	}

	/**
	 * Sets guards
	 * 
	 * @param guards
	 */
	public void setGuards(int guards) {
		this.guards = guards;
	}

	/**
	 * Gets the path
	 * 
	 * @return path
	 */
	public ArrayList<Tile> getPath() {
		return path;
	}

	/**
	 * Sets the path
	 * 
	 * @param path
	 */
	public void setPath(ArrayList<Tile> path) {
		this.path = path;
	}

	/**
	 * Keeps track of the positions (row and col) that the player moves
	 * 
	 * @param r
	 * @param c
	 */
	public void addToPath(Tile position) {
		this.row = position.getxPos();
		this.col = position.getyPos();
		headPosition = position;
		path.add(position);
	}

	/**
	 * Checks to determine if indexes belong to a tile in the current path
	 * 
	 * @author KING_Savaman
	 * @param x
	 * @param y
	 * @return true or false
	 */
	public boolean isInPath(int x, int y) {
		for (Tile currentTile : path) {
			if (currentTile.getxPos() == x && currentTile.getyPos() == y)
				return true;
		}
		return false;
	}

	/**
	 * Determines whether the x and y is ahead of players current x and y
	 * 
	 * @author KING_Savaman
	 * @param x
	 * @param y
	 * @return true or false
	 */
	public boolean isAheadOfCurrent(int x, int y) {
		int i;
		for (i = 0; i < path.size(); i++) {
			if (path.get(i).getxPos() == row && path.get(i).getyPos() == col)
				break;
		}

		for (int j = i + 1; j < path.size(); j++) {
			if (path.get(j).getxPos() == x && path.get(j).getyPos() == y) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns whether or not it is the player's turn
	 * 
	 * @return -Returns true if it is the Player's turn, false if it is not
	 */
	public boolean isTurn() {
		return turn;
	}

	/**
	 * Sets the turn for the player
	 * 
	 * @param turn
	 *            -True if it is the player's turn, false if it isn't
	 */
	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	/**
	 * Returns the head position tile
	 * 
	 * @author KING_Savaman
	 * @return headPosition
	 */
	public Tile getHeadPosition() {
		return headPosition;
	}

	/**
	 * Determines whether the x and y matches that of the head position.
	 * 
	 * @author KING_Savaman
	 * @param x
	 * @param y
	 * @return true or false
	 */
	public boolean isHeadPosition(int x, int y) {
		if (headPosition.getxPos() == x && headPosition.getyPos() == y) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Return buySupplies
	 * 
	 * @return buySupplies
	 */
	public boolean isBuySupplies() {
		return buySupplies;
	}

	/**
	 * Set supplies
	 * 
	 * @param buySupplies
	 */
	public void setBuySupplies(boolean buySupplies) {
		this.buySupplies = buySupplies;
	}

	@Override
	public boolean isRefillLocation(Player player) {
		if (!isP1Refill && player.getName().equals("Player1")) {
			return false;
		} else if (!isP2Refill && player.getName().equals("Player2")) {
			return false;
		}
		int r = player.getRow();
		int c = player.getCol();
		for (int i = 0; i < location.length; i++) {
			if (r == location[i][0] && c == location[i][1]) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void refill(int roll, Player player) {
		int current = player.getSupplies();
		player.setSupplies(current + 2 * roll);
	}

	@Override
	public void enableP1Refill() {
		isP1Refill = true;
		
	}

	@Override
	public void enableP2Refill() {
		isP2Refill = true;
		
	}

}
