package assign4;

import java.util.ArrayList;

//hand of either the player or the dealer. We will subclass them later and implement dealer logic. 
public class Hand {
	ArrayList<Card> hand;
	int total;
	int totalAces;
	
	public Hand() {
		hand = new ArrayList<>();
		total = 0;
		totalAces = 0;
	}
	
	public void resetHand() {
		hand.clear();
		total = 0;
		totalAces = 0;
	}
	
	public int dealCard(Card card) {
		hand.add(card);
		if (card.getCardValue() == 1) {
			totalAces ++;
			total += 11;
		} else {
			total += card.getCardValue();
		}
		//if we busted, but there are aces, we make those aces worth 1 instead of 11
		while(total >21) {
			if (totalAces <1) {
				break;
			}
			totalAces --;
			total -= 10;
		}
		return total;
	}
	
	public boolean busted() {
		return total > 21;
	}
	
	public int getTotal() {
		return total;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Card card: hand) {
			str.append(card);
			str.append(", ");
		}
		if (str.length()>3) {
			str.delete(str.length()-1, str.length());
		}
		return str.toString();
	}
}
