package coursework;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SwingWindow extends JFrame {

	private JPanel contentPane;

	public static ArrayList<Inventory> items=new ArrayList<Inventory>();//was private
	public static ArrayList<Inventory> filteredItems=new ArrayList<Inventory>();;//was private
	public static ArrayList<Inventory> filteredItemsMouse=new ArrayList<Inventory>();;//was private
	public static ArrayList<Inventory> basketArray=new ArrayList<Inventory>();//new arraylist of inventory
	public static DefaultListModel basketView=new DefaultListModel();
	public static DefaultListModel dlm = new DefaultListModel();
	private JScrollPane scrollPane_1;
	private JList list_1;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JTextField barcodeText;
	private JTextField colorText;
	private JTextField stockText;
	private JTextField origCostText;
	private JTextField retailCostText;
	private JTextField brandText;
	private JTextField mouseNoText;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_14;
	private JComboBox keyLayoutBox;
	private JComboBox connBox;
	private JComboBox keyMouseBox;
	private JComboBox itemTypeBox;
	private JButton btnNewButton_3;
	private Boolean updatingStock=false;
	private JList list;
	private JLabel priceError;
	private JLabel costError;
	private JLabel barcodeError;
	private JLabel stockError;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		//TODO remove main from here and port it to new class
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingWindow frame = new SwingWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		BufferedReader br=null;
	    try{
	    br=new BufferedReader(new FileReader("src/coursework/Stock.txt"));
	    String line=null;
	    while((line=br.readLine())!=null){
	      //System.out.println(line);
	      String[] data=line.split(", ");
	      //System.out.println(data[1]+" mouse "+" keyboard ");
	      if(data[1].contains("mouse")) {
	    	  Mouse mouse=new Mouse(data[0],Name.valueOf(data[1].toUpperCase()),ItemType.valueOf(data[2].toUpperCase()),data[3],data[4],Connectivity.valueOf(data[5].toUpperCase()),
	    			  Integer.valueOf(data[6]),Float.valueOf(data[7]),Float.valueOf(data[8]),Integer.valueOf(data[9]));
	    	  items.add(mouse);
	      }
	      else if(data[1].contains("keyboard")) {
	    	  Keyboard keyboard=new Keyboard(data[0],Name.valueOf(data[1].toUpperCase()),ItemType.valueOf(data[2].toUpperCase()),data[3],data[4],Connectivity.valueOf(data[5].toUpperCase()),
	    			  Integer.valueOf(data[6]),Float.valueOf(data[7]),Float.valueOf(data[8]),Layout.valueOf(data[9].toUpperCase()));
	    	  items.add(keyboard);
	      }
	      else {
	    	  System.out.println("error");
	      }
	      //SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-YYYY");
	      //Date result = sdt.parse(data[2]);
	      //Employee emp=new Employee(Integer.valueOf(data[0]),data[1],result,Integer.valueOf(data[3]));
	      //employees.add(emp);
	      
	    }
	      }
	    catch(FileNotFoundException e){
	      System.err.println(e.getMessage());
	      e.printStackTrace();
	    }
	    catch(IOException e){
	      System.err.println(e.getMessage());
	      e.printStackTrace();
	    }
	    for(int i=0;i<items.size();i++){
	        System.out.println(items.get(i).toString());}
	    System.out.println("Finished");
	    //Customer cust=new Customer();
	    //cust.viewProducts(items);

	}*/

	/**
	 * Create the frame.
	 */
	public SwingWindow() {
		if(!FileInterface.readFrom) {//if haven't already read from file, then read the items from file
			System.out.println("here");
		items=FileInterface.returnItems();}
		//items=FileInterface.returnItems();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 678);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Collections.sort(items, new sortAsc());//might not need this
		filteredItems=items;
		filteredItemsMouse=items;
		Person person=Login.getPerson();//gets the person selected in the login window
		for(int i=0;i<(filteredItems).size();i++) {//for every item in the items list, add it's string version to defaultlistmodel, so it can be used in the jlist
			if(person instanceof Customer) {
			dlm.addElement((filteredItems).get(i).toString());}
			else if(person instanceof Admin) {
				if(filteredItems.get(i) instanceof Keyboard) {
					Keyboard keyToAdd=(Keyboard)filteredItems.get(i);
					dlm.addElement((keyToAdd.toStringAdmin()));
				}
				else if(filteredItems.get(i) instanceof Mouse) {
					Mouse mouseToAdd=(Mouse)filteredItems.get(i);
					dlm.addElement((mouseToAdd.toStringAdmin()));
				}
			}
		}
		
		JLabel lblNewLabel_1 = new JLabel("No. Of Mouse Buttons");
		lblNewLabel_1.setBounds(266, 495, 144, 16);
		contentPane.add(lblNewLabel_1);
		
		if(person instanceof Customer) {//if the person is a customer, make them a customer type and save their basket to basketArray
			Customer customer=(Customer)person;
			basketArray=customer.viewBasket();
		for(int i=0;i<(basketArray.size());i++) {//add each element in the basket array to basket view, to be shown in a jlist
			basketView.addElement((basketArray).get(i).toString());
		}
		}
		
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 16, 566, 456);
		contentPane.add(scrollPane_1);
		
		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//if a jlist item is clicked
				JList list = (JList)e.getSource();
		        if (e.getClickCount() == 2) {//checks if it's a double clicked
		        	int index = list.locationToIndex(e.getPoint());//gets the index of the element clicked
		        	Inventory i = (filteredItems.get(index));//(was items) returns the item stored at that index
		        	//Customer cust=new Customer();
		        	//cust.addBasket(i);
		        	//check if already in basket
		        	if(basketView.indexOf(i)==-1) {//if the item isn't already in the basket, add it to the basket
		        		basketView.addElement(i);//TODO change this to account for filtering
		        		basketArray.add(i);//might need to delete
		        		}
		        	/*basketArray=cust.viewBasket();
		    		for(int j=0;j<(basketArray).size();j++) {
		    			basketView.addElement(basketArray.get(j).toString());
		    		}*/
		        	Person person=Login.getPerson();
		        	if(person instanceof Customer) {//if the person is a customer, update their basket
		        		Customer customer=(Customer)person;
		        		customer.setBasket(basketArray);
		        	}
		        	else if(person instanceof Admin) {//if the person is an admin, autofill fields with values of the item clicked
		        		barcodeText.setText(i.barcode);
		        		brandText.setText(String.valueOf(i.brand));
		        		colorText.setText(i.color);
		        		connBox.setSelectedItem(i.connectivity);
		        		stockText.setText(String.valueOf(i.stock));
		        		origCostText.setText(String.valueOf(i.originalCost));
		        		retailCostText.setText(String.valueOf(i.retailPrice));
		        		keyMouseBox.setSelectedItem(i.name.toString());
		        		System.out.println(i.name);
		        		itemTypeBox.setSelectedItem(i.type);
		        		if(i instanceof Mouse) {
		        			Mouse selectedMouse=(Mouse)i;
		        			mouseNoText.setText(String.valueOf(selectedMouse.numButtons));
		        		}
		        		else {
		        			Keyboard selectedKeyboard=(Keyboard)i;
		        			keyLayoutBox.setSelectedItem(selectedKeyboard.layout);
		        		}
		        		updatingStock=true;
		        		btnNewButton_3.setText("Update Stock");
		        	}
		        	System.out.println(basketView);
		        }
			}
		});
		scrollPane_1.setViewportView(list);
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(dlm);
		
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//logout button action; clears all important data structures, closes the window and opens the login window
				dispose();
				FileInterface.readFrom=false;
				items.clear();
				dlm.clear();
				filteredItems.clear();
				filteredItemsMouse.clear();
				basketView.clear();
				basketArray.clear();
				Login window=new Login();
				window.main(null);
				window.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(899, 538, 117, 29);
		contentPane.add(btnNewButton_1);
		
		Person user=Login.getPerson();//used to output user's name at top of screen in label below
		
		JLabel lblNewLabel_2 = new JLabel("Hello "+user.name);
		lblNewLabel_2.setBounds(596, 18, 132, 16);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {//if an item is selected in the combobox for filtering brands
				JComboBox comboBox=(JComboBox)e.getSource();
				//basketView.filter(comboBox.getSelectedItem());
				filteredItems=(ArrayList<Inventory>) items.clone();
				if(comboBox.getSelectedIndex()!=0) {
				for(int i=0;i<items.size();i++) {
					
					if(!((items.get(i)).brand.equals(comboBox.getSelectedItem()))) {//removes any item from the jlist view that isn't the selected brand
						System.out.println("Items: "+items.get(i).brand);
						System.out.println("Box: "+comboBox.getSelectedItem());
						filteredItems.remove(items.get(i));
					}
				}
				}
				dlm.clear();
				for(int i=0;i<(filteredItems).size();i++) {//if the mouse buttons filter is being used aswell, will remove any elements that need to be filtered aswell
					Boolean flag=false;
					for(int j=0;j<filteredItemsMouse.size();j++) {
						if(filteredItems.get(i)==filteredItemsMouse.get(j)) {
							flag=true;
						}
					}
					if(flag) {
						dlm.addElement((filteredItems).get(i).toString());//updates jlist view
					}
				}
			}
		});
		//TODO change this to read all brands from inventory
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"No Filter", "Advent", "Anker", "Apple", "Asus", "Corsair", "Logitech", "Microsoft"}));
		comboBox.setModel(new DefaultComboBoxModel(FileInterface.returnBrands(items).toArray()));
		comboBox.setBounds(16, 501, 152, 66);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Filter Brand");
		lblNewLabel.setBounds(29, 495, 88, 16);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"No Filter", "1", "2", "3", "4"}));
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {//filter for mouse buttons
				JComboBox comboBox=(JComboBox)e.getSource();
				//basketView.filter(comboBox.getSelectedItem());
				filteredItemsMouse=(ArrayList<Inventory>) items.clone();
				if(comboBox.getSelectedIndex()!=0) {
				for(int i=0;i<items.size();i++) {
					Inventory currentItem=items.get(i);
					if(currentItem instanceof Mouse) {//checks if the item is a mouse, removes those that aren't
						Mouse currentMouse=(Mouse)items.get(i);
						int num=Integer.parseInt((String)comboBox.getSelectedItem());
						if(!(currentMouse.numButtons==num)) {//checks if item's mouse buttons is the same as filter, removes those that aren't
							filteredItemsMouse.remove(items.get(i));
						}
					}
					else {
						filteredItemsMouse.remove(currentItem);
					}
					/*
					if(!((items.get(i)).brand.equals(comboBox.getSelectedItem()))) {
						System.out.println("Items: "+items.get(i).brand);
						System.out.println("Box: "+comboBox.getSelectedItem());
						filteredItems.remove(items.get(i));
					}
				}*/
				}
				}
				dlm.clear();
				for(int i=0;i<(filteredItems).size();i++) {//applies filter for brand if applicable
					Boolean flag=false;
					for(int j=0;j<filteredItemsMouse.size();j++) {
						if(filteredItems.get(i)==filteredItemsMouse.get(j)) {
							flag=true;
						}
					}
					if(flag) {
						dlm.addElement((filteredItems).get(i).toString());//updates jlist view
					}
				}
				
			}
		});
		comboBox_1.setBounds(261, 520, 117, 29);
		contentPane.add(comboBox_1);
		
		if(person instanceof Customer) {//only shows this section when person is a customer
		scrollPane = new JScrollPane();
		scrollPane.setBounds(596, 51, 466, 298);
		contentPane.add(scrollPane);
		
		
		list_1 = new JList();
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//checks if basket list has been clicked
				JList list = (JList)e.getSource();
		        if (e.getClickCount() == 2) {//checks for double click
		        	int index = list.locationToIndex(e.getPoint());
		        	basketView.remove(index);//TODO change this to account for filtering// removes the selected element at the index from basket
		        	basketArray.remove(index);//might need to delete
		        	Person person=Login.getPerson();
		        	if(person instanceof Customer) {//updates customer basket
		        		Customer customer=(Customer)person;
		        		customer.setBasket(basketArray);
		        	}
		        }
			}
		});
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list_1);
		list_1.setModel(basketView);//shows the customer basket in the jlist element
		
		JButton btnNewButton = new JButton("Clear Basket");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//checks for clear basket button press
				basketView.clear();//clears basket
				basketArray.clear();
				Person person=Login.getPerson();
	        	if(person instanceof Customer) {//updates person's basket
	        		Customer customer=(Customer)person;
	        		customer.setBasket(basketArray);
	        	}
			}
		});
		btnNewButton.setBounds(899, 375, 117, 29);
		contentPane.add(btnNewButton);//clear basket button
		
		JButton btnNewButton_2 = new JButton("Checkout");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//checks if checkout button clicked
				Person user=Login.getPerson();
				if(user instanceof Customer) {
					Customer cust=(Customer)user;
				Payment payment=new Payment(cust,cust.viewBasket());//passes in the customer and basket data to payment class
				dispose();//clears window of all elements
				dlm.clear();
				payment.setVisible(true);//shows payment pane
				//basketView.clear();
				//basketArray.clear();
				}
			}
		});
		btnNewButton_2.setBounds(596, 375, 117, 29);
		contentPane.add(btnNewButton_2);}
		
		else if(person instanceof Admin) {//only shows if person is admin
			//TODO put code here for adding an item}
			JLabel lblNewLabel_3 = new JLabel("Add Item");
			lblNewLabel_3.setBounds(781, 60, 61, 16);
			contentPane.add(lblNewLabel_3);
			
			barcodeText = new JTextField();
			barcodeText.setBounds(596, 156, 106, 26);
			contentPane.add(barcodeText);
			barcodeText.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("Barcode");
			lblNewLabel_4.setBounds(596, 132, 61, 16);
			contentPane.add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("Brand");
			lblNewLabel_5.setBounds(738, 132, 61, 16);
			contentPane.add(lblNewLabel_5);
			
			colorText = new JTextField();
			colorText.setBounds(862, 156, 130, 26);
			contentPane.add(colorText);
			colorText.setColumns(10);
			
			JLabel lblNewLabel_6 = new JLabel("Color");
			lblNewLabel_6.setBounds(862, 132, 61, 16);
			contentPane.add(lblNewLabel_6);
			
			connBox = new JComboBox();
			connBox.setModel(new DefaultComboBoxModel(Connectivity.values()));
			connBox.setBounds(596, 242, 106, 29);
			contentPane.add(connBox);
			
			JLabel lblNewLabel_7 = new JLabel("Connectivity");
			lblNewLabel_7.setBounds(596, 214, 94, 16);
			contentPane.add(lblNewLabel_7);
			
			stockText = new JTextField();
			stockText.setBounds(726, 242, 130, 26);
			contentPane.add(stockText);
			stockText.setColumns(10);
			
			JLabel lblNewLabel_8 = new JLabel("Stock");
			lblNewLabel_8.setBounds(725, 214, 61, 16);
			contentPane.add(lblNewLabel_8);
			
			origCostText = new JTextField();
			origCostText.setBounds(885, 242, 130, 26);
			contentPane.add(origCostText);
			origCostText.setColumns(10);
			
			JLabel lblNewLabel_9 = new JLabel("Original Cost");
			lblNewLabel_9.setBounds(885, 214, 107, 16);
			contentPane.add(lblNewLabel_9);
			
			retailCostText = new JTextField();
			retailCostText.setBounds(598, 317, 130, 26);
			contentPane.add(retailCostText);
			retailCostText.setColumns(10);
			
			JLabel lblNewLabel_10 = new JLabel("Retail Price");
			lblNewLabel_10.setBounds(596, 295, 94, 16);
			contentPane.add(lblNewLabel_10);
			
			brandText = new JTextField();
			brandText.setBounds(720, 156, 130, 26);
			contentPane.add(brandText);
			brandText.setColumns(10);
		
		
		keyMouseBox = new JComboBox();
		keyMouseBox.setModel(new DefaultComboBoxModel(new String[] {"Select...", "MOUSE", "KEYBOARD"}));
		keyMouseBox.setBounds(760, 318, 117, 25);
		contentPane.add(keyMouseBox);
		
		JLabel lblNewLabel_11 = new JLabel("Keyboard/ Mouse");
		lblNewLabel_11.setBounds(760, 295, 117, 16);
		contentPane.add(lblNewLabel_11);
		
		itemTypeBox = new JComboBox();
		itemTypeBox.setModel(new DefaultComboBoxModel(ItemType.values()));
		itemTypeBox.setBounds(891, 321, 124, 20);
		contentPane.add(itemTypeBox);
		
		JLabel lblNewLabel_12 = new JLabel("Type");
		lblNewLabel_12.setBounds(892, 295, 61, 16);
		contentPane.add(lblNewLabel_12);
		
		System.out.println("Keyboard");
		lblNewLabel_13 = new JLabel("Keyboard Layout");
		lblNewLabel_13.setBounds(599, 373, 152, 16);
		contentPane.add(lblNewLabel_13);
		lblNewLabel_13.setVisible(false);
		
		keyLayoutBox = new JComboBox();
		keyLayoutBox.setModel(new DefaultComboBoxModel(Layout.values()));
		keyLayoutBox.setBounds(598, 402, 130, 26);
		contentPane.add(keyLayoutBox);
		keyLayoutBox.setVisible(false);
		
		
		System.out.println("Mouse");
		lblNewLabel_14 = new JLabel("Number of Buttons");
		lblNewLabel_14.setBounds(584, 373, 191, 16);
		contentPane.add(lblNewLabel_14);
		lblNewLabel_14.setVisible(false);

		mouseNoText = new JTextField();
		mouseNoText.setBounds(598, 401, 130, 26);
		contentPane.add(mouseNoText);
		mouseNoText.setColumns(10);
		mouseNoText.setVisible(false);
		
		keyMouseBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {	//TODO finish this//changes elements shown depending if mouse or keyboard selected
				if(e.getStateChange()==ItemEvent.SELECTED) {
				JComboBox keyMouseBox=(JComboBox)e.getSource();
				if(keyMouseBox.getSelectedItem().equals("MOUSE")) {//if mouse selected, will show box for inputting how many mouse buttons there are
					lblNewLabel_13.setVisible(false);
					keyLayoutBox.setVisible(false);
					lblNewLabel_14.setVisible(true);
					mouseNoText.setVisible(true);
				}
				else if(keyMouseBox.getSelectedItem().equals("KEYBOARD")) {//if keyboard selected will show box for keyboard layout entry
					lblNewLabel_13.setVisible(true);
					keyLayoutBox.setVisible(true);
					lblNewLabel_14.setVisible(false);
					mouseNoText.setVisible(false);
				}
				}
			}
		});
		
		btnNewButton_3 = new JButton("Add Item");
		btnNewButton_3.setBounds(760, 425, 117, 29);
		contentPane.add(btnNewButton_3);
		
		barcodeError = new JLabel("Invalid Barcode");
		barcodeError.setForeground(Color.RED);
		barcodeError.setBounds(596, 186, 106, 16);
		contentPane.add(barcodeError);
		barcodeError.setVisible(false);
		
		stockError = new JLabel("Invalid Stock");
		stockError.setForeground(Color.RED);
		stockError.setBounds(726, 267, 94, 16);
		contentPane.add(stockError);
		stockError.setVisible(false);
		
		costError = new JLabel("Invalid Cost (Use Decimal Point)");
		costError.setForeground(Color.RED);
		costError.setBounds(865, 267, 320, 16);//160->81 865->885
		contentPane.add(costError);
		costError.setVisible(false);
		
		priceError = new JLabel("Invalid Price (Use Decimal Point)");
		priceError.setForeground(Color.RED);
		priceError.setBounds(576, 345, 320, 16);//160->106 576->596
		contentPane.add(priceError);
		priceError.setVisible(false);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			//TODO account for item already being in list/ file
			//TODO make a function for updating dlm
			//TODO add error-checking for text fields here; already checks for barcode clashes intrinsically, 
			//just need to make sure its 6 digit
			//also check brand, whether new/ custom ones can be added
			@Override
			public void mousePressed(MouseEvent e) {//if add item pressed
				Boolean updatingStock=false;//TODO something here; error checking on barcode
				Boolean correctInputs=false;//false if inputs invalid, true if they are valid
				//makes all error labels invisible if there aren't errors
				costError.setVisible(false);
				barcodeError.setVisible(false);
				stockError.setVisible(false);
				priceError.setVisible(false);
				//regex to check each field for adding an item, displays respective errors 
				if(!Pattern.matches("[0-9]*[.]{1}[0-9]*",origCostText.getText())) {
					costError.setVisible(true);
				}
				if(!Pattern.matches("[0-9]{1,}", stockText.getText())) {
					stockError.setVisible(true);
				}
				if(!Pattern.matches("[0-9]{6}",barcodeText.getText())) {
					barcodeError.setVisible(true);
				}
				if(!Pattern.matches("[0-9]*[.]{1}[0-9]*", retailCostText.getText())) {
					priceError.setVisible(true);
				}
				if(!(costError.isVisible()&&stockError.isVisible()&&barcodeError.isVisible()&&priceError.isVisible())) {
					correctInputs=true;
				}
				//if all inputs are valid, then will add the inputted item details to stock
				if(correctInputs) {
				if(mouseNoText.isVisible()) {//if the item to be added is a mouse, save it as a mouse type
					Mouse newMouse=new Mouse(barcodeText.getText(),Name.valueOf(((String)keyMouseBox.getSelectedItem()).toUpperCase()),(ItemType)itemTypeBox.getSelectedItem(),
							brandText.getText(),colorText.getText(),(Connectivity)connBox.getSelectedItem(),Integer.valueOf(stockText.getText()),
							Float.valueOf(origCostText.getText()),Float.valueOf(retailCostText.getText()),Integer.valueOf(mouseNoText.getText()));
					for(int i=0;i<items.size();i++) {//update inventory file and jlist with new item; deleting old version of it
						if(items.get(i).barcode.equals(newMouse.barcode)) {
							dlm.removeElement(items.get(i).toString());
							items.remove(i);
							items.add(newMouse);
							Collections.sort(items,new sortAsc());
							FileInterface.updateItems(items);
							dlm.addElement(newMouse.toString());
							//filteredItems=(ArrayList<Inventory>)items.clone();
							updatingStock=true;
							sortDlm();
						}
					}
					if(!updatingStock) {//if item does not already exist in inventory, then add it
					items.add(newMouse);
					FileInterface.addItem(newMouse);
					Collections.sort(items,new sortAsc());
					dlm.addElement(newMouse.toString());}
					//updateList(items);
				}
				else if(keyLayoutBox.isVisible()) {//if item is keyboard, save it as keyboard type
					Keyboard newKeyboard=new Keyboard(barcodeText.getText(),Name.valueOf(((String)keyMouseBox.getSelectedItem()).toUpperCase()),(ItemType)itemTypeBox.getSelectedItem(),
							brandText.getText(),colorText.getText(),(Connectivity)connBox.getSelectedItem(),Integer.valueOf(stockText.getText()),
							Float.valueOf(origCostText.getText()),Float.valueOf(retailCostText.getText()),(Layout)keyLayoutBox.getSelectedItem());
					for(int i=0;i<items.size();i++) {//update inventory file and jlist with new item; deleting old version of it
						if(items.get(i).barcode.equals(newKeyboard.barcode)) {
							dlm.removeElement(items.get(i).toString());
							items.remove(i);
							items.add(newKeyboard);
							Collections.sort(items,new sortAsc());
							FileInterface.updateItems(items);
							dlm.addElement(newKeyboard.toString());
							//filteredItems=(ArrayList<Inventory>)items.clone();
							updatingStock=true;
							sortDlm();
						}
					}
					if(!updatingStock) {//if item doesn't already exist in inventory, then add it
					items.add(newKeyboard);
					FileInterface.addItem(newKeyboard);
					Collections.sort(items,new sortAsc());
					dlm.addElement(newKeyboard.toString());}
				}
				//Inventory item=new Inventory(brandText.getText(),Name.valueOf(nameText));
				}}
			});
		}
		

	}
	public static void sortDlm() {//sorts the list to be displayed in jlist on the page
		dlm.clear();
		for(int i=0;i<items.size();i++) {
			dlm.addElement(items.get(i).toString());
		}
	}
}
