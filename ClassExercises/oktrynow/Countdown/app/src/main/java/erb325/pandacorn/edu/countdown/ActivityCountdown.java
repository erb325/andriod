package erb325.pandacorn.edu.countdown;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.GregorianCalendar;

/**
 * Created by erb39405 on 10/1/2015.
 */
public class ActivityCountdown extends AppCompatActivity {

    public static final String RESULT_MESSAGE = "result_message";
    private int daysRemaining;
    private int hoursRemaining;
    private int minutesRemining;
    private int secondsRemaining;
    private static final DecimalFormat f = new DecimalFormat("00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

       // ActionBar actionBar = getSupportActionBar();
       // actionBar.setDisplayHomeAsUpEnabled(true);

        GregorianCalendar calendar= new GregorianCalendar();
        secondsRemaining = 60- calendar.get(GregorianCalendar.SECOND);
        minutesRemining = 60 - calendar.get(GregorianCalendar.MINUTE)-1;
        hoursRemaining= 24 - calendar.get(GregorianCalendar.HOUR_OF_DAY)-1;

        Bundle extras= getIntent().getExtras();
        int month = extras.getInt(MainActivity.MONTH);
        int day = extras.getInt(MainActivity.DAY);
        int year = extras.getInt(MainActivity.YEAR);

        Date eventDate = new Date(month, day, year);
        Date currentDate = new Date();

        daysRemaining=0;
        while (currentDate.before(eventDate)){
            daysRemaining++;
            currentDate.tick();
        }

        daysRemaining--;

        final TextView daysView = (TextView) findViewById(R.id.daysRemainingView);
        final TextView hrsView = (TextView) findViewById(R.id.hrsRemainingView);
        final TextView minsView = (TextView) findViewById(R.id.minsRemainingView);
        final TextView secsView = (TextView) findViewById(R.id.secsRemainingView);

        class MyTimer extends CountDownTimer{

            private static final int MILLISECS_PER_HOUR = 3600 * 1000;

            public MyTimer (long millisecondsUntilFinished){
                super(hoursRemaining * MILLISECS_PER_HOUR, 1000);
            }

            @Override
            public void onTick(long millisUntilFinished) {
                secondsRemaining--;
                if (secondsRemaining <0 ){
                    secondsRemaining = 59;
                    minutesRemining--;
                    if (minutesRemining <0){
                        minutesRemining = 59;
                        hoursRemaining--;
                    }
                }
                daysView.setText(f.format(daysRemaining));
                hrsView.setText(f.format(hoursRemaining));
                minsView.setText(f.format(minutesRemining));
                secsView.setText(f.format(secondsRemaining));
            }

            @Override
            public void onFinish() {

            }
        }
        MyTimer timer = new MyTimer(1000);
        timer.start();
//        daysView.setText(f.format(daysRemaining));
//        hrsView.setText(f.format(hoursRemaining));
//        minsView.setText(f.format(minutesRemining));
//        secsView.setText(f.format(secondsRemaining));

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

        if(id== android.R.id.home){
            getIntent().putExtra(RESULT_MESSAGE, daysRemaining +" days remaining");
            setResult(RESULT_OK, getIntent());
            finish();
        }
        if(id == android.R.id.home){
            Toast.makeText(this, "Finishing Countdown", Toast.LENGTH_LONG).show();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void buttonClickHandler(View view){
        getIntent().putExtra(RESULT_MESSAGE, daysRemaining +" days remaining");
        setResult(RESULT_OK, getIntent());
        finish();
    }

}
