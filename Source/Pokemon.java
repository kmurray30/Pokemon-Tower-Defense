public class Pokemon {
	String name;
	String imageFile;
	int atk;
	int price;
	
	public Pokemon(String name, String imageFile, int atk, int price) {
		this.name = name;
		this.imageFile = imageFile;
		this.atk = atk;
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public String getLoc() {
		return imageFile;
	}

	public int getAttack() {
		return atk;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return (name + " - $" + price);
	}
}