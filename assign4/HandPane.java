package assign4;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class HandPane extends Pane{
	TextField playerHand, handTotal;
	
	public HandPane(String playerName) {
		Label name = new Label(playerName);
		name.relocate(10, 20);
		name.setPrefSize(100, 30);
		Label total = new Label("Total");
		total.relocate(10, 55);
		total.setPrefSize(100, 30);
		playerHand = new TextField();
		playerHand.relocate(120, 20);
		playerHand.setPrefSize(225, 30);
		handTotal = new TextField();
		handTotal.relocate(120, 55);
		handTotal.setPrefSize(225, 30);
		getChildren().addAll(name, total, playerHand, handTotal);
		
	}
	
	public void setHand(Hand hand) {
		playerHand.setText(hand.toString());
		handTotal.setText(Integer.toString(hand.getTotal()));
	}

}
