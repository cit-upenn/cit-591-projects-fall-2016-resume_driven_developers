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
		
		gameWindow.onePlayerGame.addActionListener(new generateAL());
		gameWindow.twoPlayerGame.addActionListener(new generateAL());
		gameWindow.howToPlay.addActionListener(new generateAL());
		
	}

	public class generateAL implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			if(e.getSource().equals(gameWindow.onePlayerGame)) {
				gameWindow.setNumPlayers(1);
			} else if(e.getSource().equals(gameWindow.twoPlayerGame)) {
				gameWindow.setNumPlayers(2);
			} else if(e.getSource().equals(gameWindow.howToPlay)) {
				
			}	
				
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
