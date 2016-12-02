import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Dimension;

public class gameBoard extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gameBoard frame = new gameBoard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public gameBoard() {
		getContentPane().setBackground(UIManager.getColor("Desktop.background"));
		setBounds(100, 100, 1200, 860);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		ImageIcon cardA = new ImageIcon(gameBoard.class.getResource("/cardimages/Card01clkd.jpg"));
		ImageIcon cardB = new ImageIcon(gameBoard.class.getResource("/cardimages/Card02.jpg"));
		ImageIcon cardC = new ImageIcon(gameBoard.class.getResource("/cardimages/Card43clkd.jpg"));
		ImageIcon cardD = new ImageIcon(gameBoard.class.getResource("/cardimages/Card04.jpg"));
		ImageIcon cardE = new ImageIcon(gameBoard.class.getResource("/cardimages/Card25.jpg"));
		ImageIcon cardF = new ImageIcon(gameBoard.class.getResource("/cardimages/Card06.jpg"));
		ImageIcon cardG = new ImageIcon(gameBoard.class.getResource("/cardimages/Card37.jpg"));
		ImageIcon cardH = new ImageIcon(gameBoard.class.getResource("/cardimages/Card08clkd.jpg"));
		ImageIcon cardI = new ImageIcon(gameBoard.class.getResource("/cardimages/Card49.jpg"));
		ImageIcon cardJ = new ImageIcon(gameBoard.class.getResource("/cardimages/Card10clkd.jpg"));
		ImageIcon cardK = new ImageIcon(gameBoard.class.getResource("/cardimages/Card51.jpg"));
		ImageIcon cardL = new ImageIcon(gameBoard.class.getResource("/cardimages/Card12.jpg"));
		ImageIcon cardM = new ImageIcon(gameBoard.class.getResource("/cardimages/empty_card.png"));
		ImageIcon cardN = new ImageIcon(gameBoard.class.getResource("/cardimages/empty_card_clkd.png"));
		ImageIcon cardP = new ImageIcon(gameBoard.class.getResource("/cardimages/Card81.jpg"));		
		ImageIcon deck = new ImageIcon(gameBoard.class.getResource("/cardimages/deck.jpg"));
		
		JLabel lblPlayer1 = new JLabel("Player 1: " + "score");
		lblPlayer1.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblPlayer1.setBackground(Color.LIGHT_GRAY);
		lblPlayer1.setOpaque(true);
		lblPlayer1.setForeground(new Color(139, 0, 139));
		lblPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPlayer1 = new GridBagConstraints();
		gbc_lblPlayer1.insets = new Insets(10, 10, 5, 5);
		gbc_lblPlayer1.gridx = 2;
		gbc_lblPlayer1.gridy = 1;
		gbc_lblPlayer1.gridwidth = 3;
		gbc_lblPlayer1.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(lblPlayer1, gbc_lblPlayer1);
		
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
		
		JLabel lblPlayer2 = new JLabel("Player 2: " + "score");
		lblPlayer2.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblPlayer2.setBackground(Color.LIGHT_GRAY);
		lblPlayer2.setForeground(new Color(0, 100, 0));
		lblPlayer2.setOpaque(true);
		lblPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPlayer2 = new GridBagConstraints();
		gbc_lblPlayer2.insets = new Insets(10, 10, 5, 5);
		gbc_lblPlayer2.gridx = 6;
		gbc_lblPlayer2.gridy = 1;
		gbc_lblPlayer2.gridwidth = 2;
		gbc_lblPlayer2.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(lblPlayer2, gbc_lblPlayer2);
		
		JButton cardAButton = new JButton("");
		cardAButton.setIcon(cardA);
		cardAButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardA = new GridBagConstraints();
		gbc_cardA.insets = new Insets(0, 0, 5, 5);
		gbc_cardA.gridx = 4;
		gbc_cardA.gridy = 3;
		getContentPane().add(cardAButton, gbc_cardA);
		
		JButton cardBButton = new JButton("");
		cardBButton.setIcon(cardB);
		cardBButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardB = new GridBagConstraints();
		gbc_cardB.insets = new Insets(0, 0, 5, 5);
		gbc_cardB.gridx = 5;
		gbc_cardB.gridy = 3;
		getContentPane().add(cardBButton, gbc_cardB);
		
		JButton cardCButton = new JButton("");
		cardCButton.setIcon(cardC);
		cardCButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardC = new GridBagConstraints();
		gbc_cardC.insets = new Insets(0, 0, 5, 5);
		gbc_cardC.gridx = 6;
		gbc_cardC.gridy = 3;
		getContentPane().add(cardCButton, gbc_cardC);
		
		JButton btnHowToPlay = new JButton("How To Play");
		GridBagConstraints gbc_btnHowToPlay = new GridBagConstraints();
		gbc_btnHowToPlay.insets = new Insets(0, 0, 5, 5);
		gbc_btnHowToPlay.gridx = 0;
		gbc_btnHowToPlay.gridy = 4;
		getContentPane().add(btnHowToPlay, gbc_btnHowToPlay);
		
		JButton cardDButton = new JButton("");
		cardDButton.setIcon(cardD);
		cardDButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardD = new GridBagConstraints();
		gbc_cardD.insets = new Insets(0, 0, 5, 5);
		gbc_cardD.gridx = 4;
		gbc_cardD.gridy = 4;
		getContentPane().add(cardDButton, gbc_cardD);
		
		JButton cardEButton = new JButton("");
		cardEButton.setIcon(cardE);
		cardEButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardE = new GridBagConstraints();
		gbc_cardE.insets = new Insets(0, 0, 5, 5);
		gbc_cardE.gridx = 5;
		gbc_cardE.gridy = 4;
		getContentPane().add(cardEButton, gbc_cardE);
		
		JButton cardFButton = new JButton("");
		cardFButton.setIcon(cardF);
		cardFButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardF = new GridBagConstraints();
		gbc_cardF.insets = new Insets(0, 0, 5, 5);
		gbc_cardF.gridx = 6;
		gbc_cardF.gridy = 4;
		getContentPane().add(cardFButton, gbc_cardF);
		
		JButton btnAddCards = new JButton("Add 3 Cards");
		GridBagConstraints gbc_btnAddCards = new GridBagConstraints();
		gbc_btnAddCards.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddCards.gridx = 8;
		gbc_btnAddCards.gridy = 4;
		getContentPane().add(btnAddCards, gbc_btnAddCards);
		
		JButton btnPlayerGame_1 = new JButton("1 Player Game");
		GridBagConstraints gbc_btnPlayerGame_1 = new GridBagConstraints();
		gbc_btnPlayerGame_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlayerGame_1.gridx = 0;
		gbc_btnPlayerGame_1.gridy = 5;
		getContentPane().add(btnPlayerGame_1, gbc_btnPlayerGame_1);
		
		JButton cardGButton = new JButton("");
		cardGButton.setIcon(cardG);
		cardGButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardG = new GridBagConstraints();
		gbc_cardG.insets = new Insets(0, 0, 5, 5);
		gbc_cardG.gridx = 4;
		gbc_cardG.gridy = 5;
		getContentPane().add(cardGButton, gbc_cardG);
		
		JButton cardHButton = new JButton("");
		cardHButton.setIcon(cardH);
		cardHButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardH = new GridBagConstraints();
		gbc_cardH.insets = new Insets(0, 0, 5, 5);
		gbc_cardH.gridx = 5;
		gbc_cardH.gridy = 5;
		getContentPane().add(cardHButton, gbc_cardH);
		
		JButton cardIButton = new JButton("");
		cardIButton.setIcon(cardI);
		cardIButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardI = new GridBagConstraints();
		gbc_cardI.insets = new Insets(0, 0, 5, 5);
		gbc_cardI.gridx = 6;
		gbc_cardI.gridy = 5;
		getContentPane().add(cardIButton, gbc_cardI);
		
		JButton btnHint = new JButton("Get A Hint");
		btnHint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnHint = new GridBagConstraints();
		gbc_btnHint.insets = new Insets(0, 0, 5, 0);
		gbc_btnHint.gridx = 8;
		gbc_btnHint.gridy = 5;
		getContentPane().add(btnHint, gbc_btnHint);
		
		JButton btnPlayerGame = new JButton("2 Player Game");
		GridBagConstraints gbc_btnPlayerGame = new GridBagConstraints();
		gbc_btnPlayerGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlayerGame.gridx = 0;
		gbc_btnPlayerGame.gridy = 6;
		getContentPane().add(btnPlayerGame, gbc_btnPlayerGame);
		
		JButton cardJButton = new JButton("");
		cardJButton.setIcon(cardJ);
		cardJButton.setContentAreaFilled(false);
		GridBagConstraints gbc_CardJ = new GridBagConstraints();
		gbc_CardJ.insets = new Insets(0, 0, 5, 5);
		gbc_CardJ.gridx = 4;
		gbc_CardJ.gridy = 6;
		getContentPane().add(cardJButton, gbc_CardJ);
		
		JButton cardKButton = new JButton("");
		cardKButton.setIcon(cardK);
		cardKButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardK = new GridBagConstraints();
		gbc_cardK.insets = new Insets(0, 0, 5, 5);
		gbc_cardK.gridx = 5;
		gbc_cardK.gridy = 6;
		getContentPane().add(cardKButton, gbc_cardK);
		
		JButton cardLButton = new JButton("");
		cardLButton.setIcon(cardL);
		cardLButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardL = new GridBagConstraints();
		gbc_cardL.insets = new Insets(0, 0, 5, 5);
		gbc_cardL.gridx = 6;
		gbc_cardL.gridy = 6;
		getContentPane().add(cardLButton, gbc_cardL);
		
		JButton btnSkipTurn = new JButton("Skip Turn");
		GridBagConstraints gbc_btnSkipTurn = new GridBagConstraints();
		gbc_btnSkipTurn.insets = new Insets(0, 0, 5, 0);
		gbc_btnSkipTurn.gridx = 8;
		gbc_btnSkipTurn.gridy = 6;
		getContentPane().add(btnSkipTurn, gbc_btnSkipTurn);
		
		JButton btnQuitGame = new JButton("Quit Game");
		GridBagConstraints gbc_btnQuitGame = new GridBagConstraints();
		gbc_btnQuitGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnQuitGame.gridx = 0;
		gbc_btnQuitGame.gridy = 7;
		getContentPane().add(btnQuitGame, gbc_btnQuitGame);
		
		JButton cardMButton = new JButton("");
		cardMButton.setIcon(cardM);
		cardMButton.setContentAreaFilled(false);
		GridBagConstraints gbc_CardM = new GridBagConstraints();
		gbc_CardM.insets = new Insets(0, 0, 5, 5);
		gbc_CardM.gridx = 4;
		gbc_CardM.gridy = 7;
		getContentPane().add(cardMButton, gbc_CardM);
		
		JButton cardNButton = new JButton("");
		cardNButton.setIcon(cardN);
		cardNButton.setContentAreaFilled(false);
		GridBagConstraints gbc_cardN = new GridBagConstraints();
		gbc_cardN.insets = new Insets(0, 0, 5, 5);
		gbc_cardN.gridx = 5;
		gbc_cardN.gridy = 7;
		getContentPane().add(cardNButton, gbc_cardN);
		
		JButton cardPButton = new JButton("");
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
		

		
		

	}

}
