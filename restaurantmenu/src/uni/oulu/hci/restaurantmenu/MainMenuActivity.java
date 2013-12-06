package uni.oulu.hci.restaurantmenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class MainMenuActivity extends FragmentActivity {
    
    private PopupWindow myorderpopup;
    private PopupWindow corfirmedpopup;
    private HashMap<String, List<MenuItem>> data;
    private UserOrder userOrder;
    private String currentFragmentTag;
    
    @SuppressWarnings("unchecked")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        final String[] titles = {"Starters", "Pizzas", "Pastas", "Burgers", "Drinks"};
        
        //Starters tab contents
        
        Intent intent = getIntent();
        this.data = (HashMap<String, List<MenuItem>>)intent.getSerializableExtra("data");
        this.userOrder = (UserOrder)intent.getSerializableExtra("order");
        createTabHost(titles);
        if (this.userOrder.isEmpty()) {
        	((Button)findViewById(R.id.myOrderButton)).setEnabled(false);
        }
        if (this.userOrder.isConfirmedEmpty()) {
        	((Button)findViewById(R.id.checkoutButton)).setEnabled(false);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first_page, menu);
        return true;
    }
    
    public UserOrder getUserOrder() {
    	return this.userOrder;
    }
    
    private void createTabHost(String[] titles) {
    	final FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        
        for (String title : titles) {
            TabSpec spec = tabHost.newTabSpec(title);
            Button tabButton = (Button)getLayoutInflater().inflate(R.layout.tabbutton, null);
            if (title.equals("Starters")) {
                tabButton.setBackgroundColor(getResources().getColor(R.color.hilighted));
            }
            tabButton.setText(title);
            Bundle args = new Bundle();
            args.putSerializable("data", (Serializable)getMenuItemsByCategory(title));
            spec.setIndicator(tabButton);
            tabHost.addTab(spec, TabMenuFragment.class, args);
        }
        
        this.currentFragmentTag = "Starters";
        
        tabHost.setOnTabChangedListener(new OnTabChangeListener(){
            /**
             * http://stackoverflow.com/questions/2099959/how-do-i-change-the-background-of-an-android-tab-widget
             */
        	@Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++)
                {
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.default_color)); //unselected
                }
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(getResources().getColor(R.color.hilighted)); // selected
                currentFragmentTag = tabId;
            }});
    }
    
    private List<MenuItem> getMenuItemsByCategory(String title) {
    	return this.data.get(title);
    }
    
    public void languagesClicked(final View view) {
    	Intent resultIntent = new Intent();
    	resultIntent.putExtra("data", (Serializable)this.data);
    	resultIntent.putExtra("order", (Serializable)this.userOrder);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    
    public void checkoutClicked(final View view) {
        // should be active after there are dishes or drinks that have been paid
        // launch activity payment options
        //Intent intent = new Intent(this, CheckoutActivity.class);
        //startActivityForResult(intent, 0);
        Log.d("MainMenuActivity","Checkout button clicked.");
        Log.d("MainMenuActivity","New intent.");
        Intent intent = new Intent(this, PaymentOptionsActivity.class);
        Log.d("MainMenuActivity","Starting activity.");
        startActivityForResult(intent, 0);
    }
    
    public void myOrderClicked(final View view) {
        // should be active when there are dishes or drinks added to order
        // launch popup_my_order
        Log.d("MainMenuActivity","My order button clicked.");
        
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = vi.inflate(R.layout.popup_my_order, null);
        
        populateMyOrderView(popupView);
        this.myorderpopup = new PopupWindow(popupView);
        
        this.myorderpopup.showAtLocation((View) findViewById(R.id.buttonLayout2), Gravity.LEFT | Gravity.TOP, 15, 60);
        
        this.myorderpopup.setFocusable(true);
        this.myorderpopup.update(570, 900);
    }
    
    public void closeMyOrderClicked(final View view) {
        // close search popup with cancel
        Log.d("MainMenuActivity","Close button clicked.");
        
        this.myorderpopup.dismiss();
    }
    
    public void clearMyOrderClicked(final View view) {
        // close search popup with ok
        Log.d("MainMenuActivity","Clear button clicked.");
        
        TabMenuFragment fragment = getCurrentFragment();
        fragment.setItemOrderViews((ViewGroup)fragment.getExpandedView(), 0);
        this.userOrder.clearWaiting();
        if (this.userOrder.isConfirmedEmpty()) {
        	((Button)findViewById(R.id.myOrderButton)).setEnabled(false);
            this.myorderpopup.dismiss();
        } else {
        	((Button)view.getRootView().findViewById(R.id.confirmOrderButton)).setEnabled(false);
        	((Button)view.getRootView().findViewById(R.id.clearAllButton)).setEnabled(false);
        }
        
        //myorderpopup.dismiss();
    }
    
    public void confirmMyOrderClicked(final View view) {
        // close search popup with ok
        Log.d("MainMenuActivity","Confirm button clicked.");
        this.myorderpopup.dismiss();
        ((Button)findViewById(R.id.checkoutButton)).setEnabled(true);
        this.userOrder.confirmOrder();
        
        TabMenuFragment fragment = getCurrentFragment();
        fragment.setItemOrderViews((ViewGroup)fragment.getExpandedView(), 0);
        
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = vi.inflate(R.layout.popup_order_confirmed, null);
        
        this.corfirmedpopup = new PopupWindow(popupView);
        this.corfirmedpopup.showAtLocation((View) findViewById(R.id.buttonLayout2), Gravity.LEFT | Gravity.TOP, 100, 260);
        this.corfirmedpopup.setFocusable(true);
        this.corfirmedpopup.update(400, 300);
    }
    
    public void removeFromWaitingClicked(final View view) {
    	View parentView = (View) view.getParent().getParent();
    	LinearLayout layout = (LinearLayout)parentView.getParent();
    	View popupView = layout.getRootView();
    	
    	int index = layout.indexOfChild(parentView);
    	MenuItem item = this.userOrder.getWaitingItems().get(index);
    	this.userOrder.removeFromOrder(index);
    	layout.removeView(parentView);
    	((TextView)popupView.findViewById(R.id.totalPriceView)).setText(this.userOrder.calculateOrderSum() + " €");
    	
    	TabMenuFragment fragment = getCurrentFragment();
        fragment.setItemOrderViews((ViewGroup)fragment.getExpandedView(), this.userOrder.getCount(item.getTitle()));
    	
    	if (layout.getChildCount() == 0 && this.userOrder.isConfirmedEmpty()) {
    		((Button)findViewById(R.id.myOrderButton)).setEnabled(false);
    		this.myorderpopup.dismiss();
    	} else if (layout.getChildCount() == 0) {
    		((Button)popupView.findViewById(R.id.confirmOrderButton)).setEnabled(false);
    		((Button)popupView.findViewById(R.id.clearAllButton)).setEnabled(false);
    	}
    }
    
    private void populateOrderedItems(List<MenuItem> items, LinearLayout scrollViewLayout, int layoutId) {
    	LinearLayout layout;
    	for (MenuItem item : items) {
	    	layout = (LinearLayout)getLayoutInflater().inflate(layoutId, null);
	        ((TextView)layout.findViewById(R.id.itemTitleView)).setText(item.getTitle());
	        ((TextView)layout.findViewById(R.id.itemPriceView)).setText(Double.toString(item.getPrice()) + " €");
	        scrollViewLayout.addView(layout);
	    }
    }
    
    private void populateMyOrderView(View view) {
    	LinearLayout waitingScrLayout =(LinearLayout)view.findViewById(R.id.waitingScrollViewLayout);
    	
    	if (!this.userOrder.isConfirmedEmpty()) {
        	LinearLayout confirmedScrLayout = (LinearLayout)view.findViewById(R.id.confirmedScrollViewLayout);
        	((View)view.findViewById(R.id.confirmedView)).setVisibility(View.VISIBLE);
        	((View)view.findViewById(R.id.confirmedSeparator)).setVisibility(View.VISIBLE);
        	((View)view.findViewById(R.id.confirmedScrollView)).setVisibility(View.VISIBLE);
    		populateOrderedItems(this.userOrder.getConfirmedItems(), confirmedScrLayout, R.layout.confirmed_item);
    	}
    	if (!this.userOrder.isWaitingEmpty()) {
    		populateOrderedItems(this.userOrder.getWaitingItems(), waitingScrLayout, R.layout.waiting_item);
    	} else {
    		((Button)view.findViewById(R.id.confirmOrderButton)).setEnabled(false);
    		((Button)view.findViewById(R.id.clearAllButton)).setEnabled(false);
    	}
    	((TextView)view.findViewById(R.id.totalPriceView)).setText(this.userOrder.calculateOrderSum() + " €");
    }
    
    public void confirmationOkClicked(final View view) {
        // close search popup with cancel
        Log.d("MainMenuActivity","OrderSent popup clicked.");
        
        this.corfirmedpopup.dismiss();
    }
    
    public TabMenuFragment getCurrentFragment() {
    	return (TabMenuFragment)getSupportFragmentManager().findFragmentByTag(currentFragmentTag);
    }
    
    public void searchClicked(final View view) {
        TabMenuFragment fragment = getCurrentFragment();
        fragment.searchClicked(view);
    }
    
    public void cancelSearchClicked(final View view) {
        TabMenuFragment fragment = getCurrentFragment();
        fragment.cancelSearchClicked(view);
    }
    
    public void okSearchClicked(final View view) {
        TabMenuFragment fragment = getCurrentFragment();
        fragment.okSearchClicked(view);
    }
    
    public void clearSearchClicked(final View view) {
        TabMenuFragment fragment = getCurrentFragment();
        fragment.clearSearchClicked(view);
    }
    
    public void itemClicked(final View view) {
    	TabMenuFragment fragment = getCurrentFragment();
        fragment.itemClicked(view, this.userOrder);
    }
    
    public void personalChoicesOpened(final View view) {
    	TabMenuFragment fragment = getCurrentFragment();
        fragment.personalChoicesOpened(view, this.userOrder);
    }
    
    public void personalChoicesClosed(final View view) {
    	TabMenuFragment fragment = getCurrentFragment();
        fragment.personalChoicesClosed(view, this.userOrder);
    }
    
    public void addToOrderClicked(final View view) {
    	TabMenuFragment fragment = getCurrentFragment();
        fragment.addToOrderClicked(view, this.userOrder);
    }
    
    public void removeFromOrderClicked(final View view) {
    	TabMenuFragment fragment = getCurrentFragment();
        fragment.removeFromOrderClicked(view, this.userOrder);
    }
    
    public void likesClicked(final View view) {
    	TabMenuFragment fragment = getCurrentFragment();
    	fragment.likesClicked(view);
    }

    public void callWaiterClicked(final View view) {
        // would send a message to a waiter to come by, not implemented here
        // feedback to tell that a request was sent
        Log.d("TabMenuActivity","Call waiter button clicked.");
    }  
}
