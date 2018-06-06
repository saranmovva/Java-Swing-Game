package edu.ben.groupproject;
/**
 * @author Ryan Yanes
 * 
 * This interface contains basic methods that are necessary for any implementation of movement in a game.
 */
import java.util.ArrayList;

public interface MovementInterface {
	
	/**
	 * Creates the path and cost of path based on the ArrayList<Tile> sent to
	 * it.
	 * 
	 * @param path
	 *            An Array list of tiles which hold the following variables:
	 *            crew, supplies, and a x and y axis.
	 * @param player
	 *            This is the player who we are creating the the path for.
	 *            Should be of type Purchase.
	 */
	void createPath(ArrayList<Tile> path, Player player); 
	
	/**
	 * This method finds the shortest passed based of the position of the player the spot the player wants to move to.
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
	 */
	ArrayList<Tile> findShortestPath(int x, int y, Player p);
	
	/**
	 * Determines whether the path chosen to be the shortest is a valid path.
	 *
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
	boolean isValidPath(int roll, int x, int y, Player p);

}
