package team.cake.theredalliance;

import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Map;
import java.util.Random;

public class Survey_Radio extends Field{
    String _data;
    int _id;
    public Survey_Radio(Map<String, String> map) {
        super(map);
    }

    public void makeView(ViewGroup parent) {
        LinearLayout view = generateContainer();
        view.setId(_id);
        RadioGroup group = new RadioGroup(_activity.get());
        for (int i = 0; _map.containsKey(String.valueOf(i)); i++) {
            RadioButton button = new RadioButton(_activity.get());
            //ToDo: box.setLayoutParams(new LinearLayout.LayoutParams(160, 160));
            button.setTag(_map.get(String.valueOf(i)));
            button.setText(_map.get(String.valueOf(i)));
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            group.addView(button);
        }
        view.addView(group);
        parent.addView(view);
    }

    @Override
    public String saveViewData() {
        return null;
    }
}
