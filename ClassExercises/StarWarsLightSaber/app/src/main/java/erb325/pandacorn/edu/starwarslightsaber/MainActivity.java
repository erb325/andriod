package erb325.pandacorn.edu.starwarslightsaber;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private boolean saberOn = false;
    private MediaPlayer mpSaberOn;
    private MediaPlayer mpSaberOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mpSaberOn = MediaPlayer.create(this, R.raw.saber_on);
        mpSaberOff = MediaPlayer.create(this, R.raw.saber_off);
        mpSaberOff.setLooping(false);
        mpSaberOn.setLooping(false);
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

    public void playOnClick(View view){
        Button button = (Button) view;
        if (saberOn){
            mpSaberOff.start();
            button.setText("Use the Force");
            saberOn = false;
        } else {
            mpSaberOn.start();
            button.setText("Lose the Force");
            saberOn = true;
        }

    }
}
