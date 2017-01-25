import java.util.ArrayList;

public class Turret extends Stationary {
	private static ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
	private boolean hasShot = false;
	private int shotTimer = 0;

	public Turret(String location, int xstart, int ystart) {
		super(location, xstart, ystart);
		if (pokedex.size() < 1) {
			pokedex.add(new Pokemon("Rattata", "/Resource/rattata.png", 5, 0));
		}
	}

	public Attacker shoot(Trainer t) {
		if(hasShot) return null;
		shotTimer = 0;
		if(pokedex.size() > 0) {
	        int rand = (int) (Math.random() * pokedex.size());
			Pokemon poke = pokedex.get(rand);
			Attacker atker = new Attacker(poke, this.getX(), this.getY(), t);
			hasShot = true;
			return atker;
		}
		return null;
	}

	public void addToShotTimer() {
		shotTimer++;
	}


	public int shotTimer() {
		return shotTimer;
	}

	public static void addToDex(Pokemon pika) {
		pokedex.add(pika);
	}

	public static int numTurrets() {
		return pokedex.size();
	}

	public void reload() {
		hasShot = false;
	}

	public void placeTurret() {

	}
}