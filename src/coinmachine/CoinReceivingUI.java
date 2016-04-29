package coinmachine;

import java.util.Observable;
import java.util.Observer;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;

public class CoinReceivingUI extends JFrame implements Observer   {
	
	private JLabel textCoin;
	private JTextField textAmountOfCoin;
	private JLabel textStatus;
	private static int amountOfCoin = 0 ;
	
	public CoinReceivingUI(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponent();
	}
	
	public void run(){
		setSize( 90, 85);
		setVisible( true );
		setResizable( false );
	}
	
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
	
	public void update( Observable subject , Object info){
		amountOfCoin++;
		if( amountOfCoin < 10 )
			this.showAmountOfCoin();
		else
			this.showFullCoin();	
	}
	
	public void showFullCoin(){
		textAmountOfCoin.setText("" + amountOfCoin );
		textStatus.setText("Machine is Full !!! ");
		textStatus.setForeground( Color.RED );
	}
	
	public void showAmountOfCoin(){
		textAmountOfCoin.setText("" + amountOfCoin );
		textStatus.setText("Accepting Coins");
		textStatus.setForeground( Color.GREEN );
	}

}
