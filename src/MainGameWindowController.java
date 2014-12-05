import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;


public class MainGameWindowController {
	private MainGameWindow view;
	private Board board;
	public GameController game;
	
	private CellButton click1;
	private CellButton click2;
	
	public MainGameWindowController(MainGameWindow view) {
		this.view = view;
	}

	public void startButtonClicked() {
		view.dispose();
	}
	
	public void loadButtonClicked() {
		view.dispose();
	}
	
	public void configButtonClicked() {
		view.dispose();
	}
	
	public void exitButtonClicked() {
		System.exit(0);
	}
	
	public void cellClicked(CellButton cb){
		if(click1 != null && (cb.coordX != click1.coordX || cb.coordY != click1.coordY)){
			click2 = cb;
			if(click1 == null || click2 == null || game == null){
				System.out.println("ouch");
			}
			sendSwap();
		}
		click1 = cb;
		cb.setBorder(BorderFactory.createLineBorder(Color.red));
	}
	
	public void sendSwap(){
		System.out.println("Entering sendSwap");
		//Board nb = new Board(6, 6);
		if(game.setSwapCoordinates(click1.coordX, click1.coordY, click2.coordX, click2.coordY)){
			if(game == null) System.out.println("game null");
			if(game.gp == null) System.out.println("gamegame null");
			updateBoard(game.gp.board);
		} else {
			
		}
		click1 = null;
		click2 = null;
	}
	
	public void updateBoard(Board b){
		view.updateBoard(b);
	}
}
