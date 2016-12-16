import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * a class represents a deck in the set game
 * it can generate all the possible cards and deal a card randomly
 * @author YPLin
 *
 */
public class Deck {
	List<Card> cards;
	final String[] colors = {"green", "red", "purple"};
	final String[] shapes = {"oval", "squiggle", "diamond"};
	final String[] shadings = {"empty", "striped", "solid"};
	final String[] quantities = {"1", "2", "3"};
	
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
						System.out.print(card);
					}
				}
			}
		}
	}
	
	public Card[] deal12Cards(){
		Card[] cards = new Card[12];
		for(int i=0; i<12; i++) {
			cards[i] = dealCard();
		}
		System.out.println("12 cards are dealed");
		return cards;
	}
	
	/**
	 * a method deal and remove a card from the deck randomly
	 * @return
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
	
}
