import javafx.scene.control.Label;
import javafx.scene.Group;
import java.util.ArrayList;

public class Menus {
	final private Group startMenu = new Group();
	final private Group mainMenu = new Group();
	final private Group gameMenu = new Group();
	final private Group dexMenu = new Group();
	final private Group settingsMenu = new Group();
	final private ArrayList<Group> menus = new ArrayList<Group>();
    private Label creditLabel = new Label("Welcome!");
    private Label towerLife;
    private Label endText = new Label("");
    private Label bank = new Label();

	public Menus(Entities entities, Store store) {
		towerLife = new Label("Gym Leader health: " + entities.getTowerHealth().toString());
		menus.add(startMenu);
		menus.add(mainMenu);
		menus.add(gameMenu);
		menus.add(dexMenu);
		menus.add(settingsMenu);
	}

	public Group switchMenu(int i) {
		return menus.get(i);
	}
}