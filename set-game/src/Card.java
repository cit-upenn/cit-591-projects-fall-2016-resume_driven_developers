/**
 * This class creates a card for Set game.
 * @author eherzog
 *
 */
public class Card {

	private String quantity;
	private String color;
	private String shading;
	private String shape;
	private String idNumber;

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

	public boolean equal(Card c) {
		if(c== null) return false;
		//System.out.println(this + " vs " + c);
		if(this.getColor().equals(c.getColor()) 
				&& this.getShading().equals(c.getShading()) 
				&& this.getQuantity().equals(c.getQuantity()) 
				&& this.getShape().equals(c.getShape())) return true;
		else return false;
	}
	
	public String getImageFile() {
		String image = null;
		if (Integer.parseInt(idNumber) < 10){
			image = "/cardimages/Card0" + idNumber + ".jpg";
		} else {
			image = "/cardimages/Card" + idNumber + ".jpg";
		}
		return image;
	}

	public String getClickedImage() {
		String image = null;
		if (Integer.parseInt(idNumber) < 10){
			image = "/cardimages/Card0" + idNumber + "clkd.jpg";
		} else {
			image = "/cardimages/Card" + idNumber + "clkd.jpg";
		}
		return image;
	}

	public String toString(){
		return quantity+"/" +color +"/"+shape+"/"+shading; 
	}
}
