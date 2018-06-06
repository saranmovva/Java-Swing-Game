package edu.ben.groupproject;

import java.util.ArrayList;

/**
 * Movement Class Created by: @Saraan JavaDocs Created by: @KING_Savaman
 * 
 * Methods Created/Contributed by @KING_Savaman: createPath, printMovementCost,
 * findShortestPath, isValidPath, getMovementPath
 * 
 * Methods Created/Contributed by @Saraan: findShortestPath, isValidPath,
 * setBoard, addToPath
 * 
 * @author KING_Savaman
 * @author Saraan
 * @version 1.5
 * @since 1.0
 */
public class Movement implements MovementInterface{
	private Board board;
	private ArrayList<Tile> path;

	/**
	 * Initializes movement class with a board of tiles.
	 * 
	 * @param board
	 *            Should be a grid board of tiles.
	 */
	public Movement(Board board) {
		this.board = board;
		path = new ArrayList<Tile>();
	}

	/**
	 * Sets the board in which the movement will take place. Should be a board
	 * which utilize a grid of tiles.
	 * 
	 * @param board
	 *            Should be a grid board of tiles.
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Utilized mostly for testing.
	 * 
	 * @param tile
	 *            Adds the tile to the path of the class.
	 */
	public void addToPath(Tile tile) {
		path.add(tile);
	}

	@Override
	/**
	 * Creates the path and cost of path based on the ArrayList<Tile> sent to
	 * it.
	 * 
	 * @author KING_Savaman
	 * @param path
	 *            An Array list of tiles which hold the following variables:
	 *            crew, supplies, and a x and y axis.
	 * @param player
	 *            This is the player who we are creating the the path for.
	 *            Should be of type Purchase.
	 */
	public void createPath(ArrayList<Tile> path, Player player) {
		int supplies = 0;

		for (Tile currentTile : path) {
			if (!player.isInPath(currentTile.getxPos(), currentTile.getyPos())) {
				player.addToPath(board.getTile(currentTile.getxPos(), currentTile.getyPos()));
				board.getTile(currentTile.getxPos(), currentTile.getyPos()).setTrack(true);
				supplies++;
			}
		}

		player.setSupplies(player.getSupplies() - supplies);
	}

	/**
	 * Prints the cost of the Movement classes Array List.
	 * 
	 * @param player
	 *            The player that we are printing the movement costs for.
	 * @return String that contains the cost of it takes for the movement to
	 *         occur.
	 */
	public String printMovementCost(Purchase player) {
		String requirements = "";
		int crew = 0;
		int numOfTiles = 0;
		int supplies = 0;
		boolean isAheadOfCurrent;
		for (Tile currentTile : path) {
			if (player.isInPath(currentTile.getxPos(), currentTile.getyPos())) {
				isAheadOfCurrent = player.isAheadOfCurrent(path.get(0).getxPos(), path.get(0).getyPos());
				if (isAheadOfCurrent) {
					crew += board.getCrewCostOfTile(currentTile.getType());
				}
			} else {
				crew += board.getCrewCostOfTile(currentTile.getType());
				supplies++;
			}
			numOfTiles++;
		}

		requirements += crew + " Crew member(s)\n" + supplies + " Supply Unit(s)\n" + numOfTiles + " Moves";

		return requirements;
	}

	@Override
	/**
	 * Sa Vaun Hamilton created the first if-statement within the method that
	 * implements traversing of the players track. Saraan creates the path
	 * (else-statement) that appears on the board when the player attempts to
	 * extend from the head of the track.
	 * 
	 * @param x
	 *            This is the x-axis of the tile clicked on by the user.
	 * @param y
	 *            This is the y-axis of the tile clicked on by the user.
	 * @param p
	 *            This is the player who clicked on the tile. Should be of type
	 *            Purchase.
	 * @return Array List of tiles that should be used for the train path. The
	 *         last tile in the Array List holds the tile clicked. It also sets
	 *         this returned Array List as new Array List for the movement.
	 *         instance.
	 * @author KING_Savaman
	 * @author Saraan
	 */
	public ArrayList<Tile> findShortestPath(int x, int y, Player p) {
		ArrayList<Tile> shortest = new ArrayList<Tile>();
		boolean isPlayerTrack = p.isInPath(x, y);
		boolean isAheadOfCurrent = p.isAheadOfCurrent(x, y);
		int playerx = p.getRow();
		int playery = p.getCol();

		// This if statement occurs whenever a player is
		// traversing the track @KING_Savaman
		if (isPlayerTrack) {
			ArrayList<Tile> playerTiles = p.getPath();
			if (isAheadOfCurrent) {
				for (Tile tile : playerTiles) {
					if (tile.getxPos() == x && tile.getyPos() == y) {
						shortest.add(tile);
						break;
					} else if (p.isAheadOfCurrent(tile.getxPos(), tile.getyPos())) {
						shortest.add(tile);
					}
				}
			} else {
				boolean beginAddToPath = false;
				for (Tile tile : playerTiles) {
					if (tile.getxPos() == p.getRow() && tile.getyPos() == p.getCol()) {
						break;
					} else if ((tile.getxPos() == x && tile.getyPos() == y) || beginAddToPath) {
						shortest.add(tile);
						beginAddToPath = true;
					}
				}
			}

		} else {
			if (x > playerx && y > playery) {
				while (playery != y) {
					shortest.add(board.getTile(playerx, ++playery));
				}
				while (playerx != x) {
					shortest.add(board.getTile(++playerx, playery));
				}
			} else if (x < playerx && y > playery) {
				while (playery != y) {
					shortest.add(board.getTile(playerx, ++playery));
				}
				while (playerx != x) {
					shortest.add(board.getTile(--playerx, playery));
				}
			} else if (x == playerx && y > playery) {
				while (playery != y) {
					shortest.add(board.getTile(playerx, ++playery));
				}
			} else if (x == playerx && y < playery) {
				while (playery != y) {
					shortest.add(board.getTile(playerx, --playery));
				}
			} else if (x < playerx && y < playery) {
				while (playery != y) {
					shortest.add(board.getTile(playerx, --playery));
				}
				while (playerx != x) {
					shortest.add(board.getTile(--playerx, playery));
				}
			} else if (x > playerx && y < playery) {
				while (playery != y) {
					shortest.add(board.getTile(playerx, --playery));
				}
				while (playerx != x) {
					shortest.add(board.getTile(++playerx, playery));
				}
			} else if (x > playerx && y == playery) {
				while (playerx != x) {
					shortest.add(board.getTile(++playerx, playery));
				}
			} else if (x < playerx && y == playery) {
				while (playerx != x) {
					shortest.add(board.getTile(--playerx, playery));
				}
			}
		}

		path = shortest;
		return shortest;
	}

	@Override
	/**
	 * Determines whether the path chosen to be the shortest is a valid path.
	 * Created and implemented by Saraan. Edited and simplified by Sa Vaun to
	 * handle traversing a track.
	 * 
	 * @author KING_Savaman
	 * @author Saraan
	 * @param roll
	 *            This is the max number of tiles that should be allowed to
	 *            move.
	 * @param x
	 *            This is the x-axis of the Tile clicked.
	 * @param y
	 *            This is the y-axis of the Tile clicked.
	 * @param p
	 *            This is the player that clicked the tile. Should be of type
	 *            Purchase.
	 * @return True or False for whether the path found is a valid path.
	 */
	public boolean isValidPath(int roll, int x, int y, Player p) {
		ArrayList<Tile> temp = findShortestPath(x, y, p);
		boolean isPlayerTrack = p.isInPath(x, y);
		boolean isAheadOfCurrent = p.isAheadOfCurrent(x, y);
		int crewCost = 0;
		int suppliesCost = 0;
		if (p.getRow() == x && p.getCol() == y) {
			return false;
		} else if (temp.size() > roll) {
			return false;
		} else if (isPlayerTrack && !isAheadOfCurrent) {
			return true;
		} else if (!(p.getRow() == p.getHeadPosition().getxPos() && p.getCol() == p.getHeadPosition().getyPos())
				&& !isPlayerTrack) {
			return false;
		} else {
			for (Tile t : temp) {
				if (p.isInPath(t.getxPos(), t.getyPos())) {
					crewCost += board.getCrewCostOfTile(t.getType());
				} else {
					crewCost += board.getCrewCostOfTile(t.getType());
					suppliesCost++;
				}
			}

			if (p.getCrew() < crewCost || p.getSupplies() < suppliesCost) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * Utilized mostly for testing.
	 * 
	 * @return The Array List of tiles of the Movement Class. (The Path)
	 */
	public ArrayList<Tile> getMovementPath() {
		return path;
	}

}
