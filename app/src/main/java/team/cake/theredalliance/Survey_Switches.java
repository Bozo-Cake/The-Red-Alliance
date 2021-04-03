package team.cake.theredalliance;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;


import androidx.annotation.RequiresApi;

import java.util.Map;

public class Survey_Switches extends Field{
    private final String TAG = "Survey_Switches";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Survey_Switches(Map<String, String> map) {
        super(map);
    }
    public void makeView(ViewGroup parent) {
        LinearLayout view = generateContainer();
        view.setId(_id);
        for (int i = 0; _map.containsKey(String.valueOf(i)); i++) {
            Switch switchy = new Switch(_activity.get());
            switchy.setTag(_map.get(String.valueOf(i)));
            switchy.setText(_map.get(String.valueOf(i)));
            switchy.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            view.addView(switchy);
        }
        parent.addView(view);
    }
    @Override
    public String saveViewData() {
        StringBuilder result = new StringBuilder(getName());
        LinearLayout layout = _activity.get().findViewById(_id);
        for (int i = 0; i < layout.getChildCount()-1; i++) {
            View v = layout.getChildAt(i);
            if (v instanceof Switch) {
                Switch item = (Switch) v;
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
            Log.d(TAG, "No Switches found in " + getName());
        }
        Log.d("Saving_Switches", result.toString());
        return result.toString();
    }
}
