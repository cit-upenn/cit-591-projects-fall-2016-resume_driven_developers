import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;

public class CardGame2 extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardGame2 frame = new CardGame2();
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
	public CardGame2() {
		getContentPane().setBackground(UIManager.getColor("Desktop.background"));
		setBounds(100, 100, 1200, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblPlayer1 = new JLabel("Player 1: " + "score");
		lblPlayer1.setBackground(Color.LIGHT_GRAY);
		lblPlayer1.setOpaque(true);
		lblPlayer1.setForeground(new Color(139, 0, 139));
		GridBagConstraints gbc_lblPlayer1 = new GridBagConstraints();
		gbc_lblPlayer1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer1.gridx = 2;
		gbc_lblPlayer1.gridy = 1;
		gbc_lblPlayer1.gridwidth = 2;
		getContentPane().add(lblPlayer1, gbc_lblPlayer1);
		
//		JLabel lblScore1 = new JLabel("Score");
//		lblScore1.setBackground(Color.LIGHT_GRAY);
//		lblScore1.setOpaque(true);
//		lblScore1.setForeground(new Color(139, 0, 139));
//		GridBagConstraints gbc_lblScore1 = new GridBagConstraints();
//		gbc_lblScore1.insets = new Insets(0, 0, 5, 5);
//		gbc_lblScore1.gridx = 3;
//		gbc_lblScore1.gridy = 1;
//		gbc_lblScore1.anchor = GridBagConstraints.WEST;
//		getContentPane().add(lblScore1, gbc_lblScore1);
		
		JLabel clock = new JLabel("00:00");
		clock.setBackground(Color.WHITE);
		clock.setOpaque(true);
		clock.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_clock = new GridBagConstraints();
		gbc_clock.insets = new Insets(0, 0, 5, 5);
		gbc_clock.gridx = 4;
		gbc_clock.gridy = 1;
		getContentPane().add(clock, gbc_clock);
		
		JLabel lblPlayer2 = new JLabel("Player 2: " + "score");
		lblPlayer2.setBackground(Color.LIGHT_GRAY);
		lblPlayer2.setForeground(new Color(0, 100, 0));
		lblPlayer2.setOpaque(true);
		lblPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPlayer2 = new GridBagConstraints();
		gbc_lblPlayer2.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer2.gridx = 5;
		gbc_lblPlayer2.gridy = 1;
		gbc_lblPlayer2.gridwidth = 2;
		getContentPane().add(lblPlayer2, gbc_lblPlayer2);
		
//		JLabel lblScore2 = new JLabel("Score");
//		lblScore2.setBackground(Color.LIGHT_GRAY);
//		lblScore2.setOpaque(true);
//		lblScore2.setForeground(new Color(0, 100, 0));
//		GridBagConstraints gbc_lblScore2 = new GridBagConstraints();
//		gbc_lblScore2.insets = new Insets(0, 0, 5, 5);
//		gbc_lblScore2.gridx = 6;
//		gbc_lblScore2.gridy = 1;
//		gbc_lblScore2.anchor = GridBagConstraints.WEST;
//		getContentPane().add(lblScore2, gbc_lblScore2);
		
		JButton btnHowToPlay = new JButton("How To Play");
		GridBagConstraints gbc_btnHowToPlay = new GridBagConstraints();
		gbc_btnHowToPlay.insets = new Insets(0, 0, 5, 5);
		gbc_btnHowToPlay.gridx = 0;
		gbc_btnHowToPlay.gridy = 2;
		getContentPane().add(btnHowToPlay, gbc_btnHowToPlay);
		
		JButton btnAddCards = new JButton("Add 3 Cards");
		GridBagConstraints gbc_btnAddCards = new GridBagConstraints();
		gbc_btnAddCards.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddCards.gridx = 7;
		gbc_btnAddCards.gridy = 2;
		getContentPane().add(btnAddCards, gbc_btnAddCards);
		
		JButton btnNewButton_3 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 3;
		gbc_btnNewButton_3.gridy = 3;
		getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 4;
		gbc_btnNewButton_4.gridy = 3;
		getContentPane().add(btnNewButton_4, gbc_btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 5;
		gbc_btnNewButton_5.gridy = 3;
		getContentPane().add(btnNewButton_5, gbc_btnNewButton_5);
		
		JButton btnPlayerGame_1 = new JButton("1 Player Game");
		GridBagConstraints gbc_btnPlayerGame_1 = new GridBagConstraints();
		gbc_btnPlayerGame_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlayerGame_1.gridx = 0;
		gbc_btnPlayerGame_1.gridy = 4;
		getContentPane().add(btnPlayerGame_1, gbc_btnPlayerGame_1);
		
		JButton btnNewButton_6 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_6.gridx = 3;
		gbc_btnNewButton_6.gridy = 4;
		getContentPane().add(btnNewButton_6, gbc_btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_7.gridx = 4;
		gbc_btnNewButton_7.gridy = 4;
		getContentPane().add(btnNewButton_7, gbc_btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
		gbc_btnNewButton_8.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_8.gridx = 5;
		gbc_btnNewButton_8.gridy = 4;
		getContentPane().add(btnNewButton_8, gbc_btnNewButton_8);
		
		JButton btnHint = new JButton("Get A Hint");
		btnHint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnHint = new GridBagConstraints();
		gbc_btnHint.insets = new Insets(0, 0, 5, 0);
		gbc_btnHint.gridx = 7;
		gbc_btnHint.gridy = 4;
		getContentPane().add(btnHint, gbc_btnHint);
		
		JButton btnPlayerGame = new JButton("2 Player Game");
		GridBagConstraints gbc_btnPlayerGame = new GridBagConstraints();
		gbc_btnPlayerGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlayerGame.gridx = 0;
		gbc_btnPlayerGame.gridy = 5;
		getContentPane().add(btnPlayerGame, gbc_btnPlayerGame);
		
		JButton btnNewButton_9 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_9 = new GridBagConstraints();
		gbc_btnNewButton_9.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_9.gridx = 3;
		gbc_btnNewButton_9.gridy = 5;
		getContentPane().add(btnNewButton_9, gbc_btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_10 = new GridBagConstraints();
		gbc_btnNewButton_10.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_10.gridx = 4;
		gbc_btnNewButton_10.gridy = 5;
		getContentPane().add(btnNewButton_10, gbc_btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_11 = new GridBagConstraints();
		gbc_btnNewButton_11.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_11.gridx = 5;
		gbc_btnNewButton_11.gridy = 5;
		getContentPane().add(btnNewButton_11, gbc_btnNewButton_11);
		
		JButton btnRevealSolution = new JButton("Reveal Solution");
		GridBagConstraints gbc_btnRevealSolution = new GridBagConstraints();
		gbc_btnRevealSolution.insets = new Insets(0, 0, 5, 0);
		gbc_btnRevealSolution.gridx = 7;
		gbc_btnRevealSolution.gridy = 5;
		getContentPane().add(btnRevealSolution, gbc_btnRevealSolution);
		
		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 6;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 6;
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 5;
		gbc_btnNewButton_2.gridy = 6;
		getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnQuitGame = new JButton("Quit Game");
		GridBagConstraints gbc_btnQuitGame = new GridBagConstraints();
		gbc_btnQuitGame.insets = new Insets(0, 0, 0, 5);
		gbc_btnQuitGame.gridx = 0;
		gbc_btnQuitGame.gridy = 7;
		getContentPane().add(btnQuitGame, gbc_btnQuitGame);
		
		JButton btnSkipTurn = new JButton("Skip Turn");
		GridBagConstraints gbc_btnSkipTurn = new GridBagConstraints();
		gbc_btnSkipTurn.gridx = 7;
		gbc_btnSkipTurn.gridy = 7;
		getContentPane().add(btnSkipTurn, gbc_btnSkipTurn);
		
		

	}

}
