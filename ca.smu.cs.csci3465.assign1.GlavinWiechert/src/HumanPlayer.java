import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HumanPlayer extends Player implements ActionListener {
	
	private JButton[][] buttons;
	
	Engine currentEngine;
	
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
        //Create and set up the window.
        JFrame frame = new JFrame("Human Player GUI");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        
        /*
        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        contentPane.add(label);
         */
        
        //
        GridLayout boardLayout = new GridLayout(3,3);
        JPanel panel = new JPanel();
        panel.setLayout(boardLayout);
        addButtons(panel);
        contentPane.add(panel);
                
        //Display the window.
        contentPane.validate();
        frame.pack();
        frame.setVisible(true);
    }
    
    
    public void addButtons(JPanel panel) {
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
    
	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("ActionPerformed");
		if (currentEngine == null)
		{
			System.out.println("Player has no Engine.");
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
						} else 
						{
							System.out.println("Invalid move! ["+pos[0]+","+pos[1]+"]");
						}
						displayBoard(currentEngine.getBoard(), currentEngine);
						return;
					}
				}
			}
		}
	}
	
	@Override
	public void makeMove(Board board, Engine engine) {
		System.out.println("=================");
		
		System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Make Move:");
		
		displayBoard(board, engine);
		
		currentEngine = engine;
		
		/*
		int[] pos = randomPosition();
		boolean validMove = engine.move(this, pos);
		if (validMove)
		{
			System.out.println("Valid move! ["+pos[0]+","+pos[1]+"]");
		} else 
		{
			System.out.println("Invalid move! ["+pos[0]+","+pos[1]+"]");
		}
		*/
	}
	
	@Override
	public void lostGame(Board board, Engine engine) {
		// 
		System.out.println("=================");
		System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Lost Game");
		displayBoard(board, engine);
	}

	@Override
	public void wonGame(Board board, Engine engine) {
		// 
		System.out.println("=================");
		System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Won Game");
		displayBoard(board, engine);
	}

	@Override
	public void drawGame(Board board, Engine engine) {
		// 
		System.out.println("=================");
		System.out.println("Player '"+engine.getLetterForPlayer(this)+"' Draw Game");
		displayBoard(board, engine);
	}
	
	@Override
	public void message(String message) {
		System.out.println(message);
	}


	
}
