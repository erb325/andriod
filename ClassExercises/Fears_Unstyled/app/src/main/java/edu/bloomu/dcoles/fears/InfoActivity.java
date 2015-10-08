package edu.bloomu.dcoles.fears;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Drue Coles
 *
 * This activity receives an intent and uses it to initialize the text
 * in two text views.
 */
public class InfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Bundle extra = getIntent().getExtras();
        String legend = extra.getString(MainActivity.LEGEND_KEY);
        String text = extra.getString(MainActivity.MESSAGE_KEY);


        TextView legendTextView = (TextView) findViewById(R.id.legendTextView);
        TextView messageTextView = (TextView) findViewById(R.id.messageTextView);
        legendTextView.setText(legend);
        messageTextView.setText(text);
    }
}
