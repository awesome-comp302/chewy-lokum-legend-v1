import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameController {
	
	public static int x1;
	public static int y1;
	public static int x2;
	public static int y2;
	public static boolean activity = false;
	
	public static int boardSizeX;
	public static int boardSizeY;
	
	public static int passingScore;
	public static int remainingMove;
	public static int levelID;
	
	public static Board board;
	public static Level level;
	public static GamePlay gp;
	public static MainGameWindowController mgwc;
	
	public static final int selectonLength = 2;
	
	public int selectedXs[];
	public int selectedYs[];
	public int lastIndex;
	
	
	public GamePlay game;
	public JFrame currentView;
	
	public GameController() {
		//game = new GamePlay(level);
		selectedXs = new int[selectonLength];
		selectedYs = new int[selectonLength];
		lastIndex = -1;
	}
	
	public Boolean setSwapCoordinates(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		System.out.println("in setSwapCoordinates");
		if(this.gp.swap(x1, y1, x2, y2)){
			gp.updateBoardGC();
			return true;
		} else {
			return false;
		}
	}
}
