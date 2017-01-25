import java.util.ArrayList;

public class GrassMap {
	ArrayList<Coord> grass1 = new ArrayList<Coord>();
	int[] box1 = {64, 593, 176, 657};
	ArrayList<Coord> grass2 = new ArrayList<Coord>();
	int[] box2 = {192, 465, 288, 545};
	ArrayList<Coord> grass3 = new ArrayList<Coord>();
	int[] box3 = {256, 373, 352, 369};
	ArrayList<Coord> grass4 = new ArrayList<Coord>();
	int[] box4 = {160, 177, 352, 257};
	private int DIM = 16;

	public GrassMap() {
		for (int i = 0; i < 28; i++) {
			int j = i/7;
			int k = i/14;
			int x = 128 - i*DIM + j*7*DIM + k*2*DIM;
			int y = 641 - j*DIM;
			grass1.add(new Coord(x, y));
		}
		for (int i = 0; i < 30; i++) {
			int j = i/6;
			int x = 272 - i*DIM + j*6*DIM;
			int y = 529 - j*DIM;
			grass2.add(new Coord(x, y));
		}
		for (int i = 0; i < 30; i++) {
			int j = i/6;
			int x = 336 - i*DIM + j*6*DIM;
			int y = 353 - j*DIM;
			grass3.add(new Coord(x, y));
		}
		for (int i = 0; i < 60; i++) {
			int j = i/12;
			int x = 336 - i*DIM + j*12*DIM;
			int y = 241 - j*DIM;
			grass4.add(new Coord(x, y));
		}
	}

	public Coord isPlaceable(Coord cursor) {
		for (int i = 0; i < grass1.size(); i++) {
			Coord patch = grass1.get(i);
			if(patch.isClose(cursor, DIM + 2)) {
				return patch;
			}
		}
		for (int i = 0; i < grass2.size(); i++) {
			Coord patch = grass2.get(i);
			if(patch.isClose(cursor, DIM + 2)) {
				return patch;
			}
		}
		for (int i = 0; i < grass3.size(); i++) {
			Coord patch = grass3.get(i);
			if(patch.isClose(cursor, DIM + 2)) {
				return patch;
			}
		}
		for (int i = 0; i < grass4.size(); i++) {
			Coord patch = grass4.get(i);
			if(patch.isClose(cursor, DIM + 2)) {
				return patch;
			}
		}
		return new Coord(-1000, 0);
	}

	public boolean isInside(Moveable m) {
		//
		return true;
	}
}