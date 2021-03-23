package team.cake.theredalliance;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;
import java.util.Random;

public class Survey_Counter extends Field implements Askable {
    String _data;
    int _id;
    public Survey_Counter(Map<String, String> map) {
        /*
        List desired parameters to be required or optional to be included in config.csv here:
        Excludes parameters handled by parent class [Field]: name, type
        -Int data (required)
        -Count by (optional if default to 1) (extra optional: multiple count by buttons - +1, +5, +10)
        -Count when long press - timer while holding down button to time events (optional)
        */

        //ToDo: Extract Counter data here.

        //pass on remaining items to parent class.
        super(map);
    }
    @Override
    public Integer makeView(LinearLayout layout) {
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
        //TODO make sure each view is not displaying over top of another view.
        EditText editText = new EditText(_activity.get());
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.MarginLayoutParams.WRAP_CONTENT );
        editText.setId(_id);
        editText.setHint("name");
        editText.setLayoutParams(editTextParams);

        layout.addView(editText);
        return _id;
    }
    @Override
    public void saveViewData(){
        EditText textView = _activity.get().findViewById(_id);
        _data = textView.getText().toString();
    }
    @Override
    public void loadViewData(String data){
        EditText textView = _activity.get().findViewById(_id);
        textView.setText(_data + _data);
    }
}
