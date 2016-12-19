/**
 * This class represents a player in Set Game.
 */
public class Player {

	private String name;
	private int score;
	
	/**
	 * The constructor initializes the player's score to 0
	 * and his/her name to null.
	 */
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
	
	/**
	 * This method add points to the player's current score.
	 * @param points number of points to add to current score
	 */
	public void addPoints(int points){
		score += points;
	}
	
	/**
	 * This method take points away from the player's current score.
	 * @param points number of points to subtract from current score
	 */
	public void takePoints(int points){
		score -= points;
	}
	
	public int getScore(){
		return score;
	}
	
}
