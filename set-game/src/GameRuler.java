import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * a class for game rules
 * @author YPLin
 *
 */

public class GameRuler {

	Player playerOne;
	Player playerTwo;
	int currentPlayer;
	
	// GameRuler holds a Board & a Deck object
	Board playBoard;
	Deck playDeck;

	/**
	 * constructor which initiates the Deck and the Board
	 */
	public GameRuler() {
		playerOne = new Player();
		playerTwo = new Player();
		currentPlayer = 0;
		
		// create the deck
		playDeck = new Deck();
		
		// create a board object: only deal 12 cards at first
		playBoard = new Board(playDeck.deal12Cards());
		ensureBoardHasSolution();
	
	}
	
	/**
	 * This method will add 3 additional cards to the board, so that the total will now be 15 cards for the
	 * remainder of the game
	 */
	public void addThreeCardsToBoard() {
		for(int i = 12; i < 15; i++) {
			this.playBoard.getPlayedCards()[i] = playDeck.dealCard();
		}
	}
	
	
	/**
	 * Throw away 60 cards to help test the end game
	 */
	public void throwAwayCards() {
		for(int i = 0; i < 66; i++){
			this.playDeck.dealCard();
		}
	}

	/**
	 * This method is called by GameWindow when a player finds a match.  It replaces the cards that are matched with 3 new cards from the deck.
	 * In addition, it will be the method that knows when the game ends, so it returns a value of "true" if there are no more cards in
	 * the deck and no more matches on the board
	 * @param matchedCards An array list of integers containing the indices of the matched cards
	 * @return "true" if there are no more cards in the deck and no more matches on the board; false otherwise
	 * @author Andrew
	 */
	public boolean replacedMatchedBoardCards(ArrayList<Integer> matchedCards) {
		boolean endGameFlag = false;
		
		if(this.playDeck.getCards().size()>0) {
			
			// cards left in the deck to draw
			for(int i = 0; i < matchedCards.size(); i++) {
				this.playBoard.getPlayedCards()[matchedCards.get(i)] = playDeck.dealCard();
			}
			
			// if the 3 cards result in a board with no solutions, deal three more cards
			if (getSolutions(this.playBoard.getPlayedCards()).size() == 0) replacedMatchedBoardCards(matchedCards);
		} 
		else {
			// no more cards left to draw			
			for(int i = 0; i < matchedCards.size(); i++) {
				this.playBoard.getPlayedCards()[matchedCards.get(i)] = null;
			}
			
			// no more cards && no more solutions -> end the game!
			if (getSolutions(this.playBoard.getPlayedCards()).size() == 0) {
				endGameFlag = true;
				return endGameFlag;
			}
		}
				
		return endGameFlag;
	}
	

		/**
	 * This method is called when there are 15 cards displayed on the board.
	 * It uses an ArrayList to hold the 12 cards remaining after the set is found.
	 * An array of Cards is created. The first 12 cards are set to the ArrayList
	 * and the last three are set to null.
	 */
	public boolean shiftedMatchedBoardCards(ArrayList<Integer> matchedCards){
		boolean endGameFlag = false;
		
		ArrayList<Card> currentTwelve = new ArrayList<Card>();
		
		if(this.playDeck.getCards().size()>0) {
			
			// cards left in the deck to draw
			for(int i = 0; i < 15; i++) {
				if (!matchedCards.contains(i)){
					currentTwelve.add(playBoard.getPlayedCards()[i]);
				} else {
					continue;
				}
				
			}
			
			Card[] newFifteen = new Card[15];
			
			for (int j = 0; j < 15; j++){
				if (j < 12){
					newFifteen[j] = currentTwelve.get(j);
				} else {
					newFifteen[j] = null;
				}
			}
				
			playBoard.setPlayedCards(newFifteen);

			
			// if the 3 cards result in a board with no solutions, deal three more cards
			if (getSolutions(this.playBoard.getPlayedCards()).size() == 0) replacedMatchedBoardCards(matchedCards);
		} 
		else {
			// no more cards left to draw			
			for(int i = 0; i < matchedCards.size(); i++) {
				this.playBoard.getPlayedCards()[matchedCards.get(i)] = null;
			}
			
			// no more cards && no more solutions -> end the game!
			if (getSolutions(this.playBoard.getPlayedCards()).size() == 0) {
				endGameFlag = true;
				return endGameFlag;
			}
		}
				
		return endGameFlag;
	}

	
	/**
	 * Create a method that checks if there's a solution on the board.  If there's not, then randomly pick three cards to replace them.  We'll
	 * need to call this every time we update the board, with maybe the exception of adding three cards.
	 * @author Andrew
	 */
	public void ensureBoardHasSolution() {
		//Keep randomly replacing one card until we get a solution
		while(getSolutions(this.playBoard.getPlayedCards()).size() == 0) {
			int rnd = new Random().nextInt(this.playBoard.getPlayedCards().length);
			this.playBoard.getPlayedCards()[rnd] = playDeck.dealCard();
		}
		
	}
	
	
	/**
	 * check whether there is any solution in the cards
	 * @param cardsOnBoard
	 * @return
	 */
	public static boolean containsSolution(Card[] cardsOnBoard) {
		if(getSolutions(cardsOnBoard).size() == 0) return false;
		else return true;
	}
	
	/**
	 * Generator of set solutions from the input cards
	 * @param cardsOnBoard
	 * @return
	 */
	public  static List<Set<Card>> getSolutions(Card[] cardsOnBoard) {
		List<Set<Card>> solutions = new ArrayList<Set<Card>>();
		int number = cardsOnBoard.length;

		if(cardsOnBoard[14] == null) number = 12;
		for(int i=0; i<number-2; i++) {
			for(int j=i+1; j<number-1; j++) {
				for(int k=j+1; k<number; k++) {

					if(cardsOnBoard[i] != null && cardsOnBoard[j] != null && cardsOnBoard[k] != null){
						if(containsRule(cardsOnBoard[i], cardsOnBoard[j], cardsOnBoard[k])) {
							Set<Card> sol = new HashSet<Card>();
							sol.add(cardsOnBoard[i]);
							sol.add(cardsOnBoard[j]);
							sol.add(cardsOnBoard[k]);
							solutions.add(sol);
						}
					}
				}
			}			
		}
		
		return solutions;
	}
	
	
	/**
	 * This method is the exact same as below, except it takes in an ArrayList instead of 3 separate card objects
	 * Added by ATM
	 * @param ArrayList containing the three selected card variables
	 * @return true if they are a set, false if they aren't
	 */
	public boolean containsRuleArrayInput(List<Card> selectedCardsFun) {
		Card card1 = selectedCardsFun.get(0);
		Card card2 = selectedCardsFun.get(1);
		Card card3 = selectedCardsFun.get(2);
		
		int[] featureCounts = new int[4]; // featureCounts[0] will never be used though
		
		featureCounts[getQuantityCount(card1, card2, card3)]++;
		featureCounts[getColorCount(card1, card2, card3)]++;
		featureCounts[getShapeCount(card1, card2, card3)]++;
		featureCounts[getShadingCount(card1, card2, card3)]++;

		if(featureCounts[3] == 1 && featureCounts[1] == 3) return true;
		if(featureCounts[3] == 2 && featureCounts[1] == 2) return true;
		if(featureCounts[3] == 3 && featureCounts[1] == 1) return true;
		if(featureCounts[3] == 4) return true;

		return false;
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
	
	/**
	 * methods for comparing 2 cards
	 * @param card1
	 * @param card2
	 * @return
	 */
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

	/**
	 * @return the playBoard
	 */
	public Board getPlayBoard() {
		return playBoard;
	}

	/**
	 * @param playBoard the playBoard to set
	 */
	public void setPlayBoard(Board playBoard) {
		this.playBoard = playBoard;
	}

	/**
	 * @return the playDeck
	 */
	public Deck getPlayDeck() {
		return playDeck;
	}

	/**
	 * @param playDeck the playDeck to set
	 */
	public void setPlayDeck(Deck playDeck) {
		this.playDeck = playDeck;
	}

	/**
	 * @return the playerOne
	 */
	public Player getPlayerOne() {
		return playerOne;
	}

	/**
	 * @param playerOne the playerOne to set
	 */
	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}

	/**
	 * @return the playerTwo
	 */
	public Player getPlayerTwo() {
		return playerTwo;
	}

	/**
	 * @param playerTwo the playerTwo to set
	 */
	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}
	
	public void setCurrentPlayer(int i){
		currentPlayer = i;
	}
	
}
