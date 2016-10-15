
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
	
	private double Vx =7;    //rgen.nextDouble(1.0 , 11.0);
	

	private double Vy = rgen.nextDouble(1.0 , 10.0); ;

	public void run()
	{
		if (rgen.nextBoolean( 0.5 ))    Vx=-Vx;
	   	 CreatWindow();
	  

	   	 BallInitialPosition();
		MoveBallDownward();
			 MoveUpWard();
	
	}

	private void MoveUpWard()
	{
		double RightWall= (WIDTH - 2* BALL_RADIUS) - (( WIDTH /2) -BALL_RADIUS ) ;
		double bottomWall=  (HEIGHT - 2* BALL_RADIUS) - (( HEIGHT /2) -BALL_RADIUS ) ;
		int centerX=(WIDTH /2) - (BALL_RADIUS);
		int centerY= (HEIGHT / 2) - (BALL_RADIUS);

		while (  ((Ball.getX() - centerX) < RightWall) &&   ( (Ball.getY() - centerY) < bottomWall) && (Ball.getX() > 0 ) )   
		{	
			if  (( Ball.getX() <  RightWall )  &&  (Ball.getX() > 0 ) )
			{
				print(" \n Ball.getX() " +Ball.getX() );
				print("\n (WIDTH /2) - (BALL_RADIUS) " +((WIDTH /2) - (BALL_RADIUS)));
				print(" \n RightWall " +RightWall);
				pause(50);
				Ball.move(Vx, Vy);
				
			}
			
			if (Ball.getY() < bottomWall) 
			{
				pause(50);
				Ball.move(Vx, Vy);
			}
			
			
		}
			
	
		
	}

	private void MoveBallDownward() {
		// TODO Auto-generated method stub
		
	}

	private void BallInitialPosition() {
		
		Ball= new GOval( (WIDTH /2) - (BALL_RADIUS) , (HEIGHT / 2) - (BALL_RADIUS) , 2* BALL_RADIUS , 2* BALL_RADIUS);
		Ball.setFilled(true);
		add(Ball);
	}

	private void CreatWindow() {
		
		GRect GameBoard = new GRect(0 , 0, WIDTH , HEIGHT );
		
		add(GameBoard);
	}
		
		
	}
