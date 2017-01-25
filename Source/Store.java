import java.util.ArrayList;

public class Store {
	private ArrayList<Pokemon> inventory = new ArrayList<Pokemon>();
	private static int money = 200;
	private int turretPrice = 100;

	public Store() {
		Pokemon pikachu = new Pokemon("Pikachu", "/Resource/pikachu.png", 15, 500);
		inventory.add(pikachu);
		Pokemon squirtle = new Pokemon("Squirtle", "/Resource/squirtle.png", 30, 1000);
		inventory.add(squirtle);
		Pokemon charizard = new Pokemon("Charizard", "/Resource/charizard.png", 50, 2000);
		inventory.add(charizard);
	}

	public void buyPika(int indPika) {
        if(inventory.get(indPika).getPrice() <= money){
        	Turret.addToDex(inventory.get(indPika));
			spend(inventory.get(indPika).getPrice());
			inventory.remove(indPika);
        }
	}

	public ArrayList<Pokemon> getInventory() {
		return inventory;
	}

	public static void spend(int i) {
		money -= i;
	}

	public static Integer getMoney() {
		return money;
	}

	public int getTurretPrice() {
		return turretPrice;
	}

	public Turret buyTurret(Coord turretPos, int level) {
		if(money >= turretPrice) {
            int x = turretPos.getX();
            int y = turretPos.getY();
            spend(turretPrice);
            turretPrice += 50;
            return new Turret("/Resource/red_bush.png", x, y);
		}
		return null;
	}
}