package edu.bloomu.dcoles.fears;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Drue Coles
 *
 * Responds to either of two button clicks by launching a second activity to display text depending
 * on which of three checkboxes (indicating personal fears) are selected.
 */
public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_KEY = "MESSAGE_KEY";
    public static final String LEGEND_KEY = "LEGEND_KEY";

    // ArrayMap (from Android API) is more memory efficient than HashMap. But we must edit the build
    // script for the app: set minimum API level to 19.
    private static ArrayMap<String, String> adviceMap = new ArrayMap<>();
    private static ArrayMap<String, String> feelBetterMap = new ArrayMap<>();


    static {
        adviceMap.put("tornado", "Stay out of Kansas.\n");
        adviceMap.put("glass", "Wear shoes outside.\n");
        adviceMap.put("lyme", "Remove ticks promptly using tweezers.\n");
        feelBetterMap.put("tornado", "Cows have been sucked up into tornados and survived.\n");
        feelBetterMap.put("glass", "Cuts can easily be stitched up and then you're as good as new.\n");
        feelBetterMap.put("lyme", "Deer ticks are not active when snow is on the ground.\n");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method handles clicking of both the advice button and the feel better button. An intent
     * is launched with the text to be displayed depending on which button was clicked and which
     * checkboxes have been selected.
     * @param view the button that was clicked
     */
    public void buttonHandler(View view) {
        Intent intent = new Intent(this, InfoActivity.class);
        switch (view.getId()) {
            case R.id.adviceButton:
                intent.putExtra(LEGEND_KEY, "ADVICE");
                intent.putExtra(MESSAGE_KEY, getText(adviceMap));
                break;
            case R.id.feelBetterButton:
                intent.putExtra(LEGEND_KEY, "RELAX");
                intent.putExtra(MESSAGE_KEY, getText(feelBetterMap));
                break;
            default: // unreachable
        }
        startActivity(intent);
    }

    /**
     * @param map
     * @return the text associated to the subset of selected checkboxes for the given map
     */
    private String getText(ArrayMap map) {
        String text = "";
        if (isChecked(R.id.tornadoCheckBox)) {
            text += map.get("tornado");
        }
        if (isChecked(R.id.sharpGlassCheckBox)) {
            text += map.get("glass");
        }
        if (isChecked(R.id.lymeCheckBox)) {
            text += map.get("lyme");
        }
        return text.trim();
    }

    /**
     * @param id
     * @return true if the checkbox with given id is selected
     */
    private boolean isChecked(int id) {
        CheckBox c = (CheckBox) findViewById(id);
        return c.isChecked();
    }
}
