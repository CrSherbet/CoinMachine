package coinmachine;

/**
 * This is the main class for run coin-machine program that show the amount of coin, total balance, 
 * and status of machine
 * 
 * @author Warisara
 *
 */
public class CoinMachineApp {
	/**
	 * This is main method that create every GUI and objects that will run the program
	 * @param args was not used
	 * 
	 */
	public static void main(String[] args) {
		final int capacity = 10;  // how many coins the machine can hold
		
		// create objects
		CoinMachine machine = new CoinMachine( capacity );
		MachineUI machineUI = new MachineUI( capacity , machine );
		CoinReceivingUI receivingGUI = new CoinReceivingUI( machine );
		Demo demo = new Demo(); 
		
		// add observer into machine
		machine.addObserver(machineUI);
		machine.addObserver(receivingGUI);
		
		// show massage in console that you can also insert coin by this way 
		demo.insertDialog(machine);
	}
}
