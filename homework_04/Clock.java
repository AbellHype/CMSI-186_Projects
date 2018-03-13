/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  Matthew Abell
 *  Date written  :  2018-03-13
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None needed (handled by ClockSolver)
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2018-03-13  Matthew Abell Working release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private double currentTime;
   private double angleDif;
   private double timeSlice;

  /**
   *  Constructor goes here
   */
   public Clock(double angleDif, double timeSlice) {
      if (validateAngleArg(angleDif)){
        this.angleDif = angleDif;
      }
      if(validateTimeSliceArg(timeSlice)){
        this.timeSlice = timeSlice;
      }
      this.currentTime = 0;
   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   */
   public void tick() {
      currentTime = currentTime + timeSlice;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  Double from ClockSolver
   *  @return  boolean: positive if the input is valid
   */
   public boolean validateAngleArg( double argValue ) {
      if (argValue > 0 && argValue < MAXIMUM_DEGREE_VALUE) {
        return true;
      }
      else {
        System.out.println("Invalid angle input, please input a value in degrees between 0 and 360 degrees.");
        System.exit(1);
        return false;
      }
    }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  Double from ClockSolver
   *  @return boolean: positive if the input is valid
   */
   public boolean validateTimeSliceArg( double argValue ) {
      if (argValue > 0 && argValue < 1800.0) {
        return true;
      }
      else {
        System.out.println("Invalid time slice input, please input a value in seconds between 0 and 1800 seconds.");
        System.exit(1);
        return false;
      }
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      return (.1/12) * currentTime;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      double totalAngle = .1 * currentTime;
      return totalAngle % 360; 
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      double totalAngle = Math.abs(getHourHandAngle() - getMinuteHandAngle());
      if (totalAngle >  180) {
        return totalAngle - 180;
      }
      else {
        return totalAngle;
      }
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return currentTime;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
      int hours = (int)Math.floor(currentTime / 3600);
      double secondsRem = currentTime % 3600;
      int minutes = (int)Math.floor(secondsRem / 60);
      double seconds = secondsRem % 60;
      seconds = (Math.floor(seconds * 100))/100;
      return "Time: " + hours + ":" + minutes + ":" + Double.toString(seconds);
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                         "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock(10,10);
      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      //try { System.out.println( (0.0 == clock.validateAngleArg( 0 )) ? " - got 0.0" : " - no joy" ); }
      //catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}
