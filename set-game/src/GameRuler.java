import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * a class for game rules
 * @author YPLin
 *
 */

public class GameRuler {

	GameWindow gameWindow;

	public GameRuler() {

		gameWindow = new GameWindow();
		gameWindow.setVisible(true);

		gameWindow.howToPlay.addActionListener(new generateAL());
		gameWindow.onePlayerGame.addActionListener(new generateAL());
		gameWindow.twoPlayerGame.addActionListener(new generateAL());

	}

	public class generateAL implements ActionListener {
		public void actionPerformed (ActionEvent e) {

			if(e.getSource().equals(gameWindow.onePlayerGame)) {
				gameWindow.setNumPlayers(1);
				onePlayerGame();
			} else if(e.getSource().equals(gameWindow.twoPlayerGame)) {
				gameWindow.setNumPlayers(2);
				twoPlayerGame();
			} else if(e.getSource().equals(gameWindow.howToPlay)) {
				//show panel with rules and 
			} else if (e.getSource().equals(gameWindow.addCards)) {
				//add 3 cards to empty row at the bottom
			} else if (e.getSource().equals(gameWindow.getHint)) {
				//highlight one card that is part of a set on the board
			} else if (e.getSource().equals(gameWindow.skipTurn)) {
				//prompt if sure
				//start other player's turn
			} else if (e.getSource().equals(gameWindow.quitGame)) {
				
			}
		}
	}

	public void onePlayerGame(){
		initButtons(1);
		
		//get player's name
		
		//initialize timer
		
		//listen for 3 clicked cards
		
		//pause timer & check if selected cards are a set
		
		//if set, add points
		
		//if not set, tell player & resume timer
		
		
	}

	public void twoPlayerGame(){
		initButtons(2);
		
		//get players' names
		
		//randomly select which player goes first
		
		//deal 12 cards
		
		//initialize timer
		
		//listen for 3 clicked cards
		
		//pause timer & check if selected cards are a set
		
		//if set, add points to current player & switch players
		
		//if not set, tell player & resume timer
	}

	public void initButtons(int n){
		gameWindow.quitGame.addActionListener(new generateAL());
		gameWindow.addCards.addActionListener(new generateAL());
		gameWindow.getHint.addActionListener(new generateAL());

		if (n == 2){
			gameWindow.skipTurn.addActionListener(new generateAL());
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
