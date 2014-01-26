

public class Board 
{
	
	/**
	 * 
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
	 * 
	 * @param position
	 * @return
	 */
	public Player getPlayerAtPosition(int[] position) 
	{
		return board[position[0]][position[1]];
	}
	
	private void setPlayerAtPosition(Player player, int[] position) 
	{
		board[position[0]][position[1]] = player;
	}
	
	/**
	 * 
	 * @param player
	 * @param position
	 * @return Move was valid (successful).
	 */
	public boolean move(Player player, int[] position)
	{
		// Check if position is already taken.
		if ( getPlayerAtPosition(position) == null) 
		{
			// GOOD: It is empty.
			setPlayerAtPosition(player, position);
			return true;
		} 
		else 
		{
			// BAD: A player is already in that position.	
			return false;
		}
	}

	/**
	 * 
	 * @return 
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
