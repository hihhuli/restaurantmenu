package uni.oulu.hci.restaurantmenu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
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
