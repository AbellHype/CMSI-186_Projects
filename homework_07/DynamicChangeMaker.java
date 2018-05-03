import java.util.Arrays;

public class DynamicChangeMaker{

	public DynamicChangeMaker() {
	}

	public static Tuple makeChangeWithDynamicProgramming(int [] coins, int amount){
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
		System.out.println(deSort(solver[coins.length-1][amount-1],sortMem).toString());
		return deSort(solver[coins.length-1][amount-1],sortMem);
	}

	public static int [] sorter(int [] coins){
		int [] ans = new int [coins.length];
		int [] sortCoins = coins.clone();
		Arrays.sort(sortCoins);
		for (int i = 0; i < coins.length; i++){
			ans[i] = indexOf(coins,sortCoins[i]);
		}
		return ans;
	}

	public static Tuple deSort(Tuple tup, int [] mem){
		Tuple ans = new Tuple(tup.length());
		for (int i = 0; i < tup.length(); i++){
			ans.setElement(mem[i],tup.getElement(i));
		}
		return ans;
	}

	public static int indexOf(int [] arr, int ind){
		int ans = -1;
		for (int i = 0; i < arr.length; i++){
			if(arr[i] == ind){
				ans = i;
			}
		}
		return ans;
	}

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
