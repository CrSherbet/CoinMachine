package coinmachine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.Observer; 
import java.util.Observable;

public class MachineUI extends JFrame implements Observer {
	
	private JPanel coinPanel ;
	private JLabel balanceText ;
	private JLabel statusText ;
	private JProgressBar statusBar ;
	private JButton oneBaht ;
	private JButton fiveBaht ;
	private JButton tenBaht ;
	
	private int balance ;
	private int amountOfCoin ;
	private final int capacity ;
	private final CoinMachine machine ;
	
	public MachineUI( int capacity , CoinMachine machine ){
		this.machine = machine ;
		this.capacity = capacity ;
		balance = 0 ;
		amountOfCoin = 0 ;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponent();
	}
	
	public void run(){
		setSize( 450, 210);
		setVisible( true );
		setResizable( false );
	}
	
	public void initComponent(){
		setLayout( new FlowLayout());
		
		coinPanel = new JPanel ();
		
		balanceText = new JLabel("Balance: " + balance );
		statusText = new JLabel (" Status : ");
		statusBar = new JProgressBar ( 0, capacity );
		statusBar.setStringPainted( true );
		statusBar.setString(""+amountOfCoin);
		
		ImageIcon oneCoin = new ImageIcon(this.getClass().getClassLoader().getResource("images/1Baht.png"));
		ImageIcon fiveCoin = new ImageIcon(this.getClass().getClassLoader().getResource("images/5Baht.png"));
		ImageIcon tenCoin = new ImageIcon(this.getClass().getClassLoader().getResource("images/10Baht.png"));
		
		oneBaht = new JButton( oneCoin );
		fiveBaht = new JButton( fiveCoin );
		tenBaht = new JButton( tenCoin );
		
		oneBaht.addActionListener( new NumGenerator ( 1 ) );
		fiveBaht.addActionListener( new NumGenerator ( 5 ) );
		tenBaht.addActionListener( new NumGenerator ( 10 ) );
		coinPanel.add( oneBaht );
		coinPanel.add( fiveBaht );
		coinPanel.add( tenBaht );
		
		TitledBorder title = new TitledBorder("Insert Money");
		coinPanel.setBorder(title);
		
		add( balanceText );
		add( statusText );
		add( statusBar );
		add( coinPanel );
		

		run();	
	}
	
	public class NumGenerator implements ActionListener {
		private int valueOfCoin ;
		
		public NumGenerator (final int valueOfCoin){
			this.valueOfCoin = valueOfCoin ;
		}
		public void actionPerformed(ActionEvent e) {
			machine.insert(new Coin( valueOfCoin ));
		}
	}

	@Override
	public void update(Observable subject , Object info ) {
		balance = ((CoinMachine) subject).getBalance();
		balanceText.setText("Balance: " + balance );
		amountOfCoin  = ((CoinMachine) subject).getCount() ;
		statusBar.setValue( amountOfCoin );
		statusBar.setString("" + amountOfCoin);
		changeColorBar();
	}
	
	public void changeColorBar(){
		if ( amountOfCoin < 8 )
			statusBar.setForeground( Color.GREEN );
		else if ( amountOfCoin < 10)
			statusBar.setForeground( Color.ORANGE );
		else
			statusBar.setForeground( Color.RED );
	}
}
