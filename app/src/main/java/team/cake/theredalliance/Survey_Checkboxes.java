package team.cake.theredalliance;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.Map;
import java.util.Random;

public class Survey_Checkboxes extends Field {
    private final String TAG = "Survey_Checkboxes";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Survey_Checkboxes(Map<String, String> map) {
        super(map);
    }

    @Override
    public void makeView(ViewGroup parent) {
        //Random rand = new Random(); //instance of random class
        //int upperbound = 2500;
        //_id = rand.nextInt(upperbound);
        LinearLayout view = generateContainer();
        for (int i = 0; _map.containsKey(String.valueOf(i)); i++) {
            CheckBox box = new CheckBox(_activity.get());
            //ToDo: box.setLayoutParams(new LinearLayout.LayoutParams(160, 160));
            box.setTag(_map.get(String.valueOf(i)));
            box.setText(_map.get(String.valueOf(i)));
            box.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            view.setId(_id);
            view.addView(box);
        }
        parent.addView(view);
    }

    @Override
    public String saveViewData() {
        StringBuilder result = new StringBuilder(getName());
        LinearLayout layout = _activity.get().findViewById(_id);
        for (int i = 0; i < layout.getChildCount()-1; i++) {
            View v = layout.getChildAt(i);
            if (v instanceof CheckBox) {
                CheckBox item = (CheckBox) v;
                String text = item.getText().toString();
                if(item.isChecked()){
                    result.append(",").append(text).append(",true");
                }
                else{
                    result.append(",").append(text).append(",false");
                }
            }
        }
        if (result.toString() == getName()) {
            result.append(",null");
            Log.d(TAG, "No CheckBoxes found in " + getName());
        }
        return result.toString();
    }
}