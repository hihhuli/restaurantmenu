package uni.oulu.hci.restaurantmenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class UserOrder {
	private ArrayList<MenuItem> orderedItems;
	
	public UserOrder() {
		this.orderedItems = new ArrayList<MenuItem>();
	}
	
	public void addtoOrder(MenuItem item) {
		this.orderedItems.add(item);
	}
	
	public void removeFromOrder(String title) {
		for (Iterator<MenuItem> it = this.orderedItems.iterator(); it.hasNext();) {
			MenuItem item = it.next();
			if (item.getTitle().equals(title)) {
				it.remove();
				break;
			}
		}
	}
	
	public int getCount(String title) {
		int count = 0;
		for (MenuItem item : this.orderedItems) {
			if (item.getTitle().equals(title)) {
				count++;
			}
		}
		return count;
	}
}