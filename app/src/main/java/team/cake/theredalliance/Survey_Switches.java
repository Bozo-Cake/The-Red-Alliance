package team.cake.theredalliance;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;
import java.util.Random;

public class Survey_Switches extends Field{
    String _data;
    int _id;
    public Survey_Switches(Map<String, String> map) {
        /*
        List desired parameters to be required or optional to be included in config.csv here:
        Excludes parameters handled by parent class [Field]: name, type
        -Number of Switches     (required)
        -List<String> values    (required)
        -List<bool> defaults    (optional)
        */
        //ToDo: Extract Switch data here.

        //pass on remaining items to parent class.
        super(map);
    }
    public void makeView(ViewGroup layout) {
        Random rand = new Random(); //instance of random class
        int upperbound = 25;
        //generate random values from 0-24
        _id = rand.nextInt(upperbound);
        TextView textView = new TextView(_activity.get());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        textView.setText(_name);
        layout.addView(textView);
        EditText editText = new EditText(_activity.get());
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.MarginLayoutParams.WRAP_CONTENT );
        editText.setId(_id);
        editText.setHint("name");
        editText.setLayoutParams(editTextParams);

        layout.addView(editText);
    }
    @Override
    public String saveViewData(){
        EditText textView = _activity.get().findViewById(_id);
        return textView.getText().toString();
    }
}
