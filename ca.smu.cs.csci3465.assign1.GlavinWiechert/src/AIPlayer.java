/**
 * @author Glavin Wiechert
 */

/**
 * More sophisticated AI (computer) player.
 */
public class AIPlayer extends RandomPlayer {

    @Override   
    protected int[] generateMovePosition(Board board, Engine engine)
    {
        boolean validMove = false;
        int[] pos;
        
        do 
        {
            // Generate move position
            pos = randomPosition();
            // Test move
            validMove = board.move(this, pos);
        } // Check if move was valid. 
        while (!validMove);
        // Move generated should be valid.
        return pos;
    }
    
}
