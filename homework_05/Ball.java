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
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Ball {

	private double xpos;
	private double ypos;
	private double xvel;
	private double yvel;

	public Ball (double xpos, double ypos, double xvel, double yvel){
		this.xpos = xpos;
		this.ypos = ypos;
		this.xvel = xvel;
		this.yvel = yvel;
	}

	public void updatePos(double timeslice){
		xpos = xpos + (xvel * timeslice);
		ypos = ypos + (yvel * timeslice);
	}

	public void updateVel(double timeslice){
		double vel = Math.sqrt(xvel*xvel + yvel*yvel);
		double ang = Math.atan(yvel/xvel);
		if (vel >= 1){
			vel = vel - (vel*.01*timeslice);
			xvel = Math.cos(ang) * vel;
			yvel = Math.sin(ang) * vel;
		}
		else {
			xvel = 0;
			yvel = 0;
		}	
	}

	public String toString(){
		String xposstring = Double.toString(xpos/12);
		String yposstring = Double.toString(ypos/12);
		String xvelstring = Double.toString(xvel/12);
		String yvelstring = Double.toString(yvel/12);
		return ("There is a ball located at " + xposstring + "," + yposstring + ". The current velcity is " + xvelstring + "," + yvelstring + ".");
	}
}
