package assign4;

public class DealerHand extends Hand{
	
	
	//dealer stays on 17 or above, pulls on soft 17
	public boolean dealerFinished() {
		if (getTotal()==17) {
			return !isSoft();
		}
		return getTotal()>17;
	}
	
	private boolean isSoft() {
		return totalAces >0;
	}

}
