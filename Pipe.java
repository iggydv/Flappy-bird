
public class Pipe {

	public static boolean passed = false;

	public void drawPipe(double x, double y, double width, double height) {
		StdDraw.picture(x , y, "pipe3.png", width, height);
	}

	public void drawPipe(double x, double y, double width, double height, double degrees) {
		StdDraw.picture(x , y, "pipe3.png", width, height, degrees);
	}

}
