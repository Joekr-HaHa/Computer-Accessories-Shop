package coursework;

import java.util.Comparator;
//import java.util.Date;
class sortAsc implements Comparator<Inventory>{
public int compare(Inventory a,Inventory b){//compares two prices to help sort list in ascending order
	return Float.compare(a.retailPrice,b.retailPrice);
 // return b.retailPrice.compareTo(a.retailPrice);
}
}