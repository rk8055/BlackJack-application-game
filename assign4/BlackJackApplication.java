package assign4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BlackJackApplication extends Application{

	//UI elements
	BlackJackModel model;
	BlackJackView view;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("BlackJack!");
        model = new BlackJackModel();
        view = new BlackJackView(model);
        //instantiate the model and view here
           
        primaryStage.setScene(new Scene(view, 400, 400));
        primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/*
	 * The following are helper methods you can call from the EventHandlers (if you want). 
	 * If you use them, in these methods you must update the view and the model appropriately.
	 */
	private void playerBets() {
		
	}

	private void newDeal() {
		
	}

	private void hitMe() {
		
	}


	private void stay() {
		
	}


}
