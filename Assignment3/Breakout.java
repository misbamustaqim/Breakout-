/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import sun.util.calendar.Gregorian;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	
	private GRect CreatePaddle;
	private GRect paddleToMove;
	private GOval Ball;
	
	
	private RandomGenerator rgen= RandomGenerator.getInstance();
	

	/** VELOCITY of x and y for ball
	 */
	private double Vx =  rgen.nextDouble(1.0 , 3.0);
	private double Vy = 2 ;
	
	private boolean mouseDragged = true;
	
	private GPoint Last_XYCoOrdinates = new GPoint(2, ( HEIGHT - PADDLE_Y_OFFSET ));

	/* Method: init() */
	/** Sets up the Breakout program. */
	public void init() {
		CreatWindow();
		CreateBrisk();
		CreatePaddle();
		addMouseListeners();
		BallInitialPosition();
		if(mouseDragged== true)
		{	
			MoveBallDownward();
			pause(10);
		}
		MoveUpWard();
		
	}

	

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		addMouseListeners();
	}
	
	private void CreatWindow() 
	{
		//print((WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW);
		GRect GameBoard = new GRect(0 , 0, WIDTH , HEIGHT );
		//print(" \n Width of window "  +WIDTH);
		//print(" \n Height of window "  +HEIGHT);
		add(GameBoard);
		//GCanvas m= new GCanvas();
		//add(m);
		
	}
	
	private void CreateBrisk() 
	{
		//GRect CreateBrisk1= new GRect(2 , BRICK_Y_OFFSET, BRICK_WIDTH, BRICK_HEIGHT);
		//add(CreateBrisk1);
		for(int j = 0 ; j < 10 ; j++ )
		{
			for (int i = 0; i < NBRICKS_PER_ROW; i++)
			{	
				GRect CreateBrisk= new GRect( 2 +(BRICK_WIDTH * i) + (BRICK_SEP* (i)) , BRICK_Y_OFFSET + (j * (BRICK_HEIGHT+ BRICK_SEP)), BRICK_WIDTH, BRICK_HEIGHT);
				CreateBrisk.setFilled(true);
				if((j <= 2))
				{
					CreateBrisk.setColor(Color.RED);
				}
				if(( j > 2 ) && (j <= 4))
				{
					CreateBrisk.setColor(Color.ORANGE);
				}
				
				if(( j > 4 ) && (j <= 6))
				{
					CreateBrisk.setColor(Color.YELLOW);
				}
				
				if(( j > 6 ) && (j <= 8))
				{
					CreateBrisk.setColor(Color.GREEN);
				}
				
				if(( j > 8 ) && (j <= 10))
				{
					CreateBrisk.setColor(Color.CYAN);
				}
				add(CreateBrisk);
				//print("\n X- Co-Ordinate " +(2 +(BRICK_WIDTH * i) + (BRICK_SEP* (i))));
			}
		}
	}

	private void CreatePaddle()
	{
		
		CreatePaddle=new GRect( 2, ( HEIGHT - PADDLE_Y_OFFSET ) , PADDLE_WIDTH , PADDLE_HEIGHT );
		CreatePaddle.setFilled(true);
		CreatePaddle.setFillColor(Color.RED);
		//print("\n " + ( HEIGHT - PADDLE_Y_OFFSET ));
		add(CreatePaddle);
		
	}
	

	public void mousePressed(MouseEvent e)
	{
		Last_XYCoOrdinates = new GPoint( e.getPoint());
		GRect tempPaddle  = (GRect) getElementAt(Last_XYCoOrdinates);
		if (tempPaddle == CreatePaddle)
		{
			print(" \n Last_XYCoOrdinates "  +Last_XYCoOrdinates);
			paddleToMove = tempPaddle;
		}
		else
			paddleToMove=null;
		
	}
	
	public void mouseDragged(MouseEvent e)
	{	
		int newDistance = (int) ( e.getX() - Last_XYCoOrdinates.getX() );
		int max = (int) (WIDTH - paddleToMove.getX() - PADDLE_WIDTH);
		
			if( paddleToMove != null )
			{
				if (( max  >= newDistance) && ( paddleToMove.getX() + newDistance >= 2))
				{
					paddleToMove.move(( e.getX() - Last_XYCoOrdinates.getX()), 0) ;			 
					Last_XYCoOrdinates = new GPoint( e.getPoint());
					//print(" \n NEW Last_XYCoOrdinates "  +Last_XYCoOrdinates);
					mouseDragged =true;
				}
			}
			mouseDragged=false;
	}
	private void BallInitialPosition() 
	{
		Ball= new GOval( (WIDTH /2) - (BALL_RADIUS) , (HEIGHT / 2) - (BALL_RADIUS) , 2* BALL_RADIUS , 2* BALL_RADIUS);
		Ball.setFilled(true);
		add(Ball);
		print("center   " +((WIDTH /2) - (BALL_RADIUS)));
		print("\n"+((HEIGHT / 2) - (BALL_RADIUS)));
	}



	private void MoveBallDownward()
	{
		while( Ball.getY() < ( HEIGHT - (2 * BALL_RADIUS) ))
		{ 
		 	
			if(Ball.getX() < WIDTH - (2 * BALL_RADIUS))
			{
				Ball.move(Vx, Vy);
			}
			else
				break;
		}
		
	}



	private void MoveUpWard() {
		// TODO Auto-generated method stub
		
	}



}























