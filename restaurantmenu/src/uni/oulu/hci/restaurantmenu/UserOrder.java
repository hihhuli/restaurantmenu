package uni.oulu.hci.restaurantmenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class UserOrder implements Serializable {
	private ArrayList<MenuItem> orderedItems;
	
	public UserOrder() {
		this.orderedItems = new ArrayList<MenuItem>();
	}
	
	public void addtoOrder(MenuItem item) {
		this.orderedItems.add(item);
	}
	
	public void removeFromOrder(String title) {
		for (MenuItem item : this.orderedItems) {
			if (item.getTitle().equals(title)) {
				this.orderedItems.remove(item);
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