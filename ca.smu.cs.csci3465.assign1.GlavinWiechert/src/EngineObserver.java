/**
 * @author Glavin Wiechert
*/

/**
 * Interface for passing messages from Engine to Player.
 */
public interface EngineObserver {

	/**
	 * Notify the Player that it is their turn to make a move.
	 * @param board The game Board.
	 * @param engine The game Engine.
	 */
	void makeMove(Board board, Engine engine);
	
	/**
	 * Notify the Player that they have lost.
	 * @param board The game Board.
	 */
	void lostGame(Board board, Engine engine);
	
	/**
	 * Notifiy the Player that they have won.
	 * @param board The game Board.
	 * @param engine The game Engine.
	 */
	void wonGame(Board board, Engine engine);
	
	/**
	 * Notifiy the Player that it was a draw.
	 * @param board The game Board.
	 * @param engine The game Engine.
	 */
	void drawGame(Board board, Engine engine);
	
	/**
	 * Notify the Player that a game has started.
	 * @param board The game Board.
	 * @param engine The game Engine.
	 */
	void startGame(Board board, Engine engine);
	
	/**
	 * Notify the Player with a message. This may be an error message.
	 * @param message The message to this player.
	 */
	void message(String message);
	
}
