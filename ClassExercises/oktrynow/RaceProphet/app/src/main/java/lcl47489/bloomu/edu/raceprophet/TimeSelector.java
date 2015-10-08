package lcl47489.bloomu.edu.raceprophet;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erb39405 on 9/24/2015.
 */
public class TimeSelector extends LinearLayout {
    public TimeSelector(Context context) {
        super(context, null);
    }

    public TimeSelector(Context context, AttributeSet attrs){
        super(context, attrs);
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.timer_selector, this);

        // Set the test view with text specified by the given attribute set
        TextView textView = (TextView) findViewById(R.id.timeTextView);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TimeSelector);
        int n = a.getIndexCount();
        for (int i = 0; i <n; i++){
            int attr = a.getIndex(i);
            if(attr==R.styleable.TimeSelector_timeLabelText){
                String s = a.getString(attr);
                textView.setText(s);
            }
        }
        a.recycle();

        // Populate the spinner and seconds wit 00, 01,02 ...59
        Spinner minSpinner = (Spinner) findViewById(R.id.minutesSpinner);
        Spinner secSpinner = (Spinner) findViewById(R.id.secondsSpinner);
        List<String> list = new ArrayList<>();
        for (int i= 0; i<60; i++){
            if(i<10){
                list.add("0"+i);
            } else {
                list.add(""+i);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
        minSpinner.setAdapter(adapter);
        secSpinner.setAdapter(adapter);
    }
}
