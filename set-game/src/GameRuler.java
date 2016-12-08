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
		} else {
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
		} else if (currentPlayer == 2){
			currentPlayer = 1;
		}
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


}
