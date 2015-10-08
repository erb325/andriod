package erb325.pandacorn.edu.countdown;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;


public class MainActivity extends AppCompatActivity {


    public static final String MONTH = "month";
    public static final String DAY = "day";
    public static final String YEAR = "year";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startCountdown(View view) {
        DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker);

        Intent intent = new Intent(this, ActivityCountdown.class);
        intent.putExtra(MONTH, datePicker.getMonth()+1);
        intent.putExtra(DAY, datePicker.getDayOfMonth());
        intent.putExtra(YEAR, datePicker.getYear());
        startActivity(intent);
    }

    public void websiteClickedHandler(View view){
        Uri webpage = Uri.parse("http://starwarsuncut.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }
}
