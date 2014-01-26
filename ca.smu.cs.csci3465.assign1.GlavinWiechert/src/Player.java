
public abstract class Player implements EngineObserver {

	private int randomPositionPart(int min, int max)
	{
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	private int[] randomPosition()
	{
		int[] pos = { 
				randomPositionPart(0,2),
				randomPositionPart(0,2)
		};
		return pos;
	}
	
	private void printBoard(Board board) 
	{
		System.out.println("=================");
		System.out.println("Current Board:");
		for (int x=0; x<3; x++)
		{
			for (int y=0; y<3; y++) 
			{
				int[] pos = {x,y};
				Player curr = board.getPlayerAtPosition(pos);
				if (curr == null) 
				{
					System.out.print(" ");					
				} else if ( curr == this) {
					System.out.print("x");
				} else {
					System.out.print("o");		
				}
			}
			System.out.println("");
		}
	}
	
	@Override
	public void makeMove(Board board, Engine engine) {
		
		printBoard(board);
		
		System.out.println("Make Move");
		
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
		// TODO Auto-generated method stub
		System.out.println("Lost game.");
	}

	@Override
	public void wonGame(Board board, Engine engine) {
		// TODO Auto-generated method stub
		System.out.println("Won game!");
	}

	@Override
	public void message(String message) {
		System.out.println(message);
	}
	
	
}
