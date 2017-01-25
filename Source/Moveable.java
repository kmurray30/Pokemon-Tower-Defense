import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Moveable extends Graphic {
	private int xloc;
	private int yloc;
	private int speed; // Either 1 or 2
	final private int xstart;
	final private int ystart;
	private Path path;
	private int nextCorner;
	private int counter;

	public Moveable(String location, int xstart, int ystart, int speed) {
		    super(location);
		    this.xstart = xstart;
		    this.ystart = ystart;
		    xloc = xstart;
		    yloc = ystart;
		    this.speed = speed;
		    nextCorner = 0;
			counter = 0;
	        // super.getImageView().setTranslateX(xloc);
	        // super.getImageView().setTranslateY(yloc);
	}

	public boolean hasReached() {
		if(!(nextCorner < path.size())) {
			return true;
		}
		return false;
	}

	public void move() {
		if(!(nextCorner < path.size())) {
			return;
		}
		counter++;
		counter = (counter < 2 ? counter : 0);
		if(speed <= counter) {
			return;
		}
		// System.out.println("Corner y: " + (ystart + path.getCoord(nextCorner).getY()));
		// System.out.println("yloc: " + yloc);
		// System.out.println("Corner x: " + (xstart + path.getCoord(nextCorner).getX()));
		// System.out.println("xloc: " + xloc);
		// System.out.println();
		if(xloc < path.getCoord(nextCorner).getX()) {
			xloc++;
		} else if(xloc > path.getCoord(nextCorner).getX()) {
			xloc--;
		}
		if(yloc < path.getCoord(nextCorner).getY()) {
			yloc++;
		} else if(yloc > path.getCoord(nextCorner).getY()) {
			yloc--;
		}
		// If corner reached, set new corner index
		if(xloc == path.getCoord(nextCorner).getX() && yloc == path.getCoord(nextCorner).getY()) {
			nextCorner++;
		}
	}

	public void update(Group grp) {
		// System.out.println(grp.getChildren().get(0).getX());
		grp.setTranslateX(xloc);
		grp.setTranslateY(yloc);
	}

	// public void update(Moveable mvl) {
	// 	mvl.getImageView().setTranslateX(xloc);
	// 	mvl.getImageView().setTranslateY(yloc);
	// }

	public void update() {
		this.getImageView().setTranslateX(xloc);
		this.getImageView().setTranslateY(yloc);
	}

	public Image getImage() {
		return super.getImage();
	}

	public int getX() {
		return xloc;
	}

	public int getY() {
		return yloc;
	}

	public void setX(int i) {
		xloc = i;
	}

	public void setY(int i) {
		yloc = i;
	}

	public void setPath(Path p) {
		path = p;
	}

	// public void endPath(){
	// 	nextCorner = 999;
	// }
}