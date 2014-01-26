import java.util.ArrayList;

public class Engine implements MoveObserver {
	
	/*
	 * Variables
	 */
	private ArrayList<Player> players;
	private Board board;
	private Player currentPlayer;
	
	/*
	 * Methods
	 */
	public Engine() {
		players = new ArrayList<Player>();
	}
	
	public Board getBoard() 
	{
		return board;
	}

	public Player getCurrentPlayer() 
	{
		return null;
	}
	
	public void play(int[] position) 
	{
		
	}
	
	public Player getWinner() 
	{
		return null;
	}
	
	/**
	 * 
	 * @param newPlayer
	 * @return If successfully added newPlayer.
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
	 * 
	 * @param oldPlayer
	 * @return If successfully removed oldPlayer.
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
			player.message("Not your turn!");
			return false;
		}
		// Make the move
		boolean validMove = board.move(player, position);
		// Check if player's move was successful.
		if (validMove) 
		{
			// GOOD: Player's move was valid. Onto the next player.
			// Select next player
			currentPlayer = getNextPlayer();
		}
		// Check if there are any winners
		Player winner = getWinner();
		Player loser = null;
		// Check if the board is full
		if (board.isFull())
		{
			// Game over
			System.out.println("Game Over.");
			return validMove;
		}
		// Ask the next current player to make their move
		promptPlayerForMove(currentPlayer);
		// Return
		return validMove;
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
	
	/**
	 * Starts game, using gameBoard.
	 * @param gameBoard
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
		promptPlayerForMove(currentPlayer);
		
	}
	
	private int getIndexOfPlayer(Player player) 
	{
		int idx = players.indexOf(player);
		return idx;
	}
	
	private int getNextPlayerIndex() 
	{
		int idx = getIndexOfPlayer(currentPlayer);
		idx++;
		if (idx >= players.size()) 
		{
			idx = 0;
		}
		return idx;
	}
	
	private Player getNextPlayer() 
	{
		int nextIndex = getNextPlayerIndex(); 
		if (nextIndex > -1)
		{
			Player next = players.get(nextIndex);
			return next;
		} else {
			return null;
		}
	}
	
	
	
	
}
