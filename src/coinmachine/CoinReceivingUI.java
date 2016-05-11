package coinmachine;

import java.util.Observable;
import java.util.Observer;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 * 
 * This is GUI of class CoinReceivingUI that will update the amount of coin when the program
 * insert the coin to the machine.
 * 
 * @author Warisara Inprom
 *
 */

public class CoinReceivingUI extends JFrame implements Observer {
	// create attributes
	private JLabel textCoin;
	private JTextField textAmountOfCoin;
	private JLabel textStatus;
	private CoinMachine machine ;
	
	/**
	 * Constructors of CoinReceivingUI.
	 * @param machine is main machine that will use to insert the coin
	 */
	public CoinReceivingUI( CoinMachine machine ){
		// set initial value
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponent();
		this.machine = machine ;
	}
	
	/**
	 * Run the GUI.
	 */
	public void run(){
		setSize( 90, 85);
		setVisible( true );
		setResizable( false );
	}
	
	/**
	 * Initial components of this GUI.
	 */
	public void initComponent(){
		textCoin = new JLabel ("#Coins: ");
		textAmountOfCoin = new JTextField( 3 );
		textStatus = new JLabel ("Wait for loading");
		textStatus.setForeground( Color.RED );
		textAmountOfCoin.setText("0");
		
		setLayout( new FlowLayout());
		add( textCoin );
		add( textAmountOfCoin );
		add( textStatus );
		run();
	}
	
	/**
	 * Update method will update the amount of coin inside the machine
	 * when user insert the coin to the machine.
	 * 
	 */
	public void update( Observable subject , Object info){
		textAmountOfCoin.setText("" + machine.getCount() );
		if( machine.getCount() < 10 )
			this.showAmountOfCoin();
		else
			this.showFullCoin();	
	}
	
	/**
	 * Show status with red text when the amount of coin inside the machine is full.
	 */
	public void showFullCoin(){
		textStatus.setText("Machine is Full !!! ");
		textStatus.setForeground( Color.RED );
	}
	
	/**
	 * Show status with green text when the coin inside the machine can insert the coin more.
	 */
	public void showAmountOfCoin(){
		textStatus.setText("Accepting Coins");
		textStatus.setForeground( Color.GREEN );
	}

}
