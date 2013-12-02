package uni.oulu.hci.restaurantmenu;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class FirstPageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first_page, menu);
        return true;
    }
    
    public void startMenuClicked(final View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivityForResult(intent, 0);
    }
    
    public void selectEnglishClicked(final View view) {
        // change text on first page to english
        // change some language selection variable accordingly
        // language selection variable sent as extra in intent to MainMenuActivity
    }
    
    public void selectFinnishClicked(final View view) {
        // change text on first page to finnish
        // change some language selection variable accordingly
        // language selection variable sent as extra in intent to MainMenuActivity
    }
}
