import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameController {
	
	private static int x1;
	private static int y1;
	private static int x2;
	private static int y2;
	private static boolean activity = false;
	
	private static int boardSizeX;
	private static int boardSizeY;
	
	private static int passingScore;
	private static int remainingMove;
	private static int levelID;
	
	private static Board board;
	private static Level level;
	private static GamePlay gp;
	
	private static final int selectonLength = 2;
	
	private int selectedXs[];
	private int selectedYs[];
	private int lastIndex;
	
	
	private GamePlay game;
	private JFrame currentView;
	
	public GameController() {
		game = new GamePlay(level);
		selectedXs = new int[selectonLength];
		selectedYs = new int[selectonLength];
		lastIndex = -1;
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		boardSizeX = 5; 
		boardSizeY = 5;
		
		passingScore = 50;
		remainingMove = 10;
		levelID = 1;
		
		// upper part will be initialized from a spesific file. *TO BE IMPLEMENTED*
		
		board = new Board(boardSizeX, boardSizeY);
		level = new Level(passingScore, remainingMove,board, levelID);
		gp = new GamePlay(level);
		
		
		gp.updateBoardGC();
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

			
		}
		
		//game over notation on GUI *TO BE IMPLEMENTED*

	}
	
	public void setSwapCoordinates(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		
		activity= true;
	}

	
	
}
