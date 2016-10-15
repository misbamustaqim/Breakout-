import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import sun.util.calendar.Gregorian;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class BouncingBall extends GraphicsProgram{
	private static final int BALL_DIAMETER =30;
	private static final int GRAVITY =3;
	private static final int DELAY =50;
	private static final int X_LOCATION =BALL_DIAMETER/2;
	private static final int Y_LOCATION =100;
	private static final int X_START =BALL_DIAMETER/2;
	private static final int Y_START=100;
	private static final double REDUCE =0.9;
	
	private GOval ball;
	private double x_Vel=5;
	private double y_Vel= 0;
	public void run()
	{
		ball = new GOval( X_LOCATION, Y_LOCATION, BALL_DIAMETER ,BALL_DIAMETER);
		ball.setFilled(true);
		add(ball);
		while( ball.getX() < getWidth())
		{
			moveBall();
			//CheckForBall();
			pause(DELAY);
		}
		
	}
	private void moveBall()
	{
		y_Vel = y_Vel + GRAVITY;
		ball.move(x_Vel, y_Vel);
		
	}
	//private void CheckForBall() 
	{
	//	if ( ball.getY() > ( getHeight() - BALL_DIAMETER ) )
		{
		//	y_Vel = - y_Vel * REDUCE;
		}
	}

}
