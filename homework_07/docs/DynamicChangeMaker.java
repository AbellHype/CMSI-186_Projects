/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Program that uses a matrix of tuples to find the optimal way to make change
 * @author    :  Matthew Abell
 * Date       :  2018-05-03
 * Description:  This program creates a matrix out of tuples to use dynamic programming to find the optimal
 *               way to make a certain amount of change when given a list of coins with various denominations
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2018-05-03  Matthew Abell Initial release
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class DynamicChangeMaker{

	public DynamicChangeMaker() {
		//nothing needed in the constructor
	}

  /**
   * This method finds a tuple represenation of the optimal way to make change
   *
   * @param coins:an int array that lists all possible coins, amount: the integer for
   * which the program is making change for
   *
   * @return a tuple that represents the optimal number of each coin that makes the proper
   * amount of change
   */
	public static Tuple makeChangeWithDynamicProgramming(int [] coins, int amount){
		if(isInvalid(coins,amount)){
			return new Tuple(new int[0]);
		}
		int [] sortMem = sorter(coins);
		Arrays.sort(coins);
		Tuple [] [] solver = new Tuple [coins.length] [amount];
		for (int i = 0; i < coins.length; i++){
			for (int k = 0; k < amount; k++){
				solver[i][k] = new Tuple(coins.length);
				if (k-coins[i] < 0){
					if (coins[i] == k+1){
						solver[i][k].setElement(i,1);
					}
					else{
						solver[i][k] = new Tuple(new int[0]);
					}
				}
				else if(solver[i][k-coins[i]].isImpossible()){
					solver[i][k] = new Tuple(new int[0]);
				}
				else{
					Tuple adder = new Tuple(coins.length);
					adder.setElement(i,1);
					solver[i][k] = solver[i][k-coins[i]].add(adder);
				}
				if (i != 0){
					if (!solver[i-1][k].isImpossible() && solver[i][k].isImpossible()){
						solver[i][k] = solver[i-1][k];
					}
					else if (!solver[i-1][k].isImpossible() && numOfCoins(solver[i-1][k]) < numOfCoins(solver[i][k])){
						solver[i][k] = solver[i-1][k];
					}
				}		
			}
		}
		return deSort(solver[coins.length-1][amount-1],sortMem);
	}

  /**
   * This method finds a tuple represenation of the optimal way to make change
   *
   * @param coins:an int array that lists all possible coins, amount: the integer for
   * which the program is making change for
   *
   * @return a boolean that is true if the arguements for makeChangeWithDynamicProgamming 
   * are invalid
   */
	public static boolean isInvalid(int [] coins, int amount){
		if (amount < 0){
			System.out.println("Bad input, please input a positive target amount of money");
			return true;
		}
		for (int i = 0; i < coins.length; i++){
			if (coins[i] <= 0 || indexOf(coins,coins[i]) != i){
				System.out.println("Bad input, please insure that all coin values are positive and that there are no repeats");
				return true;
			}
		}
		return false;
	}

  /**
   * This method creates an int array that 'remembers' the order of the coins
   *
   * @param coins:a int array that lists all possible coins
   *
   * @return an int array that holds the new location of the coins when they get sorted
   */
	public static int [] sorter(int [] coins){
		int [] ans = new int [coins.length];
		int [] sortCoins = coins.clone();
		Arrays.sort(sortCoins);
		for (int i = 0; i < coins.length; i++){
			ans[i] = indexOf(coins,sortCoins[i]);
		}
		return ans;
	}

  /**
   * This method unsorts a previously sorted tuple
   *
   * @param tup: a tuple that is being unsorted, mem: an integer array that contains the 
   * previous locations of the values in the tuple
   * @return the inputted tuple unsorted back to its original form
   */
	public static Tuple deSort(Tuple tup, int [] mem){
		Tuple ans = new Tuple(tup.length());
		for (int i = 0; i < tup.length(); i++){
			ans.setElement(mem[i],tup.getElement(i));
		}
		return ans;
	}

  /**
   * This method finds the index of an arguement in an array
   *
   * @param arr: an integer array that is being searched, ind: the integer value that
   * is being searched for
   * @return an integer that is the index where the arguement is located in the array,
   * returns -1 if arguement isn't in the array
   */
	public static int indexOf(int [] arr, int ind){
		int ans = -1;
		for (int i = 0; i < arr.length; i++){
			if(arr[i] == ind){
				ans = i;
			}
		}
		return ans;
	}

  /**
   * This method finds the number of coins in a tuple
   *
   * @param tup: the tuple in which we are counting the number of coins
   *
   * @return an integer that cointains the number of coins in the inputted tuple
   */
	public static int numOfCoins(Tuple tup){
		int ans = 0;
		for (int i = 0; i < tup.length(); i++){
			ans = ans + tup.getElement(i);
		}
		return ans;
	}

	public static void main(String [] args){
		;
	}

}
