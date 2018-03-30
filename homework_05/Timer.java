/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Timer.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  @author       :  Matthew Abell
 *  Date written  :  2018-03-20
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
 *  @version 1.0.0  2018-03-20  Matthew Abell Initial writing
 *  @version 1.1.0  2018-03-27  Matthew Abell Final working version
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Timer{
	
	//Class field definitions
	private double time = 0;
	private double timeSlice;

	//Constructor
	public Timer (double timeSlice){
		this.timeSlice = timeSlice;
	}

	//Method that 'ticks' the time
	public void Tick(){
		time = time + timeSlice;
	}

	/**
    *  Method to create a string representation of the time
    *  @return  String: string containing information about the time
    */
	public String toString(){
		int hours = (int)Math.floor(time / 3600);
     	double secondsRem = time % 3600;
     	int minutes = (int)Math.floor(secondsRem / 60);
     	double seconds = secondsRem % 60;
      	seconds = (Math.floor(seconds * 100))/100;
      	return "Time: " + hours + ":" + minutes + ":" + Double.toString(seconds);
	}
}
