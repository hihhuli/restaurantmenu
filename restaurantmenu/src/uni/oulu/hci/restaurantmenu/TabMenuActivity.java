package uni.oulu.hci.restaurantmenu;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.GridLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class TabMenuActivity extends Activity implements ScrollViewListener {
    
    private PopupWindow searchpopup;
    private List<MenuItem> menuItems;
    private ViewGroup expandedItem;
    
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menutab);
        
        this.menuItems = (List<MenuItem>) getIntent().getSerializableExtra("data");
        this.searchpopup = null;
        this.expandedItem = null;
        
        ((ObservableScrollView) findViewById(R.id.scrollView)).setScrollViewListener(this);
        populateScrollView();
    }
    
    private void populateScrollView() {
    	ViewGroup item;
    	LinearLayout scrollViewLayout = (LinearLayout)findViewById(R.id.scrollViewLayout);
        
        for (int i = 0; i < this.menuItems.size(); i++) {
        	MenuItem menuItem = this.menuItems.get(i);
        	if (i == 1) {
        		item = (GridLayout)getLayoutInflater().inflate(R.layout.largeitem, null);
        		this.expandedItem = item;
        		((TextView)item.findViewById(R.id.itemDescriptionView)).setText(menuItem.getDescription());
        		((TextView)item.findViewById(R.id.itemSpicinessView)).setText("Spiciness: " + menuItem.getSpiciness());
        		((TextView)item.findViewById(R.id.itemDietsView)).setText("Diets: " + menuItem.getDiets());
        	} else {
        		item = (LinearLayout)getLayoutInflater().inflate(R.layout.smallitem, null);
                ((TextView)item.findViewById(R.id.itemPriceView)).setText(Double.toString(menuItem.getPrice()));
                ((TextView)item.findViewById(R.id.itemDietsView)).setText(menuItem.getDiets());
        	}
            ((TextView)item.findViewById(R.id.itemTitleView)).setText(menuItem.getTitle());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 10);
            item.setLayoutParams(params);
            scrollViewLayout.addView(item);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first_page, menu);
        return true;
    }
    
    public void callWaiterClicked(final View view) {
        // would send a message to a waiter to come by, not implemented here
        // feedback to tell that a request was sent
        Log.d("TabMenuActivity","Call waiter button clicked.");
    }
    
    public void searchClicked(final View view) {
        // launch search popup
        Log.d("TabMenuActivity","Search button clicked.");
        
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = vi.inflate(R.layout.popup_filter_by, null);
        
        this.searchpopup = new PopupWindow(popupView);
        this.searchpopup.showAtLocation((View) findViewById(R.id.menutab), Gravity.LEFT | Gravity.TOP, 15, 60);
        this.searchpopup.setFocusable(true);
        this.searchpopup.update(570, 900);
    }
    
    public void cancelSearchClicked(final View view) {
        // close search popup with cancel
        Log.d("TabMenuActivity","Cancel button clicked.");
        
        this.searchpopup.dismiss();
    }
    
    public void okSearchClicked(final View view) {
        // close search popup with ok
        Log.d("TabMenuActivity","Ok button clicked.");
        
        this.searchpopup.dismiss();
    }
    
    public void clearSearchClicked(final View view) {
        // close search popup with ok
        Log.d("TabMenuActivity","Clear button clicked.");
    }

	@Override
	public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
		Log.d("onScroll", x + " " + y + " " + oldx + " " + oldy);
	}
}
