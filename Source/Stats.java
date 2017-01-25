public class Stats {
	private static Integer level = 1;
	private static Integer timer = 1;

	public Stats() {
		
	}

	public static Integer getLevel() {
		return level;
	}

	public static void advanceLevel() {
		level++;
	}
}