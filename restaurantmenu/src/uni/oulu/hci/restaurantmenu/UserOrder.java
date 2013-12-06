package uni.oulu.hci.restaurantmenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("serial")
public class UserOrder implements Serializable {
	private ArrayList<MenuItem> waitingItems;
	private ArrayList<MenuItem> confirmedItems;
	
	public UserOrder() {
		this.waitingItems = new ArrayList<MenuItem>();
		this.confirmedItems = new ArrayList<MenuItem>();
	}
	
	public void addtoOrder(MenuItem item) {
		this.waitingItems.add(item);
	}
	
	public void removeFromOrder(String title) {
		for (Iterator<MenuItem> it = this.waitingItems.iterator(); it.hasNext();) {
			MenuItem item = it.next();
			if (item.getTitle().equals(title)) {
				it.remove();
				break;
			}
		}
	}
	
	public int getCount(String title) {
		int count = 0;
		for (MenuItem item : this.waitingItems) {
			if (item.getTitle().equals(title)) {
				count++;
			}
		}
		return count;
	}
	
	public ArrayList<MenuItem> getConfirmedItems() {
		return this.confirmedItems;
	}
	
	public ArrayList<MenuItem> getWaitingItems() {
		return this.waitingItems;
	}
	
	public void confirmOrder() {
		this.confirmedItems.addAll(this.waitingItems);
		this.waitingItems.clear();
	}
	
	public boolean isConfirmedEmpty() {
		return this.confirmedItems.isEmpty();
	}
	
	public boolean isEmpty() {
		return this.waitingItems.isEmpty() && this.confirmedItems.isEmpty();
	}
	
	public double calculateOrderSum() {
		double sum = 0;
		for (MenuItem item : this.waitingItems) {
			sum += item.getPrice();
		}
		for (MenuItem item : this.confirmedItems) {
			sum += item.getPrice();
		}
		sum *= 100;
		sum = Math.round(sum);
		sum /= 100;
		return sum;
	}
}