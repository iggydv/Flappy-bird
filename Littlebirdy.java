import java.awt.*;

public class Littlebirdy
{
	private final static double MAX_RESOLUTION = 1;

	public static void Load()
	{
		StdDraw.setCanvasSize(1080, 1080);
		StdDraw.picture(0.5,0.5,"MainMenu.png"); // picture was made in paint
		StdAudio.play("mb_new.wav"); // play sound. see READ ME.txt (1)
	}

	public static void GameOver(int score)
	{
		StdAudio.play("hit2.wav");// play sound. see README.txt (2)
		StdDraw.setPenColor(StdDraw.RED);//change pen colour
		StdDraw.text(MAX_RESOLUTION/2 , MAX_RESOLUTION/2.5, ""+score); // write score at indicated postion
		StdDraw.text(MAX_RESOLUTION/2 , MAX_RESOLUTION/2, "GAME OVER!"); // write text
	}

	public static void fall(Bird bird, double gap1, double gap2, double gap3, double positionPipe1, double positionPipe2, double positionPipe3, int score)
	{
		Pipe pipe1 = new Pipe();
		Pipe pipe2 = new Pipe();
		Pipe pipe3 = new Pipe();
		
		StdAudio.play("mb_sc.wav");// play sound. see README.txt (3)
		while(bird.Y>0) // while not at bottom
		{
			double rx = -0.5;
			bird.degrees += -1; //tilt of bird
			bird.verticalVelocity -= bird.gravity;
			bird.Y += bird.verticalVelocity;
			
			// draw Objects
			StdDraw.picture(0.5, 0.5, "background.png");
			StdDraw.picture(0.1, bird.Y, "birdy.png", bird.degrees);

			pipe1.drawPipe(positionPipe1 , 0.1, 0.15, gap1-0.1);
			pipe1.drawPipe(positionPipe1 , 1, 0.15, gap1+0.1, 180);
			
			pipe2.drawPipe(positionPipe2 , 0.1, 0.15, gap2-0.1);
			pipe2.drawPipe(positionPipe2 , 1, 0.15, gap2+0.1, 180);
			
			pipe3.drawPipe(positionPipe3 , 0.1, 0.15, gap3-0.1);
			pipe3.drawPipe(positionPipe3 , 1, 0.15, gap3+0.1, 180);
		
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.text((rx-0.3) , 0.9, ""+score);
			StdDraw.show(20);
		}
	}

	public static void Fly()
	{ 
//		double birdX = 0.1;
//		double birdY = 0.5;
//		double verticalVelocity = 0.0;
//		double gravity = 0.0020;
//		double jump = 0.030;  
//		double birdRadius = 0.055;
//		double degrees = 0;
//		double degchange = -1;
		Bird bird = new Bird();
		
		double positionPipe1 = 1.5;
		double ax = -0.01;
		double positionPipe2 = 2.5;
		double ax1= -0.01;
		double positionPipe3 = 3.5;
		double ax2 = -0.01;

		//velocity of each individual pipe
		Pipe pipe1 = new Pipe();
		Pipe pipe2 = new Pipe();
		Pipe pipe3 = new Pipe();

		Double gap1 = Math.random()*(0.65-0.3)+0.3;
		Double gap2 = Math.random()*(0.65-0.3)+0.3;
		Double gap3 = Math.random()*(0.65-0.3)+0.3;

		int score = 0;
		StdDraw.picture(bird.X, bird.Y , "birdy.png", bird.degrees);
		// main loop
		while (true)  {
			//while not at bottom of window or at the top
			if (bird.Y + bird.verticalVelocity < bird.radius || bird.Y + bird.verticalVelocity > 1-0.01)
			{
				fall(bird, gap1 , gap2, gap3, positionPipe1 , positionPipe2, positionPipe3, score);
				GameOver(score);//call gameover function
				break;
			}

			// set pillars to origional position
			//*************************************************************************************************************
			if(((positionPipe1 + ax) < -1.5 - bird.radius)) //pillar 1
			{
				positionPipe1 = 1.5;
				pipe1.passed = false;
				gap1 = Math.random()*(0.65-0.3)+0.3;
			}

			if(((positionPipe2 + ax1) < -1.5- bird.radius)) // pillar 2
			{
				positionPipe2 = 1.5;
				pipe2.passed = false;
				gap2 = Math.random()*(0.65-0.3)+0.3;
			}

			if(((positionPipe3 + ax2) < (-1.5 - bird.radius))) // pillar 3
			{
				positionPipe3 = 1.5;
				pipe3.passed = false;
				gap3 = Math.random()*(0.65-0.3)+0.3;
			}
			//*************************************************************************************************************
			// TODO: pillar hit detection
			//*************************************************************************************************************        
			//
			//*************************************************************************************************************
			
			// flap when spacebar is pressed
			if(StdDraw.isKeyPressed(' '))
			{
				bird.flap();
			}

			//bird movement
			bird.verticalVelocity -= bird.gravity;
			bird.Y += bird.verticalVelocity;

			// give pipes a certain velocity
			positionPipe1 = positionPipe1 + ax;
			positionPipe2 = positionPipe2 + ax1;
			positionPipe3 = positionPipe3 + ax2;

			//check if successfully passed through pipe
			//*************************************************************************************************************
			if (positionPipe1 < bird.X && positionPipe1 > bird.X-0.3 && pipe1.passed == false) {
				StdAudio.play("pp.wav"); // play sound. see README.txt (5)
				pipe1.passed = true;
				
			}

			if (positionPipe2 < bird.X && positionPipe2 > bird.X-0.3 && pipe2.passed == false) {
				StdAudio.play("pp.wav"); // play sound. see README.txt (5)
				pipe2.passed = true;
				
			}

			if (positionPipe3 < bird.X && positionPipe3 > bird.X-0.3 && pipe3.passed == false) {
				StdAudio.play("pp.wav"); // play sound. see README.txt (5)
				pipe1.passed = true;
				
			}
			//**********************************************************************************************************

			bird.degrees += bird.degchange; // make bird rotate

			// clear the background
			StdDraw.picture(0.5, 0.5, "background.png");

			// draw pipes
			//**********************************************************************************************************
			pipe1.drawPipe(positionPipe1 , 0.1, 0.15, gap1-0.1);
			pipe1.drawPipe(positionPipe1 , 1, 0.15, gap1+0.1, 180);
			StdDraw.filledSquare(positionPipe1, gap1, 0.02);
			
			pipe2.drawPipe(positionPipe2 , 0.1, 0.15, gap2-0.1);
			pipe2.drawPipe(positionPipe2 , 1, 0.15, gap2+0.1, 180);
			StdDraw.filledSquare(positionPipe2, gap2, 0.02);
			
			pipe3.drawPipe(positionPipe3 , 0.1, 0.15, gap3-0.1);
			pipe3.drawPipe(positionPipe3 , 1, 0.15, gap3+0.1, 180);
			StdDraw.filledSquare(positionPipe3, gap3, 0.02);
			//**********************************************************************************************************
			//draw bird
			StdDraw.picture(bird.X, bird.Y, "birdy.png", bird.degrees);
			//**********************************************************************************************************
			//show score
			//TODO: Create new font here
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.text(0.9, 0.9, ""+score);
			StdDraw.show(20);
		} 
	}

	public static void main(String[] args) {
		Load();
		while(true) {
			if(StdDraw.isKeyPressed(' '))
				Fly();
		}
	}
} 
