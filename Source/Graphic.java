import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Graphic {
    private Image IMAGE;
    private ImageView IMAGEVIEW;


	public Graphic(String location) {
		    IMAGE = new Image(TowerDefense.class.getResourceAsStream(location));
			IMAGEVIEW = new ImageView(IMAGE);
	}

	public Image getImage() {
		return IMAGE;
	}

	public ImageView getImageView() {
		return IMAGEVIEW;
	}
}