package uni.oulu.hci.restaurantmenu;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.GridLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

public class TabMenuActivity extends Activity {
    
    private PopupWindow searchpopup;
    private List<MenuItem> menuItems;
    private int expandedIndex;
    
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menutab);
        
        this.menuItems = (List<MenuItem>) getIntent().getSerializableExtra("data");
        this.searchpopup = null;
        this.expandedIndex = 0;

        populateScrollView();
    }
    
    private void populateScrollView() {
    	ViewGroup layout;
    	
    	layout = getExpandedItemLayout(0);
    	insertToScrollView(layout, ((LinearLayout)findViewById(R.id.scrollViewLayout)), 0);
        for (int i = 1; i < this.menuItems.size(); i++) {
        	layout = getItemLayout(i);
            insertToScrollView(layout, ((LinearLayout)findViewById(R.id.scrollViewLayout)), i);
        }
    }
    
    private void insertToScrollView(View view, LinearLayout scrollViewLayout, int index) {
    	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 10);
        view.setLayoutParams(params);
    	scrollViewLayout.addView(view, index);
    }
    
    private GridLayout getExpandedItemLayout(int index) {
    	MenuItem menuItem = this.menuItems.get(index);
    	GridLayout layout = (GridLayout)getLayoutInflater().inflate(R.layout.largeitem, null);
		
    	((TextView)layout.findViewById(R.id.itemDescriptionView)).setText(menuItem.getDescription());
		((TextView)layout.findViewById(R.id.itemSpicinessView)).setText("Spiciness: " + menuItem.getSpiciness());
		((TextView)layout.findViewById(R.id.itemDietsView)).setText("Diets: " + menuItem.getDiets());
        ((TextView)layout.findViewById(R.id.itemTitleView)).setText(menuItem.getTitle());
		
		return layout;
    }
    
    private GridLayout getFurtherExpandedItemLayout(int index) {
    	MenuItem menuItem = this.menuItems.get(index);
    	GridLayout layout = (GridLayout)getLayoutInflater().inflate(R.layout.largestitem, null);
		
    	((TextView)layout.findViewById(R.id.itemDescriptionView)).setText(menuItem.getDescription());
		((TextView)layout.findViewById(R.id.itemSpicinessView)).setText("Spiciness: " + menuItem.getSpiciness());
		((TextView)layout.findViewById(R.id.itemDietsView)).setText("Diets: " + menuItem.getDiets());
        ((TextView)layout.findViewById(R.id.itemTitleView)).setText(menuItem.getTitle());
		
		return layout;
    }
    
    public void addToOrderClicked(final View view) {
        // close search popup with ok
        Log.d("TabMenuActivity","Add to order clicked.");
        
    }
    
    private LinearLayout getItemLayout(int index) {
    	MenuItem menuItem = this.menuItems.get(index);
    	LinearLayout layout = (LinearLayout)getLayoutInflater().inflate(R.layout.smallitem, null);
    	
        ((TextView)layout.findViewById(R.id.itemPriceView)).setText(Double.toString(menuItem.getPrice()));
        ((TextView)layout.findViewById(R.id.itemDietsView)).setText(menuItem.getDiets());
        ((TextView)layout.findViewById(R.id.itemTitleView)).setText(menuItem.getTitle());
        
        return layout;
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
    
    public void itemClicked(final View view) {
    	ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
    	LinearLayout scrollViewLayout = (LinearLayout) scrollView.getChildAt(0);
    	int diff = scrollViewLayout.getChildAt(this.expandedIndex).getHeight() + view.getHeight() + 10;
    	int index = scrollViewLayout.indexOfChild(view);
    	
    	scrollView.setSmoothScrollingEnabled(true);
		scrollView.smoothScrollTo(0, scrollViewLayout.getChildAt(index).getBottom() - diff);
    	scrollViewLayout.removeViewAt(this.expandedIndex);
		insertToScrollView(getItemLayout(this.expandedIndex), scrollViewLayout, this.expandedIndex);
		this.expandedIndex = index;
		scrollViewLayout.removeViewAt(index);
		insertToScrollView(getExpandedItemLayout(index), scrollViewLayout, index);
    }
    
    public void personalChoicesOpened(final View view) {
        LinearLayout scrollViewLayout = (LinearLayout)findViewById(R.id.scrollViewLayout);
        scrollViewLayout.removeViewAt(this.expandedIndex);
        insertToScrollView(getFurtherExpandedItemLayout(this.expandedIndex), scrollViewLayout, this.expandedIndex);
    }
    
    public void personalChoicesClosed(final View view) {
        LinearLayout scrollViewLayout = (LinearLayout)findViewById(R.id.scrollViewLayout);
        scrollViewLayout.removeViewAt(this.expandedIndex);
        insertToScrollView(getExpandedItemLayout(this.expandedIndex), scrollViewLayout, this.expandedIndex);
    }
}
