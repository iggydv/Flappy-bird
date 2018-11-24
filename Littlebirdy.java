import java.awt.*;

public class Littlebirdy
{
	private final static double MAX_RESOLUTION = 1;
	static double ax = -0.01;

	static volatile double gap1;
	static volatile double gap2;
	static volatile double gap3;

	//velocity of each individual pipe
	static Pipe pipe1;
	static Pipe pipe2;
	static Pipe pipe3;

	static Bird bird = new Bird();
	static volatile int score = 0;

	public static void Load() {	
		StdDraw.setCanvasSize(1920, 1080);
		StdDraw.setScale(0,1);
		StdDraw.picture(0.5,0.5,"MainMenu.png"); // picture was made in paint
		StdAudio.play("startup.wav"); // play sound. see READ ME.txt (1)
	}

	public static void GameOver(int score) {
		StdDraw.pause(100);
		StdAudio.play("hit-ground.wav");// play sound. see README.txt (2)
		StdDraw.picture(0.5, 0.5, "background.png");
		StdDraw.setPenColor(StdDraw.RED);//change pen colour
		StdDraw.text(MAX_RESOLUTION/2 , MAX_RESOLUTION/2.5, ""+score); // write score at indicated postion
		StdDraw.text(MAX_RESOLUTION/2 , MAX_RESOLUTION/2, "GAME OVER!"); // write text
		StdDraw.show(20);
	}

	public static void reset() {
		gap1 = Math.random()*(0.8-0.4)+0.4;
		gap2 = Math.random()*(0.8-0.4)+0.4;
		gap3 = Math.random()*(0.8-0.4)+0.4;
		pipe1 = new Pipe(1, (gap1)/2, (gap1+0.1)*0.15, gap1);
		pipe2 = new Pipe(1.5 , (gap2)/2, (gap2+0.1)*0.15, gap2);
		pipe3 = new Pipe(2.0 , (gap3)/2, (gap3+0.1)*0.15, gap3);
		bird = new Bird();
		score = 0;
	}

	public static void fall() {
		StdAudio.play("hit-top-or-pillar.wav");
		
		while( bird.y > 0) {
			bird.degrees += -1; //tilt of bird
			bird.verticalVelocity -= bird.gravity;
			bird.y += bird.verticalVelocity;

			// draw Objects
			StdDraw.picture(0.5, 0.5, "background.png");
			StdDraw.picture(0.1, bird.y, "birdy.png", bird.degrees);
			pipe1.drawPipe();
			pipe2.drawPipe();
			pipe3.drawPipe();

			StdDraw.show(20);
		}
	}

	public static void Fly() {
		reset();
		Font font = new Font("Arial", Font.BOLD, 30);
		StdDraw.setFont(font);

		while (true) {
			// Clear the background
			//**********************************************************************************************************
			StdDraw.picture(0.5, 0.5, "background.png");

			// Draw pipes
			//**********************************************************************************************************
			pipe1.drawPipe();
			pipe2.drawPipe();
			pipe3.drawPipe();

			// Draw Bird
			//**********************************************************************************************************
			StdDraw.picture(bird.x, bird.y, "birdy.png", bird.degrees);

			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.text(0.9, 0.9, ""+score);
			StdDraw.show(20);

			// Window limits
			if (bird.y + bird.verticalVelocity > 1-0.01) {
				fall();
				GameOver(score);
				break;
			}

			if (bird.y + bird.verticalVelocity < bird.radius) {
				GameOver(score);
				break;
			}

			// Set pillars to origional position
			if ( (pipe1.x) < - 0.5 ) {
				pipe1.passed = false;
				gap1 = Math.random()*(0.8-0.4)+0.4;
				pipe1.x = 1.0;
				pipe1.y = (gap1)/2;
				pipe1.height = gap1;
				pipe1.width = (gap1)*0.15;
				pipe1.newRandomIndex();
			}

			if ( (pipe2.x) < - 0.5 ) {
				pipe2.passed = false;
				gap2 = Math.random()*(0.8-0.4)+0.4;
				pipe2.x = 1.0;
				pipe2.y = (gap2)/2;
				pipe2.height = gap2;
				pipe2.width = (gap2)*0.15;
				pipe2.newRandomIndex();
			}

			if ( (pipe3.x) < - 0.5 ) {
				pipe3.passed = false;
				gap3 = Math.random()*(0.8-0.4)+0.4;
				pipe3.x = 1.0;
				pipe3.y = (gap3)/2;
				pipe3.height = gap3;
				pipe3.width = (gap3)*0.15;
				pipe3.newRandomIndex();
			}

			if(StdDraw.isKeyPressed(' '))
			{
				bird.flap();
			}

			updateBirdPosition();
			
			updatePipePosition();
			
			if (checkPillerHit()) {
				break;
			}
			
			checkPillerPass();
		} 
	}

	public static void updateBirdPosition() {
		bird.verticalVelocity -= bird.gravity;
		bird.y += bird.verticalVelocity;
		bird.degrees += bird.degchange; // make bird rotate
	}

	public static void updatePipePosition() {
		pipe1.x += ax;
		pipe2.x += ax;
		pipe3.x += ax;
	}

	public static void checkPillerPass() {
		if (pipe1.x < bird.x && pipe1.passed == false) {
			StdAudio.play("score.wav"); // play sound. see README.txt (5)
			pipe1.passed = true;
			score++;
		}

		if (pipe2.x < bird.x && pipe2.passed == false) {
			StdAudio.play("score.wav");
			pipe2.passed = true;
			score++;
		}

		if (pipe3.x < bird.x && pipe3.passed == false) {
			StdAudio.play("score.wav"); // play sound. see README.txt (5)
			pipe3.passed = true;
			score++;
		}
	}

	public static boolean checkPillerHit() {
		// pillar 1
		if (almostEqual(bird.x, pipe1.x, 0.06 ) && (pipe1.getRotaion() == 0 ))  {
			if ((bird.y < pipe1.height)) {
				fall();
				GameOver(score);
				return true;
			}

		}
		if (almostEqual(bird.x, pipe1.x, 0.06 ) && (pipe1.getRotaion() == 1 ))  {
			if ((bird.y > 1 - pipe1.height)) {
				fall();
				GameOver(score);
				return true;
			}

		}
		// pillar 2
		if (almostEqual(bird.x, pipe2.x, 0.06 ) && (pipe2.getRotaion() == 0 ))  {
			if ((bird.y < pipe2.height)) {
				fall();
				GameOver(score);
				return true;
			}

		}
		if (almostEqual(bird.x, pipe2.x, 0.06 ) && (pipe2.getRotaion() == 1 ))  {
			if ((bird.y > 1 - pipe2.height)) {
				fall();
				GameOver(score);
				return true;
			}

		}

		// pillar 3
		if (almostEqual(bird.x, pipe3.x, 0.06 ) && (pipe3.getRotaion() == 0 ))  {
			if ((bird.y < pipe3.height)) {
				fall();
				GameOver(score);
				return true;
			}

		}
		if (almostEqual(bird.x, pipe3.x, 0.06 ) && (pipe3.getRotaion() == 1 ))  {
			if ((bird.y > 1 - pipe3.height)) {
				fall();
				GameOver(score);
				return true;
			}
		}
		return false;
	}

	private static boolean almostEqual(double a, double b, double eps){
		return Math.abs(a-b)<eps;
	}

	public static void main(String[] args) {
		Load();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (StdDraw.isKeyPressed(' ')) {
						Fly();
					}
				}
			}
		});

		t1.start();

		try {
			t1.join();
		} catch (InterruptedException e) {
			System.out.println(""+e);
			e.printStackTrace();
		}
	}
} 
