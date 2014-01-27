import java.util.ArrayList;

/**
 * @author Glavin Wiechert
*/

/**
 * This is the main class that handles the logic of the Tic-Tac-Toe game.
 */
/**
 * @author glavin
 *
 */
public class Engine implements MoveObserver {
	
	/*
	 * Variables
	 */
	final private char XCHAR = 'X';
	final private char OCHAR = 'O';
	private ArrayList<Player> players;
	private Board board;
	private Player currentPlayer;
	
	/**
	 * Constructor.
	 * Initializes the ArrayList of players.
	 */
	public Engine() {
		players = new ArrayList<Player>();
	}
	
	/**
	 * Starts game. Initializes a new Board.
	 * @throws Exception InvalidNumberOfPlayers
	 */
	public void startGame() throws Exception 
	{
		Board gameBoard = new Board();
		startGame(gameBoard);
	}

	/**
	 * Starts game, using gameBoard.
	 * @param gameBoard The game Board.
	 * @throws Exception InvalidNumberOfPlayers
	 */
	public void startGame(Board gameBoard) throws Exception 
	{
		// Use gameBoard
		board = gameBoard;
				
		// Verify that we have 2 players.
		if (players.size() == 2) {
			// GOOD: Exactly 2 players.
			System.out.println("Starting Game!");
		} else {
			// BAD
			throw new Exception("Invalid number of players. Not enough or too many players. There are currently "+players.size()+" players.");
		}
		
		// Set current player
		currentPlayer = players.get(0);
		
		// Prep Players
		currentPlayer.startGame(gameBoard, this);
		getNextPlayer(currentPlayer).startGame(gameBoard, this);
		
		// 
		promptPlayerForMove(currentPlayer);
	}
	
	/**
	 * End the current game and disable further moves by players.
	 */
	public void endGame()
	{
		// Setting the `currentPlayer` to `null` will disable any further moves by Players.
		currentPlayer = null;
	}

	
	/**
	 * Get the Engine's Board.
	 * @return The Engine's Board.
	 */
	public Board getBoard() 
	{
		return board;
	}
	
	
	/**
	 * Get the losing player, if there is one.
	 * @return Losing player. `null` is there is currently not a loser.
	 */
	public Player getLoser() 
	{
		Player winner = getWinner();
		// Assuming there are correctly only 2 players, the next player is logically the loser.
		Player loser = getNextPlayer(winner);
		return loser;
	}
	
	
	/**
	 * Get the winning player, if there is one.
	 * @return Winning player. `null` is there is currently not winner.
	 */
	public Player getWinner() 
	{
		// Check horizontal rows
		for (int y=0; y<3; y++)
		{
			// Horizontal row: y
			if ( // Check if every player in this row is the same.
					(
					board.getPlayerAtPosition(new int[]{0, y})
					== board.getPlayerAtPosition(new int[]{1, y})
					)
					&&
					(
					board.getPlayerAtPosition(new int[]{1, y})
					== board.getPlayerAtPosition(new int[]{2, y})
					)
					)
			{
				// The same.
				return board.getPlayerAtPosition(new int[]{0, y});
			} else {
				// Not the same.
			}
		}
		// Check vertical columns
		for (int x=0; x<3; x++)
		{
			// Vertical column: x
			if ( // Check if every player in this column is the same.
					(
					board.getPlayerAtPosition(new int[]{x, 0})
					== board.getPlayerAtPosition(new int[]{x, 1})
					)
					&&
					(
					board.getPlayerAtPosition(new int[]{x, 1})
					== board.getPlayerAtPosition(new int[]{x, 2})
					)
					)
			{
				// The same.
				return board.getPlayerAtPosition(new int[]{x, 0});
			} else {
				// Not the same.
			}
		}	
		// Check diagonal
		if ( // Check if every player in this diagonal is the same.
				/**
				 * X__
				 * _X_
				 * __X
				 */
				(
				board.getPlayerAtPosition(new int[]{0, 0})
				== board.getPlayerAtPosition(new int[]{1, 1})
				)
				&&
				(
				board.getPlayerAtPosition(new int[]{1, 1})
				== board.getPlayerAtPosition(new int[]{2, 2})
				)
				)
		{
			// The same.
			return board.getPlayerAtPosition(new int[]{0, 0});
		} else {
			// Not the same.
		}
		if ( // Check if every player in this diagonal is the same.
				/**
				 * __X
				 * _X_
				 * X__
				 */
				(
				board.getPlayerAtPosition(new int[]{2, 0})
				== board.getPlayerAtPosition(new int[]{1, 1})
				)
				&&
				(
				board.getPlayerAtPosition(new int[]{1, 1})
				== board.getPlayerAtPosition(new int[]{0, 2})
				)
				)
		{
			// The same.
			return board.getPlayerAtPosition(new int[]{2, 0});
		} else {
			// Not the same.
		}
		
		// No winners found.
		return null;
	}
	
	
	/**
	 * Get the letter being used on the Board for the player.
	 * @param player The player to retrieve the letter for.
	 * @return The letter for the player.
	 */
	public char getLetterForPlayer(Player player)
	{
		int idx = getIndexOfPlayer(player);
		if (idx == -1)
		{
			return ' ';
		}
		else if (idx == 0)
		{
			return XCHAR;
		} 
		else
		{
			return OCHAR;
		}
	}
	
	/**
	 * Add a new player to the game.
	 * @param newPlayer
	 * @return True if successfully added newPlayer. Will return false if player already added.
	 */
	public boolean addPlayer(Player newPlayer) 
	{
		if (!players.contains(newPlayer))
		{
			players.add(newPlayer);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Remove player from game.
	 * @param oldPlayer Player to be removed.
	 * @return True if successfully removed oldPlayer. Will return false if player was not in game.
	 */
	public boolean removePlayer(Player oldPlayer) 
	{
		int idx = players.indexOf(oldPlayer);
		if (idx > -1)
		{
			players.remove(idx);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean move(Player player, int[] position) 
	{
		// Check if player is the currentPlayer
		if (player != currentPlayer) 
		{
			if (currentPlayer == null)
			{
				player.message("Game Over!");
			} 
			else 
			{
				player.message("Not your turn!");	
			}
			return false;
		}
		// Make the move
		boolean validMove = board.move(player, position);
		// Check if there are any winners
		Player winner = getWinner();
		Player loser = getLoser();
		if (winner != null) {
			winner.wonGame(board, this);
			loser.lostGame(board, this);
			endGame();
			return validMove;
		}
		// Check if the board is full
		else if (board.isFull())
		{
			// Game over
			System.out.println("Draw");
			currentPlayer.drawGame(board, this);
			getNextPlayer(currentPlayer).drawGame(board, this);
			endGame();
			return validMove;
		}
		// Check if player's move was successful.
		if (validMove) 
		{
			// GOOD: Player's move was valid. Onto the next player.
			switchPlayer();
		}
		// Ask the next current player to make their move
		promptPlayerForMove(currentPlayer);
		// Return
		return validMove;
	}
	
	/**
	 * Switch to the next player.
	 */
	private void switchPlayer()
	{
		// Select next player
		currentPlayer = getNextPlayer(currentPlayer);
	}
		
	/**
	 * Ask the next current player to make their move.
	 * @param player
	 */
	private void promptPlayerForMove(Player player)
	{
		// Ask the next current player to make their move
		final Engine engine = this;
		Runnable myRunnable = new Runnable() {
		    public void run() {
				currentPlayer.makeMove(board.clone(), engine);
		    }
		};
		new Thread(myRunnable).start();//Call it when you need to run the function
	}

	
	private int getIndexOfPlayer(Player player) 
	{
		int idx = players.indexOf(player);
		return idx;
	}
	
	private int getNextPlayerIndex(Player player) 
	{
		int idx = getIndexOfPlayer(player);
		idx++;
		if (idx >= players.size()) 
		{
			idx = 0;
		}
		return idx;
	}
	
	private Player getNextPlayer(Player player) 
	{
		int nextIndex = getNextPlayerIndex(player); 
		if (nextIndex > -1)
		{
			Player next = players.get(nextIndex);
			return next;
		} else {
			return null;
		}
	}
	
	
	
	
}
