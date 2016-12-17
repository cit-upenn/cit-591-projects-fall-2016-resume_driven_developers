import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that contains all of the cards on the board and whether or not they are highlighted
 * ATM
 * @author Andrew
 *
 */

public class Board {
	//Two arrays to store the cards currently on the board and whether or not they have been clicked
	private Card[] playedCards;
	private boolean[] clickedCards;
	
	/**
	 * Constructor when now input values are given
	 */
	public Board() {
		playedCards = new Card[15];
		clickedCards = new boolean[15];
	}
	
	/**
	 * Constructor when an array of cards is given.  The boolean array is automatically initialized to all false's, 
	 * which is what we want, so we don't worry about initializing it with a parameter.
	 */
	public Board(Card[] inputCardArray) {
		playedCards = new Card[15];
		for(int i = 0; i < inputCardArray.length; i++) {
			playedCards[i] = inputCardArray[i];
		}
		clickedCards = new boolean[15];
	}

	/**
	 * @return the playedCards
	 */
	public Card[] getPlayedCards() {
		return playedCards;
	}

	/**
	 * @param playedCards the playedCards to set
	 */
	public void setPlayedCards(Card[] playedCards) {
		this.playedCards = playedCards;
	}

	/**
	 * @return the clickedCards
	 */
	public boolean[] getClickedCards() {
		return clickedCards;
	}

	/**
	 * @param clickedCards the clickedCards to set
	 */
	public void setClickedCards(boolean[] clickedCards) {
		this.clickedCards = clickedCards;
	}
	
	
	
}
