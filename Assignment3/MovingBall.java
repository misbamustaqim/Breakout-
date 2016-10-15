import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class MovingBall extends GraphicsProgram
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
	
	private double Vx = rgen.nextDouble(1.0 , 10.0);
	

	private double Vy = rgen.nextDouble(1.0 , 10.0); ;

	public void run()
	{
		if (rgen.nextBoolean( 0.5 ))    Vx=-Vx;
	   	 CreatWindow();
	  

	   	 BallInitialPosition();
		MoveBallDownward();
			 MoveUpWard();
	
	}
	
	
 public void BallInitialPosition() {
	Ball= new GOval( (WIDTH /2) - (BALL_RADIUS) , (HEIGHT / 2) - (BALL_RADIUS) , 2* BALL_RADIUS , 2* BALL_RADIUS);
	Ball.setFilled(true);
	add(Ball);
		
	}


void MoveUpWard() {
		// TODO Auto-generated method stub
		
	}

	private void MoveBallDownward()
	{
			int rightWall= WIDTH -( 2 * BALL_RADIUS) ;
			int LeftWall = 0;
			//int UpperWall = 0;
			int bottomWall = WIDTH -( 2 * BALL_RADIUS) ;
			
			 
			
			while (   (Ball.getX() > LeftWall )  ||  ( Ball.getX() < rightWall )  || (Ball.getY() < bottomWall)  )
			{
			
				if (  (Ball.getX()  > LeftWall )  ||  ( Ball.getX() < rightWall )  )
				{
					Ball.move(Vx, Vy);
				}
				else 
					break;
			}
					
		
	}

	private void CreatWindow() 
	{
		
		GRect GameBoard = new GRect(0 , 0, WIDTH , HEIGHT );
		
		add(GameBoard);
		
		
	}
	




 
			
		
	
}
