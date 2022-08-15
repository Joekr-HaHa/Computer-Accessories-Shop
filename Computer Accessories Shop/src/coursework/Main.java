package coursework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
	
	public static ArrayList<Inventory> items=new ArrayList<Inventory>();//was private

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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

	}
	}

