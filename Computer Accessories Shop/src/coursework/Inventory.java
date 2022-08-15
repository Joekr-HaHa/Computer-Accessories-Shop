package coursework;


public class Inventory {
	
	public String barcode;
	public Name name;
	public ItemType type;
	public String brand;
	public String color;
	public Connectivity connectivity;
	public int stock;
	public float originalCost;
	public float retailPrice;
	public String additionalInfo;

	//constructor
	public Inventory(String barcode,Name name,ItemType type,String brand,String color,Connectivity connectivity,
			int stock, float originalCost, float retailPrice) {
		// TODO Auto-generated constructor stub
		this.barcode=barcode;
		this.name=name;
		this.type=type;
		this.brand=brand;
		this.color=color;
		this.connectivity=connectivity;
		this.stock=stock;
		this.originalCost=originalCost;
		this.retailPrice=retailPrice;
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
}
