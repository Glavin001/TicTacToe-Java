import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HumanPlayer extends Player implements ActionListener {
	
	private JButton[][] buttons;
	Engine currentEngine;
	JLabel message;
	
	public HumanPlayer() 
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
    	for (int k = 0; k < 3; k++) {
	      for (int j = 0; j < 3; j++) {
	         buttons[k][j] = new JButton("");
	         buttons[k][j].addActionListener(this);
	         buttons[k][j].setActionCommand("move");
	         panel.add(buttons[k][j]);
    	   }
    	}
	}
    
    private void disableButtons()
    {
    	for (int k = 0; k < 3; k++) {
  	      for (int j = 0; j < 3; j++) {
  	    	  JButton button = buttons[k][j];
  	    	  button.setEnabled(false);
  	      }
    	}
    }
    
    private void enableButtons()
    {
    	for (int k = 0; k < 3; k++) {
  	      for (int j = 0; j < 3; j++) {
  	    	  JButton button = buttons[k][j];
  	    	  button.setEnabled(true);
  	      }
    	}
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
			System.out.println("Player has no Engine.");
			//displayMessage("Player has no Engine.");
			displayMessage("Please wait for your turn.");
			return;
		}
		String command = event.getActionCommand();
		if (command == "move")
		{
			for (int x=0; x<3; x++)
			{
				for (int y=0; y<3; y++) 
				{
					int[] pos = {x,y};
					JButton btn = buttons[x][y];
					if (btn == event.getSource() )
					{
						boolean validMove = currentEngine.move(this, pos);
						if (validMove)
						{
							System.out.println("Valid move! ["+pos[0]+","+pos[1]+"]");
							//displayMessage("Valid move.");
							//displayMessage("Wait for your turn.");
							disableButtons();
						} else 
						{
							System.out.println("Cannnot make that move! ["+pos[0]+","+pos[1]+"]");
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
			try {
				currentEngine.startGame();
				displayBoard(currentEngine.getBoard(), currentEngine);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	
	@Override
	public void makeMove(Board board, Engine engine) {
		enableButtons();
		System.out.println("=================");
		System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Make Move:");
		displayBoard(board, engine);
		displayMessage("Make your move!");
		currentEngine = engine;
	}
	
	@Override
	public void lostGame(Board board, Engine engine) {
		// 
		System.out.println("=================");
		System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Lost Game");
		displayMessage("You lost.");
		displayBoard(board, engine);
	}

	@Override
	public void wonGame(Board board, Engine engine) {
		// 
		System.out.println("=================");
		System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Won Game");
		displayMessage("You won!");
		displayBoard(board, engine);
	}

	@Override
	public void drawGame(Board board, Engine engine) {
		// 
		System.out.println("=================");
		System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Draw Game");
		displayMessage("Game was a Draw");
		displayBoard(board, engine);
	}
	
	@Override
	public void message(String message) {
		System.out.println(message);
	}


	
}
