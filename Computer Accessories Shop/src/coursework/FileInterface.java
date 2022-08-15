package coursework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class FileInterface {
	
	public static Boolean readFrom=false;
	
	public static ArrayList<Inventory> returnItems(){//returns all items in inventory as an arraylist of type inventory elements
		ArrayList<Inventory> items=new ArrayList<Inventory>();
		BufferedReader br=null;
	    try{
	    br=new BufferedReader(new FileReader("Stock.txt"));//src/coursework/Stock.txt
	    String line=null;
	    while((line=br.readLine())!=null){
	      //System.out.println(line);
	      String[] data=line.split(", ");
	      //System.out.println(data[1]+" mouse "+" keyboard ");
	      if(data[1].toUpperCase().contains("MOUSE")) {
	    	  Mouse mouse=new Mouse(data[0],Name.valueOf(data[1].toUpperCase()),ItemType.valueOf(data[2].toUpperCase()),data[3],data[4],Connectivity.valueOf(data[5].toUpperCase()),
	    			  Integer.valueOf(data[6]),Float.valueOf(data[7]),Float.valueOf(data[8]),Integer.valueOf(data[9]));
	    	  items.add(mouse);
	      }
	      else if(data[1].toUpperCase().contains("KEYBOARD")) {
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
	    readFrom=true;
	    return items;
	}
	
	public static void updateItems(ArrayList<Inventory> items) {//rewrites entire inventory to stock file
		try {
			System.out.println("Over here big ears");
			FileWriter fw=new FileWriter("Stock.txt");
			for(int i=0;i<items.size();i++) {
				Inventory currentItem=items.get(i);
				if(currentItem instanceof Mouse) {
					Mouse currentMouse=(Mouse)currentItem;
				fw.write(currentItem.barcode+", "+currentItem.name.toString().toLowerCase()+", "+
				currentItem.type.toString().toLowerCase()+", "+currentItem.brand.toString()+", "+currentItem.color.toString()+", "+
						currentItem.connectivity.toString().toLowerCase()+", "+String.valueOf(currentItem.stock)+", "+
				String.valueOf(currentItem.originalCost)+", "+String.valueOf(currentItem.retailPrice)+", "+
						String.valueOf(currentMouse.numButtons)+"\n");}
				else {
					Keyboard currentKeyboard=(Keyboard)currentItem;
					fw.write(currentItem.barcode+", "+currentItem.name.toString().toLowerCase()+", "+
							currentItem.type.toString().toLowerCase()+", "+currentItem.brand.toString()+", "+currentItem.color.toString()+", "+
									currentItem.connectivity.toString().toLowerCase()+", "+String.valueOf(currentItem.stock)+", "+
							String.valueOf(currentItem.originalCost)+", "+String.valueOf(currentItem.retailPrice)+", "+
									String.valueOf(currentKeyboard.layout)+"\n");
				}
				System.out.println("Writing");
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addItem(Inventory currentItem) {//adds a new item onto the end of stock file
		try {
			FileWriter fw=new FileWriter("Stock.txt",true);
			if(currentItem instanceof Mouse) {
				Mouse currentMouse=(Mouse)currentItem;
			fw.write(currentItem.barcode+", "+currentItem.name.toString().toLowerCase()+", "+
			currentItem.type.toString().toLowerCase()+", "+currentItem.brand.toString()+", "+currentItem.color.toString()+", "+
					currentItem.connectivity.toString().toLowerCase()+", "+String.valueOf(currentItem.stock)+", "+
			String.valueOf(currentItem.originalCost)+", "+String.valueOf(currentItem.retailPrice)+", "+
					String.valueOf(currentMouse.numButtons)+"\n");}
			else {
				Keyboard currentKeyboard=(Keyboard)currentItem;
				fw.write(currentItem.barcode+", "+currentItem.name.toString().toLowerCase()+", "+
						currentItem.type.toString().toLowerCase()+", "+currentItem.brand.toString()+", "+currentItem.color.toString()+", "+
								currentItem.connectivity.toString().toLowerCase()+", "+String.valueOf(currentItem.stock)+", "+
						String.valueOf(currentItem.originalCost)+", "+String.valueOf(currentItem.retailPrice)+", "+
								String.valueOf(currentKeyboard.layout)+"\n");
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> returnBrands(ArrayList<Inventory>items){//returns all unique brands in inventory
		ArrayList<String>brands=new ArrayList<String>();
		brands.add("No Filter");
		for(int i=0;i<items.size();i++) {
			if(!brands.contains(items.get(i).brand)) {
				brands.add(items.get(i).brand);
			}
		}
		Collections.sort(brands.subList(1, brands.size()));
		return brands;
		
	}

}
