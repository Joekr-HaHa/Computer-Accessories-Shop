package coursework;

import java.util.ArrayList;
import java.util.Collections;

public class Customer extends Person {

	private static ArrayList<Inventory> basket=new ArrayList<Inventory>();//was private
	
	public Customer(String userID,String username,String name,int houseNumber,String postcode,String city) {//constructor
		super(userID,username,name,houseNumber,postcode,city);
		// TODO Auto-generated constructor stub
	}
	
	public void viewProducts(ArrayList<Inventory> items){//views the customer-accessible products and sorts them
		 Collections.sort(items, new sortAsc());
		for(int i=0;i<items.size();i++){
			System.out.println("Asc");
		    System.out.println(items.get(i).toString());}
		    
	}
	
	public ArrayList<Inventory> viewBasket() {//returns customer's basket
		/*
		for(int i=0;i<basket.size();i++) {
			return(basket.get(i).toString());
		}*/
		return basket;
	}
	
	public void setBasket(ArrayList<Inventory>items) {//updates the customer's basket
		basket=items;
	}
	public void addBasket(Inventory i) {//adds an item to the customer's basket
		basket.add(i);
		
	}
	
	public float getBasketPrice() {//returns the total price of a customer's basket
		float total=0;
		for(int i=0;i<basket.size();i++) {
			total+=basket.get(i).retailPrice;
		}
		return total;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
