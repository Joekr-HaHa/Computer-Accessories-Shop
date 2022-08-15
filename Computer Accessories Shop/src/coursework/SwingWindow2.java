package coursework;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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

public class SwingWindow2 extends JFrame {

	private JPanel contentPane;

	public static ArrayList<Inventory> items=new ArrayList<Inventory>();//was private
	public static ArrayList<Inventory> filteredItems=new ArrayList<Inventory>();;//was private
	public static ArrayList<Inventory> filteredItemsMouse=new ArrayList<Inventory>();;//was private
	public static ArrayList<Inventory> basketArray=new ArrayList<Inventory>();
	public static DefaultListModel basketView=new DefaultListModel();
	public static DefaultListModel dlm = new DefaultListModel();
	private JScrollPane scrollPane_1;
	private JList list_1;
	private JScrollPane scrollPane;
	private JTextField textField;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SwingWindow2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingWindow2 frame = new SwingWindow2();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 678);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Collections.sort(items, new sortAsc());//might not need this
		filteredItems=items;
		filteredItemsMouse=items;
		for(int i=0;i<(filteredItems).size();i++) {
			dlm.addElement((filteredItems).get(i).toString());
		}
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 16, 566, 456);
		contentPane.add(scrollPane_1);
		
		JList list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList list = (JList)e.getSource();
		        if (e.getClickCount() == 2) {
		        	int index = list.locationToIndex(e.getPoint());
		        	Inventory i = (filteredItems.get(index));//was items
		        	//Customer cust=new Customer();
		        	//cust.addBasket(i);
		        	//check if already in basket
		        	if(basketView.indexOf(i)==-1) {
		        		basketView.addElement(i);//TODO change this to account for filtering
		        		}
		        	/*basketArray=cust.viewBasket();
		    		for(int j=0;j<(basketArray).size();j++) {
		    			basketView.addElement(basketArray.get(j).toString());
		    		}*/
		        	System.out.println(basketView);
		        }
			}
		});
		scrollPane_1.setViewportView(list);
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(dlm);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(596, 51, 466, 298);
		contentPane.add(scrollPane);
		
		
		list_1 = new JList();
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList list = (JList)e.getSource();
		        if (e.getClickCount() == 2) {
		        	int index = list.locationToIndex(e.getPoint());
		        	basketView.remove(index);//TODO change this to account for filtering
		        }
			}
		});
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list_1);
		list_1.setModel(basketView);
		
		JButton btnNewButton = new JButton("Clear Basket");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				basketView.clear();
			}
		});
		btnNewButton.setBounds(899, 375, 117, 29);
		contentPane.add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				JComboBox comboBox=(JComboBox)e.getSource();
				//basketView.filter(comboBox.getSelectedItem());
				filteredItems=(ArrayList<Inventory>) items.clone();
				if(comboBox.getSelectedIndex()!=0) {
				for(int i=0;i<items.size();i++) {
					
					if(!((items.get(i)).brand.equals(comboBox.getSelectedItem()))) {
						System.out.println("Items: "+items.get(i).brand);
						System.out.println("Box: "+comboBox.getSelectedItem());
						filteredItems.remove(items.get(i));
					}
				}
				}
				dlm.clear();
				for(int i=0;i<(filteredItems).size();i++) {
					Boolean flag=false;
					for(int j=0;j<filteredItemsMouse.size();j++) {
						if(filteredItems.get(i)==filteredItemsMouse.get(j)) {
							flag=true;
						}
					}
					if(flag) {
						dlm.addElement((filteredItems).get(i).toString());
					}
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"No Filter", "Advent", "Anker", "Apple", "Asus", "Corsair", "Logitech", "Microsoft"}));
		comboBox.setBounds(16, 501, 152, 66);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Filter Brand");
		lblNewLabel.setBounds(29, 495, 88, 16);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"No Filter", "1", "2", "3", "4"}));
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				JComboBox comboBox=(JComboBox)e.getSource();
				//basketView.filter(comboBox.getSelectedItem());
				filteredItemsMouse=(ArrayList<Inventory>) items.clone();
				if(comboBox.getSelectedIndex()!=0) {
				for(int i=0;i<items.size();i++) {
					Inventory currentItem=items.get(i);
					if(currentItem instanceof Mouse) {
						Mouse currentMouse=(Mouse)items.get(i);
						int num=Integer.parseInt((String)comboBox.getSelectedItem());
						if(!(currentMouse.numButtons==num)) {
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
				for(int i=0;i<(filteredItems).size();i++) {
					Boolean flag=false;
					for(int j=0;j<filteredItemsMouse.size();j++) {
						if(filteredItems.get(i)==filteredItemsMouse.get(j)) {
							flag=true;
						}
					}
					if(flag) {
						dlm.addElement((filteredItems).get(i).toString());
					}
				}
				
			}
		});
		comboBox_1.setBounds(261, 520, 117, 29);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("No. Of Mouse Buttons");
		lblNewLabel_1.setBounds(266, 495, 144, 16);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				JFrame LoginWindow=new Login();
				LoginWindow.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(899, 538, 117, 29);
		contentPane.add(btnNewButton_1);

	}
}
