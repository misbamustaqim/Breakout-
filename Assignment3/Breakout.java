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
	private static int NTURNS = 3;
	
	private static int NumberOfBriks = NBRICK_ROWS* NBRICKS_PER_ROW;
	
	private GRect CreatePaddle;
	private GRect paddleToMove;
	private GOval Ball;

	
	
	private RandomGenerator rgen= RandomGenerator.getInstance();
	private GRect [] [] arrayForBriks= new GRect[10][10];

	/** VELOCITY of x and y for ball
	 */
	private double Vx = rgen.nextDouble(3,8);         // rgen.nextDouble(1.0 , 10);
	private double Vy = 7; 
	
	
	private boolean isMouseClicked = false;
	
	private GPoint Last_XYCoOrdinates = new GPoint(2, ( HEIGHT - PADDLE_Y_OFFSET ));

	/* Method: init() */
	/** Sets up the Breakout program. */
	public void init() 
	{
		
		CreatWindow();
		CreateBrisk();
		CreatePaddle();
		//addMouseListeners();
	
		BallInitialPosition();
		
		
	}

	

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		addMouseListeners();
		
		while(true)
		{
			pause(50);
			if(isMouseClicked== true )
			{
				 
					MovaBall();
				
			}	
		}
		
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
				arrayForBriks[i][j]= new GRect( 2 +(BRICK_WIDTH * i) + (BRICK_SEP* (i)) , BRICK_Y_OFFSET + (j * (BRICK_HEIGHT+ BRICK_SEP)), BRICK_WIDTH, BRICK_HEIGHT);
				arrayForBriks[i][j].setFilled(true);
				if((j < 2))
				{
					arrayForBriks[i][j].setColor(Color.RED);
				}
				if(( j >=2 ) && (j <= 3))
				{
					arrayForBriks[i][j].setColor(Color.ORANGE);
				}
				
				if(( j > 3 ) && (j <= 5))
				{
					arrayForBriks[i][j].setColor(Color.YELLOW);
				}
				
				if(( j > 5 ) && (j <= 7))
				{
					arrayForBriks[i][j].setColor(Color.GREEN);
				}
				
				if(( j > 7) && (j <10))
				{
					arrayForBriks[i][j].setColor(Color.CYAN);
				}
				add(arrayForBriks[i][j]);
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
			
			paddleToMove = tempPaddle;
			isMouseClicked=true;
			
		}
		else
		{
			paddleToMove=null;
			//isMouseClicked = false;
		}
		
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
					
				}
			}
			
	}
	private void BallInitialPosition() 
	{
		Ball= new GOval( (WIDTH /2) - (BALL_RADIUS) , (HEIGHT / 2) - (BALL_RADIUS) , 2* BALL_RADIUS , 2* BALL_RADIUS);
		Ball.setFilled(true);
		add(Ball);
	
	}



	private void MovaBall()
	{
		int bottomWall = ( HEIGHT - ( 2 * BALL_RADIUS));
		int rightWall = ( WIDTH - ( 2 * BALL_RADIUS));
		int leftWall= 0;
		int topWall = 0;
	
		
		while( ( Ball.getX() > rightWall)  ||  (Ball.getY() > bottomWall)   ||   (Ball.getY() > topWall)     ||      (Ball.getX() > leftWall)  || ( NumberOfBriks != 0))
		{
			/* if(isMouseMoving == false)
			{
				continue;
			}*/

			
					pause(5);
					int distanceToRightWall = (int) (rightWall - Ball.getX());
					int distanceToBottomWall = (int) (bottomWall - Ball.getY()); 
					int distanceToLeftWall = (int) (  Ball.getX() - leftWall);
					int tempVelocityInXDirection = (int) Vx;
					int distanceToTopWall = (int) (Ball.getY() - topWall);
					
					int yCoOrdinateOfPaddle = (int) CreatePaddle.getY();
					int xCoOrdinateOfPaddle = (int) CreatePaddle.getX();
					int xCoOrdinateOfBallNearPaddle = (int) Ball.getX() + (2 *BALL_RADIUS );
					int yCoOrdinateOfBallNearPaddle = (int) Ball.getY() + (2 *BALL_RADIUS );
					if ((distanceToRightWall <= Vx))
					{
						Ball.move(distanceToRightWall, Vy);
						Vx = -Vx;
					}
					else if((distanceToLeftWall <= -Vx))
					{
						Ball.move( -distanceToLeftWall, Vy);
						Vx = -Vx;
					}
					else if( (distanceToBottomWall <= Vy))
					{
						Ball.move(Vx, distanceToBottomWall);
						Vy=-Vy;
						
					/*	Ball.setVisible(false);
						NTURNS --;
						isMouseClicked=false;
						if( NTURNS > 0)
						{
							
							BallInitialPosition();
							
							break;
						}
						
						
						else
						{
							GameOver();
						}
						*/
					}
					else if( (distanceToTopWall <=-Vy))
					{
						Ball.move(Vx ,-distanceToTopWall);
						Vy=-Vy;
					}
					else if( (0 <= ( xCoOrdinateOfBallNearPaddle - xCoOrdinateOfPaddle ))   && ((xCoOrdinateOfBallNearPaddle - xCoOrdinateOfPaddle ) <= (PADDLE_WIDTH - 2 * BALL_RADIUS))  &&  (yCoOrdinateOfPaddle - yCoOrdinateOfBallNearPaddle) <= Vy   )
					{
						Ball.move(Vx, (yCoOrdinateOfPaddle - yCoOrdinateOfBallNearPaddle));
						Vy=-Vy;
					}
			
					else if( (Ball.getY() - (arrayForBriks[0][9].getY() + BRICK_HEIGHT) )<= -Vy)
					{
						
						{
							for(int j = 0 ; j < 10 ; j++ )
							{
								for (int i = 0; i < NBRICKS_PER_ROW; i++)
								{
									if(arrayForBriks[i][j].isVisible())
									{
									double y1Bricks = arrayForBriks[i][j].getY() + BRICK_HEIGHT;
									double x1Bricks = arrayForBriks[i][j].getX() -1 ;
									double XOfCircle = Ball.getX() + BALL_RADIUS;
									double yOfCircle = Ball.getY();
									
									
									double y2Bricks = arrayForBriks[i][j].getY() ;
									double x2Bricks = arrayForBriks[i][j].getX()  ;
									double X2OfCircle = Ball.getX() ;
									double y2OfCircle = Ball.getY() + (2 * BALL_RADIUS);
									
									boolean bottomEdgeMax = ( (yOfCircle - y1Bricks) < -Vy);
									boolean bottomEdgeMin = ( ( yOfCircle -y1Bricks ) >= 0);
									boolean leftEdge = (x1Bricks <= XOfCircle);
									boolean rightEdge = XOfCircle <= (x1Bricks + BRICK_WIDTH +1);
									
									boolean bottomEdgeMax2 = ( (y2OfCircle - y2Bricks) <= Vy);
									boolean bottomEdgeMin2 = ( ( y2OfCircle -y2Bricks ) >= 0);
									boolean leftEdge2 = (x2Bricks <= X2OfCircle);
									boolean rightEdge2 = X2OfCircle <= (x2Bricks + BRICK_WIDTH +1);
							
									if(  ( bottomEdgeMax && bottomEdgeMin  &&   leftEdge && rightEdge)   || (bottomEdgeMax2 && bottomEdgeMin2  &&   leftEdge2 && rightEdge2) )
									{
									
										arrayForBriks[i][j].setVisible(false);
										NumberOfBriks--;
										Vy=-Vy;
										Ball.move(Vx, Vy);
										
									
									}
									
									if( NumberOfBriks == 0)
									{
										GLabel Win = new GLabel( " You Win The Game ",(WIDTH /2), (HEIGHT / 2));
										Win.setFont(new Font("Serif", Font.BOLD, 18));
										add(Win);
										isMouseClicked=false;
										return;
										
									}
								
									}
								}
							}
							Ball.move(Vx, Vy);
						}
						
				
					}
					else
						Ball.move(Vx, Vy);
				
				
			
		}
		
	}

	
	private void GameOver()
	{
		GLabel GameOver=new GLabel("GAME OVER" , (WIDTH /2), (HEIGHT / 2));
		GameOver.setFont(new Font("Serif", Font.BOLD, 18));
		add(GameOver);
	}
}























