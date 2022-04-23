package assign4;

/*
 * This class should allow us to play a complete game of blackjack. It will maintain the state and the rules
 * that allow certain actions to be taken at certain times. This class needs to be able to do two things:
 * 
 * 1. Trigger game events (deal cards, place bets, etc), also known as application logic
 * 2. Offer up information about the game state to a View class that will display it. 
 */
public class BlackJackModel {
	private Hand       player;
	private DealerHand dealer;
	private Deck       deck;
	private int        cash;
	private int        bet;

	private static final int DEFAULT_STARTING_CASH = 300;

	//constants to track game state
	private static final int PLACEBETS   = 0;
	private static final int PLAYERHITS  = 1;
	private static final int PLAYERBROKE = 2;

	private int gameState;

	// the message we want the player to see
	private String message;


	public BlackJackModel() {

		dealer    = new DealerHand();
		player    = new Hand();
		deck      = new Deck();
		bet       = 0;
		cash      = DEFAULT_STARTING_CASH;
		gameState = PLACEBETS;


	}
	
	/* PUBLIC METHODS: GETTERS AND SETTERS *************************************/
	
	/* hint: you will use the getters to populate the BlackJackView ***************************/
		
	public int getPlayerCash() {
		return cash;
	}

	public int getCurrentBet() {
		return bet;
	}
	
	public Hand getPlayerHand() {
		return player;
	}

	public Hand getDealerHand() {
		return dealer;
	}
	
	public String getMessage() {
		return message;
	}

	
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	/* PUBLIC METHODS: APPLICATION LOGIC **************************/
	
	/* hint: you will call these when the appropriate view element is triggered,
	 * i.e., button is pressed. They are listed below for convenience. 
	 * 
	 * public void playerBets(int bet)
	 * 
	 * public void newDeal()
	 * 
	 * public void hitMe()
	 * 
	 * public void stay()
	 */

	public boolean canBet(){
		return gameState == PLACEBETS;
	}
	
	public void placeBet(int bet) {
		if(!canBet()) {
			return;
		}
		setMessage("Bet placed!");
		setCurrentBet(bet);
	}
	
	public void newDeal() {		
		//this button doubles as a "new game" button if the player is broke, which we check for here
		if(gameState == PLAYERBROKE) {
			gameState = PLACEBETS;
			setMessage("Place your bets!");
			resetCash();
			return;
		}
		//are we in the correct game state?
		if(gameState != PLACEBETS) {
			return;
		}
		//has the player placed a bet?
		if (getCurrentBet()<=0) {
			setMessage("Player needs to place bet.");
			return;
		}
		gameState = PLAYERHITS;
		setMessage("New deal!");

		//take the old cards away		
		dealer.resetHand();
		player.resetHand();

		//dealer gets one card to start
		Card card = deck.dealCard();
		dealer.dealCard(card);

		//player gets two cards to start
		card = deck.dealCard();
		player.dealCard(card);
		card = deck.dealCard();
		player.dealCard(card);
	}

	public boolean canHit(){
		return gameState == PLAYERHITS;
	}
	
	
	public void hitMe() {

		//are we in the correct gameState?
		if (!canHit()) {
			return;
		}
		//deal a card to the player and check if they bust
		player.dealCard(deck.dealCard());
		if(playerBusted()) {
			//player has lost, take their money away
			takeCurrentBet();
			if (getPlayerCash()<=0) {
				setMessage("Player BROKE! Hit 'New Deal' for new game!");
				gameState = PLAYERBROKE;
			}else {
				setMessage("Player Busted!");
				gameState = PLACEBETS;
			}
		}else {
			setMessage("Player hits!");
		}
	}

	public boolean playerBusted() {
		return player.busted();
	}


	public boolean canStay(){
		return gameState == PLAYERHITS;
	}

	public void stay() {
		//we are not in the correct game state for this method
		if(!canStay()) {
			return;
		}
		//deal cards to the dealer until they are done
		while(!dealerFinished()) {
			cardToDealer();
		}
		//dealer has finished taking cards, let's see who won
		int win = playerWins();
		if (win == 0) {
			setMessage("Push!");
		}else if(win<0) {
			takeCurrentBet();
			if (getPlayerCash()<=0) {
				setMessage("Player BROKE! Hit 'New Deal' for new game!");
			}else {
				setMessage("Player loses.");
			}
		}else {
			setMessage("Player wins!");
			addBetToCash();
		}
		gameState = PLACEBETS;
	}
	
	/* private (helper) methods *****************************************/
	/* these private methods help with self documenting the code ********/

	private void setCurrentBet(int bet) {

		//does player have enough money to bet?
		if(bet - this.bet > cash) {
			return;
		}
		cash -= (bet-this.bet);
		this.bet = bet;		
	}

	//player has won, pay them out
	private void addBetToCash() {
		cash +=bet;
	}

	// start the game over
	private void resetCash() {
		resetCash(DEFAULT_STARTING_CASH);
	}

	private void resetCash(int cash) {
		this.cash = cash;
	}

	// player loses, take their bet
	private void takeCurrentBet() {
		bet = 0;
	}

	private boolean dealerFinished() {
		return dealer.dealerFinished();
	}

	private void cardToDealer() {
		dealer.dealCard(deck.dealCard());
	}

	private int playerWins() {
		if (player.busted()) {return -1;}
		if (dealer.busted()) {return 1;}
		return player.getTotal()-dealer.getTotal();
	}

}
