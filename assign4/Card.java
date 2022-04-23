package assign4;

import java.util.Random;

public class Card {
	
	//we use String instead of char because "10" is two characters. 
	public static final String[] cardNumbers = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	
	//unicode values in hex
	static int diamond = 0x2662;
	static int heart = 0x2661;
	static int club = 0x2663;
	static int spade = 0x2660;
	
	public static final char[] suits = {(char)heart,(char)diamond,(char)club,(char)spade};

	//initialize to useless values
	int cardNumber = -1;
	int cardSuit = -1;
	
	//to generate random cards
	Random random = new Random();
	
	//public static final char[] suits = {Character.toString((char)heart), 
	//		Character.toString((char)diamond), Character.toString((char)club), Character.toString((char)spade)};
			
	
	//must be a number from 0 to 12 and 0 to 3
	//probably make the students do this
	public Card(int cardNumber, int cardSuit) {
		if (cardNumber > 12||cardNumber <0) {
			this.cardNumber = random.nextInt(13);
		}else {
			this.cardNumber = cardNumber;
		}
		if (cardSuit > 3||cardSuit <0) {
			this.cardSuit = random.nextInt(4);
		}else {
			this.cardSuit = cardSuit;
		}
	}
	
	//make this a random card
	public Card() {
		cardNumber = random.nextInt(13);
		cardSuit = random.nextInt(4);
	}
	
	public static Card randomCard() {
		return new Card();
	}
	
	public String toString() {
		return Card.cardNumbers[cardNumber]+Card.suits[cardSuit];
	}
	
	public static void main (String[] args) {
		for(int i = 0; i < 13; i++) {
			for (int j = 0; j < 4; j ++) {
				System.out.println(cardNumbers[i]+suits[j]);
			}
		}
	}

	public int getCardValue() {
		if (cardNumber >=9) {
			return 10;
		}else {
			return cardNumber+1;
		}
	}

}
