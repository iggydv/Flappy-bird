import java.awt.*;

public class Littlebirdy
{
  public static void Load()
  {
     Font name = new Font("Game", Font.BOLD, 30); // create new font
     StdDraw.picture(0.5,0.5,"MainMenu.png"); // picture was made in paint
     StdDraw.show(10);// show image for 1000ms
     StdAudio.play("mb_new.wav"); // play sound. see READ ME.txt (1)
  }
  
  public static void GameOver(int score)
  {
    double rx = -0.5; // x position of bird
    StdAudio.play("hit2.wav");// play sound. see README.txt (2)
    StdDraw.setPenColor(StdDraw.RED);//change pen colour
    StdDraw.text((rx-0.3) , 0.9, ""+score); // write score at indicated postion
    StdDraw.text(0.0 , 0.0, "GAME OVER!"); // write text
    StdDraw.show(20);//show for 20ms
  }
  
  public static void fall(double vy, double ry, double gravity, double degrees, double gap, double gap1, double gap2, double vx, double vx1, double vx2, int score)
  {
    StdAudio.play("mb_sc.wav");// play sound. see README.txt (3)
    while(ry>-1.1) // while not at bottom
    {
      double rx = -0.5;
      degrees += -1; //tilt of bird
      vy = vy - gravity;//velocity becomes negative
      ry += vy;//falling of bird
      
      //draw background
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.filledSquare(0, 0, 1.2);
                             
      // draw pipes
      StdDraw.setPenColor(StdDraw.BLACK); 
      StdDraw.filledRectangle(vx , 0.0, 0.23, 1.5);
      StdDraw.filledRectangle(vx1, 0.0, 0.23,1.5);    
      StdDraw.filledRectangle(vx2, 0.0, 0.23,1.5);
                 
      //draw gap
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.filledSquare(vx, gap, 0.32);
      StdDraw.filledSquare(vx1, gap1, 0.32);
      StdDraw.filledSquare(vx2, gap2, 0.32);
      
      //draw bird. see README.txt (4)
      StdDraw.picture(-0.5, ry, "birdy.png",degrees);
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.text((rx-0.3) , 0.9, ""+score); // write score at indicated postion
      StdDraw.show(20);
    }
  }

  public static void Fly()
  { 
    // set the scale of the coordinate system
        double rx = 0.1, ry = 0.2;      // position
        double vy = 0.0;                // velocity
        double gravity = 0.0020;        // gravity
        double jump = 0.030;            // jump
        double radius = 0.055;          // radius

        //velocity of each individual pipe
        double vx = 2.5;
        double ax = -0.01;
        double vx1 = 3.5;
        double ax1= -0.01;
        double vx2 = 4.5;
        double ax2 = -0.01;
        double degrees = 0;
        double degchange = -1;
        
        //generate random positions for gap of each pipe
        double gap = (Math.random()*(0.6-(-0.6))-0.6);
        double gap1 = (Math.random()*(0.6-(-0.6))-0.6);
        double gap2 = (Math.random()*(0.6-(-0.6))-0.6);
        
        int score = 0;
        StdDraw.picture(rx, ry, "birdy.png",degrees);
        // main loop
        while (true)  { 

         if (Math.abs(ry + vy)>  1.1 - radius)//while not at bottom of window
         {
            GameOver(score);//call gameover function
            break;
         }
         
         // set pillars to origional position
         //*************************************************************************************************************
         if(((vx + ax) < -1.5 - radius)) //pillar 2
         {
           vx = 1.5;
           gap = (Math.random()*(0.6-(-0.6))-0.6);
         }
         
         if(((vx1 + ax1) < -1.5- radius)) // pillar 2
         {
           vx1 = 1.5;
           gap1 = (Math.random()*(0.6-(-0.6))-0.6);
         }
         
         if(((vx2 + ax2) < (-1.5 - radius))) // pillar 3
         {
           vx2 = 1.5;
           gap2 = (Math.random()*(0.6-(-0.6))-0.6);
         }
         //*************************************************************************************************************
         //pillar hit detection
         //*************************************************************************************************************        
         if(((vx-0.36)<=rx)&&((vx-0.36)>=-0.51))  //detects whether bird & pillar are at  same x position
         {
          if((ry+0.05)>=(gap+0.32)||((ry-0.05)<=(gap-0.32))) 
          {
            fall(vy, ry, gravity*2, degrees, gap , gap1, gap2, vx , vx1, vx2, score); //fall function is called
            GameOver(score);// GameOver function is called
            break; //exit loop
          }
         }
         
         if(((vx-0.36)<= rx)&&((vx-0.36)<=-0.51)&&((vx+0.36)>= rx)) //detect hits when bird is inside piller
         if((ry+0.05)>=(gap+0.26)||((ry-0.05)<=(gap-0.26))) 
            {
               fall(vy*0.3, ry, gravity*2, degrees, gap , gap1, gap2, vx , vx1, vx2, score); //fall function is called
               GameOver(score);// GameOver function is called
               break; //exit loop
            }

         if(((vx1-0.36)<=rx)&&((vx1-0.36)>=-0.51))  //detects whether bird & pillar are at  same x position
         {
          if((ry+0.05)>=(gap1+0.32)||((ry-0.05)<=(gap1-0.32))) 
          {
            fall(vy, ry, gravity, degrees, gap , gap1, gap2, vx , vx1, vx2, score);//fall function is called
            GameOver(score);// GameOver function is called
            break;//exit loop
          }
         }
         
         if(((vx1-0.36)<= rx)&&((vx1-0.36)<=-0.51)&&((vx1+0.36)>= rx))//detect hits when bird is inside piller
         if((ry+0.05)>=(gap1+0.26)||((ry-0.05)<=(gap1-0.26))) 
            {
               fall(vy*0.3, ry, gravity*2, degrees, gap , gap1, gap2, vx , vx1, vx2, score); //fall function is called
               GameOver(score);// GameOver function is called
               break; //exit loop
            }
         
         if(((vx2-0.36)<=rx)&&((vx2-0.36)>=-0.51))  //detects whether bird & pillar are at  same x position
         {
          if((ry+0.05)>=(gap2+0.32)||((ry-0.05)<=(gap2-0.32))) 
          {
            fall(vy, ry, gravity, degrees, gap , gap1, gap2, vx , vx1, vx2, score);//fall function is called
            GameOver(score);// GameOver function is called
            break;//exit loop
          }
         }
         
         if(((vx2-0.36)<= rx)&&((vx2-0.36)<=-0.51)&&((vx2+0.36)>= rx)) //detect hits when bird is inside piller
         if((ry+0.05)>=(gap2+0.26)||((ry-0.05)<=(gap2-0.26))) 
            {
               fall(vy*0.3, ry, gravity*2, degrees, gap , gap1, gap2, vx , vx1, vx2, score); //fall function is called1
               GameOver(score);// GameOver function is called
               break; //exit loop
            }
         //*************************************************************************************************************       
            // flap when spacebar is pressed
            if(StdDraw.isKeyPressed(' '))
            {
              vy =  jump; // set velocity to 'jump'
              degrees = 20; // rotation to 20 degrees
            }

            //bird movement
            vy = vy - gravity;
            ry += vy;

            // give pipes a certain velocity
            vx = vx + ax;
            vx1 = vx1 + ax1;
            vx2 = vx2 + ax2;
            
            //check if successfully passed through pipe
            //*************************************************************************************************************
            while((vx<=rx)&&(vx>=-0.51))
            {
              StdAudio.play("pp.wav"); // play sound. see README.txt (5)
              score = score + 1;
              break; 
            }

            while((vx1<=rx)&&(vx1>=-0.51))
            {
              StdAudio.play("pp.wav"); // play sound. see README.txt (5)
              score = score + 1;
              break; 
            }
            
            while((vx2<=rx)&&(vx2>=-0.51))
            {
              StdAudio.play("pp.wav"); // play sound. see README.txt (5)
              score = score + 1;
              break; 
            }
            //**********************************************************************************************************

            degrees += degchange; // make bird rotate

            // clear the background
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledSquare(0, 0, 1.2);

            // draw pipes
            //**********************************************************************************************************
            StdDraw.setPenColor(StdDraw.BLACK); 
            StdDraw.filledRectangle(vx , 0.0, 0.23, 1.5);
            StdDraw.filledRectangle(vx1, 0.0, 0.23,1.5);
            StdDraw.filledRectangle(vx2, 0.0, 0.23,1.5);
            //**********************************************************************************************************

            //draw gap
            //**********************************************************************************************************
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledSquare(vx, gap, 0.32);
            StdDraw.filledSquare(vx1, gap1, 0.32);
            StdDraw.filledSquare(vx2, gap2, 0.32);
            //**********************************************************************************************************

            //draw bird
            StdDraw.picture(rx, ry, "birdy.png",degrees);

            //show score 
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text((rx-0.3) , 0.9, ""+score);
            StdDraw.show(20);  
        } 
    }
  
   public static void main(String[] args)//main function
   {
     Load(); // show loading screen
     while(true) // call continiously
     {
      if(StdDraw.isKeyPressed(' ')) // checks if user wants to restart
      Fly(); // call fly function
     }
   }
} 
