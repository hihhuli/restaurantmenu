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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class TabMenuActivity extends Activity {
    
    private PopupWindow searchpopup;
    private List<MenuItem> menuItems;
    
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menutab);
        menuItems = (List<MenuItem>) getIntent().getSerializableExtra("data");
        populateScrollView();
    }
    
    private void populateScrollView() {
        LinearLayout scrollViewLayout = (LinearLayout)findViewById(R.id.scrollViewLayout);
        
        for(MenuItem menuItem : menuItems) {
            LinearLayout item = (LinearLayout)getLayoutInflater().inflate(R.layout.smallitem, null);
            ((TextView)item.findViewById(R.id.itemTitleView)).setText(menuItem.getTitle());
            ((TextView)item.findViewById(R.id.itemDietsView)).setText(menuItem.getDiets());
            ((TextView)item.findViewById(R.id.itemPriceView)).setText(Double.toString(menuItem.getPrice()));
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
        
        searchpopup = new PopupWindow(popupView);
        searchpopup.showAtLocation((View) findViewById(R.id.menutab), Gravity.LEFT | Gravity.TOP, 15, 60);
        searchpopup.setFocusable(true);
        searchpopup.update(570, 900);
    }
    
    public void cancelSearchClicked(final View view) {
        // close search popup with cancel
        Log.d("TabMenuActivity","Cancel button clicked.");
        
        searchpopup.dismiss();
    }
    
    public void okSearchClicked(final View view) {
        // close search popup with ok
        Log.d("TabMenuActivity","Ok button clicked.");
        
        searchpopup.dismiss();
    }
    
    public void clearSearchClicked(final View view) {
        // close search popup with ok
        Log.d("TabMenuActivity","Clear button clicked.");
        
        
    }
}
