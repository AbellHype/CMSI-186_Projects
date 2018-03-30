/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  Simulate soccer balls on a pitch
 *  @author       :  Matthew Abell
 *  Date written  :  2018-03-20
 *  Description   :  This program utilizes the ball and timer classes to simulate the ball rolling and 
 *					 detect collisions.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  NumberFormatException for when the input is not a double
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-26  Matthew Abell Initial writing and release
 *  @version 1.1.0  2018-03-27  Matthew Abell Final working version
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class SoccerSim {

	//Class field definitions
	private double timeSlice;
	private Ball [] Balls;
	private Timer Time;
	private double [] pole = new double []{600,600}; //places pole at (50,50)ft.

	//Constructor
	//Creates balls and timer from user input
	public SoccerSim(String args[]) {
		if (args.length % 4 < 2 && args.length >= 4){
			try{
				Balls = new Ball[(int)Math.floor(args.length/4)];
				double timeSlice = 1.0;
				if(args.length % 4 == 1){
					timeSlice = Double.parseDouble(args[args.length-1]);
				}
				int c = 0;
				for(int i = 0; i < (args.length - args.length % 4); i = i+4){
					Ball newBall = new Ball((Double.parseDouble(args[i]))*12,(Double.parseDouble(args[i+1]))*12,(Double.parseDouble(args[i+2]))*12,(Double.parseDouble(args[i+3]))*12,timeSlice);
					Balls[c] = newBall;
					c++;
				}
				Time = new Timer(timeSlice);
			}
			catch (NumberFormatException nfe) {
				System.out.println("Please enter a valid input. All inputs should be doubles.");
				System.exit(1);
			}
		}
		else {
			System.out.println("Please enter a valid input. Follow this template for every ball: [xpos][ypos][xvel][yvel]");
			System.exit(1);
		}		
	}

	//Method to check the initial values for certain special cases
	public void initialVals(){
		for (int i = 0; i < Balls.length; i++){
			for (int j = 0; j < Balls.length; j++){
				if(j != i){
					if(Balls[i].collision(Balls[j].returnPos())){
						System.out.println("The initial placements have a collision between ball " + Integer.toString(i+1) + " and ball " + Integer.toString(j+1));
						System.exit(1);
					}
				}
			}
		}
		for (int i = 0; i < Balls.length; i++){
			if(Balls[i].collision(pole)){
				System.out.println("The initial placements have a collision between the pole and ball " + Integer.toString(i+1));
				System.exit(1);
			}
		}	
	}

	//Method that outputs a representation of the simulation
	public void Output(){
		System.out.println(Time.toString());
		for (int i = 0; i < Balls.length; i++){
			if(inRange(Balls[i].returnPos())){
				System.out.println("Ball " + Integer.toString(i+1) + Balls[i].toString());
			}
			else{
				System.out.println("Ball " + Integer.toString(i+1) + " is off the field");
			}
		}
		System.out.println("Pole Position: 50.00,50.00");
		System.out.println("");
	}

	/**
    *  Method that checks if the balls are moving
    *  @return  Boolean: returns true if none of the balls are moving
    */
	public boolean isStill(){
		for (int i = 0; i < Balls.length; i++){
			if(Balls[i].isMoving()){
				return false;
			}
		}
		return true;
	}

	/**
    *  Method to check if a ball is on the field
    *  @param   argValue  double array that contains the location of the ball
    *  @return  boolean: positive if the ball is on the field
    */
	public boolean inRange(double [] ball1){
		if (Math.abs(ball1[0]) < 6000 && Math.abs(ball1[1]) < 6000){ //creates a field that is 1000 x 10000 ft
			return true;
		}
		return false;
	}

	//Method that runs the simulation
	public void simRun(){
		boolean run = true;
		while(run){
			Time.Tick();
			for (int i = 0; i < Balls.length; i++){
				Balls[i].updatePos();
				Balls[i].updateVel();
			}
			Output();
			for (int i = 0; i < Balls.length; i++){
				for (int j = 0; j < Balls.length; j++){
					if(j != i){
						if(Balls[i].collision(Balls[j].returnPos()) && inRange(Balls[j].returnPos()) && inRange(Balls[i].returnPos())){
							System.out.println("There was a collision between ball " + Integer.toString(i+1) + " and ball " + Integer.toString(j+1));
							System.exit(1);
						}
					}
				}
			}
			for (int i = 0; i < Balls.length; i++){
				if(Balls[i].collision(pole)){
					System.out.println("There was a collision between the pole and ball " + Integer.toString(i+1));
					System.exit(1);
				}
			}
			if(isStill()){
				run = false;
			}
		}
		System.out.println("No collision is possible");
	}

	public static void main ( String args[] ){
		SoccerSim sim = new SoccerSim(args);
		sim.Output();
		sim.initialVals();
		sim.simRun();
	}
}
