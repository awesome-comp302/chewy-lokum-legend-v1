

public class LoadGameController {
	private LoadGameWindow view;
	
	public LoadGameController(LoadGameWindow view) {
		this.view = view;
	}

	public void startButtonClicked() {
		int boardSizeX = 5; 
		int boardSizeY = 5;
		
		int passingScore = 5000;
		int remainingMove = 10;
		int levelID = 1;
		
		Board b = new Board(boardSizeX, boardSizeY);
		
		// upper part will be initialized from a spesific file. *TO BE IMPLEMENTED*
		GameController gc = new GameController();
		
		//gc.board = new Board(boardSizeX, boardSizeY);
		gc.level = new Level(passingScore, remainingMove,b, levelID);
		gc.gp = new GamePlay(gc.level);
		gc.board = gc.gp.board;
		gc.mgwc = new MainGameWindow(gc.gp).getController();
		
		gc.gp.initBoard();
		
		gc.mgwc.game = gc;
		//System.out.println("INIT mgwc.game");
		gc.mgwc.updateBoard(gc.gp);
		
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
}
