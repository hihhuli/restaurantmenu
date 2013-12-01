package uni.oulu.hci.restaurantmenu;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainMenuActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        final String[] titles = {"Starters", "Pizzas", "Pastas", "Burgers", "Drinks"};
        final HashMap<String, ArrayList<HashMap<String, String>>> data = createMenuData(titles);
        createTabHost(data, titles);
        ((Button)findViewById(R.id.myOrderButton)).setEnabled(false);
        ((Button)findViewById(R.id.checkoutButton)).setEnabled(false);
    }

    private HashMap<String, ArrayList<HashMap<String, String>>> createMenuData(final String[] titles) {
    	HashMap<String, ArrayList<HashMap<String, String>>> data = new HashMap<String, ArrayList<HashMap<String, String>>>();
    	String[][][] data_arr = new String[][][]{
    			{
	    			{"1. Lumache della casa", "LL, (L, G)", "9.40 € / 15.20 €"},
	    			{"2. Instanta al pollo", "L, (G)", "9.20 €"},
	    			{"3. Insalata al salmone", "D, (G)", "7.90 €"},
	    			{"4. Salad table", "", "3.60 € / 9.20 €"},
	    			{"5. Sweet potato soup", "LL, (G)", "7.90 €"}
    			},
    			{}, {}, {}, {}
    	};
    	
    	final String[] keys = {"title", "diets", "price"};
    	for (int i = 0; i < titles.length; i++) {
    		ArrayList<HashMap<String, String>> tab_data = new ArrayList<HashMap<String, String>>();
    		for (int j = 0; j < data_arr[i].length; j++) {
        		HashMap<String, String> map = new HashMap<String, String>();
    			for (int k = 0; k < keys.length; k++) {
    				map.put(keys[k], data_arr[i][j][k]);
    			}
    			tab_data.add(map);
    		}
    		data.put(titles[i], tab_data);
    	}
    	return data;
    }
    
    

    
    private void createTabHost(final HashMap<String, ArrayList<HashMap<String, String>>> data, String[] titles) {
    	final TabHost tabHost = getTabHost();
     
        for (String title : titles) {
        	TabSpec spec = tabHost.newTabSpec(title);
        	Button tabButton = (Button)getLayoutInflater().inflate(R.layout.tabbutton, null);
        	if (title.equals("Starters")) {
        		tabButton.setBackgroundColor(getResources().getColor(R.color.hilighted));
        	}
        	tabButton.setText(title);
        	Intent intent = new Intent(this, TabMenuActivity.class);
        	intent.putExtra("map", data.get(title));
        	spec.setIndicator(tabButton).setContent(intent);
        	tabHost.addTab(spec);
        }
        
        tabHost.setOnTabChangedListener(new OnTabChangeListener(){
        	@Override
        	public void onTabChanged(String tabId) {
                for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
                {
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.default_color)); //unselected
                }
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(getResources().getColor(R.color.hilighted)); // selected
        }});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first_page, menu);
        return true;
    }

    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        
    	// To save the state of an activity onSaveInstanceState must be overridden to allow for more information to be saved
    	
    	// Save the user's current game state
        //savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
        //savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);
        
        // Always call the superclass so it can save the view hierarchy state
        //super.onSaveInstanceState(savedInstanceState);
    }
    
    public void languagesClicked(final View view) {
    	setResult(RESULT_OK, this.getIntent());
    	finish();
    }
    
    public void checkoutClicked(final View view) {
    	// should be active after there are dishes or drinks that have been paid
    	// launch activity payment options
    	//Intent intent = new Intent(this, CheckoutActivity.class);
    	//startActivityForResult(intent, 0);
    }
    
    public void myOrderClicked(final View view) {
    	// should be active when there are dishes or drinks added to order
    	// launch popup_my_order
    }
    
    public void callWaiterClicked(final View view) {
    	// would send a message to a waiter to come by, not implemented here
    	// feedback to tell that a request was sent
    }
    
    public void searchClicked(final View view) {
    	// launch search popup
    }
    
}