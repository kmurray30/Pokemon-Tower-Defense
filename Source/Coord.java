
import java.lang.Math;

public class Coord {
	final private int x;
	final private int y;

	public Coord(int xloc, int yloc) {
		x = xloc;
		y = yloc;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isClose(Coord cord, int i) {
		int cx = cord.getX();
		int cy = cord.getY();
		if((cx - x) < i && (cx - x) >= 0 && (cy - y) < i && (cy - y) >= 0) {
			return true;
		}
		return false;
	}
}