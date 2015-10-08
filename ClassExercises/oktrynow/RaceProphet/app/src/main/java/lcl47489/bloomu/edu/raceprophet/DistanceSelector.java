package lcl47489.bloomu.edu.raceprophet;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by erb39405 on 9/24/2015.
 */
public class DistanceSelector extends LinearLayout{

    private static ArrayList<String> list = new ArrayList<>();
    static {
        for (int i = 0; i <30; i ++){
            for (int j = 0; j <10; j++){
                list.add(i+ "."+j);
            }
        }
    }

    public DistanceSelector(Context context) {
        super(context, null);
    }

    public DistanceSelector(Context context, AttributeSet attrs){
        super(context, attrs);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.distance_selector, this);

        TextView textView = (TextView) findViewById(R.id.distanceTextView);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DistanceSelector);
        String string = a.getString(R.styleable.DistanceSelector_distanceLabelText);
        textView.setText(string);

        NumberPicker numberPicker = (NumberPicker)findViewById(R.id.distanceNumberPicker);
        String[] arr = new String[list.size()];
        for (int i = 0; i < arr.length; i++){
            arr[i] = list.get(i);
        }
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(arr.length-1);
        numberPicker.setDisplayedValues(arr);
        numberPicker.setValue(40);

    }

}
