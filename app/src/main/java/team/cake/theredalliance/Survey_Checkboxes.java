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

public class Survey_Checkboxes extends Field {
    int _id;

    public Survey_Checkboxes(Map<String, String> map) {
        super(map);
    }

    @Override
    public void makeView(ViewGroup parent) {
        Random rand = new Random(); //instance of random class
        int upperbound = 2500;
        _id = rand.nextInt(upperbound);
        LinearLayout view = generateContainer();
        for (int i = 0; _map.containsKey(String.valueOf(i)); i++) {
            CheckBox box = new CheckBox(_activity.get());
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
        LinearLayout layout = _activity.get().findViewById(_id);
        //TODO Get child checkboxes and check if they are checked. Return string with name of checkbox and if they are checked.
        return "Checked";
    }
}