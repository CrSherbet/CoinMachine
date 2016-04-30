package coinmachine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.Observer;
import java.util.Observable;

/**
 * This class is GUI of MachineUI that will show status of machine, balance, and coin buttons that when 
 * you click the button, it will insert the coin into the machine
 * 
 * @author Warisara
 *
 */
public class MachineUI extends JFrame implements Observer {
	// create attributes
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
	 * Constructor of MachineUI
	 * @param capacity is number of coins that the machine can hold
	 * @param machine is main machine that will use to insert the coin
	 */
	public MachineUI(int capacity, CoinMachine machine) {
		this.machine = machine;
		this.capacity = capacity;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponent();
	}

	/**
	 * Run method will set size, show the UI, and set the size of window that you can't change
	 */
	public void run() {
		setSize(450, 210);
		setVisible(true);
		setResizable(false);
	}

	/**
	 * create initial component and add to the JFrame
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
		oneBaht.addActionListener(new NumGenerator(1));
		fiveBaht.addActionListener(new NumGenerator(5));
		tenBaht.addActionListener(new NumGenerator(10));
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
	 * This class is action listener of button, it can pass the value of coin into the action listener
	 * @author Warisara
	 *
	 */
	public class NumGenerator implements ActionListener {
		private int valueOfCoin;
		/**
		 * This method will set value of coin that insert to the machine
		 * @param valueOfCoin
		 */
		public NumGenerator(final int valueOfCoin) {
			this.valueOfCoin = valueOfCoin;
		}
		
		/**
		 * This method is action of the button
		 * @param e was not used
		 */
		public void actionPerformed(ActionEvent e) {
			//insert the coin into the machine
			machine.insert(new Coin(valueOfCoin));
		}
	}

	@Override
	public void update(Observable subject, Object info) {
		balanceText.setText("Balance: " + machine.getBalance());
		statusBar.setValue(machine.getCount());
		statusBar.setString("" + machine.getCount());
		changeColorBar();
	}
	
	/**
	 * This method will change color of status bar. If it has the amount of coin less than 8, it is green bar.
	 * On the other hands, if it has the amount of coin equal 8 or 9, it will be orange bar. The last, if it has 10 coins, it will be red bar
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
