import java.util.ArrayList;
import javafx.scene.Group;

public class Entities {
	private ArrayList<Turret> turrets = new ArrayList<Turret>();
	private ArrayList<Attacker> atkers = new ArrayList<Attacker>();
    final private Group pikas = new Group();
    final private Group turretGroup = new Group();
    final private Group allTrainers = new Group();
    final private Group all = new Group(pikas, turretGroup, allTrainers);
    final private ArrayList<Trainer> trainers = new ArrayList<Trainer>();
    final private ArrayList<Group> trainersWballs = new ArrayList<Group>();
    private Integer towerHealth = 1000;


	public Entities() {
	}

	public void updateAll() {
		for(Attacker a : atkers) {
			a.update();
		}
	}

	public void add(Turret t) {
		turrets.add(t);
        turretGroup.getChildren().add(t.getImageView());
	}

	// public Turret getTurret(int i) {
	// 	return turrets.get(i);
	// }

	public ArrayList<Turret> getTurrets() {
		return turrets;
	}

	public void add(Attacker a) {
        pikas.getChildren().add(a.getImageView());
		atkers.add(a);
	}

	public void remove(Attacker a) {
		pikas.getChildren().remove(a.getImageView());
        atkers.remove(a);
	}

	public ArrayList<Attacker> getAtkers() {
		return atkers;
	}

	public Group getAll() {
		return all;
	}

	// public Group getAllTrainers() {
	// 	return allTrainers;
	// }

	public ArrayList<Trainer> getTrainers() {
		return trainers;
	}

	public ArrayList<Group> getTrainersWballs() {
		return trainersWballs;
	}

	public int numAttackers() {
		return atkers.size();
	}

	// public Group getPikas() {
	// 	return pikas;
	// }

	public void createNewTrainer(int level) {
		Trainer newTrainer = randomTrainer(level);
        trainers.add(newTrainer);
        trainersWballs.add(newTrainer.getGroup());
        newTrainer.update(newTrainer.getGroup());
        allTrainers.getChildren().add(newTrainer.getGroup());
	}

    public Trainer randomTrainer(int level) {
        ArrayList<String> pics = new ArrayList<String>();
        pics.add("/Resource/trainer1.png");
        pics.add("/Resource/trainer2.png");
        pics.add("/Resource/trainer3.png");
        pics.add("/Resource/trainer4.png");
        pics.add("/Resource/trainer5.png");
        int rand = (int) (Math.random() * pics.size());
        Trainer trainer = new Trainer(pics.get(rand), 202, 800, 12*level - 5);
        return trainer;
    }

    public void attackTrainers() {
        // System.out.println("Size: " + turrets.size());
        for(int i = 0; i < turrets.size(); i++) {
            // System.out.println("ind: " + i);
            Turret turret = turrets.get(i);
            turret.reload();
            for (int j = 0; j < trainers.size(); j++) {
                Trainer trainer = trainers.get(j);
                int rand = (int) (Math.random() * 200);
                if(rand == 1 || turret.shotTimer() > 500) {
                    try {
                        if(Math.abs(turret.getX() - trainer.getX()) < 75 && Math.abs(turret.getY() - trainer.getY()) < 87) {
                            Attacker pika = turret.shoot(trainer);
                            add(pika);
                        }
                    } catch(Exception e) {
                        System.out.println("Turret index out of range");
                    }
                } else {
                	turret.addToShotTimer();
                }
            }
        }
    }

    public void moveTrainers() {
    	ArrayList<Trainer> dead = new ArrayList<Trainer>();
        for (int k = 0; k < trainers.size(); k++) {
            Trainer trainer = trainers.get(k);
            trainer.move();
            trainer.update(trainersWballs.get(k));
            if (trainer.getX() < 190 && trainer.getY() < 110) {
                towerHealth--;
            }
            if(trainer.getHealth() < 1) {
                dead.add(trainer);
            }
        }
        // Kill trainers
        for(Trainer t : dead) {
            allTrainers.getChildren().remove(t.getImageView());
            trainers.remove(t);
            trainersWballs.remove(t.getGroup());
            allTrainers.getChildren().remove(t.getGroup());
            Store.spend(-50);
        }
    }

	public void moveAttackers() {
        ArrayList<Attacker> pokedone = new ArrayList<Attacker>();
        for(Attacker a : atkers) {
            if(a.hasReached()) {
                a.getTarget().damage(a.getAtk());
                pokedone.add(a);
            } else {
                a.updatePath(a.getTarget());
                a.move();
                // a.update();
            }
        }
        for(Attacker a : pokedone) {
            this.remove(a);
        }
	}

	public Integer getTowerHealth() {
		return towerHealth;
	}
}