package erb325.pandacorn.edu.starwarstextapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    private String completeScript;
    private String filteredScript;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.scriptTextView);
        InputStream is = getResources().openRawResource(R.raw.episode4);
        Scanner sc = new Scanner(is);
        StringBuilder sb = new StringBuilder();

        // This does not work if I use a String instead of StringBuilder. That is because in that
        // case the concatenation of each line results in the creation of a String object. This is
        // horrendously slow and causes a runtime error.
        final String ender = " \n\n";
        final String space = " ";
        while (sc.hasNextLine()) {
            String line = sc.nextLine() + space;
            sb.append(line);

            // This is a hack to deal with the fact that the text file containing the completeScript has
            // line breaks within paragraphs. I only want line breaks between paragraphs.L
            if (line.length() < 2) {
                sb.append(ender);
            }
        }
        completeScript = sb.toString();
        filteredScript = sb.toString();
        tv.setText(completeScript);

        final EditText editText = (EditText) findViewById(R.id.editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView userInputTextView, int actionId, KeyEvent event){
                String searchString = userInputTextView.getText().toString().trim();
                Scanner sc = new Scanner(filteredScript);
                StringBuffer filteredText = new StringBuffer();
                String space1 = "\n";
                String space2 = "\n\n";
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if (line.contains(searchString)) {
                        filteredText.append(line);
                        filteredText.append(line.length() < 2 ? space1 : space2);
                    }
                }

                TextView scriptTextView = (TextView) findViewById(R.id.scriptTextView);
                filteredScript = filteredText.toString();
                scriptTextView.setText(filteredScript);
                userInputTextView.setText("");

// Do not consume event. Returning true will cause keypad to remain
                return false;
            }
        });

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

    public void restoreScript(View view) {
        TextView tv = (TextView) findViewById(R.id.scriptTextView);
        tv.setText(completeScript);
    }
}
