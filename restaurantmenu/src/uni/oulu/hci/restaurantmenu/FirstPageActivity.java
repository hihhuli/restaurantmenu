package uni.oulu.hci.restaurantmenu;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class FirstPageActivity extends Activity {
    
    private HashMap<String, List<MenuItem>> data;
    private UserOrder userOrder;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        this.userOrder = new UserOrder();
        MenuDataSource dataSource = new MenuDataSource(this);
        dataSource.open();
        dataSource.deleteAllMenuItems();
        
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
        this.data = dataSource.getMenuItemMap();
        dataSource.close();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first_page, menu);
        return true;
    }
    
    public void startMenuClicked(final View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.putExtra("data", (Serializable)this.data);
        intent.putExtra("order", (Serializable)this.userOrder);
        startActivityForResult(intent, 0);
    }
    
    public void selectEnglishClicked(final View view) {
        // change text on first page to english
        // change some language selection variable accordingly
        // language selection variable sent as extra in intent to MainMenuActivity
    	Log.d("FirstPageActivity","English button clicked.");
    }
    
    public void selectFinnishClicked(final View view) {
        // change text on first page to finnish
        // change some language selection variable accordingly
        // language selection variable sent as extra in intent to MainMenuActivity
    	Log.d("FirstPageActivity","Finnish button clicked.");
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      this.data = (HashMap<String, List<MenuItem>>)data.getSerializableExtra("data");
      this.userOrder = (UserOrder)data.getSerializableExtra("order");
    }
}
