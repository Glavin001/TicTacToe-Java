
public class App {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		// Initialize instances.
		Player human1 = new HumanPlayer();
		Player human2 = new HumanPlayer();
		Player computer1 = new AIPlayer();
		//Player computer2 = new AIPlayer();
		Engine engine = new Engine();
		
		// Add Players to communicate to Engine and play the game.
		engine.addPlayer(human1);
		engine.addPlayer(human2);
		//engine.addPlayer(computer1);
		
		// Start the game!
		engine.startGame();
		
	}

}
