package uni.oulu.hci.restaurantmenu;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

public class TabMenuFragment extends Fragment {
    
	private View fragmentView;
    private PopupWindow searchPopup;
    private List<MenuItem> menuItems;
    private int expandedIndex;
    private LinearLayout scrollViewLayout;
    private ScrollView scrollView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	this.fragmentView = inflater.inflate(R.layout.activity_menutab, container, false);
        
        this.menuItems = (List<MenuItem>) getArguments().getSerializable("data");
        Log.d("tab initialized", "" + this.menuItems.size());
        this.expandedIndex = 0;
        this.scrollViewLayout = (LinearLayout) this.fragmentView.findViewById(R.id.scrollViewLayout);
        this.scrollView = (ScrollView) this.fragmentView.findViewById(R.id.scrollView);
        this.searchPopup = new PopupWindow(inflater.inflate(R.layout.popup_filter_by, null));
        
        this.scrollView.setSmoothScrollingEnabled(true);
        populateScrollView();
        
        return this.fragmentView;
    }
    
    public View getExpandedView() {
    	return this.scrollViewLayout.getChildAt(this.expandedIndex);
    }
    
    private void populateScrollView() {
    	ViewGroup layout;
    	
    	if (this.menuItems.size() > 0) {
    		layout = getExpandedItemLayout(0, ((MainMenuActivity)getActivity()).getUserOrder());
        	insertToScrollView(layout, this.scrollViewLayout, 0);
    	}
        for (int i = 1; i < this.menuItems.size(); i++) {
        	layout = getItemLayout(i);
            insertToScrollView(layout, this.scrollViewLayout, i);
        }
    }
    
    private void insertToScrollView(View view, LinearLayout scrollViewLayout, int index) {
    	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 10);
        view.setLayoutParams(params);
    	scrollViewLayout.addView(view, index);
    }
    
    public void setItemOrderViews(ViewGroup layout, int count) {
        Button removeButton = ((Button)layout.findViewById(R.id.removeFromOrderButton));
        TextView inOrder = ((TextView)layout.findViewById(R.id.inOrderView));
    	if (count > 0) {
        	removeButton.setVisibility(View.VISIBLE);
            inOrder.setVisibility(View.VISIBLE);
            inOrder.setText(count + " in order");
        } else {
        	removeButton.setVisibility(View.INVISIBLE);
            inOrder.setVisibility(View.INVISIBLE);
        }
    }
    
    private void setItemProperties(ViewGroup layout, MenuItem menuItem, int count) {
    	((TextView)layout.findViewById(R.id.itemDescriptionView)).setText(menuItem.getDescription());
		((TextView)layout.findViewById(R.id.itemSpicinessView)).setText("Spiciness: " + menuItem.getSpiciness());
		((TextView)layout.findViewById(R.id.itemDietsView)).setText("Diets: " + menuItem.getDiets());
        ((TextView)layout.findViewById(R.id.itemTitleView)).setText(menuItem.getTitle());
        ((TextView)layout.findViewById(R.id.itemPriceView)).setText(Double.toString(menuItem.getPrice()) + " €");
        int imgId = getResources().getIdentifier(menuItem.getImage(), "drawable", getActivity().getPackageName());
        ((ImageView)layout.findViewById(R.id.itemImageView)).setImageResource(imgId);
        setItemOrderViews(layout, count);
    }
    
    private GridLayout getExpandedItemLayout(int index, UserOrder userOrder) {
    	MenuItem menuItem = this.menuItems.get(index);
    	GridLayout layout = (GridLayout)getActivity().getLayoutInflater().inflate(R.layout.largeitem, null);
		setItemProperties(layout, menuItem, userOrder.getCount(menuItem.getTitle()));

		return layout;
    }
    
    private GridLayout getFurtherExpandedItemLayout(int index, UserOrder userOrder) {
    	MenuItem menuItem = this.menuItems.get(index);
    	GridLayout layout = (GridLayout)getActivity().getLayoutInflater().inflate(R.layout.largestitem, null);
		if (userOrder != null) {
			setItemProperties(layout, menuItem, userOrder.getCount(menuItem.getTitle()));
		} else {
			setItemProperties(layout, menuItem, 0);
		}
		
		return layout;
    }
    
    private LinearLayout getItemLayout(int index) {
    	MenuItem menuItem = this.menuItems.get(index);
    	LinearLayout layout = (LinearLayout)getActivity().getLayoutInflater().inflate(R.layout.smallitem, null);
    	
        ((TextView)layout.findViewById(R.id.itemPriceView)).setText(Double.toString(menuItem.getPrice()) + " €");
        ((TextView)layout.findViewById(R.id.itemDietsView)).setText(menuItem.getDiets());
        ((TextView)layout.findViewById(R.id.itemTitleView)).setText(menuItem.getTitle());
        
        return layout;
    }

    public void searchClicked(final View view) {
        Log.d("TabMenuActivity","Search button clicked.");

        this.searchPopup.showAtLocation((View) this.fragmentView.findViewById(R.id.menutab), Gravity.LEFT | Gravity.TOP, 15, 60);
        this.searchPopup.setFocusable(true);
        this.searchPopup.update(570, 900);
    }
    
    public void cancelSearchClicked(final View view) {
        // close search popup with cancel
        Log.d("TabMenuActivity","Cancel button clicked.");
        
        this.searchPopup.dismiss();
    }
    
    public void okSearchClicked(final View view) {
        // close search popup with ok
        Log.d("TabMenuActivity","Ok button clicked.");
        
        this.searchPopup.dismiss();
    }
    
    public void clearSearchClicked(final View view) {
        // close search popup with ok
        Log.d("TabMenuActivity","Clear button clicked.");
    }
    
    public void itemClicked(final View view, UserOrder userOrder) {
    	int diff = getExpandedView().getHeight() + view.getHeight() + 10;
    	int index = scrollViewLayout.indexOfChild(view);
    	
		this.scrollView.smoothScrollTo(0, this.scrollViewLayout.getChildAt(index).getBottom() - diff);
    	this.scrollViewLayout.removeViewAt(this.expandedIndex);
		insertToScrollView(getItemLayout(this.expandedIndex), this.scrollViewLayout, this.expandedIndex);
		this.expandedIndex = index;
		this.scrollViewLayout.removeViewAt(index);
		insertToScrollView(getExpandedItemLayout(index, userOrder), this.scrollViewLayout, index);
    }
    
    public void personalChoicesOpened(final View view, UserOrder userOrder) {
        this.scrollViewLayout.removeViewAt(this.expandedIndex);
        GridLayout layout = getFurtherExpandedItemLayout(this.expandedIndex, userOrder);
        insertToScrollView(layout, this.scrollViewLayout, this.expandedIndex);
        this.scrollView.smoothScrollTo(0, ((GridLayout)view.getParent().getParent()).getTop() - 40);
    }
    
    public void personalChoicesClosed(final View view, UserOrder userOrder) {
        this.scrollViewLayout.removeViewAt(this.expandedIndex);
        insertToScrollView(getExpandedItemLayout(this.expandedIndex, userOrder), scrollViewLayout, this.expandedIndex);
    }
    
    public void addToOrderClicked(final View view, UserOrder userOrder) {
        Log.d("TabMenuActivity","Add to order clicked.");
        LinearLayout layout = (LinearLayout) view.getParent();
        MenuItem menuItem = menuItems.get(expandedIndex);
        userOrder.addtoOrder(menuItem);
        setItemOrderViews(layout, userOrder.getCount(menuItem.getTitle()));
        ((Button)getActivity().findViewById(R.id.myOrderButton)).setEnabled(true);
    }
    
    public void removeFromOrderClicked(final View view, UserOrder userOrder) {
        Log.d("TabMenuActivity","Add to order clicked.");
        LinearLayout layout = (LinearLayout) view.getParent();
        MenuItem menuItem = menuItems.get(expandedIndex);
        userOrder.removeFromOrder(menuItem.getTitle());
        setItemOrderViews(layout, userOrder.getCount(menuItem.getTitle()));
        if (userOrder.isEmpty()) {
        	((Button)getActivity().findViewById(R.id.myOrderButton)).setEnabled(false);
        }
    }
    
}
