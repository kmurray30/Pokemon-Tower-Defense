public class TurretMarker extends Moveable {
	private static Coord grassCoord = new Coord(100, 100);
    private static GrassMap grassMap = new GrassMap();
	private boolean isHover = false;

	public TurretMarker(String location, int xstart, int ystart, int speed) {
		super(location, xstart, ystart, speed);
	}

	public void hover(int x, int y) {
        Coord grass = grassMap.isPlaceable(new Coord(x, y));
            // System.out.println("x: " + grass.getX());
            // System.out.println("y: " + grass.getY());
            // System.out.println();
        isHover = (grass.getX() > 0 ? true : false);
        grassCoord = grass; // combine this with line 11
        this.setX(grass.getX());
        this.setY(grass.getY());
        // store.buyTurret();
        // turretPrice += 50;
    }

    public boolean isHovering() {
    	return isHover;
    }

    public Coord getGrassCoord() {
    	return grassCoord;
    }
}


            // Coord grass = grassMap.isPlaceable(new Coord(x, y));
            // boolean isHover = (grass.getX() > 0 ? true : false);
            // Var.isHovering(isHover);
            // Var.grassCoord(grass);
            // turretMark.setX(grass.getX());
            // turretMark.setY(grass.getY());
            // turretMark.update(cursor);