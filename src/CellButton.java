import javax.swing.JButton;

public class CellButton extends JButton{
		public int coordX;
		public int coordY;
		public CellButton(Cell c, int i, int j){
			super(c.getCurrentObject().type.substring(0, 2));
			coordX = i;
			coordY = j;
		}
	}