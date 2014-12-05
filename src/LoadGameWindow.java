import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class LoadGameWindow extends JFrame {
	private JButton startGameButton;
	private JButton loadGameButton;
	private JButton configButton;
	private JButton exitButton;
	LoadGameController controller;
	
	public LoadGameWindow() {
		super("Welcome!");
		controller = new LoadGameController(this);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(3);
		setVisible(true);
		setLayout(new GridLayout(4, 1));
		setSize(300, 400);
		
		Interact interact = new Interact();
		
		startGameButton = new JButton("Start");
		add(startGameButton);
		startGameButton.addActionListener(interact);
		
		loadGameButton = new JButton("Load");
		add(loadGameButton);
		loadGameButton.addActionListener(interact);
		
		configButton = new JButton("Config");
		add(configButton);
		configButton.addActionListener(interact);
		
		exitButton = new JButton("Exit");
		add(exitButton);
		exitButton.addActionListener(interact);
		
		
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
			}
		}
		
		
	}
}
