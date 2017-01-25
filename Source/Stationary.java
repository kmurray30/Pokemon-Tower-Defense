import javafx.scene.image.Image;

public class Stationary extends Graphic {
	private int xloc;
	private int yloc;

	public Stationary(String location, int xloc, int yloc) {
		    super(location);
		    this.xloc = xloc;
		    this.yloc = yloc;
	        super.getImageView().setTranslateX(xloc);
	        super.getImageView().setTranslateY(yloc);
	}

	public int getX() {
		return xloc;
	}

	public int getY() {
		return yloc;
	}
}