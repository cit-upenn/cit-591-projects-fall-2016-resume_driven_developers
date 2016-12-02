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
	
}
