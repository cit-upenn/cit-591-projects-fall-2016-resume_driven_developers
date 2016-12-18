import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Color;
import javax.swing.UIManager;

//import GameWindow.generateAL;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GameWindow extends JFrame {

	private static GameWindow frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		javax.swing.UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 28));
		javax.swing.UIManager.put("OptionPane.buttonFont", new Font("Tahoma", Font.PLAIN, 22));

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GameWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	

	//instance variable for classes to run the game
	GameRuler ruler;

	//instance variables for game setup buttons
	JButton howToPlay;
	JButton onePlayerGame;
	JButton twoPlayerGame;
	JButton addCards;
	JButton getHint;
	JButton skipTurn;
	JButton quitGame;

	//instance variables for card buttons
	JButton[] cardButtons;
	GridBagConstraints[] gbc_cardButtons;
	Card[] cards;

	//instance variables for card images
	ImageIcon back;
	ImageIcon empty;
	ImageIcon emptyClicked;
	ImageIcon blank;

	//instance variables for player stats
	JLabel player1;
	JLabel player2;
	String player1Name;
	String player2Name;
	Player playerOne;
	Player playerTwo;
	int currentPlayer;
	//ATM: try to avoid creating these variables
//	int player1Score = 0;
//	int player2Score = 0;
//	String player1Score;
//	String player2Score;
	boolean isSinglePlayerGame;
	
	// user selected cards
	List<Card> selectedCards = new ArrayList<Card>();
	//Stores the indices of the selected cards
	ArrayList<Integer> selectedIndices = new ArrayList<Integer>();
	
	// timer fields
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm:ss");
	JLabel clock= new JLabel();
	//Have to start the time, otherwise it won't show up, so put in a value that
	//display as 59:59, which I assume is the max	
	public long seconds = 60000; // default as 60 seconds
	Timer SimpleTimer;
	
	/**
	 * Create the frame.
	 */
	public GameWindow() {
		
		// initiate and start the clock
		SimpleTimer = new Timer(1000, new ActionListener(){
			int gameOver = 1;
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if(seconds<=0) {
		        	if(gameOver == 1) {
			        	gameOver = JOptionPane.showConfirmDialog(frame, "Time out.", "", JOptionPane.CLOSED_OPTION);
		        	}
		        	return;
		        }
		    	seconds -= 1000; 
		    	clock.setText(sdf.format(seconds));
		    }
		});
		//Have to start the time, otherwise it won't show up, so start the time at 0
		SimpleTimer.start();
		
		
		//calls an instance of the GameRuler to run the game
		ruler = new GameRuler();
		
		//ATM: I think we should always access the cards on the game board through game ruler....if we have the card board in an instance
		//variable in GameWindow, we risk accessing a card board that isn't updated.
		//initialize array of 15 cards
//		cards = new Card[15];

		//creates the window pane
		getContentPane().setBackground(new Color(70, 130, 180));
		setBounds(100, 100, 1200, 860);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.5, 0.0, 0.0, 0.0, 0.5};
		gridBagLayout.rowWeights = new double[]{0.0, 0.7, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.3, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		getContentPane().setVisible(true);

		
		/*
		 * assigns images for the four non-card face images used
		 * back - back face of cards
		 * empty - for when a card in the main 12 area has been removed
		 * emptyClicked - for when a user accidentally clicks an empty card cell
		 * blank - for the bottom row that isn't always in use
		 */
		back = new ImageIcon(GameWindow.class.getResource("/cardimages/back.jpg"));
		empty = new ImageIcon(GameWindow.class.getResource("/cardimages/empty_card.png"));
		emptyClicked = new ImageIcon(GameWindow.class.getResource("/cardimages/empty_card_clkd.png"));
		blank = new ImageIcon(GameWindow.class.getResource("/cardimages/blank.png"));

		//creates the text area for player 1's name and score
		player1 = new JLabel("Player 1: " + ruler.playerOne.getScore());
		player1.setBackground(new Color(255, 255, 240));
		player1.setFont(new Font("Tahoma", Font.PLAIN, 42));
		player1.setOpaque(true);
		player1.setForeground(new Color(148, 0, 211));
		player1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPlayer1 = new GridBagConstraints();
		gbc_lblPlayer1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPlayer1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer1.gridx = 0;
		gbc_lblPlayer1.gridy = 1;
		getContentPane().add(player1, gbc_lblPlayer1);

		//creates the text area for the timer
		//JLabel clock = new JLabel("00:00");
		
		
		clock.setFont(new Font("Tahoma", Font.BOLD, 42));
		clock.setBackground(new Color(255, 255, 240));
		clock.setOpaque(true);
		clock.setForeground(new Color(255, 0, 0));
		clock.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_clock = new GridBagConstraints();
		gbc_clock.insets = new Insets(10, 10, 5, 5);
		gbc_clock.gridx = 2;
		gbc_clock.gridy = 1;
		gbc_clock.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(clock, gbc_clock);

		//creates buttons for the 15 clickable cards
		cardButtons = new JButton[15];
		gbc_cardButtons = new GridBagConstraints[15];

		for (int i = 0; i < 15; i++){
			cardButtons[i] = new JButton("");
			//Create the separate action listener for the card buttons
			cardButtons[i].addActionListener(new buttonAL());

			//this sets the first 12 cards to show the back face
			//and the last 3 to be empty
			if (i < 12){
				cardButtons[i].setIcon(back);
			} else {
				cardButtons[i].setIcon(blank);
			}

			//this makes sure nothing but the card image shows (no border, etc)
			cardButtons[i].setContentAreaFilled(false);
			cardButtons[i].setBorder(null);
			cardButtons[i].setBorderPainted(false);
			cardButtons[i].setHorizontalAlignment(SwingConstants.CENTER);
			cardButtons[i].setVerticalAlignment(SwingConstants.CENTER);
			gbc_cardButtons[i] = new GridBagConstraints();
			gbc_cardButtons[i].insets = new Insets(10, 10, 5, 5);

			//the cards are in locations (3, 1) through (7, 3)
			//this makes 5 rows and 3 columns
			//(0,0) is the upper left corner of the GridBagLayout
			if (i < 3){
				gbc_cardButtons[i].gridy = 3;
			} else if (i < 6){
				gbc_cardButtons[i].gridy = 4;
			} else if (i < 9){
				gbc_cardButtons[i].gridy = 5;
			} else if (i < 12){
				gbc_cardButtons[i].gridy = 6;
			} else {
				gbc_cardButtons[i].gridy = 7;
			}

			if (i%3 == 0){
				gbc_cardButtons[i].gridx = 1;
			} else if (i%3 == 1){
				gbc_cardButtons[i].gridx = 2;
			} else if (i%3 == 2) {
				gbc_cardButtons[i].gridx = 3;
			}

			//this add each card button to the window pane
			getContentPane().add(cardButtons[i], gbc_cardButtons[i]);

		}

		//creates the player 2 text area for name and score
		player2 = new JLabel("Player 2: " + ruler.playerTwo.getScore());
		player2.setBackground(new Color(255, 255, 240));
		player2.setFont(new Font("Tahoma", Font.PLAIN, 42));
		player2.setForeground(new Color(0, 100, 0));
		player2.setOpaque(true);
		player2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPlayer2 = new GridBagConstraints();
		gbc_lblPlayer2.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayer2.gridx = 4;
		gbc_lblPlayer2.gridy = 1;
		gbc_lblPlayer2.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(player2, gbc_lblPlayer2);

		//creates the how to play button
		howToPlay = new JButton("How To Play");
		howToPlay.setFont(new Font("Tahoma", Font.PLAIN, 28));
		GridBagConstraints gbc_btnHowToPlay = new GridBagConstraints();
		gbc_btnHowToPlay.insets = new Insets(0, 0, 5, 5);
		gbc_btnHowToPlay.gridx = 0;
		gbc_btnHowToPlay.gridy = 3;
		getContentPane().add(howToPlay, gbc_btnHowToPlay);
		howToPlay.addActionListener(new generateAL());

		//creates the 1 player game button
		onePlayerGame = new JButton("1 Player Game");
		onePlayerGame.setFont(new Font("Tahoma", Font.PLAIN, 28));
		GridBagConstraints gbc_btnPlayerGame_1 = new GridBagConstraints();
		gbc_btnPlayerGame_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlayerGame_1.gridx = 0;
		gbc_btnPlayerGame_1.gridy = 4;
		getContentPane().add(onePlayerGame, gbc_btnPlayerGame_1);
		onePlayerGame.addActionListener(new generateAL());

		//creates the add 3 cards button
		addCards = new JButton("Add 3 Cards");
		addCards.setFont(new Font("Tahoma", Font.PLAIN, 28));
		GridBagConstraints gbc_btnAddCards = new GridBagConstraints();
		gbc_btnAddCards.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddCards.gridx = 4;
		gbc_btnAddCards.gridy = 4;
		getContentPane().add(addCards, gbc_btnAddCards);
		addCards.addActionListener(new generateAL());
		addCards.setEnabled(false);

		//creates the 2 player game button
		twoPlayerGame = new JButton("2 Player Game");
		twoPlayerGame.setFont(new Font("Tahoma", Font.PLAIN, 28));
		GridBagConstraints gbc_btnPlayerGame = new GridBagConstraints();
		gbc_btnPlayerGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlayerGame.gridx = 0;
		gbc_btnPlayerGame.gridy = 5;
		getContentPane().add(twoPlayerGame, gbc_btnPlayerGame);
		twoPlayerGame.addActionListener(new generateAL());

		//creates the get a hint button
		getHint = new JButton("Get A Hint");
		getHint.setFont(new Font("Tahoma", Font.PLAIN, 28));
		GridBagConstraints gbc_btnHint = new GridBagConstraints();
		gbc_btnHint.insets = new Insets(0, 0, 5, 0);
		gbc_btnHint.gridx = 4;
		gbc_btnHint.gridy = 5;
		getContentPane().add(getHint, gbc_btnHint);
		getHint.addActionListener(new generateAL());
		getHint.setEnabled(false);

		//creates the quit game button
		quitGame = new JButton("Quit Game");
		quitGame.setFont(new Font("Tahoma", Font.PLAIN, 28));
		GridBagConstraints gbc_btnQuitGame = new GridBagConstraints();
		gbc_btnQuitGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnQuitGame.gridx = 0;
		gbc_btnQuitGame.gridy = 6;
		getContentPane().add(quitGame, gbc_btnQuitGame);
		quitGame.addActionListener(new generateAL());

		//creates the skip turn button
		skipTurn = new JButton("Skip Turn");
		skipTurn.setFont(new Font("Tahoma", Font.PLAIN, 28));
		GridBagConstraints gbc_btnSkipTurn = new GridBagConstraints();
		gbc_btnSkipTurn.insets = new Insets(0, 0, 5, 0);
		gbc_btnSkipTurn.gridx = 4;
		gbc_btnSkipTurn.gridy = 6;
		getContentPane().add(skipTurn, gbc_btnSkipTurn);
		skipTurn.addActionListener(new generateAL());
		skipTurn.setEnabled(false);

		//creates a location to display an image of the deck
		//we originally wanted to show the deck decreasing through the game
		//we may need to scrap this idea - it's harder than i expected to find image files of a stacked deck
		JLabel lblDeck = new JLabel("");
		lblDeck.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeck.setIcon(back);
		GridBagConstraints gbc_lblDeckImages = new GridBagConstraints();
		gbc_lblDeckImages.insets = new Insets(10, 10, 5, 5);
		gbc_lblDeckImages.gridx = 2;
		gbc_lblDeckImages.gridy = 8;
		getContentPane().add(lblDeck, gbc_lblDeckImages);


	}

	
	/**
	 * This is what makes the magic happen!
	 * In particular, this makes the gray buttons on the left and right
	 * of the game board actually do things.
	 * @author eherzog
	 *
	 */
	private class generateAL implements ActionListener {

		public void actionPerformed (ActionEvent e) {

			
			if(e.getSource().equals(onePlayerGame)) {
				
				//asks for and displays player 1's name
				getName(1);
				
				//sets the correct buttons to enabled or disabled
				if (player1Name != null){
					player2.setText("");
					skipTurn.setEnabled(false);
					onePlayerGame.setEnabled(false);
					twoPlayerGame.setEnabled(false);
					getHint.setEnabled(true);
					addCards.setEnabled(true);
					
					
					flipCards();
					//Use this to test the end game, throws away a bunch of cards from the deck
//					ruler.throwAwayCards();
					
					//next, play the game!
					onePlayerGame();
				}
			} else if(e.getSource().equals(twoPlayerGame)) {
				
				//ask for and display both player's names
				for (int i = 1; i < 3; i++){
					getName(i);
				}
				
				//sets the correct buttons to enabled and disabled
				if (player1Name != null && player2Name != null){
					skipTurn.setEnabled(true);
					onePlayerGame.setEnabled(false);
					twoPlayerGame.setEnabled(false);
					getHint.setEnabled(true);
					addCards.setEnabled(true);

					//deal 12 cards
					flipCards();
					
					//Throw away cards to test the endgame
					ruler.throwAwayCards();
					
					//play a 2 player game
					twoPlayerGame(player1Name, player2Name);
					
					//tell the players who will go first
					if (ruler.currentPlayer == 1){
						JOptionPane.showMessageDialog(frame, player1Name + " will go first!", "", JOptionPane.PLAIN_MESSAGE);
						player1.setText(">" + player1Name + ": " + ruler.playerOne.getScore());
					} else if (ruler.currentPlayer == 2){
						JOptionPane.showMessageDialog(frame, player2Name + " will go first!", "", JOptionPane.PLAIN_MESSAGE);
						player2.setText(">" + player2Name + ": " + ruler.playerTwo.getScore());
					}
				}
			} else if(e.getSource().equals(howToPlay)) {

				showRules();

			} else if (e.getSource().equals(addCards)) {

				addCards();

			} else if (e.getSource().equals(getHint)) {

				getHint();

			} else if (e.getSource().equals(skipTurn)) {

				skipTurn();

			} else if (e.getSource().equals(quitGame)) {

				quitGame();
			}

		}
	}
	
	
	//Takes in the action listener, and return the index of the clicked button
	private int findClickedButton(ActionEvent eInFun) {
		for(int i = 0; i <15; i++) {		
			if (eInFun.getSource().equals(this.cardButtons[i])) {
				return i;
			} 
		}
		
		//Nothing was found, return an error
		return -1;
	}
	
	/**
	 * This method updates all of the JButton cards with the appropriate pictures from the cards array, which is stored in the board object,
	 * which is stored in the game ruler object.  It is called when a player picks three wrong cards, or when new cards are added to the board
	 * and the visual display needs to be updated appropriately.
	 * @author ATM
	 */
	private void refreshBoard() {
		//Check to see if 12 or 15 cards are on the card board.
		int loopLength = 0;
		if(ruler.playBoard.getPlayedCards()[13] == null) {
			loopLength = 12;
		} else{
			loopLength = 15;
		}
		
		for (int i = 0; i < loopLength; i++){			
			if(!(ruler.playBoard.getPlayedCards()[i] == null)) {
				String filename = ruler.playBoard.getPlayedCards()[i].getImageFile();
				cardButtons[i].setIcon(new ImageIcon(GameWindow.class.getResource(filename)));
			} else {
				/**
				 * We run into this scenario in the end game, when there are still matches on the board but there are no more cards left
				 * in the deck to draw.  In this case, set the matched cards to the back.
				 */
				cardButtons[i].setIcon(blank);
			}
		}
	}
	
	
	/**
	 * This method is called when a player successfully finds a match, and increments their score appropriately.
	 * ATM
	 */
	private void incrementScore() {
		if(currentPlayer == 1) {
			ruler.playerOne.setScore(ruler.playerOne.getScore() + 10);			
		}
		else {
			ruler.playerTwo.setScore(ruler.playerTwo.getScore() + 10);
		}
		
		player1.setText(player1Name + ": " + ruler.playerOne.getScore());
		if(!isSinglePlayerGame) player2.setText(player2Name + ": " + ruler.playerTwo.getScore());
	}
	
	
	//Action listener for the card buttons: ATM
	private class buttonAL implements ActionListener {

		public void actionPerformed (ActionEvent e) {
			// TODO: pressing the buttons of cards to add
			//Cards is an instance variable of GameWindow and is an array of Card objects
			//List<Card> selectedCards = new ArrayList<Card>();
			//List<Integer> selectedIndices = new ArrayList<Integer>();
			
			//Highlight the button that's been clicked
			int clickedButtonIndex = findClickedButton(e);
			Card clickedCard = ruler.playBoard.getPlayedCards()[clickedButtonIndex];
			selectedIndices.add(clickedButtonIndex);
			String picFilePathOfClicked = clickedCard.getClickedImage();
			
			cardButtons[clickedButtonIndex].setIcon(new ImageIcon(GameWindow.class.getResource(picFilePathOfClicked)));
			selectedCards.add(ruler.playBoard.getPlayedCards()[clickedButtonIndex]);
			
			//The button we clicked is the third button
			if (selectedCards.size() > 2 ) {
				
				if (ruler.containsRuleArrayInput(selectedCards)) {
					//We found a match
					if(ruler.replacedMatchedBoardCards(selectedIndices)) {
						//No more cards or matches, so end the game.  Make sure to increment score!
						incrementScore();
						finishGameDialog();
					} else {				
						incrementScore();
//						updatePlayerScoreLabels(true, false);
						refreshBoard();						
					}					
				} else {
					System.out.println("Nope, try again");
					refreshBoard();
				}
				
				if(!isSinglePlayerGame) switchPlayer();
				selectedCards.clear();
				selectedIndices.clear();
			} 
			

		}
	}

	private void getName(int i){
		if (i == 1){
			player1Name = (String)JOptionPane.showInputDialog(frame, "Enter player one's name:\n", "", JOptionPane.PLAIN_MESSAGE);
			if (player1Name != null){
				player1.setText(player1Name + ": 0");
				player2.setText("Player 2: 0");
			}
		} else if (i == 2 && player1Name != null){
			player2Name = (String)JOptionPane.showInputDialog(frame, "Enter player two's name:\n", "", JOptionPane.PLAIN_MESSAGE);
			if (player2Name != null){
				player2.setText(player2Name + ": 0");
			}
		}
	}

	private void showRules(){
		Object[] choices = {"The Basics", "1 Player Rules", "2 Player Rules"};
		int j = JOptionPane.showOptionDialog(frame, "What do you want to read?", "How To Play", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
		if (j == 0){
			JOptionPane.showMessageDialog(frame, "A SET is three cards where each feature, when looked at individually, is either all the same OR all different.\n"
					+ "Each card contains four features: color (red, purple or green), shape (oval, squiggle or diamond), number (one, two or three) and shading (solid, striped or outlined).",
					"How To Play", JOptionPane.DEFAULT_OPTION);
			//add panel with example of set and not set
		} else if (j == 1){
			JOptionPane.showMessageDialog(frame,"1 player rules go here", "1-Player Game Rules", JOptionPane.DEFAULT_OPTION);
		} else if (j == 2) {
			JOptionPane.showMessageDialog(frame,"2 player rules go here", "2-Player Game Rules", JOptionPane.DEFAULT_OPTION);
		}
	}




	private void addCards(){
		//prompt if player is sure
		int reveal = JOptionPane.showConfirmDialog(frame, "Are you sure you want to reveal 3 cards?\nThis will result in a penalty.", "", JOptionPane.YES_NO_OPTION);

		//add 3 cards to empty row at the bottom
		if (reveal == JOptionPane.YES_OPTION){
			//add 3 cards to the board permanently 
			ruler.addThreeCardsToBoard();
			refreshBoard();

			//penalize current player
			if (ruler.currentPlayer == 1){
				ruler.playerOne.takePoints(3);
				player1.setText(">" + player1Name + ": " + ruler.playerOne.getScore());
			} else if (ruler.currentPlayer == 2){
				ruler.playerTwo.takePoints(3);
				player2.setText(">" + player2Name + ": " + ruler.playerTwo.getScore());
			}

			addCards.setEnabled(false);
			//this needs to stay disabled until there are only 12 cards on the board
		}
	}

	private void skipTurn(){
		//prompt if sure
		int skip = JOptionPane.showConfirmDialog(frame, "Are you sure you want to skip your turn?\nYour current score will not change.", "", JOptionPane.YES_NO_OPTION);

		if (skip == JOptionPane.YES_OPTION && ruler.currentPlayer == 1){
			switchPlayer();
			player2.setText(">" + player2Name + ": " + ruler.playerTwo.getScore());
			player1.setText(player1Name + ": " + ruler.playerOne.getScore());
		} else if (skip == JOptionPane.YES_OPTION && ruler.currentPlayer ==2){
			switchPlayer();
			player2.setText(player2Name + ": " + ruler.playerTwo.getScore());
			player1.setText(">" + player1Name + ": " + ruler.playerOne.getScore());
		}
		//start other player's turn
		//reset clock
	}

	private void getHint(){
		int hint = JOptionPane.showConfirmDialog(frame, "Are you sure you want to get a hint?\nThis will result in a penalty.", "", JOptionPane.YES_NO_OPTION);

		if (hint == JOptionPane.YES_OPTION){

			List<Set<Card>> solutions = ruler.getSolutions(ruler.playBoard.getPlayedCards());
			if(solutions==null) {
				return;
			}

			System.out.println("Solutions on the board: " + solutions); // should comment it
			Card hintCard =  solutions.get(0).iterator().next();
			System.out.println(hintCard);

			//ask GameRuler for the position of a card that is in a set on the board			
			for (int i=0; i< ruler.playBoard.getPlayedCards().length; i++){
				Card matched = ruler.playBoard.getPlayedCards()[i];
				if(hintCard.equal(matched)) {
					System.out.println("Found the hinted card on the board:  " + matched);
					selectedIndices.add(i);

					//ask Card for the ClickedImage filename of that Card
					String picFilePathOfClicked = matched.getClickedImage();
					
					//display the ClickedImage of that Card in the correct button position					
					cardButtons[i].setIcon(new ImageIcon(GameWindow.class.getResource(picFilePathOfClicked)));

					//set the boolean "selected" to true for that card position
					selectedCards.add(ruler.playBoard.getPlayedCards()[i]);

				}
			}

			//penalize current player
			if (ruler.currentPlayer == 1){
				ruler.playerOne.takePoints(2);
				player1.setText(">" + player1Name + ": " + ruler.playerOne.getScore());
			} else if (ruler.currentPlayer == 2){
				ruler.playerTwo.takePoints(2);
				player2.setText(">" + player2Name + ": " + ruler.playerTwo.getScore());
			}
		}
	}

	
	
	/**
	 * Once a user ends a game, either by quitting or by finishing their current game, this method is called
	 * to reset the game to its initial state.
	 * @author Andrew
	 */
	private void resetGame() {
		//if the window isn't closed, reset everything to initial conditions
		player1.setText("Player 1: 0");
		player2.setText("Player 2: 0");

		for (int i = 0; i < 15; i++){
			if (i < 12){
				cardButtons[i].setIcon(back);
			} else {
				cardButtons[i].setIcon(blank);
			}
		}
		
		//Create a new game ruler object
		ruler = new GameRuler();

		onePlayerGame.setEnabled(true);
		twoPlayerGame.setEnabled(true);
		skipTurn.setEnabled(false);
		getHint.setEnabled(false);
		addCards.setEnabled(false);
		player1.setBackground(new Color(255, 255, 240));
		player2.setBackground(new Color(255, 255, 240));
		//reset clock to 00:00
		//end other game stuff
	}
	
	/**
	 * This method presents a dialog box to the user when they finish a game, and asks them if they
	 * want to play again or continue
	 */
	private void finishGameDialog(){
		int endGame = -1;
		if(isSinglePlayerGame) {
			String endGameMessageString = "You finished the game!  Your final score is: " + ruler.getPlayerOne().getScore() +
					".\n  Would you like to play again?\nClick 'Yes' to start a new game or 'No' to quit.";
			endGame = JOptionPane.showConfirmDialog(frame, endGameMessageString, "", JOptionPane.YES_NO_OPTION);
		} else {
			if(ruler.getPlayerOne().getScore()>ruler.getPlayerTwo().getScore()) {
				String endGameMessageString = "The game is over! " + ruler.getPlayerOne().getName() + "wins\nby a score of " +
						ruler.getPlayerOne().getScore() + " to " + ruler.getPlayerTwo().getScore() + ".\n"
								+ "Would you like to play again?\nClick 'Yes' to start a new game or 'No' to quit.";
				endGame = JOptionPane.showConfirmDialog(frame, endGameMessageString, "", JOptionPane.YES_NO_OPTION);
			} else if (ruler.getPlayerOne().getScore() < ruler.getPlayerTwo().getScore()) {
				String endGameMessageString = "The game is over! " + ruler.getPlayerTwo().getName() + "wins\nby a score of " +
						ruler.getPlayerTwo().getScore() + " to " + ruler.getPlayerOne().getScore() + ".\n"
								+ "Would you like to play again?\nClick 'Yes' to start a new game or 'No' to quit.";
				endGame = JOptionPane.showConfirmDialog(frame, endGameMessageString, "", JOptionPane.YES_NO_OPTION);
			} else {
				String endGameMessageString = "Nice Game! The result is a tie: " + ruler.getPlayerOne().getScore() + " to " +
						ruler.getPlayerTwo().getScore() + ".\n" + 
						"Would you like to play again?\nClick 'Yes' to start a new game or 'No' to quit.";
				endGame = JOptionPane.showConfirmDialog(frame, endGameMessageString, "", JOptionPane.YES_NO_OPTION);
			}
		}
		
		//add 3 cards to empty row at the bottom
		if (endGame == JOptionPane.YES_OPTION){
			resetGame();			
		} else {
			frame.setVisible(false);
			System.exit(0);
		}
	}
	
	private void quitGame(){
		
		int endGame = JOptionPane.showConfirmDialog(frame, "Are you sure you want to end this game?", "", JOptionPane.YES_NO_OPTION);
	
		//add 3 cards to empty row at the bottom
		if (endGame == JOptionPane.YES_OPTION){
			int exitWindow = JOptionPane.showConfirmDialog(frame, "Would you like to close the game window?", "", JOptionPane.YES_NO_OPTION);

			if (exitWindow == JOptionPane.YES_OPTION){
				frame.setVisible(false);
				System.exit(0);
			} else {
				resetGame();				
			}

		}
	}
	
	
	/**
	 * At the start of the game, changes the button images from the back to the front of the cards.
	 * @author Andrew
	 */
	private void flipCards() {
		System.out.println("\nNew Board:");		
		//Check to see if 12 or 15 cards are on the card board, just in case
		int loopLength = 0;
		if(ruler.playBoard.getPlayedCards()[13] == null) {
			loopLength = 12;
		} else{
			loopLength = 15;
		}
		
		for (int i = 0; i < loopLength; i++){
			if(i%3 == 0) System.out.print("\n");
			String filename = ruler.playBoard.getPlayedCards()[i].getImageFile();
			cardButtons[i].setIcon(new ImageIcon(GameWindow.class.getResource(filename)));
			System.out.print(ruler.playBoard.getPlayedCards()[i].toString() + ",  ");
		}
		System.out.println("");
	}
	
	public void onePlayerGame(){
		playerOne = new Player();
		currentPlayer = 1;
		isSinglePlayerGame = true;
		
		//initialize timer
//		SimpleTimer.start();
		seconds = 10000;		
		for(JButton jb:cardButtons){
			jb.setEnabled(true);
		}
	}

	public void twoPlayerGame(String player1Name, String player2Name){
		playerOne = new Player();
		playerOne.setName(player1Name);
		playerTwo = new Player();
		playerTwo.setName(player2Name);
		
		Random rand = new Random();
		if (rand.nextBoolean()){
			currentPlayer = 1;
			player1.setBackground(new Color(222,30,30));
		} else {
			currentPlayer = 2;
			player2.setBackground(new Color(222,30,30));
		}
		
		//get players' names
		
		//randomly select which player goes first
		
		//deal 12 cards
		
		//initialize timer
		seconds = 20000;
		
		//listen for 3 clicked cards
		
		//pause timer & check if selected cards are a set
		SimpleTimer.stop();
		
		//if set, add points to current player & switch players
		
		//if not set, tell player & resume timer
	}
	
	public void switchPlayer(){
		if (currentPlayer == 1){
			currentPlayer = 2;
			//Change the background colors to reflect the player
			player2.setBackground(new Color(222,30,30));
			player1.setBackground(new Color(255, 255, 240));
		} else if (currentPlayer == 2){
			currentPlayer = 1;
			player1.setBackground(new Color(222,30,30));
			player2.setBackground(new Color(255, 255, 240));
		}
	}
}
