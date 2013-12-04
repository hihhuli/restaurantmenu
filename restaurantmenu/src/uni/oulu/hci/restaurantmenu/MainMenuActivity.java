package uni.oulu.hci.restaurantmenu;

import java.io.Serializable;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainMenuActivity extends TabActivity {
    
    private PopupWindow myorderpopup;
    private MenuDataSource dataSource;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        final String[] titles = {"Starters", "Pizzas", "Pastas", "Burgers", "Drinks"};
        
        dataSource = new MenuDataSource(this);
        dataSource.open();
        
        dataSource.deleteAllMenuItems();
        
        dataSource.createMenuItem("Lumache della casa", 
                "LL, (L, G)", 
                9.4, 
                "Pizzas", 
                "Description", 
                "Hot;Mild", 
                "pizza.png", 
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small");
        dataSource.createMenuItem("Lumache della casa2", 
                "LL, (L, G)", 
                9.4, 
                "Pizzas", 
                "Description", 
                "Hot;Mild", 
                "pizza.png", 
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small");
        dataSource.createMenuItem("Lumache della casa3", 
                "LL, (L, G)", 
                9.4, 
                "Pizzas", 
                "Description", 
                "Hot;Mild", 
                "pizza.png", 
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small");
        dataSource.createMenuItem("Lumache della casa4", 
                "LL, (L, G)", 
                9.4, 
                "Pizzas", 
                "Description", 
                "Hot;Mild", 
                "pizza.png", 
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small");
        dataSource.createMenuItem("Lumache della casa5", 
                "LL, (L, G)", 
                9.4, 
                "Pizzas", 
                "Description", 
                "Hot;Mild", 
                "pizza.png", 
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small");
        dataSource.createMenuItem("Lumache della casa6", 
                "LL, (L, G)", 
                9.4, 
                "Pizzas", 
                "Description", 
                "Hot;Mild", 
                "pizza.png", 
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small");
        dataSource.createMenuItem("Instanta al pollo", 
                "L, (G)", 
                9.2, 
                "Pizzas", 
                "Description", 
                "Hot;Mild", 
                "pizza.png", 
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small");
        dataSource.createMenuItem("Lumache della casa7", 
                "LL, (L, G)", 
                9.4, 
                "Pizzas", 
                "Description", 
                "Hot;Mild", 
                "pizza.png", 
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small");
        dataSource.createMenuItem("Lumache della casa8", 
                "LL, (L, G)", 
                9.4, 
                "Pizzas", 
                "Description", 
                "Hot;Mild", 
                "pizza.png", 
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small");
        dataSource.createMenuItem("Lumache della casa9", 
                "LL, (L, G)", 
                9.4, 
                "Pizzas", 
                "Description", 
                "Hot;Mild", 
                "pizza.png", 
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small");
        dataSource.createMenuItem("Insalata al salmone", 
                "D, (G)", 
                7.9, 
                "Pizzas", 
                "Description", 
                "Hot;Mild", 
                "pizza.png", 
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small");
        dataSource.createMenuItem("Salad table", 
                "", 
                3.6, 
                "Starters", 
                "Description", 
                "Hot;Mild", 
                "pizza.png", 
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small");
        dataSource.createMenuItem("Sweet potato soup", 
                "LL, (G)", 
                7.9, 
                "Starters", 
                "Description", 
                "Hot;Mild", 
                "pizza.png", 
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small");
        
        createTabHost(titles);
        ((Button)findViewById(R.id.myOrderButton)).setEnabled(false);
        ((Button)findViewById(R.id.checkoutButton)).setEnabled(false);
    }
    
    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }
    
    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
    
    private void createTabHost(String[] titles) {
        final TabHost tabHost = getTabHost();
        
        for (String title : titles) {
            TabSpec spec = tabHost.newTabSpec(title);
            Button tabButton = (Button)getLayoutInflater().inflate(R.layout.tabbutton, null);
            if (title.equals("Starters")) {
                tabButton.setBackgroundColor(getResources().getColor(R.color.hilighted));
            }
            tabButton.setText(title);
            Intent intent = new Intent(this, TabMenuActivity.class);
            intent.putExtra("data", (Serializable)dataSource.getMenuItemsByCategory(title));
            spec.setIndicator(tabButton).setContent(intent);
            tabHost.addTab(spec);
        }
        
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
        Log.d("MainMenuActivity","Checkout button clicked.");
    }
    
    public void myOrderClicked(final View view) {
        // should be active when there are dishes or drinks added to order
        // launch popup_my_order
        Log.d("MainMenuActivity","My order button clicked.");
        
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = vi.inflate(R.layout.popup_my_order, null);
        
        myorderpopup = new PopupWindow(popupView);
        myorderpopup.showAtLocation((View) findViewById(R.id.menutab), Gravity.LEFT | Gravity.TOP, 15, 60);
        myorderpopup.setFocusable(true);
        myorderpopup.update(570, 900);
    }
}
