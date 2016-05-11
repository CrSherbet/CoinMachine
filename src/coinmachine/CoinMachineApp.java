package coinmachine;

/**
 * This is the main class for run coin-machine program.
 *  
 * @author Warisara Inprom
 *
 */
public class CoinMachineApp {
	/**
	 * This is main method that create all elements of this program.
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
		
		// add observer to machine
		machine.addObserver(machineUI);
		machine.addObserver(receivingGUI);
		
		// show message in console
		demo.insertDialog(machine);
	}
}
