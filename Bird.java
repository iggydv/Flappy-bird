
public class Bird {
	
	double X = 0.1;
	double Y = 0.5;
	double verticalVelocity = 0.0;
	double gravity = 0.0020;
	double jump = 0.030;  
	double radius = 0.055;
	double degrees = 0;
	double degchange = -1;
	
	public void flap() {
		verticalVelocity =  jump; // set velocity to 'jump'
		degrees = 20; // rotation to 20 degrees
	}

}
