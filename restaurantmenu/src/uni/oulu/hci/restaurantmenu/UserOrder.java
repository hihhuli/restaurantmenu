package uni.oulu.hci.restaurantmenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("serial")
public class UserOrder implements Serializable {
	private ArrayList<MenuItem> orderedItems;
	private ArrayList<MenuItem> confirmedItems;
	
	public UserOrder() {
		this.orderedItems = new ArrayList<MenuItem>();
		this.confirmedItems = new ArrayList<MenuItem>();
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
	
	public void confirmOrder() {
		this.confirmedItems.addAll(orderedItems);
		this.orderedItems.clear();
	}
	
	public boolean isConfirmedEmpty() {
		return this.confirmedItems.isEmpty();
	}
	
	public boolean isEmpty() {
		return this.orderedItems.isEmpty() && this.confirmedItems.isEmpty();
	}
}