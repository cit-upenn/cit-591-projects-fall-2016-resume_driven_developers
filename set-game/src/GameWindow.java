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
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GameWindow extends JFrame {

	static GameWindow frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	JButton cardAButton;
	JButton cardBButton;
	JButton cardCButton;
	JButton cardDButton;
	JButton cardEButton;
	JButton cardFButton;
	JButton cardGButton;
	JButton cardHButton;
	JButton cardIButton;
	JButton cardJButton;
	JButton cardKButton;
	JButton cardLButton;
	JButton cardMButton;
	JButton cardNButton;
	JButton cardPButton;
	
	//instance variables for card images
	ImageIcon cardA;
	ImageIcon cardB;
	ImageIcon cardC;
	ImageIcon cardD;
	ImageIcon cardE;
	ImageIcon cardF;
	ImageIcon cardG;
	ImageIcon cardH;
	ImageIcon cardI;
	ImageIcon cardJ;
	ImageIcon cardK;
	ImageIcon cardL;
	ImageIcon cardM;
	ImageIcon cardN;
	ImageIcon cardP;
	ImageIcon deck;
	
	//instance variables for player stats
	JLabel player1;
	JLabel player2;
	int numPlayers;
	String player1Name;
	String player2Name;
	String player1Score;
	String player2Score;
	
	/**
	 * Create the frame.
	 */
	public GameWindow() {
		
		ruler = new GameRuler();
		
		getContentPane().setBackground(UIManager.getColor("Desktop.background"));
		setBounds(100, 100, 1200, 860);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		getContentPane().setVisible(true);
		
		cardA = new ImageIcon(GameWindow.class.getResource("/cardimages/empty_card.png"));
		cardB = new ImageIcon(GameWindow.class.getResource("/cardimages/empty_card.png"));
		cardC = new ImageIcon(GameWindow.class.getResource("/cardimages/empty_card.png"));
		cardD = new ImageIcon(GameWindow.class.getResource("/cardimages/empty_card.png"));
		cardE = new ImageIcon(GameWindow.class.getResource("/cardimages/empty_card.png"));
		cardF = new ImageIcon(GameWindow.class.getResource("/cardimages/empty_card.png"));
		cardG = new ImageIcon(GameWindow.class.getResource("/cardimages/empty_card.png"));
		cardH = new ImageIcon(GameWindow.class.getResource("/cardimages/empty_card.png"));
		cardI = new ImageIcon(GameWindow.class.getResource("/cardimages/empty_card.png"));
		cardJ = new ImageIcon(GameWindow.class.getResource("/cardimages/empty_card.png"));
		cardK = new ImageIcon(GameWindow.class.getResource("/cardimages/empty_card.png"));
		cardL = new ImageIcon(GameWindow.class.getResource("/cardimages/empty_card.png"));
		cardM = null;
		cardN = null;
		cardP = null;		
		deck = null;
		
		player1Name = "Player 1";
		player1Score = "0";
		player2Name = "Player 2";
		player2Score = "0";
		
		player1 = new JLabel(player1Name + ": " + player1Score);
		player1.setFont(new Font("Tahoma", Font.PLAIN, 38));
		player1.setBackground(Color.LIGHT_GRAY);
		player1.setOpaque(true);
		player1.setForeground(new Color(139, 0, 139));
		player1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPlayer1 = new GridBagConstraints();
		gbc_lblPlayer1.insets = new Insets(10, 10, 5, 5);
		gbc_lblPlayer1.gridx = 2;
		gbc_lblPlayer1.gridy = 1;
		gbc_lblPlayer1.gridwidth = 3;
		gbc_lblPlayer1.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(player1, gbc_lblPlayer1);
		
		JLabel clock = new JLabel("00:00");
		clock.setFont(new Font("Tahoma", Font.PLAIN, 42));
		clock.setBackground(Color.WHITE);
		clock.setOpaque(true);
		clock.setForeground(new Color(255, 0, 0));
		clock.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_clock = new GridBagConstraints();
		gbc_clock.insets = new Insets(10, 10, 5, 5);
		gbc_clock.gridx = 5;
		gbc_clock.gridy = 1;
		gbc_clock.fill = GridBagConstraints.BOTH;
		getContentPane().add(clock, gbc_clock);
		
		player2 = new JLabel(player2Name + ": " + player2Score);
		player2.setFont(new Font("Tahoma", Font.PLAIN, 38));
		player2.setBackground(Color.LIGHT_GRAY);
		player2.setForeground(new Color(0, 100, 0));
		player2.setOpaque(true);
		player2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPlayer2 = new GridBagConstraints();
		gbc_lblPlayer2.insets = new Insets(10, 10, 5, 5);
		gbc_lblPlayer2.gridx = 6;
		gbc_lblPlayer2.gridy = 1;
		gbc_lblPlayer2.gridwidth = 2;
		gbc_lblPlayer2.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(player2, gbc_lblPlayer2);
		
		howToPlay = new JButton("How To Play");
		GridBagConstraints gbc_btnHowToPlay = new GridBagConstraints();
		gbc_btnHowToPlay.insets = new Insets(0, 0, 5, 5);
		gbc_btnHowToPlay.gridx = 0;
		gbc_btnHowToPlay.gridy = 3;
		getContentPane().add(howToPlay, gbc_btnHowToPlay);
		
		cardAButton = new JButton("");
		cardAButton.setIcon(cardA);
		cardAButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardA = new GridBagConstraints();
		gbc_cardA.insets = new Insets(0, 0, 5, 5);
		gbc_cardA.gridx = 4;
		gbc_cardA.gridy = 3;
		getContentPane().add(cardAButton, gbc_cardA);
		
		cardBButton = new JButton("");
		cardBButton.setIcon(cardB);
		cardBButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardB = new GridBagConstraints();
		gbc_cardB.insets = new Insets(0, 0, 5, 5);
		gbc_cardB.gridx = 5;
		gbc_cardB.gridy = 3;
		getContentPane().add(cardBButton, gbc_cardB);
		
		cardCButton = new JButton("");
		cardCButton.setIcon(cardC);
		cardCButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardC = new GridBagConstraints();
		gbc_cardC.insets = new Insets(0, 0, 5, 5);
		gbc_cardC.gridx = 6;
		gbc_cardC.gridy = 3;
		getContentPane().add(cardCButton, gbc_cardC);
		
		onePlayerGame = new JButton("1 Player Game");
		GridBagConstraints gbc_btnPlayerGame_1 = new GridBagConstraints();
		gbc_btnPlayerGame_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlayerGame_1.gridx = 0;
		gbc_btnPlayerGame_1.gridy = 4;
		getContentPane().add(onePlayerGame, gbc_btnPlayerGame_1);
		
		cardDButton = new JButton("");
		cardDButton.setIcon(cardD);
		cardDButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardD = new GridBagConstraints();
		gbc_cardD.insets = new Insets(0, 0, 5, 5);
		gbc_cardD.gridx = 4;
		gbc_cardD.gridy = 4;
		getContentPane().add(cardDButton, gbc_cardD);
		
		cardEButton = new JButton("");
		cardEButton.setIcon(cardE);
		cardEButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardE = new GridBagConstraints();
		gbc_cardE.insets = new Insets(0, 0, 5, 5);
		gbc_cardE.gridx = 5;
		gbc_cardE.gridy = 4;
		getContentPane().add(cardEButton, gbc_cardE);
		
		cardFButton = new JButton("");
		cardFButton.setIcon(cardF);
		cardFButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardF = new GridBagConstraints();
		gbc_cardF.insets = new Insets(0, 0, 5, 5);
		gbc_cardF.gridx = 6;
		gbc_cardF.gridy = 4;
		getContentPane().add(cardFButton, gbc_cardF);
		
		addCards = new JButton("Add 3 Cards");
		GridBagConstraints gbc_btnAddCards = new GridBagConstraints();
		gbc_btnAddCards.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddCards.gridx = 8;
		gbc_btnAddCards.gridy = 4;
		getContentPane().add(addCards, gbc_btnAddCards);
		
		twoPlayerGame = new JButton("2 Player Game");
		GridBagConstraints gbc_btnPlayerGame = new GridBagConstraints();
		gbc_btnPlayerGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlayerGame.gridx = 0;
		gbc_btnPlayerGame.gridy = 5;
		getContentPane().add(twoPlayerGame, gbc_btnPlayerGame);
		
		cardGButton = new JButton("");
		cardGButton.setIcon(cardG);
		cardGButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardG = new GridBagConstraints();
		gbc_cardG.insets = new Insets(0, 0, 5, 5);
		gbc_cardG.gridx = 4;
		gbc_cardG.gridy = 5;
		getContentPane().add(cardGButton, gbc_cardG);
		
		cardHButton = new JButton("");
		cardHButton.setIcon(cardH);
		cardHButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardH = new GridBagConstraints();
		gbc_cardH.insets = new Insets(0, 0, 5, 5);
		gbc_cardH.gridx = 5;
		gbc_cardH.gridy = 5;
		getContentPane().add(cardHButton, gbc_cardH);
		
		cardIButton = new JButton("");
		cardIButton.setIcon(cardI);
		cardIButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardI = new GridBagConstraints();
		gbc_cardI.insets = new Insets(0, 0, 5, 5);
		gbc_cardI.gridx = 6;
		gbc_cardI.gridy = 5;
		getContentPane().add(cardIButton, gbc_cardI);
		
		getHint = new JButton("Get A Hint");
		GridBagConstraints gbc_btnHint = new GridBagConstraints();
		gbc_btnHint.insets = new Insets(0, 0, 5, 0);
		gbc_btnHint.gridx = 8;
		gbc_btnHint.gridy = 5;
		getContentPane().add(getHint, gbc_btnHint);
		
		quitGame = new JButton("Quit Game");
		GridBagConstraints gbc_btnQuitGame = new GridBagConstraints();
		gbc_btnQuitGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnQuitGame.gridx = 0;
		gbc_btnQuitGame.gridy = 6;
		getContentPane().add(quitGame, gbc_btnQuitGame);
		
		cardJButton = new JButton("");
		cardJButton.setIcon(cardJ);
		cardJButton.setContentAreaFilled(false);
		GridBagConstraints gbc_CardJ = new GridBagConstraints();
		gbc_CardJ.insets = new Insets(0, 0, 5, 5);
		gbc_CardJ.gridx = 4;
		gbc_CardJ.gridy = 6;
		getContentPane().add(cardJButton, gbc_CardJ);
		
		cardKButton = new JButton("");
		cardKButton.setIcon(cardK);
		cardKButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardK = new GridBagConstraints();
		gbc_cardK.insets = new Insets(0, 0, 5, 5);
		gbc_cardK.gridx = 5;
		gbc_cardK.gridy = 6;
		getContentPane().add(cardKButton, gbc_cardK);
		
		cardLButton = new JButton("");
		cardLButton.setIcon(cardL);
		cardLButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardL = new GridBagConstraints();
		gbc_cardL.insets = new Insets(0, 0, 5, 5);
		gbc_cardL.gridx = 6;
		gbc_cardL.gridy = 6;
		getContentPane().add(cardLButton, gbc_cardL);
		
		skipTurn = new JButton("Skip Turn");
		GridBagConstraints gbc_btnSkipTurn = new GridBagConstraints();
		gbc_btnSkipTurn.insets = new Insets(0, 0, 5, 0);
		gbc_btnSkipTurn.gridx = 8;
		gbc_btnSkipTurn.gridy = 6;
		getContentPane().add(skipTurn, gbc_btnSkipTurn);
		
		cardMButton = new JButton("");
		cardMButton.setIcon(cardM);
		cardMButton.setContentAreaFilled(false);
		GridBagConstraints gbc_CardM = new GridBagConstraints();
		gbc_CardM.insets = new Insets(0, 0, 5, 5);
		gbc_CardM.gridx = 4;
		gbc_CardM.gridy = 7;
		getContentPane().add(cardMButton, gbc_CardM);
		
		cardNButton = new JButton("");
		cardNButton.setIcon(cardN);
		cardNButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardN = new GridBagConstraints();
		gbc_cardN.insets = new Insets(0, 0, 5, 5);
		gbc_cardN.gridx = 5;
		gbc_cardN.gridy = 7;
		getContentPane().add(cardNButton, gbc_cardN);
		
		cardPButton = new JButton("");
		cardPButton.setIcon(cardP);
		cardPButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardP = new GridBagConstraints();
		gbc_cardP.insets = new Insets(0, 0, 5, 5);
		gbc_cardP.gridx = 6;
		gbc_cardP.gridy = 7;
		getContentPane().add(cardPButton, gbc_cardP);
		
		JLabel lblDeck = new JLabel("");
		lblDeck.setIcon(deck);
		GridBagConstraints gbc_lblDeckImages = new GridBagConstraints();
		gbc_lblDeckImages.insets = new Insets(0, 0, 0, 5);
		gbc_lblDeckImages.gridx = 5;
		gbc_lblDeckImages.gridy = 9;
		getContentPane().add(lblDeck, gbc_lblDeckImages);
		
		howToPlay.addActionListener(new generateAL());
		onePlayerGame.addActionListener(new generateAL());
		twoPlayerGame.addActionListener(new generateAL());
		addCards.addActionListener(new generateAL());
		getHint.addActionListener(new generateAL());
		skipTurn.addActionListener(new generateAL());
		quitGame.addActionListener(new generateAL());
		
		
		
	}
	
	private class generateAL implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			if(e.getSource().equals(onePlayerGame)) {
				getName(1);
				skipTurn.setEnabled(false);
				ruler.onePlayerGame();
			} else if(e.getSource().equals(twoPlayerGame)) {
				for (int i = 1; i < 3; i++){
					getName(i);
				}
				skipTurn.setEnabled(true);
				ruler.twoPlayerGame();
			} else if(e.getSource().equals(howToPlay)) {
				showRules();
			} else if (e.getSource().equals(addCards)) {
				//add 3 cards to empty row at the bottom
			} else if (e.getSource().equals(getHint)) {
				//highlight one card that is part of a set on the board
			} else if (e.getSource().equals(skipTurn)) {
				//prompt if sure
				//start other player's turn
			} else if (e.getSource().equals(quitGame)) {
				
			}
		}
	}
	
	public void getName(int i){
		if (i == 1){
		player1Name = null;
		do{
			player1Name = (String)JOptionPane.showInputDialog("Enter player one's name:\n");
		} while (player1Name == null || player1Name.length() <= 0);
		player1.setText(player1Name + ": " + player1Score);
		} else if (i == 2){
			player2Name = null;
			do{
				player2Name = (String)JOptionPane.showInputDialog("Enter player two's name:\n");
			} while (player2Name == null || player2Name.length() <= 0);
			player2.setText(player2Name + ": " + player2Score);
		}
	}
	
	private void showRules(){
		Object[] choices = {"The Basics", "1 Player Rules", "2 Player Rules"};
		int j = JOptionPane.showOptionDialog(frame, "What do you want to read?", "How To Play", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices, choices[0]);
		if (j == 0){
			JOptionPane.showMessageDialog(frame, "A SET is three cards where each feature, when looked at individually, is either all the same OR all different.\n"
					+ "Each card contains four features: color (red, purple or green), shape (oval, squiggle or diamond), number (one, two or three) and shading (solid, striped or outlined).");
			//add panel with example of set and not set
		} else if (j == 1){
			JOptionPane.showMessageDialog(frame,"1 player rules go here");
		} else if (j == 2) {
			JOptionPane.showMessageDialog(frame,"2 player rules go here");
		}
	}

}
