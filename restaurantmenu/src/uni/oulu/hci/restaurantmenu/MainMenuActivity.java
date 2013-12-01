package uni.oulu.hci.restaurantmenu;

import android.os.Bundle;
import android.app.Activity;
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
