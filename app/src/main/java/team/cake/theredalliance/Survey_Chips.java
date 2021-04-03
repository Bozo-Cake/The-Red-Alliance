package team.cake.theredalliance;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;
import java.util.Map;

public class Survey_Chips extends Field{
    private final String TAG = "Survey_Chips";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    Survey_Chips(Map<String, String> map) {
        super(map);
    }

    public void makeView(ViewGroup parent) {
        LinearLayout view = generateContainer();
        view.setId(_id);
        ChipGroup group = new ChipGroup(_activity.get());
        for (int i = 0; _map.containsKey(String.valueOf(i)); i++) {
            Chip button = new Chip(_activity.get());
            button.setCheckable(true);
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
        if(view instanceof ChipGroup) {
            ChipGroup group = (ChipGroup)view;
            for (int i = 0; i < group.getChildCount()-1; i++) {
                View v = layout.getChildAt(i);
                if (v instanceof Chip) {
                    Chip item = (Chip) v;
                    String text = item.getText().toString();
                    if(item.isChecked()){
                        result.append(",").append(text).append(",true");
                    }
                    else{
                        result.append(",").append(text).append(",false");
                    }
                }
            }
        }
        if (result.toString() == getName()) {
            result.append(",null");
            Log.d(TAG, "ChipGroup/Selection Not Found in " + getName());
        }
        Log.d("Saving Chips", result.toString());
        return result.toString();
    }
}
