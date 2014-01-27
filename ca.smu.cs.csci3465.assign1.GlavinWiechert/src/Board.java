/**
 * @author Glavin Wiechert
*/

/**
 * Class for handling the data model of the Tic-Tac-Toe board.
 */

public class Board 
{
    
    /**
     * Board data.
     */
    private Player[][] board;
    
    /**
     * Constructor.
     */
    public Board () 
    {
        board = new Player[3][3];
    }
    
    /**
     * Get the player at the given position.
     * @param position The position to inspect for a player.
     * @return Player at the position specified.
     */
    public Player getPlayerAtPosition(int[] position) 
    {
        if (
                position[0] < board.length 
                && position[1] < board[position[0]].length
                ) 
        {
            return board[position[0]][position[1]];
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Set the Player to mark at the position given.
     * @param player Player to mark the position specified.
     * @param position The position specified to be marked.
     * @return 
     */
    private boolean setPlayerAtPosition(Player player, int[] position) 
    {
        if (
                position[0] < board.length 
                && position[1] < board[position[0]].length
                ) 
        {
            board[position[0]][position[1]] = player;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Player attempts to make a move.
     * @param player Player making the move.
     * @param position Position of the move.
     * @return Move was valid (successful).
     */
    public boolean move(Player player, int[] position)
    {
        // Check if position is already taken.
        if ( getPlayerAtPosition(position) == null) 
        {
            // GOOD: It is empty.
            return setPlayerAtPosition(player, position);
        } 
        else 
        {
            // BAD: A player is already in that position.   
            return false;
        }
    }

    /**
     * Create a data clone of the board. 
     * This is useful for the Engine sharing Boards to other Players without giving them a reference to the original Board, 
     * which is a vulnerability. 
     * @return A new instance of Board, with a copy of all of this board's data.
     */
    public Board clone() 
    {
        Board clone = new Board();  
        for (int x=0; x<3; x++)
        {
            for (int y=0; y<3; y++)
            {
                int[] pos = { x, y };
                Player curr = getPlayerAtPosition(pos);
                clone.move(curr, pos);
            }
        }
        
        return clone;
    }
    
    /**
     * Check if the board is full: all positions marked by a Player.
     * @return If the board is full. Therefore, game over.
     */
    public boolean isFull()
    {
        boolean full = true;
        for (int x=0; x<3; x++)
        {
            for (int y=0; y<3; y++)
            {
                int[] pos = { x, y };
                Player curr = getPlayerAtPosition(pos);
                if(curr==null)
                {
                    // Empty spot
                    full=false;
                    break;
                }
            }
            if (!full)
            {
                break;
            }
        }
        return full;
    }
}
