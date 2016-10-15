
import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;

import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class NewMovingBall extends GraphicsProgram
{
	private GOval Ball;
	
	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 600;
private RandomGenerator rgen= RandomGenerator.getInstance();

int rightWall= WIDTH -( 2 * BALL_RADIUS) ;
int LeftWall = 0;
//int UpperWall = 0;
int bottomWall = WIDTH -( 2 * BALL_RADIUS) ;
	
	private double Vx =rgen.nextDouble(1.0 , 11.0);
	

	private double Vy = rgen.nextDouble(1.0 , 10.0); ;

	public void run()
	{
		if (rgen.nextBoolean( 0.5 ))    Vx=-Vx;
	   	 CreatWindow();
	  

	   	 BallInitialPosition();
		MoveBallDownward();
			 MoveUpWard();
	
	}

	private void CreatWindow() {
		
		GRect GameBoard = new GRect(0 , 0, WIDTH , HEIGHT );
		
		add(GameBoard);
	}
		
	
	private void BallInitialPosition() {
		
		Ball= new GOval( (WIDTH /2) - (BALL_RADIUS) , (HEIGHT / 2) - (BALL_RADIUS) , 2* BALL_RADIUS , 2* BALL_RADIUS);
		Ball.setFilled(true);
		add(Ball);
	}


	private void MoveBallDownward()
	{
		int bottomWall = ( HEIGHT - ( 2 * BALL_RADIUS));
		int rightWall = ( WIDTH - ( 2 * BALL_RADIUS));
		int leftWall= 0;
		int topWall = 0;
		
		
		while(  ( Ball.getX() > rightWall)  ||  (Ball.getY() > bottomWall)   ||   (Ball.getY() > topWall)     ||      (Ball.getX() > leftWall))
		{
			pause(50);
			int distanceToRightWall = (int) (rightWall - Ball.getX());
			int distanceToBottomWall = (int) (bottomWall - Ball.getY()); 
			int distanceToLeftWall = (int) (  Ball.getX() - leftWall);
			int tempVelocityInXDirection = (int) Vx;
			int distanceToTopWall = (int) (Ball.getY() - topWall);
			if ((distanceToRightWall < Vx))
			{
				Ball.move(distanceToRightWall, Vy);
				Vx = -Vx;
			}
			else if((distanceToLeftWall < -Vx))
			{
				Ball.move( -distanceToLeftWall, Vy);
				Vx = -Vx;
			}
			else if( (distanceToBottomWall < Vy))
			{
				Ball.move(Vx, distanceToBottomWall);
				Vy=-Vy;
			}
			else if( (distanceToTopWall < -Vy))
			{
				Ball.move(Vx ,-distanceToTopWall);
				Vy=-Vy;
			}
			else
			{
				Ball.move(Vx, Vy);
			}
		}
	}

	private void MoveUpWard()
	{
		
				
	
		
	}



		
	}
