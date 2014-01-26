
public class App {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		// Initialize instances.
		Player human = new HumanPlayer();
		Player computer = new AIPlayer();
		Engine engine = new Engine();
		
		// Add Players to communicate to Engine and play the game.
		engine.addPlayer(human);
		engine.addPlayer(computer);
		
		// Start the game!
		engine.startGame();
		
	}

}
