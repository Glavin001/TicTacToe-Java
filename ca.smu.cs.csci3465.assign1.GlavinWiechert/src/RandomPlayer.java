
public class RandomPlayer extends Player {
	
	private int randomPositionPart(int min, int max)
	{
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	protected int[] randomPosition()
	{
		int[] pos = { 
				randomPositionPart(0,2),
				randomPositionPart(0,2)
		};
		return pos;
	}
	
	private void printBoard(Board board, Engine engine) 
	{
		//System.out.println("Current Board:");
		for (int x=0; x<3; x++)
		{
			for (int y=0; y<3; y++) 
			{
				int[] pos = {x,y};
				Player curr = board.getPlayerAtPosition(pos);
				System.out.print(engine.getLetterForPlayer(curr));
			}
			System.out.println("");
		}
	}
	
	public void startGame(Board board, Engine engine)
	{
		printBoard(board, engine);
	}

	@Override
	public void makeMove(Board board, Engine engine) {
		System.out.println("=================");
		
		System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Make Move:");
		
		printBoard(board, engine);

		
		int[] pos = randomPosition();
		boolean validMove = engine.move(this, pos);
		if (validMove)
		{
			System.out.println("Valid move! ["+pos[0]+","+pos[1]+"]");
		} else 
		{
			System.out.println("Invalid move! ["+pos[0]+","+pos[1]+"]");
		}
	}

	@Override
	public void lostGame(Board board, Engine engine) {
		// 
		System.out.println("=================");
		System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Lost Game");
		printBoard(board, engine);
	}

	@Override
	public void wonGame(Board board, Engine engine) {
		// 
		System.out.println("=================");
		System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Won Game");
		printBoard(board, engine);
	}

	@Override
	public void drawGame(Board board, Engine engine) {
		// 
		System.out.println("=================");
		System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Draw Game");
		printBoard(board, engine);
	}
	
	@Override
	public void message(String message) {
		System.out.println(message);
	}

}
