package erb325.pandacorn.lifecycledemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity { //ActivityBar is deprecated

    /**
     * Bundle is a package that save the application state to load
     * next time it is open.
     *
     */
    private static String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "Created!");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "Started!");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(LOG_TAG, "...Resumed!");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(LOG_TAG, "......paused!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(LOG_TAG, "...Stopped!");
    }

    /**
     * inflates the action bar menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d(LOG_TAG, "options menu created");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(LOG_TAG, "-options item selected");

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
}
