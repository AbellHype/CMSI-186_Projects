/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  CountTheDays.java
 *  Purpose       :  Find days between 2 dates
 *  Author        :  Matthew Abell
 *  Date          :  2018-01-25
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class CountTheDays {
	public static void main( String args [] ) {
		if (args.length == 6) {
			long month1 = Long.parseLong( args[0] );
			long day1 = Long.parseLong( args[1] );
			long year1 = Long.parseLong( args[2] );
			long month2 = Long.parseLong( args[3] );
			long day2 = Long.parseLong( args[4] );
			long year2 = Long.parseLong( args[5] );
			if ( CalendarStuff.isValidDate( month1, day1, year1 ) && CalendarStuff.isValidDate( month2, day2, year2 ) ){
				System.out.println(CalendarStuff.daysBetween( month1, day1, year1, month2, day2, year2 ));
			} else {
				System.out.println( "Date is invalid" );
				System.exit( -1 );
			}
		} else {
			System.out.println( "Please enter 2 dates with 3 values each (month, day, year)" );
			System.exit( -1 );
		}
	}
}
