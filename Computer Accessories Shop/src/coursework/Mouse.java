package coursework;

public class Mouse extends Inventory {
	
	public int numButtons;
	
	public Mouse(String barcode,Name name,ItemType type,String brand,String color,Connectivity connectivity,
			int stock, float originalCost, float retailPrice,int numButtons) {
		super(barcode,name,type,brand,color,connectivity,
			stock, originalCost,retailPrice);
		this.numButtons=numButtons;
		
		// TODO Auto-generated constructor stub
	}
	public String toString() {//custom tostring function, to show only data customer can access
		return "Barcode: "+barcode+" Name: "+name+" Type: "+type+" Brand: "+brand+
				" Color: "+color+" Connectivity: "+connectivity+" Stock: "+Integer.toString(stock)+
				" Retail Price: "+Float.toString(retailPrice)+
				" Number Of Buttons: "+Integer.toString(numButtons);
	}
	public String toStringAdmin() {//custom tostring function, to show all data admin can access
		return "Barcode: "+barcode+" Name: "+name+" Type: "+type+" Brand: "+brand+
				" Color: "+color+" Connectivity: "+connectivity+" Stock: "+Integer.toString(stock)+
				" Original Cost: "+Float.toString(originalCost)+" Retail Price: "+Float.toString(retailPrice)+
				" Number Of Buttons: "+Integer.toString(numButtons);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
