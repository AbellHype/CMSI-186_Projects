/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  Matthew Abell
 *  Date written  :  2018-03-13
 *  Description   :  This class finds the time values at the inputteed angles
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2018-03-13  Matthew Abell Working release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private static double angleDif;
   private static double timeSlice;

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"

      System.out.println( "Hello world, from the ClockSolver program!!\n\n" ) ;
      if( 0 == args.length || args.length > 2) {
         System.out.println( "   Sorry you must enter one or two inputs\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit(1);
      }
      try{
        angleDif = Double.parseDouble(args[0]);
        if (args.length == 2){
          timeSlice = Double.parseDouble(args[1]);
        }
        else {
          timeSlice = DEFAULT_TIME_SLICE_SECONDS;
        }
      }
      catch( NumberFormatException nfe ) {
        System.out.println( "Not a valid input, please input a double" );
        System.exit(1);
      }
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      ClockSolver cse = new ClockSolver();
      cse.handleInitialArguments( args );
      Clock clock = new Clock(angleDif,timeSlice);
      System.out.println("Looking for time when the angle between the hands is " + args[0] + " degrees.");
      System.out.println("Epsilon value is .1, tick length is " + Double.toString(timeSlice) + " seconds.");
      while( clock.getTotalSeconds() < 43200 ) {
         if (Math.abs(clock.getHandAngle() - angleDif) < .1){
            System.out.println(clock.toString());
         }
         clock.tick();
      }
      System.exit( 0 );
   }
}
