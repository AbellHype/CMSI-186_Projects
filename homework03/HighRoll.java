/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Plays a game using Die and DiceSet
 *  Author        :  Matthew Abell
 *  Date          :  2018-02-22
 *  Description   :  
 *  Notes         :  Some code was provided by B.J. Johnson for this program
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-02-22  Matthew Abell Initial writing and release 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll{
	public static void main( String[] args ) {
		if (args.length != 2){
			System.out.println("Invalid input, you must enter 2 values when starting the program (one for count and another for sides)");
			System.exit(1);
		}
		int count = Integer.parseInt(args[0]);
		int sides = Integer.parseInt(args[1]);
		int highScore = 0;
		DiceSet ds = new DiceSet(count,sides);
		BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
		while (true) {
			System.out.println("Input value associated with your desired option \n" +
			"1: Roll all the dice \n" +
			"2: Roll a single die (give a second input between 1 and " + args[0] + ")\n" +
			"3: Calculate the score for this set \n" +
			"4: Save this score as high schore \n" +
			"5: Display the high score \n" +
			"Q: Enter 'Q' to quit the program");
			String inputLine = null;
	        try {
	        	inputLine = input.readLine();
	        	if( 0 == inputLine.length() ) {
	        		System.out.println("Please enter in some text");
	        	}
	        	System.out.println( "   You typed: " + inputLine );
            	if( 'Q' == inputLine.charAt(0) ) {
               		break;
            	}
            	else if( '1' == inputLine.charAt(0) ) {
               		ds.roll();
               		System.out.println("You have rolled the dice.");
               		System.out.println(ds.toString() + "\n\n");
            	}
            	else if( '2' == inputLine.charAt(0) ) {
            		int dieIndex = Integer.parseInt( inputLine.substring( 2, inputLine.length() ) );
            		if (dieIndex > 0 && dieIndex <= count){
            			ds.rollIndividual(dieIndex - 1);
            			System.out.println("You rolled one die");
               			System.out.println(ds.toString() + "\n\n");
               		}
               		else{
               			System.out.println("Invalid input, that die does not exist");
               		}
            	}
            	else if( '3' == inputLine.charAt(0) ) {
               		System.out.println("Your current score is " + ds.sum() + "\n\n");
            	}
            	else if( '4' == inputLine.charAt(0) ) {
               		if (highScore < ds.sum()) {
               			highScore = ds.sum();
               			System.out.println(Integer.toString(highScore) + " has been saved as your high score" + "\n\n");
               		}
               		else {
               			System.out.println("Your current score is smaller than your high score. \n\n");
               		}
            	}
            	else if( '5' == inputLine.charAt(0) ) {
               		System.out.println("The high score is " + Integer.toString(highScore) + "\n\n");
            	}
            	else {
            		System.out.println("Please enter a valid input \n\n");
            	}
	        }
	        catch(IOException ioe) {
	        	System.out.println( "Caught IOException" );
	        }
	        catch( StringIndexOutOfBoundsException error ) {
          		System.out.println( "Invalid Input, please try again \n\n" );
			}
		}
   }
}
