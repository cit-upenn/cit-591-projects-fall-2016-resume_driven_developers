import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a deck in the Set game.
 * It can generate all the possible cards and deal a card randomly.
 */
public class Deck {
	
	private List<Card> cards;
	private final String[] colors = {"green", "red", "purple"};
	private final String[] shapes = {"oval", "squiggle", "diamond"};
	private final String[] shadings = {"solid", "striped", "empty"};
	private final String[] quantities = {"1", "2", "3"};
	
	/**
	 * constructor to initiate all the possible cards in the deck
	 */
	public Deck() {
		cards = new ArrayList<Card>();
		int count = 1;
		for(String color:colors) {
			for(String shape:shapes) {
				for(String shading:shadings) {
					for(String quan:quantities) {
						Card card = new Card(""+count, quan, color, shading, shape);
						cards.add(card);
						count++;
					}
				}
			}
		}
	}
	
	/**
	 * This method deals 12 cards 
	 * @return an array of 12 randomly selected Cards
	 */
	public Card[] deal12Cards(){
		Card[] cards = new Card[12];
		for(int i=0; i<12; i++) {
			cards[i] = dealCard();
		}
		return cards;
	}
	
	/**
	 * This method deals a card randomly
	 * and removes that card from the remaining deck
	 * @return a randomly selected Card
	 */
	public Card dealCard() {
		int number = cards.size();
		Random ran = new Random();
		int idx = ran.nextInt(number);
		Card removed = cards.get(idx);
		Card c = new Card(removed.getIdNumber(), removed.getQuantity(), removed.getColor(), removed.getShading(), removed.getShape());
		cards.remove(idx);
		return c;
	}

	/**
	 * @return the cards still in the deck
	 */
	public List<Card> getCards() {
		return cards;
	}
	
}
