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
    private PopupWindow corfirmedpopup;
    private MenuDataSource dataSource;
    private UserOrder userOrder;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        final String[] titles = {"Starters", "Pizzas", "Pastas", "Burgers", "Drinks"};
        UserOrder userOrder = new UserOrder();
        
        dataSource = new MenuDataSource(this);
        dataSource.open();
        
        dataSource.deleteAllMenuItems();
        
        //Starters tab contents
        dataSource.createMenuItem("1. Butternut Squash", 
                "L, (G)", 
                10, 
                "Starters", 
                "Butternut squash, garlic, unsalted butter, olive oil, walnuts, blue cheese, honey black pepper", 
                "Mild", 
                "salad", 
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("2. Lumache Della Casa", 
                "LL, (L, G)", 
                9.4, 
                "Starters", 
                "Fresh salad, pomegranate seeds, olives, marinated paprika and chili mayonnaise", 
                "Mild", 
                "salad", 
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("3. Classic Caesar Salad", 
                "L", 
                9.9, 
                "Starters", 
                "Unpeeled raw prawns, sweet chilli sauce, Japanese soy sauce, garlic cloves, olive oil, rye bread, steaky beacon", 
                "Strong", 
                "salad",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("4. Hot Caramel Pastry", 
                "L, (G)", 
                8.9, 
                "Starters", 
                "Frozen puff pastry, camembert, cooked mixed pepper, black pepper, sprig of fresh thyme leave, egg yolk", 
                "None", 
                "salad",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("5. Butternut Soup", 
                "L, D", 
                12.5, 
                "Starters", 
                "Butternut squash, unsalted butter, onion, ginger, garlic, garlic chopped, peanut butter, sea salt and black pepper.", 
                "Mild", 
                "salad",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("6. Instanta al pollo", 
                "L, (G)", 
                9.2, 
                "Starters", 
                "Fresh salad, cheese, chicken, tomatoes, artichoke", 
                "Mild", 
                "salad",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("7. Insalata al salmone", 
                "D, (G)", 
                7.9, 
                "Starters", 
                "Salmon, lemon salvia mayonnaise, thyme bread, octopus", 
                "None", 
                "salad",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("8. Salad table", 
                "", 
                3.6, 
                "Starters", 
                "A delicious assortment of different salads.", 
                "None", 
                "salad",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("9. Sweet potato soup", 
                "LL, (G)", 
                7.9, 
                "Starters", 
                "Potato, cocos, creamy sauce, thyme bread", 
                "None", 
                "salad",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        
        //Pizzas tab contents
        dataSource.createMenuItem("1. Satay Chicken Pizza", 
                "L, (G)", 
                12.5, 
                "Pizzas", 
                "Vegetable oil, green onions, chopped, boneless chicken breast, small pita breads, Thai peanut sauce, provolone cheese", 
                "Medium", 
                "pizza_tab",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("2. Garlic Chicken Pizza", 
                "L", 
                9.9, 
                "Pizzas", 
                "Garlic, vegetable oil, mozzarella cheese, bread flour, boneless chicken breast, red onion, tomato, green pepper", 
                "Medium", 
                "pizza_tab",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("3. Spinach Alfredo Pizza", 
                "", 
                11.9, 
                "Pizzas", 
                "Spinach, mozzarella cheese, pizza crusts, Alfredo Sauce, olive oil, mushrooms, artichoke, grated Parmesan cheese", 
                "Medium", 
                "pizza_tab",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("4. Pepperoni Pizza", 
                "L, (G)", 
                11.5, 
                "Pizzas", 
                "Dry polenta, marinara sauce, pepperoni, tomato, oregano, olive oil, onion, green pepper, mozzarella cheese", 
                "Medium", 
                "pizza_tab",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("5. Pear & Cheese Pizza", 
                "L, (G)", 
                12.9, 
                "Pizzas", 
                "Pizza crust dough, walnuts, provolone cheese, gorgonzola cheese, Bosc pear, chopped fresh chives", 
                "Mild", 
                "pizza_tab",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("6. Opera", 
                "L, (G)", 
                8.9, 
                "Pizzas", 
                "Ham, tuna, cheese, tomato", 
                "None", 
                "pizza_tab",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("7. Napoli", 
                "(L)", 
                9.5, 
                "Pizzas", 
                "Fresh tomato, mozzarella cheese, minced meat, blue cheese", 
                "None", 
                "pizza_tab",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("8. Frutti Di Mare", 
                "L, (G)", 
                9.9, 
                "Pizzas", 
                "Shrimps, cheese, tuna, clam, tomato sauce", 
                "None", 
                "pizza_tab",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        dataSource.createMenuItem("9. Della Casa", 
                "L, (G)", 
                8.9, 
                "Pizzas", 
                "Tomato sauce, cheese, ham, onion, paprika", 
                "None", 
                "pizza_tab",
                "ingredient1;ingredient2", 
                "ingredient1;ingredient2;ingredient3;ingredient4", 
                "lactose-free", 
                "big;small",
                2);
        
        createTabHost(titles);
        ((Button)findViewById(R.id.myOrderButton)).setEnabled(true);
        ((Button)findViewById(R.id.checkoutButton)).setEnabled(true);
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
            intent.putExtra("order", (Serializable)userOrder);
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
        
        myorderpopup = new PopupWindow(popupView);
        
        myorderpopup.showAtLocation((View) findViewById(R.id.buttonLayout2), Gravity.LEFT | Gravity.TOP, 15, 60);
        
        myorderpopup.setFocusable(true);
        myorderpopup.update(570, 900);
    }
    
    public void closeMyOrderClicked(final View view) {
        // close search popup with cancel
        Log.d("MainMenuActivity","Close button clicked.");
        
        myorderpopup.dismiss();
    }
    
    public void clearMyOrderClicked(final View view) {
        // close search popup with ok
        Log.d("MainMenuActivity","Clear button clicked.");
        
        //myorderpopup.dismiss();
    }
    
    public void confirmMyOrderClicked(final View view) {
        // close search popup with ok
        Log.d("MainMenuActivity","Confirm button clicked.");
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = vi.inflate(R.layout.popup_order_confirmed, null);
        
        corfirmedpopup = new PopupWindow(popupView);
        
        corfirmedpopup.showAtLocation((View) findViewById(R.id.buttonLayout2), Gravity.LEFT | Gravity.TOP, 15, 60);
        
        corfirmedpopup.setFocusable(true);
        corfirmedpopup.update(570, 900);
    }
    
    public void callWaiterClicked(final View view) {
        // would send a message to a waiter to come by, not implemented here
        // feedback to tell that a request was sent
        Log.d("MainMenuActivity","Call waiter button clicked.");
    }
    
    
    
    public void closeOrderSent(final View view) {
        // close search popup with cancel
        Log.d("MainMenuActivity","OrderSent popup clicked.");
        
        corfirmedpopup.dismiss();
    }
}
