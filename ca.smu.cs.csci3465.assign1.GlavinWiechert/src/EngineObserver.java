
public interface EngineObserver {

	/**
	 * 
	 * @param board
	 * @param engine
	 */
	void makeMove(Board board, Engine engine);
	
	/**
	 * Notify the Player that they have lost.
	 * @param board
	 */
	void lostGame(Board board, Engine engine);
	
	/**
	 * Notifiy the Player that they have won.
	 * @param board
	 * @param engine
	 */
	void wonGame(Board board, Engine engine);
	
	/**
	 * Notifiy the Player that it was a draw.
	 * @param board
	 * @param engine
	 */
	void drawGame(Board board, Engine engine);
	
	/**
	 * Notify the Player with a message. This may be an error message.
	 * @param message
	 */
	void message(String message);
	
}
