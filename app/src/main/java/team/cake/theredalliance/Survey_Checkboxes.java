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
    public void makeView(ViewGroup parent) {
        LinearLayout view = generateContainer();
        for (int i = 0; _map.containsKey(String.valueOf(i)); i++) {
            CheckBox box = new CheckBox(_activity.get());
            box.setTag(_map.get(String.valueOf(i)));
            box.setText(_map.get(String.valueOf(i)));
            box.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            view.addView(box);
        }
        parent.addView(view);
    }
}