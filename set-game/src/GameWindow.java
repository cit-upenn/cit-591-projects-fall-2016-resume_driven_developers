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
	ImageIcon blank;

	//instance variables for player stats
	JLabel player1;
	JLabel player2;
	//	String ruler.playerOne.getName();
	//	String ruler.playerTwo.getName();
	//	Player playerOne;
	//	Player playerTwo;
	//	int currentPlayer;

	//ATM: try to avoid creating these variables
	//	int player1Score = 0;
	//	int player2Score = 0;
	//	String player1Score;
	//	String player2Score;
	boolean isSinglePlayerGame;

	// user selected cards & their indices on the board
	List<Card> selectedCards = new ArrayList<Card>();
	ArrayList<Integer> selectedIndices = new ArrayList<Integer>();

	//   fields
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm:ss");
	JLabel clock= new JLabel();
	//Have to start the time, otherwise it won't show up, so put in a value that
	//display as 59:59, which I assume is the max	
	public long seconds = 90000; // default as 60 seconds
	Timer SimpleTimer = new Timer(1000, new ActionListener(){
		int gameOver = 1;
		@Override
		public void actionPerformed(ActionEvent e) {
			if(seconds<=0) {
				if(gameOver == 1 && isSinglePlayerGame) {
					gameOver = JOptionPane.showConfirmDialog(frame, "Time is up.\nGAME OVER.", "", JOptionPane.CLOSED_OPTION);
					for (int i = 0; i < 15; i++){
						cardButtons[i].setEnabled(false);
					}
				} else if (!isSinglePlayerGame){
					JOptionPane.showConfirmDialog(frame, "Time is up!\nYour turn is over.", "", JOptionPane.CLOSED_OPTION);
					switchPlayer();
				}
				return;
			}
			seconds -= 1000; 
			clock.setText(sdf.format(seconds));
		}
	});

	/**
	 * Create the frame.
	 */
	public GameWindow() {

		//calls an instance of the GameRuler to run the game
		ruler = new GameRuler();

		//creates the window pane
		getContentPane().setBackground(new Color(70, 130, 180));
		//	    setContentPane(new JLabel(new ImageIcon("/cardimages/back.png")));

		setBounds(100, 100, 1200, 860);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.5, 0.0, 0.0, 0.0, 0.5};
		gridBagLayout.rowWeights = new double[]{0.0, 0.7, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.3, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		getContentPane().setVisible(true);


		/*
		 * assigns images for the four non-card face images used
		 * back - back face of cards
		 * blank - for the bottom row that isn't always in use
		 */
		back = new ImageIcon(GameWindow.class.getResource("/cardimages/back.jpg"));
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
			cardButtons[i].setEnabled(false);
			if (i < 12){
				cardButtons[i].setDisabledIcon(back);
			}
			//set the first 12 cards to show the back face
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
				if (ruler.playerOne.getName() != null){
					player2.setText("");
					skipTurn.setEnabled(false);
					onePlayerGame.setEnabled(false);
					twoPlayerGame.setEnabled(false);
					getHint.setEnabled(true);
					addCards.setEnabled(true);

//					System.out.println(ruler.playerOne.getName());


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
				skipTurn.setEnabled(true);
				onePlayerGame.setEnabled(false);
				twoPlayerGame.setEnabled(false);
				getHint.setEnabled(true);
				addCards.setEnabled(true);

				//deal 12 cards
				flipCards();

				//Throw away cards to test the endgame
				//					ruler.throwAwayCards();

				//play a 2 player game
				twoPlayerGame();

				//tell the players who will go first


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
			if (!addCards.isEnabled()){
				for (int j = 12; j < 15; j++){
					cardButtons[j].setIcon(blank);
					cardButtons[j].setEnabled(false);
				}
			}

		} else{
			loopLength = 15;
		}

		for (int i = 0; i < loopLength; i++){			
			if(!(ruler.playBoard.getPlayedCards()[i] == null)) {
				String filename = ruler.playBoard.getPlayedCards()[i].getImageFile();
				cardButtons[i].setIcon(new ImageIcon(GameWindow.class.getResource(filename)));
			} else {
				/*
				 * We run into this scenario in the end game, when there are still matches on the board but there are no more cards left
				 * in the deck to draw.  In this case, set the matched cards to the back.
				 */
				cardButtons[i].setIcon(blank);
				cardButtons[i].setEnabled(false);
			}
		}
	}


	/**
	 * This method is called when a player successfully finds a match, and increments their score appropriately.
	 * ATM
	 */
	private void incrementScore() {
		if (isSinglePlayerGame){
			ruler.playerOne.addPoints(6);
		} else{
			if(ruler.currentPlayer == 1) {
				if (seconds > 60000){
					ruler.playerOne.addPoints(10);	
				} else if (seconds <= 60000 && seconds > 30000){
					ruler.playerOne.addPoints(8);	
				} else if (seconds < 30000){
					ruler.playerOne.addPoints( 6);	
				}

			}
			else {
				if (seconds > 60000){
					ruler.playerTwo.addPoints(10);
				} else if (seconds <= 60000 && seconds > 30000){
					ruler.playerTwo.addPoints(8);	
				} else if (seconds < 30000){
					ruler.playerTwo.addPoints(6);	
				}
			}

		}

		player1.setText(ruler.playerOne.getName() + ": " + ruler.playerOne.getScore());
		if(!isSinglePlayerGame) player2.setText(ruler.playerTwo.getName() + ": " + ruler.playerTwo.getScore());

	}


	/**
	 * Action listener for the card buttons: ATM 
	 * 
	 *
	 */
	private class buttonAL implements ActionListener {

		public void actionPerformed (ActionEvent e) {
			//Cards is an instance variable of GameWindow and is an array of Card objects

			// Highlight the button that's been clicked
			int clickedButtonIndex = findClickedButton(e);
			Card clickedCard = ruler.playBoard.getPlayedCards()[clickedButtonIndex];
			selectedIndices.add(clickedButtonIndex);
			String picFilePathOfClicked = clickedCard.getClickedImage();

			cardButtons[clickedButtonIndex].setIcon(new ImageIcon(GameWindow.class.getResource(picFilePathOfClicked)));
			selectedCards.add(ruler.playBoard.getPlayedCards()[clickedButtonIndex]);

			if (selectedCards.size() == 1 && isSinglePlayerGame){
				player2.setVisible(false);
			}

			//The button we clicked is the third button
			if (selectedCards.size() > 2 ) {

				if (ruler.containsRuleArrayInput(selectedCards)) {
					//We found a match
					if (ruler.playBoard.getPlayedCards()[13] == null) {
						if(ruler.replacedMatchedBoardCards(selectedIndices)) {
							//No more cards or matches, so end the game.  Make sure to increment score!
							incrementScore();
							finishGameDialog();
						} else {
							if (isSinglePlayerGame){
								player2.setVisible(true);
								player2.setText("Awesome!!");
							}
							incrementScore();
							//						updatePlayerScoreLabels(true, false);
							refreshBoard();	

							if(!isSinglePlayerGame) switchPlayer();

							selectedCards.clear();
							selectedIndices.clear();
						}
					} else {
						if (ruler.shiftedMatchedBoardCards(selectedIndices)){
							incrementScore();
							finishGameDialog();
						} else {
							if (isSinglePlayerGame){
								player2.setVisible(true);
								player2.setText("Awesome!!");
							}
							incrementScore();
							//						updatePlayerScoreLabels(true, false);
							refreshBoard();	

							selectedCards.clear();
							selectedIndices.clear();
						}
					}
				} else {
					SimpleTimer.stop();
					JOptionPane.showMessageDialog(frame,"Nope! That is not a set.", "", JOptionPane.DEFAULT_OPTION);
					SimpleTimer.restart();
					selectedCards.clear();
					selectedIndices.clear();
					refreshBoard();
				}


			} 


		}
	}

	// for JUnit
	void setNameHelper() {
		ruler.playerOne.setName((String)JOptionPane.showInputDialog(frame, "Enter player one's name:\n", "", JOptionPane.PLAIN_MESSAGE));
	}

	void setNameHelper2() {
		ruler.playerTwo.setName((String)JOptionPane.showInputDialog(frame, "Enter player one's name:\n", "", JOptionPane.PLAIN_MESSAGE));
	}

	private void getName(int i){
		if (i == 1){
			setNameHelper();
			//			ruler.playerOne.setName((String)JOptionPane.showInputDialog(frame, "Enter player one's name:\n", "", JOptionPane.PLAIN_MESSAGE));
			if (ruler.playerOne.getName() != null){
				player1.setText(ruler.playerOne.getName() + ": 0");
				player2.setText("Player 2: 0");
			}
		} else if (i == 2 && ruler.playerOne.getName() != null){
			setNameHelper2();
			//			ruler.playerTwo.setName((String)JOptionPane.showInputDialog(frame, "Enter player two's name:\n", "", JOptionPane.PLAIN_MESSAGE));
			if (ruler.playerTwo.getName() != null){
				player2.setText(ruler.playerTwo.getName() + ": 0");
			}
		}
	}

	int showRuleOptionDialog() {
		Object[] choices = {"The Basics", "1 Player Rules", "2 Player Rules", "Special Features"};
		return JOptionPane.showOptionDialog(frame, "What do you want to read?", "How To Play", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
	}

	int showBasicRules1(){
		int input = -1;

		ImageIcon cardExample = new ImageIcon(GameWindow.class.getResource("/cardimages/cardExample.png"));
		Object[] choices = {"Close", "Next Page"};
		input = JOptionPane.showOptionDialog(frame, "Each card contains four features:\n"
				+ "~shape (oval, squiggle or diamond)\n~color (red, purple or green)\n~number (one, two or three)\n~shading (solid, striped or outlined)",
				"How To Play", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, cardExample, choices, choices[1]);
		if (input == 1){
			showBasicRules2();
		}
		return input;
	}

	int showBasicRules2(){
		int input = -1;
		ImageIcon setExample = new ImageIcon(GameWindow.class.getResource("/cardimages/setExample.png"));
		Object[] choices = {"Previous Page", "Close", "Next Page"};
		input = JOptionPane.showOptionDialog(frame, "Each row in this picture is a set.\n\n"
				+ "In the first row, the colors, shapes, and quanities\nare all the same, while the shadings are all different."
				+ "\n\nIn the middle row, the cards are completely different.\nNone of the characteristics are repeated."
				+ "\n\nIn the last row, the shading is all the same,\nbut the colors, quantities, and shapes are all different.",
				"How To Play", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, setExample, choices, choices[2]);
		if (input == 2){
			showBasicRules3();
		} else if (input == 0){
			showBasicRules1();
		}
		return input;
	}

	int showBasicRules3(){
		int input = -1;

		ImageIcon setExample = new ImageIcon(GameWindow.class.getResource("/cardimages/summaryExamples.png"));
		Object[] choices = {"Previous Page", "Close"};
		input = JOptionPane.showOptionDialog(frame, "",
				"How To Play", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, setExample, choices, choices[1]);
		if (input == 0){
			showBasicRules2();
		}
		return input; 
	}
	
	private void showOnePlayerRules(){
		JOptionPane.showMessageDialog(frame,"In a one-player game, your goal is to find as many sets as possible in 5 minutes.\n"
				+ "Each set you find is worth 6 points.\n\n"
				+ "See the Special Features explanation for information on getting hints.", 
				"1-Player Game Rules", JOptionPane.DEFAULT_OPTION);
	}
	
	private void showTwoPlayerRules(){
		JOptionPane.showMessageDialog(frame,"In a two-player game, players take turns finding a set on the board.\n"
		+ "Your turn lasts for 90 seconds. The faster you identify a set, the more points you will earn.\n\n"
		+ "You earn 10 points if you find a set in less than 30 seconds\n"
		+ "You earn 8 points if you find a set in more than 30 seconds, but less than 1 minute.\n"
		+ "You earn 6 points if you find a set in more than 1 minute.\n\n"
		+ "See Special Features to learn about hints, adding extra cards, and skipping your turn.", 
		"2-Player Game Rules", JOptionPane.DEFAULT_OPTION);
	}
	
	private void showSpecialFeatures(){
		JOptionPane.showMessageDialog(frame, "You may get a hint, but it will cost you 2 points!\n"
				+ "The hint will select a card that is part of a set on the board.\n"
				+ "Then, all you have to do is find the other 2 cards that complete the set.\n\n"
				+ "You can also add 3 cards to the board. This comes with a 3-point penalty.\n"
				+ "Having 15 cards on the board significantly increases the chances of a set occurring.\n\n"
				+ "In a two-player game, you may skip your turn. This has no affect on your score.\n",
				"Special Features", JOptionPane.DEFAULT_OPTION);
	}

	private void showRules(){
		int j = showRuleOptionDialog();

		if (j == 0){
			int k = showBasicRules1();
		} else if (j == 1){
			showOnePlayerRules();
		} else if (j == 2) {
			showTwoPlayerRules();
		} else if (j == 3){
			showSpecialFeatures();
		}
	}

	int showConfirmAddCardDialog() {
		return JOptionPane.showConfirmDialog(frame, "Are you sure you want to reveal 3 cards?\nThis will result in a penalty.", "", JOptionPane.YES_NO_OPTION);
	}

	private void addCards(){

		if (isSinglePlayerGame){
			player2.setVisible(false);
		}	

		//prompt if player is sure
		int reveal = showConfirmAddCardDialog();
		//		int reveal = JOptionPane.showConfirmDialog(frame, "Are you sure you want to reveal 3 cards?\nThis will result in a penalty.", "", JOptionPane.YES_NO_OPTION);

		//add 3 cards to empty row at the bottom
		if (reveal == JOptionPane.YES_OPTION){

			//add 3 cards to the board permanently 
			ruler.addThreeCardsToBoard();
			refreshBoard();

			//penalize current player
			if (ruler.currentPlayer == 1){
				ruler.playerOne.takePoints(3);
				player1.setText(ruler.playerOne.getName() + ": " + ruler.playerOne.getScore());
			} else if (ruler.currentPlayer == 2){
				ruler.playerTwo.takePoints(3);
				player2.setText(ruler.playerTwo.getName() + ": " + ruler.playerTwo.getScore());
			}

			// only able when 12 cards on the board
			addCards.setEnabled(false);
		}
	}

	private void skipTurn(){
		SimpleTimer.stop();
		//prompt if sure
		int skip = JOptionPane.showConfirmDialog(frame, "Are you sure you want to skip your turn?\nYour current score will not change.", "", JOptionPane.YES_NO_OPTION);

		if (skip == JOptionPane.YES_OPTION && ruler.currentPlayer == 1){
			switchPlayer();
			player2.setText(ruler.playerTwo.getName() + ": " + ruler.playerTwo.getScore());
			player1.setText(ruler.playerOne.getName() + ": " + ruler.playerOne.getScore());
		} else if (skip == JOptionPane.YES_OPTION && ruler.currentPlayer ==2){
			switchPlayer();
			player2.setText(ruler.playerTwo.getName() + ": " + ruler.playerTwo.getScore());
			player1.setText(ruler.playerOne.getName() + ": " + ruler.playerOne.getScore());
		}
		//TODO: start other player's turn
		//reset clock
		if (skip == JOptionPane.NO_OPTION){
			SimpleTimer.restart();
		}
	}


	int showConfirmHintDialog() {
		return JOptionPane.showConfirmDialog(frame, "Are you sure you want to get a hint?\nThis will result in a penalty.", "", JOptionPane.YES_NO_OPTION);
	}

	private Card getHint(){


		if (isSinglePlayerGame){
			player2.setVisible(false);
		}

		SimpleTimer.stop();

		Card matched = null;

		if (showConfirmHintDialog() == JOptionPane.YES_OPTION){

			getHint.setEnabled(false);
			List<Set<Card>> solutions = ruler.getSolutions(ruler.playBoard.getPlayedCards());
			if(solutions==null) {
				JOptionPane.showConfirmDialog(frame, "Sorry, no solution!", "", JOptionPane.YES_NO_OPTION);
				return null;
			}

			System.out.println("Solutions on the board: " + solutions);
			Card hintCard =  solutions.get(0).iterator().next();
			System.out.println(hintCard);

			// find the position of a card of a solution set			
			for (int i=0; i< ruler.playBoard.getPlayedCards().length; i++){
				matched = ruler.playBoard.getPlayedCards()[i];
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

			// penalize current player for 2 pts
			if (ruler.currentPlayer == 1){
				ruler.playerOne.takePoints(2);
				player1.setText(ruler.playerOne.getName() + ": " + ruler.playerOne.getScore());
			} else if (ruler.currentPlayer == 2){
				ruler.playerTwo.takePoints(2);
				player2.setText(ruler.playerTwo.getName() + ": " + ruler.playerTwo.getScore());
			}
		}
		SimpleTimer.restart();

		return matched;
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
			cardButtons[i].setEnabled(false);
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
		SimpleTimer.stop();
		clock.setText("00:00");
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
				String endGameMessageString = "The game is over! " + ruler.getPlayerOne().getName() + " wins\nby a score of " +
						ruler.getPlayerOne().getScore() + " to " + ruler.getPlayerTwo().getScore() + ".\n"
						+ "Would you like to play again?\nClick 'Yes' to start a new game or 'No' to quit.";
				endGame = JOptionPane.showConfirmDialog(frame, endGameMessageString, "", JOptionPane.YES_NO_OPTION);
			} else if (ruler.getPlayerOne().getScore() < ruler.getPlayerTwo().getScore()) {
				String endGameMessageString = "The game is over! " + ruler.getPlayerTwo().getName() + " wins\nby a score of " +
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


	int showConfirmQuitDialog() {
		return JOptionPane.showConfirmDialog(frame, "Are you sure you want to end this game?", "", JOptionPane.YES_NO_OPTION);
	}
	int showConfirmExitDialog() {
		return JOptionPane.showConfirmDialog(frame, "Would you like to close the game window?", "", JOptionPane.YES_NO_OPTION);
	}

	private void quitGame(){

		int endGame = showConfirmQuitDialog();
		//		int endGame = JOptionPane.showConfirmDialog(frame, "Are you sure you want to end this game?", "", JOptionPane.YES_NO_OPTION);

		SimpleTimer.stop();



		//add 3 cards to empty row at the bottom
		if (endGame == JOptionPane.YES_OPTION){
			int exitWindow = showConfirmExitDialog();
			//			int exitWindow = JOptionPane.showConfirmDialog(frame, "Would you like to close the game window?", "", JOptionPane.YES_NO_OPTION);

			if (exitWindow == JOptionPane.YES_OPTION){
				frame.setVisible(false);
				System.exit(0);
			} 
			else {
				resetGame();				
			}

		} else if (endGame == JOptionPane.NO_OPTION){
			SimpleTimer.restart();
		}
	}


	/**
	 * At the start of the game, changes the button images from the back to the front of the cards.
	 * @author Andrew
	 */
	private void flipCards() {
//		System.out.println("\nNew Board:");		
		//Check to see if 12 or 15 cards are on the card board, just in case
		int loopLength = 0;
		if(ruler.playBoard.getPlayedCards()[13] == null) {
			loopLength = 12;
		} else{
			loopLength = 15;
		}

		for (int i = 0; i < loopLength; i++){
//			if(i%3 == 0) System.out.print("\n");
			String filename = ruler.playBoard.getPlayedCards()[i].getImageFile();
			cardButtons[i].setIcon(new ImageIcon(GameWindow.class.getResource(filename)));
//			System.out.print(ruler.playBoard.getPlayedCards()[i].toString() + ",  ");
		}
//		System.out.println("");
	}

	public void onePlayerGame(){
		ruler.currentPlayer = 1;
		isSinglePlayerGame = true;

		//initialize timer
		seconds = 300000;		
		for(JButton jb:cardButtons){
			jb.setEnabled(true);
		}

		//start the clock
		SimpleTimer.start();
	}

	public void twoPlayerGame(){

		Random rand = new Random();
		if (rand.nextBoolean()){
			ruler.setCurrentPlayer(1);
			player1.setBackground(new Color(255, 255, 0));
		} else {
			ruler.setCurrentPlayer(2);
			player2.setBackground(new Color(255, 255, 0));
		}

		if (ruler.currentPlayer == 1){
			JOptionPane.showMessageDialog(frame, ruler.playerOne.getName() + " will go first!", "", JOptionPane.PLAIN_MESSAGE);
			player1.setText(ruler.playerOne.getName() + ": " + ruler.playerOne.getScore());
		} else if (ruler.currentPlayer == 2){
			player2.setVisible(true);
			JOptionPane.showMessageDialog(frame, ruler.playerTwo.getName() + " will go first!", "", JOptionPane.PLAIN_MESSAGE);
			player2.setText(ruler.playerTwo.getName() + ": " + ruler.playerTwo.getScore());
		}

		//get players' names

		//randomly select which player goes first


		//enable card buttons
		for(JButton jb:cardButtons){
			jb.setEnabled(true);
		}


		//initialize timer
		seconds = 90000;
		SimpleTimer.start();

		//listen for 3 clicked cards

		//pause timer & check if selected cards are a set

		//if set, add points to current player & switch players

		//if not set, tell player & resume timer
	}

	public void switchPlayer(){

		getHint.setEnabled(true);

		if (ruler.currentPlayer == 1){
			ruler.setCurrentPlayer(2);

			//Change the background colors to reflect the player
			player2.setBackground(new Color(255, 255, 0));
			player1.setBackground(new Color(255, 255, 240));
			seconds = 90000;
			SimpleTimer.start();

		} else if (ruler.currentPlayer == 2){
			ruler.setCurrentPlayer(1);
			player1.setBackground(new Color(255, 255, 0));
			player2.setBackground(new Color(255, 255, 240));
			seconds = 90000;
			SimpleTimer.start();
		}
	}
}
