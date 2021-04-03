package team.cake.theredalliance;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Map;
import java.util.Random;

public class Survey_Radio extends Field{
    private final String TAG = "Survey_Radio";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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
    public String saveViewData(){
        StringBuilder result = new StringBuilder(getName());
        LinearLayout layout = _activity.get().findViewById(_id);
        View view = layout.getChildAt(1);

        if(view instanceof RadioGroup) {
            RadioGroup group = (RadioGroup)view;
            int itemID= group.getCheckedRadioButtonId();
            RadioButton item = group.findViewById(itemID);
            String text = item.getText().toString();
            result.append(",selected,").append(text);
        }
        if (result.toString() == getName()) {
            result.append(",null");
            Log.d(TAG, "RadioGroup/Selection Not Found in " + getName());
        }
        Log.d("Saving_Radio", result.toString());
        return result.toString();
    }
}
