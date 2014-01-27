/**
 * @author Glavin Wiechert
*/

/**
 * Main application that creates the Tic-Tac-Toe game.
 */
public class App {

    /**
     * Create the game!
     * @param args Command line arguments for the game (not currently being used).
     * @throws Exception Catch all exceptions.
     */
    public static void main(String[] args) throws Exception {
        
        // Initialize instances.
        Player firstPlayer = new GUIPlayer();
        Player secondPlayer = new AIPlayer();
        Engine engine = new Engine();
        
        // Add Players to communicate to Engine and play the game.
        engine.addPlayer(firstPlayer); // First Player, 'X'
        engine.addPlayer(secondPlayer); // Second Player, 'O'
        
        // Start the game!
        engine.startGame();
        
    }

}
