package coursework;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Payment extends JFrame {

	private JPanel contentPane;
	private Customer customerGlobal;
	private ArrayList<Inventory> basketGlobal=new ArrayList <Inventory>();
	private JTextField emailText;
	private JPasswordField passwordField;
	private JTextField cardText;
	private JLabel cardLabel;
	private JLabel cvvLabel;
	private JLabel emailLabel;
	private JLabel lblNewLabel;
	private JTextField cvvText;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JButton btnNewButton;
	private JLabel cardError;
	private JLabel cvvError;
	private JLabel emailError;
	//private ArrayList<Inventory> basketGlobal=new ArrayList <Inventory>();

	/**
	 * Launch the application.
	 */
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment(null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Payment(Customer customer,ArrayList<Inventory> basket) {
		customerGlobal=customer;
		basketGlobal=basket;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		setBounds(100, 100, 1080, 678);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbtnNewRadioButton = new JRadioButton("PayPal");
		rdbtnNewRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {//if the paypal radio button is selected
					//make paypal fields (email input) visible
					//make card fields (cvv, card no.) not visible
					//make the stuff visible here
					cvvError.setVisible(false);
					emailError.setVisible(false);
					cardError.setVisible(false);
					cardText.setVisible(false);
					//passwordField.setVisible(false);
					cvvText.setVisible(false);
					cvvLabel.setVisible(false);
					cardLabel.setVisible(false);
					emailText.setVisible(true);
					emailLabel.setVisible(true);
					
				}
			}
		});
		rdbtnNewRadioButton.setBounds(890, 113, 141, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Card");
		rdbtnNewRadioButton_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {///if card radio button selected
					//make the stuff visible here
					//make paypal fields (email input) not visible
					//make card fields (cvv, card no.) visible
					cvvError.setVisible(false);
					emailError.setVisible(false);
					cardError.setVisible(false);
					cardText.setVisible(true);
					//passwordField.setVisible(true);
					cvvText.setVisible(true);
					cvvLabel.setVisible(true);
					cardLabel.setVisible(true);
					emailText.setVisible(false);
					emailLabel.setVisible(false);
					
				}
			}
		});
		rdbtnNewRadioButton_1.setBounds(697, 113, 141, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setBounds(39, 47, 557, 438);
		scrollPane.setBounds(6, 16, 566, 456);
		contentPane.add(scrollPane);
		
		JList list = new JList();//used to show customer basket
		scrollPane.setViewportView(list);
		
		emailText = new JTextField();
		emailText.setBounds(616, 226, 130, 26);
		contentPane.add(emailText);
		emailText.setColumns(10);
		emailText.setVisible(false);
		
		emailLabel = new JLabel("Email Address");
		emailLabel.setBounds(614, 198, 105, 16);
		contentPane.add(emailLabel);
		emailLabel.setVisible(false);
		
		/*passwordField = new JPasswordField();
		passwordField.setBounds(892, 226, 91, 26);
		contentPane.add(passwordField);
		passwordField.setVisible(false);*/
		
		cvvLabel = new JLabel("CVV");
		cvvLabel.setBounds(899, 198, 61, 16);
		contentPane.add(cvvLabel);
		cvvLabel.setVisible(false);
		
		cardText = new JTextField();
		cardText.setBounds(616, 226, 130, 26);
		contentPane.add(cardText);
		cardText.setColumns(10);
		cardText.setVisible(false);
		
		
		cardLabel = new JLabel("Card Number");
		cardLabel.setBounds(616, 198, 130, 16);
		contentPane.add(cardLabel);
		cardLabel.setVisible(false);
		
		JButton payButton = new JButton("Confirm Payment");
		payButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//if confirm payment button clicked
				JButton payButton=(JButton)e.getSource();
				Boolean purchase=false;
				//do regex for the fields here to check validity
				//then get items from swingwindow and deduct from their stock
				System.out.println("In function");
				System.out.println(emailText.isVisible());
				if(emailText.isVisible()) {//if paypal payment used
					if(Pattern.matches("[A-Za-z0-9!?]+@{1}.+[.]{1}.+", emailText.getText())) {//checks if email valid
						emailError.setVisible(false);
						purchase=true;
						//outputs details used in purchase
						lblNewLabel.setText("<html><center>£"+String.valueOf(customerGlobal.getBasketPrice())+" paid using PayPal,<br><br> and the delivery address is:<br><br> "
								+customerGlobal.name+" "+customerGlobal.houseNumber+" "+customerGlobal.city+" "+customerGlobal.postcode);
						//makes everything on page invisible and makes button to exit visible
						payButton.setVisible(false);
						emailText.setVisible(false);
						emailLabel.setVisible(false);
						rdbtnNewRadioButton.setVisible(false);
						rdbtnNewRadioButton_1.setVisible(false);
						btnNewButton.setVisible(true);
					}
					else {//if email formatted wrong, output error
						emailError.setVisible(true);
					}
				}
				else {//if payment type is card
					System.out.println(cardText.getText());
					cvvError.setVisible(false);
					cardError.setVisible(false);
					if(Pattern.matches("\\d{6}", cardText.getText())&&Pattern.matches("\\d{3}", cvvText.getText())) {//check if card no. and cvv formatted correctly
						System.out.println("Card fine");
							System.out.println("CVV Fine");
							purchase=true;
							//output details used to purchase
							lblNewLabel.setText("<html><center>£"+String.valueOf(customerGlobal.getBasketPrice())+" paid using Credit Card,<br><br> and the delivery address is: <br><br>"
									+customerGlobal.name+" "+customerGlobal.houseNumber+" "+customerGlobal.city+" "+customerGlobal.postcode);
							//make everything not visible and make exit button visible
							payButton.setVisible(false);
							cardText.setVisible(false);
							cardLabel.setVisible(false);
							cvvText.setVisible(false);
							cvvLabel.setVisible(false);
							rdbtnNewRadioButton.setVisible(false);
							rdbtnNewRadioButton_1.setVisible(false);
							btnNewButton.setVisible(true);
							
					}
					else {//if formatted wrong
						if(!Pattern.matches("\\d{3}", cvvText.getText())) {//if cvv wrong, output cvv error
							cvvError.setVisible(true);
						}
						if(!Pattern.matches("\\d{6}", cardText.getText())) {//if card no. wrong output card no. error
							cardError.setVisible(true);
						}
					}
				}
				if(purchase) {
				System.out.println(basketGlobal+" and "+basketGlobal.size());//debugging
				for(int i=0;i<SwingWindow.items.size();i++) {
					for(int j=0;j<basketGlobal.size();j++) {
						//System.out.println(basket.get(j).barcode+" and "+SwingWindow.items.get(i).barcode);
						if(basketGlobal.get(j).barcode.equals(SwingWindow.items.get(i).barcode)){//reduces stock of any item bought in transaction
							System.out.println("In Here");
							SwingWindow.items.get(i).stock-=1;
							System.out.println(SwingWindow.items.get(i).stock);
							
						}
					}
				}
			}}
		});
		payButton.setBounds(889, 369, 152, 29);
		contentPane.add(payButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		
		DefaultListModel dlm=new DefaultListModel();
		for(int i=0;i<(basketGlobal).size();i++) {//adds each element of basket to a dlm, to be output to a jlist
			//System.out.println(basketGlobal);
			dlm.addElement((basketGlobal).get(i).toString());
		}
		
		list.setModel(dlm);
		
		lblNewLabel = new JLabel("Hello "+customer.name+". The cost of your shop is: £"+customer.getBasketPrice());//shows customer their name, and their name of shop
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(616, 6, 445, 118);
		contentPane.add(lblNewLabel);
		
		cvvText = new JTextField();
		cvvText.setBounds(880, 226, 130, 26);
		contentPane.add(cvvText);
		cvvText.setColumns(10);
		
		btnNewButton = new JButton("Return to Shop");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//if return to shop button clicked; will re-open shop window
				SwingWindow window=new SwingWindow();
				FileInterface.updateItems(SwingWindow.items);
				dispose();
				SwingWindow.basketView.clear();
				SwingWindow.basketArray.clear();
				window.setVisible(true);
			}
		});
		btnNewButton.setBounds(749, 317, 141, 29);
		contentPane.add(btnNewButton);
		
		cardError = new JLabel("Card Incorrect");
		cardError.setForeground(Color.RED);
		cardError.setBounds(613, 262, 106, 29);
		contentPane.add(cardError);
		
		cvvError = new JLabel("CVV Incorrect");
		cvvError.setForeground(Color.RED);
		cvvError.setBounds(880, 264, 106, 29);
		contentPane.add(cvvError);
		
		emailError = new JLabel("Email Incorrect");
		emailError.setForeground(Color.RED);
		emailError.setBounds(613, 264, 106, 29);
		contentPane.add(emailError);
		btnNewButton.setVisible(false);
		cvvText.setVisible(false);
		cardError.setVisible(false);
		cvvError.setVisible(false);
		emailError.setVisible(false);
	}
}
