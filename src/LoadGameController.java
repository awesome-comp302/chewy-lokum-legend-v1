import javax.swing.JFrame;


public class LoadGameController {
	private JFrame view;
	
	public LoadGameController(JFrame view) {
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
}
