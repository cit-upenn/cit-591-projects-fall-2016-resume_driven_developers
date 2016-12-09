
import java.awt.event.ActionEvent;		
import java.awt.event.ActionListener;		
import java.util.ArrayList;		
import java.util.Random;

/**
 * a class for game rules
 * @author YPLin
 *
 */

public class GameRuler {

	Player playerOne;		
 	Player playerTwo;		
 	int currentPlayer;		
 		
 	public GameRuler() {		
 		playerOne = null;		
 		playerTwo = null;		
 		currentPlayer = 0;
 	}
 	
 	public void onePlayerGame(){		
 				playerOne = new Player();		
 				currentPlayer = 1;		
 						
 				//get player's name		
 						
 				//initialize timer		
 						
 				//listen for 3 clicked cards		
 						
 				//pause timer & check if selected cards are a set		
 						
 				//if set, add points		
 						
 				//if not set, tell player & resume timer		
 						
 						
 	}		
 				
 	public void twoPlayerGame(String player1Name, String player2Name){		
 		playerOne = new Player();		
 		playerOne.setName(player1Name);		
 		playerTwo = new Player();		
 		playerTwo.setName(player2Name);		
 						
 		Random rand = new Random();		
 		if (rand.nextBoolean()){		
 			currentPlayer = 1;		
 		} 
 		else {		
 			currentPlayer = 2;		
 		}		
 						
 		//get players' names		
 						
 		//randomly select which player goes first		
 						
 		//deal 12 cards		
 						
 		//initialize timer		
 						
 		//listen for 3 clicked cards		
 						
 		//pause timer & check if selected cards are a set		
 						
 		//if set, add points to current player & switch players		
 						
 		//if not set, tell player & resume timer		
 	}		
 		  			  	
 	public void switchPlayer(){		
 		if (currentPlayer == 1){		
 		 	currentPlayer = 2;		
 		} 
 		else if (currentPlayer == 2){		
 			currentPlayer = 1;		
 		}		
 	}
 	
 	
	/**
	 * A method to validate whether the 3 cards contain a "rule" among them
	 * @param card1
	 * @param card2
	 * @param card3
	 * @return
	 */
	public static boolean containsRule(Card card1, Card card2, Card card3) {
		int[] featureCounts = new int[4]; // featureCounts[0] will never be used though
		
		featureCounts[getQuantityCount(card1, card2, card3)]++;
		featureCounts[getColorCount(card1, card2, card3)]++;
		featureCounts[getShapeCount(card1, card2, card3)]++;
		featureCounts[getShadingCount(card1, card2, card3)]++;

		if(featureCounts[3] == 1 && featureCounts[1] == 3) return true;
		if(featureCounts[3] == 2 && featureCounts[1] == 2) return true; // additional rule
		if(featureCounts[3] == 3 && featureCounts[1] == 1) return true;
		if(featureCounts[3] == 4) return true;

		return false;
	}
	
	
	public static int countSimilarity(Card card1, Card card2) {
		int count = 0;
		count += isSameShape(card1, card2);
		count += isSameColor(card1, card2);
		count += isSameShading(card1, card2);
		count += isSameQuantity(card1, card2);
		
		return count;
	}
	
	public static int isSameShape(Card card1, Card card2) {
		return card1.getShape().equals(card2.getShape())? 1:0; 
	}

	public static int isSameColor(Card card1, Card card2) {
		return card1.getColor().equals(card2.getColor())? 1:0; 		
	}

	
	public static int isSameShading(Card card1, Card card2) {
		return card1.getShading().equals(card2.getShading())? 1:0; 			
	}

	public static int isSameQuantity(Card card1, Card card2) {
		return card1.getQuantity().equals(card2.getQuantity())? 1:0; 			
	}
	
	
	/**
	 * return the number of unique features among the 3 cards
	 * @param card1
	 * @param card2
	 * @param card3
	 * @return
	 */
	public static int getShapeCount(Card card1, Card card2, Card card3) {
		if(card1.getShape().equals(card2.getShape()) && card1.getShape().equals(card3.getShape())) {
			return 1; // all the same
		}
		else if(!card1.getShape().equals(card2.getShape()) 
				&& !card1.getShape().equals(card3.getShape())
				&& !card2.getShape().equals(card3.getShape())) {
			return 3; // all different
		}
		else {
			return 2;
		}
	}
		
	/**
	 * return the number of unique features among the 3 cards
	 * @param card1
	 * @param card2
	 * @param card3
	 * @return
	 */
	public static int getColorCount(Card card1, Card card2, Card card3) {
		if(card1.getColor().equals(card2.getColor()) && card1.getColor().equals(card3.getColor())) {
			return 1; // all the same
		}
		else if(!card1.getColor().equals(card2.getColor()) 
				&& !card1.getColor().equals(card3.getColor())
				&& !card2.getColor().equals(card3.getColor())) {
			return 3; // all different
		}
		else {
			return 2;
		}
	}
	
	/**
	 * return the number of unique features among the 3 cards
	 * @param card1
	 * @param card2
	 * @param card3
	 * @return
	 */
	public static int getShadingCount(Card card1, Card card2, Card card3) {
		if(card1.getShading().equals(card2.getShading()) && card1.getShading().equals(card3.getShading())) {
			return 1; // all the same
		}
		else if(!card1.getShading().equals(card2.getShading()) 
				&& !card1.getShading().equals(card3.getShading())
				&& !card2.getShading().equals(card3.getShading())) {
			return 3; // all different
		}
		else {
			return 2;
		}
	}
	
	
	/**
	 * return the number of unique features among the 3 cards
	 * @param card1
	 * @param card2
	 * @param card3
	 * @return
	 */
	public static int getQuantityCount(Card card1, Card card2, Card card3) {
		if(card1.getQuantity().equals(card2.getQuantity()) && card1.getQuantity().equals(card3.getQuantity())) {
			return 1; // all the same
		}
		else if(!card1.getQuantity().equals(card2.getQuantity()) 
				&& !card1.getQuantity().equals(card3.getQuantity())
				&& !card2.getQuantity().equals(card3.getQuantity())) {
			return 3; // all different
		}
		else {
			return 2;
		}
	}
	
}