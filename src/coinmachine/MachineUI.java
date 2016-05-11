package coinmachine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.Observer;
import java.util.Observable;

/**
 * The GUI of MachineUI that will show balance , status of machine , and coin buttons which you can press to insert the coin.
 * 
 * @author Warisara Inprom
 *
 */
public class MachineUI extends JFrame implements Observer {
	// Attributes
	private JPanel coinPanel;
	private JLabel balanceText;
	private JLabel statusText;
	private JProgressBar statusBar;
	private JButton oneBaht;
	private JButton fiveBaht;
	private JButton tenBaht;

	private final int capacity;
	private final CoinMachine machine;

	/**
	 * Constructor of MachineUI.
	 * @param capacity is the number of coins that the machine can hold.
	 * @param machine is main machine that will use to insert the coin.
	 */
	public MachineUI(int capacity, CoinMachine machine) {
		this.machine = machine;
		this.capacity = capacity;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponent();
	}

	/**
	 * Run this GUI.
	 */
	public void run() {
		setSize(450, 210);
		setVisible(true);
		setResizable(false);
	}

	/**
	 * Initial components of this GUI. 
	 */
	public void initComponent() {
		setLayout(new FlowLayout());

		coinPanel = new JPanel();
		balanceText = new JLabel("Balance: " + machine.getBalance());
		statusText = new JLabel(" Status : ");
		statusBar = new JProgressBar(0, capacity);
		statusBar.setStringPainted(true);
		statusBar.setString("" + machine.getCount());
		
		// add image into the buttons
		ImageIcon oneCoin = new ImageIcon(this.getClass().getClassLoader().getResource("images/1Baht.png"));
		ImageIcon fiveCoin = new ImageIcon(this.getClass().getClassLoader().getResource("images/5Baht.png"));
		ImageIcon tenCoin = new ImageIcon(this.getClass().getClassLoader().getResource("images/10Baht.png"));
		
		oneBaht = new JButton(oneCoin);
		fiveBaht = new JButton(fiveCoin);
		tenBaht = new JButton(tenCoin);

		//add action listener for each button
		oneBaht.addActionListener(new CoinInserting(1));
		fiveBaht.addActionListener(new CoinInserting(5));
		tenBaht.addActionListener(new CoinInserting(10));
		coinPanel.add(oneBaht);
		coinPanel.add(fiveBaht);
		coinPanel.add(tenBaht);

		// set the name of group coin button
		TitledBorder title = new TitledBorder("Insert Money");
		coinPanel.setBorder(title);

		//add the button to JFrame
		add(balanceText);
		add(statusText);
		add(statusBar);
		add(coinPanel);

		run();
	}

	/**
	 * This class is action listener of the button.
	 */
	public class CoinInserting implements ActionListener {
		private int valueOfCoin;
		/**
		 * Set the value of coin that insert to the machine.
		 * @param valueOfCoin is the value of coin.
		 */
		public CoinInserting(final int valueOfCoin) {
			this.valueOfCoin = valueOfCoin;
		}
		
		/**
		 * Action of the button.
		 * @param e was not used.
		 */
		public void actionPerformed(ActionEvent e) {
			//insert the coin to the machine
			machine.insert(new Coin(valueOfCoin));
		}
	}

	/**
	 * Update balance and status bar.
	 */
	@Override
	public void update(Observable subject, Object info) {
		balanceText.setText("Balance: " + machine.getBalance());
		statusBar.setValue(machine.getCount());
		statusBar.setString("" + machine.getCount());
		changeColorBar();
	}
	
	/**
	 * Change color of status bar.
	 */
	public void changeColorBar() {
		if (machine.getCount() < 8)
			statusBar.setForeground(Color.GREEN);
		else if (machine.getCount() < 10)
			statusBar.setForeground(Color.ORANGE);
		else
			statusBar.setForeground(Color.RED);
	}
}
