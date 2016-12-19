/**
 * This class creates a card for Set game.
 */
public class Card {

	private String quantity;
	private String color;
	private String shading;
	private String shape;
	private String idNumber;

	/**
	 * Card constructor
	 * @param idNumber unique ID: 1 through 81
	 * @param quantity 1, 2, or 3
	 * @param color red, purple, or green
	 * @param shading solid, striped, or empty
	 * @param shape oval, diamond, or squiggle
	 */
	public Card(String idNumber, String quantity, String color, String shading, String shape) {
		this.idNumber = idNumber;
		this.quantity = quantity;
		this.color = color;
		this.shading = shading;
		this.shape = shape;
		//Test comment
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getShading() {
		return shading;
	}

	public void setShading(String shading) {
		this.shading = shading;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * Checks if two Cards are equal
	 * @param c Card to compare to
	 * @return true if cards have all the same features, false otherwise
	 */
	public boolean equal(Card c) {
		if(c== null) return false;
		//System.out.println(this + " vs " + c);
		if(this.getColor().equals(c.getColor()) 
				&& this.getShading().equals(c.getShading()) 
				&& this.getQuantity().equals(c.getQuantity()) 
				&& this.getShape().equals(c.getShape())) return true;
		else return false;
	}
	
	/**
	 * This method generates a String that refers to the filename for a card.
	 * @return filenaem for Card image
	 */
	public String getImageFile() {
		String image = null;
		if (Integer.parseInt(idNumber) < 10){
			image = "/cardimages/Card0" + idNumber + ".jpg";
		} else {
			image = "/cardimages/Card" + idNumber + ".jpg";
		}
		return image;
	}

	/**
	 * This method generates a String that refers to the filename
	 * for the clicked image of a card.
	 * @return filename for clicked image of Card
	 */
	public String getClickedImage() {
		String image = null;
		if (Integer.parseInt(idNumber) < 10){
			image = "/cardimages/Card0" + idNumber + "clkd.jpg";
		} else {
			image = "/cardimages/Card" + idNumber + "clkd.jpg";
		}
		return image;
	}

}
