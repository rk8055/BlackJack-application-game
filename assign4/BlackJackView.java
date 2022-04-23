package assign4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.scene.control.Alert.AlertType;
public class BlackJackView extends Pane{
	
	HandPane       dealerPane;
	PlayerPane     playerPane;
	Button         hitMe, stay, newGame;
	Label          message;
	BlackJackModel model;
	
	public BlackJackView(BlackJackModel model) {
		this.model = model;
		dealerPane = new HandPane("Dealer Hand");
		playerPane = new PlayerPane("Player Hand");
        newGame = new Button("New Deal");
        hitMe = new Button("Hit Me");
        stay = new Button("Stay");
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				model.newDeal();
				update();
			}
		});
		hitMe.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(model.canHit() == true) {
					model.hitMe();
					update();
					if(model.playerBusted() == true){
						Alert alert2 = new Alert(AlertType.INFORMATION);
						alert2.setTitle("Alert");
						alert2.setHeaderText(null);
						alert2.setContentText("Player Busted");
						alert2.showAndWait();

					}
				}
			}
		});
		stay.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(model.canStay() == true) {
					model.stay();
					update();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Alert");
					alert.setHeaderText(null);
					alert.setContentText(model.getMessage());
					alert.showAndWait();
				}
			}
		});

		playerPane.getBetButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				playerPane.currentBet.setText(playerPane.nextBet.getText());
				model.placeBet(model.getCurrentBet());
				update();
			}
		});
       
        message = new Label("Place bets!");
        
        // you may change this to BorderPane if you like.
        // Either way, add the components to it.
        GridPane root = new GridPane();
        root.setPrefSize(400,400);
        root.setMaxSize(400,400);
        //dealerPane.relocate(10,10);
       // root.add(dealerPane,0,1);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(20);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(20);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(20);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(20);
		root.getColumnConstraints().addAll(column1,column2,column3,column4);
		root.add(dealerPane,0,1);

        //playerPane.relocate(10,105);
        root.add(playerPane,0,2);
        getChildren().add(root);
        root.add(newGame,0,4);
        root.add(hitMe,1,4);
        root.add(stay,2,4);
        root.add(message,3,4);
	}
	public Button getHitMe(){return hitMe;}
	public  Button getNewDeal(){return newGame;}
	public Button getStay(){return stay;}
	public Button getPlaceBet(){return playerPane.getBetButton();}
	public int getBet(){
		return playerPane.getBet();
	}
	/*
	 * Write getters for 'newDeal', 'hitMe', and 'stay' button.
	 * Write getter for  'betButton' (from the playerPane class).
	 * Write getter for  'getBet()'  (from the playerPane class).
	 */
	
	
	
	// we update all fields in the view with data from the model
	public void update() {
		playerPane.setHand(model.getPlayerHand());
		dealerPane.setHand(model.getDealerHand());
		playerPane.setCash(model.getPlayerCash());
		playerPane.setBet(model.getCurrentBet());
		message.setText(model.getMessage());
		//update the player hand (in playerPane object), 
		//       the player bet  (in playerPane object),  
		//       the player cash (in playerPane object), 
		//       the dealer hand (in dealerPane object),
		//       the message label.
		
		// All of this information is available by calling the appropriate model method. 

		
	}

}
