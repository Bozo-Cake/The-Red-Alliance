package team.cake.theredalliance;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;
import java.util.Random;

public class Survey_Text_Box extends Field implements Askable {
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
    public void makeView(ViewGroup layout) {
        Random rand = new Random(); //instance of random class
        int upperbound = 2500;
        _id = rand.nextInt(upperbound);
        TextView textView = new TextView(_activity.get());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setText(_name);
        layout.addView(textView);
        EditText editText = new EditText(_activity.get());
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.MarginLayoutParams.WRAP_CONTENT );
        editText.setId(_id);
        if(_data != null){
            editText.setText(_data);
        }
        editText.setLayoutParams(editTextParams);

        layout.addView(editText);
    }
    @Override
    public String saveViewData(){
        EditText textView = _activity.get().findViewById(_id);
        return textView.getText().toString();
    }
}
