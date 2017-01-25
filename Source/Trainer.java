import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.collections.ObservableList;
import java.lang.Math;

public class Trainer extends Moveable {
	private int health;
	private ArrayList<Moveable> pokeballs = new ArrayList<Moveable>();
	final private Group trainerwballs;
	final private int MAXHEALTH = 120;
	final private int MAXBALLS = 6;

	public Trainer(String location, int xloc, int yloc, int health) {
		    super(location, xloc, yloc, 1);
		    // health = (health < 0 ? 0 : health > MAXHEALTH ? MAXHEALTH : health);
		    this.health = health;
		    Coord one = new Coord(202, 586);
		    Coord two = new Coord(105, 586);
		    Coord three = new Coord(105, 507);
		    Coord four = new Coord(202, 507);
		    Coord five = new Coord(202, 400);
		    Coord six = new Coord(154, 400);
		    Coord seven = new Coord(154, 346);
		    Coord eight = new Coord(283, 346);
		    Coord nine = new Coord(283, 118);
		    Coord ten = new Coord(170, 118);
		    Coord eleven = new Coord(170, 95);
		    Path path = new Path(one, two, three, four, five, six, seven, eight, nine, ten, eleven);
		    super.setPath(path);
			trainerwballs = new Group();
			updateBalls();
	}

	public void damage(int i) {
		health -= i;
		updateBalls();
	}

	public void updateBalls() {
		pokeballs.clear();
		int xpos;
		int ypos;
	    trainerwballs.getChildren().clear();
	    trainerwballs.getChildren().add(this.getImageView());
	    for (int i = 0; i < getNumBalls(); i++) {
	    	String fileLoc = "/Resource/pokeball.png";
	    	pokeballs.add(new Moveable(fileLoc, 0, 0, 1));
	    	xpos = (i < 3 ? 20-i*10 : (i-3)*10) + 1;
	    	ypos = (i < 3 ? 0 : -10);
	    	Moveable pokeball = pokeballs.get(i);
	    	ImageView pokeballView = pokeball.getImageView();
			pokeballView.setTranslateX(xpos);
			pokeballView.setTranslateY(ypos);
	    	trainerwballs.getChildren().add(pokeballView);
	    }
	}

	public Image getImage() {
		return super.getImage();
	}

	public Image getPokeball(int i) {
		return pokeballs.get(i).getImage();
	}

	public int getNumBalls() {
		Double numBalls = Math.ceil(((double) health)/(MAXHEALTH/MAXBALLS));
		if(numBalls <= MAXBALLS) {
			return numBalls.intValue();
		} else return MAXBALLS;
	}

	public Group getGroup() {
	    return trainerwballs;
	}

	public int getHealth() {
	    return health;
	}
}