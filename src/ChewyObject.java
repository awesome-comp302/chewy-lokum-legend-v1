
public abstract class ChewyObject implements Clonable {
	protected String type;

	public String getType()
	{
		return type;
	}
	
	public abstract ChewyObject clone();
	
}
