//oldu
public class Board {
	private int width;
	private int height;
	private Cell[][] cells;
	
	/**
	 * 
	 * @param width: Board width
	 * @param height: Board height
	 * @throws IllegalArgumentException :for too large and negative coordinate values
	 */
	public Board(int width, int height) throws IllegalArgumentException{
		
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException("Board width and height should be positive");
		}
		this.width = width;
		this.height = height;
		cells = new Cell[height][width];
	}
	
	public boolean repOk() {
		//inspects whether the constructor did its job
		if (width < 1 || height < 1) {
			return false;
		}
		
		if (cells == null) {
			return false;
		}
		return true;
	}
	
	
	/**
	
	 * @param x: x coordinate
	 * @param y: y coordinate
	 * @throws IllegalArgumentException: for too big or negative indexes
	 * @postcondition: Wanted cell should be returned
	 */
	public Cell cellAt(int x, int y) throws IllegalArgumentException{
		try {
			return cells[y][x];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Invalid position");
		}
	}
	
	/**
	
	 * @param x: x coordinate
	 * @param y: y coordinate
	 * @throws IllegalArgumentException: for too big or negative indexes
	 * @postcondition: Wanted cell should be filled with the given object
	 */
	public void fillCellAt(int x, int y, ChewyObject co) throws IllegalArgumentException{
		try {
			if(cells[y][x] == null ) 
				cells[y][x] = new Cell(co);
			else
				cells[y][x].setCurrentObject(co);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Invalid position");
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
	
	@Override
	public String toString() {
		String result = "\n";
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if(cells[i][j] != null)
				result += String.format("%-5s",cells[i][j].toString());
				
			}
			result += "\n";
			
		}
		return result;
	}
	
	public String status() {
		return  "\nBOARD STATUS \n"+
				"Board width: " +width+ "\n"+
				"Board height: " +height+ "\n"+
				"Cells array's width: " + cells.length+ "\n"+
				"Cells array's height: "  +  cells[0].length;

	}
}
