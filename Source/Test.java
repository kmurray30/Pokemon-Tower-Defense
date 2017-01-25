public class Test {
	private static Integer i = new Integer(1);

	public static void main(String[] args) {
		Integer ex = new Integer(i*3);
		i++;
		ex++;
		System.out.println(ex);
	}
}