import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll{
	public static void main( String[] args ) {
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
            	if( '1' == inputLine.charAt(0) ) {
               		ds.roll();
               		System.out.println("You have rolled the dice.");
               		System.out.println(ds.toString() + "\n\n");
            	}
            	if( '2' == inputLine.charAt(0) ) {
            		int dieIndex = Integer.parseInt( inputLine.substring( 2, inputLine.length() ) );
            		ds.rollIndividual(dieIndex - 1);
            		System.out.println("You rolled one die");
               		System.out.println(ds.toString() + "\n\n");

            	}
            	if( '3' == inputLine.charAt(0) ) {
               		System.out.println("Your current score is " + ds.sum() + "\n\n");
            	}
            	if( '4' == inputLine.charAt(0) ) {
               		highScore = ds.sum();
               		System.out.println(Integer.toString(highScore) + " has been saved as your high score" + "\n\n");
            	}
            	if( '5' == inputLine.charAt(0) ) {
               		System.out.println("The high score is " + Integer.toString(highScore) + "\n\n");
            	}
	        }
	        catch(IOException ioe) {
	        	System.out.println( "Caught IOException" );
	        }
		}
   }
}
