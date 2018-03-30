/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  @author       :  Matthew Abell
 *  Date written  :  2018-03-15
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class 
 *                   for homework #5
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-15  Matthew Abell Initial writing
 *  @version 1.1.0  2018-03-27  Matthew Abell Final working version
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.text.DecimalFormat;

public class Ball {
	
    //Class field definintions
	private double xpos;
	private double ypos;
	private double xvel; 
	private double yvel; //all data stored in inches
	private double timeslice;

	//Constructor
	public Ball (double xpos, double ypos, double xvel, double yvel, double timeslice){
		this.xpos = xpos;
		this.ypos = ypos;
		this.xvel = xvel;
		this.yvel = yvel;
		this.timeslice = timeslice;
	}

	//Method to update the position of the ball
	public void updatePos(){
		xpos = xpos + (xvel * timeslice);
		ypos = ypos + (yvel * timeslice);
	}

	/**
    *  Method to return the position of the ball
    *  @return  double array:array containing the x and y posistion of the ball
    */
	public double [] returnPos(){
		double [] ans = {xpos,ypos};
		return ans;
	}

	//Method to update the velocity of the ball
	public void updateVel(){
		if(Math.abs(xvel) > 1){
			xvel = xvel - (xvel*.01*timeslice);
		}	
		else{
			xvel = 0;
		}
		if(Math.abs(yvel) > 1){
			yvel = yvel - (yvel*.01*timeslice);
		}
		else{
			yvel = 0;
		}	
	}

	/**
    *  Method to check if the ball is moving
    *  @return  boolean: positive if the ball is moving
    */
	public boolean isMoving(){
		if (xvel == 0 && yvel == 0){
			return false;
		}
		else{
			return true;
		}
	}

	/**
    *  Method to check if the ball is touching another ball
    *  @param   argValue  double array that contains the location of the other ball
    *  @return  boolean: positive if the two ball are touching
    */
	public boolean collision(double [] Ball2){
		double dist = Math.sqrt((xpos-Ball2[0])*(xpos-Ball2[0]) + (ypos-Ball2[1])*(ypos-Ball2[1]));
		if (dist < 8.9){ //8.9 inches is the diameter of the soccer balls
			return true;
		}
		return false;
	}

	/**
    *  Method to create a string representation of the ball
    *  @return  String: string containing information about the ball
    */
	public String toString(){
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		String xposstring = (numberFormat.format(xpos/12)); 
		String yposstring = (numberFormat.format(ypos/12));
		String xvelstring = (numberFormat.format(xvel/12));
		String yvelstring = (numberFormat.format(yvel/12)); // /12 is to convert inches to feet
		return (" Position: " + xposstring + "," + yposstring + " Velocity: " + xvelstring + "," + yvelstring);
	}
}
