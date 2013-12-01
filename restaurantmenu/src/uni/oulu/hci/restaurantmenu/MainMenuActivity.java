package uni.oulu.hci.restaurantmenu;

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
        
        final TabHost tabHost = getTabHost();
        TabSpec startersTab = tabHost.newTabSpec("Starters");
        TabSpec pizzasTab = tabHost.newTabSpec("Pizzas");
        TabSpec pastasTab= tabHost.newTabSpec("Pastas");
        TabSpec burgersTab = tabHost.newTabSpec("Burgers");
        TabSpec drinksTab= tabHost.newTabSpec("Drinks");
        
        TabSpec[] specs = {startersTab, pizzasTab, pastasTab, burgersTab, drinksTab};
        
        for (TabSpec spec : specs) {
        	Button tabButton = (Button)getLayoutInflater().inflate(R.layout.tabbutton, null);
        	String tag = spec.getTag();
        	if (tag.equals("Starters")) {
        		tabButton.setBackgroundColor(getResources().getColor(R.color.hilighted));
        	}
        	tabButton.setText(tag);
        	spec.setIndicator(tabButton).setContent(new Intent(this, TabMenuActivity.class));
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

    public static void setTabColor(TabHost tabhost) {

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first_page, menu);
        return true;
    }
    
    public void languagesClicked(final View view) {
        setResult(RESULT_OK, this.getIntent());
        finish();
    }

	public void checkoutClicked(final View view) {
	        // should be active after there are dishes or drinks that have been paid
	        // launch activity payment options
	}
	
	public void myOrderClicked(final View view) {
	        // should be active when there are dishes or drinks added to order
	        // launch popup_my_order
	}
}
