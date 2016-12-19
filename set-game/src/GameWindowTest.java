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
		gw = null;
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
		gw = null;
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
		gw = null;
	}	
	
	@Test
	public void testShowRules4() {
		GameWindow gw = new GameWindow(){
			int showRuleOptionDialog() {
				return 3;
			}
		};
		gw.howToPlay.setEnabled(true);
		gw.howToPlay.doClick();
		gw = null;
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
		gw = null;
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
		gw = null;
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
		gw = null;
	}	
	
	@Test
	public void testOnePlayerClickSameCard() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}			

		};
		gw.onePlayerGame.setEnabled(true);
		gw.onePlayerGame.doClick();
		
		gw.cardButtons[0].setEnabled(true);
		gw.cardButtons[0].doClick();
		gw.cardButtons[0].doClick();

		
		System.out.println(gw.selectedCards);
		assertTrue(gw.selectedCards.size() == 0);	
		gw = null;
	}	
	
	@Test
	public void testOnePlayerClickThreeCards2() {
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
		gw.ruler.playBoard.getPlayedCards()[0] = new Card(""+1, "1", "red", "empty", "diamond");
		gw.ruler.playBoard.getPlayedCards()[1] = new Card(""+2, "2", "red", "empty", "diamond");
		gw.ruler.playBoard.getPlayedCards()[2] = new Card(""+3, "3", "red", "empty", "diamond");
		
		gw.cardButtons[0].setEnabled(true);
		gw.cardButtons[0].doClick();
		gw.cardButtons[1].setEnabled(true);
		gw.cardButtons[1].doClick();
		gw.cardButtons[2].setEnabled(true);
		gw.cardButtons[2].doClick();
		
		System.out.println(gw.selectedCards);
		assertTrue(gw.selectedCards.size() == 3 || gw.selectedCards.size() == 0);	
		gw = null;
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
		gw = null;
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
		
		assertTrue(gw.ruler.playerOne.getScore() == 6);
		gw = null;
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
		gw = null;
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
		gw = null;
	}


	@Test
	public void testTwoPlayerAddPoints() {
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
		
		gw.ruler.currentPlayer = 1;
		gw.incrementScore();
		
		assertTrue(gw.ruler.playerOne.getScore() == 10);
		gw = null;
	}	

	@Test
	public void testTwoPlayerAddPoints5() {
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
		
		gw.ruler.currentPlayer = 1;
		gw.seconds = 40000;
		gw.incrementScore();
		
		assertTrue(gw.ruler.playerOne.getScore() == 8);
		gw = null;
	}	

	@Test
	public void testTwoPlayerAddPoints6() {
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
		
		gw.ruler.currentPlayer = 1;
		gw.seconds = 20000;
		gw.incrementScore();
		
		assertTrue(gw.ruler.playerOne.getScore() == 6);
		gw = null;
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
		gw = null;
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
		gw = null;
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
		gw = null;
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
		gw = null;
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
		gw = null;
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
		gw = null;
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
		gw = null;
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
		gw = null;
	}
	
	@Test
	public void testDialog1() {
		GameWindow gw = new GameWindow();
		gw.showBasicRules1();
	}
	
	@Test
	public void testDialog2() {
		GameWindow gw = new GameWindow();
		gw.showBasicRules2();
	}
	
	@Test
	public void testDialog3() {
		GameWindow gw = new GameWindow();
		gw.showBasicRules3();
	}
	
}
