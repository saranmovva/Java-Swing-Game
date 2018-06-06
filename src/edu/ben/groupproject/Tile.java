package edu.ben.groupproject;

/**
 * Tile class that holds information about each tile
 * 
 * @author Saran
 *
 */
public class Tile {
	// Class variables
	private TileType type;
	private int xPos;
	private int yPos;
	private boolean isP1Track = false;
	private boolean isP2Track = false;
	private boolean isTrack = false;

	/**
	 * Default Constructor
	 * 
	 * @param x
	 *            x position of the tile
	 * @param y
	 *            y position of the tile
	 * @param t
	 *            the type of tile
	 */
	public Tile(int x, int y, TileType t) {
		xPos = x;
		yPos = y;
		type = t;
	}

	/**
	 * Gets the type of tile it is
	 * 
	 * @return returns the type of tile
	 */
	public TileType getType() {
		return type;
	}

	/**
	 * Sets the type of tile
	 * 
	 * @param type
	 *            TileType that is going to be set
	 */
	public void setType(TileType type) {
		this.type = type;
	}

	/**
	 * Gets the x Position of the Tile
	 * 
	 * @return integer value of the x position of Tile
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * Gets the y Position of the Tile
	 * 
	 * @return integer value of the x position of Tile
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @deprecated Should use isInPath in the Player class to determine track.
	 *             This method is not versatile for multiple players. Checks to
	 *             see if there is a player1 track on the tile
	 * 
	 * @return true if there is a track and false if there is not
	 */
	public boolean isP1Track() {
		return isP1Track;
	}

	/**
	 * Adds or removes the player1 track on the tiles
	 * 
	 * @param isP1Track
	 *            true to add track and false to remove track
	 */
	public void setP1Track(boolean isP1Track) {
		this.isP1Track = isP1Track;
	}

	/**
	 * Checks to see if there is a player1 track on the tile
	 * 
	 * @deprecated Should use isInPath in the Player class to determine track.
	 *             This method is not versatile for multiple players. Checks to
	 *             see if there is a player2 track on the tile
	 * 
	 * @return true if there is a track and false if there is not
	 */
	public boolean isP2Track() {
		return isP2Track;
	}

	/**
	 * Adds or removes the player1 track on the tile
	 * 
	 * @param isP2Track
	 *            isP1Track true to add track and false to remove track
	 */
	public void setP2Track(boolean isP2Track) {
		this.isP2Track = isP2Track;
	}

	/**
	 * Checks to see if a track is built on current tile
	 * 
	 * @return
	 */
	public boolean isTrack() {
		return isTrack;
	}

	/**
	 * Sets the track on current tile
	 * 
	 * @param isTrack
	 */
	public void setTrack(boolean isTrack) {
		this.isTrack = isTrack;
	}

	/**
	 * Check if a tile is the same
	 * 
	 * @param t
	 *            the tile that is being compared
	 * @return returns true if they are the same tile and false if not
	 */
	public boolean equals(Tile t) {
		if (xPos == t.getxPos() && yPos == t.getyPos() && type == t.getType()) {
			return true;
		} else {
			return false;
		}
	}
}
