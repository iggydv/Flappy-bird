import java.util.concurrent.ThreadLocalRandom;

public class Pipe {

	volatile boolean passed = false;
	volatile double x;
	volatile double y;
	volatile double width;
	volatile double height;
	int rotation = randomIndex();
	
	public Pipe (double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void drawPipe() {
		if (rotation == 0) {
			StdDraw.picture(x , y, "resources/pictures/pipe3.png", width, height);
		} else {
			StdDraw.picture(x , 1-y, "resources/pictures/pipe3.png", width, height, 180);
		}
	}
	
	public void newRandomIndex() {
		rotation = randomIndex();
	}
	
	public int getRotaion() {
		return this.rotation;
	}
	
	private int randomIndex() {
		return ThreadLocalRandom.current().nextInt(0, 1 + 1);
	}
}
