package team.cake.theredalliance;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;
import java.util.Random;

public class Survey_Checkboxes extends Field{
    String _questions[];
    public Survey_Checkboxes(Map<String, String> map) {
        /*
        List desired parameters to be required or optional to be included in config.csv here:
        Excludes parameters handled by parent class [Field]: name, type
        -List<String> selections    (required)
        -Number of checkboxes       (required)
        -default selections?        (optional)
        */
        super(map);
    }
    @Override
    public void makeView(ViewGroup parentView) {
        //Get the common stuff out of the way
        LinearLayout view = generateContainer();
        //Loop through all Key:Value pairs where 0 <= Key <= N and add another CheckBox View
        for (int i = 0; _map.containsKey(String.valueOf(i)); i++) {
            //New CheckBox; set parameters
            CheckBox box = new CheckBox(_activity.get());
            box.setTag(_map.get(String.valueOf(i)));
            box.setText(_map.get(String.valueOf(i)));
            box.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            //Add to Question Root View (Currently LinearLayout)
            view.addView(box);
            //Can you add it to the Root Container passed in to makeView(thisParameter)?
            parentView.addView(view);
        }
    }

//    @Override
//    public void saveViewData(){
//        EditText textView = _activity.get().findViewById(_id);
//        _data = textView.getText().toString();
//    }
//    @Override
//    public void loadViewData(String data){
//        EditText textView = _activity.get().findViewById(_id);
//        textView.setText(_data + _data);
//    }
}
