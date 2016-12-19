import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * test for game ruler class's methods
 * @author YPLin
 *
 */
public class GameRulerTest {
	private GameRuler testRuler;
	private Card card1;
	private Card card2;
	private Card card3;
	private Card card4;
	private Card card5;
	private Card card6;
	private ArrayList<Integer> testMatchList;
	
	
	
	@Before 
	public void setUpHand() { 
		testRuler = new GameRuler();
		card1 = new Card("a", "b", "c", "d", "e");
		card2 = new Card("a", "b", "c", "d", "e");
		card3 = new Card("a", "b", "c", "d", "e");
		
		card4 = new Card("id", "1", "green", "striped", "stri");
		card5 = new Card("id", "2", "purple", "striped", "oval");
		card6 = new Card("id", "3", "red", "striped", "diamond");
		testMatchList = new ArrayList<Integer>();
		testMatchList.add(1);
		testMatchList.add(2);
		testMatchList.add(3);
	}
	
	// TEST SINGLE VARIABLE
	@Test
	public void testColor() {
		int actual = GameRuler.isSameColor(card1, card2);
		int expected = 1;
		assertTrue(actual == expected);
	}
	
	@Test
	public void testShape() {
		int actual = GameRuler.isSameShape(card1, card2);
		int expected = 1;
		assertTrue(actual == expected);
	}	
	
	
	@Test
	public void testShading() {
		int actual = GameRuler.isSameShading(card1, card2);
		int expected = 1;
		assertTrue(actual == expected);
	}	
	
	@Test
	public void testQuantity() {
		int actual = GameRuler.isSameQuantity(card1, card2);
		int expected = 1;
		assertTrue(actual == expected);
	}		
	
//	@Test
//	public void testCountSimilarity1() {
//		int actual = GameRuler.countSimilarity(card1, card2);
//		int expected = 4;
//		assertTrue(actual == expected);
//	}	
//
//	@Test
//	public void testCountSimilarity2() {
//		Card card1 = new Card("id", "quan", "color", "shading", "shape");
//		Card card2 = new Card("id", "quan", "color", "shading", "shapeeee");
//		
//		int actual = GameRuler.countSimilarity(card1, card2);
//		int expected = 3;
//		assertTrue(actual == expected);
//	}	
	
	@Test
	public void testContainsRuleArrayInput(){
		ArrayList<Card> selectedCardsFun = new ArrayList<Card>();
		selectedCardsFun.add(card4);
		selectedCardsFun.add(card5);
		selectedCardsFun.add(card6);
		
		boolean actual = testRuler.containsRule(selectedCardsFun);
		boolean expected = true;
		assertTrue(actual == expected);
	}
	
	@Test
	public void testReplacedMatchedBoardCards(){
		
		int initialDeckSize;
		int finalDeckSize;
		boolean resultOfMatchCards;		
		
		//Since we're starting the game, the deck should have three less cards
		//after the call, and the function should return false
		initialDeckSize = testRuler.playDeck.getCards().size();
		resultOfMatchCards = testRuler.replacedMatchedBoardCards(testMatchList);
		finalDeckSize = testRuler.playDeck.getCards().size();
		
		assertEquals(initialDeckSize-3, finalDeckSize);
		assertTrue(!resultOfMatchCards);
	}
	
	@Test
	public void testShiftedMatchedBoardCards(){
		testRuler.shiftedMatchedBoardCards(testMatchList);
		
		//If we have shifted the cards, the spots on spots 12,13 and 14 should be null
		assertEquals(testRuler.playBoard.getPlayedCards()[12], null);
		assertEquals(testRuler.playBoard.getPlayedCards()[13], null);
		assertEquals(testRuler.playBoard.getPlayedCards()[14], null);
	}
}
