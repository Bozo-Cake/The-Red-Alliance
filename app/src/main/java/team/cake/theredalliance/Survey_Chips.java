package team.cake.theredalliance;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Map;

public class Survey_Chips extends Field{
    String _data;
    int _id;
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
        EditText textView = _activity.get().findViewById(_id);
        return textView.getText().toString();
    }
}
