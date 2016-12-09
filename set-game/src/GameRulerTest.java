import static org.junit.Assert.*;

import org.junit.Test;

/**
 * test for game ruler class's methods
 * @author YPLin
 *
 */
public class GameRulerTest {

	
	// TEST SINGLE VARIABLE
	@Test
	public void testColor() {
		Card card1 = new Card("a", "b", "c", "d", "e");
		Card card2 = new Card("a", "b", "c", "d", "e");
		Card card3 = new Card("a", "b", "c", "d", "e");

		int actual = GameRuler.isSameColor(card1, card2);
		int expected = 1;
		assertTrue(actual == expected);
	}
	
	@Test
	public void testShape() {
		Card card1 = new Card("a", "b", "c", "d", "e");
		Card card2 = new Card("a", "b", "c", "d", "e");

		int actual = GameRuler.isSameShape(card1, card2);
		int expected = 1;
		assertTrue(actual == expected);
	}	
	
	
	@Test
	public void testShading() {
		Card card1 = new Card("a", "b", "c", "d", "e");
		Card card2 = new Card("a", "b", "c", "d", "e");

		int actual = GameRuler.isSameShading(card1, card2);
		int expected = 1;
		assertTrue(actual == expected);
	}	
	
	@Test
	public void testQuantity() {
		Card card1 = new Card("id", "quan", "color", "shading", "shape");
		Card card2 = new Card("id", "quan", "color", "shading", "shape");

		int actual = GameRuler.isSameQuantity(card1, card2);
		int expected = 1;
		assertTrue(actual == expected);
	}		
	
	@Test
	public void testCountSimilarity1() {
		Card card1 = new Card("id", "quan", "color", "shading", "shape");
		Card card2 = new Card("id", "quan", "color", "shading", "shape");

		int actual = GameRuler.countSimilarity(card1, card2);
		int expected = 4;
		assertTrue(actual == expected);
	}	

	@Test
	public void testCountSimilarity2() {
		Card card1 = new Card("id", "quan", "color", "shading", "shape");
		Card card2 = new Card("id", "quan", "color", "shading", "shapeeee");

		int actual = GameRuler.countSimilarity(card1, card2);
		int expected = 3;
		assertTrue(actual == expected);
	}	

	@Test
	public void testContainsRule1() {
		Card card1 = new Card("id", "2", "red", "empty", "oval");
		Card card2 = new Card("id", "2", "red", "striped", "oval");
		Card card3 = new Card("id", "2", "red", "solid", "oval");

		boolean actual = GameRuler.containsRule(card1, card2, card3);
		boolean expected = true;
		assertTrue(actual == expected);
	}	

	@Test
	public void testContainsRule2() {
		Card card1 = new Card("id", "1", "green", "striped", "stri");
		Card card2 = new Card("id", "2", "purple", "striped", "oval");
		Card card3 = new Card("id", "3", "red", "striped", "diamond");

		boolean actual = GameRuler.containsRule(card1, card2, card3);
		boolean expected = true;
		assertTrue(actual == expected);
	}	

	@Test
	public void testContainsRule3() {
		Card card1 = new Card("id", "1", "purple", "striped", "oval");
		Card card2 = new Card("id", "2", "green", "solid", "diamond");
		Card card3 = new Card("id", "3", "red", "empty", "stri");

		boolean actual = GameRuler.containsRule(card1, card2, card3);
		boolean expected = true;
		assertTrue(actual == expected);
	}	
	
	@Test
	public void testContainsRule4() {
		Card card1 = new Card("id", "1", "green", "striped", "oval");
		Card card2 = new Card("id", "2", "green", "solid", "diamond");
		Card card3 = new Card("id", "3", "red", "empty", "stri");

		boolean actual = GameRuler.containsRule(card1, card2, card3);
		boolean expected = false;
		assertTrue(actual == expected);
	}	
}
