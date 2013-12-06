package uni.oulu.hci.restaurantmenu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;



public class PaymentOptionsActivity extends Activity {
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);
        
        
        
        
        
        //((Button)findViewById(R.id.myOrderButton)).setEnabled(true);
        //((Button)findViewById(R.id.checkoutButton)).setEnabled(true);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
    }
    
    public void backClicked(final View view) {
        setResult(RESULT_OK, this.getIntent());
        finish();
    }
    
    public void tabletPayClicked(final View view) {
        // would send a message to a waiter to come by, not implemented here
        // feedback to tell that a request was sent
        Log.d("PaymentOptionsActivity","Tablet button clicked.");
    }
    
    public void phonePayClicked(final View view) {
        // would send a message to a waiter to come by, not implemented here
        // feedback to tell that a request was sent
        Log.d("PaymentOptionsActivity","Phone button clicked.");
    }
    
    public void callWaiterClicked(final View view) {
        // would send a message to a waiter to come by, not implemented here
        // feedback to tell that a request was sent
        Log.d("PaymentOptionsActivity","Call waiter button clicked.");
    }
}