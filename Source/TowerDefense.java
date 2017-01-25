import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.awt.Component;

/**
 * MailReader class
 *
 * @author Kyle Murray
 * @version 1.0
 */
public class TowerDefense extends Application {

    private Timeline timeline;
    static int gamespeed = 10;
    final static int WIDTH = 384;
    final static int HEIGHT = 738;

    private Integer timeSeconds = 0;
    private Integer waveTime = 0;
    private Integer level = 1;
    private boolean gameOn = false;
    private int spawned = 0;

    final static Stationary background = new Stationary("/Resource/background.png", 0, 0);
    final static Stationary tower = new Stationary("/Resource/tower.png", 130, 35);
    final static TurretMarker turretMark = new TurretMarker("/Resource/orange_bush.png",-100,0,2);

    final private Entities entities = new Entities();
    final private Store store = new Store();

    final private Group gameWindow = new Group();
    final private Group mainMenu = new Group();
    final private Group pokedexMenu = new Group();
    final private Group settingsMenu = new Group();
    final private Group backdrop = new Group();
    final private Group startMenu = new Group();
    final private Group root = new Group(gameWindow, mainMenu);

    private Label creditLabel = new Label("Welcome!");
    private Button button;
    private Label towerLife = new Label("Gym Leader health: " + entities.getTowerHealth().toString());
    private Label endText = new Label("");
    private Label bank = new Label();

    /**
     * Runner for javaFX
     *
     * @param stage  Stage you will be working with for GUI
     * @throws Exception  Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        // GameWindow
        final Group backdrop = new Group(background.getImageView(), tower.getImageView());
        backdrop.setEffect(new BoxBlur());
        turretMark.getImageView().setOnMouseClicked(e -> {
            int i = 1;
            if(turretMark.isHovering()) {
                Coord turretPos = turretMark.getGrassCoord();
                Turret turret = store.buyTurret(turretPos, i);
                if(turret == null) return;
                entities.add(turret);
            }
        });
        background.getImageView().setOnMouseMoved(e -> {
            if(gameOn) {
                int x = (int) e.getX();
                int y = (int) e.getY();
                turretMark.hover(x, y);
                turretMark.update();
            }
        });
        Rectangle rect = new Rectangle(380, 30);
        rect.setFill(Color.WHITE);
        HBox stats = new HBox(30);
        stats.getChildren().addAll(creditLabel, towerLife);
        gameWindow.getChildren().addAll(backdrop, turretMark.getImageView(), entities.getAll(), rect, stats);

        // MainMenu
        Button startWaveButton = new Button("Start Game");
        Button pokedexButton = new Button("Pokedex");
        Button fasterButton = new Button("Speed up");
        Button slowerButton = new Button("Slow down");
        Rectangle backblur = new Rectangle(WIDTH, HEIGHT);
        backblur.setOpacity(0.5);
        startWaveButton.setTranslateX(150);
        startWaveButton.setTranslateY(300);
        pokedexButton.setTranslateX(150);
        pokedexButton.setTranslateY(350);
        pokedexButton.setOnAction(e -> {
            root.getChildren().add(pokedexMenu);
        });
        fasterButton.setOnAction(e -> {
            gamespeed = 5;
            mainMenu.getChildren().add(slowerButton);
            mainMenu.getChildren().remove(fasterButton);
        });
        slowerButton.setOnAction(e -> {
            gamespeed = 10;
            mainMenu.getChildren().add(fasterButton);
            mainMenu.getChildren().remove(slowerButton);
        });
        fasterButton.setTranslateX(300);
        slowerButton.setTranslateX(300);
        mainMenu.getChildren().addAll(backblur, startWaveButton, pokedexButton, fasterButton, endText);

        // Pokedex
        Button returnButton = new Button("Return to game");
        Button buyButton = new Button("Buy");
        Rectangle whiteback = new Rectangle(WIDTH, 500);
        whiteback.setFill(Color.WHITE);
        BorderPane border = new BorderPane();
        VBox leftPane = new VBox(10);
        HBox topPane = new HBox(100);
        ListView<Pokemon> listDex = new ListView<Pokemon>();
        ObservableList<Pokemon> dex = FXCollections.observableArrayList(store.getInventory());
        returnButton.setOnAction(e -> {
            root.getChildren().remove(pokedexMenu);
        });
        buyButton.setOnAction(e -> {
            int indPika = listDex.getSelectionModel().getSelectedIndex();
            store.buyPika(indPika);
            ObservableList<Pokemon> dex2 = FXCollections.observableArrayList(store.getInventory());
            listDex.setItems(dex2);
            listDex.getSelectionModel().select(0);
            bank.setText("$" + store.getMoney().toString());
            creditLabel.setText("Credit: $" + store.getMoney().toString());
        });
        listDex.setItems(dex);
        listDex.setMaxWidth(150);
        leftPane.getChildren().add(listDex);
        border.setLeft(leftPane);
        topPane.getChildren().addAll(returnButton, bank, buyButton);
        border.setTop(topPane);
        pokedexMenu.getChildren().addAll(whiteback, border);

        startWaveButton.setOnAction(e -> {
            gameOn = true;
            root.getChildren().remove(mainMenu);
            backdrop.setEffect(null);
            if (timeline != null) {
                timeline.stop();
            }
            waveTime = 0;
            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(gamespeed),
                      new EventHandler<ActionEvent>() {
                        // KeyFrame event handler
                        public void handle(ActionEvent event) {
                            wave();
                            if(spawned == level && entities.getTrainers().size() == 0) {
                                wave();
                                System.out.println("END OF WAVE " + level);
                                // break out of loop
                                level++;
                                spawned = 0;
                                startWaveButton.setText("Begin wave " + level + "!");
                                backdrop.setEffect(new BoxBlur());
                                root.getChildren().addAll(mainMenu);
                                try {
                                    timeline.stop();
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                            if(entities.getTowerHealth() < 1) {
                                towerLife.setText("GAME OVER");
                                towerLife.setTextFill(Color.RED);
                                backdrop.setEffect(new BoxBlur());
                                try {
                                    timeline.stop();
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                          }
                    }));
            timeline.playFromStart();
        });

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Title of Window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void wave() {
        waveTime++;
        creditLabel.setText("Credit: $" + store.getMoney().toString() + "\nSpawn point cost: " + store.getTurretPrice());
        bank.setText("$" + store.getMoney().toString());
        // Spawn new trainer
        if(waveTime%(100 + 500/level) == 0 && level > entities.getTrainers().size() && spawned < level) {
            spawned++;
            entities.createNewTrainer(level);
        }
        // Move trainers
        entities.moveTrainers();
        // Move attackers and attack trainers
        entities.attackTrainers();
        // 
        entities.moveAttackers();
        // Add money
        if(waveTime%20 == 0) {
            store.spend(-1);
        }
        towerLife.setText("Gym Leader health: " + entities.getTowerHealth().toString());
        entities.moveAttackers();
        entities.updateAll();
    }
}