package coursework;

public class Keyboard extends Inventory {

	public Layout layout;
	
	//constructor
	public Keyboard(String barcode,Name name,ItemType type,String brand,String color,Connectivity connectivity,
			int stock, float originalCost, float retailPrice,Layout layout) {
		super(barcode,name,type,brand,color,connectivity,
			stock, originalCost,retailPrice);
		this.layout=layout;
		
		// TODO Auto-generated constructor stub
	}
	public String toString() {//custom tostring function, to show only data customer can access
		return "Barcode: "+barcode+" Name: "+name+" Type: "+type+" Brand: "+brand+
				" Color: "+color+" Connectivity: "+connectivity+" Stock: "+Integer.toString(stock)+
				" Retail Price: "+Float.toString(retailPrice)+
				" Layout: "+layout;
	}
	public String toStringAdmin() {//custom tostring function, to show all data admin can access
		return "Barcode: "+barcode+" Name: "+name+" Type: "+type+" Brand: "+brand+
				" Color: "+color+" Connectivity: "+connectivity+" Stock: "+Integer.toString(stock)+
				" Original Cost: "+Float.toString(originalCost)+" Retail Price: "+Float.toString(retailPrice)+
				" Layout: "+layout;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
