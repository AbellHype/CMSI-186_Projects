public class Fibonacci{
	
	private BrobInt [] Fibs;
	private int Fibn;

	public Fibonacci (int Fib) {
		this.Fibn = Fib;
		this.Fibs = new BrobInt[Fib];
		this.Fibs[0] = new BrobInt("0");
		this.Fibs[1] = new BrobInt("1");
	}

	public String runFib(){
		for(int i = 2; i < Fibn; i++){
			Fibs[i] = Fibs[i-1].addByte(Fibs[i-2]);
		}
		return Fibs[Fibn - 1].toString();
	}

	public static void main (String [] args){
		try{
			int Fib = Integer.parseInt(args[0]);
			Fibonacci f1 = new Fibonacci(Fib);
			System.out.println(f1.runFib());
		}
		catch (NumberFormatException n){
			System.out.println("Please enter a valid integer");
			System.exit(1);
		}
	}

}
