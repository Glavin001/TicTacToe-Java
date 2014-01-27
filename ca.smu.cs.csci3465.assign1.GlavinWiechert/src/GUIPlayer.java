import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUIPlayer extends Player implements ActionListener {
	
	private JButton[][] buttons;
	Engine currentEngine;
	JLabel message;
	
	public GUIPlayer() 
    {
    	createAndShowGUI();
    }
    
	
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI() 
    {
        // Create and set up the window.
        JFrame frame = new JFrame("Human Player GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        
        // Add the message Label.
        message = new JLabel("Wait for your turn.");
        contentPane.add(message);
        
        // Add the Restart button.
        JButton restart = new JButton("Restart");
        restart.addActionListener(this);
        restart.setActionCommand("restart");
        contentPane.add(restart);
        
        // Add TicTacToe button grid.
        GridLayout boardLayout = new GridLayout(3,3);
        JPanel panel = new JPanel();
        panel.setLayout(boardLayout);
        addButtons(panel);
        contentPane.add(panel);
                
        // Display the window.
        frame.pack();
        frame.setSize(250, 200);
        frame.setResizable(true);
        frame.setVisible(true);
    }
    
    
    private void addButtons(JPanel panel) {
    	buttons = new JButton[3][3];
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				buttons[x][y] = new JButton("");
				buttons[x][y].addActionListener(this);
				buttons[x][y].setActionCommand("move");
				panel.add(buttons[x][y]);
    	   }
    	}
	}
    
    private void disableButtons()
    {
    	//System.out.println("Disable Buttons - Start");
    	for (int x = 0; x < 3; x++) {
  	      for (int y = 0; y < 3; y++) {
  	    	  JButton button = buttons[x][y];
  	    	  button.setEnabled(false);
  	      }
    	}
    	//System.out.println("Disable Buttons - End");
    }
    
    private void enableButtons()
    {
    	//System.out.println("Enable Buttons - Start");
    	for (int x = 0; x < 3; x++) {
    		for (int y = 0; y < 3; y++) {
    			JButton button = buttons[x][y];
    			button.setEnabled(true);
  	      }
    	}
    	//System.out.println("Enable Buttons - End");
    }

    private void displayBoard(Board board, Engine engine) 
	{
		//System.out.println("Current Board:");
		for (int x=0; x<3; x++)
		{
			for (int y=0; y<3; y++) 
			{
				int[] pos = {x,y};
				Player curr = board.getPlayerAtPosition(pos);
				//System.out.print(engine.getLetterForPlayer(curr));
				JButton btn = buttons[x][y];
				btn.setText(""+engine.getLetterForPlayer(curr) );
			}
			//System.out.println("");
		}
	}
    
    public void startGame(Board board, Engine engine)
	{
    	currentEngine = engine;
		displayBoard(board, engine);
		disableButtons(); // Wait for your turn.
		displayMessage("Wait for your turn.");
	}

    
    private void displayMessage(String msg) 
    {
    	message.setText(msg);
    }
    
	@Override
	public void actionPerformed(ActionEvent event) {
		//System.out.println("ActionPerformed");
		if (currentEngine == null)
		{
			//System.out.println("Player has no Engine.");
			displayMessage("Player has no Engine.");
			return;
		}
		String command = event.getActionCommand();
		if (command == "move")
		{
			// Find which button was pressed.
			// Iterate over the rows, x.
			for (int x=0; x<3; x++)
			{
				// Iterate over the columns, y.
				for (int y=0; y<3; y++) 
				{
					// Get current button with position.
					int[] pos = {x,y};
					JButton btn = buttons[x][y];
					// Check if this was in fact the button pressed.
					if (btn == event.getSource() )
					{
						// YES: This was the button pressed.
						disableButtons(); // Temporarily disable the buttons from user interaction.
						// Submit Move to Engine.
						boolean validMove = currentEngine.move(this, pos);
						// React to whether or not the move was valid.
						if (validMove)
						{
							// GOOD: Move was valid.
							// Check if game is over.
							if (
									currentEngine.getWinner() == null 
									&& !currentEngine.getBoard().isFull() 
									)
							{
								// Game is not over.
								//System.out.println("Valid move! ["+pos[0]+","+pos[1]+"]");
								displayMessage("Valid move.");
								//displayMessage("Wait for your turn.");
							}
						} else 
						{
							// BAD: Move was invalid.
							//System.out.println("Cannnot make that move! ["+pos[0]+","+pos[1]+"]");
							displayMessage("Cannnot make that move.");
						}
						displayBoard(currentEngine.getBoard(), currentEngine);
						return;
					}
				}
			}
		}
		else if (command == "restart")
		{
			// Attempt to Restart the game.
			// What a sore loser...
			try {
				// Notify the Engine of the request.
				currentEngine.startGame();
				// Display the hopefully cleared board.
				displayBoard(currentEngine.getBoard(), currentEngine);
			} catch (Exception e) {
				// Hopefully no errors occur in the process.
				e.printStackTrace();
			}
		}
			
	}
	
	@Override
	public void makeMove(Board board, Engine engine) {
		//System.out.println("=================");
		//System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Make Move:");
		displayBoard(board, engine);
		displayMessage("Make your move! You are '"+engine.getLetterForPlayer(this)+"'s.");
		currentEngine = engine;
		enableButtons();
	}
	
	@Override
	public void lostGame(Board board, Engine engine) {
		// 
		//System.out.println("=================");
		//System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Lost Game");
		displayMessage("You lost.");
		displayBoard(board, engine);
	}

	@Override
	public void wonGame(Board board, Engine engine) {
		// 
		//System.out.println("=================");
		//System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Won Game");
		displayMessage("You won!");
		displayBoard(board, engine);
	}

	@Override
	public void drawGame(Board board, Engine engine) {
		// 
		//System.out.println("=================");
		//System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Draw Game");
		displayMessage("Game was a Draw");
		displayBoard(board, engine);
	}
	
	@Override
	public void message(String message) {
		//System.out.println(message);
		displayMessage(message);
	}


	
}
