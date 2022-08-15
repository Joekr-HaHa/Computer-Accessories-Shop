package coursework;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Login extends JFrame {
	
	private static ArrayList<Person> people=new ArrayList<Person>();//stores all people in useraccounts.txt as a person
	public static Person person;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		BufferedReader br=null;
	    try{
	    	///Users/joestraker/Documents/coursework oop/UserAccounts.txt
	    	//System.out.println(new File("UserAccounts.txt").getAbsoluteFile());
	    br=new BufferedReader(new FileReader("UserAccounts.txt"));//creates a reader to read useraccounts.txt
	    String line=null;
	    while((line=br.readLine())!=null){//this part reads every line of useraccounts.txt, splitting each piece of data on a comma
	      //System.out.println(line);
	      String[] data=line.split(", ");
	      //System.out.println(data[1]+" mouse "+" keyboard ");
	      if(data[6].contains("admin")) {//if the line read is an admin, create an admin class with the details of that line
	    	  //Mouse mouse=new Mouse(data[0],Name.valueOf(data[1].toUpperCase()),ItemType.valueOf(data[2].toUpperCase()),data[3],data[4],Connectivity.valueOf(data[5].toUpperCase()),
	    	//		  Integer.valueOf(data[6]),Float.valueOf(data[7]),Float.valueOf(data[8]),Integer.valueOf(data[9]));
	    	  Admin admin=new Admin(data[0],data[1],data[2],Integer.valueOf(data[3]),data[4],data[5]);
	    	  
	    	  people.add(admin);
	      }
	      else if(data[6].contains("customer")) {//if the line read is a customer, create a customer class with the details of that line
	    	 // Keyboard keyboard=new Keyboard(data[0],Name.valueOf(data[1].toUpperCase()),ItemType.valueOf(data[2].toUpperCase()),data[3],data[4],Connectivity.valueOf(data[5].toUpperCase()),
	    	//		  Integer.valueOf(data[6]),Float.valueOf(data[7]),Float.valueOf(data[8]),Layout.valueOf(data[9].toUpperCase()));
	    	  Customer customer=new Customer(data[0],data[1],data[2],Integer.valueOf(data[3]),data[4],data[5]);
	    	  people.add(customer);
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
	    for(int i=0;i<people.size();i++){//error checking
	        System.out.println(people.get(i).toString());}
	    System.out.println("Finished");
	    //Customer cust=new Customer();
	    //cust.viewProducts(items);
	}
	public static Person getPerson() {
		return person;
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {//triggers when user selects an element in the combobox
				if(e.getStateChange()==ItemEvent.SELECTED) {
				JComboBox comboBox=(JComboBox)e.getSource();
				if(comboBox.getSelectedIndex()!=0) {//if the selected item isn't "Select User"
				for(int i=0;i<people.size();i++) {
					System.out.println(people.get(i).username);
					System.out.println(comboBox.getSelectedItem());
					if(people.get(i).username.equals(comboBox.getSelectedItem())) {//will find the selected user in the people array
						person=people.get(i);//gets the user information
						dispose();//closes window
						SwingWindow window=new SwingWindow();
						//window.main(null);
						window.setVisible(true);
						System.out.println("Doing it");//creates a window and opens it
						break;
					}
				}
				
			}
				}
			}
		});
		ArrayList<String> stuffs=new ArrayList<String>();//makes an array and populates it with the usernames from people
		stuffs.add("Select User");
		for(int i=0;i<people.size();i++) {
			if(!stuffs.contains(people.get(i).username)) {
			stuffs.add(people.get(i).username);}
		}
		comboBox.setModel(new DefaultComboBoxModel(stuffs.toArray()));//makes a combobox with the usernames from people
		comboBox.setBounds(96, 122, 203, 58);
		contentPane.add(comboBox);
		JLabel lblNewLabel = new JLabel("Select User");
		lblNewLabel.setBounds(143, 94, 111, 16);
		contentPane.add(lblNewLabel);
	}
}
