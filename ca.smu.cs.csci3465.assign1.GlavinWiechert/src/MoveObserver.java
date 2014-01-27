/**
 * @author Glavin Wiechert
*/

/**
 * Interface for passing messages from Player to Engine.
 */

public interface MoveObserver {
	
	/**
	 * Player makes a move.
	 * @param player Player who is making the move.
	 * @param position Position of the move.
	 * @return Move was valid (successful).
	 */
	boolean move(Player player, int[] position);
	
}
