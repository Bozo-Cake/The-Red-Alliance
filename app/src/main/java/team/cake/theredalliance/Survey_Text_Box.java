package team.cake.theredalliance;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;
import java.util.Random;

public class Survey_Text_Box extends Field implements Askable {
    String _data;
    int _id;
    public Survey_Text_Box(Map<String,String> map) {
        /*
        List desired parameters to be required or optional to be included in config.csv here:
        Excludes parameters handled by parent class [Field]: name, type
        -Max Length (optional)
        */
        //ToDo: Extract TextBox data here.

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
                LinearLayout.LayoutParams.MATCH_PARENT);
        editText.setId(_id);

        TextInputLayout textInputLayout = new TextInputLayout(_activity.get());
        LinearLayout.LayoutParams textInputLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        textInputLayout.setLayoutParams(textInputLayoutParams);
        textInputLayout.addView(editText, editTextParams);
        textInputLayout.setHint("hint");

        layout.addView(textInputLayout);
        return _id;
    }
    @Override
    public void saveViewData(){
        EditText textView = _activity.get().findViewById(_id);
        _data = textView.getText().toString();
    }
    public void loadViewData(String data){
        EditText textView = _activity.get().findViewById(_id);
        textView.setText(_data + _data);
    }
}
