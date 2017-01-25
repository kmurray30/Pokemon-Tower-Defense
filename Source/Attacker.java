public class Attacker extends Moveable {
	Path path;
	int atk;
	Trainer target;

	public Attacker(Pokemon pika, int xstart, int ystart, Trainer target) {
		super(pika.getLoc(), xstart, ystart, 2);
		this.target = target;
		Coord trainerpos = new Coord(target.getX(), target.getY());
		path = new Path(trainerpos);
		this.atk = pika.getAttack();
		super.setPath(path);
	}

	public void updatePath(Trainer target) {
		int x = target.getX();
		int y = target.getY();
		Coord targetcoord = new Coord(x, y);
		path = new Path(targetcoord);
		super.setPath(path);
	}

	public int getAtk() {
		return atk;
	}

	public Trainer getTarget() {
		return target;
	}
}