/**
 * This class represents a player in Set Game.
 * @author eherzog
 *
 */
public class Player {

	private String name;
	private int score;
	
	public Player(){
		name = null;
		score = 0;
	}
	
	public void setName(String playerName){
		name = playerName;
	}
	
	public String getName(){
		return name;
	}
	
	public void addPoints(int points){
		score += points;
	}
	
	public void takePoints(int points){
		score -= points;
	}
	
	public int getScore(){
		return score;
	}
	
}
