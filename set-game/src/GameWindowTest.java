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
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}
			
			int showConfirmHintDialog() {
				return 0;
			}
		};
		gw.SimpleTimer.start();
	}
	
	@Test
	public void testShowRules1() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}
			
			int showConfirmHintDialog() {
				return 0;
			}
			
			int showConfirmQuitDialog() {
				return 1;
			}
			
			int showConfirmExitDialog() {
				return 0;
			}
			
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
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}
			
			int showConfirmHintDialog() {
				return 0;
			}
			
			int showConfirmQuitDialog() {
				return 1;
			}
			
			int showConfirmExitDialog() {
				return 0;
			}
			
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
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}
			
			int showConfirmHintDialog() {
				return 0;
			}
			
			int showConfirmQuitDialog() {
				return 1;
			}
			
			int showConfirmExitDialog() {
				return 0;
			}
			
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
			int showConfirmHintDialog() {
				return 0;
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
	public void testTwoPlayerAddCard1() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}
			void setNameHelper2(){
				ruler.playerTwo.setName("tester2");
			}			
			int showConfirmHintDialog() {
				return 0;
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
			int showConfirmHintDialog() {
				return 0;
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
			int showConfirmHintDialog() {
				return 0;
			}
			int showConfirmAddCardDialog() {
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
		};
		gw.twoPlayerGame.setEnabled(true);
		gw.twoPlayerGame.doClick();
		
		gw.ruler.currentPlayer = 2;
		gw.skipTurn.setEnabled(true);
		gw.skipTurn.doClick();
		
		assertTrue(gw.ruler.currentPlayer == 1);
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
	public void testQuitGame() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}
			
			int showConfirmHintDialog() {
				return 0;
			}
			
			int showConfirmQuitDialog() {
				return 0;
			}
			
			int showConfirmExitDialog() {
				return 1;
			}
			
		};
		gw.quitGame.setEnabled(true);
		gw.quitGame.doClick();
	}
	
	@Test
	public void testQuitGame2() {
		GameWindow gw = new GameWindow(){
			void setNameHelper(){
				ruler.playerOne.setName("tester");
			}
			
			int showConfirmHintDialog() {
				return 0;
			}
			
			int showConfirmQuitDialog() {
				return 1;
			}
			
			int showConfirmExitDialog() {
				return 0;
			}
			
		};
		gw.quitGame.setEnabled(true);
		gw.quitGame.doClick();
	}
}