

public class LoadGameController {
	private LoadGameWindow view;
	
	public LoadGameController(LoadGameWindow view) {
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
