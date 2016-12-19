import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Test;


/**
 * test cases for GUI Class
 * @author YPLin
 *
 */
public class GameWindowTest {


	@Test
	public void testTimer() {
		GameWindow gw = new GameWindow(){

		};
		gw.SimpleTimer.start();
	}
	
	@Test
	public void testShowRules1() {
		GameWindow gw = new GameWindow(){
			
			int showRuleOptionDialog() {
				return 0;
			}
		};
		gw.howToPlay.setEnabled(true);
		gw.howToPlay.doClick();
	}
	
	@Test
	public void testShowRules2() {
		GameWindow gw = new GameWindow(){

			int showRuleOptionDialog() {
				return 1;
			}
		};
		gw.howToPlay.setEnabled(true);
		gw.howToPlay.doClick();
	}	
	
	@Test
	public void testShowRules3() {
		GameWindow gw = new GameWindow(){


			int showRuleOptionDialog() {
				return 2;
			}
		};
		gw.howToPlay.setEnabled(true);
		gw.howToPlay.doClick();
	}	
	
	@Test
	public void testOnePlayerAddCard() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}			

			int showConfirmAddCardDialog() {
				return 0;
			}
		};
		gw.onePlayerGame.setEnabled(true);
		gw.onePlayerGame.doClick();
		
		gw.addCards.setEnabled(true);
		gw.addCards.doClick();
		
		assertTrue(gw.ruler.playBoard.getPlayedCards().length == 15);
	}
	
	@Test
	public void testOnePlayerClickACard() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}			

			int showConfirmAddCardDialog() {
				return 0;
			}
		};
		gw.onePlayerGame.setEnabled(true);
		gw.onePlayerGame.doClick();
		
		gw.cardButtons[0].setEnabled(true);
		gw.cardButtons[0].doClick();

		assertTrue(gw.selectedIndices.contains(0) == true);	
	}	

	@Test
	public void testOnePlayerClickThreeCards() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}			

			int showConfirmAddCardDialog() {
				return 0;
			}
		};
		gw.onePlayerGame.setEnabled(true);
		gw.onePlayerGame.doClick();
		
		gw.cardButtons[0].setEnabled(true);
		gw.cardButtons[0].doClick();
		gw.cardButtons[1].setEnabled(true);
		gw.cardButtons[1].doClick();
		gw.cardButtons[2].setEnabled(true);
		gw.cardButtons[2].doClick();
		
		System.out.println(gw.selectedCards);
		assertTrue(gw.selectedCards.size() == 3 || gw.selectedCards.size() == 0);	
	}	
	
	@Test
	public void testOnePlayerRefreshBoard() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}			
		};
		gw.onePlayerGame.setEnabled(true);
		gw.onePlayerGame.doClick();
		
		gw.addCards.setEnabled(false);
		gw.refreshBoard();
		
		assertTrue(gw.ruler.playBoard.getPlayedCards().length == 15);
	}	
	
	@Test
	public void testOnePlayerAddPoints1() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}			
		};
		gw.onePlayerGame.setEnabled(true);
		gw.onePlayerGame.doClick();
		
		
		gw.incrementScore();
		
		assertTrue(gw.ruler.playerOne.getScore() == 10);
	}	

	@Test
	public void testOnePlayerAddPoints2() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}			
		};
		gw.onePlayerGame.setEnabled(true);
		gw.onePlayerGame.doClick();
		
		gw.seconds = 40000;
		gw.incrementScore();
		
		assertTrue(gw.ruler.playerOne.getScore() == 8);
	}	

	@Test
	public void testOnePlayerAddPoints3() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}			
		};
		gw.onePlayerGame.setEnabled(true);
		gw.onePlayerGame.doClick();
		
		gw.seconds = 20000;
		gw.incrementScore();
		
		assertTrue(gw.ruler.playerOne.getScore() == 6);
	}

	@Test
	public void testTwoPlayerAddPoints1() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}			
			void setNameHelper2(){
				ruler.playerTwo.setName("tester2");
			}
		};
		gw.twoPlayerGame.setEnabled(true);
		gw.twoPlayerGame.doClick();
		
		gw.ruler.currentPlayer = 2;
		gw.incrementScore();
		
		assertTrue(gw.ruler.playerTwo.getScore() == 10);
	}	

	@Test
	public void testTwoPlayerAddPoints2() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}			
			void setNameHelper2(){
				ruler.playerTwo.setName("tester2");
			}
		};
		gw.twoPlayerGame.setEnabled(true);
		gw.twoPlayerGame.doClick();
		
		gw.ruler.currentPlayer = 2;
		gw.seconds = 40000;
		gw.incrementScore();
		
		assertTrue(gw.ruler.playerTwo.getScore() == 8);
	}	

	@Test
	public void testTwoPlayerAddPoints3() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}			
			void setNameHelper2(){
				ruler.playerTwo.setName("tester2");
			}
		};
		gw.twoPlayerGame.setEnabled(true);
		gw.twoPlayerGame.doClick();
		
		gw.ruler.currentPlayer = 2;
		gw.seconds = 20000;
		gw.incrementScore();
		
		assertTrue(gw.ruler.playerTwo.getScore() == 6);
	}
	
	@Test
	public void testTwoPlayerAddCard1() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}
			void setNameHelper2(){
				ruler.playerTwo.setName("tester2");
			}			

			int showConfirmAddCardDialog() {
				return 0;
			}
		};
		gw.twoPlayerGame.setEnabled(true);
		gw.twoPlayerGame.doClick();
		
		gw.ruler.currentPlayer = 1;
		gw.addCards.setEnabled(true);
		gw.addCards.doClick();
		
		assertTrue(gw.ruler.playBoard.getPlayedCards().length == 15);
	}
	
	@Test
	public void testTwoPlayerAddCard2() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}
			void setNameHelper2(){
				ruler.playerTwo.setName("tester2");
			}			
			int showConfirmAddCardDialog() {
				return 0;
			}
		};
		gw.twoPlayerGame.setEnabled(true);
		gw.twoPlayerGame.doClick();
		
		gw.ruler.currentPlayer = 2;
		gw.addCards.setEnabled(true);
		gw.addCards.doClick();
		
		assertTrue(gw.ruler.playBoard.getPlayedCards().length == 15);
	}	
	
	@Test
	public void testTwoPlayerSwitch1() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}
			void setNameHelper2(){
				ruler.playerTwo.setName("tester2");
			}			
			int showConfirmSkipTurnDialog() {
				return 0;
			}
		};
		gw.twoPlayerGame.setEnabled(true);
		gw.twoPlayerGame.doClick();
		
		gw.ruler.currentPlayer = 1;
		gw.skipTurn.setEnabled(true);
		gw.skipTurn.doClick();
		
		assertTrue(gw.ruler.currentPlayer == 2);
	}
	
	@Test
	public void testTwoPlayerSwitch2() {
		GameWindow gw = new GameWindow(){
			
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}
			void setNameHelper2(){
				ruler.playerTwo.setName("tester2");
			}
			
			int showConfirmSkipTurnDialog() {
				return 0;
			}
		};
		gw.twoPlayerGame.setEnabled(true);
		gw.twoPlayerGame.doClick();
		
		gw.ruler.currentPlayer = 2;
		gw.skipTurn.setEnabled(true);
		gw.skipTurn.doClick();
		
		assertTrue(gw.ruler.currentPlayer == 1);
	}
	
	@Test
	public void testTwoPlayerCancelSwitch() {
		GameWindow gw = new GameWindow(){
			
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}
			void setNameHelper2(){
				ruler.playerTwo.setName("tester2");
			}
			
			int showConfirmSkipTurnDialog() {
				return 1;
			}
		};
		gw.twoPlayerGame.setEnabled(true);
		gw.twoPlayerGame.doClick();
		
		gw.ruler.currentPlayer = 2;
		gw.skipTurn.setEnabled(true);
		gw.skipTurn.doClick();
		
		assertTrue(gw.ruler.currentPlayer == 2);
	}
	
	@Test
	public void testGetHint() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}
			
			int showConfirmHintDialog() {
				return 0;
			}
		};
		gw.onePlayerGame.setEnabled(true);
		gw.onePlayerGame.doClick();
		
		
		gw.getHint.setEnabled(true);
		gw.getHint.doClick();
		assertTrue( gw.selectedCards.size() == 1);		
		assertTrue( gw.ruler.playerOne.getScore() == -2);
	}
	
	@Test
	public void testRestartQuitGame() {
		GameWindow gw = new GameWindow(){

			
			int showConfirmQuitDialog() {
				return 0;
			}
			
			int showConfirmExitDialog() {
				return 1;
			}
			
		};
		gw.quitGame.setEnabled(true);
		gw.quitGame.doClick();
		assertNotNull(gw);
	}
	
	@Test
	public void testCancelQuitGame() {
		GameWindow gw = new GameWindow(){
	
			int showConfirmQuitDialog() {
				return 1;
			}

		};
		gw.quitGame.setEnabled(true);
		gw.quitGame.doClick();
		assertNotNull(gw);
	}
	
	
}
