import java.util.ArrayList;

public class Path {
	private ArrayList<Coord> corners;

	public Path(Coord ... corners) {
		this.corners = new ArrayList<Coord>();
		for(Coord c : corners) {
			this.corners.add(c);
		}
	}

	public Path() {
	}

	public Coord getCoord(int nextCorner) {
		return corners.get(nextCorner);
	}

	public int size() {
		return corners.size();
	}
}