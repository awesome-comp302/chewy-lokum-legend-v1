import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainGameWindow extends JFrame {
	private JButton startGameButton;
	private JButton loadGameButton;
	private JButton configButton;
	private JButton exitButton;
	private JPanel 	boardHolder;
	private JPanel	buttonHolder;
	private JPanel 	boardPanel;
	private Interact interact;
	
	Board b;
	private MainGameWindowController controller;
	
	public MainGameWindow(Board initBoard){
		//Score
		//Target
		//Moves
		//Level
		//Menu~Exit
		//Game Board
		super("Welcome!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		controller = new MainGameWindowController(this);
		
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(2);
		
		getContentPane().setLayout(new GridBagLayout());
		setSize(1200, 800);
		
		interact = new Interact();
		GridBagConstraints c = new GridBagConstraints();
		
		buttonHolder = new JPanel();
		buttonHolder.setBackground(Color.RED);
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		add(buttonHolder,c);
		
		startGameButton = new JButton("Start");
		c.gridheight = 1;
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		buttonHolder.add(startGameButton);
		startGameButton.addActionListener(interact);
		
		loadGameButton = new JButton("Load");
		c.gridheight = 1;
		c.weightx = 0.5;
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		buttonHolder.add(loadGameButton);
		loadGameButton.addActionListener(interact);
		
		configButton = new JButton("Config");
		c.gridheight = 1;
		c.weightx = 0.5;
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		buttonHolder.add(configButton);
		configButton.addActionListener(interact);
		
		exitButton = new JButton("Exit");
		c.gridheight = 1;
		c.weightx = 0.5;
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		buttonHolder.add(exitButton);
		exitButton.addActionListener(interact);	
		
		boardHolder = new JPanel();
		boardHolder.setBackground(Color.blue);
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		add(boardHolder,c);
		
		boardPanel = new JPanel();
		updateBoard(initBoard);
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.9;
		c.weighty = 0.9;
		boardHolder.add(boardPanel,c);
		boardHolder.setVisible(true);
		
		//pack();
		setVisible(true);
	}
	
	private class Interact implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object srcButton =  e.getSource();
			if (srcButton == startGameButton) {
				controller.startButtonClicked();
			} else if (srcButton == loadGameButton) {
				controller.loadButtonClicked();
			} else if (srcButton == configButton) {
				controller.configButtonClicked();
			} else if (srcButton == exitButton) {
				controller.exitButtonClicked();
			} else if (srcButton.getClass() == CellButton.class){
				System.out.println("Entering cellClicked");
				controller.cellClicked((CellButton) srcButton);
				
			}
		}	
	}
	
	public MainGameWindowController getController(){
		return controller;
	}
	
	public void updateBoard(Board nb){
		b = nb;
		
		boardPanel.removeAll();
		b.getHeight();
		b.getWidth();
		boardPanel.setLayout(new GridLayout(b.getHeight(),b.getWidth()));
		
		for(int i = 0; i < b.getWidth(); i++){
			for(int j = 0; j < b.getHeight(); j++){
				Cell curr = b.cellAt(i, j);
				CellButton cb = new CellButton(curr,i,j);
				boardPanel.add(cb);
				cb.addActionListener(interact);
			}
		}
		
		boardPanel.updateUI();
	}
}