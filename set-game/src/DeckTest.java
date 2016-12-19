import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class DeckTest {

	private Deck testDeck;
	
	@Before 
	public void setUpHand() { 
		testDeck = new Deck(); 
	}
	
	@Test
	public void testDrawCardMethod() { 
		int initialDeckSize;
		int finalDeckSize;
		
		initialDeckSize = testDeck.getCards().size();
		Card testCard = testDeck.dealCard();
		finalDeckSize = testDeck.getCards().size();
		assertEquals("Deck size should decrease by 1 after a card is dealt", initialDeckSize-1, finalDeckSize);
	}
	
	
	@Test
	public void testDraw12CardMethod() { 
		int initialDeckSize;
		int finalDeckSize;
		Card[] testCardList;
		
		initialDeckSize = testDeck.getCards().size();
		testCardList = testDeck.deal12Cards();
		finalDeckSize = testDeck.getCards().size();
		assertEquals("Deck size should decrease by 12 after a card is dealt", initialDeckSize-12, finalDeckSize);
		assertEquals("12 cards should be dealt", testCardList.length, 12);
	}

}
