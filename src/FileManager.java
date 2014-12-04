import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class FileManager {
	private static FileManager instance;
	
	
	public static FileManager getInstance() {
		if ( instance == null ) {
			instance = new FileManager();
		}
		return instance;
	}
	
	public boolean anyGameSaveExist() {
		try {
			ObjectInputStream input = new ObjectInputStream(
					new FileInputStream("game.ser"));
			input.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public GamePlay getSavedGame() {
		GamePlay currentGamePlay = null;
		try {
			ObjectInputStream input = new ObjectInputStream(
					new FileInputStream("game.ser"));
			currentGamePlay = (GamePlay) input.readObject();
			input.close();

		} catch (FileNotFoundException e) {
			throw new RuntimeException("An error occured in file reading");
		} catch (IOException e) {
			
			throw new RuntimeException("An error occured in file reading");
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("An error occured in file reading");
		}
		return currentGamePlay;
	}
	
	public void saveGame(GamePlay gp) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(
					new FileOutputStream("game.ser"));
			output.writeObject(gp);
			output.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File not found!");
		} catch (IOException e) {
			throw new RuntimeException("An error occured in file reading");
		}
	}
	
	
	
}
