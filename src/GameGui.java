import javax.swing.JFrame;
import javax.swing.JLabel;

//import statements
//Check if window closes automatically. Otherwise add suitable code
public class GameGui extends JFrame {

	public static void main(String[] args) throws InterruptedException {
		int boardSizeX = 5; 
		int boardSizeY = 5;
		
		int passingScore = 50;
		int remainingMove = 10;
		int levelID = 1;
		
		Board b = new Board(boardSizeX, boardSizeY);
		
		// upper part will be initialized from a spesific file. *TO BE IMPLEMENTED*
		GameController gc = new GameController();
		//gc.board = new Board(boardSizeX, boardSizeY);
		gc.level = new Level(passingScore, remainingMove,b, levelID);
		gc.gp = new GamePlay(gc.level);
		gc.board = gc.gp.board;
		gc.mgwc = new MainGameWindow(gc.board).getController();
		
		gc.gp.updateBoardGC();
		
		gc.mgwc.game = gc;
		//System.out.println("INIT mgwc.game");
		gc.mgwc.updateBoard(gc.board);
		
		
		
		/*
		System.out.println(board); // give the initial board to gui at here *TO BE IMPLEMENTED*
		Scanner scan = new Scanner(System.in);
		while(true){

			try {
				x1 = scan.nextInt();
				System.out.println("x1: " +x1); 
				y1 = scan.nextInt();
				System.out.println("y1: " +y1); 
				x2 = scan.nextInt();
				System.out.println("x2: " +x2); 
				y2 = scan.nextInt();
				System.out.println("y2: " +y2); 
				activity = true;
			} catch (Exception e) {
				System.out.println("Gerizekalı!!\nSalak!!\nMal!!\nAHAHAHAHHAH\nAHAHAHA"); 
				Thread.sleep(100);
			}
			
			if(activity){ //GUI will set activity to true, if any interaction is done *TO BE IMPLEMENTED*
				
				if(gp.swap(x1,y1,x2,y2)){//GUI will set x1,y1,x2,y2 with the interaction *TO BE IMPLEMENTED*
					gp.updateBoardGC();
					System.out.println(board); // GUI will be updated *TO BE IMPLEMENTED*
					
				}else{
					System.out.println(board);
					System.out.println("move is illegal");
					//notifies the GUI about the swap is illegal, and GUI throws a warning to player
					//*TO BE IMPLEMENTED*
				}
				activity = false;
			}
			
			if(gp.isGameOver()) break;

			
		}*/
		
		//game over notation on GUI *TO BE IMPLEMENTED*

	}

}