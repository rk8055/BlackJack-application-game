package assign4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PlayerPane extends HandPane {

	TextField currentBet, nextBet, currentCash;
	Button placeBet;

	public PlayerPane(String playerName) {
		super(playerName);
		
		Label betLabel = new Label("Next Bet:");
		betLabel.setPrefSize(100, 30);
		betLabel.relocate(180, 135);
		placeBet = new Button("Place Bet");
		placeBet.setPrefSize(100, 30);
		placeBet.relocate(60, 100);
		Label cbet = new Label("Current Bet:");
		cbet.setPrefSize(100, 30);
		cbet.relocate(180, 100);
		currentBet   = new TextField();
		currentBet.setPrefSize(100, 30);
		currentBet.relocate(290, 100);
		nextBet   = new TextField();
		nextBet.setPrefSize(100, 30);
		nextBet.relocate(290, 135);
		Label cCash = new Label("Cash:");
		cCash.setPrefSize(100,30);
		cCash.relocate(10, 135);
		currentCash = new TextField();
		currentCash.setPrefSize(100, 30);
		currentCash.relocate(60,135);
		
		placeBet.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	currentBet.setText(nextBet.getText());
	            }
	    });
        
		getChildren().addAll(betLabel,cbet,cCash,  placeBet, currentBet, nextBet, currentCash);
		
		
		
	}
	
	public Button getBetButton() {
		return placeBet;
	}
	
	public int getBet() {
		if(nextBet.getText().contentEquals("")) {
			return 0;
		}
		return Integer.parseInt(nextBet.getText());
	}
	
	public void setBet(int bet) {
		currentBet.setText(Integer.toString(bet));
	}

	public void setCash(int playerCash) {
		currentCash.setText(Integer.toString(playerCash));
		
	}

}
