/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class BrobInt {

  public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
  public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
  public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// These are the internal fields
   private String internalValue = "";        // internal String representation of this BrobInt
   private byte   sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   private byte[] byteVersion   = null;      // byte array for storing the string values; uses the reversed string

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) {
      String valueNum;
      this.internalValue = value;
      if(value.charAt(0) == '-'){
        this.sign = 1;
        valueNum = value.substring(1); 
      }
      else{
        this.sign = 0;
        valueNum = value;
      }
      if(valueNum.length() % 2 == 0 && valueNum.length() > 1){
        this.byteVersion = new byte[valueNum.length() / 2];
        this.byteVersion[0] = Byte.parseByte(valueNum.substring(0,2));
        valueNum = valueNum.substring(2);
      }
      else{
        this.byteVersion = new byte[(valueNum.length() / 2) + 1];
        this.byteVersion[0] = Byte.parseByte(valueNum.substring(0,1));
        valueNum = valueNum.substring(1);
      }
      int c = 1;
      for(int i = 0; i < valueNum.length(); i = i + 2){
        this.byteVersion[c] = Byte.parseByte(valueNum.substring(i,i+2));
        c++;
      }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using byte array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt addByte( BrobInt gint ) {
      boolean isNeg = false;
      if (gint.sign == 1 && sign == 1){
        isNeg = true;
      }
      if (gint.sign == 1 && sign == 0){
        BrobInt gint2 = new BrobInt(gint.toString().substring(1));
        BrobInt thisVal = new BrobInt(internalValue);
        return thisVal.subtractByte(gint2);
      }
      if (gint.sign == 0 && sign == 1){
        BrobInt thisVal = new BrobInt(internalValue.substring(1));
        return gint.subtractByte(thisVal);
      }
      String ans = "";
      int carry = 0;
      int shortLength;
      if (byteVersion.length > gint.byteVersion.length){
        shortLength = gint.byteVersion.length;
      }
      else{
        shortLength = byteVersion.length;
      }
      int addRes;
      for(int i = 0; i < shortLength; i++){
        addRes = byteVersion[byteVersion.length-i-1] + gint.byteVersion[gint.byteVersion.length-i-1] + carry;
        if(addRes > 99){
          carry = 1;
          addRes = addRes - 100;
          if(addRes > 9){ 
            ans = Integer.toString(addRes) + ans;
          }
          else{
            ans = "0" + Integer.toString(addRes) + ans;
          }
        }
        else{
          carry = 0;
          if(addRes > 9){ 
            ans = Integer.toString(addRes) + ans;
          }
          else{
            ans = "0" + Integer.toString(addRes) + ans;
          }
        }
      }
      if (byteVersion.length > gint.byteVersion.length){
        for(int i = byteVersion.length - gint.byteVersion.length -1; i >= 0; i--){
          addRes = byteVersion[i] + carry;
          if(addRes > 99){
            carry = 1;
            addRes = addRes - 100;
            if(addRes > 9){ 
              ans = Integer.toString(addRes) + ans;
            }
            else{
              ans = "0" + Integer.toString(addRes) + ans;
            }
          }
          else{
            carry = 0;
            if(addRes > 9){ 
              ans = Integer.toString(addRes) + ans;
            }
            else{
              ans = "0" + Integer.toString(addRes) + ans;
            }
          }
        }
        if(carry == 1){
          ans = "1" + ans;
        }
      }
      else if(byteVersion.length == gint.byteVersion.length){
        if(carry == 1){
          ans = "1" + ans;
        }
      }
      else{
        for(int i = gint.byteVersion.length - byteVersion.length -1; i >= 0; i--){
          addRes = gint.byteVersion[i] + carry;
          if(addRes > 99){
            carry = 1;
            addRes = addRes - 100;
            if(addRes > 9){ 
              ans = Integer.toString(addRes) + ans;
            }
            else{
              ans = "0" + Integer.toString(addRes) + ans;
            }
          }
          else{
            carry = 0;
            if(addRes > 9){ 
              ans = Integer.toString(addRes) + ans;
            }
            else{
              ans = "0" + Integer.toString(addRes) + ans;
            }
          }
        }
        if(carry == 1){
          ans = "1" + ans;
        }
      }
      if(isNeg){
        ans = '-' + ans;
      }
      return new BrobInt(ans);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using bytes
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtractByte( BrobInt gint ) {
      if(sign == 0 && gint.sign == 1){
        BrobInt gint2 = new BrobInt(gint.internalValue.substring(1));
        BrobInt thisVal = new BrobInt(internalValue);
        return thisVal.addByte(gint2);
      }
      if(sign == 1 && gint.sign == 0){
        BrobInt thisVal = new BrobInt(internalValue);
        BrobInt gint2 = new BrobInt("-" + gint.internalValue);
        return thisVal.addByte(gint2);
      }
      if(sign == 1 && gint.sign == 1){
        BrobInt thisVal = new BrobInt(internalValue.substring(1));
        BrobInt gint2 = new BrobInt(gint.internalValue.substring(1));
        return gint2.subtractByte(thisVal);
      }
      boolean isNeg;
      String strAns;
      String high;
      String low;
      BrobInt lessVal;
      BrobInt ans = new BrobInt("0");
      BrobInt num1 = new BrobInt(internalValue);
      BrobInt num2 = gint;
      String str1 = num1.toString();
      while(str1.charAt(0) == '0'){
        str1 = str1.substring(1);
      }
      String str2 = num2.toString();
      while(str2.charAt(0) == '0' && str2.length() > 1){
        str2 = str2.substring(1);
      }
      num1 = new BrobInt(str1);
      num2 = new BrobInt(str2);
      if(num1.compareTo(num2) > 0){
        isNeg = false;
        high = num1.toString();
        low = num2.toString();
        lessVal = num2;
      }
      else{
        isNeg = true;
        high = num2.toString();
        low = num1.toString();
        lessVal = num1;
      }
      while(!high.equals(low)){
        ans = ans.addByte(ONE);
        lessVal = lessVal.addByte(ONE);
        low = lessVal.toString();
        while(low.charAt(0) == '0'){
          low = low.substring(1);
        }
      }
      if (isNeg){
        strAns = "-" + ans.toString();
      }
      else{
        strAns = ans.toString();
      }
      return new BrobInt(strAns);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) {
      boolean isNeg = false;
      BrobInt count = ZERO;
      String countStr;
      BrobInt gint2 = null;
      BrobInt thisVal = null;
      BrobInt ans = ZERO;
      if(sign == 0 && gint.sign == 0){
        isNeg = false;
        thisVal = new BrobInt(internalValue);
        String gintStr = gint.toString();
        while(gintStr.charAt(0) == '0'){
          gintStr = gintStr.substring(1);
        }
        gint2 = new BrobInt(gintStr);
      }
      if(sign == 0 && gint.sign == 1){
        isNeg = true;
        thisVal = new BrobInt(internalValue);
        String gintStr = gint.toString();
        while(gintStr.charAt(0) == '0' || gintStr.charAt(0) == '-'){
          gintStr = gintStr.substring(1);
        }
        gint2 = new BrobInt(gintStr);
      }
      if(sign == 1 && gint.sign == 0){
        isNeg = true;
        thisVal = new BrobInt(internalValue.substring(1));
        String gintStr = gint.toString();
        while(gintStr.charAt(0) == '0'){
          gintStr = gintStr.substring(1);
        }
        gint2 = new BrobInt(gintStr);
      }
      if(sign == 1 && gint.sign == 1){
        isNeg = false;
        thisVal = new BrobInt(internalValue.substring(1));
        String gintStr = gint.toString();
        while(gintStr.charAt(0) == '0' || gintStr.charAt(0) == '-'){
          gintStr = gintStr.substring(1);
        }
        gint2 = new BrobInt(gintStr);
      }
      while(!count.toString().equals(gint2.toString())){
        ans = ans.addByte(thisVal);
        count = count.addByte(ONE);
        countStr = count.toString();
        while(countStr.charAt(0) == '0'){
          countStr = countStr.substring(1);
        }
        count = new BrobInt(countStr);
      }
      if(isNeg){
        String ansStr = "-" + ans.toString();
        ans = new BrobInt(ansStr);
      }
      return ans;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
      BrobInt n = new BrobInt("0");
      BrobInt thisVal = new BrobInt(internalValue);
      if(thisVal.compareTo(gint) < 0){
        return ZERO;
      }
      while(thisVal.subtractByte(n.multiply(gint).divide(gint)) == ZERO){
        n = n.addByte(ONE);
      }
      return n;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method does not do a lexicographical comparison using the java String "compareTo()" method
   *        It takes into account the length of the two numbers, and if that isn't enough it does a
   *        character by character comparison to determine
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {
      if( internalValue.length() > gint.internalValue.length() ) {
         return 1;
      } else if( internalValue.length() < gint.internalValue.length() ) {
         return (-1);
      } else {
         for( int i = 0; i < internalValue.length(); i++ ) {
            Character a = new Character( internalValue.charAt(i) );
            Character b = new Character( gint.internalValue.charAt(i) );
            if( new Character(a).compareTo( new Character(b) ) > 0 ) {
               return 1;
            } else if( new Character(a).compareTo( new Character(b) ) < 0 ) {
               return (-1);
            }
         }
      }
      return 0;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( Long.valueOf( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      String byteVersionOutput = "";
      for( int i = 0; i < byteVersion.length; i++ ) {
         byteVersionOutput = byteVersionOutput.concat( Byte.toString( byteVersion[i] ) );
      }
      byteVersionOutput = new String( new StringBuffer( byteVersionOutput ).reverse() );
      return internalValue;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( byte[] d ) {
      System.out.println( Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );
      BrobInt x = new BrobInt("25");
      BrobInt y = new BrobInt("10");
      BrobInt z = x.divide(y);
      System.out.println(z.toString());
      System.exit( 0 );
   }
}
