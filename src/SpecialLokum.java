import java.util.Arrays;


public class SpecialLokum extends Lokum {

	public static final String specialTypes[] = {
		"Color Bomb",
		"Striped", 
		"Wrapped",
	};
	
	public SpecialLokum(String type) throws IllegalArgumentException {
		super(type);
	}
	
	@Override
	protected boolean isValid(String type) {
		return Arrays.binarySearch(specialTypes, type) >= 0;
	}

	@Override
	public boolean isMatched(Matchable l) {
		return l instanceof Lokum;
	}
	
	
}
